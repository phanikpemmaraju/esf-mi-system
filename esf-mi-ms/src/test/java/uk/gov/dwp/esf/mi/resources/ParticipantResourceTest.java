package uk.gov.dwp.esf.mi.resources;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import uk.gov.dwp.esf.mi.common.RecordState;
import uk.gov.dwp.esf.mi.dtos.ParticipantDTO;
import uk.gov.dwp.esf.mi.model.Participant;

/**
 * @Author Phani Krishna
*/

public class ParticipantResourceTest {
	
	private SimpleDateFormat dateFormat;
	
	@Before
	public void setup(){
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	}
	
	@Test
	public void resourceTest() throws ParseException{
		ParticipantResource resource = new ParticipantResource();		
		assertNotNull(resource);
		resource.setParticipant(null);
		assertNull(resource.getParticipant());
		
		ParticipantDTO participant = new ParticipantDTO();
		participant.setParticipantId("56dffaa6097d9818d8b455a7");participant.setProviderId(1111111);
		participant.setContractId(4001115);participant.setNino("SJ1960589B");participant.setDob(dateFormat.parse("1982-12-16"));
		participant.setCreationDate(dateFormat.parse(dateFormat.format(new Date())));participant.setUpdatedDate(dateFormat.parse(dateFormat.format(new Date())));participant.setRecordState(RecordState.CREATED);
		participant.setExOffender(false);
		resource = new ParticipantResource(participant);
		assertNotNull(resource);
		
		assertTrue(resource.toString().contains("56dffaa6097d9818d8b455a7"));
		assertFalse(resource.toString().contains("SJ1960589B1"));		
		assertEquals(resource.hashCode(),-933511780);
		
		Participant other = new Participant();
		other.setContractId(4001115);other.setProviderId(1111111);other.setNino("SJ1960589B");		
		assertTrue(resource.equals(other));
		
		other.setContractId(4001116);
		assertFalse(resource.equals(other));
		other.setContractId(4001115);other.setProviderId(1111112);
		assertFalse(resource.equals(other));
		other.setProviderId(1111111);;other.setNino("SJ1960590");
		assertFalse(resource.equals(other));
		
	}

}