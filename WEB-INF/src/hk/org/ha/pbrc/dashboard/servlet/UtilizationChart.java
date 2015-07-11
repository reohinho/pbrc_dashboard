package hk.org.ha.pbrc.dashboard.servlet;

import hk.org.ha.pbrc.dashboard.bean.UtilizationSampleBean;

import hk.org.ha.pbrc.dashboard.parser.WindowParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;


import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

public class UtilizationChart extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=Big5";
    
    Logger logger = Logger.getLogger("GenericUtilities");
    
    final public static String CMD_INIT = "init";
    final public static String CMD_DISPLAY = "display";


    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException,
                                                           IOException {
        ServletConfig config = getServletConfig();
        String cmd = request.getParameter("cmd");
        
        if (cmd==null || cmd.equals(CMD_INIT)) {
            
        }
        else if (cmd!=null && cmd.equals(CMD_DISPLAY)) {
        
            String inDate = request.getParameter("date");
            String inServer = request.getParameter("server");
            
            //final String[] serverNames = {"DC5PBRCMDT05", "DC5PBRCMDT06", "DC5PBRCMDT07", "DC5PBRCMDT08", "DC5PBRCMDT09"};
            //final String[] serverNames = {"DC3PBRCMDT02", "DC6PBRCMDT03", "DC6PBRCMDT04"};
            //final String[] serverNames = {"DC5PBRCMDT09"};
            final int instanceSize = 1;
            
            ArrayList[] resultList = new ArrayList[instanceSize];        
            
            response.setContentType(CONTENT_TYPE);
            PrintWriter out = response.getWriter();
            
            WindowParser parser = new WindowParser();
    
            InputStream input = null;
            InputStreamReader in = null;
            BufferedReader reader = null;
            String inStr = "";
            for(int k=0; k<instanceSize; k++) {
                
                ArrayList beanList = new ArrayList<UtilizationSampleBean>();
    
                //input = config.getServletContext().getResourceAsStream("/WEB-INF/AS/"+serverNames[k]+"_"+inDate+".csv");
                input = config.getServletContext().getResourceAsStream("/WEB-INF/AS/"+inServer+"_"+inDate+".csv");
                in = new InputStreamReader(input);         
        
                reader = new BufferedReader(in);
                
                reader.readLine();
                reader.readLine();
                
                int i = 1;
                while((inStr = reader.readLine())!= null) {
                    UtilizationSampleBean bean = parser.parse(i++, inStr);    
                    beanList.add(bean);
                }
                
                resultList[k] = beanList;
            }
            
            out.println("<html>");
            out.println("  <head>");
            out.println("    <script type=\"text/javascript\" src=\"http://canvg.googlecode.com/svn/trunk/rgbcolor.js\"></script> ");
            out.println("    <script type=\"text/javascript\" src=\"http://canvg.googlecode.com/svn/trunk/canvg.js\"></script>");
            out.println("    <script type=\"text/javascript\">");        
            out.println("    function getImgData(chartContainer) {   ");
            out.println("           var chartArea = chartContainer.getElementsByTagName('div')[1];  ");
            out.println("           var svg = chartArea.innerHTML;  ");
            out.println("           var doc = chartContainer.ownerDocument; ");
            out.println("           var canvas = doc.createElement('canvas');       ");
            out.println("           canvas.setAttribute('width', chartArea.offsetWidth);    ");
            out.println("           canvas.setAttribute('height', chartArea.offsetHeight);  ");
            out.println("                   ");
            out.println("                   ");
            out.println("           canvas.setAttribute(    ");
            out.println("               'style',    ");
            out.println("               'position: absolute; ' +    ");
            out.println("               'top: ' + (-chartArea.offsetHeight * 2) + 'px;' +   ");
            out.println("               'left: ' + (-chartArea.offsetWidth * 2) + 'px;');   ");
            out.println("           doc.body.appendChild(canvas);   ");
            out.println("           canvg(canvas, svg);     ");
            out.println("           var imgData = canvas.toDataURL(\"image/png\");    ");
            out.println("           canvas.parentNode.removeChild(canvas);  ");
            out.println("           return imgData; ");
            out.println("         } ");
            out.println("           ");
            out.println("         function saveAsImg(chartContainer) {      ");
            out.println("           var imgData = getImgData(chartContainer);       ");
            out.println("                   ");
            out.println("           // Replacing the mime-type will force the browser to trigger a download ");
            out.println("           // rather than displaying the image in the browser window.      ");
            out.println("           window.location = imgData.replace(\"image/png\", \"image/octet-stream\");   ");
            out.println("         } ");
            out.println("           ");
            out.println("         function toImg(chartContainer, imgContainer) {    ");
            out.println("           var doc = chartContainer.ownerDocument; ");
            out.println("           var img = doc.createElement('img');     ");
            out.println("           img.src = getImgData(chartContainer);   ");
            out.println("                   ");
            out.println("           while (imgContainer.firstChild) {       ");
            out.println("             imgContainer.removeChild(imgContainer.firstChild);    ");
            out.println("           }       ");
            out.println("           imgContainer.appendChild(img);  ");
            out.println("         } ");
            out.println("    </script>");        
            out.println("    <script type=\"text/javascript\" src=\"https://www.google.com/jsapi\"></script>");
            out.println("    <script type=\"text/javascript\">");
            out.println("      google.load(\"visualization\", \"1\", {packages:[\"corechart\"]});");
            out.println("      google.setOnLoadCallback(drawChart);");
            out.println("     function drawChart() {");
            out.println("        var data = google.visualization.arrayToDataTable([");
            out.print("          ['Year'");
                for(int i=0; i<instanceSize; i++) {
                    out.print(", '"+inServer+"'");
                }
                    
            int m = 0;
            UtilizationSampleBean[] beans = new UtilizationSampleBean[instanceSize];
      
            while(m < resultList[0].size()) {  
                out.println("],");
                
                for(int n=0; n<instanceSize;n++) {
                    beans[n] = (UtilizationSampleBean)resultList[n].get(m);
                }
                out.print("          ['"+beans[0].getTimestamp()+"'");
                for(int p=0; p<instanceSize; p++) {
                    out.print(",  "+beans[p].getSystemUsage());
                }
                m++;
            }
            out.println("]");
            out.println("        ]);");
    
            out.println("        var options = {");
            out.println("          title: 'CPU Utilization "+inDate+"',");
            out.println("          vAxis: {maxValue: 100}");
            out.println("        };");
    
            out.println("        var chart = new google.visualization.LineChart(document.getElementById('chart_div'));");
            out.println("        chart.draw(data, options);");
            out.println("      }");
            out.println("    </script>");
            out.println("  </head>");
            out.println("  <body>");
            out.println("  <div id=\"chart_div\" style=\"width: 1280px; height: 700px;\"></div>");
            out.println("  <button onclick=\"saveAsImg(document.getElementById('chart_div'));\">Save as PNG Image</button>    ");
            out.println("  <button onclick=\"toImg(document.getElementById('chart_div'), document.getElementById('chart_div'));\">Convert to image</button> ");
            out.println("  <div id=\"line_div\"></div> ");        
            out.println("  </body>");
            out.println("</html>");
            
            out.close();
        }
    }
}
