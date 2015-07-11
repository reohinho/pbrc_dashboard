<html>
  <head>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script src="js/justgage.1.0.1.js"></script>
    <script src="js/raphael.2.1.0.min.js"></script>
    <script type='text/javascript' src='https://www.google.com/jsapi'></script>    
    <link id="boostrap-css" href="css/bootstrap.css" rel="stylesheet" media="screen">
    <link id="font-awesone-css" href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/bootstrap-responsive.css" rel="stylesheet">
    <script type="text/javascript">

    google.load('visualization', '1', {    packages: ['corechart','gauge']  });
    google.setOnLoadCallback(drawGauge);

function drawGauge() {
  
    var mbarData = $.ajax({
          url: "pmimeasureservlet?subType=MINIBAR_PROCESSING",
          dataType:"text",
          async: false
          }).responseText;            
          
    var mbarGage = new JustGage({
        id: "pmi_mbar_gauge", 
        value: Number(mbarData), 
        min: 0,
        max: 100,
        title: "PMI(Minibar)"
    }); 
   
    var srcData = $.ajax({
          url: "pmimeasureservlet?subType=SOURCE_PROCESSING",
          dataType:"text",
          async: false
          }).responseText;            
          
    var srcGage = new JustGage({
        id: "pmi_src_gauge", 
        value: Number(srcData), 
        min: 0,
        max: 100,
        title: "PMI(Minibar)"
    }); 
  
   setInterval(function() {
    $.get('pmimeasureservlet?subType=MINIBAR_PROCESSING', function (newValue) { mbarGage.refresh(newValue); });
    $.get('pmimeasureservlet?subType=SOURCE_PROCESSING', function (newValue) { srcGage.refresh(newValue); });
    }, 30000 ); 

}

$(document).ready(function(){

});

      </script>
  </head>
  <body>
  <div class="navbar">
  <div class="navbar-inner">
    <a class="brand" href="#">DASH BOARD</a>
      <ul class="nav">
        <li class="active"><a href="production.jsp">PMI</a></li>
        <li><a href="trend">GCRS</a></li>
        <li><a href="workflow.jsp">WORKFLOW</a></li>
      </ul>
  </div>  
  </div>
  <div class=row><div id=pmi_mbar_gauge class="200x160px"></div><div id=pmi_src_gauge class="200x160px"></div></div>
  </body>
</html>