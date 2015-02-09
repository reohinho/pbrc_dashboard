package hk.org.ha.pbrc.dashboard.servlet;

import hk.org.ha.model.session.UserSessionEJB;
import hk.org.ha.pbrc.dashboard.bean.ADUser;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.Enumeration;
import java.util.Hashtable;

import java.util.Vector;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;

import javax.naming.directory.SearchResult;

import javax.servlet.*;
import javax.servlet.http.*;

import com.google.gson.Gson;

import hk.org.ha.model.entiy.ConfigUser;

import javax.naming.InitialContext;


public class LDAPUserSearchServlet extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=Big5";
    @SuppressWarnings("compatibility:-177753502622866956")
    private static final long serialVersionUID = 1L;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doPost(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException,
                                                           IOException {
        doGet(request, response);
    }
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException,
                                                           IOException {
        
        
        String loginUser = request.getParameter("user");
        String loginPassword = request.getParameter("password");
        
        loginUser = "chh068";
        loginPassword = "K123456lc!!";
        
        Gson gson = new Gson();
        
        String servername = "ldapscorp.server.ha.org.hk:389";
        Hashtable envVars = new Hashtable();
        envVars.put(Context.INITIAL_CONTEXT_FACTORY,
                    "com.sun.jndi.ldap.LdapCtxFactory");
        envVars.put(Context.PROVIDER_URL, "ldap://"+servername);
        envVars.put(Context.SECURITY_AUTHENTICATION, "simple");
        envVars.put(Context.SECURITY_PRINCIPAL, "corp\\" + loginUser);
        envVars.put(Context.SECURITY_CREDENTIALS, loginPassword);
        InitialDirContext myContext = null;
        
        try {
            myContext = new InitialDirContext(envVars);
        } catch (NamingException e) {
            e.printStackTrace();
        }
        
        SearchControls searchCtrls = new SearchControls();
        searchCtrls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        String[] attributes = { "sAMAccountName","cn", "telephoneNumber", "sn", "userPrincipalName","givenName","name","displayName"};           
        searchCtrls.setReturningAttributes(attributes);


        String[] accountList;
        accountList = new String[] { "csf906" };
        String filter = "(&(objectCategory=person)(objectClass=user)(sAMAccountName=KSN134))";
        //String filter = "(&(objectCategory=person)(objectClass=user)(displayName=*KONG*))";
        //String filter = "(&(objectCategory=person)(objectClass=user)(displayName=*EA IIIA*))";
        
        NamingEnumeration values = null;
        try {
            values = myContext.search("ou=Hospitals,DC=corp,DC=ha,DC=org,DC=hk",filter, searchCtrls);
        } catch (NamingException e) {
            e.printStackTrace();
        }
        
        Vector resultList = new Vector();
        while (values.hasMoreElements()) {
            try {
                ADUser user;
                user = new ADUser();
                SearchResult result = (SearchResult)values.next();
                Attributes attribs = result.getAttributes();
                if (null != attribs) {
                    for (NamingEnumeration ae = attribs.getAll(); ae.hasMoreElements(); ) {
                        Attribute atr = (Attribute)ae.next();
                        String attributeID = atr.getID();
                        for (Enumeration vals = atr.getAll(); vals.hasMoreElements(); ) 
                        {                            
                            if(attributeID.equals("sAMAccountName")) {
                                user.setUsername((String)vals.nextElement());
                            }
                            else if(attributeID.equals("givenName")) {
                                user.setGivenName((String)vals.nextElement());
                            }
                            else if(attributeID.equals("sn")) {
                                user.setLastName((String)vals.nextElement());
                            }
                            else if(attributeID.equals("displayName")) {
                                user.setDisplayName((String)vals.nextElement());
                            }
                            else if(attributeID.equals("userPrincipalName")) {
                                user.setEmail((String)vals.nextElement());   
                            }
                            else if(attributeID.equals("telephoneNumber")) {
                                user.setTelephone((String)vals.nextElement());
                            }
                            else {
                                vals.nextElement();
                            }
                        }
                    }
                    InitialContext context = new InitialContext();
                    UserSessionEJB userSession = (UserSessionEJB)context.lookup("UserSessionEJB#hk.org.ha.model.session.UserSessionEJB");
                    user.setExistsInPBRC(userSession.checkUserExists(user.getUsername()));
                    resultList.add(user);                   
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            
        }     
        try {
            myContext.close();
        }
        catch (NamingException e) {
            e.printStackTrace();
        }
        
        
        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        out.println(gson.toJson(resultList));        
        out.close();
    } 
}
