package hk.org.ha.pbrc.dashboard.scheduler;

import hk.org.ha.pbrc.dashboard.bean.ReportMeasureBean;
import hk.org.ha.pbrc.dashboard.dao.ReportMeasureDao;

import java.util.Vector;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;

import org.apache.commons.mail.HtmlEmail;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ReportMonitorJob implements Job {
   
    public void execute(JobExecutionContext context) throws JobExecutionException {
    
      System.out.println("Cron executing ");
      try {
          
          // add handlers for main MIME types
          MailcapCommandMap mc = (MailcapCommandMap)CommandMap.getDefaultCommandMap();
          mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
          mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
          mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
          mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
          mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
          CommandMap.setDefaultCommandMap(mc);
          
          HtmlEmail email = new HtmlEmail();         
          email.setHostName("MAILDEVSMTP");          
          email.addTo("ylm182@ha.org.hk");
          email.addTo("chh068@ha.org.hk");
          email.setFrom("pbrcadm@gmail.com", "PBRC Support Gmail");
          email.setSubject("Report Monitor");          
           
          String msg = "<html>\n" + 
          "<head>\n" + 
          "    <meta http-equiv=\"Content-Type\" content=\"text/html; x-java-content-handler=com.sun.mail.handlers.text_html; charset=utf-8\">\n" + 
          "    <title>PBRC Report Monitor</title>\n" + 
          "        <style media=\"all\" type=\"text/css\">\n" + 
          "             article,aside,details,figcaption,figure,footer,header,hgroup,nav,section{display:block}\n" + 
          "		audio,canvas,video{display:inline-block;*display:inline;*zoom:1}\n" + 
          "		audio:not([controls]){display:none}\n" + 
          "		html{font-size:100%;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%}\n" + 
          "		a:focus{outline:thin dotted #333;outline:5px auto -webkit-focus-ring-color;outline-offset:-2px}\n" + 
          "		a:hover,a:active{outline:0}\n" + 
          "		sub,sup{position:relative;font-size:75%;line-height:0;vertical-align:baseline}\n" + 
          "		sup{top:-0.5em}\n" + 
          "		sub{bottom:-0.25em}\n" + 
          "		img{max-width:100%;width:auto\\9;height:auto;vertical-align:middle;border:0;-ms-interpolation-mode:bicubic}\n" + 
          "		#map_canvas img{max-width:none}\n" + 
          "		button,input,select,textarea{margin:0;font-size:100%;vertical-align:middle}\n" + 
          "		button,input{*overflow:visible;line-height:normal}\n" + 
          "		button::-moz-focus-inner,input::-moz-focus-inner{padding:0;border:0}\n" + 
          "		button,input[type=\"button\"],input[type=\"reset\"],input[type=\"submit\"]{cursor:pointer;-webkit-appearance:button}\n" + 
          "		input[type=\"search\"]{-webkit-box-sizing:content-box;-moz-box-sizing:content-box;box-sizing:content-box;-webkit-appearance:textfield}\n" + 
          "		input[type=\"search\"]::-webkit-search-decoration,input[type=\"search\"]::-webkit-search-cancel-button{-webkit-appearance:none}\n" + 
          "		textarea{overflow:auto;vertical-align:top}\n" + 
          "		.clearfix{*zoom:1}\n" + 
          "		.clearfix:before,.clearfix:after{display:table;content:\"\";line-height:0}\n" + 
          "		.clearfix:after{clear:both}\n" + 
          "		.hide-text{font:0/0 a;color:transparent;text-shadow:none;background-color:transparent;border:0}\n" + 
          "		.input-block-level{display:block;width:100%;min-height:30px;-webkit-box-sizing:border-box;-moz-box-sizing:border-box;box-sizing:border-box}\n" + 
          "		body{margin:0;font-family:\"Segoe UI\",\"Helvetica Neue\",Helvetica,Arial,sans-serif;font-size:13px;line-height:20px;color:#333;background-color:#fff}\n" + 
          "		a{color:#0063ca;text-decoration:none}\n" + 
          "		a:hover{color:#003e7e;text-decoration:underline}\n" + 
          "		.img-rounded{-webkit-border-radius:6px;-moz-border-radius:6px;border-radius:6px}\n" + 
          "		.img-polaroid{padding:4px;background-color:#fff;border:1px solid #ccc;border:1px solid rgba(0,0,0,0.2);-webkit-box-shadow:0 1px 3px rgba(0,0,0,0.1);-moz-box-shadow:0 1px 3px rgba(0,0,0,0.1);box-shadow:0 1px 3px rgba(0,0,0,0.1)}\n" + 
          "		.img-circle{-webkit-border-radius:500px;-moz-border-radius:500px;border-radius:500px}\n" + 
          "		.row{margin-left:-20px;*zoom:1}\n" + 
          "		.row:before,.row:after{display:table;content:\"\";line-height:0}\n" + 
          "		.row:after{clear:both}\n" + 
          "		[class*=\"span\"]{float:left;min-height:1px;margin-left:20px}\n" + 
          "		.container,.navbar-static-top .container,.navbar-fixed-top .container,.navbar-fixed-bottom .container{width:940px}\n" + 
          "		.span12{width:940px}\n" + 
          "		.span11{width:860px}\n" + 
          "		.span10{width:780px}\n" + 
          "		.span9{width:700px}\n" + 
          "		.span8{width:620px}\n" + 
          "		.span7{width:540px}\n" + 
          "		.span6{width:460px}\n" + 
          "		.span5{width:380px}\n" + 
          "		.span4{width:300px}\n" + 
          "		.span3{width:220px}\n" + 
          "		.span2{width:140px}\n" + 
          "		.span1{width:60px}\n" + 
          "		.offset12{margin-left:980px}\n" + 
          "		.offset11{margin-left:900px}\n" + 
          "		.offset10{margin-left:820px}\n" + 
          "		.offset9{margin-left:740px}\n" + 
          "		.offset8{margin-left:660px}\n" + 
          "		.offset7{margin-left:580px}\n" + 
          "		.offset6{margin-left:500px}\n" + 
          "		.offset5{margin-left:420px}\n" + 
          "		.offset4{margin-left:340px}\n" + 
          "		.offset3{margin-left:260px}\n" + 
          "		.offset2{margin-left:180px}\n" + 
          "		.offset1{margin-left:100px}\n" + 
          "		.row-fluid{width:100%;*zoom:1}\n" + 
          "		.row-fluid:before,.row-fluid:after{display:table;content:\"\";line-height:0}\n" + 
          "		.row-fluid:after{clear:both}\n" + 
          "		.row-fluid [class*=\"span\"]{display:block;width:100%;min-height:30px;-webkit-box-sizing:border-box;-moz-box-sizing:border-box;box-sizing:border-box;float:left;margin-left:2.127659574468085%;*margin-left:2.074468085106383%}\n" + 
          "		.row-fluid [class*=\"span\"]:first-child{margin-left:0}\n" + 
          "		.row-fluid .span12{width:100%;*width:99.94680851063829%}\n" + 
          "		.row-fluid .span11{width:91.48936170212765%;*width:91.43617021276594%}\n" + 
          "		.row-fluid .span10{width:82.97872340425532%;*width:82.92553191489361%}\n" + 
          "		.row-fluid .span9{width:74.46808510638297%;*width:74.41489361702126%}\n" + 
          "		.row-fluid .span8{width:65.95744680851064%;*width:65.90425531914893%}\n" + 
          "		.row-fluid .span7{width:57.44680851063829%;*width:57.39361702127659%}\n" + 
          "		.row-fluid .span6{width:48.93617021276595%;*width:48.88297872340425%}\n" + 
          "		.row-fluid .span5{width:40.42553191489362%;*width:40.37234042553192%}\n" + 
          "		.row-fluid .span4{width:31.914893617021278%;*width:31.861702127659576%}\n" + 
          "		.row-fluid .span3{width:23.404255319148934%;*width:23.351063829787233%}\n" + 
          "		.row-fluid .span2{width:14.893617021276595%;*width:14.840425531914894%}\n" + 
          "		.row-fluid .span1{width:6.382978723404255%;*width:6.329787234042553%}\n" + 
          "		.row-fluid .offset12{margin-left:104.25531914893617%;*margin-left:104.14893617021275%}\n" + 
          "		.row-fluid .offset12:first-child{margin-left:102.12765957446808%;*margin-left:102.02127659574467%}\n" + 
          "		.row-fluid .offset11{margin-left:95.74468085106382%;*margin-left:95.6382978723404%}\n" + 
          "		.row-fluid .offset11:first-child{margin-left:93.61702127659574%;*margin-left:93.51063829787232%}\n" + 
          "		.row-fluid .offset10{margin-left:87.23404255319149%;*margin-left:87.12765957446807%}\n" + 
          "		.row-fluid .offset10:first-child{margin-left:85.1063829787234%;*margin-left:84.99999999999999%}\n" + 
          "		.row-fluid .offset9{margin-left:78.72340425531914%;*margin-left:78.61702127659572%}\n" + 
          "		.row-fluid .offset9:first-child{margin-left:76.59574468085106%;*margin-left:76.48936170212764%}\n" + 
          "		.row-fluid .offset8{margin-left:70.2127659574468%;*margin-left:70.10638297872339%}\n" + 
          "		.row-fluid .offset8:first-child{margin-left:68.08510638297872%;*margin-left:67.9787234042553%}\n" + 
          "		.row-fluid .offset7{margin-left:61.70212765957446%;*margin-left:61.59574468085106%}\n" + 
          "		.row-fluid .offset7:first-child{margin-left:59.574468085106375%;*margin-left:59.46808510638297%}\n" + 
          "		.row-fluid .offset6{margin-left:53.191489361702125%;*margin-left:53.085106382978715%}\n" + 
          "		.row-fluid .offset6:first-child{margin-left:51.063829787234035%;*margin-left:50.95744680851063%}\n" + 
          "		.row-fluid .offset5{margin-left:44.68085106382979%;*margin-left:44.57446808510638%}\n" + 
          "		.row-fluid .offset5:first-child{margin-left:42.5531914893617%;*margin-left:42.4468085106383%}\n" + 
          "		.row-fluid .offset4{margin-left:36.170212765957444%;*margin-left:36.06382978723405%}\n" + 
          "		.row-fluid .offset4:first-child{margin-left:34.04255319148936%;*margin-left:33.93617021276596%}\n" + 
          "		.row-fluid .offset3{margin-left:27.659574468085104%;*margin-left:27.5531914893617%}\n" + 
          "		.row-fluid .offset3:first-child{margin-left:25.53191489361702%;*margin-left:25.425531914893618%}\n" + 
          "		.row-fluid .offset2{margin-left:19.148936170212764%;*margin-left:19.04255319148936%}\n" + 
          "		.row-fluid .offset2:first-child{margin-left:17.02127659574468%;*margin-left:16.914893617021278%}\n" + 
          "		.row-fluid .offset1{margin-left:10.638297872340425%;*margin-left:10.53191489361702%}\n" + 
          "		.row-fluid .offset1:first-child{margin-left:8.51063829787234%;*margin-left:8.404255319148938%}\n" + 
          "		[class*=\"span\"].hide,.row-fluid [class*=\"span\"].hide{display:none}\n" + 
          "		[class*=\"span\"].pull-right,.row-fluid [class*=\"span\"].pull-right{float:right}\n" + 
          "		.container{margin-right:auto;margin-left:auto;*zoom:1}\n" + 
          "		.container:before,.container:after{display:table;content:\"\";line-height:0}\n" + 
          "		.container:after{clear:both}\n" + 
          "		.container-fluid{padding-right:20px;padding-left:20px;*zoom:1}\n" + 
          "		.container-fluid:before,.container-fluid:after{display:table;content:\"\";line-height:0}\n" + 
          "		.container-fluid:after{clear:both}\n" + 
          "		p{margin:0 0 10px}\n" + 
          "		.lead{margin-bottom:20px;font-size:19.5px;font-weight:200;line-height:30px}\n" + 
          "		small{font-size:85%}\n" + 
          "		strong{font-weight:bold}\n" + 
          "		em{font-style:italic}\n" + 
          "		cite{font-style:normal}\n" + 
          "		.muted{color:#999}\n" + 
          "		.text-warning{color:#c09853}\n" + 
          "		.text-error{color:#b94a48}\n" + 
          "		.text-info{color:#3a87ad}\n" + 
          "		.text-success{color:#468847}\n" + 
          "		h1,h2,h3,h4,h5,h6{margin:10px 0;font-family:inherit;font-weight:bold;line-height:1;color:inherit;text-rendering:optimizelegibility}\n" + 
          "		h1 small,h2 small,h3 small,h4 small,h5 small,h6 small{font-weight:normal;line-height:1;color:#999}\n" + 
          "		h1{font-size:36px;line-height:40px}\n" + 
          "		h2{font-size:30px;line-height:40px}\n" + 
          "		h3{font-size:24px;line-height:40px}\n" + 
          "		h4{font-size:18px;line-height:20px}\n" + 
          "		h5{font-size:14px;line-height:20px}\n" + 
          "		h6{font-size:12px;line-height:20px}\n" + 
          "		h1 small{font-size:24px}\n" + 
          "		h2 small{font-size:18px}\n" + 
          "		h3 small{font-size:14px}\n" + 
          "		h4 small{font-size:14px}\n" + 
          "		.page-header{padding-bottom:9px;margin:20px 0 30px;border-bottom:1px solid #eee}\n" + 
          "		ul,ol{padding:0;margin:0 0 10px 25px}\n" + 
          "		ul ul,ul ol,ol ol,ol ul{margin-bottom:0}\n" + 
          "		li{line-height:20px}\n" + 
          "		ul.unstyled,ol.unstyled{margin-left:0;list-style:none}\n" + 
          "		dl{margin-bottom:20px}\n" + 
          "		dt,dd{line-height:20px}\n" + 
          "		dt{font-weight:bold}\n" + 
          "		dd{margin-left:10px}\n" + 
          "		.dl-horizontal{*zoom:1}\n" + 
          "		.dl-horizontal:before,.dl-horizontal:after{display:table;content:\"\";line-height:0}\n" + 
          "		.dl-horizontal:after{clear:both}\n" + 
          "		.dl-horizontal dt{float:left;width:160px;clear:left;text-align:right;overflow:hidden;text-overflow:ellipsis;white-space:nowrap}\n" + 
          "		.dl-horizontal dd{margin-left:180px}\n" + 
          "		hr{margin:20px 0;border:0;border-top:1px solid #eee;border-bottom:1px solid #fff}\n" + 
          "		abbr[title]{cursor:help;border-bottom:1px dotted #999}\n" + 
          "		abbr.initialism{font-size:90%;text-transform:uppercase}\n" + 
          "		blockquote{padding:0 0 0 15px;margin:0 0 20px;border-left:5px solid #eee}\n" + 
          "		blockquote p{margin-bottom:0;font-size:16px;font-weight:300;line-height:25px}\n" + 
          "		blockquote small{display:block;line-height:20px;color:#999}\n" + 
          "		blockquote small:before{content:'\\2014 \\00A0'}\n" + 
          "		blockquote.pull-right{float:right;padding-right:15px;padding-left:0;border-right:5px solid #eee;border-left:0}\n" + 
          "		blockquote.pull-right p,blockquote.pull-right small{text-align:right}\n" + 
          "		blockquote.pull-right small:before{content:''}\n" + 
          "		blockquote.pull-right small:after{content:'\\00A0 \\2014'}\n" + 
          "		q:before,q:after,blockquote:before,blockquote:after{content:\"\"}\n" + 
          "		address{display:block;margin-bottom:20px;font-style:normal;line-height:20px}\n" + 
          "		code,pre{padding:0 3px 2px;font-family:Monaco,Menlo,Consolas,\"Courier New\",monospace;font-size:11px;color:#333;-webkit-border-radius:3px;-moz-border-radius:3px;border-radius:3px}\n" + 
          "		code{padding:2px 4px;color:#d14;background-color:#f7f7f9;border:1px solid #e1e1e8}\n" + 
          "		pre{display:block;padding:9.5px;margin:0 0 10px;font-size:12px;line-height:20px;word-break:break-all;word-wrap:break-word;white-space:pre;white-space:pre-wrap;background-color:#f5f5f5;border:1px solid #ccc;border:1px solid rgba(0,0,0,0.15);-webkit-border-radius:4px;-moz-border-radius:4px;border-radius:4px}\n" + 
          "		pre.prettyprint{margin-bottom:20px}\n" + 
          "		pre code{padding:0;color:inherit;background-color:transparent;border:0}\n" + 
          "		.pre-scrollable{max-height:340px;overflow-y:scroll}\n" + 
          "		table{max-width:100%;background-color:transparent;border-collapse:collapse;border-spacing:0}\n" + 
          "		.table{width:100%;margin-bottom:0px}\n" + 
          "		.table th,.table td{padding:8px;line-height:20px;text-align:left;vertical-align:top;border-top:1px solid #ddd}\n" + 
          "		.table th{font-weight:bold}\n" + 
          "		.table thead th{vertical-align:bottom}\n" + 
          "		.table caption+thead tr:first-child th,.table caption+thead tr:first-child td,.table colgroup+thead tr:first-child th,.table colgroup+thead tr:first-child td,.table thead:first-child tr:first-child th,.table thead:first-child tr:first-child td{border-top:0}\n" + 
          "		.table tbody+tbody{border-top:2px solid #ddd}\n" + 
          "		.table-condensed th,.table-condensed td{padding:0px 0px}\n" + 
          "		.table-bordered{border:1px solid #ddd;border-collapse:separate;*border-collapse:collapse;border-left:0;-webkit-border-radius:4px;-moz-border-radius:4px;border-radius:4px}\n" + 
          "		.table-bordered th,.table-bordered td{border-left:1px solid #ddd}\n" + 
          "		.table-bordered caption+thead tr:first-child th,.table-bordered caption+tbody tr:first-child th,.table-bordered caption+tbody tr:first-child td,.table-bordered colgroup+thead tr:first-child th,.table-bordered colgroup+tbody tr:first-child th,.table-bordered colgroup+tbody tr:first-child td,.table-bordered thead:first-child tr:first-child th,.table-bordered tbody:first-child tr:first-child th,.table-bordered tbody:first-child tr:first-child td{border-top:0}\n" + 
          "		.table-bordered thead:first-child tr:first-child th:first-child,.table-bordered tbody:first-child tr:first-child td:first-child{-webkit-border-top-left-radius:4px;border-top-left-radius:4px;-moz-border-radius-topleft:4px}\n" + 
          "		.table-bordered thead:first-child tr:first-child th:last-child,.table-bordered tbody:first-child tr:first-child td:last-child{-webkit-border-top-right-radius:4px;border-top-right-radius:4px;-moz-border-radius-topright:4px}\n" + 
          "		.table-bordered thead:last-child tr:last-child th:first-child,.table-bordered tbody:last-child tr:last-child td:first-child,.table-bordered tfoot:last-child tr:last-child td:first-child{-webkit-border-radius:0 0 0 4px;-moz-border-radius:0 0 0 4px;border-radius:0 0 0 4px;-webkit-border-bottom-left-radius:4px;border-bottom-left-radius:4px;-moz-border-radius-bottomleft:4px}\n" + 
          "		.table-bordered thead:last-child tr:last-child th:last-child,.table-bordered tbody:last-child tr:last-child td:last-child,.table-bordered tfoot:last-child tr:last-child td:last-child{-webkit-border-bottom-right-radius:4px;border-bottom-right-radius:4px;-moz-border-radius-bottomright:4px}\n" + 
          "		.table-bordered caption+thead tr:first-child th:first-child,.table-bordered caption+tbody tr:first-child td:first-child,.table-bordered colgroup+thead tr:first-child th:first-child,.table-bordered colgroup+tbody tr:first-child td:first-child{-webkit-border-top-left-radius:4px;border-top-left-radius:4px;-moz-border-radius-topleft:4px}\n" + 
          "		.table-bordered caption+thead tr:first-child th:last-child,.table-bordered caption+tbody tr:first-child td:last-child,.table-bordered colgroup+thead tr:first-child th:last-child,.table-bordered colgroup+tbody tr:first-child td:last-child{-webkit-border-top-right-radius:4px;border-top-right-radius:4px;-moz-border-radius-topleft:4px}\n" + 
          "		.table-striped tbody tr:nth-child(odd) td,.table-striped tbody tr:nth-child(odd) th{background-color:#f9f9f9}\n" + 
          "		.table-hover tbody tr:hover td,.table-hover tbody tr:hover th{background-color:#f5f5f5}\n" + 
          "		table [class*=span],.row-fluid table [class*=span]{display:table-cell;float:none;margin-left:0}\n" + 
          "		.table .span1{float:none;width:44px;margin-left:0}\n" + 
          "		.table .span2{float:none;width:124px;margin-left:0}\n" + 
          "		.table .span3{float:none;width:204px;margin-left:0}\n" + 
          "		.table .span4{float:none;width:284px;margin-left:0}\n" + 
          "		.table .span5{float:none;width:364px;margin-left:0}\n" + 
          "		.table .span6{float:none;width:444px;margin-left:0}\n" + 
          "		.table .span7{float:none;width:524px;margin-left:0}\n" + 
          "		.table .span8{float:none;width:604px;margin-left:0}\n" + 
          "		.table .span9{float:none;width:684px;margin-left:0}\n" + 
          "		.table .span10{float:none;width:764px;margin-left:0}\n" + 
          "		.table .span11{float:none;width:844px;margin-left:0}\n" + 
          "		.table .span12{float:none;width:924px;margin-left:0}\n" + 
          "		.table .span13{float:none;width:1004px;margin-left:0}\n" + 
          "		.table .span14{float:none;width:1084px;margin-left:0}\n" + 
          "		.table .span15{float:none;width:1164px;margin-left:0}\n" + 
          "		.table .span16{float:none;width:1244px;margin-left:0}\n" + 
          "		.table .span17{float:none;width:1324px;margin-left:0}\n" + 
          "		.table .span18{float:none;width:1404px;margin-left:0}\n" + 
          "		.table .span19{float:none;width:1484px;margin-left:0}\n" + 
          "		.table .span20{float:none;width:1564px;margin-left:0}\n" + 
          "		.table .span21{float:none;width:1644px;margin-left:0}\n" + 
          "		.table .span22{float:none;width:1724px;margin-left:0}\n" + 
          "		.table .span23{float:none;width:1804px;margin-left:0}\n" + 
          "		.table .span24{float:none;width:1884px;margin-left:0}\n" + 
          "		.table tbody tr.success td{background-color:#dff0d8}\n" + 
          "		.table tbody tr.error td{background-color:#f2dede}\n" + 
          "		.table tbody tr.warning td{background-color:#fcf8e3}\n" + 
          "		.table tbody tr.info td{background-color:#d9edf7}\n" + 
          "		.table-hover tbody tr.success:hover td{background-color:#d0e9c6}\n" + 
          "		.table-hover tbody tr.error:hover td{background-color:#ebcccc}\n" + 
          "		.table-hover tbody tr.warning:hover td{background-color:#faf2cc}\n" + 
          "		.table-hover tbody tr.info:hover td{background-color:#c4e3f3}\n" + 
          "		[class^=\"icon-\"],[class*=\" icon-\"]{display:inline-block;width:14px;height:14px;*margin-right:.3em;line-height:14px;vertical-align:text-top;background-image:url(\"https://az340737.vo.msecnd.net/resources/glyphicons-halflings.png\");background-position:14px 14px;background-repeat:no-repeat;margin-top:1px}\n" + 
          "		.icon-white,.nav-tabs>.active>a>[class^=\"icon-\"],.nav-tabs>.active>a>[class*=\" icon-\"],.nav-pills>.active>a>[class^=\"icon-\"],.nav-pills>.active>a>[class*=\" icon-\"],.nav-list>.active>a>[class^=\"icon-\"],.nav-list>.active>a>[class*=\" icon-\"],.navbar-inverse .nav>.active>a>[class^=\"icon-\"],.navbar-inverse .nav>.active>a>[class*=\" icon-\"],.dropdown-menu>li>a:hover>[class^=\"icon-\"],.dropdown-menu>li>a:hover>[class*=\" icon-\"],.dropdown-menu>.active>a>[class^=\"icon-\"],.dropdown-menu>.active>a>[class*=\" icon-\"]{background-image:url(\"https://az340737.vo.msecnd.net/resources/glyphicons-halflings-white.png\")}\n" + 
          "		.icon-glass{background-position:0 0}\n" + 
          "		.icon-music{background-position:-24px 0}\n" + 
          "		.icon-search{background-position:-48px 0}\n" + 
          "		.icon-envelope{background-position:-72px 0}\n" + 
          "		.icon-heart{background-position:-96px 0}\n" + 
          "		.icon-star{background-position:-120px 0}\n" + 
          "		.icon-star-empty{background-position:-144px 0}\n" + 
          "		.icon-user{background-position:-168px 0}\n" + 
          "		.icon-film{background-position:-192px 0}\n" + 
          "		.icon-th-large{background-position:-216px 0}\n" + 
          "		.icon-th{background-position:-240px 0}\n" + 
          "		.icon-th-list{background-position:-264px 0}\n" + 
          "		.icon-ok{background-position:-288px 0}\n" + 
          "		.icon-remove{background-position:-312px 0}\n" + 
          "		.icon-zoom-in{background-position:-336px 0}\n" + 
          "		.icon-zoom-out{background-position:-360px 0}\n" + 
          "		.icon-off{background-position:-384px 0}\n" + 
          "		.icon-signal{background-position:-408px 0}\n" + 
          "		.icon-cog{background-position:-432px 0}\n" + 
          "		.icon-trash{background-position:-456px 0}\n" + 
          "		.icon-home{background-position:0 -24px}\n" + 
          "		.icon-file{background-position:-24px -24px}\n" + 
          "		.icon-time{background-position:-48px -24px}\n" + 
          "		.icon-road{background-position:-72px -24px}\n" + 
          "		.icon-download-alt{background-position:-96px -24px}\n" + 
          "		.icon-download{background-position:-120px -24px}\n" + 
          "		.icon-upload{background-position:-144px -24px}\n" + 
          "		.icon-inbox{background-position:-168px -24px}\n" + 
          "		.icon-play-circle{background-position:-192px -24px}\n" + 
          "		.icon-repeat{background-position:-216px -24px}\n" + 
          "		.icon-refresh{background-position:-240px -24px}\n" + 
          "		.icon-list-alt{background-position:-264px -24px}\n" + 
          "		.icon-lock{background-position:-287px -24px}\n" + 
          "		.icon-flag{background-position:-312px -24px}\n" + 
          "		.icon-headphones{background-position:-336px -24px}\n" + 
          "		.icon-volume-off{background-position:-360px -24px}\n" + 
          "		.icon-volume-down{background-position:-384px -24px}\n" + 
          "		.icon-volume-up{background-position:-408px -24px}\n" + 
          "		.icon-qrcode{background-position:-432px -24px}\n" + 
          "		.icon-barcode{background-position:-456px -24px}\n" + 
          "		.icon-tag{background-position:0 -48px}\n" + 
          "		.icon-tags{background-position:-25px -48px}\n" + 
          "		.icon-book{background-position:-48px -48px}\n" + 
          "		.icon-bookmark{background-position:-72px -48px}\n" + 
          "		.icon-print{background-position:-96px -48px}\n" + 
          "		.icon-camera{background-position:-120px -48px}\n" + 
          "		.icon-font{background-position:-144px -48px}\n" + 
          "		.icon-bold{background-position:-167px -48px}\n" + 
          "		.icon-italic{background-position:-192px -48px}\n" + 
          "		.icon-text-height{background-position:-216px -48px}\n" + 
          "		.icon-text-width{background-position:-240px -48px}\n" + 
          "		.icon-align-left{background-position:-264px -48px}\n" + 
          "		.icon-align-center{background-position:-288px -48px}\n" + 
          "		.icon-align-right{background-position:-312px -48px}\n" + 
          "		.icon-align-justify{background-position:-336px -48px}\n" + 
          "		.icon-list{background-position:-360px -48px}\n" + 
          "		.icon-indent-left{background-position:-384px -48px}\n" + 
          "		.icon-indent-right{background-position:-408px -48px}\n" + 
          "		.icon-facetime-video{background-position:-432px -48px}\n" + 
          "		.icon-picture{background-position:-456px -48px}\n" + 
          "		.icon-pencil{background-position:0 -72px}\n" + 
          "		.icon-map-marker{background-position:-24px -72px}\n" + 
          "		.icon-adjust{background-position:-48px -72px}\n" + 
          "		.icon-tint{background-position:-72px -72px}\n" + 
          "		.icon-edit{background-position:-96px -72px}\n" + 
          "		.icon-share{background-position:-120px -72px}\n" + 
          "		.icon-check{background-position:-144px -72px}\n" + 
          "		.icon-move{background-position:-168px -72px}\n" + 
          "		.icon-step-backward{background-position:-192px -72px}\n" + 
          "		.icon-fast-backward{background-position:-216px -72px}\n" + 
          "		.icon-backward{background-position:-240px -72px}\n" + 
          "		.icon-play{background-position:-264px -72px}\n" + 
          "		.icon-pause{background-position:-288px -72px}\n" + 
          "		.icon-stop{background-position:-312px -72px}\n" + 
          "		.icon-forward{background-position:-336px -72px}\n" + 
          "		.icon-fast-forward{background-position:-360px -72px}\n" + 
          "		.icon-step-forward{background-position:-384px -72px}\n" + 
          "		.icon-eject{background-position:-408px -72px}\n" + 
          "		.icon-chevron-left{background-position:-432px -72px}\n" + 
          "		.icon-chevron-right{background-position:-456px -72px}\n" + 
          "		.icon-plus-sign{background-position:0 -96px}\n" + 
          "		.icon-minus-sign{background-position:-24px -96px}\n" + 
          "		.icon-remove-sign{background-position:-48px -96px}\n" + 
          "		.icon-ok-sign{background-position:-72px -96px}\n" + 
          "		.icon-question-sign{background-position:-96px -96px}\n" + 
          "		.icon-info-sign{background-position:-120px -96px}\n" + 
          "		.icon-screenshot{background-position:-144px -96px}\n" + 
          "		.icon-remove-circle{background-position:-168px -96px}\n" + 
          "		.icon-ok-circle{background-position:-192px -96px}\n" + 
          "		.icon-ban-circle{background-position:-216px -96px}\n" + 
          "		.icon-arrow-left{background-position:-240px -96px}\n" + 
          "		.icon-arrow-right{background-position:-264px -96px}\n" + 
          "		.icon-arrow-up{background-position:-289px -96px}\n" + 
          "		.icon-arrow-down{background-position:-312px -96px}\n" + 
          "		.icon-share-alt{background-position:-336px -96px}\n" + 
          "		.icon-resize-full{background-position:-360px -96px}\n" + 
          "		.icon-resize-small{background-position:-384px -96px}\n" + 
          "		.icon-plus{background-position:-408px -96px}\n" + 
          "		.icon-minus{background-position:-433px -96px}\n" + 
          "		.icon-asterisk{background-position:-456px -96px}\n" + 
          "		.icon-exclamation-sign{background-position:0 -120px}\n" + 
          "		.icon-gift{background-position:-24px -120px}\n" + 
          "		.icon-leaf{background-position:-48px -120px}\n" + 
          "		.icon-fire{background-position:-72px -120px}\n" + 
          "		.icon-eye-open{background-position:-96px -120px}\n" + 
          "		.icon-eye-close{background-position:-120px -120px}\n" + 
          "		.icon-warning-sign{background-position:-144px -120px}\n" + 
          "		.icon-plane{background-position:-168px -120px}\n" + 
          "		.icon-calendar{background-position:-192px -120px}\n" + 
          "		.icon-random{background-position:-216px -120px;width:16px}\n" + 
          "		.icon-comment{background-position:-240px -120px}\n" + 
          "		.icon-magnet{background-position:-264px -120px}\n" + 
          "		.icon-chevron-up{background-position:-288px -120px}\n" + 
          "		.icon-chevron-down{background-position:-313px -119px}\n" + 
          "		.icon-retweet{background-position:-336px -120px}\n" + 
          "		.icon-shopping-cart{background-position:-360px -120px}\n" + 
          "		.icon-folder-close{background-position:-384px -120px}\n" + 
          "		.icon-folder-open{background-position:-408px -120px;width:16px}\n" + 
          "		.icon-resize-vertical{background-position:-432px -119px}\n" + 
          "		.icon-resize-horizontal{background-position:-456px -118px}\n" + 
          "		.icon-hdd{background-position:0 -144px}\n" + 
          "		.icon-bullhorn{background-position:-24px -144px}\n" + 
          "		.icon-bell{background-position:-48px -144px}\n" + 
          "		.icon-certificate{background-position:-72px -144px}\n" + 
          "		.icon-thumbs-up{background-position:-96px -144px}\n" + 
          "		.icon-thumbs-down{background-position:-120px -144px}\n" + 
          "		.icon-hand-right{background-position:-144px -144px}\n" + 
          "		.icon-hand-left{background-position:-168px -144px}\n" + 
          "		.icon-hand-up{background-position:-192px -144px}\n" + 
          "		.icon-hand-down{background-position:-216px -144px}\n" + 
          "		.icon-circle-arrow-right{background-position:-240px -144px}\n" + 
          "		.icon-circle-arrow-left{background-position:-264px -144px}\n" + 
          "		.icon-circle-arrow-up{background-position:-288px -144px}\n" + 
          "		.icon-circle-arrow-down{background-position:-312px -144px}\n" + 
          "		.icon-globe{background-position:-336px -144px}\n" + 
          "		.icon-wrench{background-position:-360px -144px}\n" + 
          "		.icon-tasks{background-position:-384px -144px}\n" + 
          "		.icon-filter{background-position:-408px -144px}\n" + 
          "		.icon-briefcase{background-position:-432px -144px}\n" + 
          "		.icon-fullscreen{background-position:-456px -144px}\n" + 
          "		.well{min-height:20px;padding:19px;margin-bottom:20px;background-color:#f5f5f5;border:1px solid #e3e3e3;-webkit-border-radius:4px;-moz-border-radius:4px;border-radius:4px;-webkit-box-shadow:inset 0 1px 1px rgba(0,0,0,0.05);-moz-box-shadow:inset 0 1px 1px rgba(0,0,0,0.05);box-shadow:inset 0 1px 1px rgba(0,0,0,0.05)}\n" + 
          "		.well blockquote{border-color:#ddd;border-color:rgba(0,0,0,0.15)}\n" + 
          "		.well-large{padding:24px;-webkit-border-radius:6px;-moz-border-radius:6px;border-radius:6px}\n" + 
          "		.well-small{padding:9px;-webkit-border-radius:3px;-moz-border-radius:3px;border-radius:3px}\n" + 
          "		.btn{display:inline-block;*display:inline;*zoom:1;padding:4px 14px;margin-bottom:0;font-size:13px;line-height:20px;*line-height:20px;text-align:center;vertical-align:middle;cursor:pointer;color:#333;text-shadow:0 1px 1px rgba(255,255,255,0.75);background-color:#f5f5f5;background-image:-moz-linear-gradient(top,#fff,#e6e6e6);background-image:-webkit-gradient(linear,0 0,0 100%,from(#fff),to(#e6e6e6));background-image:-webkit-linear-gradient(top,#fff,#e6e6e6);background-image:-o-linear-gradient(top,#fff,#e6e6e6);background-image:linear-gradient(to bottom,#fff,#e6e6e6);background-repeat:repeat-x;filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#ffffffff',endColorstr='#ffe6e6e6',GradientType=0);border-color:#e6e6e6 #e6e6e6 #bfbfbf;border-color:rgba(0,0,0,0.1) rgba(0,0,0,0.1) rgba(0,0,0,0.25);*background-color:#e6e6e6;filter:progid:DXImageTransform.Microsoft.gradient(enabled = false);border:1px solid #bbb;*border:0;border-bottom-color:#a2a2a2;-webkit-border-radius:4px;-moz-border-radius:4px;border-radius:4px;*margin-left:.3em;-webkit-box-shadow:inset 0 1px 0 rgba(255,255,255,0.2),0 1px 2px rgba(0,0,0,0.05);-moz-box-shadow:inset 0 1px 0 rgba(255,255,255,0.2),0 1px 2px rgba(0,0,0,0.05);box-shadow:inset 0 1px 0 rgba(255,255,255,0.2),0 1px 2px rgba(0,0,0,0.05)}\n" + 
          "		.btn:hover,.btn:active,.btn.active,.btn.disabled,.btn[disabled]{color:#333;background-color:#e6e6e6;*background-color:#d9d9d9}\n" + 
          "		.btn:active,.btn.active{background-color:#ccc \\9}\n" + 
          "		.btn:first-child{*margin-left:0}\n" + 
          "		.btn:hover{color:#333;text-decoration:none;background-color:#e6e6e6;*background-color:#d9d9d9;background-position:0 -15px;-webkit-transition:background-position .1s linear;-moz-transition:background-position .1s linear;-o-transition:background-position .1s linear;transition:background-position .1s linear}\n" + 
          "		.btn:focus{outline:thin dotted #333;outline:5px auto -webkit-focus-ring-color;outline-offset:-2px}\n" + 
          "		.btn.active,.btn:active{background-color:#e6e6e6;background-color:#d9d9d9 \\9;background-image:none;outline:0;-webkit-box-shadow:inset 0 2px 4px rgba(0,0,0,0.15),0 1px 2px rgba(0,0,0,0.05);-moz-box-shadow:inset 0 2px 4px rgba(0,0,0,0.15),0 1px 2px rgba(0,0,0,0.05);box-shadow:inset 0 2px 4px rgba(0,0,0,0.15),0 1px 2px rgba(0,0,0,0.05)}\n" + 
          "		.btn.disabled,.btn[disabled]{cursor:default;background-color:#e6e6e6;background-image:none;opacity:.65;filter:alpha(opacity=65);-webkit-box-shadow:none;-moz-box-shadow:none;box-shadow:none}\n" + 
          "		.btn-large{padding:9px 14px;font-size:15px;line-height:normal;-webkit-border-radius:5px;-moz-border-radius:5px;border-radius:5px}\n" + 
          "		.btn-large [class^=\"icon-\"]{margin-top:2px}\n" + 
          "		.btn-small{padding:3px 9px;font-size:11px;line-height:18px}\n" + 
          "		.btn-small [class^=\"icon-\"]{margin-top:0}\n" + 
          "		.btn-mini{padding:2px 6px;font-size:10px;line-height:17px}\n" + 
          "		.btn-block{display:block;width:100%;padding-left:0;padding-right:0;-webkit-box-sizing:border-box;-moz-box-sizing:border-box;box-sizing:border-box}\n" + 
          "		.btn-block+.btn-block{margin-top:5px}\n" + 
          "		input[type=\"submit\"].btn-block,input[type=\"reset\"].btn-block,input[type=\"button\"].btn-block{width:100%}\n" + 
          "		.btn-primary.active,.btn-warning.active,.btn-danger.active,.btn-success.active,.btn-info.active,.btn-inverse.active{color:rgba(255,255,255,0.75)}\n" + 
          "		.btn{border-color:#c5c5c5;border-color:rgba(0,0,0,0.15) rgba(0,0,0,0.15) rgba(0,0,0,0.25)}\n" + 
          "		.btn-primary{color:#fff;text-shadow:0 -1px 0 rgba(0,0,0,0.25);background-color:#0054ab;background-image:-moz-linear-gradient(top,#0063ca,#003e7e);background-image:-webkit-gradient(linear,0 0,0 100%,from(#0063ca),to(#003e7e));background-image:-webkit-linear-gradient(top,#0063ca,#003e7e);background-image:-o-linear-gradient(top,#0063ca,#003e7e);background-image:linear-gradient(to bottom,#0063ca,#003e7e);background-repeat:repeat-x;filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#ff0063ca',endColorstr='#ff003e7e',GradientType=0);border-color:#003e7e #003e7e #001831;border-color:rgba(0,0,0,0.1) rgba(0,0,0,0.1) rgba(0,0,0,0.25);*background-color:#003e7e;filter:progid:DXImageTransform.Microsoft.gradient(enabled = false)}\n" + 
          "		.btn-primary:hover,.btn-primary:active,.btn-primary.active,.btn-primary.disabled,.btn-primary[disabled]{color:#fff;background-color:#003e7e;*background-color:#003164}\n" + 
          "		.btn-primary:active,.btn-primary.active{background-color:#00254a \\9}\n" + 
          "		.btn-warning{color:#fff;text-shadow:0 -1px 0 rgba(0,0,0,0.25);background-color:#faa732;background-image:-moz-linear-gradient(top,#fbb450,#f89406);background-image:-webkit-gradient(linear,0 0,0 100%,from(#fbb450),to(#f89406));background-image:-webkit-linear-gradient(top,#fbb450,#f89406);background-image:-o-linear-gradient(top,#fbb450,#f89406);background-image:linear-gradient(to bottom,#fbb450,#f89406);background-repeat:repeat-x;filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#fffbb450',endColorstr='#fff89406',GradientType=0);border-color:#f89406 #f89406 #ad6704;border-color:rgba(0,0,0,0.1) rgba(0,0,0,0.1) rgba(0,0,0,0.25);*background-color:#f89406;filter:progid:DXImageTransform.Microsoft.gradient(enabled = false)}\n" + 
          "		.btn-warning:hover,.btn-warning:active,.btn-warning.active,.btn-warning.disabled,.btn-warning[disabled]{color:#fff;background-color:#f89406;*background-color:#df8505}\n" + 
          "		.btn-warning:active,.btn-warning.active{background-color:#c67605 \\9}\n" + 
          "		.btn-danger{color:#fff;text-shadow:0 -1px 0 rgba(0,0,0,0.25);background-color:#da4f49;background-image:-moz-linear-gradient(top,#ee5f5b,#bd362f);background-image:-webkit-gradient(linear,0 0,0 100%,from(#ee5f5b),to(#bd362f));background-image:-webkit-linear-gradient(top,#ee5f5b,#bd362f);background-image:-o-linear-gradient(top,#ee5f5b,#bd362f);background-image:linear-gradient(to bottom,#ee5f5b,#bd362f);background-repeat:repeat-x;filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#ffee5f5b',endColorstr='#ffbd362f',GradientType=0);border-color:#bd362f #bd362f #802420;border-color:rgba(0,0,0,0.1) rgba(0,0,0,0.1) rgba(0,0,0,0.25);*background-color:#bd362f;filter:progid:DXImageTransform.Microsoft.gradient(enabled = false)}\n" + 
          "		.btn-danger:hover,.btn-danger:active,.btn-danger.active,.btn-danger.disabled,.btn-danger[disabled]{color:#fff;background-color:#bd362f;*background-color:#a9302a}\n" + 
          "		.btn-danger:active,.btn-danger.active{background-color:#942a25 \\9}\n" + 
          "		.btn-success{color:#fff;text-shadow:0 -1px 0 rgba(0,0,0,0.25);background-color:#5bb75b;background-image:-moz-linear-gradient(top,#62c462,#51a351);background-image:-webkit-gradient(linear,0 0,0 100%,from(#62c462),to(#51a351));background-image:-webkit-linear-gradient(top,#62c462,#51a351);background-image:-o-linear-gradient(top,#62c462,#51a351);background-image:linear-gradient(to bottom,#62c462,#51a351);background-repeat:repeat-x;filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#ff62c462',endColorstr='#ff51a351',GradientType=0);border-color:#51a351 #51a351 #387038;border-color:rgba(0,0,0,0.1) rgba(0,0,0,0.1) rgba(0,0,0,0.25);*background-color:#51a351;filter:progid:DXImageTransform.Microsoft.gradient(enabled = false)}\n" + 
          "		.btn-success:hover,.btn-success:active,.btn-success.active,.btn-success.disabled,.btn-success[disabled]{color:#fff;background-color:#51a351;*background-color:#499249}\n" + 
          "		.btn-success:active,.btn-success.active{background-color:#408140 \\9}\n" + 
          "		.btn-info{color:#fff;text-shadow:0 -1px 0 rgba(0,0,0,0.25);background-color:#49afcd;background-image:-moz-linear-gradient(top,#5bc0de,#2f96b4);background-image:-webkit-gradient(linear,0 0,0 100%,from(#5bc0de),to(#2f96b4));background-image:-webkit-linear-gradient(top,#5bc0de,#2f96b4);background-image:-o-linear-gradient(top,#5bc0de,#2f96b4);background-image:linear-gradient(to bottom,#5bc0de,#2f96b4);background-repeat:repeat-x;filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#ff5bc0de',endColorstr='#ff2f96b4',GradientType=0);border-color:#2f96b4 #2f96b4 #1f6377;border-color:rgba(0,0,0,0.1) rgba(0,0,0,0.1) rgba(0,0,0,0.25);*background-color:#2f96b4;filter:progid:DXImageTransform.Microsoft.gradient(enabled = false)}\n" + 
          "		.btn-info:hover,.btn-info:active,.btn-info.active,.btn-info.disabled,.btn-info[disabled]{color:#fff;background-color:#2f96b4;*background-color:#2a85a0}\n" + 
          "		.btn-info:active,.btn-info.active{background-color:#24748c \\9}\n" + 
          "		.btn-inverse{color:#fff;text-shadow:0 -1px 0 rgba(0,0,0,0.25);background-color:#363636;background-image:-moz-linear-gradient(top,#444,#222);background-image:-webkit-gradient(linear,0 0,0 100%,from(#444),to(#222));background-image:-webkit-linear-gradient(top,#444,#222);background-image:-o-linear-gradient(top,#444,#222);background-image:linear-gradient(to bottom,#444,#222);background-repeat:repeat-x;filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#ff444444',endColorstr='#ff222222',GradientType=0);border-color:#222 #222222 #000;border-color:rgba(0,0,0,0.1) rgba(0,0,0,0.1) rgba(0,0,0,0.25);*background-color:#222;filter:progid:DXImageTransform.Microsoft.gradient(enabled = false)}\n" + 
          "		.btn-inverse:hover,.btn-inverse:active,.btn-inverse.active,.btn-inverse.disabled,.btn-inverse[disabled]{color:#fff;background-color:#222;*background-color:#151515}\n" + 
          "		.btn-inverse:active,.btn-inverse.active{background-color:#080808 \\9}\n" + 
          "		button.btn,input[type=\"submit\"].btn{*padding-top:3px;*padding-bottom:3px}\n" + 
          "		button.btn::-moz-focus-inner,input[type=\"submit\"].btn::-moz-focus-inner{padding:0;border:0}\n" + 
          "		button.btn.btn-large,input[type=\"submit\"].btn.btn-large{*padding-top:7px;*padding-bottom:7px}\n" + 
          "		button.btn.btn-small,input[type=\"submit\"].btn.btn-small{*padding-top:3px;*padding-bottom:3px}\n" + 
          "		button.btn.btn-mini,input[type=\"submit\"].btn.btn-mini{*padding-top:1px;*padding-bottom:1px}\n" + 
          "		.btn-link,.btn-link:active,.btn-link[disabled]{background-color:transparent;background-image:none;-webkit-box-shadow:none;-moz-box-shadow:none;box-shadow:none}\n" + 
          "		.btn-link{border-color:transparent;cursor:pointer;color:#0063ca;-webkit-border-radius:0;-moz-border-radius:0;border-radius:0}\n" + 
          "		.btn-link:hover{color:#003e7e;text-decoration:underline;background-color:transparent}\n" + 
          "		.btn-link[disabled]:hover{color:#333;text-decoration:none}\n" + 
          "		.btn-group{position:relative;font-size:0;vertical-align:middle;white-space:nowrap;*margin-left:.3em}\n" + 
          "		.btn-group:first-child{*margin-left:0}\n" + 
          "		.btn-group+.btn-group{margin-left:5px}\n" + 
          "		.btn-toolbar{font-size:0;margin-top:10px;margin-bottom:10px}\n" + 
          "		.btn-toolbar .btn-group{display:inline-block;*display:inline;*zoom:1}\n" + 
          "		.btn-toolbar .btn+.btn,.btn-toolbar .btn-group+.btn,.btn-toolbar .btn+.btn-group{margin-left:5px}\n" + 
          "		.btn-group>.btn{position:relative;-webkit-border-radius:0;-moz-border-radius:0;border-radius:0}\n" + 
          "		.btn-group>.btn+.btn{margin-left:-1px}\n" + 
          "		.btn-group>.btn,.btn-group>.dropdown-menu{font-size:13px}\n" + 
          "		.btn-group>.btn-mini{font-size:11px}\n" + 
          "		.btn-group>.btn-small{font-size:12px}\n" + 
          "		.btn-group>.btn-large{font-size:16px}\n" + 
          "		.btn-group>.btn:first-child{margin-left:0;-webkit-border-top-left-radius:4px;-moz-border-radius-topleft:4px;border-top-left-radius:4px;-webkit-border-bottom-left-radius:4px;-moz-border-radius-bottomleft:4px;border-bottom-left-radius:4px}\n" + 
          "		.btn-group>.btn:last-child,.btn-group>.dropdown-toggle{-webkit-border-top-right-radius:4px;-moz-border-radius-topright:4px;border-top-right-radius:4px;-webkit-border-bottom-right-radius:4px;-moz-border-radius-bottomright:4px;border-bottom-right-radius:4px}\n" + 
          "		.btn-group>.btn.large:first-child{margin-left:0;-webkit-border-top-left-radius:6px;-moz-border-radius-topleft:6px;border-top-left-radius:6px;-webkit-border-bottom-left-radius:6px;-moz-border-radius-bottomleft:6px;border-bottom-left-radius:6px}\n" + 
          "		.btn-group>.btn.large:last-child,.btn-group>.large.dropdown-toggle{-webkit-border-top-right-radius:6px;-moz-border-radius-topright:6px;border-top-right-radius:6px;-webkit-border-bottom-right-radius:6px;-moz-border-radius-bottomright:6px;border-bottom-right-radius:6px}\n" + 
          "		.btn-group>.btn:hover,.btn-group>.btn:focus,.btn-group>.btn:active,.btn-group>.btn.active{z-index:2}\n" + 
          "		.btn-group .dropdown-toggle:active,.btn-group.open .dropdown-toggle{outline:0}\n" + 
          "		.btn-group>.btn+.dropdown-toggle{padding-left:8px;padding-right:8px;-webkit-box-shadow:inset 1px 0 0 rgba(255,255,255,0.125),inset 0 1px 0 rgba(255,255,255,0.2),0 1px 2px rgba(0,0,0,0.05);-moz-box-shadow:inset 1px 0 0 rgba(255,255,255,0.125),inset 0 1px 0 rgba(255,255,255,0.2),0 1px 2px rgba(0,0,0,0.05);box-shadow:inset 1px 0 0 rgba(255,255,255,0.125),inset 0 1px 0 rgba(255,255,255,0.2),0 1px 2px rgba(0,0,0,0.05);*padding-top:5px;*padding-bottom:5px}\n" + 
          "		.btn-group>.btn-mini+.dropdown-toggle{padding-left:5px;padding-right:5px;*padding-top:2px;*padding-bottom:2px}\n" + 
          "		.btn-group>.btn-small+.dropdown-toggle{*padding-top:5px;*padding-bottom:4px}\n" + 
          "		.btn-group>.btn-large+.dropdown-toggle{padding-left:12px;padding-right:12px;*padding-top:7px;*padding-bottom:7px}\n" + 
          "		.btn-group.open .dropdown-toggle{background-image:none;-webkit-box-shadow:inset 0 2px 4px rgba(0,0,0,0.15),0 1px 2px rgba(0,0,0,0.05);-moz-box-shadow:inset 0 2px 4px rgba(0,0,0,0.15),0 1px 2px rgba(0,0,0,0.05);box-shadow:inset 0 2px 4px rgba(0,0,0,0.15),0 1px 2px rgba(0,0,0,0.05)}\n" + 
          "		.btn-group.open .btn.dropdown-toggle{background-color:#e6e6e6}\n" + 
          "		.btn-group.open .btn-primary.dropdown-toggle{background-color:#003e7e}\n" + 
          "		.btn-group.open .btn-warning.dropdown-toggle{background-color:#f89406}\n" + 
          "		.btn-group.open .btn-danger.dropdown-toggle{background-color:#bd362f}\n" + 
          "		.btn-group.open .btn-success.dropdown-toggle{background-color:#51a351}\n" + 
          "		.btn-group.open .btn-info.dropdown-toggle{background-color:#2f96b4}\n" + 
          "		.btn-group.open .btn-inverse.dropdown-toggle{background-color:#222}\n" + 
          "		.btn .caret{margin-top:8px;margin-left:0}\n" + 
          "		.btn-mini .caret,.btn-small .caret,.btn-large .caret{margin-top:6px}\n" + 
          "		.btn-large .caret{border-left-width:5px;border-right-width:5px;border-top-width:5px}\n" + 
          "		.dropup .btn-large .caret{border-bottom:5px solid #000;border-top:0}\n" + 
          "		.btn-primary .caret,.btn-warning .caret,.btn-danger .caret,.btn-info .caret,.btn-success .caret,.btn-inverse .caret{border-top-color:#fff;border-bottom-color:#fff}\n" + 
          "		.btn-group-vertical{display:inline-block;*display:inline;*zoom:1}\n" + 
          "		.btn-group-vertical .btn{display:block;float:none;width:100%;-webkit-border-radius:0;-moz-border-radius:0;border-radius:0}\n" + 
          "		.btn-group-vertical .btn+.btn{margin-left:0;margin-top:-1px}\n" + 
          "		.btn-group-vertical .btn:first-child{-webkit-border-radius:4px 4px 0 0;-moz-border-radius:4px 4px 0 0;border-radius:4px 4px 0 0}\n" + 
          "		.btn-group-vertical .btn:last-child{-webkit-border-radius:0 0 4px 4px;-moz-border-radius:0 0 4px 4px;border-radius:0 0 4px 4px}\n" + 
          "		.btn-group-vertical .btn-large:first-child{-webkit-border-radius:6px 6px 0 0;-moz-border-radius:6px 6px 0 0;border-radius:6px 6px 0 0}\n" + 
          "		.btn-group-vertical .btn-large:last-child{-webkit-border-radius:0 0 6px 6px;-moz-border-radius:0 0 6px 6px;border-radius:0 0 6px 6px}\n" + 
          "		.alert{padding:8px 35px 8px 14px;margin-bottom:20px;text-shadow:0 1px 0 rgba(255,255,255,0.5);background-color:#fcf8e3;border:1px solid #fbeed5;-webkit-border-radius:4px;-moz-border-radius:4px;border-radius:4px;color:#c09853}\n" + 
          "		.alert h4{margin:0}\n" + 
          "		.alert .close{position:relative;top:-2px;right:-21px;line-height:20px}\n" + 
          "		.alert-success{background-color:#dff0d8;border-color:#d6e9c6;color:#468847}\n" + 
          "		.alert-danger,.alert-error{background-color:#f2dede;border-color:#eed3d7;color:#b94a48}\n" + 
          "		.alert-info{background-color:#d9edf7;border-color:#bce8f1;color:#3a87ad}\n" + 
          "		.alert-block{padding-top:14px;padding-bottom:14px}\n" + 
          "		.alert-block>p,.alert-block>ul{margin-bottom:0}\n" + 
          "		.alert-block p+p{margin-top:5px}\n" + 
          "		.nav{margin-left:0;margin-bottom:20px;list-style:none}\n" + 
          "		.nav>li>a{display:block}\n" + 
          "		.nav>li>a:hover{text-decoration:none;background-color:#eee}\n" + 
          "		.nav>.pull-right{float:right}\n" + 
          "		.nav-header{display:block;padding:3px 15px;font-size:11px;font-weight:bold;line-height:20px;color:#999;text-shadow:0 1px 0 rgba(255,255,255,0.5);text-transform:uppercase}\n" + 
          "		.nav li+.nav-header{margin-top:9px}\n" + 
          "		.nav-list{padding-left:15px;padding-right:15px;margin-bottom:0}\n" + 
          "		.nav-list>li>a,.nav-list .nav-header{margin-left:-15px;margin-right:-15px;text-shadow:0 1px 0 rgba(255,255,255,0.5)}\n" + 
          "		.nav-list>li>a{padding:3px 15px}\n" + 
          "		.nav-list>.active>a,.nav-list>.active>a:hover{color:#fff;text-shadow:0 -1px 0 rgba(0,0,0,0.2);background-color:#0063ca}\n" + 
          "		.nav-list [class^=\"icon-\"]{margin-right:2px}\n" + 
          "		.nav-list .divider{*width:100%;height:1px;margin:9px 1px;*margin:-5px 0 5px;overflow:hidden;background-color:#e5e5e5;border-bottom:1px solid #fff}\n" + 
          "		.nav>.disabled>a{color:#999}\n" + 
          "		.nav>.disabled>a:hover{text-decoration:none;background-color:transparent;cursor:default}\n" + 
          "		.navbar{overflow:visible;margin-bottom:20px;color:#777;*position:relative;*z-index:2}\n" + 
          "		.navbar-inner{min-height:40px;padding-left:20px;padding-right:20px;background-color:#fafafa;background-image:-moz-linear-gradient(top,#fff,#f2f2f2);background-image:-webkit-gradient(linear,0 0,0 100%,from(#fff),to(#f2f2f2));background-image:-webkit-linear-gradient(top,#fff,#f2f2f2);background-image:-o-linear-gradient(top,#fff,#f2f2f2);background-image:linear-gradient(to bottom,#fff,#f2f2f2);background-repeat:repeat-x;filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#ffffffff',endColorstr='#fff2f2f2',GradientType=0);border:1px solid #d4d4d4;-webkit-border-radius:4px;-moz-border-radius:4px;border-radius:4px;-webkit-box-shadow:0 1px 4px rgba(0,0,0,0.065);-moz-box-shadow:0 1px 4px rgba(0,0,0,0.065);box-shadow:0 1px 4px rgba(0,0,0,0.065);*zoom:1}\n" + 
          "		.navbar-inner:before,.navbar-inner:after{display:table;content:\"\";line-height:0}\n" + 
          "		.navbar-inner:after{clear:both}\n" + 
          "		.navbar .container{width:auto}\n" + 
          "		.nav-collapse.collapse{height:auto}\n" + 
          "		.navbar .brand{float:left;display:block;padding:4px 20px 0;margin-left:-20px;font-size:20px;font-weight:200;color:#777;text-shadow:0 1px 0 #fff}\n" + 
          "		.navbar .brand:hover{text-decoration:none}\n" + 
          "		.navbar-text{margin-bottom:0;line-height:40px}\n" + 
          "		.navbar-link{color:#777}\n" + 
          "		.navbar-link:hover{color:#333}\n" + 
          "		.navbar .divider-vertical{height:40px;margin:0 9px;border-left:1px solid #f2f2f2;border-right:1px solid #fff}\n" + 
          "		.navbar .btn,.navbar .btn-group{margin-top:5px}\n" + 
          "		.navbar .btn-group .btn,.navbar .input-prepend .btn,.navbar .input-append .btn{margin-top:0}\n" + 
          "		.navbar-form{margin-bottom:0;*zoom:1}\n" + 
          "		.navbar-form:before,.navbar-form:after{display:table;content:\"\";line-height:0}\n" + 
          "		.navbar-form:after{clear:both}\n" + 
          "		.navbar-form input,.navbar-form select,.navbar-form .radio,.navbar-form .checkbox{margin-top:5px}\n" + 
          "		.navbar-form input,.navbar-form select,.navbar-form .btn{display:inline-block;margin-bottom:0}\n" + 
          "		.navbar-form input[type=\"image\"],.navbar-form input[type=\"checkbox\"],.navbar-form input[type=\"radio\"]{margin-top:3px}\n" + 
          "		.navbar-form .input-append,.navbar-form .input-prepend{margin-top:6px;white-space:nowrap}\n" + 
          "		.navbar-form .input-append input,.navbar-form .input-prepend input{margin-top:0}\n" + 
          "		.navbar-search{position:relative;float:left;margin-top:5px;margin-bottom:0}\n" + 
          "		.navbar-search .search-query{margin-bottom:0;padding:4px 14px;font-family:\"Segoe UI\",\"Helvetica Neue\",Helvetica,Arial,sans-serif;font-size:13px;font-weight:normal;line-height:1;-webkit-border-radius:15px;-moz-border-radius:15px;border-radius:15px}\n" + 
          "		.navbar-static-top{position:static;width:100%;margin-bottom:0}\n" + 
          "		.navbar-static-top .navbar-inner{-webkit-border-radius:0;-moz-border-radius:0;border-radius:0}\n" + 
          "		.navbar-fixed-top,.navbar-fixed-bottom{position:fixed;right:0;left:0;z-index:10300;margin-bottom:0}\n" + 
          "		.navbar-fixed-top .navbar-inner,.navbar-static-top .navbar-inner{border-width:0 0 1px}\n" + 
          "		.navbar-fixed-bottom .navbar-inner{border-width:1px 0 0}\n" + 
          "		.navbar-fixed-top .navbar-inner,.navbar-fixed-bottom .navbar-inner{padding-left:0;padding-right:0;-webkit-border-radius:0;-moz-border-radius:0;border-radius:0}\n" + 
          "		.navbar-static-top .container,.navbar-fixed-top .container,.navbar-fixed-bottom .container{width:100%}\n" + 
          "		.navbar-fixed-top{top:0}\n" + 
          "		.navbar-fixed-top .navbar-inner,.navbar-static-top .navbar-inner{-webkit-box-shadow:inset 0 -1px 0 rgba(0,0,0,0.1),0 1px 10px rgba(0,0,0,0.1);-moz-box-shadow:inset 0 -1px 0 rgba(0,0,0,0.1),0 1px 10px rgba(0,0,0,0.1);box-shadow:inset 0 -1px 0 rgba(0,0,0,0.1),0 1px 10px rgba(0,0,0,0.1)}\n" + 
          "		.navbar-fixed-bottom{bottom:0}\n" + 
          "		.navbar-fixed-bottom .navbar-inner{-webkit-box-shadow:inset 0 1px 0 rgba(0,0,0,0.1),0 -1px 10px rgba(0,0,0,0.1);-moz-box-shadow:inset 0 1px 0 rgba(0,0,0,0.1),0 -1px 10px rgba(0,0,0,0.1);box-shadow:inset 0 1px 0 rgba(0,0,0,0.1),0 -1px 10px rgba(0,0,0,0.1)}\n" + 
          "		.navbar .nav{position:relative;left:0;display:block;float:left;margin:0 10px 0 0}\n" + 
          "		.navbar .nav.pull-right{float:right;margin-right:0}\n" + 
          "		.navbar .nav>li{float:left}\n" + 
          "		.navbar .nav>li>a{float:none;padding:10px 15px 10px;color:#777;text-decoration:none;text-shadow:0 1px 0 #fff}\n" + 
          "		.navbar .nav .dropdown-toggle .caret{margin-top:8px}\n" + 
          "		.navbar .nav>li>a:focus,.navbar .nav>li>a:hover{background-color:transparent;color:#333;text-decoration:none}\n" + 
          "		.navbar .nav>.active>a,.navbar .nav>.active>a:hover,.navbar .nav>.active>a:focus{color:#555;text-decoration:none;background-color:#e5e5e5;-webkit-box-shadow:inset 0 3px 8px rgba(0,0,0,0.125);-moz-box-shadow:inset 0 3px 8px rgba(0,0,0,0.125);box-shadow:inset 0 3px 8px rgba(0,0,0,0.125)}\n" + 
          "		.navbar .btn-navbar{display:none;float:right;padding:7px 10px;margin-left:5px;margin-right:5px;color:#fff;text-shadow:0 -1px 0 rgba(0,0,0,0.25);background-color:#ededed;background-image:-moz-linear-gradient(top,#f2f2f2,#e5e5e5);background-image:-webkit-gradient(linear,0 0,0 100%,from(#f2f2f2),to(#e5e5e5));background-image:-webkit-linear-gradient(top,#f2f2f2,#e5e5e5);background-image:-o-linear-gradient(top,#f2f2f2,#e5e5e5);background-image:linear-gradient(to bottom,#f2f2f2,#e5e5e5);background-repeat:repeat-x;filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#fff2f2f2',endColorstr='#ffe5e5e5',GradientType=0);border-color:#e5e5e5 #e5e5e5 #bfbfbf;border-color:rgba(0,0,0,0.1) rgba(0,0,0,0.1) rgba(0,0,0,0.25);*background-color:#e5e5e5;filter:progid:DXImageTransform.Microsoft.gradient(enabled = false);-webkit-box-shadow:inset 0 1px 0 rgba(255,255,255,0.1),0 1px 0 rgba(255,255,255,0.075);-moz-box-shadow:inset 0 1px 0 rgba(255,255,255,0.1),0 1px 0 rgba(255,255,255,0.075);box-shadow:inset 0 1px 0 rgba(255,255,255,0.1),0 1px 0 rgba(255,255,255,0.075)}\n" + 
          "		.navbar .btn-navbar:hover,.navbar .btn-navbar:active,.navbar .btn-navbar.active,.navbar .btn-navbar.disabled,.navbar .btn-navbar[disabled]{color:#fff;background-color:#e5e5e5;*background-color:#d9d9d9}\n" + 
          "		.navbar .btn-navbar:active,.navbar .btn-navbar.active{background-color:#ccc \\9}\n" + 
          "		.navbar .btn-navbar .icon-bar{display:block;width:18px;height:2px;background-color:#f5f5f5;-webkit-border-radius:1px;-moz-border-radius:1px;border-radius:1px;-webkit-box-shadow:0 1px 0 rgba(0,0,0,0.25);-moz-box-shadow:0 1px 0 rgba(0,0,0,0.25);box-shadow:0 1px 0 rgba(0,0,0,0.25)}\n" + 
          "		.btn-navbar .icon-bar+.icon-bar{margin-top:3px}\n" + 
          "		.navbar .nav>li>.dropdown-menu:before{content:'';display:inline-block;border-left:7px solid transparent;border-right:7px solid transparent;border-bottom:7px solid #ccc;border-bottom-color:rgba(0,0,0,0.2);position:absolute;top:-7px;left:9px}\n" + 
          "		.navbar .nav>li>.dropdown-menu:after{content:'';display:inline-block;border-left:6px solid transparent;border-right:6px solid transparent;border-bottom:6px solid #fff;position:absolute;top:-6px;left:10px}\n" + 
          "		.navbar-fixed-bottom .nav>li>.dropdown-menu:before{border-top:7px solid #ccc;border-top-color:rgba(0,0,0,0.2);border-bottom:0;bottom:-7px;top:auto}\n" + 
          "		.navbar-fixed-bottom .nav>li>.dropdown-menu:after{border-top:6px solid #fff;border-bottom:0;bottom:-6px;top:auto}\n" + 
          "		.navbar .nav li.dropdown.open>.dropdown-toggle,.navbar .nav li.dropdown.active>.dropdown-toggle,.navbar .nav li.dropdown.open.active>.dropdown-toggle{background-color:#e5e5e5;color:#555}\n" + 
          "		.navbar .nav li.dropdown>.dropdown-toggle .caret{border-top-color:#777;border-bottom-color:#777}\n" + 
          "		.navbar .nav li.dropdown.open>.dropdown-toggle .caret,.navbar .nav li.dropdown.active>.dropdown-toggle .caret,.navbar .nav li.dropdown.open.active>.dropdown-toggle .caret{border-top-color:#555;border-bottom-color:#555}\n" + 
          "		.navbar .pull-right>li>.dropdown-menu,.navbar .nav>li>.dropdown-menu.pull-right{left:auto;right:0}\n" + 
          "		.navbar .pull-right>li>.dropdown-menu:before,.navbar .nav>li>.dropdown-menu.pull-right:before{left:auto;right:12px}\n" + 
          "		.navbar .pull-right>li>.dropdown-menu:after,.navbar .nav>li>.dropdown-menu.pull-right:after{left:auto;right:13px}\n" + 
          "		.navbar .pull-right>li>.dropdown-menu .dropdown-menu,.navbar .nav>li>.dropdown-menu.pull-right .dropdown-menu{left:auto;right:100%;margin-left:0;margin-right:-1px;-webkit-border-radius:6px 0 6px 6px;-moz-border-radius:6px 0 6px 6px;border-radius:6px 0 6px 6px}\n" + 
          "		.navbar-inverse{color:#fff}\n" + 
          "		.navbar-inverse .navbar-inner{background-color:#0052a8;background-image:-moz-linear-gradient(top,#0063ca,#003874);background-image:-webkit-gradient(linear,0 0,0 100%,from(#0063ca),to(#003874));background-image:-webkit-linear-gradient(top,#0063ca,#003874);background-image:-o-linear-gradient(top,#0063ca,#003874);background-image:linear-gradient(to bottom,#0063ca,#003874);background-repeat:repeat-x;filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#ff0063ca',endColorstr='#ff003874',GradientType=0);border-color:#252525}\n" + 
          "		.navbar-inverse .brand,.navbar-inverse .nav>li>a{color:#fff;text-shadow:0 -1px 0 rgba(0,0,0,0.25)}\n" + 
          "		.navbar-inverse .brand:hover,.navbar-inverse .nav>li>a:hover{color:#fff}\n" + 
          "		.navbar-inverse .nav>li>a:focus,.navbar-inverse .nav>li>a:hover{background-color:transparent;color:#fff}\n" + 
          "		.navbar-inverse .nav .active>a,.navbar-inverse .nav .active>a:hover,.navbar-inverse .nav .active>a:focus{color:#fff;background-color:#003874}\n" + 
          "		.navbar-inverse .navbar-link{color:#fff}\n" + 
          "		.navbar-inverse .navbar-link:hover{color:#fff}\n" + 
          "		.navbar-inverse .divider-vertical{border-left-color:#003874;border-right-color:#0063ca}\n" + 
          "		.navbar-inverse .nav li.dropdown.open>.dropdown-toggle,.navbar-inverse .nav li.dropdown.active>.dropdown-toggle,.navbar-inverse .nav li.dropdown.open.active>.dropdown-toggle{background-color:#003874;color:#fff}\n" + 
          "		.navbar-inverse .nav li.dropdown>.dropdown-toggle .caret{border-top-color:#fff;border-bottom-color:#fff}\n" + 
          "		.navbar-inverse .nav li.dropdown.open>.dropdown-toggle .caret,.navbar-inverse .nav li.dropdown.active>.dropdown-toggle .caret,.navbar-inverse .nav li.dropdown.open.active>.dropdown-toggle .caret{border-top-color:#fff;border-bottom-color:#fff}\n" + 
          "		.navbar-inverse .navbar-search .search-query{color:#fff;background-color:#0076f3;border-color:#003874;-webkit-box-shadow:inset 0 1px 2px rgba(0,0,0,0.1),0 1px 0 rgba(255,255,255,0.15);-moz-box-shadow:inset 0 1px 2px rgba(0,0,0,0.1),0 1px 0 rgba(255,255,255,0.15);box-shadow:inset 0 1px 2px rgba(0,0,0,0.1),0 1px 0 rgba(255,255,255,0.15);-webkit-transition:none;-moz-transition:none;-o-transition:none;transition:none}\n" + 
          "		.navbar-inverse .navbar-search .search-query:-moz-placeholder{color:#ccc}\n" + 
          "		.navbar-inverse .navbar-search .search-query:-ms-input-placeholder{color:#ccc}\n" + 
          "		.navbar-inverse .navbar-search .search-query::-webkit-input-placeholder{color:#ccc}\n" + 
          "		.navbar-inverse .navbar-search .search-query:focus,.navbar-inverse .navbar-search .search-query.focused{padding:5px 15px;color:#333;text-shadow:0 1px 0 #fff;background-color:#fff;border:0;-webkit-box-shadow:0 0 3px rgba(0,0,0,0.15);-moz-box-shadow:0 0 3px rgba(0,0,0,0.15);box-shadow:0 0 3px rgba(0,0,0,0.15);outline:0}\n" + 
          "		.navbar-inverse .btn-navbar{color:#fff;text-shadow:0 -1px 0 rgba(0,0,0,0.25);background-color:#00458e;background-image:-moz-linear-gradient(top,#0057b1,#002c5a);background-image:-webkit-gradient(linear,0 0,0 100%,from(#0057b1),to(#002c5a));background-image:-webkit-linear-gradient(top,#0057b1,#002c5a);background-image:-o-linear-gradient(top,#0057b1,#002c5a);background-image:linear-gradient(to bottom,#0057b1,#002c5a);background-repeat:repeat-x;filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#ff0057b1',endColorstr='#ff002c5a',GradientType=0);border-color:#002c5a #002c5a #00070e;border-color:rgba(0,0,0,0.1) rgba(0,0,0,0.1) rgba(0,0,0,0.25);*background-color:#002c5a;filter:progid:DXImageTransform.Microsoft.gradient(enabled = false)}\n" + 
          "		.navbar-inverse .btn-navbar:hover,.navbar-inverse .btn-navbar:active,.navbar-inverse .btn-navbar.active,.navbar-inverse .btn-navbar.disabled,.navbar-inverse .btn-navbar[disabled]{color:#fff;background-color:#002c5a;*background-color:#001f41}\n" + 
          "		.navbar-inverse .btn-navbar:active,.navbar-inverse .btn-navbar.active{background-color:#001327 \\9}\n" + 
          "		.breadcrumb{padding:8px 15px;margin:0 0 20px;list-style:none;background-color:#f5f5f5;-webkit-border-radius:4px;-moz-border-radius:4px;border-radius:4px}\n" + 
          "		.breadcrumb li{display:inline-block;*display:inline;*zoom:1;text-shadow:0 1px 0 #fff}\n" + 
          "		.breadcrumb .divider{padding:0 5px;color:#ccc}\n" + 
          "		.breadcrumb .active{color:#999}\n" + 
          "		.thumbnails{margin-left:-20px;list-style:none;*zoom:1}\n" + 
          "		.thumbnails:before,.thumbnails:after{display:table;content:\"\";line-height:0}\n" + 
          "		.thumbnails:after{clear:both}\n" + 
          "		.row-fluid .thumbnails{margin-left:0}\n" + 
          "		.thumbnails>li{float:left;margin-bottom:20px;margin-left:20px}\n" + 
          "		.thumbnail{display:block;padding:4px;line-height:20px;border:1px solid #ddd;-webkit-border-radius:4px;-moz-border-radius:4px;border-radius:4px;-webkit-box-shadow:0 1px 3px rgba(0,0,0,0.055);-moz-box-shadow:0 1px 3px rgba(0,0,0,0.055);box-shadow:0 1px 3px rgba(0,0,0,0.055);-webkit-transition:all .2s ease-in-out;-moz-transition:all .2s ease-in-out;-o-transition:all .2s ease-in-out;transition:all .2s ease-in-out}\n" + 
          "		a.thumbnail:hover{border-color:#0063ca;-webkit-box-shadow:0 1px 4px rgba(0,105,214,0.25);-moz-box-shadow:0 1px 4px rgba(0,105,214,0.25);box-shadow:0 1px 4px rgba(0,105,214,0.25)}\n" + 
          "		.thumbnail>img{display:block;max-width:100%;margin-left:auto;margin-right:auto}\n" + 
          "		.thumbnail .caption{padding:9px;color:#555}\n" + 
          "		.label,.badge{font-size:10.998px;font-weight:bold;line-height:14px;color:#fff;vertical-align:baseline;white-space:nowrap;text-shadow:0 -1px 0 rgba(0,0,0,0.25);background-color:#999}\n" + 
          "		.label{padding:1px 4px 2px;-webkit-border-radius:3px;-moz-border-radius:3px;border-radius:3px}\n" + 
          "		.badge{padding:1px 9px 2px;-webkit-border-radius:9px;-moz-border-radius:9px;border-radius:9px}\n" + 
          "		a.label:hover,a.badge:hover{color:#fff;text-decoration:none;cursor:pointer}\n" + 
          "		.label-important,.badge-important{background-color:#b94a48}\n" + 
          "		.label-important[href],.badge-important[href]{background-color:#953b39}\n" + 
          "		.label-warning,.badge-warning{background-color:#f89406}\n" + 
          "		.label-warning[href],.badge-warning[href]{background-color:#c67605}\n" + 
          "		.label-success,.badge-success{background-color:#468847}\n" + 
          "		.label-success[href],.badge-success[href]{background-color:#356635}\n" + 
          "		.label-info,.badge-info{background-color:#3a87ad}\n" + 
          "		.label-info[href],.badge-info[href]{background-color:#2d6987}\n" + 
          "		.label-inverse,.badge-inverse{background-color:#333}\n" + 
          "		.label-inverse[href],.badge-inverse[href]{background-color:#1a1a1a}\n" + 
          "		.btn .label,.btn .badge{position:relative;top:-1px}\n" + 
          "		.btn-mini .label,.btn-mini .badge{top:0}\n" + 
          "		.hero-unit{padding:50px;margin-bottom:30px;background-color:#eee;-webkit-border-radius:6px;-moz-border-radius:6px;border-radius:6px}\n" + 
          "		.hero-unit h1{margin-bottom:10px;margin-top:0;font-size:40px;line-height:1;color:inherit;letter-spacing:-1px;text-align:center}\n" + 
          "		.hero-unit h2{font-size:30px;margin:0}\n" + 
          "		.hero-unit p{font-size:16px;font-weight:200;line-height:25px;color:inherit}\n" + 
          "		.pull-right{float:right}\n" + 
          "		.pull-left{float:left}\n" + 
          "		.hide{display:none}\n" + 
          "		.show{display:block}\n" + 
          "		.invisible{visibility:hidden}\n" + 
          "		.affix{position:fixed}\n" + 
          "    </style>\n" + 
          "</head>\n" + 
          "<body>\n" + 
          "    <table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\">\n" + 
          "        <tr>\n" + 
          "            <td class=\"navbar navbar-inverse\" align=\"center\">\n" + 
          "                <!-- This setup makes the nav background stretch the whole width of the screen. -->\n" + 
          "                <table class=\"container\">\n" + 
          "                    <tr class=\"navbar navbar-inverse\">\n" + 
          "                        <td colspan=\"4\"><b>Report Monitor</b></td>\n" + 
          "                    </tr>\n" + 
          "                </table>\n" + 
          "            </td>\n" + 
          "        </tr>\n" + 
          "        <tr>\n" + 
          "            <td bgcolor=\"#FFFFFF\" align=\"center\">\n" + 
          "                <table class=\"container\">\n" + 
          "                    <tr>\n" + 
          "                        <td>\n";
          Vector result = new Vector();                      
          result = getResult();
          msg += toString(result) +          
          "            </td>\n" + 
          "        </tr>\n" + 
          "        <tr>\n" + 
          "            <td bgcolor=\"#FFFFFF\" align=\"center\">\n" + 
          "                <table class=\"container\">\n" + 
          "                    <tr>\n" + 
          "                        <td>\n" + 
          "                            <hr>\n" + 
          "                            <p>Presented by <a class=\"brand\" href=\"http://pbrcd1:18080/pbrc_dashboard/\">PBRC Dashboard</a></p>\n" + 
          "                        </td>\n" + 
          "                    </tr>\n" + 
          "                </table>\n" + 
          "            </td>\n" + 
          "        </tr>\n" + 
          "    </table>\n" + 
          "</body>\n" + 
          "</html>\n";
          email.setHtmlMsg(msg);
          email.send();

      }
      catch (Exception e) {
          e.printStackTrace();
      }
    }
    
    
    public String toString(Vector vec) {        
        StringBuffer str = new StringBuffer("<table class=\"table\">\n" + 
        "    <thead>\n" + 
        "        <tr>\n" + 
        "            <th>#</th>\n" + 
        "            <th>Type</th>\n" + 
        "            <th>Date Time</th>\n" + 
        "            <th>Status</th>\n" + 
        "            <th>No of row</th>\n" + 
        "        </tr>\n" + 
        "    </thead>\n ");
        for ( int i = 0 ; i < vec.size() ; i++ ) {     
            ReportMeasureBean bean = (ReportMeasureBean)vec.get(i);
            str.append(
            "    <tbody>\n" + 
            "        <tr>\n" + 
            "            <td>"+(i+1)+"</td>\n" + 
            "            <td>"+bean.getLabel()+"</td>\n" + 
            "            <td>"+bean.getDateStr()+"</td>\n" + 
            "            <td>"+bean.getStatusStr()+"</td>\n" + 
            "            <td>"+bean.getNoOfRowStr()+"</td>\n" + 
            "        </tr>\n" + 
            "    </tbody>"
            );
        }
        str.append("</table>");        
        return str.toString();                                                                                                                                                                                                        
    }
    
    public Vector getResult(){
        
        Vector result = new Vector();  
        
        ReportMeasureDao dao = new ReportMeasureDao();
        result = dao.getReportStatus();
        return result;        
    }
}
