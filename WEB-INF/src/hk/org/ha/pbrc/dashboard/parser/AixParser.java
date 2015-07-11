package hk.org.ha.pbrc.dashboard.parser;

import hk.org.ha.pbrc.dashboard.bean.UtilizationSampleBean;

public class AixParser {
    public UtilizationSampleBean parse(String inStr) {
        //String inStr = "0000  1  1 23256239 47460   0   0   0   0    0   0 1520 34231 6303 12  3 84  1";
      
        String[] str = inStr.split("\\s+");
        UtilizationSampleBean sample = new UtilizationSampleBean();
        sample.setTimestamp(str[0]);
        sample.setUserUsage(str[14]);
        sample.setSystemUsage(str[15]);    

        return sample;      
    }
}
