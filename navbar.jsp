<div class="navbar">
<div class="navbar-inner">
<%
String menu = request.getParameter("menu");
%>
<a class="brand" href="#"><i class="fa fa-bar-chart ">&nbsp;DASH BOARD</i></a>
<% if (menu!=null && !menu.equals("")) { %> 
  <ul class="nav">
    <li <%if(menu!=null&&menu.equals("production")){ %>class="active"<%}%>><a href="dashboardservlet?cmd=p">PRODUCTION</a></li>  
    <li <%if(menu!=null&&menu.equals("end2end")){ %>class="active"<%}%>><a href="dashboardservlet?cmd=e">END TO END</a></li>
    <li <%if(menu!=null&&menu.equals("trend")){ %>class="active"<%}%>><a href="dashboardservlet?cmd=t">TREND</a></li>
    <li <%if(menu!=null&&menu.equals("workflow")){ %>class="active"<%}%>><a href="dashboardservlet?cmd=w">WORKFLOW</a></li>
    <li <%if(menu!=null&&menu.equals("report")){ %>class="active"<%}%>><a href="dashboardservlet?cmd=r">REPORT</a></li>
  </ul>
<% } %>  
</div>  
</div> 