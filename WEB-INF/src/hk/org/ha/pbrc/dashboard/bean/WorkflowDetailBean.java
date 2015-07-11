package hk.org.ha.pbrc.dashboard.bean;

public class WorkflowDetailBean {
    public WorkflowDetailBean() {
        super();
    }
    
    String queueId;
    String agentName;
    String originCode;
    String eventDtm;
    int priority;
    int rowCount;
    String hostName;
    String agentStatus;
    
    
    String minEventDtmStr;
    String maxEventDtmStr;


    public void setQueueId(String queueId) {
        this.queueId = queueId;
    }

    public String getQueueId() {
        return queueId;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setOriginCode(String originCode) {
        this.originCode = originCode;
    }

    public String getOriginCode() {
        return originCode;
    }

    public void setEventDtm(String eventDtm) {
        this.eventDtm = eventDtm;
    }

    public String getEventDtm() {
        return eventDtm;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setMinEventDtmStr(String minEventDtmStr) {
        this.minEventDtmStr = minEventDtmStr;
    }

    public String getMinEventDtmStr() {
        return minEventDtmStr;
    }

    public void setMaxEventDtmStr(String maxEventDtmStr) {
        this.maxEventDtmStr = maxEventDtmStr;
    }

    public String getMaxEventDtmStr() {
        return maxEventDtmStr;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getHostName() {
        return hostName;
    }

    public void setAgentStatus(String agentStatus) {
        this.agentStatus = agentStatus;
    }

    public String getAgentStatus() {
        return agentStatus;
    }
}
