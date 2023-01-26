package com.aizquierdo.rallyslot.core.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static final String TIMESTAMP_FORMAT = "yyyy-MM-dd hh:mm:ss.S";

    private DateUtils(){
    };

    public static String dateToString(Date date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    public static String dateToTimestampJson(Date date) {
        return dateToString(date, TIMESTAMP_FORMAT);
    }
}
