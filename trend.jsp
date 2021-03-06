<html>
  <head>
<%@ include file = "head.jsp"%>
    <script type="text/javascript">

    google.load('visualization', '1', {    packages: ['corechart','gauge']  });
    google.setOnLoadCallback(drawChart);

function drawChart() {
    drawTrend();
}

function drawTrend() {
    var jsonGcrsTrend = $.ajax({
          url: "gcrstrendservlet",
          dataType:"json",
          async: false
          }).responseText;
          
    var dataGcrsTrend = new google.visualization.DataTable(jsonGcrsTrend);    

    var trendOptions = {
        width: 1000,
        height: 400,      
        min: 0,
        colors: ['#ADD8E6','#F7C2C0','#0000FF','#FF0000'],
        legend: "top"
    };
    
    var GcrsTrendChart = new google.visualization.LineChart(document.getElementById('gcrs_trend_line'));
    GcrsTrendChart.draw(dataGcrsTrend, trendOptions);       
    
    var jsonPmiTrend = $.ajax({
          url: "pmitrendservlet",
          dataType:"json",
          async: false
          }).responseText;
          
    var dataPmiTrend = new google.visualization.DataTable(jsonPmiTrend);    
    
    var PmiTrendChart = new google.visualization.LineChart(document.getElementById('pmi_trend_line'));
    PmiTrendChart.draw(dataPmiTrend, trendOptions);
    
    
    var immPmiTrendOptions = {
        width: 1000,
        height: 400,      
        min: 0,
        colors: ['#0000FF','#ADD8E6'],
        legend: "top"
    };
    
    var jsonImmPmiTrend = $.ajax({
          url: "immpmitrendservlet",
          dataType:"json",
          async: false
          }).responseText;
          
    var dataImmPmiTrend = new google.visualization.DataTable(jsonImmPmiTrend);    
    
    var immPmiTrendChart = new google.visualization.LineChart(document.getElementById('imm_pmi_trend_line'));
    immPmiTrendChart.draw(dataImmPmiTrend, immPmiTrendOptions);
}


$(document).ready(function(){
    setInterval(drawTrend, 150000)
});

      </script>
  </head>
<body>
<jsp:include page="navbar.jsp" >
    <jsp:param name="menu" value="trend" />
</jsp:include>   
  <div class=row><div class=span2 align=center><span class="label label-info">PMI Trend</span></div></div>
  <div class=row id='pmi_trend_line'></div>
  <div class=row><div class=span2 align=center><span class="label label-info">GCRS Trend</span></div></div>
  <div class=row id='gcrs_trend_line'></div>
  <div class=row><div class=span2 align=center><span class="label label-info">IMM PMI</span></div></div>
  <div class=row id='imm_pmi_trend_line'></div>
  </body>
</html>