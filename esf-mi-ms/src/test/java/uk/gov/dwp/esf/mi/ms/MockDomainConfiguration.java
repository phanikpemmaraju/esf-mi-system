package uk.gov.dwp.esf.mi.ms;

/*
 *  @Author : Phani Krishna
 *
 */

import uk.gov.dwp.esf.mi.model.Participant;
import uk.gov.dwp.esf.mi.commons.PredicatesBuilder;
import uk.gov.dwp.esf.mi.assemblers.ParticipantResourceAssembler;
import uk.gov.dwp.esf.mi.services.ParticipantService;
import uk.gov.dwp.esf.mi.services.StartDateUpdateService;
import uk.gov.dwp.esf.mi.repositories.ParticipantRepository;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mysema.query.types.path.PathBuilder;

import static org.mockito.Mockito.mock;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Phani Krishna
*/

@Configuration
@EnableAutoConfiguration
public class MockDomainConfiguration {
	
	@Bean(name="participantRepository")
	public ParticipantRepository mockParticipantRepository(){
		return mock(ParticipantRepository.class);
	}
	
	@Bean(name="participantResourceAssembler")
	public ParticipantResourceAssembler mockParticipantResourceAssembler(){
		return mock(ParticipantResourceAssembler.class);
	}
	
	@Bean(name="builder")
	public PredicatesBuilder mockPredicatesBuilder(){
		return mock(PredicatesBuilder.class);
	}
	
	@Bean(name="participantService")
	public ParticipantService mockParticipantService(){
		return mock(ParticipantService.class);
	}
	
	@Bean(name="request")
	public HttpServletRequest mockHttpServletRequest(){
		return mock(HttpServletRequest.class);
	}
	
	@SuppressWarnings("unchecked")
	@Bean(name="participantPath")
	public PathBuilder<Participant> mockParticipantPath(){
		return mock(PathBuilder.class);
	}
	
	@Bean(name="modelMapper")
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
	
	@Bean(name="startDateService")
	public StartDateUpdateService startDateService(){
		return mock(StartDateUpdateService.class);
	}

}
