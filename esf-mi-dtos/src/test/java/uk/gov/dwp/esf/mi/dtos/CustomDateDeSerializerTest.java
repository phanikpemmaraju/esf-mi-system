package uk.gov.dwp.esf.mi.dtos;

import static org.junit.Assert.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @Author Phani Krishna
*/

@RunWith(MockitoJUnitRunner.class)
public class CustomDateDeSerializerTest {
	
	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	private CustomDateDeSerializer deserializer;
	
	@Mock
	private DeserializationContext ctxt;
	
	@Mock
	private JsonParser parser;
	
	@Before
	public void setUp(){
		deserializer = new CustomDateDeSerializer();
	}
	
		
	@Test
	public void deserializeTest() throws IOException, ParseException{
		final String jsonDate = "1981-12-16";
		Mockito.when(parser.getText()).thenReturn(jsonDate);
		final Date date = deserializer.deserialize(parser, ctxt);
		assertEquals(formatter.parse(jsonDate),date);
	}

	@SuppressWarnings("unchecked")
	@Test(expected=ParseException.class)
	public void deserializeParseExceptionTest() throws IOException, ParseException{
		Mockito.when(parser.getText()).thenThrow(ParseException.class);
		deserializer.deserialize(parser, ctxt);
	}
	
	public void deserializeRuntimeExceptionTest() throws IOException, ParseException{
		final String jsonDate = "esf-mi";
		Mockito.when(parser.getText()).thenReturn(jsonDate);
		assertNull(deserializer.deserialize(parser, ctxt));
	}
}
