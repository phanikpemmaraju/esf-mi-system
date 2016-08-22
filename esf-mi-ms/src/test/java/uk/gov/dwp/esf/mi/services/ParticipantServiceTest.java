package uk.gov.dwp.esf.mi.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import uk.gov.dwp.esf.mi.model.Participant;
import uk.gov.dwp.esf.mi.commons.PredicatesBuilder;
import uk.gov.dwp.esf.mi.assemblers.ParticipantResourceAssembler;
import uk.gov.dwp.esf.mi.common.RecordState;
import uk.gov.dwp.esf.mi.dtos.ParticipantDTO;
import uk.gov.dwp.esf.mi.exceptions.DataException;
import uk.gov.dwp.esf.mi.resources.ParticipantResource;
import uk.gov.dwp.esf.mi.resources.ParticipantResources;
import uk.gov.dwp.esf.mi.repositories.ParticipantRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.hateoas.Link;
import com.mysema.query.types.path.PathBuilder;

/**
 * @Author Phani Krishna
*/

@RunWith(MockitoJUnitRunner.class)
public class ParticipantServiceTest {
	
	private SimpleDateFormat dateFormat;
	
	@Mock
	private ModelMapper modelMapper;
	
	@Mock
	private ParticipantRepository participantRepository;
					
	@Mock
	private ParticipantResourceAssembler participantResourceAssembler;
	
	@Mock
	private PredicatesBuilder builder;
	
	@Mock
	private PathBuilder<Participant> participantPath;
	
	@Mock
	private Page<Participant> participants;
	
	@InjectMocks
	private ParticipantService participantService;
	
