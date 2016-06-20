package com.cq.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by admin on 2016/6/3.
 */
public class TransDateTime {
    public static final String STR_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     *
     */
    public TransDateTime() {
    }

    public static String transDateTime(Date date){
        SimpleDateFormat time = new SimpleDateFormat(STR_FORMAT);
        return time.format(date);
    }
    public static Date TransDateTime(Date date) throws ParseException {
        DateFormat df = new SimpleDateFormat(STR_FORMAT);
        return  df.parse( transDateTime(date));
    }

    public static void main(String[] args) throws ParseException {
        System.out.println(TransDateTime(new Date()));
    }
}
