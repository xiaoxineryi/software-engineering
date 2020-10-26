package org.cn.kaito.auth.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateStringUtil {
    private DateStringUtil(){}
    public static String Date2String(Date date){
        SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String time = sformat.format(date);
        return time;
    }
    public static Date String2Date(String date){
        SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date d  = null;
        try {
            d = sformat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }
}
