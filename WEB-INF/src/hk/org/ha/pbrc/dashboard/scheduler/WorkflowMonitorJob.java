package hk.org.ha.pbrc.dashboard.scheduler;

import hk.org.ha.pbrc.dashboard.bean.WorkflowSampleBean;
import hk.org.ha.pbrc.dashboard.constant.AppConstants;
import hk.org.ha.pbrc.dashboard.dao.WorkflowMeasureDao;


import hk.org.ha.pbrc.dashboard.utilities.DateUtilities;

import java.io.FileWriter;

import java.util.List;
import java.util.Vector;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class WorkflowMonitorJob implements Job {
    
    public WorkflowMonitorJob() {
        super();
    }
    
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        
        try {
            FileWriter pmiWriterDaytime = new FileWriter(AppConstants.DATASOURCE_TXT_FOLDER+AppConstants.WKF_DAYTIME_FILE_WRITER);
            pmiWriterDaytime.write(toString(getResult("Daytime", "")));      
            pmiWriterDaytime.close();
            
            FileWriter pmiWriterBatch = new FileWriter(AppConstants.DATASOURCE_TXT_FOLDER+AppConstants.WKF_DAYEND_FILE_WRITER);
            pmiWriterBatch.write(toString(getResult("Dayend", "")));      
            pmiWriterBatch.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }               
        
    }        
    
    public Vector getResult(String type, String previousDate){            
        Vector result = new Vector();  
        
        if(previousDate==null||previousDate.equals("")) {
            previousDate = DateUtilities.getDate("dd-MM-yyyy", -1);
        }
        
        if (type.equals("Daytime") ) {        
             WorkflowMeasureDao dao = new WorkflowMeasureDao();
             result.add(dao.getWorkflowDaytime(previousDate));
             result.add(dao.getWorkflowDaytime(DateUtilities.getCurrentDate("dd-MM-yyyy")));    
        }            
        else {        
            WorkflowMeasureDao dao = new WorkflowMeasureDao();
            result.add(dao.getWorkflowDayend(previousDate));
            result.add(dao.getWorkflowDayend(DateUtilities.getCurrentDate("dd-MM-yyyy")));         
        }
        return result;    
    }
    
    public String toString(Vector vec) {       
        List<WorkflowSampleBean> list = (List<WorkflowSampleBean>)vec.get(0);
        List<WorkflowSampleBean> list2 = (List<WorkflowSampleBean>)vec.get(1);

                
        String jsonString = "{ \"cols\": [{\"id\":\"\",\"label\":\"time\",\"type\":\"string\"},{\"id\":\"\",\"label\":\"corp (previous)\",\"type\":\"number\"},{\"id\":\"\",\"label\":\"non-corp (previous)\",\"type\":\"number\"},{\"id\":\"\",\"label\":\"corp\",\"type\":\"number\"},{\"id\":\"\",\"label\":\"non-corp\",\"type\":\"number\"}],\"rows\":["; 
        for(int i=0; i<list.size();i++) {   

            WorkflowSampleBean bean = list.get(i);                  
            if(i<list2.size()) {
                WorkflowSampleBean bean2 = list2.get(i);
                jsonString += "{\"c\":[{\"v\":\""+bean.getLabel()+"\"},{\"v\":"+bean.getCorpValue()+"},{\"v\":"+bean.getNonCorpValue()+"},{\"v\":"+bean2.getCorpValue()+"},{\"v\":"+bean2.getNonCorpValue()+"}]}\n "; 
            }
            else{
                jsonString += "{\"c\":[{\"v\":\""+bean.getLabel()+"\"},{\"v\":"+bean.getCorpValue()+"},{\"v\":"+bean.getNonCorpValue()+"}]}\n "; 
            }

            if(i<287) {
                jsonString += ",";
            }
        }
        jsonString += "]}"; 
        
        return jsonString;                                                                                                                                                                                             
    }
}
