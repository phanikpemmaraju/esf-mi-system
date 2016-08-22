package uk.gov.dwp.esf.mi.controllers;


/*
 *  @Author : Phani Krishna
 *  @Description : ParticipantController class for RestController mappings
 *  @Version : 1.0
 */

import uk.gov.dwp.esf.mi.common.View;
import uk.gov.dwp.esf.mi.dtos.ParticipantDTO;
import uk.gov.dwp.esf.mi.exceptions.DataException;
import uk.gov.dwp.esf.mi.exceptions.PredicateException;
import uk.gov.dwp.esf.mi.resources.ParticipantResource;
import uk.gov.dwp.esf.mi.resources.ParticipantResources;
import uk.gov.dwp.esf.mi.services.ParticipantService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(allowedHeaders="*",methods={RequestMethod.GET, RequestMethod.POST,RequestMethod.PUT, RequestMethod.DELETE},origins="*")
@RestController
@RequestMapping( produces = {"application/json;charset=UTF-8"})
public class ParticipantController {
	
		
	@Autowired
	private ParticipantService participantService;		
	
	@Autowired
	private HttpServletRequest request;
	
		
	/*
	 *  This controller method retrieves all the participants information for a given providerId.
	 *  
	 */		
	@SuppressWarnings("rawtypes")
	@JsonView(View.SummaryWithBasicParticipants.class)
	@RequestMapping(path="/participants",method = RequestMethod.GET)
	public ResponseEntity getAllParticipants(Pageable pageable) {
		final ParticipantResources resources = participantService.getAllParticipants(pageable, request.getRequestURI());		
		return ResponseEntity.ok().cacheControl(CacheControl.noCache()).body(resources);
	}
	
	
	/*
	 *  This controller method retrieves the participant information for a given participantId.
	 *  
	 */
	@SuppressWarnings("rawtypes")
	@JsonView(View.SummaryWithFullParticipants.class)
	@RequestMapping(path="/participants/{participantId}",method = RequestMethod.GET)	
	public ResponseEntity getParticipant(@PathVariable String participantId, Pageable pageable) {
		final List<ParticipantResource> resources = participantService.getParticipant(participantId,pageable,request.getRequestURI());
		final ResponseEntity entity = (resources == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok().cacheControl(CacheControl.noCache()).body(resources);
		return entity;
	}
		
	
	/*
	 *  This controller method retrieves the participants information based on the Predicate filters.
	 *  
	 */
	@SuppressWarnings("rawtypes")
	@JsonView(View.SummaryWithFullParticipants.class)
	@RequestMapping(path="/participants/search/{filter}",method = RequestMethod.GET	)
	public ResponseEntity getFilteredParticipants(@PathVariable String filter, Pageable pageable) throws PredicateException{
		final ParticipantResources resources = participantService.getFilteredParticipants(filter, pageable,request.getRequestURI());		
		final ResponseEntity entity = (resources == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok().cacheControl(CacheControl.noCache()).body(resources);
		return entity;
	}

	
	/*
	 *  This controller method creates an Participant based on the information provided.
	 *  
	 */		
	@SuppressWarnings("rawtypes")
	@JsonView(View.SummaryWithFullParticipants.class)
	@RequestMapping(path="/participants",method = RequestMethod.POST , consumes = {"application/json;charset=UTF-8"} )
	public ResponseEntity createParticipant(@Validated @RequestBody ParticipantDTO participant) throws DataException {	
		return ResponseEntity.status(HttpStatus.CREATED).body(participantService.createParticipant(participant,request.getRequestURI()));
	}
	
	
	/*
	 *  This controller method updates an existing Participant based on the information provided.
	 *  
	 */		
	@SuppressWarnings("rawtypes")
	@JsonView(View.SummaryWithFullParticipants.class)
	@RequestMapping(path="/participants/{participantId}",method = RequestMethod.PUT , consumes = {"application/json;charset=UTF-8"} )
	public ResponseEntity updateParticipant(@Validated @RequestBody ParticipantDTO participant) {	
		final List<ParticipantResource> resources = participantService.updateParticipant(participant,request.getRequestURI());
		final ResponseEntity entity = (resources == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok().cacheControl(CacheControl.noCache()).body(resources);
		return entity;
	}
		
	
	/*
	 *  This controller method deletes the Participant object for the provided participantId.
	 *  
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(path="/participants/{participantId}",method = RequestMethod.DELETE)	
	public ResponseEntity deleteParticipant(@PathVariable String participantId) throws DataException{
		participantService.deleteParticipant(participantId);
		return ResponseEntity.ok().cacheControl(CacheControl.noCache()).build();
	}

	
}
