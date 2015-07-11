package hk.org.ha.pbrc.dashboard.filter;

import hk.org.ha.pbrc.dashboard.constant.AppConstants;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionCheckFilter implements Filter {

  private String contextPath;

  @Override
  public void init(FilterConfig fc) throws ServletException {
    contextPath = fc.getServletContext().getContextPath();
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc) throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse res = (HttpServletResponse) response;  
    
    String cmd = req.getParameter("cmd");
    String path = req.getServletPath();
                   
    if (req.getSession().getAttribute(AppConstants.PARAM_USERNAME) == null) { //checks if there is username set in session
        String username = "";
        Cookie cookies[] = req.getCookies();
        //get username from cookies if any
        if (cookies!=null) {
            for(int i=0 ; i < cookies.length; i++) {
                if (cookies[i].getName().equals(AppConstants.PARAM_DASHBOARD_USER)) {
                    username = cookies[i].getValue();
                    req.getSession().setAttribute(AppConstants.PARAM_USERNAME, username);
                }
            }    
        }
        
        //redirect to login.jsp if no username and page is not from login.jsp
        if (username==null || username.equals("")) {      
            if ( path.indexOf("login.jsp")<0 && (cmd!=null && !( cmd.equals("") || cmd.equals(AppConstants.CMD_LOGIN) ))) {
                res.sendRedirect(contextPath+"?cmd="+cmd); //redirect                
            }
            else {
                res.sendRedirect(contextPath);
            }
        } 
        else {
            res.sendRedirect(contextPath+"?cmd=p"); //redirect
        }
    }
    else {
        fc.doFilter(req, res);
    }

  }

  @Override
  public void destroy() {
  }
}