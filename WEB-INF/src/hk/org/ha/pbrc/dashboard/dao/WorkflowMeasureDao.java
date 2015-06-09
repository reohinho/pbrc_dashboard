package hk.org.ha.pbrc.dashboard.dao;

import hk.org.ha.pbrc.dashboard.bean.WorkflowDetailBean;
import hk.org.ha.pbrc.dashboard.utilities.DateUtilities;
import hk.org.ha.pbrc.dashboard.bean.WorkflowSampleBean;
import hk.org.ha.pbrc.dashboard.jdbc.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

public class WorkflowMeasureDao {
    
    private final String getWorkflowTop20SQL = "select po_details.queue_id, po_details.agent_name, po_details.event_date_time, po_details.origin_code, po_details.priority, po_details.ROW_COUNT, host_name, agent_status from ( " + 
                                                "SELECT PEQ.QUEUE_ID, PEQN.AGENT_NAME, TO_CHAR(PEQ.EVENT_DATE_TIME, 'YYYY-MM-DD HH24:MI:SS') event_date_time,PEQ.ORIGIN_CODE, PEQ.PRIORITY, PO_COUNT.NUM ROW_COUNT " + 
                                                ", substr(cr.ip_address,1,12) host_name, cr.requested_state agent_status "+
                                                "FROM BILLING_SCHEMA.PO_EVENT_QUEUE PEQ, BILLING_SCHEMA.PO_EVENT_QUEUE_NAMES PEQN, " + 
                                                "(select min(event_date_time) event_date_time, queue_id, priority, count(1) num  " + 
                                                "from billing_schema.po_event_queue " + 
                                                "GROUP BY QUEUE_ID ,PRIORITY " + 
                                                ") PO_COUNT " + 
                                                " , BILLING_SCHEMA.CONTAINER_REGISTRY CR "+
                                                "where peq.event_date_time = PO_COUNT.event_date_time " + 
                                                "and peq.queue_id = PO_COUNT.queue_id " + 
                                                "and peq.queue_id = peqn.name " + 
                                                "and peq.priority = PO_COUNT.priority " + 
                                                "AND CR.NAME = PEQN.AGENT_NAME "+
                                                "order by po_count.num desc) po_details " + 
                                                "where rownum <= 20 ";
    
    private final String getWorkflowQueueByDtm= "select * from ( " + 
                                                "SELECT PEQ.QUEUE_ID,PEQN.AGENT_NAME, TO_CHAR(PO_COUNT.EVENT_DATE_TIME, 'YYYY-MM-DD HH24:MI:SS') min_create_date, PEQ.ORIGIN_CODE,PEQ.PRIORITY, PO_COUNT.NUM " + 
                                                ", substr(cr.ip_address,1,12) host_name, cr.requested_state agent_status "+
                                                "FROM BILLING_SCHEMA.PO_EVENT_QUEUE PEQ, BILLING_SCHEMA.PO_EVENT_QUEUE_NAMES PEQN, " + 
                                                "(select min(event_date_time) event_date_time, queue_id, priority, count(1) num " + 
                                                "from billing_schema.po_event_queue " + 
                                                "GROUP BY QUEUE_ID ,PRIORITY " + 
                                                ") PO_COUNT " + 
                                                ", BILLING_SCHEMA.CONTAINER_REGISTRY CR "+
                                                "where peq.event_date_time = PO_COUNT.event_date_time " + 
                                                "and peq.queue_id = PO_COUNT.queue_id " + 
                                                "and peq.queue_id = peqn.name " + 
                                                "and peq.priority = PO_COUNT.priority " + 
                                                "AND CR.NAME = PEQN.AGENT_NAME "+
                                                "order by peq.event_date_time ) po_by_dtm " + 
                                                "where rownum <= 20 ";
    
    private final String getWorkflowQueueByCategory = "select count(1), origin_code, TO_CHAR(min(event_date_time), 'YYYY-MM-DD HH24:MI:SS'), TO_CHAR(min(event_date_time), 'YYYY-MM-DD HH24:MI:SS'), priority from billing_schema.po_event_queue group by origin_code, priority " + 
                                                    "order by min(event_date_time) ";
     
