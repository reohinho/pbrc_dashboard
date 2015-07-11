package hk.org.ha.pbrc.dashboard.utilities;

import hk.org.ha.pbrc.dashboard.constant.AppConstants;

import java.util.Hashtable;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.InitialDirContext;

public class LDAPAuthenticator {
    public String doAuthenticate(String username, String password) {
        String result = "";
        Hashtable envVars = new Hashtable();
        envVars.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        envVars.put(Context.PROVIDER_URL, "ldap://" + AppConstants.LDAP_URL);
        envVars.put(Context.SECURITY_AUTHENTICATION, "simple");        
        
        if (username != null) {
            envVars.put(Context.SECURITY_PRINCIPAL, "corp\\"+username);
        }
        if (password != null) {
            envVars.put(Context.SECURITY_CREDENTIALS, password);
        }
        try {
            InitialDirContext myContext = new InitialDirContext(envVars);   
            myContext.close();
            result = AppConstants.LDAP_OK;
        } catch (AuthenticationException e) {
            result = AppConstants.LDAP_AUTHEN_FAIL;
            e.printStackTrace();
        } catch (NamingException ne) {
            result = AppConstants.LDAP_NAMING_EXCEPTION;         
            ne.printStackTrace();
        }        
        return result;
    }
}