	@Before
	public void setUp(){
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	}
	
	
	/*
     * Test getAllParticipants controller rest method
     * This method returns with the basic provider information.
     * A self link is also populated. 
   	*/
	@Test
	public void testGetAllParticipants() throws Exception{
		final Pageable pageable = new PageRequest(0,1,Direction.ASC,"providerId");
		final List<Participant> participantsList = new ArrayList<>();
		final Participant participant1 = new Participant.ParticipantBuilder("56e1624c710856201cd55f61", 1111111, "SL123457B", dateFormat.parse(dateFormat.format(new Date())),4001114)
				.creationDate(dateFormat.parse(dateFormat.format(new Date()))).updatedDate(dateFormat.parse(dateFormat.format(new Date()))).recordState(RecordState.CREATED).build();
		final Participant participant2 = new Participant.ParticipantBuilder("56e1624c710856201cd55f62", 1111111, "SL123457B", dateFormat.parse(dateFormat.format(new Date())),4001115)
				.creationDate(dateFormat.parse(dateFormat.format(new Date()))).updatedDate(dateFormat.parse(dateFormat.format(new Date()))).recordState(RecordState.CREATED).build();
    	
		participantsList.add(participant1);participantsList.add(participant2);
		
		final List<ParticipantResource> participantResourcesList = new ArrayList<>();
		ParticipantResources participantResources = new ParticipantResources();
		participantsList.forEach(participant -> {
			final ParticipantDTO participantDTO = new ModelMapper().map(participant, ParticipantDTO.class);
			final ParticipantResource participantResource = new ParticipantResource(participantDTO);
			participantResource.add(new Link("/participants/"+participant.getParticipantId(),"self"));
			participantResourcesList.add(participantResource);
		});
		
		participantResources.setParticipants(participantResourcesList);
		
		when(participants.getContent()).thenReturn(participantsList);
		when(participantRepository.findAll( pageable)).thenReturn(participants);
		when(participantResourceAssembler.toResourcesForParticipants(modelMapper,participantRepository.findAll(pageable),pageable,"/participants")).thenReturn(null);
		
		ParticipantResources resources = participantService.getAllParticipants( pageable, "/participants");
		assertNull(resources);
		
		when(participantResourceAssembler.toResourcesForParticipants(modelMapper,participantRepository.findAll(pageable),pageable,"/participants")).thenReturn(participantResources);
		resources = participantService.getAllParticipants( pageable, "/participants");
		assertNotNull(resources);
		assertEquals(resources.getParticipants().size(),2);
		assertEquals(resources.getParticipants().get(0).getLinks().get(0).getHref(),"/participants/56e1624c710856201cd55f61");
		assertEquals(resources.getParticipants().get(1).getLinks().get(0).getHref(),"/participants/56e1624c710856201cd55f62");
		
		participantsList.clear();
		when(participants.getContent()).thenReturn(participantsList);
		resources = participantService.getAllParticipants(pageable, "/participants");
		assertNull(resources);
		
		participants = null;
		when(participantRepository.findAll( pageable)).thenReturn(participants);
		resources = participantService.getAllParticipants( pageable, "/participants");
		assertNull(resources);
	}

	
	/*
     * Test getParticipantsByProviderIdAndParticipantId controller rest method
     * This method returns with the basic provider information.
     * A self link is also populated. 
   	*/
	@Test
	public void testGetParticipant() throws Exception{
		final Pageable pageable = new PageRequest(0,1,Direction.ASC,"providerId");
		final List<Participant> participantsList = new ArrayList<>();
		final Participant participant1 = new Participant.ParticipantBuilder("56e1624c710856201cd55f61", 1111111, "SL123457B", dateFormat.parse(dateFormat.format(new Date())),4001114)
				.creationDate(dateFormat.parse(dateFormat.format(new Date()))).updatedDate(dateFormat.parse(dateFormat.format(new Date()))).recordState(RecordState.CREATED).build();
    	    	    					
		participantsList.add(participant1);
		
		List<ParticipantResource> participantResourcesList = new ArrayList<>();
		participantsList.forEach(participant -> {
			final ParticipantDTO participantDTO = new ModelMapper().map(participant, ParticipantDTO.class);
			final ParticipantResource participantResource = new ParticipantResource(participantDTO);
			participantResource.add(new Link("/participants/"+participant.getParticipantId(),"self"));
			participantResourcesList.add(participantResource);
		});
		
		
		when(participants.getContent()).thenReturn(participantsList);
		when(participantRepository.findByParticipantId("56e1624c710856201cd55f61", pageable)).thenReturn(participants);
		when(participantResourceAssembler.toResourcesForAParticipant(modelMapper,participantRepository.findByParticipantId("56e1624c710856201cd55f61", pageable),"/participants/56e1624c710856201cd55f61")).thenReturn(null);
		
		List<ParticipantResource>  resources = participantService.getParticipant("56e1624c710856201cd55f61", pageable, "/participants/56e1624c710856201cd55f61");
		assertNull(resources);
		
		when(participantResourceAssembler.toResourcesForAParticipant(modelMapper,participantRepository.findByParticipantId("56e1624c710856201cd55f61", pageable),"/participants/56e1624c710856201cd55f61")).thenReturn(participantResourcesList);
		
		resources = participantService.getParticipant("56e1624c710856201cd55f61", pageable, "/participants/56e1624c710856201cd55f61");
		assertNotNull(resources);
		assertEquals(resources.get(0).getLinks().get(0).getHref(),"/participants/56e1624c710856201cd55f61");
		assertEquals(resources.get(0).getParticipant().getParticipantId(),"56e1624c710856201cd55f61");
		
		participantsList.clear();
		when(participants.getContent()).thenReturn(participantsList);
		resources = participantService.getParticipant("56e1624c710856201cd55f61", pageable, "/participants/56e1624c710856201cd55f61");
		assertNull(resources);
		
		participants = null;
		when(participantRepository.findByParticipantId("56e1624c710856201cd55f61", pageable)).thenReturn(participants);
		resources = participantService.getParticipant("56e1624c710856201cd55f61", pageable, "/participants/56e1624c710856201cd55f61");
		assertNull(resources);
	}

	
	/*
     * Test getFilteredParticipants controller rest method
     * This method returns with the basic provider information.
     * A self link is also populated. 
   	*/
	@Test
	public void testGetFilteredParticipants() throws Exception{
		final Pageable pageable = new PageRequest(0,1,Direction.ASC,"providerId");
		final List<Participant> participantsList = new ArrayList<>();

		final Participant participant1 = new Participant.ParticipantBuilder("56e1624c710856201cd55f61", 1111111, "SL123457B", dateFormat.parse(dateFormat.format(new Date())),4001114)
				.creationDate(dateFormat.parse(dateFormat.format(new Date()))).updatedDate(dateFormat.parse(dateFormat.format(new Date()))).recordState(RecordState.CREATED).build();
    	
		final Participant participant2 = new Participant.ParticipantBuilder("56e1624c710856201cd55f62", 1111111, "SL123457B", dateFormat.parse(dateFormat.format(new Date())),4001115)
				.creationDate(dateFormat.parse(dateFormat.format(new Date()))).updatedDate(dateFormat.parse(dateFormat.format(new Date()))).recordState(RecordState.CREATED).build();

		participantsList.add(participant1);participantsList.add(participant2);
		
		//Page<Participant> participants = new PageImpl<Participant>(participantsList);
		final List<ParticipantResource> participantResourcesList = new ArrayList<>();
		ParticipantResources participantResources = new ParticipantResources();
		participantsList.forEach(participant -> {
			final ParticipantDTO participantDTO = new ModelMapper().map(participant, ParticipantDTO.class);
			final ParticipantResource participantResource = new ParticipantResource(participantDTO);
			participantResource.add(new Link("/providers/"+participant.getProviderId()+"/participants/"+participant.getParticipantId(),"self"));
			participantResourcesList.add(participantResource);
		});
		
		participantResources.setParticipants(participantResourcesList);
		
		when(participants.getContent()).thenReturn(participantsList);
		when(participantRepository.findAll(participantPath.in(participantsList), pageable)).thenReturn(participants);
		when(participantResourceAssembler.toResourcesForParticipants(modelMapper,participantRepository.findAll(participantPath.in(participantsList), pageable),pageable,"/providers/1111111/participants/search/participantId_in=56e1624c710856201cd55f61,56e1624c710856201cd55f62")).thenReturn(null);
		
		ParticipantResources resources = participantService.getFilteredParticipants("participantId_in=56e1624c710856201cd55f61,56e1624c710856201cd55f62", pageable, "/providers/1111111/participants/search/participantId_in=56e1624c710856201cd55f61,56e1624c710856201cd55f62");
		assertNull(resources);
		
		when(participantResourceAssembler.toResourcesForParticipants(modelMapper,participantRepository.findAll(participantPath.in(participantsList), pageable),pageable,"/providers/1111111/participants/search/participantId_in=56e1624c710856201cd55f61,56e1624c710856201cd55f62")).thenReturn(participantResources);
		resources = participantService.getFilteredParticipants("participantId_in=56e1624c710856201cd55f61,56e1624c710856201cd55f62", pageable, "/providers/1111111/participants/search/participantId_in=56e1624c710856201cd55f61,56e1624c710856201cd55f62");
		assertNotNull(resources);
		assertEquals(resources.getParticipants().get(0).getParticipant().getParticipantId(),"56e1624c710856201cd55f61");
		assertEquals(resources.getParticipants().get(1).getParticipant().getParticipantId(),"56e1624c710856201cd55f62");
		
		participantsList.clear();
		when(participants.getContent()).thenReturn(participantsList);
		resources = participantService.getFilteredParticipants("participantId_in=56e1624c710856201cd55f61,56e1624c710856201cd55f62", pageable, "/providers/1111111/participants/search/participantId_in=56e1624c710856201cd55f61,56e1624c710856201cd55f62");
		assertNull(resources);
		
		participants = null;
		when(participantRepository.findAll(participantPath.in(participantsList), pageable)).thenReturn(participants);
		resources = participantService.getFilteredParticipants("participantId_in=56e1624c710856201cd55f61,56e1624c710856201cd55f62", pageable, "/providers/1111111/participants/search/participantId_in=56e1624c710856201cd55f61,56e1624c710856201cd55f62");
		assertNull(resources);
	}
	
	
	/*
     * Test createParticipant controller rest method
     * This method saves the participant object into the repository and returns with the participant object with a generated Id.
     * A self link is also populated. 
   	*/
	@Test
	public void testCreateParticipant() throws Exception{
		final List<Participant> participantsList = new ArrayList<>();
		final Participant participant = new Participant.ParticipantBuilder(null, 1111111, "SL123457B", dateFormat.parse(dateFormat.format(new Date())),4001114)
				.creationDate(dateFormat.parse(dateFormat.format(new Date()))).updatedDate(dateFormat.parse(dateFormat.format(new Date()))).recordState(RecordState.CREATED).build();
    	
				
		final Participant persistentParticipant = new Participant.ParticipantBuilder("56b4694d7eb68a5fff76290c", 1111111, "SL123457B", dateFormat.parse(dateFormat.format(new Date())),4001114)
				.creationDate(dateFormat.parse(dateFormat.format(new Date()))).updatedDate(dateFormat.parse(dateFormat.format(new Date()))).recordState(RecordState.CREATED).build();
    	    					
		participantsList.add(participant);
		List<ParticipantResource> participantResourcesList = new ArrayList<>();
		participantsList.forEach(participantResource -> {
			final ParticipantDTO participantDTO = new ModelMapper().map(persistentParticipant, ParticipantDTO.class);
			final ParticipantResource resource = new ParticipantResource(participantDTO);
			resource.add(new Link("/providers/"+participant.getProviderId()+"/participants/"+participant.getParticipantId(),"self"));
			participantResourcesList.add(resource);
		});

		final ParticipantDTO participantDTO = new ModelMapper().map(persistentParticipant, ParticipantDTO.class);
		participantDTO.setDob(dateFormat.parse("1982-12-16"));participantDTO.setNino("SJ196058B");
		
		when(participantRepository.save(modelMapper.map(participantDTO, Participant.class))).thenReturn(persistentParticipant);
		when(participantResourceAssembler.toResourcesForAParticipant(participantDTO,persistentParticipant.getParticipantId(),"/providers/1111111/participants")).thenReturn(null);
		
		List<ParticipantResource>  resources = participantService.createParticipant(participantDTO, "/providers/1111111/participants");
		assertNull(resources);
		
		when(participantResourceAssembler.toResourcesForAParticipant(participantDTO,persistentParticipant.getParticipantId(),"/providers/1111111/participants")).thenReturn(participantResourcesList);
		resources = participantService.createParticipant(participantDTO, "/providers/1111111/participants");
		assertNotNull(resources);		
        assertThat(resources.get(0).getParticipant().getProviderId(), is(1111111));
        assertThat(resources.get(0).getParticipant().getParticipantId(), is("56b4694d7eb68a5fff76290c"));
        
        when(participantRepository.save(modelMapper.map(participantDTO, Participant.class))).thenReturn(null);
        resources = participantService.createParticipant(participantDTO, "/providers/1111111/participants");
		assertNull(resources);
		
	}
	
