package utils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Chaojier on 2016/8/2 15:51.
 */
public class DateUtil {

    public static Date addTime(Date date, int calendarType,int value){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(calendarType,value);
        date =cal.getTime();
        return date;
    }
}
