package uk.gov.dwp.esf.mi.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;

import uk.gov.dwp.esf.mi.common.RecordState;
import uk.gov.dwp.esf.mi.common.StartDateRecord;
import uk.gov.dwp.esf.mi.model.Participant;
import uk.gov.dwp.esf.mi.repositories.ParticipantRepository;

/**
 * @Author Phani Krishna
*/

@RunWith(MockitoJUnitRunner.class)
public class StartDateUpdateServiceTest {
	
	private SimpleDateFormat dateFormat;
	
	@Mock
	private ParticipantRepository participantRepository;
	
	@InjectMocks
	private StartDateUpdateService startDateService;
	
	@Before
	public void setUp(){
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	}
	
	/*
     * Test to Update the Participant details with start date, updated date , proposed Exit date and record state
	 * 
	 */ 
	@Test
	public void testProcessParticipant() throws Exception{
		String[] strings = {"SL123457B","4001114","2016-05-10"};
		List<Participant> participants = new ArrayList<>();
		when(participantRepository.findByContractIdAndNino(Integer.valueOf("4001114"), "SL123457B")).thenReturn(participants);
		StartDateRecord startDateRecord = startDateService.processParticipant(strings);
		assertThat(startDateRecord.getStatus(),is(nullValue()));
		
		Participant persistentParticipant = new Participant.ParticipantBuilder("56b4694d7eb68a5fff76290c", 1111111, "SL123457B", dateFormat.parse(dateFormat.format(new Date())),4001114)
				.creationDate(dateFormat.parse("2016-04-22")).startDate(dateFormat.parse("2016-05-10")).updatedDate(dateFormat.parse(dateFormat.format(new Date()))).recordState(RecordState.CREATED).build();
    					
		
		participants.add(persistentParticipant);
		startDateRecord = startDateService.processParticipant(strings);
		assertThat(startDateRecord.getStatus(),is(nullValue())); participants.clear();
		
		persistentParticipant = new Participant.ParticipantBuilder("56b4694d7eb68a5fff76290c", 1111111, "SL123457B", dateFormat.parse(dateFormat.format(new Date())),4001114)
				.creationDate(dateFormat.parse("2016-04-22")).updatedDate(dateFormat.parse(dateFormat.format(new Date()))).recordState(RecordState.CREATED).build();
		
		final Participant updatedParticipant = new Participant.ParticipantBuilder("56b4694d7eb68a5fff76290c", 1111111, "SL123457B", dateFormat.parse(dateFormat.format(new Date())),4001114)
				.creationDate(dateFormat.parse("2016-04-22")).startDate(dateFormat.parse("2016-05-10")).updatedDate(dateFormat.parse(dateFormat.format(new Date()))).recordState(RecordState.STARTED).proposedExitDate(dateFormat.parse("2017-06-06"))
				.build();

		participants.add(persistentParticipant);
		startDateRecord = startDateService.processParticipant(strings);
		List<Participant> participantList = participantRepository.findByContractIdAndNino(Integer.valueOf("4001114"), "SL123457B");
		assertThat(startDateRecord.getStatus(),is("Success"));
		assertEquals(participantList.get(0).toString(),updatedParticipant.toString());		
		assertThat(participantList.get(0).getProposedExitDate(),is(dateFormat.parse("2017-06-06")));
		assertThat(participantList.get(0).getRecordState(),is(RecordState.STARTED));
		assertThat(participantList.get(0).getStartDate(),is(dateFormat.parse("2016-05-10")));
		
		persistentParticipant = new Participant.ParticipantBuilder("56b4694d7eb68a5fff76290c", 1111111, "SL123457B", dateFormat.parse(dateFormat.format(new Date())),4001114)
				.creationDate(dateFormat.parse("2016-04-22")).updatedDate(dateFormat.parse(dateFormat.format(new Date()))).startDate(dateFormat.parse(dateFormat.format(new Date()))).recordState(RecordState.CREATED).build();
		participants.clear(); participants.add(persistentParticipant);
		when(participantRepository.findByContractIdAndNino(Integer.valueOf("4001114"), "SL123457B")).thenReturn(participants);
		startDateRecord = startDateService.processParticipant(strings);
		assertNull(startDateRecord.getStatus());
	}
	
	
	@Test
	public void testUpdateParticipant() throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		final StartDateRecord startDateRecord = new StartDateRecord("SL123457B",4001114,format.parse("10-05-2016"));
		Participant participant = new Participant();
		startDateService.updateParticipant(participant, startDateRecord);

	}

}
