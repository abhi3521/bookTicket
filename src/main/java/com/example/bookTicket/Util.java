package com.example.bookTicket;

import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
@Getter
public class Util {
    public static Date formatDateTime(String dateTime) {
        String formatPattern = "dd/MM/yyyy HH:mm:ss";
        SimpleDateFormat inputDateFormat = new SimpleDateFormat(formatPattern);
        try {
            // Parse the string to obtain a Date object
            Date date = inputDateFormat.parse(dateTime);
            return date;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
