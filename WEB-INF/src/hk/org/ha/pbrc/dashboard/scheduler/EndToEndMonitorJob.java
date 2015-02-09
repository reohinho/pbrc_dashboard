package hk.org.ha.pbrc.dashboard.scheduler;

import hk.org.ha.pbrc.dashboard.bean.SampleBean;
import hk.org.ha.pbrc.dashboard.bean.SeriesBean;
import hk.org.ha.pbrc.dashboard.constant.AppConstants;
import hk.org.ha.pbrc.dashboard.dao.ImmMeasureDao;
import hk.org.ha.pbrc.dashboard.jdbc.DataSource;
import hk.org.ha.pbrc.dashboard.utilities.DateUtilities;

import java.io.FileWriter;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class EndToEndMonitorJob implements Job {
    public EndToEndMonitorJob() {
        super();
    }

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        
        try {
            FileWriter pmiWriter = new FileWriter(AppConstants.DATASOURCE_TXT_FOLDER+AppConstants.N2N_PMI_FILE_WRITER);
            pmiWriter.write(toString(getResult("PMI")));      
            pmiWriter.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
        try {
            FileWriter opasWriter = new FileWriter(AppConstants.DATASOURCE_TXT_FOLDER+AppConstants.N2N_OPAS_FILE_WRITER);
            opasWriter.write(toString(getResult("OPAS")));
            opasWriter.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        try {
            FileWriter gcrsWriter = new FileWriter(AppConstants.DATASOURCE_TXT_FOLDER+AppConstants.N2N_GCRS_FILE_WRITER);
            gcrsWriter.write(toString(getResult("GCRSLIS")));
            gcrsWriter.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        
    }
    
    public Vector getResult(String sourceSystem) {        
        Connection conn = null;     
        Vector result = new Vector();
        try {
            conn = DataSource.getMBarConnection();             
            
            ImmMeasureDao dao = new ImmMeasureDao();
            String[] hospitalCde = new String[7];
            String[] measureType = new String[4];
            String[] subTypeSuffix = new String[4];
            String hospitalList = "PMH|PWH|QEH|QMH|PYN|UCH|TMH";
            String measureTypeList = "MB_TIME|MB_TIME|IMM_TIME|IMM_TIME";            
            String subTypeSuffixList = "SRC|MBCL|INSERT|DELETE";
            if(sourceSystem!=null&&sourceSystem.equals("GCRSLIS")) {                
                hospitalList = "PWH|QEH|QMH|PMH|PWH|QEH|QMH";
            }            
            hospitalCde = hospitalList.split("\\|");                                    
            measureType = measureTypeList.split("\\|");
            subTypeSuffix = subTypeSuffixList.split("\\|");
            
            int i = 0;
            int j = 0;
            String[] subType = {
            sourceSystem+"-"+hospitalCde[i]+"-"+subTypeSuffix[j++], 
            sourceSystem+"-"+hospitalCde[i]+"-"+subTypeSuffix[j++], 
            sourceSystem+"-"+hospitalCde[i]+"-"+subTypeSuffix[j++], 
            sourceSystem+"-"+hospitalCde[i]+"-"+subTypeSuffix[j++]};            
            result.add(dao.getBeansByMeasureType(conn, measureType, subType, DateUtilities.getCurrentDate("yyyyMMdd"))); 
            i++;
            for (j = 0; j < subTypeSuffix.length; j++) { subType[j] = sourceSystem+"-"+hospitalCde[i]+"-"+subTypeSuffix[j]; }                        
            result.add(dao.getBeansByMeasureType(conn, measureType, subType, DateUtilities.getCurrentDate("yyyyMMdd")));            
            i++;
            for (j = 0; j < subTypeSuffix.length; j++) { subType[j] = sourceSystem+"-"+hospitalCde[i]+"-"+subTypeSuffix[j]; }                        
            result.add(dao.getBeansByMeasureType(conn, measureType, subType, DateUtilities.getCurrentDate("yyyyMMdd")));                     
            i++;            
            if(sourceSystem!=null&&sourceSystem.equals("GCRSLIS")) {                
                sourceSystem = "GCRSRIS";
            }  
            for (j = 0; j < subTypeSuffix.length; j++) { subType[j] = sourceSystem+"-"+hospitalCde[i]+"-"+subTypeSuffix[j]; }                        
            result.add(dao.getBeansByMeasureType(conn, measureType, subType, DateUtilities.getCurrentDate("yyyyMMdd")));   
            i++;
            for (j = 0; j < subTypeSuffix.length; j++) { subType[j] = sourceSystem+"-"+hospitalCde[i]+"-"+subTypeSuffix[j]; }                        
            result.add(dao.getBeansByMeasureType(conn, measureType, subType, DateUtilities.getCurrentDate("yyyyMMdd"))); 
            i++;
            for (j = 0; j < subTypeSuffix.length; j++) { subType[j] = sourceSystem+"-"+hospitalCde[i]+"-"+subTypeSuffix[j]; }                        
            result.add(dao.getBeansByMeasureType(conn, measureType, subType, DateUtilities.getCurrentDate("yyyyMMdd"))); 
            i++;
            for (j = 0; j < subTypeSuffix.length; j++) { subType[j] = sourceSystem+"-"+hospitalCde[i]+"-"+subTypeSuffix[j]; }                        
            result.add(dao.getBeansByMeasureType(conn, measureType, subType, DateUtilities.getCurrentDate("yyyyMMdd"))); 
                    
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
        SeriesBean srcBean = new SeriesBean("SRC");
        SeriesBean mbclBean = new SeriesBean("MBCL");
        SeriesBean immInsBean = new SeriesBean("IMM_INSERT");
        SeriesBean immDelBean = new SeriesBean("IMM_DELETE");
        List<SampleBean> list = new ArrayList<SampleBean>();
        for (int j = 0; j < vec.size(); j ++) {                       
            list = (List<SampleBean>)vec.get(j);        
            for (int i = 0; i < list.size() ; i++ ) {
                SampleBean bean = list.get(i); 
                switch(i) {
                case 0:
                    srcBean.addValue(bean.getValue());                
                    break;
                case 1:
                    mbclBean.addValue(bean.getValue());  
                    break;
                case 2:
                    immInsBean.addValue(bean.getValue());                
                    break;
                case 3:
                    immDelBean.addValue(bean.getValue());     
                    break;
                };                    
            }                        
        }        
        return "{\"data\":"+immDelBean.toString()+", \"data2\":"+immInsBean.toString()+", \"data3\":"+mbclBean.toString()+", \"data4\":"+srcBean.toString()+"}";
    }
    
}
