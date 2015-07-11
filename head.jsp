<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<%@ page import="hk.org.ha.pbrc.dashboard.constant.AppConstants" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.List" %>
<title>PBRC DASHBOARD</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script src="js/justgage.1.0.1.js"></script>
<script src="js/raphael.2.1.0.min.js"></script>
<script type='text/javascript' src='https://www.google.com/jsapi'></script>    
<link id="boostrap-css" href="css/bootstrap.css" rel="stylesheet" media="screen">
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
<link rel="stylesheet" href="css/bootstrap-responsive.css">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<%
    String cmd = request.getParameter("cmd");
    String path = request.getServletPath();
    String contextPath = request.getContextPath();
    String username = (String)request.getSession().getAttribute(AppConstants.PARAM_USERNAME) ;
    if (username == null || username.equals("")) { //checks if there is username set in session
        Cookie cookies[] = request.getCookies();
        //get username from cookies if any
        if (cookies!=null) {
            for(int i=0 ; i < cookies.length; i++) {
                if (cookies[i].getName().equals(AppConstants.PARAM_DASHBOARD_USER)) {
                    username = cookies[i].getValue();
                    request.getSession().setAttribute(AppConstants.PARAM_USERNAME, username);
                }
            }    
        }
        
        //redirect to login.jsp if no username and page is not from login.jsp
        if (username==null || username.equals("")) {                  
                response.sendRedirect(contextPath);
        } 

    }
%>
  