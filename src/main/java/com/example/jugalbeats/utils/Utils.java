package com.example.jugalbeats.utils;

import javax.servlet.http.HttpServletRequest;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;

import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DateFormat;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.regex.Pattern;

public class Utils {

    public static String getStringValue(Object s) {
        try {
            return s != null ? s.toString() : "";
        } catch (Exception e) {
            return "";
        }
    }
    public static String getStringValueOrEmptyArrayString(Object s) {
        return s != null ? s.toString() : "[]";
    }

    public static String getStringValueOrEmptyMapString(Object s) {
        return s != null ? s.toString() : "{}";
    }

    public static String getStringValueOrDefault(Object s, String defaultString) {
        return s != null ? s.toString() : defaultString;
    }

    public static Long getLongValue(Object s) {
        try {
            return s != null ? (long) Double.parseDouble(s.toString()) : new Long(0);
        } catch (NumberFormatException e) {
            return new Long(0);
        }
    }

    public static Integer getIntegerValue(Object s) {
        try {
            return s != null ? (int) Double.parseDouble(s.toString()) : 0;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static Double getDoubleValue(Object s) {
        try {
            return s != null ? Double.parseDouble(s.toString()) : new Double(0);
        } catch (NumberFormatException e) {
            return new Double(0);
        }

    }

    public static BigDecimal getBigDecimalValue(Object s) {
        try {
            return s != null ? new BigDecimal(s.toString()) : new BigDecimal("0");
        } catch (NumberFormatException e) {
            return new BigDecimal("0");
        }
    }

    public static Boolean getBooleanValue(Object s) {
        return s != null && s.toString().toLowerCase().matches("^(true)|(false)$") ? Boolean.parseBoolean(s.toString())
                : false;
    }

    public static final String generateUUIDTransanctionId() {
        return UUID.randomUUID().toString();
    }

    public static final ZonedDateTime getCurrentDateTime() {
        return ZonedDateTime.now(ZoneOffset.UTC);
    }

    public static final String getCurrentDateInFormat(String format) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String strDate = formatter.format(date);
        return strDate;
    }

    public static final XMLGregorianCalendar getCurrentDateTimeInGregorianCalendar()
            throws DatatypeConfigurationException {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTimeInMillis(System.currentTimeMillis());

        XMLGregorianCalendar xmlGrogerianCalendar = DatatypeFactory.newInstance()
                .newXMLGregorianCalendar(gregorianCalendar);
        xmlGrogerianCalendar.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
        return xmlGrogerianCalendar;

    }

    public static String formatDate(String date, String initDateFormat, String endDateFormat) throws ParseException {

        Date initDate = new SimpleDateFormat(initDateFormat).parse(date);
        SimpleDateFormat formatter = new SimpleDateFormat(endDateFormat);
        String parsedDate = formatter.format(initDate);

        return parsedDate;
    }

    public static String getDateInString(Date date, String dateFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        String parsedDate = formatter.format(date);
        return parsedDate;
    }

    public static Boolean isDateInFormat(String date, String initDateFormat) {

        try {
            new SimpleDateFormat(initDateFormat).parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static String getStackTrace(Throwable aThrowable) {
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);
        aThrowable.printStackTrace(printWriter);
        return result.toString();
    }

    public static boolean isValidDate(String inDate, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
            return true;
        } catch (ParseException pe) {
            return false;
        }
    }

    public static Date addDaysToDate(Date inputDate, int numberOfDays) {
        Calendar c = Calendar.getInstance();
        c.setTime(inputDate);
        c.add(Calendar.DATE, numberOfDays);
        return c.getTime();
    }

    public static Date addMonthsToDate(Date inputDate, int numberOfMonths) {
        Calendar c = Calendar.getInstance();
        c.setTime(inputDate);
        c.add(Calendar.MONTH, numberOfMonths);
        return c.getTime();
    }

    public static Date subtractDaysToDate(Date inputDate, int numberOfDays) {
        Calendar c = Calendar.getInstance();
        c.setTime(inputDate);
        c.add(Calendar.DATE, -numberOfDays);
        return c.getTime();
    }

    public static Date addYearsToDate(Date inputDate, int numberOfYears) {
        Calendar c = Calendar.getInstance();
        c.setTime(inputDate);
        c.add(Calendar.YEAR, numberOfYears);
        return c.getTime();
    }

    public static Date getDateFromEpochTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }

