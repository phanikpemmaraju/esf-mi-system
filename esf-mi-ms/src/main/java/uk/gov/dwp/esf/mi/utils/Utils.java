package uk.gov.dwp.esf.mi.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.stereotype.Component;

/**
 * @Author Phani Krishna
*/

@Component
public class Utils {
		
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat cisDateFormat = new SimpleDateFormat("dd-MMM-yyyy");

		
	/*
	 * Converts the CIS String formatted date to Java Date.
	 * 
	 */
	public Date convertCISDate(final String date) {
		Date cisDate = null;
		try {
			cisDate = dateFormat.parse(dateFormat.format(cisDateFormat.parse(date)));			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
		}
		return cisDate;				
	}

}
