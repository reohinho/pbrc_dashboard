<html>
  <head>
<%@ include file = "head.jsp"%>
    <style type="text/css">
      html, body {
        background-color: #eee;
      }
      .container {
          margin-left:40px; 
          margin-right:auto;
      }
      .container > footer p {
        text-align: center; 
      }

      .content {
        background-color: #fff;
        padding: 20px;
        margin: 0 -20px; 
        -webkit-border-radius: 0 0 6px 6px;
           -moz-border-radius: 0 0 6px 6px;
                border-radius: 0 0 6px 6px;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.15);
           -moz-box-shadow: 0 1px 2px rgba(0,0,0,.15);
                box-shadow: 0 1px 2px rgba(0,0,0,.15);
      }

      .page-header {
        background-color: #f5f5f5;
        padding: 20px 20px 10px;
        margin: -20px -20px 20px;
      }

      .topbar .btn {
        border: 0;
      }
      .span3 {
        width: 240px;
      }

    </style>
    <script type="text/javascript">

    google.load('visualization', '1', {    packages: ['corechart','gauge']  });
    google.setOnLoadCallback(drawCharts);

function drawCharts() {
    drawIMMGauge();
    //drawTable();
}

function drawIMMGauge() {   
    
    var mbarData = $.ajax({
          url: "pmimeasureservlet?isCR=Y&subType=MINIBAR_PROCESSING",
          dataType:"text",
          async: false
          }).responseText;            
          
    var mbarGage = new JustGage({
        id: "pmi_mbar_gauge", 
        value: Number(mbarData), 
        min: 0,
        max: 150,
        title: "Minibar"
    }); 
   
    var srcData = $.ajax({
          url: "pmimeasureservlet?isCR=Y&subType=SOURCE_PROCESSING",
          dataType:"text",
          async: false
          }).responseText;            
          
    var srcGage = new JustGage({
        id: "pmi_src_gauge", 
        value: Number(srcData), 
        min: 0,
        max: 150,
        title: "Source System"
    });   
    
    var opas_qeh_mbar_json = $.ajax({
          url: "immmeasureservlet?isCR=Y&measureType=OPAS-BAR-QEH&subType=MINIBAR_PROCESSING",
          dataType:"text",
          async: false
          }).responseText;
          
    var opas_qeh_mbar_gauge = new JustGage({
        id: "opas_qeh_mbar_gauge", 
        value: Number(opas_qeh_mbar_json), 
        min: 0,
        max: 200,
        title: "Minibar"
    }); 
    
    var opas_qeh_src_json = $.ajax({
          url: "immmeasureservlet?isCR=Y&measureType=OPAS-BAR-QEH&subType=SOURCE_PROCESSING",
          dataType:"text",
          async: false
          }).responseText;
          
    var opas_qeh_src_gauge = new JustGage({
        id: "opas_qeh_src_gauge", 
        value: Number(opas_qeh_src_json), 
        min: 0,
        max: 120,
        title: "Source System"
    });    
    
    var lis_qeh_mbar_json = $.ajax({
          url: "immmeasureservlet?isCR=Y&measureType=GCRS-LIS-QEH&subType=MINIBAR_PROCESSING",
          dataType:"text",
          async: false
          }).responseText;
          
    var lis_qeh_mbar_gauge = new JustGage({
        id: "lis_qeh_mbar_gauge", 
        value: Number(lis_qeh_mbar_json), 
        min: 0,
        max: 200,
        title: "Minibar"
    }); 
    
    var lis_qeh_src_json = $.ajax({
          url: "immmeasureservlet?isCR=Y&measureType=GCRS-LIS-QEH&subType=SOURCE_PROCESSING",
          dataType:"text",
          async: false
          }).responseText;
          
    var lis_qeh_src_gauge = new JustGage({
        id: "lis_qeh_src_gauge", 
        value: Number(lis_qeh_src_json), 
        min: 0,
        max: 120,
        title: "Source System"
    }); 
    
    var ris_qeh_mbar_json = $.ajax({
          url: "immmeasureservlet?isCR=Y&measureType=GCRS-RIS-QEH&subType=MINIBAR_PROCESSING",
          dataType:"text",
          async: false
          }).responseText;
          
    var ris_qeh_mbar_gauge = new JustGage({
        id: "ris_qeh_mbar_gauge", 
        value: Number(ris_qeh_mbar_json), 
        min: 0,
        max: 200,
        title: "Minibar"
    }); 
    
    var ris_qeh_src_json = $.ajax({
          url: "immmeasureservlet?isCR=Y&measureType=GCRS-RIS-QEH&subType=SOURCE_PROCESSING",
          dataType:"text",
          async: false
          }).responseText;
          
    var ris_qeh_src_gauge = new JustGage({
        id: "ris_qeh_src_gauge", 
        value: Number(ris_qeh_src_json), 
        min: 0,
        max: 120,
        title: "Source System"
    }); 
       
    var opas_qmh_mbar_json = $.ajax({
          url: "immmeasureservlet?isCR=Y&measureType=OPAS-BAR-QMH&subType=MINIBAR_PROCESSING",
          dataType:"text",
          async: false
          }).responseText;
          
    var opas_qmh_mbar_gauge = new JustGage({
        id: "opas_qmh_mbar_gauge", 
        value: Number(opas_qmh_mbar_json), 
        min: 0,
        max: 200,
        title: "Minibar"
    }); 
    
    var opas_qmh_src_json = $.ajax({
          url: "immmeasureservlet?isCR=Y&measureType=OPAS-BAR-QMH&subType=SOURCE_PROCESSING",
          dataType:"text",
          async: false
          }).responseText;
          
    var opas_qmh_src_gauge = new JustGage({
        id: "opas_qmh_src_gauge", 
        value: Number(opas_qmh_src_json), 
        min: 0,
        max: 120,
        title: "Source System"
    });    
    
    var lis_qmh_mbar_json = $.ajax({
          url: "immmeasureservlet?isCR=Y&measureType=GCRS-LIS-QMH&subType=MINIBAR_PROCESSING",
          dataType:"text",
          async: false
          }).responseText;
          
    var lis_qmh_mbar_gauge = new JustGage({
        id: "lis_qmh_mbar_gauge", 
        value: Number(lis_qmh_mbar_json), 
        min: 0,
        max: 200,
        title: "Minibar"
    }); 
    
    var lis_qmh_src_json = $.ajax({
          url: "immmeasureservlet?isCR=Y&measureType=GCRS-LIS-QMH&subType=SOURCE_PROCESSING",
          dataType:"text",
          async: false
          }).responseText;
          
    var lis_qmh_src_gauge = new JustGage({
        id: "lis_qmh_src_gauge", 
        value: Number(lis_qmh_src_json), 
        min: 0,
        max: 120,
        title: "Source System"
    }); 
    
    var ris_qmh_mbar_json = $.ajax({
          url: "immmeasureservlet?isCR=Y&measureType=GCRS-RIS-QMH&subType=MINIBAR_PROCESSING",
          dataType:"text",
          async: false
          }).responseText;
          
    var ris_qmh_mbar_gauge = new JustGage({
        id: "ris_qmh_mbar_gauge", 
        value: Number(ris_qmh_mbar_json), 
        min: 0,
        max: 200,
        title: "Minibar"
    }); 
     
    var ris_qmh_src_json = $.ajax({
          url: "immmeasureservlet?isCR=Y&measureType=GCRS-RIS-QMH&subType=SOURCE_PROCESSING",
          dataType:"text",
          async: false
          }).responseText;
          
    var ris_qmh_src_gauge = new JustGage({
        id: "ris_qmh_src_gauge", 
        value: Number(ris_qmh_src_json), 
        min: 0,
        max: 120,
        title: "Source System"
    }); 
    
    
    var opas_pwh_mbar_json = $.ajax({
          url: "immmeasureservlet?isCR=Y&measureType=OPAS-BAR-PWH&subType=MINIBAR_PROCESSING",
          dataType:"text",
          async: false
          }).responseText;
          
    var opas_pwh_mbar_gauge = new JustGage({
        id: "opas_pwh_mbar_gauge", 
        value: Number(opas_pwh_mbar_json), 
        min: 0,
        max: 200,
        title: "Minibar"
    }); 
    
    var opas_pwh_src_json = $.ajax({
          url: "immmeasureservlet?isCR=Y&measureType=OPAS-BAR-PWH&subType=SOURCE_PROCESSING",
          dataType:"text",
          async: false
          }).responseText;
          
    var opas_pwh_src_gauge = new JustGage({
        id: "opas_pwh_src_gauge", 
        value: Number(opas_pwh_src_json), 
        min: 0,
        max: 120,
        title: "Source System"
    });  
    

    var lis_pwh_mbar_json = $.ajax({
          url: "immmeasureservlet?isCR=Y&measureType=GCRS-LIS-PWH&subType=MINIBAR_PROCESSING",
          dataType:"text",
          async: false
          }).responseText;
          
    var lis_pwh_mbar_gauge = new JustGage({
        id: "lis_pwh_mbar_gauge", 
        value: Number(lis_pwh_mbar_json), 
        min: 0,
        max: 200,
        title: "Minibar"
    }); 
    
    var lis_pwh_src_json = $.ajax({
          url: "immmeasureservlet?isCR=Y&measureType=GCRS-LIS-PWH&subType=SOURCE_PROCESSING",
          dataType:"text",
          async: false
          }).responseText;
          
    var lis_pwh_src_gauge = new JustGage({
        id: "lis_pwh_src_gauge", 
        value: Number(lis_pwh_src_json), 
        min: 0,
        max: 120,
        title: "Source System"
    }); 
    
    var ris_pwh_mbar_json = $.ajax({
          url: "immmeasureservlet?isCR=Y&measureType=GCRS-RIS-PWH&subType=MINIBAR_PROCESSING",
          dataType:"text",
          async: false
          }).responseText;
          
    var ris_pwh_mbar_gauge = new JustGage({
        id: "ris_pwh_mbar_gauge", 
        value: Number(ris_pwh_mbar_json), 
        min: 0,
        max: 200,
        title: "Minibar"
    }); 
    
    var ris_pwh_src_json = $.ajax({
          url: "immmeasureservlet?isCR=Y&measureType=GCRS-RIS-PWH&subType=SOURCE_PROCESSING",
          dataType:"text",
          async: false
          }).responseText;
          
    var ris_pwh_src_gauge = new JustGage({
        id: "ris_pwh_src_gauge", 
        value: Number(ris_pwh_src_json), 
        min: 0,
        max: 120,
        title: "Source System"
    }); 
    
    $('#lastUpd').load('dashboardmeasureservlet');
    
    setInterval(function() {
    $('#lastUpd').load('dashboardmeasureservlet');
    $.get('pmimeasureservlet?isCR=Y&subType=MINIBAR_PROCESSING', function (newValue) { mbarGage.refresh(newValue); });
    $.get('pmimeasureservlet?isCR=Y&subType=SOURCE_PROCESSING', function (newValue) { srcGage.refresh(newValue); });
    $.get('immmeasureservlet?isCR=Y&measureType=OPAS-BAR-QEH&subType=MINIBAR_PROCESSING', function (newValue) { opas_qeh_mbar_gauge.refresh(newValue); });
    $.get('immmeasureservlet?isCR=Y&measureType=OPAS-BAR-QEH&subType=SOURCE_PROCESSING', function (newValue) { opas_qeh_src_gauge.refresh(newValue); });
    $.get('immmeasureservlet?isCR=Y&measureType=GCRS-LIS-QEH&subType=MINIBAR_PROCESSING', function (newValue) { lis_qeh_mbar_gauge.refresh(newValue); });
    $.get('immmeasureservlet?isCR=Y&measureType=GCRS-LIS-QEH&subType=SOURCE_PROCESSING', function (newValue) { lis_qeh_src_gauge.refresh(newValue); });
    $.get('immmeasureservlet?isCR=Y&measureType=GCRS-RIS-QEH&subType=MINIBAR_PROCESSING', function (newValue) { ris_qeh_mbar_gauge.refresh(newValue); });
    $.get('immmeasureservlet?isCR=Y&measureType=GCRS-RIS-QEH&subType=SOURCE_PROCESSING', function (newValue) { ris_qeh_src_gauge.refresh(newValue); });   
    $.get('immmeasureservlet?isCR=Y&measureType=OPAS-BAR-QMH&subType=MINIBAR_PROCESSING', function (newValue) { opas_qmh_mbar_gauge.refresh(newValue); });
    $.get('immmeasureservlet?isCR=Y&measureType=OPAS-BAR-QMH&subType=SOURCE_PROCESSING', function (newValue) { opas_qmh_src_gauge.refresh(newValue); });
    $.get('immmeasureservlet?isCR=Y&measureType=GCRS-LIS-QMH&subType=MINIBAR_PROCESSING', function (newValue) { lis_qmh_mbar_gauge.refresh(newValue); });
    $.get('immmeasureservlet?isCR=Y&measureType=GCRS-LIS-QMH&subType=SOURCE_PROCESSING', function (newValue) { lis_qmh_src_gauge.refresh(newValue); });
    $.get('immmeasureservlet?isCR=Y&measureType=GCRS-RIS-QMH&subType=MINIBAR_PROCESSING', function (newValue) { ris_qmh_mbar_gauge.refresh(newValue); });
    $.get('immmeasureservlet?isCR=Y&measureType=GCRS-RIS-QMH&subType=SOURCE_PROCESSING', function (newValue) { ris_qmh_src_gauge.refresh(newValue); });
    $.get('immmeasureservlet?isCR=Y&measureType=OPAS-BAR-PWH&subType=MINIBAR_PROCESSING', function (newValue) { opas_pwh_mbar_gauge.refresh(newValue); });
    $.get('immmeasureservlet?isCR=Y&measureType=OPAS-BAR-PWH&subType=SOURCE_PROCESSING', function (newValue) { opas_pwh_src_gauge.refresh(newValue); });
    $.get('immmeasureservlet?isCR=Y&measureType=GCRS-LIS-PWH&subType=MINIBAR_PROCESSING', function (newValue) { lis_pwh_mbar_gauge.refresh(newValue); });
    $.get('immmeasureservlet?isCR=Y&measureType=GCRS-LIS-PWH&subType=SOURCE_PROCESSING', function (newValue) { lis_pwh_src_gauge.refresh(newValue); });
    $.get('immmeasureservlet?isCR=Y&measureType=GCRS-RIS-PWH&subType=MINIBAR_PROCESSING', function (newValue) { ris_pwh_mbar_gauge.refresh(newValue); });
    $.get('immmeasureservlet?isCR=Y&measureType=GCRS-RIS-PWH&subType=SOURCE_PROCESSING', function (newValue) { ris_pwh_src_gauge.refresh(newValue); });    
    }, 10000 ); 
    
}

    </script>
  </head>
  <body>
