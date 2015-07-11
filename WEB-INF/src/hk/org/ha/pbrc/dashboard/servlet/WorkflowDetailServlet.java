package hk.org.ha.pbrc.dashboard.servlet;

import hk.org.ha.pbrc.dashboard.constant.AppConstants;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;

import java.io.FileReader;

import java.util.Locale;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWebAuthNoRedirect;

public class WorkflowDetailServlet extends MeasurementServlet {
    public WorkflowDetailServlet() {
        super();
    }

    protected Vector getResult(HttpServletRequest request) {
        Vector vec = new Vector();
        String type = request.getParameter("detailType");
        vec.add(type);
        return vec;
    }

    protected Vector getResultCR(HttpServletRequest request) {
        return null;
    }

    protected String toString(Vector vec) {
        String detailType = (String)vec.get(0);
        if(detailType!=null ) {

        	DbxAppInfo appInfo = new DbxAppInfo(AppConstants.APP_KEY, AppConstants.APP_SECRET);

            DbxRequestConfig config = new DbxRequestConfig(
                "JavaTutorial/1.0", Locale.getDefault().toString());
            DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(config, appInfo);
        	  
            DbxClient client = new DbxClient(config, AppConstants.ACCESS_TOKEN);       

        	String filename = "";
            if (detailType.equals("top20")){
            	filename = AppConstants.WKF_TOP_20_FILE_WRITER;
            } 
            else if (detailType.equals("bydtm")){
            	filename = AppConstants.WKF_QUEUE_BY_DTM_FILE_WRITER;
            }
            else if (detailType.equals("bycat")){
            	filename = AppConstants.WKF_QUEUE_BY_CATEGORY_FILE_WRITER;
            }
            String divHtml = "";
            try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();     
            DbxEntry.File downloadedFile = client.getFile("/" + filename, null, baos);      
            divHtml = baos.toString();
            }
            catch(Exception e) {
              e.printStackTrace();
            }
            return divHtml;
        }
        return null;
    }

	@Override
	protected String toString(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
}
