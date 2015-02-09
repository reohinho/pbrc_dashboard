package hk.org.ha.pbrc.dashboard.servlet;

import hk.org.ha.pbrc.dashboard.bean.SampleBean;
import hk.org.ha.pbrc.dashboard.bean.SeriesBean;
import hk.org.ha.pbrc.dashboard.dao.ImmMeasureDao;
import hk.org.ha.pbrc.dashboard.jdbc.DataSource;

import hk.org.ha.pbrc.dashboard.scheduler.EndToEndMonitorJob;
import hk.org.ha.pbrc.dashboard.utilities.DateUtilities;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.*;

public class EndToEndMeasurementServlet extends MeasurementServlet {
    private static final String CONTENT_TYPE = "text/html; charset=Big5";
    
    @SuppressWarnings("compatibility:-3145195992198277634")
    private static final long serialVersionUID = 1L;

    protected Vector getResult(HttpServletRequest request){      
        String sourceSys = request.getParameter("sourceSys");
        Vector result = new Vector();
        EndToEndMonitorJob job = new EndToEndMonitorJob();
        result = job.getResult(sourceSys);
        return result;      
    }
    
    protected String toString(Vector vec) {                        
        EndToEndMonitorJob job = new EndToEndMonitorJob();
        return job.toString(vec);
    }
    
    protected Vector getResultCR(HttpServletRequest request) {
        Vector result = new Vector();
        return result;        
    }    
}
