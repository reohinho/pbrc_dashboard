<html>
<head>
<%@ include file = "head.jsp"%>
<script src="http://code.highcharts.com/highcharts.js"></script>
<script src="http://code.highcharts.com/modules/exporting.js"></script>
<script src="js/grid-light.js"></script>
<script type="text/javascript">
google.setOnLoadCallback(drawPmiChart);          
google.setOnLoadCallback(drawOpasChart);   
google.setOnLoadCallback(drawGcrsChart);   
Highcharts.setOptions(Highcharts.theme);
var pmiOptions = {
    chart: {
            renderTo: 'pmiContainer',
            type: 'bar'
        },
    title: {
        text: 'PMI'
    },
    xAxis: {
        categories: ['PMH', 'PWH', 'QEH', 'QMH', 'PYN', 'UCH', 'TMH']
    },
    yAxis: {
        min: 0,
        title: {
            text: 'Total time consumed'
        }
    },
    legend: {
        reversed: true
    },
    plotOptions: {
        series: {
            stacking: 'normal'
        }
    },
    series: [{ name: 'IMM_DELETE', data: []}, { name: 'IMM_INSERT', data: []}, { name: 'MBCL', data: []}, { name: 'SRC', data: []}]
};

var opasOptions = {
    chart: {
            renderTo: 'opasContainer',
            type: 'bar'
        },
    title: {
        text: 'OPAS'
    },
    xAxis: {
        categories: ['PMH', 'PWH', 'QEH', 'QMH', 'PYN', 'UCH', 'TMH']
    },
    yAxis: {
        min: 0,
        title: {
            text: 'Total time consumed'
        }
    },
    legend: {
        reversed: true
    },
    plotOptions: {
        series: {
            stacking: 'normal'
        }
    },
    series: [{ name: 'IMM_DELETE', data: []}, { name: 'IMM_INSERT', data: []}, { name: 'MBCL', data: []}, { name: 'SRC', data: []}]
};

var gcrsOptions = {
    chart: {
            renderTo: 'gcrsContainer',
            type: 'bar'
        },
    title: {
        text: 'GCRS'
    },
    xAxis: {
        categories: ['PWH(LIS)', 'QEH(LIS)', 'QMH(LIS)', 'PMH(RIS)', 'PWH(RIS)', 'QEH(RIS)', 'QMH(RIS)']
    },
    yAxis: {
        min: 0,
        title: {
            text: 'Total time consumed'
        }
    },
    legend: {
        reversed: true
    },
    plotOptions: {
        series: {
            stacking: 'normal'
        }
    },
    series: [{ name: 'IMM_DELETE', data: []}, { name: 'IMM_INSERT', data: []}, { name: 'MBCL', data: []}, { name: 'SRC', data: []}]
};
function drawPmiChart() {        
    $.ajax({
        url : 'endtoendmeasurementservlet?sourceSys=PMI',
        datatype : 'json',
        success : function (json) {                                                    
            pmiOptions.series[0].data = json['data'];
            pmiOptions.series[1].data = json['data2'];
            pmiOptions.series[2].data = json['data3'];
            pmiOptions.series[3].data = json['data4'];
            chart = new Highcharts.Chart(pmiOptions);            
        },
        error : function (json) {
            alert("JSON error!!");
        }
    });             
}

function drawOpasChart() {        
    $.ajax({
        url : 'endtoendmeasurementservlet?sourceSys=OPAS',
        datatype : 'json',
        success : function (json) {                                                    
            opasOptions.series[0].data = json['data'];
            opasOptions.series[1].data = json['data2'];
            opasOptions.series[2].data = json['data3'];
            opasOptions.series[3].data = json['data4'];
            chart = new Highcharts.Chart(opasOptions);
        },
        error : function (json) {
            alert("JSON error!!");
        }
    });             
}

function drawGcrsChart() {        
    $.ajax({
        url : 'endtoendmeasurementservlet?sourceSys=GCRSLIS',
        datatype : 'json',
        success : function (json) {                                                    
            gcrsOptions.series[0].data = json['data'];
            gcrsOptions.series[1].data = json['data2'];
            gcrsOptions.series[2].data = json['data3'];
            gcrsOptions.series[3].data = json['data4'];
            chart = new Highcharts.Chart(gcrsOptions);
        },
        error : function (json) {
            alert("JSON error!!");
        }
    });             
}

$(document).ready(function(){
  setInterval(drawPmiChart, 180000 ); 
  setInterval(drawOpasChart, 180000 ); 
  setInterval(drawGcrsChart, 180000 ); 
});
</script>
</head>
<body>
<jsp:include page="navbar.jsp" >
<jsp:param name="menu" value="end2end" />
</jsp:include>  
<div id="pmiContainer" style="min-width: 310px; max-width: 800px; height: 400px; margin: 0 auto"></div>      
<div id="opasContainer" style="min-width: 310px; max-width: 800px; height: 400px; margin: 0 auto"></div>   
<div id="gcrsContainer" style="min-width: 310px; max-width: 800px; height: 400px; margin: 0 auto"></div>   
</body>
</html>