package com.enoca.challenge.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMM", Locale.ENGLISH);

    public static Long convertStringDateToLong(String date) throws ParseException {
        Date d = dateFormat.parse(date);
        return  d.getTime();
    }
}
