package hk.org.ha.pbrc.dashboard.dao;

import hk.org.ha.pbrc.dashboard.bean.SampleBean;
import hk.org.ha.pbrc.dashboard.jdbc.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

public class BlockingMeasureDao {
    public BlockingMeasureDao() {
        super();
    }
    
    private final String getBlockingCountSQL = "select count(1) from dashboard_measure where measure_type = 'DB_BLOCKING' " + 
                                               "and last_upd_dtm > convert(char(17), getdate(), 116)";
    
    private final String getClientSession = "select count(distinct client_identifier) " + 
                                            "    from gv$session where client_identifier is not null " + 
                                            "    and client_identifier not like '%192.168.1.150%' " + 
                                            "    and client_identifier not like '%HA5%' " + 
                                            "    and client_identifier not like 'PBRC_ADMIN%' ";        
    
    public SampleBean getBlockingCount() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;   
        
        SampleBean sample = new SampleBean();
        sample.setLabel("Blocking Session");
        sample.setValue(0);       
        try {
            conn = DataSource.getMBarConnection();            

            ps = conn.prepareStatement(getBlockingCountSQL);
            rs = ps.executeQuery();
            if(rs.next()) {
                sample.setValue(rs.getInt(1));       
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
        return sample;
    }
    
    public SampleBean getClientSession() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;   
        
        SampleBean sample = new SampleBean();
        sample.setLabel("Client Session");
        sample.setValue(0);       
        try {
            conn = DataSource.getPBRCConnection();            

            ps = conn.prepareStatement(getClientSession);
            rs = ps.executeQuery();
            if(rs.next()) {
                sample.setValue(rs.getInt(1));       
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
        return sample;
    }
    
}
