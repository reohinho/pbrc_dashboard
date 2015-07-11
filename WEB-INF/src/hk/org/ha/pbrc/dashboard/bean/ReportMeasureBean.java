package hk.org.ha.pbrc.dashboard.bean;

public class ReportMeasureBean extends SampleBean{
    public ReportMeasureBean() {
        super();
    }

    String dateStr;
    String statusStr;
    int noOfRow;

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public String getStatusStr() {
        String result = "";
        if(this.statusStr!=null){
            result = this.statusStr;
        }
        return result;
    }

    public void setNoOfRow(int noOfRow) {
        this.noOfRow = noOfRow;
    }

    public int getNoOfRow() {
        return noOfRow;
    }
    
    public String getNoOfRowStr() {
        String result = "";
        if (getNoOfRow()>0) {
            result = Integer.toString(getNoOfRow());
        }
        return result;            
    }
}
