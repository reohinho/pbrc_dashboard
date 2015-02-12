package hk.org.ha.pbrc.dashboard.servlet;

import hk.org.ha.pbrc.dashboard.utilities.DateUtilities;
import hk.org.ha.pbrc.dashboard.bean.WorkflowSampleBean;
import hk.org.ha.pbrc.dashboard.dao.WorkflowMeasureDao;

import hk.org.ha.pbrc.dashboard.scheduler.WorkflowMonitorJob;

import java.util.List;
import java.util.Vector;

import javax.servlet.http.*;

public class WorkflowMeasureServlet extends MeasurementServlet {

    @SuppressWarnings("compatibility:8751845787678257805")
    private static final long serialVersionUID = 1L;

    protected Vector getResult(HttpServletRequest request) {
        Vector result = new Vector();
        return result;        
    }   
  
    protected String toString(HttpServletRequest request) {        
        WorkflowMonitorJob job = new WorkflowMonitorJob();                                                                                                                                                                                                
        return job.toString(request);
    }
    
    protected Vector getResultCR(HttpServletRequest request) {
        Vector result = new Vector();
        return result;        
    }    
}
