package uk.gov.dwp.esf.mi.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uk.gov.dwp.esf.mi.common.RecordState;
import uk.gov.dwp.esf.mi.model.Participant;
import uk.gov.dwp.esf.mi.dtos.ParticipantDTO;
import uk.gov.dwp.esf.mi.exceptions.DataException;
import uk.gov.dwp.esf.mi.exceptions.PredicateException;
import uk.gov.dwp.esf.mi.commons.PredicatesBuilder;
import uk.gov.dwp.esf.mi.assemblers.ParticipantResourceAssembler;
import uk.gov.dwp.esf.mi.resources.ParticipantResource;
import uk.gov.dwp.esf.mi.resources.ParticipantResources;
import uk.gov.dwp.esf.mi.repositories.ParticipantRepository;
import org.modelmapper.ModelMapper;
import com.mysema.query.types.expr.BooleanExpression;

//Logger Imports
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author Phani Krishna
*/

@Service
@EnableMongoRepositories(basePackages = "uk.gov.dwp.esf")
@Transactional
public class ParticipantService {
	
	private ModelMapper modelMapper;
	private SimpleDateFormat dateFormat;

	public ParticipantService(){
		modelMapper = new ModelMapper();
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	}

	@Value("${server.port}")
	private int port;
		
	@Autowired
	private ParticipantRepository participantRepository;

	@Autowired
	private ParticipantResourceAssembler participantResourceAssembler;

	@Autowired
	private PredicatesBuilder builder;
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	/*
	 * Retrieves All participants basic information
	 * 
	 */
	public ParticipantResources getAllParticipants( final Pageable pageable, final String requestURI) {
		ParticipantResources participantResources = null;
		final Page<Participant> participants = participantRepository.findAll( pageable);
		participantResources = (participants == null || participants.getContent().isEmpty()) ? participantResources
				: participantResourceAssembler.toResourcesForParticipants(modelMapper, participants, pageable,
						requestURI);
		return participantResources;
	}

	/*
	 * Retrieves participant information based on participantId.
	 * 
	 */
	public List<ParticipantResource> getParticipant(final String participantId, final Pageable pageable, final String requestURI) {
		List<ParticipantResource> participantResources = null;
		final Page<Participant> participants = participantRepository.findByParticipantId(participantId, pageable);
		participantResources = (participants == null || participants.getContent().isEmpty()) ? participantResources
				: participantResourceAssembler.toResourcesForAParticipant(modelMapper, participants, requestURI);
		return participantResources;
	}

	/*
	 * Create a new Participant in the database based on the Participant DTO
	 * object provided.
	 * 
	 */
	public List<ParticipantResource> createParticipant(ParticipantDTO participantDTO, final String requestURI)
			throws DataException {
		// Check whether the given contractId and nino exists in the database.If exists, then throw data exception
		final List<Participant> participants = validateParticipant(participantDTO.getContractId(),participantDTO.getNino());				
		if(participants != null && !participants.isEmpty())
			throw new DataException("error.participant.found","Participant already exists");
				
		List<ParticipantResource> participantResources = null;
		mapParticipantDetails(participantDTO);
		final Participant esfParticipant = participantRepository.save(modelMapper.map(participantDTO, Participant.class));

		participantResources = (esfParticipant == null) ? participantResources
				: participantResourceAssembler.toResourcesForAParticipant( participantDTO,
						esfParticipant.getParticipantId(), requestURI);
		return participantResources;
	}
	

	/*
	 * Update the existing Participant in the database based on the Participant
	 * DTO object provided.
	 * 
	 */
	public List<ParticipantResource> updateParticipant(ParticipantDTO participantDTO, final String requestURI) {
		List<ParticipantResource> participantResources = null;
		Participant esfParticipant = participantRepository.findByParticipantId(participantDTO.getParticipantId());
		esfParticipant = (esfParticipant == null) ? esfParticipant
				: participantRepository.save(modelMapper.map(participantDTO, Participant.class));
		participantResources = (esfParticipant == null) ? participantResources
				: participantResourceAssembler.toResourcesForAParticipant( participantDTO,
						esfParticipant.getParticipantId(), requestURI);
		return participantResources;
	}
	
	
	/*
	 * Delete the Participant record from the database based on the participant Id.
	 * 
	 */
	public void deleteParticipant(final String participantId) throws DataException{
		final Participant esfParticipant = participantRepository.findByParticipantId(participantId);		
		if(esfParticipant != null)
			if(esfParticipant.getStartDate() == null)
				participantRepository.delete(participantId);
			else
				throw new DataException("error.participant.startDate.exists","Participant start Date exists");
	}
	
	
	/*
	 * Retrieves all participants based on the filtering criteria.
	 * 
	 */
	public ParticipantResources getFilteredParticipants(final String filter, final Pageable pageable,
			final String requestURI) throws PredicateException {
		ParticipantResources participantResources = null;
		BooleanExpression expression = builder.createPredicateBuilder(filter);
		final Page<Participant> participants = participantRepository.findAll(expression, pageable);
		participantResources = (participants == null || participants.getContent().isEmpty()) ? participantResources
				: participantResourceAssembler.toResourcesForParticipants(modelMapper, participants, pageable,
						requestURI);
		return participantResources;
	}
		
	/*
	 * A call to CIS web service and populate the Address object details from
	 * the CIS response.
	 * 
	 */
	private List<Participant> validateParticipant(Integer contractId , String nino) throws DataException {
		List<Participant> participants = participantRepository.findByContractIdAndNino(contractId, nino);
		return participants;
	}



	/*
	 * Populate the Creation Date, Updated Date and Status values for the
	 * Participant DTO object.
	 * 
	 */
	private ParticipantDTO mapParticipantDetails(ParticipantDTO participant) {
		try {
			participant.setCreationDate(dateFormat.parse(dateFormat.format(new Date())));
			participant.setUpdatedDate(dateFormat.parse(dateFormat.format(new Date())));
			participant.setRecordState(RecordState.CREATED);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			logger.error("Exception:" + e.getMessage());
		}
		
		return participant;
	}

}
