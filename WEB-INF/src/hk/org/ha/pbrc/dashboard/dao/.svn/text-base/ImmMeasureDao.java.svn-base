package hk.org.ha.pbrc.dashboard.dao;

import hk.org.ha.pbrc.dashboard.utilities.DateUtilities;
import hk.org.ha.pbrc.dashboard.bean.SampleBean;
import hk.org.ha.pbrc.dashboard.jdbc.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;

public class ImmMeasureDao {
    public ImmMeasureDao() {
        super();
    }
    
    private final String getImmMeasureSQL = "select top 1 sub_type ,  measure_type , duration from dashboard_measure where measure_type = ? and sub_type = ? order by last_upd_dtm desc";
    private final String getImmMeasureWithDtmSQL = "select top 1 sub_type ,  measure_type , duration from dashboard_measure where measure_type = ? and sub_type = ? and last_upd_dtm >= ? order by last_upd_dtm desc";
    private final String getImmTransactionSQL = "select top 1 duration from dashboard_measure where measure_type = ? and sub_type = ? order by last_upd_dtm desc";    
    private final String getTrendSQL = "select convert(char(5), last_upd_dtm, 18) Period, avg(duration) Processing_Time from dashboard_measure " + 
                                            "where measure_type like ? " + 
                                            "and sub_type = ? " + 
                                            "and last_upd_dtm >= ? and last_upd_dtm <= ? " + 
                                            "group by convert(char(5), last_upd_dtm, 18) " +
                                            "order by convert(char(5), last_upd_dtm, 18) ";
    private final String getLastSampleDtmSQL = "select max(last_upd_dtm) from dashboard_measure";

    
    public List<SampleBean> getBeansByMeasureType(Connection conn, String measureType, String subType) {
        PreparedStatement ps = null;
        ResultSet rs = null;           
        List<SampleBean> listOfSample = new ArrayList<SampleBean>();
        try {
            ps = conn.prepareStatement(getImmMeasureSQL);
            ps.setString(1, measureType);
            ps.setString(2, subType);
            rs = ps.executeQuery();
            if (rs.next()) {
                SampleBean sample = new SampleBean(); 
                sample.setLabel(rs.getString("sub_type"));
                sample.setValue(rs.getInt("duration"));       
                listOfSample.add(sample);                
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
    
    public List<SampleBean> getBeansByMeasureType(Connection conn, String[] measureType, String[] subType, String lastUpdDtmStr) {
        PreparedStatement ps = null;
        ResultSet rs = null;           
        List<SampleBean> listOfSample = new ArrayList<SampleBean>();
        try {
            for(int i = 0; i<measureType.length ; i++) {
                ps = conn.prepareStatement(getImmMeasureWithDtmSQL);
                ps.setString(1, measureType[i]);
                ps.setString(2, subType[i]);
                ps.setString(3, lastUpdDtmStr);
                rs = ps.executeQuery();
                if (rs.next()) {
                    SampleBean sample = new SampleBean(); 
                    sample.setLabel(rs.getString("sub_type"));
                    sample.setValue(rs.getInt("duration"));       
                    listOfSample.add(sample);                
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
        }
        return listOfSample;
    }
    
    
    public List<SampleBean> getTransByMeasureType(String measureType, String subType) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;   
        
        List<SampleBean> listOfSample = new ArrayList<SampleBean>();
        try {
            conn = DataSource.getMBarConnection();            

            ps = conn.prepareStatement(getImmTransactionSQL);
            ps.setString(1, measureType);
            ps.setString(2, subType);
            rs = ps.executeQuery();
            if(rs.next()) {
                SampleBean sample = new SampleBean();
                sample.setLabel(subType);
                sample.setValue(rs.getInt(1));       
                listOfSample.add(sample);
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
    
    public List<SampleBean> getTrend(String measureType, String subType, int date) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<SampleBean> list = new ArrayList<SampleBean>();
        
        try {
            conn = DataSource.getMBarConnection();
            ps = conn.prepareStatement(getTrendSQL);
            ps.setString(1, measureType);
            ps.setString(2, subType);
            ps.setString(3, DateUtilities.getDate(date, -3));
            ps.setString(4, DateUtilities.getDate(date, 0));
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
    
    public Timestamp getLastSampleDtm() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Timestamp ts = null;
        
        try {
            conn = DataSource.getMBarConnection();
            ps = conn.prepareStatement(getLastSampleDtmSQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                ts = rs.getTimestamp(1);
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
        
        return ts;
    }

}
