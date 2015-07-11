package hk.org.ha.pbrc.dashboard.servlet;

import hk.org.ha.pbrc.dashboard.scheduler.ReportMonitorJob;

import java.util.Vector;

import javax.servlet.http.*;

public class ReportMeasureServlet extends MeasurementServlet {
    private static final String CONTENT_TYPE = "text/html; charset=Big5";

    protected Vector getResult(HttpServletRequest request){        
        ReportMonitorJob job = new ReportMonitorJob();        
        return job.getResult();
    }
    
    protected String toString(HttpServletRequest request) {        
        ReportMonitorJob job = new ReportMonitorJob();
        return job.toString(request);
    }
    
    protected Vector getResultCR(HttpServletRequest request) {
        Vector result = new Vector();
        return result;        
    }     
}
