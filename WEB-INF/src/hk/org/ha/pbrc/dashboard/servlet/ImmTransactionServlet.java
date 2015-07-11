package hk.org.ha.pbrc.dashboard.servlet;

import hk.org.ha.pbrc.dashboard.bean.SampleBean;
import hk.org.ha.pbrc.dashboard.dao.ImmMeasureDao;

import hk.org.ha.pbrc.dashboard.jdbc.DataSource;

import java.sql.Connection;

import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.*;

public class ImmTransactionServlet extends MeasurementServlet {

    protected Vector getResult(HttpServletRequest request){
        Connection conn = null;
        Vector result = new Vector();
        String measureType = request.getParameter("measureType");        
        String subType = request.getParameter("subType");        
        
        ImmMeasureDao dao = new ImmMeasureDao();
        try {
            conn = DataSource.getMBarConnection();
            result.add(dao.getBeansByMeasureType(conn, measureType, subType));
        }
        catch (Exception e) {
            e.printStackTrace();   
        }
        finally {
            if (conn!=null) try { conn.close(); } catch (Exception e) { e.printStackTrace(); }
        }
   
        return result;        
    }
    
    protected String toString(HttpServletRequest request) {
        /*
        List<SampleBean> list = (List<SampleBean>)vec.get(0);
                
        StringBuffer sb = new StringBuffer();
        for (SampleBean bean : list) {
        
            sb.append( "<b>");
            sb.append(bean.getLabel().substring(17)+": ");
            sb.append("</b> ");
            
            Calendar cal = Calendar.getInstance();
            boolean isOfficeHour = cal.get(Calendar.HOUR_OF_DAY) >= 9 && cal.get(Calendar.HOUR_OF_DAY) < 18;
            int cnt = bean.getValue();
            sb.append( "<span class=\"badge badge-"+((cnt==0)?"important":(cnt<50&&isOfficeHour)?"warning":"success")+"\">"+cnt+"</span>/min" );
            sb.append("</p>");
        }
        
        return sb.toString();        
        */
        return null;
    }
    
    protected Vector getResultCR(HttpServletRequest request) {
        Vector result = new Vector();
        return result;        
    }    
}
