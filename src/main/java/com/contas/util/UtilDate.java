package com.contas.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class UtilDate {
	
	private static ZoneId defaultZoneId = ZoneId.systemDefault();
	
	public static LocalDate convertDateToLocalDate(java.util.Date date) {
        if (date == null) 				   return null;
        if (date instanceof java.sql.Date) return ((java.sql.Date) date).toLocalDate();
        else return Instant.ofEpochMilli(date.getTime()).atZone(defaultZoneId).toLocalDate();
    }

}
