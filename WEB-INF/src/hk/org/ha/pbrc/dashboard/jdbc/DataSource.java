package hk.org.ha.pbrc.dashboard.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.naming.Context;
import javax.naming.InitialContext;
import org.apache.log4j.Logger;


public final class DataSource
{

  public static Connection getMBarConnection() {
    Connection connection = null;
    Logger logger = Logger.getLogger("DASH_BOARD");
    try
    {
      Context ctx = new InitialContext();
      Object datasourceRef = ctx.lookup("java:/comp/env/jdbc/pbrcbar-corp-s1-pbrc-staging-ds");
      //Object datasourceRef = ctx.lookup("jdbc/pbrc_all_sp19");        

      javax.sql.DataSource ds = (javax.sql.DataSource)datasourceRef;
      connection = ds.getConnection();
    }
    catch (Exception e) {
      logger.debug("Error : Unable to get Connection!", e);
    }
    return connection;
  }
  
  public static Connection getMBarCRConnection() {
      Connection connection = null;
      Logger logger = Logger.getLogger("DASH_BOARD");
      try
      {
        Context ctx = new InitialContext();
        Object datasourceRef = ctx.lookup("java:/comp/env/jdbc/pbrcbar-corp-cutover-s1-pbrc-staging-ds");
        //Object datasourceRef = ctx.lookup("jdbc/pbrc_all_sp20");        

        javax.sql.DataSource ds = (javax.sql.DataSource)datasourceRef;
        connection = ds.getConnection();
      }
      catch (Exception e) {
        logger.debug("Error : Unable to get Connection!", e);
      }
      return connection;
  }
  
    public static Connection getPBRCConnection() {
      Connection connection = null;
      Logger logger = Logger.getLogger("DASH_BOARD");
      try
      {
        Context ctx = new InitialContext();
        Object datasourceRef = ctx.lookup("java:/comp/env/jdbc/dashboard-corp-s2-billing-schema-ds");
        //Object datasourceRef = ctx.lookup("jdbc/dashboard-corp-s2-billing-schema-ds");

        javax.sql.DataSource ds = (javax.sql.DataSource)datasourceRef;
        connection = ds.getConnection();
      }
      catch (Exception e) {
        logger.debug("Error : Unable to get Connection!", e);
      }
      return connection;
    }
    
    public static Connection getPbrcSysConnection() {
      Connection connection = null;
      Logger logger = Logger.getLogger("DASH_BOARD");
      try
      {
        Context ctx = new InitialContext();
        Object datasourceRef = ctx.lookup("java:/comp/env/jdbc/dashboard-corp-s2-billing-schema-ds");
        //Object datasourceRef = ctx.lookup("jdbc/pbrc-sys");

        javax.sql.DataSource ds = (javax.sql.DataSource)datasourceRef;
        connection = ds.getConnection();
      }
      catch (Exception e) {
        logger.debug("Error : Unable to get Connection!", e);
      }
      return connection;
    }
    
    public static Connection getPbrcSbSysConnection() {
      Connection connection = null;
      Logger logger = Logger.getLogger("DASH_BOARD");
      try
      {
        Context ctx = new InitialContext();
        Object datasourceRef = ctx.lookup("java:/comp/env/jdbc/dashboard-corp-s2-billing-schema-ds");
        //Object datasourceRef = ctx.lookup("jdbc/pbrcsb-sys");

        javax.sql.DataSource ds = (javax.sql.DataSource)datasourceRef;
        connection = ds.getConnection();
      }
      catch (Exception e) {
        logger.debug("Error : Unable to get Connection!", e);
      }
      return connection;
    }

  public static void close(Object obj) {
    Logger logger = Logger.getLogger("DASH_BOARD");

    if ((obj instanceof Connection)) try {
        ((Connection)obj).close(); } catch (Exception e) { logger.error("Cannot close Connection: ", e);
      }
    if ((obj instanceof PreparedStatement)) try {
        ((PreparedStatement)obj).close(); } catch (Exception e) { logger.error("Cannot close PreparedStatement: ", e);
      }
    if ((obj instanceof ResultSet)) try {
        ((ResultSet)obj).close(); } catch (Exception e) { logger.error("Cannot close ResultSet: ", e);
      }
  }
}