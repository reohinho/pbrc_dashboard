package hk.org.ha.pbrc.dashboard.utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Calendar;

public class DateUtilities {
    public DateUtilities() {
        super();
    }
    
    public static String getCurrentDate(String dateFormatStr){        
        DateFormat dateFormat = new SimpleDateFormat(dateFormatStr);
        Calendar cal = Calendar.getInstance();
        return(dateFormat.format(cal.getTime()));       
    }
    
    public static String getDate(String dateFormatStr, int date){
        DateFormat dateFormat = new SimpleDateFormat(dateFormatStr);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, date);
        return(dateFormat.format(cal.getTime()));      
    }
    
    public static String getDate(int date, int hour) {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, date);
        cal.add(Calendar.HOUR_OF_DAY, hour);
        return(dateFormat.format(cal.getTime()));      
    }
    
    public static String minuteToDateFormat(String dateFormatStr, int minuteOfDay) {
        DateFormat dateFormat = new SimpleDateFormat(dateFormatStr);
        Calendar cal = Calendar.getInstance();        
        int hourOfDay = minuteOfDay/60;
        int minuteOfHour = minuteOfDay%60;
        cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
        cal.set(Calendar.MINUTE, minuteOfHour);
        return(dateFormat.format(cal.getTime()));        
    }
}
