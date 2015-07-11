package hk.org.ha.pbrc.dashboard.servlet;

import hk.org.ha.pbrc.dashboard.bean.SampleBean;
import hk.org.ha.pbrc.dashboard.dao.OutstandingBalanceDao;

import java.util.Vector;
import javax.servlet.http.*;

public class OutstandingBalanceMeasureServlet extends MeasurementServlet {
    
    protected Vector getResult(HttpServletRequest request) {

        Vector result = new Vector();
        OutstandingBalanceDao dao = new OutstandingBalanceDao();
        result.add(dao.getOSBalanceMeasure("OS_BAL_MAX"));

        return result;
    }

    protected String toString(HttpServletRequest request) {    
      
        /*
        SampleBean bean = (SampleBean)vec.get(0);
        StringBuffer str = new StringBuffer("<b>Response Time: </b>");
        int cnt = bean.getValue();
        str.append("<span class=\"badge badge-"+((cnt>3)?(cnt>5)?"important":"warning":"success")+"\">"+cnt+"</span></p></p>");
        str.append("<span>&nbsp;</span></p>");
        str.append("<span>&nbsp;</span></p>");
        
        return str.toString();   
        */
        return null;
    }
    
    protected Vector getResultCR(HttpServletRequest request) {
        Vector result = new Vector();
        return result;        
    }    
}
