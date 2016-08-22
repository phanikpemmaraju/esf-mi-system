package uk.gov.dwp.esf.mi.utils;

import static org.junit.Assert.*;

//import java.text.ParseException;
//import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

/**
 * @Author Phani Krishna
*/

public class UtilsTest {
	
	//private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private Utils utils;
	
	@Before
	public void setUp(){
		utils = new Utils();
	}
		
	@Test
	public void testConvertCISDate(){
		String cisDate = "25-JAN-2015";
		Date date =  utils.convertCISDate(cisDate);
		assertNotNull(date);
		assertEquals(date.toString(),"Sun Jan 25 00:00:00 GMT 2015");
		
		cisDate = "25-10-2015";
		assertNull(utils.convertCISDate(cisDate));				
	}
	
	
	/*@Test
	public void testfindAge(){
		String cisDate = "15-DEC-2015";
		Date dob = null;
		try {
			dob = dateFormat.parse("1982-12-16");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
		}
		
		assertEquals(32,utils.findAge(cisDate, dob));
		// Parse Exception
		cisDate = "15-10-2015";
		assertEquals(-1,utils.findAge(cisDate, dob));
		
		//Start date before dob
		cisDate = "01-12-1982";
		assertEquals(-1,utils.findAge(cisDate, dob));
	}*/

}
