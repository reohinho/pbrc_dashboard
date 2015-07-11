package hk.org.ha.pbrc.dashboard.dao;

import hk.org.ha.pbrc.dashboard.bean.ReportMeasureBean;
import hk.org.ha.pbrc.dashboard.bean.SampleBean;

import hk.org.ha.pbrc.dashboard.jdbc.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ReportMeasureDao {
    private final String getSoaCountSQL = "select count(1) from hkha.rpt_request_queue where report_id = 'PBP0018' and gen_status not in ('C', 'E' )";
    private final String getReportResultSQL = "SELECT " + 
    "  SEQ, TYPE, VALUE, STATUS, NUM_ROW " + 
    "FROM ( " + 
    "    SELECT 1 AS SEQ, 'LAST CASHIER ACTIVITY LOG' AS TYPE , TO_CHAR( MAX(LAST_UPD_DTM) , 'YYYY-MM-DD HH24:MI:SS' )AS VALUE, '' AS STATUS, 0 AS NUM_ROW FROM HKHA.RPT_CASHIER_ACTIVITY_LOG UNION " + 
    "    SELECT 2, 'LAST HA STATEMENT GATHER TIME' AS TYPE, TO_CHAR( MAX(CREATED_DATE_TIME) , 'YYYY-MM-DD HH24:MI:SS' ) AS VALUE, '', 0 FROM HKHA.HA_STATEMENT_GEN_HISTORY UNION " + 
    "    SELECT 3, 'LAST OS STATUS GATHER TIME' AS TYPE, TO_CHAR( MAX(COMPLETE_DATE_TIME), 'YYYY-MM-DD HH24:MI:SS' ) AS VALUE, '', 0 FROM HKHA.HKHA_INV_ENC_OS_CONTROL UNION " + 
    "    SELECT 4, 'LAST RECEIPT BALANCE GATHER TIME' AS TYPE, TO_CHAR( MAX(COMPLETE_DTM), 'YYYY-MM-DD HH24:MI:SS' ) AS VALUE,  '', 0 FROM HKHA.HKHA_RECEIPT_BALANCE_CONTROL UNION " + 
    "    SELECT 5, 'LAST UNSURE DRUG GATHER TIME' AS TYPE, TO_CHAR( MAX(INSERT_DATE_TIME), 'YYYY-MM-DD HH24:MI:SS' ) AS VALUE, '', 0 FROM HKHA.RPT_UNSURE_DRUG_WORKING UNION " + 
    "    SELECT 6, 'LAST CASE DAILY SERVICE SENT TO SP4' AS TYPE , TO_CHAR( COMPLETE_DTM, 'YYYY-MM-DD HH24:MI:SS' ), GEN_STATUS , TOTAL_ROW FROM ( SELECT COMPLETE_DTM, GEN_STATUS , TOTAL_ROW FROM HKHA.RPT_REQUEST_QUEUE WHERE REPORT_ID = 'PBT0099P' ORDER BY REQ_DTM DESC ) WHERE ROWNUM = 1 UNION " + 
    "    SELECT 7, 'LAST SFMS BILL INFO SENT TO NBI' AS TYPE, TO_CHAR( COMPLETE_DTM, 'YYYY-MM-DD HH24:MI:SS' ), GEN_STATUS , TOTAL_ROW FROM ( SELECT COMPLETE_DTM, GEN_STATUS , TOTAL_ROW FROM HKHA.RPT_REQUEST_QUEUE WHERE REPORT_ID = 'PBT0098P' ORDER BY REQ_DTM DESC ) WHERE ROWNUM = 1 UNION " + 
    "    SELECT 8, 'LAST MSW ASSESSMENT SENT FROM SP4' AS TYPE, TO_CHAR( COMPLETE_DTM, 'YYYY-MM-DD HH24:MI:SS' ), GEN_STATUS , TOTAL_ROW FROM ( SELECT * FROM HKHA.RPT_REQUEST_QUEUE WHERE REPORT_ID = 'PBP0026_SP4P' ORDER BY REQ_DTM DESC ) WHERE ROWNUM = 1 UNION " + 
    "    SELECT 9, 'LAST SFI DRUG DETAIL SENT FROM FCS' AS TYPE, TO_CHAR( COMPLETE_DTM, 'YYYY-MM-DD HH24:MI:SS' ), GEN_STATUS , TOTAL_ROW FROM ( SELECT * FROM HKHA.RPT_REQUEST_QUEUE WHERE REPORT_ID LIKE 'PBM0009%P' ORDER BY REQ_DTM DESC ) WHERE ROWNUM = 1 UNION " + 
    "    SELECT 10, 'LAST GL INFO SENT TO QEH' AS TYPE, TO_CHAR( COMPLETE_DTM, 'YYYY-MM-DD HH24:MI:SS' ), GEN_STATUS , TOTAL_ROW FROM ( SELECT * FROM HKHA.RPT_REQUEST_QUEUE WHERE REPORT_ID = 'PBC0039P' ORDER BY REQ_DTM DESC ) WHERE ROWNUM = 1 UNION " + 
    "    SELECT 11, 'LAST GL REPORT GEN DATE' AS TYPE, TO_CHAR( COMPLETE_DTM, 'YYYY-MM-DD HH24:MI:SS' ), GEN_STATUS , TOTAL_ROW FROM ( SELECT * FROM HKHA.RPT_REQUEST_QUEUE WHERE REPORT_ID = 'PBC0039' AND REQUEST_TYPE IN ( 'D', 'M' ) ORDER BY REQ_DTM DESC ) WHERE ROWNUM = 1 UNION " + 
    "    SELECT 12, 'GLOBAL AUDIT PARSE' AS TYPE, TO_CHAR(MAX(COMPLETE_PARSE_DATE_TIME), 'YYYY-MM-DD HH24:MI:SS'), '', 0 FROM HKHA.GLOBAL_AUDIT_CONVER_HISTORY WHERE SESSION_KEY <> 'INIT' UNION " + 
    "    SELECT 13, 'GLOBAL AUDIT CONVERT' AS TYPE, TO_CHAR(MAX(COMPLETE_DATE_TIME), 'YYYY-MM-DD HH24:MI:SS'), '', 0 FROM HKHA.GLOBAL_AUDIT_CONVER_HISTORY WHERE SESSION_KEY <> 'INIT' UNION " + 
    "    SELECT 14, 'GLOBAL AUDIT CONVERT (ENCOUNTER)' AS TYPE, TO_CHAR(MAX(COMPLETE_ENC_DATE_TIME), 'YYYY-MM-DD HH24:MI:SS'), '', 0 FROM HKHA.GLOBAL_AUDIT_CONVER_HISTORY WHERE SESSION_KEY <> 'INIT' UNION " + 
    "    SELECT 15, 'GLOBAL AUDIT CONVERT (RECIPIENT)' AS TYPE, TO_CHAR(MAX(COMPLETE_REC_DATE_TIME), 'YYYY-MM-DD HH24:MI:SS'), '', 0 FROM HKHA.GLOBAL_AUDIT_CONVER_HISTORY WHERE SESSION_KEY <> 'INIT' UNION " + 
    "    SELECT 16, 'LAST MANUAL CREATED DEBTOR' AS TYPE, TO_CHAR( MAX(DATE_TIME) , 'YYYY-MM-DD HH24:MI:SS' ) AS VALUE, '', 0 FROM HKHA.RPT_MANUAL_CREATED_DEBTOR_WORK UNION " + 
    "    SELECT 17, 'LAST SPECIAL FCS SERVICE' AS TYPE, TO_CHAR( MAX(DATE_TIME) , 'YYYY-MM-DD HH24:MI:SS' ) AS VALUE, '' , 0 FROM HKHA.HKHA_SPECIAL_FCS_SERVICE UNION " + 
    "    SELECT 18, 'LAST REPRINT RECEIPT' AS TYPE, TO_CHAR( MAX(LAST_UPDATE_DTM) , 'YYYY-MM-DD HH24:MI:SS' ) AS VALUE, '' , 0 FROM HKHA.DAILY_TXN_REPRINT_HISTORY UNION " + 
    "    SELECT 19, 'LAST GATHER LOCATION AND PAYCODE HISTORY' AS TYPE, TO_CHAR( MAX(DATE_TIME) , 'YYYY-MM-DD HH24:MI:SS' ) AS VALUE, '', 0 FROM HKHA.GLOBAL_AUDIT_LOCATION_HISTORY WHERE TRUNC(DATE_TIME) >= TRUNC(SYSDATE-1) UNION " + 
    "    SELECT 20, 'LAST UNMATCH WAIVER' AS TYPE, TO_CHAR( MAX(DATE_TIME), 'YYYY-MM-DD HH24:MI:SS' ) AS VALUE, '' , 0 FROM HKHA.GLOBAL_AUDIT_WAIVER_UNMATCHED UNION " + 
    "    SELECT 21, 'LAST CANCEL INVOICE' AS TYPE, TO_CHAR( MAX(DATE_TIME), 'YYYY-MM-DD HH24:MI:SS' ) AS VALUE, '' , 0  FROM HKHA.GLOBAL_AUDIT_CANCEL_INVOICE  WHERE DATE_TIME >= TRUNC(SYSDATE-1) " + 
    ") " + 
    "ORDER BY SEQ";
    public SampleBean getSOACount(String measureType, String subType) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;   
        
        SampleBean sampleBean = new SampleBean();
        try {
            conn = DataSource.getPBRCConnection();            
            ps = conn.prepareStatement(getSoaCountSQL);
            subType = "SOA";
            //ps.setString(1, measureType);
            //ps.setString(2, subType);
            rs = ps.executeQuery();
            if(rs.next()) {
                sampleBean.setLabel(subType);
                sampleBean.setValue(rs.getInt(1));       
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
        return sampleBean;             
    }
    
    public Vector getReportStatus() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;   
        Vector result = new Vector();
        try {
            conn = DataSource.getPBRCConnection();            
            ps = conn.prepareStatement(getReportResultSQL);
            rs = ps.executeQuery();
            int i;
            while(rs.next()) {
                i=2;
                ReportMeasureBean reportBean = new ReportMeasureBean();
                
                reportBean.setLabel(rs.getString(i++));
                reportBean.setDateStr(rs.getString(i++)); 
                reportBean.setStatusStr(rs.getString(i++));
                reportBean.setNoOfRow(rs.getInt(i++));
                result.add(reportBean);
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
        return result;    
    }
    
}
