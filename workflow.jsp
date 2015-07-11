<html>
  <head>
<%@ include file = "head.jsp"%>
<script type="text/javascript">

google.load('visualization', '1.1', {    packages: ['line']  });
google.setOnLoadCallback(drawChart);

function drawChart() {
    drawLine();
    getWorkflowTop20();
    getWorkflowByDtm();
    getWorkflowByOriginCode();
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
        width: 1024,
        height: 330,      
        min: 0,
        colors: ['#ADD8E6','#F7C2C0','#0000FF','#FF0000']
    };
    
    var dayendOptions = {
        width: 1024,
        height: 330,      
        min: 0,
        colors: ['#ADD8E6','#F7C2C0','#0000FF','#FF0000']
     };
     
    var dayTimechart = new google.charts.Line(document.getElementById('workflow_daytime'));
    dayTimechart.draw(daytimeData, daytimeOptions);
    
    var dayendChart = new google.charts.Line(document.getElementById('workflow_dayend'));
    dayendChart.draw(dayendData, dayendOptions);
}

function getWorkflowTop20()
{
   $.ajax({
     url: 'workflowdetailservlet?detailType=top20',
     dataType: 'text',
     success: function(data) {
          $('#div_top20_graph').html(data);
     }
   });
}

function getWorkflowByDtm()
{
   $.ajax({
     url: 'workflowdetailservlet?detailType=bydtm',
     dataType: 'text',
     success: function(data) {
          $('#div_dtm_graph').html(data);
     }
   });
}

function getWorkflowByOriginCode()
{
   $.ajax({
     url: 'workflowdetailservlet?detailType=bycat',
     dataType: 'text',
     success: function(data) {
          $('#div_category_graph').html(data);
     }
   });
}

$(function() {
    $( "#datepicker" ).datepicker({
        dateFormat: "dd-mm-yy",
        onSelect: function(dateText) { drawLine(); }    
    });
});

if (top.location != location) {
    top.location.href = document.location.href ;
}

$(function(){
window.prettyPrint && prettyPrint();
$('#startDtm').datepicker({
    format: 'yyyy-mm-dd'
});

$('#endDtm').datepicker({
    format: 'yyyy-mm-dd'
});

// disabling dates
var nowTemp = new Date();

});


$(document).ready(function() {
  $('#top20-button').click(function(e) {
    e.preventDefault();
    $('div#div-top20').toggle('500');
    $("div#div-category").hide();
    $("div#div-dtm").hide();
  });
  $('#origin-button').click(function(e) {
    e.preventDefault();
    $('div#div-category').toggle('500');
    $("div#div-top20").hide();
    $("div#div-dtm").hide();
  });
  $('#top20-dtm-button').click(function(e) {
    e.preventDefault();
    $('div#div-dtm').toggle('500');
    $("div#div-top20").hide();
    $("div#div-category").hide();
  });
});

$(document).ready(function(){
    setInterval(drawLine, 300000 ); 
    setInterval(getWorkflowTop20, 300000 ); 
    setInterval(getWorkflowByDtm, 300000 ); 
    setInterval(getWorkflowByOriginCode, 300000 ); 
});

</script>

<style>
.table td{font-size:14}
</style>
  </head>
<body>
<jsp:include page="navbar.jsp" >
    <jsp:param name="menu" value="workflow" />
</jsp:include>  
  <div class=row>
         <div class=span1></div>
         <div class=span6>
         <button type="button" class="btn btn-default" id="top20-button">TOP 20</button>
         <button type="button" class="btn btn-default" id="top20-dtm-button">BY DATETIME</button>
         <button type="button" class="btn btn-default" id="origin-button">BY ORIGIN CODE</button>
         </div>
         <div class=span5 align=center>
            <div class="well well-small">
                <b>Compare with: </b><input type="text" id="datepicker" style="margin-bottom: 0px;" placeholder="Yesterday (Default)"/>
            </div>
         </div>
  </div>  
  <div style="display: none;" id="div-top20">
  <div class=row>
  <div class=span1 style="width:10px"></div>
  <div class=span11><span class="label label-info">TOP 20</span></div>  
  </div>
  <div class=row>
    <div class=span1 style="width:10px"></div>
    <div class=span11 id="div_top20_graph"></div>     
  </div>
  </div>
  <div style="display: none;" id="div-dtm">
  <div class=row>
    <div class=span1 style="width:10px"></div>
    <div class=span11><span class="label label-info">BY DATETIME</span></div>  
  </div>
  <div class=row>
    <div class=span1 style="width:10px"></div>
    <div class=span11 id="div_dtm_graph"></div>     
  </div>
  </div>  
  <div style="display: none;" id="div-category">
  <div class=row>
    <div class=span1 style="width:10px"></div>
    <div class=span11><span class="label label-info">BY ORIGIN CODE</span></div>  
  </div>
  <div class=row>
    <div class=span1 style="width:10px"></div>
    <div class=span11 id="div_category_graph"></div>     
  </div>
  </div>
  <div class=row>
         <div class=span1 style="width:10px"></div>
         <div class=span4 align=left><span class="label label-info">Workflow Dayend</span></div> 
  </div> 
  <div class=row style="margin: 10 0 20 0">
         <div class=span1 style="width:10px"></div>
         <div class=span4 id='workflow_dayend'></div>
  </div>
  <div class=row>
         <div class=span1 style="width:10px"></div>
         <div class=span4 align=left><span class="label label-info">Workflow Online</span></div>     
  </div> 
  <div class=row style="margin: 10 0 20 0">
         <div class=span1 style="width:10px"></div>
         <div class=span4 id='workflow_daytime'></div>
  </div>
  
  </body>
</html>