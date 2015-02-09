package hk.org.ha.pbrc.dashboard.constant;

public class AppConstants {        
    
    public static final String LDAP_URL = "ldapscorp.server.ha.org.hk:389";
    
    public static final String LDAP_OK = "Login Successfull.";
    public static final String LDAP_AUTHEN_FAIL = "Login Failed. Wrong username or password.";
    public static final String LDAP_NAMING_EXCEPTION = "Login Failed. Naming Exception.";
    
    public static final String PAGE_PRODUCTION = "/production.jsp";
    public static final String PAGE_CUTOVER = "/cutover.jsp";
    public static final String PAGE_END2END = "/end2end.jsp";
    public static final String PAGE_TREND = "/trend.jsp";
    public static final String PAGE_WORKFLOW = "/workflow.jsp";
    public static final String PAGE_LOGIN = "/login.jsp";
    public static final String PAGE_REPORT = "/report.jsp";
    
    public static final String CMD_PRODUCTION = "p";
    public static final String CMD_TREND = "t";
    public static final String CMD_WORKFLOW = "w";
    public static final String CMD_CUTOVER = "c";
    public static final String CMD_END2END = "e";
    public static final String CMD_LOGIN = "l";
    public static final String CMD_REPORT = "r";
    
    public static final String PARAM_ERR_MSG = "error_msg";
    public static final String PARAM_USERNAME = "username";
    public static final String PARAM_PASSWORD = "password";
    public static final String PARAM_REMEMBER = "remember";
    public static final String PARAM_DASHBOARD_USER = "dashboard_user";
    
    public static final String DATASOURCE_TXT_FOLDER = "C:/Users/PBRCXP/Dropbox/Apps/PBRC_DASHBOARD/";
    public static final String N2N_PMI_FILE_WRITER = "end2end_pmi.txt";
    public static final String N2N_OPAS_FILE_WRITER = "end2end_opas.txt";
    public static final String N2N_GCRS_FILE_WRITER = "end2end_gcrs.txt";
    public static final String PMI_MEASURE_FILE_WRITER = "pmi_measure.txt";
    public static final String WKF_DAYEND_FILE_WRITER = "wkf_dayend.txt";
    public static final String WKF_DAYTIME_FILE_WRITER = "wkf_daytime.txt";
    
}