<jsp:include page="navbar.jsp" >
    <jsp:param name="menu" value="cutover" />
</jsp:include>   
  <div class="container" align=left>

      <div class="content">
        <div class="page-header">
          <div id=lastUpd>Last Updated: </div>
        </div>                 
          <div class=row>
              <div class=span4 align=left style="width:350px"><span class="label label-info">PMI</span></div>  
              <div class=span4 align=left></div>
              <div class=span4 align=left></div>
          </div>
          <div class=row>
              <div class=span2 id=pmi_mbar_gauge style="width:160px; height:120px" align=center></div>
              <div class=span2 id=pmi_src_gauge style="width:160px; height:120px" align=center></div>   
              <div class=span4 align=left></div>
              <div class=span4 align=left></div>                            
          </div>
          <div class=row>
              <div class=span4 style="width:350px" align=left><span class="label label-info">QEH-OPAS</span></div>          
              <div class=span4 style="width:350px" align=left><span class="label label-info">QEH-GCRS-LIS</span></div>
              <div class=span4 style="width:350px" align=left><span class="label label-info">QEH-GCRS-RIS</span></div>              
          </div>
          <div class=row>
              <div class=span2 id=opas_qeh_mbar_gauge style="width:160px; height:120px" align=center></div>    
              <div class=span2 id=opas_qeh_src_gauge style="width:160px; height:120px" align=center></div>              
              <div class=span2 id=lis_qeh_mbar_gauge style="width:160px; height:120px" align=center></div>
              <div class=span2 id=lis_qeh_src_gauge style="width:160px; height:120px" align=center></div>
              <div class=span2 id=ris_qeh_mbar_gauge style="width:160px; height:120px" align=center></div>
              <div class=span2 id=ris_qeh_src_gauge style="width:160px; height:120px" align=center></div>    
          </div>      
          <div class=row>
              <div class=span4 style="width:350px" align=left><span class="label label-info">QMH-OPAS</span></div>          
              <div class=span4 style="width:350px" align=left><span class="label label-info">QMH-GCRS-LIS</span></div>
              <div class=span4 style="width:350px" align=left><span class="label label-info">QMH-GCRS-RIS</span></div>
          </div>
          <div class=row>
              <div class=span2 id=opas_qmh_mbar_gauge style="width:160px; height:120px" align=center></div>    
              <div class=span2 id=opas_qmh_src_gauge style="width:160px; height:120px" align=center></div>               
              <div class=span2 id=lis_qmh_mbar_gauge style="width:160px; height:120px" align=center></div>
              <div class=span2 id=lis_qmh_src_gauge style="width:160px; height:120px" align=center></div>
              <div class=span2 id=ris_qmh_mbar_gauge style="width:160px; height:120px" align=center></div>
              <div class=span2 id=ris_qmh_src_gauge style="width:160px; height:120px" align=center></div>    
          </div>      
          <div class=row>
              <div class=span4 style="width:350px" align=left><span class="label label-info">PWH-OPAS</span></div>
              <div class=span4 style="width:350px" align=left><span class="label label-info">PWH-GCRS-LIS</span></div>
              <div class=span4 style="width:350px" align=left><span class="label label-info">PWH-GCRS-RIS</span></div>                                
          </div>
          <div class=row>
              <div class=span2 id=opas_pwh_mbar_gauge style="width:160px; height:120px" align=center></div>    
              <div class=span2 id=opas_pwh_src_gauge style="width:160px; height:120px" align=center></div>      
              <div class=span2 id=lis_pwh_mbar_gauge style="width:160px; height:120px" align=center></div>
              <div class=span2 id=lis_pwh_src_gauge style="width:160px; height:120px" align=center></div>
              <div class=span2 id=ris_pwh_mbar_gauge style="width:160px; height:120px" align=center></div>
              <div class=span2 id=ris_pwh_src_gauge style="width:160px; height:120px" align=center></div>              
          </div>      
      </div>
      
  </div>
      
  </body>
</html>