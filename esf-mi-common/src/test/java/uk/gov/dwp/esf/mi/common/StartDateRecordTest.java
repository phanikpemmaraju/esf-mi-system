package uk.gov.dwp.esf.mi.common;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

/**
 * @Author Phani Krishna
*/

public class StartDateRecordTest {
	
	final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	@Test
	public void shouldReturnNullStartDateRecord() {
		final StartDateRecord startDateRecord = new StartDateRecord(null,null,null);
		assertNotNull(startDateRecord);
		assertThat(startDateRecord.getNino(),is(nullValue()));
		assertThat(startDateRecord.getContractId(),is(nullValue()));
		assertThat(startDateRecord.getStartDate(),is(nullValue()));		
		assertThat(startDateRecord.getStatus(),is(nullValue()));		
	}
	
	@Test
	public void shouldReturnStartDateRecord() throws ParseException {
		final StartDateRecord startDateRecord = new StartDateRecord("SL123457B",4001114,dateFormat.parse(dateFormat.format(new Date())));
		assertNotNull(startDateRecord);
		assertThat(startDateRecord.getNino(),is("SL123457B"));
		assertThat(startDateRecord.getContractId(),is(4001114));
		assertThat(startDateRecord.getStartDate(),is(dateFormat.parse(dateFormat.format(new Date()))));	
		assertThat(startDateRecord.getStatus(),is(nullValue()));
		startDateRecord.setStatus("Success");
		assertThat(startDateRecord.getStatus(),is("Success"));
	}
	
	@Test
	public void shouldReturnStartDateRecordDefaultConstructor() throws ParseException {
		final StartDateRecord startDateRecord = new StartDateRecord();
		assertNotNull(startDateRecord);
		startDateRecord.setNino("SL123457B");startDateRecord.setContractId(Integer.parseInt("4001114"));
		startDateRecord.setStartDate(dateFormat.parse(dateFormat.format(new Date())));startDateRecord.setStatus("Success");
		assertThat(startDateRecord.getNino(),is("SL123457B"));
		assertThat(startDateRecord.getContractId(),is(4001114));
		assertThat(startDateRecord.getStartDate(),is(dateFormat.parse(dateFormat.format(new Date()))));	
		assertThat(startDateRecord.getStatus(),is("Success"));
	}
	
}
