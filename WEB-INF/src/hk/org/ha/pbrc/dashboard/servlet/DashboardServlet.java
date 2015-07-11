package hk.org.ha.pbrc.dashboard.servlet;

import hk.org.ha.pbrc.dashboard.constant.AppConstants;

import hk.org.ha.pbrc.dashboard.utilities.LDAPAuthenticator;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class DashboardServlet extends HttpServlet {
    
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException,
                                                           IOException { 
        String cmd = request.getParameter("cmd");
        ServletContext context= getServletContext();
        RequestDispatcher rd = null;
        if (cmd == null) {
            rd = context.getRequestDispatcher(AppConstants.PAGE_LOGIN);
        }
        else if (cmd.equals(AppConstants.CMD_PRODUCTION)) {
            rd = context.getRequestDispatcher(AppConstants.PAGE_PRODUCTION);
        }
        else if (cmd.equals(AppConstants.CMD_CUTOVER)) {
            rd = context.getRequestDispatcher(AppConstants.PAGE_CUTOVER);
        }
        else if (cmd.equals(AppConstants.CMD_END2END)) {
            rd = context.getRequestDispatcher(AppConstants.PAGE_END2END);
        }
        else if (cmd.equals(AppConstants.CMD_TREND)) {
            rd = context.getRequestDispatcher(AppConstants.PAGE_TREND);
        }
        else if (cmd.equals(AppConstants.CMD_WORKFLOW)) {
            rd = context.getRequestDispatcher(AppConstants.PAGE_WORKFLOW);
        }      
        else if (cmd.equals(AppConstants.CMD_REPORT)) {
            rd = context.getRequestDispatcher(AppConstants.PAGE_REPORT);
        } 
        else if (cmd.equals(AppConstants.CMD_LOGIN)) {
            String username = request.getParameter(AppConstants.PARAM_USERNAME);
            String password =request.getParameter("password");
            String remember = request.getParameter("remember");
            String result = "";
                
            //LDAPAuthenticator authenticator = new LDAPAuthenticator();
            //result = authenticator.doAuthenticate(username, password);
			result = AppConstants.LDAP_OK;
            request.setAttribute(AppConstants.PARAM_ERR_MSG, result);

            if (result!=null && result.equals(AppConstants.LDAP_OK)) {
                request.getSession().setAttribute(AppConstants.PARAM_USERNAME, username);
                if(remember!=null && remember.equals("1")) {
                    Cookie mycookie = new Cookie(AppConstants.PARAM_DASHBOARD_USER,username);
                    response.addCookie(mycookie);
                }
                rd = context.getRequestDispatcher(AppConstants.PAGE_PRODUCTION);
            }
            else {
                rd = context.getRequestDispatcher(AppConstants.PAGE_LOGIN);
            }
            
        }
        else if (cmd.equals(AppConstants.CMD_DNSLOOKUP)){
        	InetAddress inetAddress = InetAddress.getLocalHost();
			displayStuff("local host", inetAddress);
			System.out.print("--------------------------");
			inetAddress = InetAddress.getByName("api.dropbox.com");
			displayStuff("api.dropbox.com", inetAddress);
			System.out.print("--------------------------");
			InetAddress[] inetAddressArray = InetAddress.getAllByName("api.dropbox.com");
			for (int i = 0; i < inetAddressArray.length; i++) {
				displayStuff("api.dropbox.com #" + (i + 1), inetAddressArray[i]);
			}
        }
        rd.forward(request, response);
    }
    
    public void doPost(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException,
                                                           IOException { 
        doGet(request, response);
    }
    
    public static void displayStuff(String whichHost, InetAddress inetAddress) {
		System.out.println("--------------------------");
		System.out.println("Which Host:" + whichHost);
		System.out.println("Canonical Host Name:" + inetAddress.getCanonicalHostName());
		System.out.println("Host Name:" + inetAddress.getHostName());
		System.out.println("Host Address:" + inetAddress.getHostAddress());
	}
}
