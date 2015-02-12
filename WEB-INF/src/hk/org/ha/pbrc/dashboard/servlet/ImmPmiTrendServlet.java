package hk.org.ha.pbrc.dashboard.servlet;

import hk.org.ha.pbrc.dashboard.bean.SampleBean;
import hk.org.ha.pbrc.dashboard.dao.ImmMeasureDao;

import hk.org.ha.pbrc.dashboard.dao.PmiMeasureDao;
import hk.org.ha.pbrc.dashboard.jdbc.DataSource;

import java.sql.Connection;

import java.util.List;
import java.util.Vector;
import javax.servlet.http.*;

public class ImmPmiTrendServlet extends MeasurementServlet {
    
    protected Vector getResult(HttpServletRequest request){
        
        Vector result = new Vector();        
        ImmMeasureDao dao = new ImmMeasureDao();
        result.add(dao.getTrend("IMM_COUNT", "IMM_AFTER_INSERT_PMI", 0));
        result.add(dao.getTrend("IMM_COUNT", "IMM_AFTER_INSERT_PMI", -1));
        return result;        
    }
    
    protected String toString(HttpServletRequest request) {
        /*
        List<SampleBean> list = (List<SampleBean>)vec.get(0);
        List<SampleBean> list2 = (List<SampleBean>)vec.get(1);
                
        String jsonString = "{ \"cols\": [{\"id\":\"\",\"label\":\"time\",\"type\":\"string\"},{\"id\":\"\",\"label\":\"pmi (today)\",\"type\":\"number\"},{\"id\":\"\",\"label\":\"pmi (yesterday)\",\"type\":\"number\"}],\"rows\":["; 
        for(int i=0; i<list.size();i++) {   
            SampleBean bean = list.get(i);

            if(i<list2.size()) {
                SampleBean bean2 = list2.get(i);    
                jsonString += "{\"c\":[{\"v\":\""+bean.getLabel()+"\"},{\"v\":"+bean.getValue()+"},{\"v\":"+bean2.getValue()+"}]} "; 
            
            }
            else {
                jsonString += "{\"c\":[{\"v\":\""+bean.getLabel()+"\"},{\"v\":"+bean.getValue()+"}]} "; 
            }
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
