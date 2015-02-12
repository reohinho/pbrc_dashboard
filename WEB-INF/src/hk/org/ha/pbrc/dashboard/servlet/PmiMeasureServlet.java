package hk.org.ha.pbrc.dashboard.servlet;

import hk.org.ha.pbrc.dashboard.bean.SampleBean;
import hk.org.ha.pbrc.dashboard.bean.SeriesBean;
import hk.org.ha.pbrc.dashboard.dao.PmiMeasureDao;

import hk.org.ha.pbrc.dashboard.jdbc.DataSource;

import hk.org.ha.pbrc.dashboard.scheduler.PmiMonitorJob;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class PmiMeasureServlet extends MeasurementServlet {

    protected Vector getResult(HttpServletRequest request){
        PmiMonitorJob job = new PmiMonitorJob();
        return job.getResult();
    }
    
    protected Vector getResultCR(HttpServletRequest request) {
        Connection conn = null;     
        Vector result = new Vector();
        /*
        try {
            conn = DataSource.getMBarCRConnection(); 
            String subType = request.getParameter("subType");
            PmiMeasureDao dao = new PmiMeasureDao();
            result.add(dao.getPmiStats(subType, conn));      
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (conn!=null) try { conn.close(); } catch (Exception e) { e.printStackTrace(); }
        }
        */
        return result;        
    }
    
    protected String toString(HttpServletRequest request) {   
      
        /*
        PmiMonitorJob job = new PmiMonitorJob();
        return job.toString(vec);
        */
        return null;
    }
    
}
