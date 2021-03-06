<html>
  <head>
<%@ include file = "head.jsp"%>
<script type="text/javascript">

google.load('visualization', '1', {    packages: ['corechart','gauge']  });
google.setOnLoadCallback(drawChart);

function drawChart() {
    drawLine();
}

function drawLine() {
  
    var previousDate = $('#datepicker').val();

    var daytimeJson = $.ajax({
          url: "workflowmeasureservlet?type=Daytime&previousDate="+previousDate,
          dataType:"json",
          async: false
          }).responseText;
            
    var dayendJson = $.ajax({
          url: "workflowmeasureservlet?type=Dayend&previousDate="+previousDate,
          dataType:"json",
          async: false
          }).responseText;     
        
    var daytimeData = new google.visualization.DataTable(daytimeJson);    
    
    var dayendData = new google.visualization.DataTable(dayendJson);   
    
    var daytimeOptions = {
        width: 744,
        height: 400,      
        min: 0,
        chartArea: {'width': '624', 'height': '300'},
        legend: {'position': 'bottom'},
        colors: ['#ADD8E6','#F7C2C0','#0000FF','#FF0000']
    };
    
    var dayendOptions = {
        width: 480,
        height: 400,      
        min: 0,
        chartArea: {'width': '360', 'height': '300'},
        legend: {'position': 'bottom'},
        colors: ['#ADD8E6','#F7C2C0','#0000FF','#FF0000']
     };
     
    var dayTimechart = new google.visualization.LineChart(document.getElementById('workflow_daytime'));
    dayTimechart.draw(daytimeData, daytimeOptions);
    
    var dayendChart = new google.visualization.LineChart(document.getElementById('workflow_dayend'));
    dayendChart.draw(dayendData, dayendOptions);
}

$(document).ready(function(){
    setInterval(drawLine, 120000 ); 
});

</script>
<script>
$(function() {
    $( "#datepicker" ).datepicker({
        dateFormat: "dd-mm-yy",
        onSelect: function(dateText) { drawLine(); }    
    });
});
</script>
  </head>
  <body>
  <div class="navbar">
  <div class="navbar-inner">
    <a class="brand" href="#">DASH BOARD</a>
      <ul class="nav">
        <li><a href="production.jsp">REAL TIME</a></li>
        <li><a href="trend">TREND</a></li>
        <li><a href="workflow.jsp">WORKFLOW</a></li>
        <li class="active"><a href="utilizationchart">SERVER</a></li>        
      </ul>
  </div>  
  </div>  
  <div class=row>
         <div class=span8></div>
         <div class=span4 align=center>
            <div class="well well-small">
                <b>Date: </b><input type="text" id="datepicker" style="margin-bottom: 0px;" placeholder="YYYYMMDD"/>
            </div>
         </div>
  </div>
  <div class=row>
         <div class=span1 style="width:10px"></div>
         <div class=span4 align=left><span class="label label-info">CPU Utilization (OC4J)</span></div>
  </div> 
  <div class=row>
         <div class=span1 style="width:10px"></div>
         <div class=span4 id='cpu_utilization'></div>
  </div>
  </body>
</html>