	/*
     * Test updateParticipant controller rest method
     * This method saves the existing participant object with the new details into the repository.
     * A self link is also populated. 
   	*/
	@Test
	public void testUpdateParticipant() throws Exception{
		final List<Participant> participantsList = new ArrayList<>();
		final Participant participant = new Participant.ParticipantBuilder(null, 1111111, "SJ196058B", dateFormat.parse(dateFormat.format(new Date())),4001114)
				.creationDate(dateFormat.parse(dateFormat.format(new Date()))).updatedDate(dateFormat.parse(dateFormat.format(new Date()))).recordState(RecordState.CREATED).build();
				
		final Participant persistentParticipant = new Participant.ParticipantBuilder("56b4694d7eb68a5fff76290c", 1111111, "SJ196058B", dateFormat.parse(dateFormat.format(new Date())),4001114)
				.creationDate(dateFormat.parse(dateFormat.format(new Date()))).updatedDate(dateFormat.parse(dateFormat.format(new Date()))).recordState(RecordState.CREATED).build();
    	    					
		participantsList.add(participant);
		List<ParticipantResource> participantResourcesList = new ArrayList<>();
		participantsList.forEach(participantResource -> {
			final ParticipantDTO participantDTO = new ModelMapper().map(persistentParticipant, ParticipantDTO.class);
			final ParticipantResource resource = new ParticipantResource(participantDTO);
			resource.add(new Link("/providers/"+participant.getProviderId()+"/participants/"+participant.getParticipantId(),"self"));
			participantResourcesList.add(resource);
		});
		
		final ParticipantDTO participantDTO = new ModelMapper().map(persistentParticipant, ParticipantDTO.class);
		participantDTO.setDob(dateFormat.parse("1982-12-16"));participantDTO.setNino("SJ196058B");
		
		when(participantRepository.findByParticipantId("56b4694d7eb68a5fff76290c")).thenReturn(persistentParticipant);
		when(participantRepository.save(modelMapper.map(participantDTO, Participant.class))).thenReturn(persistentParticipant);
		when(participantResourceAssembler.toResourcesForAParticipant(participantDTO,persistentParticipant.getParticipantId(),"/providers/1111111/participants")).thenReturn(null);
		
		List<ParticipantResource>  resources = participantService.updateParticipant(participantDTO, "/providers/1111111/participants");
		assertNull(resources);
		
		when(participantResourceAssembler.toResourcesForAParticipant(participantDTO,persistentParticipant.getParticipantId(),"/providers/1111111/participants")).thenReturn(participantResourcesList);
		resources = participantService.updateParticipant(participantDTO, "/providers/1111111/participants");
		assertNotNull(resources);		
        assertThat(resources.get(0).getParticipant().getProviderId(), is(1111111));
        assertThat(resources.get(0).getParticipant().getParticipantId(), is("56b4694d7eb68a5fff76290c"));
        
        when(participantRepository.findByParticipantId("56b4694d7eb68a5fff76290c")).thenReturn(null);
        when(participantRepository.save(modelMapper.map(participantDTO, Participant.class))).thenReturn(null);
        resources = participantService.updateParticipant(participantDTO, "/providers/1111111/participants");
		assertNull(resources);
		
	}
	
