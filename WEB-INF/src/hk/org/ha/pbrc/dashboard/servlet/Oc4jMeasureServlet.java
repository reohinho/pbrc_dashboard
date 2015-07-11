package hk.org.ha.pbrc.dashboard.servlet;

import hk.org.ha.pbrc.dashboard.bean.UtilizationSampleBean;

import hk.org.ha.pbrc.dashboard.parser.WindowParser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletConfig;
import javax.servlet.http.*;

public class Oc4jMeasureServlet extends MeasurementServlet {
   
    protected Vector getResult(HttpServletRequest request){
        
        final String[] serverNames = {"DC5PBRCMDT05", "DC5PBRCMDT07", "DC5PBRCMDT09"};        
        ServletConfig config = getServletConfig();
        //String inDate = request.getParameter("date");        
        String inDate = "20130801";                         
        Vector result = new Vector();                
        
        WindowParser parser = new WindowParser();
        
        InputStream input = null;
        InputStreamReader in = null;
        BufferedReader reader = null;
        String inStr = "";
        for(int k=0; k<serverNames.length; k++) {
            
            ArrayList beanList = new ArrayList<UtilizationSampleBean>();

            input = config.getServletContext().getResourceAsStream("/WEB-INF/AS/"+serverNames[k]+"_"+inDate+"/"+serverNames[k]+"_"+inDate+".csv");
            in = new InputStreamReader(input);         
        
            try {
                reader = new BufferedReader(in);
                    
                reader.readLine();
                reader.readLine();
                
                int i = 1;
                while((inStr = reader.readLine())!= null) {
                    UtilizationSampleBean bean = parser.parse(i++, inStr);    
                    beanList.add(bean);
                }
            }
            catch(Exception e) {
                e.printStackTrace();
            }
            
            result.add(beanList);
        }
        return result;        
    }
    
    protected String toString(HttpServletRequest request) {
        
      /*
        List<UtilizationSampleBean> list = (ArrayList<UtilizationSampleBean>)vec.get(0);
        List<UtilizationSampleBean> list2 = (ArrayList<UtilizationSampleBean>)vec.get(1);
        List<UtilizationSampleBean> list3 = (ArrayList<UtilizationSampleBean>)vec.get(2);
                
        String jsonString = "{ \"cols\": [{\"id\":\"\",\"label\":\"time\",\"type\":\"string\"},{\"id\":\"\",\"label\":\"DC5PBRCMDT05\",\"type\":\"number\"},{\"id\":\"\",\"label\":\"DC5PBRCMDT07\",\"type\":\"number\"},{\"id\":\"\",\"label\":\"DC5PBRCMDT09\",\"type\":\"number\"}],\"rows\":["; 
        for(int i=0; i<list.size();i++) {   
            UtilizationSampleBean bean = list.get(i);
            UtilizationSampleBean bean2 = list2.get(i);    
            UtilizationSampleBean bean3 = list3.get(i);    

            jsonString += "{\"c\":[{\"v\":\""+bean.getTimestamp()+"\"},{\"v\":"+bean.getSystemUsage()+"},{\"v\":"+bean2.getSystemUsage()+"},{\"v\":"+bean3.getSystemUsage()+"}]} ";             
        
            if(i<list.size()) {
                jsonString += ",";
            }
        }
        jsonString += "]}"; 
        
        return jsonString;                                                                                                                                                                                                         
      */
      return null;
    }

    protected Vector getResultCR(HttpServletRequest request) {
        Vector result = new Vector();
        return result;        
    }    
}
