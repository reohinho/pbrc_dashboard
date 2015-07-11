package hk.org.ha.pbrc.dashboard.bean;

import java.sql.Timestamp;

public class OutStandingBalanceBean {
    public OutStandingBalanceBean() {
        super();
    }
    
    private Timestamp  lastUpdDtm;
    private int value;
    private String timeString;


    public void setTimeString(String timeString) {
        this.timeString = timeString;
    }

    public String getTimeString() {
        return timeString;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setLastUpdDtm(Timestamp lastUpdDtm) {
        this.lastUpdDtm = lastUpdDtm;
    }

    public Timestamp getLastUpdDtm() {
        return lastUpdDtm;
    }

    public long getMillisecond() {
        if(lastUpdDtm!=null){
            return lastUpdDtm.getTime();    
        }
        else{
            return 0;
        }            
    }
}
