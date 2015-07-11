package hk.org.ha.pbrc.dashboard.servlet;

import hk.org.ha.pbrc.dashboard.bean.SampleBean;
import hk.org.ha.pbrc.dashboard.bean.SeriesBean;
import hk.org.ha.pbrc.dashboard.dao.ImmMeasureDao;
import hk.org.ha.pbrc.dashboard.jdbc.DataSource;

import hk.org.ha.pbrc.dashboard.scheduler.EndToEndMonitorJob;
import hk.org.ha.pbrc.dashboard.utilities.DateUtilities;

import hk.org.ha.pbrc.dashboard.constant.AppConstants;

import java.io.ByteArrayOutputStream;

import java.util.List;
import java.util.Vector;
import java.util.Locale;

import javax.servlet.http.*;
import com.dropbox.core.*;

public class EndToEndMeasurementServlet extends MeasurementServlet {
    private static final String CONTENT_TYPE = "text/html; charset=Big5";
    
    @SuppressWarnings("compatibility:-3145195992198277634")
    private static final long serialVersionUID = 1L;

    protected Vector getResult(HttpServletRequest request){      
        Vector result = new Vector();
        return result;      
    }
    
    protected String toString(HttpServletRequest request) {                        

        DbxAppInfo appInfo = new DbxAppInfo(AppConstants.APP_KEY, AppConstants.APP_SECRET);

        DbxRequestConfig config = new DbxRequestConfig(
            "JavaTutorial/1.0", Locale.getDefault().toString());
        DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(config, appInfo);
    	  
        DbxClient client = new DbxClient(config, AppConstants.ACCESS_TOKEN);        
      
        String sourceSys = request.getParameter("sourceSys");
        String filename = "";
        if (sourceSys.equals("PMI") ) { 
        		filename = AppConstants.N2N_PMI_FILE_WRITER;   
        }
        else if (sourceSys.equals("OPAS") ) {        
				filename = AppConstants.N2N_OPAS_FILE_WRITER;
        }
        else {        
				filename = AppConstants.N2N_GCRS_FILE_WRITER;
        }
        String jsonString = "";
        try {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();     
        DbxEntry.File downloadedFile = client.getFile("/" + filename, null, baos);      
        jsonString = baos.toString();
        }
        catch(Exception e) {
          e.printStackTrace();
        }
        return jsonString;
      
    }
    
    protected Vector getResultCR(HttpServletRequest request) {
        Vector result = new Vector();
        return result;        
    }    
}
