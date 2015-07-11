package hk.org.ha.pbrc.dashboard.servlet;

import hk.org.ha.pbrc.dashboard.bean.OutStandingBalanceBean;
import hk.org.ha.pbrc.dashboard.bean.WorkflowSampleBean;
import hk.org.ha.pbrc.dashboard.dao.OutstandingBalanceDao;

import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;


public class OutStandingBalanceTrendServlet extends MeasurementServlet {

    protected Vector getResult(HttpServletRequest request) {

        Vector result = new Vector();
        OutstandingBalanceDao dao = new OutstandingBalanceDao();
        result.add(dao.getTrendBySubType("OS_BAL_MAX", -1));
        result.add(dao.getTrendBySubType("OS_BAL_MAX", 0));

        return result;
    }

    protected String toString(Vector vec) {

        List<OutStandingBalanceBean> list =
            (List<OutStandingBalanceBean>)vec.get(0);
        List<OutStandingBalanceBean> list2 =
            (List<OutStandingBalanceBean>)vec.get(1);

        String jsonString =
            "{ \"cols\": [{\"id\":\"\",\"label\":\"time\",\"type\":\"string\"}," +
            "{\"id\":\"\",\"label\":\"o/s (yesterday)\",\"type\":\"number\"}," +
            "{\"id\":\"\",\"label\":\"o/s\",\"type\":\"number\"}" +
            "],\"rows\":[";
        for (int i = 0; i < list.size(); i++) {
            OutStandingBalanceBean osb = list.get(i);
            
            if(i<list2.size()) {
                OutStandingBalanceBean osb2 = list2.get(i);
                jsonString += "{\"c\":[{\"v\":\"" + osb.getTimeString() + "\"},{\"v\":" + osb.getValue() + "},{\"v\":" + osb2.getValue() + "}]} ";            
            }
            else{
                jsonString += "{\"c\":[{\"v\":\"" + osb.getTimeString() + "\"},{\"v\":" + osb.getValue() + "}]} ";                
            }

            if (i < list.size()) {
                jsonString += ",";
            }
        }
        jsonString += "]}";

        return jsonString;
    }

    protected Vector getResultCR(HttpServletRequest request) {
        Vector result = new Vector();
        return result;        
    } 
}
