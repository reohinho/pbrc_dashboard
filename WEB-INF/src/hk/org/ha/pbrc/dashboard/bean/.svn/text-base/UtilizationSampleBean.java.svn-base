package hk.org.ha.pbrc.dashboard.bean;

public class UtilizationSampleBean {
    
    String timestamp = "";
    String systemUsage = "";
    String UserUsage = "";
    
    public UtilizationSampleBean() {
        super();
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setSystemUsage(String systemUsage) {
        if(systemUsage==null||systemUsage.equals("")) {
            this.systemUsage = "0";
        }
        else {
            this.systemUsage = systemUsage;
        }
    }

    public String getSystemUsage() {
        return systemUsage;
    }

    public void setUserUsage(String UserUsage) {
        if(UserUsage==null||UserUsage.equals("")) {
            this.UserUsage = "0";
        }
        else {            
            this.UserUsage = UserUsage;
        }
    }

    public String getUserUsage() {
        return UserUsage;
    }
}

