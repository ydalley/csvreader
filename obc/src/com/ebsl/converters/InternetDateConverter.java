package com.ebsl.converters;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import com.opensymphony.xwork2.conversion.TypeConversionException;

public class InternetDateConverter extends StrutsTypeConverter {
    // WARNING not safe in multi-threaded environments
    private static final DateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public Object convertFromString(Map context, String[] strings, Class toClass) {     
        if (strings == null || strings.length == 0 || strings[0].trim().length() == 0) {
            return null;
        }

        try {
            return DATETIME_FORMAT.parse(strings[0]);
        } catch (ParseException e) {
            throw new TypeConversionException("Unable to convert given object to date: " + strings[0]);
        }
    }

    public String convertToString(Map context, Object date) {
        if (date != null && date instanceof Date) {         
            return DATETIME_FORMAT.format(date);
        } else {
            return null;
        }
    }
}