    private final String getWorkflowStatsDayTimeSQL = "select distinct " + 
    //" (case when datepart(hh, d.last_upd_dtm) < 10 then '0' || convert(char(2), datepart(hh, d.last_upd_dtm)) else convert(char(2), datepart(hh, d.last_upd_dtm)) end) h, " + 
    "convert(char(5), last_upd_dtm, 18) h, " +
    " (select duration from pbrc_staging..dashboard_measure where measure_type = 'WORKFLOW' and sub_type = 'CORP' and last_upd_dtm = d.last_upd_dtm) corp, " + 
    " (select duration from pbrc_staging..dashboard_measure where measure_type = 'WORKFLOW' and sub_type = 'NON-CORP' and last_upd_dtm = d.last_upd_dtm) non_corp " + 
    "from pbrc_staging..dashboard_measure d " + 
    "where d.measure_type = 'WORKFLOW' and datepart(hh, d.last_upd_dtm) >= 8 and convert(char(10), d.last_upd_dtm, 105) = ? " + 
    "order by d.last_upd_dtm";
    
    private final String getWorkflowStatsDayEndSQL = "select distinct " + 
    //" (case when datepart(hh, d.last_upd_dtm) < 10 then '0' || convert(char(2), datepart(hh, d.last_upd_dtm)) else convert(char(2), datepart(hh, d.last_upd_dtm)) end) h, " + 
    "convert(char(5), last_upd_dtm, 18) h, " +                                                     
    " (select duration from pbrc_staging..dashboard_measure where measure_type = 'WORKFLOW' and sub_type = 'CORP' and last_upd_dtm = d.last_upd_dtm) corp, " + 
    " (select duration from pbrc_staging..dashboard_measure where measure_type = 'WORKFLOW' and sub_type = 'NON-CORP' and last_upd_dtm = d.last_upd_dtm) non_corp " + 
    "from pbrc_staging..dashboard_measure d " + 
    "where d.measure_type = 'WORKFLOW' and datepart(hh, d.last_upd_dtm) < 8 and convert(char(10), d.last_upd_dtm, 105) = ? " + 
    "order by d.last_upd_dtm";

    public List<WorkflowSampleBean> getWorkflowDaytime(String dateStr) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<WorkflowSampleBean> list = new ArrayList<WorkflowSampleBean>();
                
        try {
            conn = DataSource.getMBarConnection();
            ps = conn.prepareStatement(getWorkflowStatsDayTimeSQL);
            ps.setString(1, dateStr);
            rs = ps.executeQuery();
            while (rs.next()) {
                WorkflowSampleBean bean = new WorkflowSampleBean();
                bean.setLabel(rs.getString(1));
                bean.setCorpValue(rs.getInt(2));
                bean.setNonCorpValue(rs.getInt(3));
                list.add(bean);
            }            
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            if(rs!=null) try {
                    DataSource.close(rs); } catch(Exception e) {e.printStackTrace();}
            if(ps!=null) try {
                    DataSource.close(ps); } catch(Exception e) {e.printStackTrace();}         
            if(conn!=null) try {
                    DataSource.close(conn); } catch(Exception e) {e.printStackTrace();}
        }
                
        if(list.size()==0) {
            for(int i=96;i<288;i++) {
                WorkflowSampleBean bean = new WorkflowSampleBean();
                bean.setLabel(DateUtilities.minuteToDateFormat("HH:mm", i*5));
                bean.setCorpValue(0);
                bean.setNonCorpValue(0);
                list.add(bean);
            }
        }
        