    public static int getMonthsDiffBetweenDates(Date startdate, Date endDate) {
        Calendar startdateCalendar = Calendar.getInstance();
        startdateCalendar.setTime(startdate);
        Calendar endDateCalendar = Calendar.getInstance();
        endDateCalendar.setTime(endDate);
        int diffYear = endDateCalendar.get(Calendar.YEAR) - startdateCalendar.get(Calendar.YEAR);
        int diffMonth = diffYear * 12 + endDateCalendar.get(Calendar.MONTH) - startdateCalendar.get(Calendar.MONTH);
        return diffMonth;
    }

    public static int getMonthsDiffBetweenDatesDDMMYYYY(String startDate, String endDate) {
        StringBuilder formattedStartDate = new StringBuilder();
        StringBuilder formattedLastDate = new StringBuilder();
        try {
            if (startDate.length() == 8) {
                formattedStartDate = formattedStartDate.append(
                        startDate.substring(4, 8) + "-" + startDate.substring(2, 4) + "-" + startDate.substring(0, 2));
            } else {
                formattedStartDate = formattedStartDate.append(startDate);
            }
            if (endDate.length() == 8) {
                formattedLastDate = formattedLastDate.append(
                        endDate.substring(4, 8) + "-" + endDate.substring(2, 4) + "-" + endDate.substring(0, 2));
            } else {
                formattedLastDate = formattedLastDate.append(endDate);
            }
            int monthsDiff = Period.between(LocalDate.parse(formattedStartDate), LocalDate.parse(formattedLastDate))
                    .getMonths();
            return monthsDiff;
        } catch (Exception e) {
            return 0;
        }
    }

    public static String getSubStringValue(String s, int n) {
        byte[] sArray = s.getBytes();
        if (sArray.length <= n)
            return s;
        else {
            byte[] slice = Arrays.copyOfRange(sArray, 0, n);
            s = new String(slice);
            if (s.length() > 0 && !Character.isLetter(s.charAt(s.length() - 1))) {
                return s.substring(0, s.length() - 1);
            }
            return s;
        }
    }

    public static String dobformatCorrection(String dob) {
        if (dob.length() == 4) {
            dob = "01-01-" + dob;
        } else {
            try {
                if (dob.split("-")[0].length() == 4) {
                    dob = formatDate(dob, "yyyy-MM-dd", "dd-MM-yyyy");
                }
            } catch (Exception e) {
            }
        }
        return dob;
    }

    private static String arrayToStringWithSpace(String[] words) {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < words.length; j++) {
            if (!words[j].equals("")) {
                sb.append(words[j]);
                if (j < words.length - 1) {
                    sb.append(" ");
                }
            }
        }
        return sb.toString();
    }
    
    public static List<String> stringtoArray(String words) {
    	String[] arr=words.split(",");
    	List<String> array=new ArrayList<>();
    	for(int i=0;i<arr.length;i++) {
    		array.add(arr[i]);
    	}

        return array;
    }
    public static List<String> stringtoArrayOnly(String words) {
    	 
    	words=StringUtils.substringBetween(words, "[", "]");
    	String[] arr=words.split(",");
    	List<String> array=new ArrayList<>();
    	for(int i=0;i<arr.length;i++) {
    		array.add(arr[i]);
    	}

        return array;
    }
    
    public static PageRequest generatePageRequest(HttpServletRequest request) {
    	int index=0;
    	int maxItemPerPage=10;
    	String page=request.getParameter("pageIndex");
    	String itemPerPage=request.getParameter("itemPerPage");
    	String sortByColumnName= request.getParameter("sortBy");
    	String sortOrder =request.getParameter("sortOrder");
    	
    	if(StringUtils.isNotBlank(page) && StringUtils.isNumeric(page) ) {
    		index=Integer.parseInt(page);
    	}
    	if(StringUtils.isNotBlank(itemPerPage) && StringUtils.isNumeric(itemPerPage) ) {
    		maxItemPerPage=Integer.parseInt(itemPerPage);
    	}
    	PageRequest pageRequest=null;
    	if(StringUtils.isNotBlank(sortByColumnName) && StringUtils.isNotBlank(sortOrder)) {
    		Direction direction="ASC".equalsIgnoreCase(sortOrder)?Direction.ASC:Direction.DESC;
    		pageRequest=PageRequest.of(index, maxItemPerPage, direction, sortByColumnName);
    	}
    	else {
    		pageRequest=PageRequest.of(index, maxItemPerPage);
    	}
    	return pageRequest;
    	
    }

}
