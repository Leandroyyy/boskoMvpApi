package br.com.bosko.boskoapi.service.parse;

import java.util.Date;

public class ParseDate {

    public static Long dateToTimeStamp(Date date){
        Long timeStamp = date.getTime() / 1000;
        return timeStamp;
    }

    public static Date timeStampToDate(Long timestamp){
        Date dateTime = new Date(timestamp * 1000);
        return dateTime;
    }
}