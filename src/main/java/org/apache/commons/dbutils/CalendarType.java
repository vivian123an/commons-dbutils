package org.apache.commons.dbutils;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class CalendarType implements SpecialTypeHandle<Calendar> {

	@Override
	public Class<Calendar> getClassSimpleName() {
		return Calendar.class;
	}
	
	@Override
	public Calendar getResultObject(Class<?> eClass, Object value) {
		Calendar calendar = Calendar.getInstance();
		if (eClass.getName().equals("java.sql.Timestamp")) {
			Timestamp timeStamp = (Timestamp) value;
			calendar.setTimeInMillis(timeStamp.getTime());
		} else if (eClass.getName().equals("java.sql.Date")) {
			Date dateValue = (Date) value;
			calendar.setTime(dateValue);
		}
		return calendar;
	}

}