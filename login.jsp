<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="hk.org.ha.pbrc.dashboard.constant.AppConstants" %>
<%

String errorMessage = (String)request.getAttribute(AppConstants.PARAM_ERR_MSG);
String username = request.getParameter("username");

%>
<%@ page contentType="text/html;charset=Big5"%>
<html>
  <head>
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
    <link href="css/bootstrap.css" rel="stylesheet" media="screen">
    <style>
      .container {
        margin-right: auto;
        margin-left: auto;
        margin-top: 100px;
        }
    </style>
    <link href="css/bootstrap-responsive.css" rel="stylesheet">
  </head>
  <body>
<jsp:include page="navbar.jsp" ></jsp:include>  
    <div class="container">

      <div class="row">
		<div class="span4 offset4 well">
                <legend>PBRC DASHBOARD</legend>
<% if (errorMessage!=null && !errorMessage.equals("") ) { %>          	
                <div class="alert alert-error">
                <a class="close" data-dismiss="alert" href="#">×</a>
                <%=errorMessage%>
                </div>
<% } %>                
			<form method="POST" action="dashboardservlet" accept-charset="UTF-8">
			<input type="text" id=<%=AppConstants.PARAM_USERNAME%> class="span4" name=<%=AppConstants.PARAM_USERNAME%> <% if(username == null || username.equals("")) { %> placeholder="Username" <% } else { %> value=<%=username%> <% } %>>
			<input type="password" id=<%=AppConstants.PARAM_PASSWORD%> class="span4" name=<%=AppConstants.PARAM_PASSWORD%> placeholder="Password">
                        <input type="hidden" id="cmd" class="span4" name="cmd" value=<%=AppConstants.CMD_LOGIN%>>
                        <!--<label class="checkbox">
                            <input type="checkbox" name=<%=AppConstants.PARAM_REMEMBER%> value="1"> Remember Me
                        </label>-->
			<button type="submit" name="submit" class="btn btn-info btn-block">Sign in</button>
			</form>    
		</div>
	</div>

    </div> <!-- /container -->  
  </body>
</html>