package hk.org.ha.pbrc.dashboard.servlet;

import hk.org.ha.pbrc.dashboard.bean.SampleBean;
import hk.org.ha.pbrc.dashboard.bean.SeriesBean;
import hk.org.ha.pbrc.dashboard.dao.PmiMeasureDao;

import hk.org.ha.pbrc.dashboard.jdbc.DataSource;

import hk.org.ha.pbrc.dashboard.scheduler.PmiMonitorJob;

import hk.org.ha.pbrc.dashboard.constant.AppConstants;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.Locale;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.dropbox.core.*;

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
      
        DbxAppInfo appInfo = new DbxAppInfo(AppConstants.APP_KEY, AppConstants.APP_SECRET);

        DbxRequestConfig config = new DbxRequestConfig(
            "JavaTutorial/1.0", Locale.getDefault().toString());
        DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(config, appInfo);
    	  
        DbxClient client = new DbxClient(config, AppConstants.ACCESS_TOKEN);        
            
        String filename = "";
        filename = AppConstants.PMI_MEASURE_FILE_WRITER;   
        
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
    
}
