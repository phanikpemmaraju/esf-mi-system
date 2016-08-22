package uk.gov.dwp.esf.mi.exceptionhandlers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import uk.gov.dwp.esf.mi.common.ErrorMessage;
import uk.gov.dwp.esf.mi.exceptions.DataException;

/**
 * @Author Phani Krishna
*/

public class ExceptionMapperTest {
	
	private ExceptionMapper exceptionMapper;
	
	@Before
	public void setUp(){
		exceptionMapper = new ExceptionMapper();
	}
	
	@Test
	public void testGetException(){
		final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		final DataException exception = new DataException("error.nino.not.found","Nino not found");
		assertNotNull(exceptionMapper.getException(exception, httpStatus));
	}
	
	@Test
	public void testGetExceptionforFieldError(){
		final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		final FieldError fieldError = new FieldError("Nino validation error", "Nino not found","error.nino.not.found");
		final ErrorMessage error = exceptionMapper.getException(fieldError, httpStatus);
		assertNotNull(error);
		assertTrue(error.getCode().equals("error.nino.not.found"));
		assertTrue(error.getMessage().equals("Field Error on field Nino not found"));
		assertTrue(error.getDescription().equals("Bad Request"));
	}
	
	@Test
	public void testMapErrorForException(){
		final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		final DataException exception = new DataException("error.nino.not.found","Nino not found");
		final ErrorMessage error = exceptionMapper.getException(exception, httpStatus);
		assertNotNull(error);
		assertTrue(error.getCode().equals("error.nino.not.found"));
		assertTrue(error.getMessage().equals("Nino not found"));
		assertTrue(error.getDescription().equals("Bad Request"));
	}
	
	@Test
	public void testMapErrorForFieldError(){
		final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		final FieldError fieldError = new FieldError("Nino validation error", "Nino not found","error.nino.not.found");
		final ErrorMessage error = exceptionMapper.getException(fieldError, httpStatus);
		assertNotNull(error);
		assertTrue(error.getCode().equals("error.nino.not.found"));
		assertTrue(error.getMessage().equals("Field Error on field Nino not found"));
		assertTrue(error.getDescription().equals("Bad Request"));
	}

}
