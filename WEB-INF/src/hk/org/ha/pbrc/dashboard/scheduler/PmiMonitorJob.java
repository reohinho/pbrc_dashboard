package hk.org.ha.pbrc.dashboard.scheduler;

import hk.org.ha.pbrc.dashboard.bean.SampleBean;
import hk.org.ha.pbrc.dashboard.bean.SeriesBean;
import hk.org.ha.pbrc.dashboard.constant.AppConstants;
import hk.org.ha.pbrc.dashboard.dao.PmiMeasureDao;
import hk.org.ha.pbrc.dashboard.jdbc.DataSource;

import java.io.FileWriter;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class PmiMonitorJob implements Job {
    
    public PmiMonitorJob() {
        super();
    }
    
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        
        try {
            FileWriter pmiWriter = new FileWriter(AppConstants.DATASOURCE_TXT_FOLDER+AppConstants.PMI_MEASURE_FILE_WRITER);
            pmiWriter.write(toString(getResult()));      
            pmiWriter.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }               
        
    }        
    
    public Vector getResult(){
        Connection conn = null;     
        Vector result = new Vector();
        String[] hospitalCde = new String[7];
        String[] subType = new String[2];
        String hospitalList = "PMH|PWH|QEH|QMH|PYN|UCH|TMH";
        String subTypeList = "MINIBAR_PROCESSING|SOURCE_PROCESSING";     
        hospitalCde = hospitalList.split("\\|");  
        subType = subTypeList.split("\\|");
        try {
            conn = DataSource.getMBarConnection();             
            PmiMeasureDao dao = new PmiMeasureDao();
            result.add(dao.getPmiStats(hospitalCde, subType, conn));      
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (conn!=null) try { conn.close(); } catch (Exception e) { e.printStackTrace(); }
        }
        return result;      
    }
    
    public String toString(Vector vec) {       
        SeriesBean srcBean = new SeriesBean("SOURCE");
        SeriesBean mbBean = new SeriesBean("MINIBAR");
        List<SampleBean> list = new ArrayList<SampleBean>();
                        
        list = (List<SampleBean>)vec.get(0);        
        for (int i = 0; i < list.size() ; i++ ) {
            SampleBean bean = list.get(i); 
            if(bean.getLabel().equals("MINIBAR_PROCESSING")) {
                mbBean.addValue(bean.getValue());  
            }
            else if(bean.getLabel().equals("SOURCE_PROCESSING")) {
                srcBean.addValue(bean.getValue());                
            }                 
        }                        
      
        return "{\"data\":"+mbBean.toString()+", \"data2\":"+srcBean.toString()+"}";                                                                                                                                                                                         
    }
}
