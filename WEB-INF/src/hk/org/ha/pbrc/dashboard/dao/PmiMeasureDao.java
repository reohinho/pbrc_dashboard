package hk.org.ha.pbrc.dashboard.dao;

import hk.org.ha.pbrc.dashboard.utilities.DateUtilities;
import hk.org.ha.pbrc.dashboard.bean.SampleBean;
import hk.org.ha.pbrc.dashboard.jdbc.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

public class PmiMeasureDao {
    
    private final String getPmiStatsSQL = "select top 1 duration from dashboard_measure where measure_type = ? and sub_type = ? order by last_upd_dtm desc";
    private final String getSubTypeSQL = "select distinct sub_type from dashboard_measure";
    
    
    private final String getPmiTrendSQL = "select convert(char(5), last_upd_dtm, 18) Period, avg(duration) Processing_Time from dashboard_measure " + 
                                            "where measure_type like 'PMI-PMI-%' " + 
                                            "and sub_type = ? " + 
                                            "and last_upd_dtm >= ? and last_upd_dtm <= ? " + 
                                            "group by convert(char(5), last_upd_dtm, 18) " +
                                            "order by convert(char(5), last_upd_dtm, 18) ";

    
    public List<SampleBean> getPmiStats(String[] measureType, String[] subType, Connection conn) {
        PreparedStatement ps = null;
        ResultSet rs = null;   
        
        List<SampleBean> listOfSample = new ArrayList<SampleBean>();
        try {
            conn = DataSource.getMBarConnection();            
            for(int i = 0; i<measureType.length ; i++) {
                for(int j = 0; j<subType.length ; j++) {
                    ps = conn.prepareStatement(getPmiStatsSQL);
                    ps.setString(1, "PMI-PMI-"+measureType[i]);
                    ps.setString(2, subType[j]);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        SampleBean sample = new SampleBean(); 
                        sample.setLabel(subType[j]);
                        sample.setValue(rs.getInt(1));       
                        listOfSample.add(sample);                
                    }            
                }
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
        return listOfSample;
    }
    
    public List<SampleBean> getTrendBySubType(String subType, int date) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<SampleBean> list = new ArrayList<SampleBean>();
        
        try {
            conn = DataSource.getMBarConnection();
            ps = conn.prepareStatement(getPmiTrendSQL);
            ps.setString(1, subType);
            ps.setString(2, DateUtilities.getDate(date, -3));
            ps.setString(3, DateUtilities.getDate(date, 0));
            rs = ps.executeQuery();
            while (rs.next()) {
                SampleBean bean = new SampleBean();
                bean.setLabel(rs.getString(1));
                bean.setValue(rs.getInt(2));
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
