package uk.gov.dwp.esf.mi.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.gov.dwp.esf.mi.common.RecordState;
import uk.gov.dwp.esf.mi.common.StartDateRecord;
import uk.gov.dwp.esf.mi.model.Participant;
import uk.gov.dwp.esf.mi.repositories.ParticipantRepository;

/**
 * @Author Phani Krishna
*/

@Service
public class StartDateUpdateService {
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());	
	
	@Autowired
	private ParticipantRepository participantRepository;
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
	/*
	 * Updates the Participant details with start date, updated date , proposed Exit date and record state in the backend
	 * Returns StartDateRecord with status Success if its a valid record
	 * 
	 */
	public StartDateRecord processParticipant(String[] strings) {
		StartDateRecord startDateRecord = new StartDateRecord();
		try{
			startDateRecord.setNino(strings[0].trim());startDateRecord.setContractId(Integer.parseInt(strings[1].trim()));startDateRecord.setStartDate(dateFormat.parse(strings[2].trim()));
			final List<Participant> participants = participantRepository.findByContractIdAndNino(startDateRecord.getContractId(), startDateRecord.getNino());
			if(!participants.isEmpty() && participants.get(0).getStartDate() == null){	
				// Update Participant
				updateParticipant(participants.get(0), startDateRecord);								
				// Populate the status in Start Date Record class 
				startDateRecord.setStatus("Success");
			}
			
		}catch(ParseException ex){
			logger.info(ex.getMessage());
		}
		
		return startDateRecord;
	}
	
	
	public void updateParticipant(Participant participant , StartDateRecord startDateRecord) throws ParseException{
		participant.setStartDate(startDateRecord.getStartDate());
		participant.setUpdatedDate(dateFormat.parse(dateFormat.format(new Date())));
		participant.setRecordState(RecordState.STARTED);
		final Instant instant = Instant.ofEpochMilli(startDateRecord.getStartDate().getTime());
		final LocalDate localDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate().plusWeeks(56);
		participant.setProposedExitDate(Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		
		// Save the Participant
		participantRepository.save(participant);
	}
	
}