	/*
     * Test deleteParticipant controller rest method
     * This method deletes the participant object from the repository
     * A self link is also populated. 
   	*/
	@Test(expected=DataException.class)
	public void testDeleteParticipant(){				
		Participant participant = null;
		try {
			participant = new Participant.ParticipantBuilder("56b4694d7eb68a5fff76290c", 1111111, "SL123457B", dateFormat.parse(dateFormat.format(new Date())),4001114)
					.creationDate(dateFormat.parse(dateFormat.format(new Date()))).updatedDate(dateFormat.parse(dateFormat.format(new Date()))).recordState(RecordState.CREATED).build();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		when(participantRepository.findByParticipantId("56b4694d7eb68a5fff76290c")).thenReturn(participant);
		participantService.deleteParticipant("56b4694d7eb68a5fff76290c");
		verify(participantRepository,times(1)).delete("56b4694d7eb68a5fff76290c");
		
		try {
			participant.setStartDate(dateFormat.parse("2016-04-06"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		when(participantRepository.findByParticipantId("56b4694d7eb68a5fff76290c")).thenReturn(participant);
		participantService.deleteParticipant("56b4694d7eb68a5fff76290c");
		verify(participantRepository,times(0)).delete("56b4694d7eb68a5fff76290c");
	}

}
