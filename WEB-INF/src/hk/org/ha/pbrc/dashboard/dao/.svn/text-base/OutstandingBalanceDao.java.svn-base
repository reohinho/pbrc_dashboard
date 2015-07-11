package hk.org.ha.pbrc.dashboard.dao;

import hk.org.ha.pbrc.dashboard.utilities.DateUtilities;
import hk.org.ha.pbrc.dashboard.bean.OutStandingBalanceBean;
import hk.org.ha.pbrc.dashboard.bean.SampleBean;
import hk.org.ha.pbrc.dashboard.jdbc.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;


public class OutstandingBalanceDao {

    private static final String getOutStandingBalanceTrendSQL =
        "select last_upd_dtm time, duration os, convert(char(5), last_upd_dtm, 18) timestring from dashboard_measure " +
        "where measure_type = 'OS_BAL'" + "  and sub_type = ? " +
        "and convert(char(8), last_upd_dtm, 112) = ? order by last_upd_dtm asc";
    
    private static final String getOutStandingBalanceSQL = "select top 1 duration from dashboard_measure where measure_type = 'OS_BAL' and sub_type = ? order by last_upd_dtm desc";


    public List<OutStandingBalanceBean> getTrendBySubType(String subType,
                                                          int date) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<OutStandingBalanceBean> osbs =
            new ArrayList<OutStandingBalanceBean>();

        try {
            conn = DataSource.getMBarConnection();
            ps = conn.prepareStatement(getOutStandingBalanceTrendSQL);
            ps.setString(1, subType);
            ps.setString(2, DateUtilities.getDate("yyyyMMdd", date));
                        
            rs = ps.executeQuery();
            while (rs.next()) {
                OutStandingBalanceBean osb = new OutStandingBalanceBean();
                osb.setLastUpdDtm(rs.getTimestamp(1));
                osb.setValue(rs.getInt(2));
                osb.setTimeString(rs.getString(3));
                osbs.add(osb);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null)
                try {
                    DataSource.close(rs);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            if (ps != null)
                try {
                    DataSource.close(ps);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            if (conn != null)
                try {
                    DataSource.close(conn);
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }

        return osbs;
    }
    
    public SampleBean getOSBalanceMeasure(String subType) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        SampleBean sampleBean = new SampleBean();
        try {
            conn = DataSource.getMBarConnection();
            ps = conn.prepareStatement(getOutStandingBalanceSQL);
            ps.setString(1, subType);
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


}
