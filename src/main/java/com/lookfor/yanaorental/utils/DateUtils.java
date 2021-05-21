package com.lookfor.yanaorental.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static Date getNow() {
        Instant now = Instant.now();
        return Date.from(now);
    }

    public static Date calculateExpiration(int calendarType, int expiration) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(calendarType, expiration);
        return cal.getTime();
    }

    public static Date calculateExpiration(long expiration) {
        return new Date(System.currentTimeMillis() + expiration);
    }

    public static String getTimeStringFromDate(Date date){
        return new SimpleDateFormat("H:mm").format(date);
    }
}
