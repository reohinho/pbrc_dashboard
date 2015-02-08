package hk.org.ha.pbrc.dashboard.servlet;

import hk.org.ha.pbrc.dashboard.utilities.DateUtilities;
import hk.org.ha.pbrc.dashboard.bean.SampleBean;
import hk.org.ha.pbrc.dashboard.dao.PmiMeasureDao;

import java.util.List;
import java.util.Vector;

import javax.servlet.http.*;

public class PmiTrendServlet extends MeasurementServlet {
   
    protected Vector getResult(HttpServletRequest request){
        
        Vector result = new Vector();        
        PmiMeasureDao dao = new PmiMeasureDao();
        result.add(dao.getTrendBySubType("MINIBAR_PROCESSING", -1));
        result.add(dao.getTrendBySubType("SOURCE_PROCESSING", -1));
        result.add(dao.getTrendBySubType("MINIBAR_PROCESSING", 0));
        result.add(dao.getTrendBySubType("SOURCE_PROCESSING", 0));
        return result;        
    }
    
    protected String toString(Vector vec) {
        
        List<SampleBean> list = (List<SampleBean>)vec.get(0);
        List<SampleBean> list2 = (List<SampleBean>)vec.get(1);
        List<SampleBean> list3 = (List<SampleBean>)vec.get(2);
        List<SampleBean> list4 = (List<SampleBean>)vec.get(3);
                
        String jsonString = "{ \"cols\": [{\"id\":\"\",\"label\":\"time\",\"type\":\"string\"},{\"id\":\"\",\"label\":\"minibar (yesterday)\",\"type\":\"number\"},{\"id\":\"\",\"label\":\"source (yesterday)\",\"type\":\"number\"},{\"id\":\"\",\"label\":\"minibar (today)\",\"type\":\"number\"},{\"id\":\"\",\"label\":\"source (today)\",\"type\":\"number\"}],\"rows\":["; 
        for(int i=0; i<list.size();i++) {   
            SampleBean bean = list.get(i);
            SampleBean bean2 = list2.get(i);    

            if(i<list3.size()) {
                SampleBean bean3 = list3.get(i);
                SampleBean bean4 = list4.get(i);
                jsonString += "{\"c\":[{\"v\":\""+bean.getLabel()+"\"},{\"v\":"+bean.getValue()+"},{\"v\":"+bean2.getValue()+"},{\"v\":"+bean3.getValue()+"}, {\"v\":"+bean4.getValue()+"}]} "; 
            
            }
            else {
                jsonString += "{\"c\":[{\"v\":\""+bean.getLabel()+"\"},{\"v\":"+bean.getValue()+"},{\"v\":"+bean2.getValue()+"}]} "; 
            }
            if(i<list.size()) {
                jsonString += ",";
            }
        }
        jsonString += "]}"; 
        
        return jsonString;                                                                                                                                                                                                         
    }
   
    protected Vector getResultCR(HttpServletRequest request) {
        Vector result = new Vector();
        return result;        
    }    
}
