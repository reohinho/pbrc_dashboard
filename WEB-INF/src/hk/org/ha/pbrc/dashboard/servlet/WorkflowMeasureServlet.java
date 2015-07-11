package hk.org.ha.pbrc.dashboard.servlet;

import hk.org.ha.pbrc.dashboard.utilities.DateUtilities;
import hk.org.ha.pbrc.dashboard.bean.WorkflowSampleBean;
import hk.org.ha.pbrc.dashboard.dao.WorkflowMeasureDao;

import hk.org.ha.pbrc.dashboard.scheduler.WorkflowMonitorJob;

import hk.org.ha.pbrc.dashboard.constant.AppConstants;

import java.io.ByteArrayOutputStream;

import java.util.List;
import java.util.Vector;
import java.util.Locale;

import javax.servlet.http.*;
import com.dropbox.core.*;

public class WorkflowMeasureServlet extends MeasurementServlet {

    @SuppressWarnings("compatibility:8751845787678257805")
    private static final long serialVersionUID = 1L;

    protected Vector getResult(HttpServletRequest request) {
        Vector result = new Vector();
        return result;        
    }   
  
    protected String toString(HttpServletRequest request) {      

        DbxAppInfo appInfo = new DbxAppInfo(AppConstants.APP_KEY, AppConstants.APP_SECRET);

        DbxRequestConfig config = new DbxRequestConfig(
            "JavaTutorial/1.0", Locale.getDefault().toString());
        DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(config, appInfo);
    	  
        DbxClient client = new DbxClient(config, AppConstants.ACCESS_TOKEN);        
      
        String type = request.getParameter("type");
        String filename = "";
        if (type.equals("Daytime") ) { 
        		filename = AppConstants.WKF_DAYTIME_FILE_WRITER;   
        }            
        else {        
				filename = AppConstants.WKF_DAYEND_FILE_WRITER;
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
