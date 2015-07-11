package hk.org.ha.pbrc.dashboard.servlet;

import hk.org.ha.pbrc.dashboard.bean.SampleBean;
import hk.org.ha.pbrc.dashboard.dao.BlockingMeasureDao;

import java.util.Vector;

import javax.servlet.http.*;

public class OracleMeasureServlet extends MeasurementServlet {
    private static final String CONTENT_TYPE = "text/html; charset=Big5";

    protected Vector getResult(HttpServletRequest request){
        
        Vector result = new Vector();        
        BlockingMeasureDao dao = new BlockingMeasureDao();
        result.add(dao.getBlockingCount());
        result.add(dao.getClientSession());
        return result;        
    }
    
    protected String toString(Vector vec) {        
        SampleBean bean = (SampleBean)vec.get(0);   
        StringBuffer str = new StringBuffer("<b>Blocking Session: </b>");
        int cnt = bean.getValue();
        str.append("<span class=\"badge badge-"+((cnt>6)?(cnt>10)?"important":"warning":"success")+"\">"+cnt+"</span>");

        str.append("</p><b>Client Session: </b>");
        bean = (SampleBean)vec.get(1);
        cnt = bean.getValue();
        str.append("<span class=\"badge badge-success\">"+cnt+"</span>");        
        str.append("</p><span>&nbsp;</span>");
       
        return str.toString();                                                                                                                                                                                                        
    }
    
    protected Vector getResultCR(HttpServletRequest request) {
        Vector result = new Vector();
        return result;        
    }     
}
