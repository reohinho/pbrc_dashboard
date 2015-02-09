package hk.org.ha.pbrc.dashboard.bean;

import java.util.ArrayList;

public class SeriesBean {
    private String name;
    private ArrayList seriesData;
    
    public SeriesBean() {
        super();
        this.seriesData = new ArrayList();
    }
    public SeriesBean(String name) {
        super();
        this.name = name;
        this.seriesData = new ArrayList();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSeriesData(ArrayList seriesData) {
        this.seriesData = seriesData;
    }

    public ArrayList getSeriesData() {
        return seriesData;
    }
    
    public void addValue(int value) {
        this.seriesData.add(value);
    }
    
    public String toString() {
        String result;
        result = "[";
        for(int i=0; i<seriesData.size(); i++) {
            result += (Integer)seriesData.get(i);                
            if (i<seriesData.size()-1) { result +=", "; }
        }
        result += "]";
        System.out.println(result);
        return result;
    }
}
