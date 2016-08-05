package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Chaojier on 2016/8/2 15:42.
 */
public class StringUtil {

    public static Date strToDate(String strDate) {
        Date date = null;
        if (ck_str(strDate)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                date = sdf.parse(strDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date;
    }

    public static String dateToStr(Date date) {
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.format(date);
        }
        return "";
    }

    public static Integer strToInteger(String valueStr) {
        if (ck_str(valueStr)) {
            if (isNumeric(valueStr)) {
                return Integer.valueOf(valueStr);
            }
        }
        return 0;
    }

    public static boolean ck_str(String str) {
        if (str != null && !str.equals("")) {
            return true;
        }
        return false;
    }

    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }
}
