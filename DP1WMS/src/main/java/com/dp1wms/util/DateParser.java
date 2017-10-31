package com.dp1wms.util;

import javafx.util.StringConverter;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateParser {

    private static  String PATTERN = "dd/MM/yyyy";

    private static SimpleDateFormat format = new SimpleDateFormat(PATTERN);

    private static StringConverter<LocalDate> stringConverter = new StringConverter<LocalDate>() {
        private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(PATTERN);
        @Override
        public String toString(LocalDate localDate) {

            if(localDate==null)
                return "";
            return dateTimeFormatter.format(localDate);
        }

        @Override
        public LocalDate fromString(String dateString) {
            if(dateString==null || dateString.trim().isEmpty())
            {
                return null;
            }
            return LocalDate.parse(dateString,dateTimeFormatter);
        }
    };

    private DateParser(){
    }

    public static StringConverter<LocalDate> getConverter(){
        return stringConverter;
    }

    public static Timestamp stringToTimestamp(String fecha){
        try{
            Date date = format.parse(fecha);
            return new Timestamp(date.getTime());
        } catch (Exception e){
            return null;
        }
    };

    public static String timestampToString(Timestamp time){
        Date date = new Date();
        date.setTime(time.getTime());
        return format.format(date);
    }

    public static Timestamp localdateToTimestamp(LocalDate localDate){
        String fecha = stringConverter.toString(localDate);
        return stringToTimestamp(fecha);
    }

    public static LocalDate timestampToLocaldate(Timestamp time){
        String timeStr = timestampToString(time);
        return stringConverter.fromString(timeStr);
    }

    public static String currentDateStr(){
        try{
            Date date = new Date();
            return format.format(date);
        }catch (Exception e){
            return "";
        }
    }
    public static LocalDate currentDateLocalDate(){
        String current = currentDateStr();
        return stringConverter.fromString(current);
    }

    public Timestamp currentDateTimestamp(){
        return new Timestamp((new Date()).getTime());
    }

    public static long currentDateLong(){
        return (new Date()).getTime();
    }
}
