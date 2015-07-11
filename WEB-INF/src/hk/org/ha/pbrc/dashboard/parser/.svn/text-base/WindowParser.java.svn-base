package hk.org.ha.pbrc.dashboard.parser;

import hk.org.ha.pbrc.dashboard.bean.UtilizationSampleBean;

public class WindowParser {
    public UtilizationSampleBean parse(int numOfSample, String inStr) {
    
        String[] str = inStr.split(",");
        UtilizationSampleBean sample = new UtilizationSampleBean();

        sample.setTimestamp(new Integer(numOfSample/60).toString());

        sample.setUserUsage(str[9].replace("\"", ""));
        sample.setSystemUsage(str[8].replace("\"", ""));    

        return sample;      
    }
}

