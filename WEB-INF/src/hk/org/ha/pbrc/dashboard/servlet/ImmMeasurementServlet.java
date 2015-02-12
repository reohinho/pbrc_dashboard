package hk.org.ha.pbrc.dashboard.servlet;

import hk.org.ha.pbrc.dashboard.bean.SampleBean;
import hk.org.ha.pbrc.dashboard.dao.ImmMeasureDao;

import hk.org.ha.pbrc.dashboard.dao.PmiMeasureDao;
import hk.org.ha.pbrc.dashboard.jdbc.DataSource;

import java.sql.Connection;

import java.util.List;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;


public class ImmMeasurementServlet extends MeasurementServlet {

    private static final String CONTENT_TYPE = "text/html; charset=Big5";
    
    @SuppressWarnings("compatibility:-3145195992198277634")
    private static final long serialVersionUID = 1L;

    protected Vector getResult(HttpServletRequest request){      
        Connection conn = null;     
        Vector result = new Vector();
        try {
            conn = DataSource.getMBarConnection(); 
            String measureType = request.getParameter("measureType");        
            String subType = request.getParameter("subType");            
            ImmMeasureDao dao = new ImmMeasureDao();
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
    
    protected Vector getResultCR(HttpServletRequest request) {
        Connection conn = null;     
        Vector result = new Vector();
        try {
            conn = DataSource.getMBarCRConnection(); 
            String measureType = request.getParameter("measureType");        
            String subType = request.getParameter("subType");            
            ImmMeasureDao dao = new ImmMeasureDao();
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
        SampleBean bean = list.get(0);        
        return bean.getValue().toString();                                                                                                                                                                                                   
       */
      return null;
    }
    
}