        return list;

    }
    
    public List<WorkflowSampleBean> getWorkflowDayend(String dateStr) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<WorkflowSampleBean> list = new ArrayList<WorkflowSampleBean>();
                
        try {
            conn = DataSource.getMBarConnection();
            ps = conn.prepareStatement(getWorkflowStatsDayEndSQL);
            ps.setString(1, dateStr);
            rs = ps.executeQuery();
            while (rs.next()) {
                WorkflowSampleBean bean = new WorkflowSampleBean();
                bean.setLabel(rs.getString(1));
                bean.setCorpValue(rs.getInt(2));
                bean.setNonCorpValue(rs.getInt(3));
                list.add(bean);
            }            
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            if(rs!=null) try {
                    DataSource.close(rs); } catch(Exception e) {e.printStackTrace();}
            if(ps!=null) try {
                    DataSource.close(ps); } catch(Exception e) {e.printStackTrace();}         
            if(conn!=null) try {
                    DataSource.close(conn); } catch(Exception e) {e.printStackTrace();}
        }
        
        if(list.size()==0) {
            for(int i=0;i<96;i++) {
                WorkflowSampleBean bean = new WorkflowSampleBean();
                bean.setLabel(DateUtilities.minuteToDateFormat("HH:mm", i*5));
                bean.setCorpValue(0);
                bean.setNonCorpValue(0);
                list.add(bean);
            }
        }
        
        return list;
    }
    
    public List<WorkflowDetailBean> getWorkflowTop20() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<WorkflowDetailBean> list = new ArrayList<WorkflowDetailBean>();
        
        try {
            conn = DataSource.getPBRCConnection();
            ps = conn.prepareStatement(getWorkflowTop20SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                int i = 1;
                WorkflowDetailBean bean = new WorkflowDetailBean();
                bean.setQueueId(rs.getString(i++));
                bean.setAgentName(rs.getString(i++));
                bean.setEventDtm(rs.getString(i++));
                bean.setOriginCode(rs.getString(i++));
                bean.setPriority(rs.getInt(i++));
                bean.setRowCount(rs.getInt(i++));
                bean.setHostName(rs.getString(i++));
                bean.setAgentStatus(rs.getString(i++));
                
                list.add(bean);
            }
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            if(rs!=null) try {
                    DataSource.close(rs); } catch(Exception e) {e.printStackTrace();}
            if(ps!=null) try {
                    DataSource.close(ps); } catch(Exception e) {e.printStackTrace();}         
            if(conn!=null) try {
                    DataSource.close(conn); } catch(Exception e) {e.printStackTrace();}
        }              
        
        return list;       
    }
    
    public List<WorkflowDetailBean> getWorkflowTop20ByEventDtm() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<WorkflowDetailBean> list = new ArrayList<WorkflowDetailBean>();
        
        try {
            conn = DataSource.getPBRCConnection();
            ps = conn.prepareStatement(getWorkflowQueueByDtm);
            rs = ps.executeQuery();
            while (rs.next()) {
                int i = 1;
                WorkflowDetailBean bean = new WorkflowDetailBean();
                bean.setQueueId(rs.getString(i++));
                bean.setAgentName(rs.getString(i++));
                bean.setEventDtm(rs.getString(i++));
                bean.setOriginCode(rs.getString(i++));
                bean.setPriority(rs.getInt(i++));
                bean.setRowCount(rs.getInt(i++));
                bean.setHostName(rs.getString(i++));
                bean.setAgentStatus(rs.getString(i++));
                
                list.add(bean);
            }
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            if(rs!=null) try {
                    DataSource.close(rs); } catch(Exception e) {e.printStackTrace();}
            if(ps!=null) try {
                    DataSource.close(ps); } catch(Exception e) {e.printStackTrace();}         
            if(conn!=null) try {
                    DataSource.close(conn); } catch(Exception e) {e.printStackTrace();}
        }              
        
        return list;       
    }
    
    
    public List<WorkflowDetailBean> getWorkflowByCategory() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<WorkflowDetailBean> list = new ArrayList<WorkflowDetailBean>();
        
        try {
            conn = DataSource.getPBRCConnection();
            ps = conn.prepareStatement(getWorkflowQueueByCategory);
            rs = ps.executeQuery();
            while (rs.next()) {
                int i = 1;
                WorkflowDetailBean bean = new WorkflowDetailBean();
                bean.setRowCount(rs.getInt(i++));
                bean.setOriginCode(rs.getString(i++));
                bean.setMinEventDtmStr(rs.getString(i++));
                bean.setMaxEventDtmStr(rs.getString(i++));
                bean.setPriority(rs.getInt(i++));
                
                list.add(bean);
            }
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            if(rs!=null) try {
                    DataSource.close(rs); } catch(Exception e) {e.printStackTrace();}
            if(ps!=null) try {
                    DataSource.close(ps); } catch(Exception e) {e.printStackTrace();}         
            if(conn!=null) try {
                    DataSource.close(conn); } catch(Exception e) {e.printStackTrace();}
        }              
        
        return list;       
    }
    
    
}
