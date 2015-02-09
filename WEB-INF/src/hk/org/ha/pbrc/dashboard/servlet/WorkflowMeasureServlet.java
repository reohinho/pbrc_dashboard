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

    protected Vector getResult(HttpServletRequest request){
    
        String type = request.getParameter("type");
        String previousDate = request.getParameter("previousDate");        
        WorkflowMonitorJob job = new WorkflowMonitorJob();
        Vector result = job.getResult(type, previousDate);            
        return result;    
    }
    
    protected String toString(Vector vec) {        
        WorkflowMonitorJob job = new WorkflowMonitorJob();                                                                                                                                                                                                
        return job.toString(vec);
    }
    
    protected Vector getResultCR(HttpServletRequest request) {
        Vector result = new Vector();
        return result;        
    }    
}
