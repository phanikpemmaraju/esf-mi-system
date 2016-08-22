package uk.gov.dwp.esf.mi.dtos;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import uk.gov.dwp.esf.mi.common.ContactDetails;
import uk.gov.dwp.esf.mi.common.Category;
import uk.gov.dwp.esf.mi.common.Ethnicity;
import uk.gov.dwp.esf.mi.common.ExitEmpStatus;
import uk.gov.dwp.esf.mi.common.HouseHoldType;
import uk.gov.dwp.esf.mi.common.RecordState;
import uk.gov.dwp.esf.mi.common.View;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @Author Phani Krishna
*/

public class ParticipantDTOTest {
	
	private Validator validator;
	private ObjectMapper mapper;
	private SimpleDateFormat dateFormat;

	@Before
	public void setup() {
		ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
		this.validator = vf.getValidator();
		this.mapper = new ObjectMapper();
		this.dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	}
	
	@Test
	public void validationsTest() throws ParseException{
		// Invalid NI
		ParticipantDTO participant = new ParticipantDTO();
		participant.setParticipantId("56dffaa6097d9818d8b455a7");participant.setProviderId(1111111);
		participant.setContractId(4001115);participant.setNino("SJ1960589");participant.setDob(dateFormat.parse("1982-12-16"));
		participant.setCreationDate(dateFormat.parse(LocalDate.now().toString()));participant.setUpdatedDate(dateFormat.parse(LocalDate.now().toString()));participant.setRecordState(RecordState.CREATED);
		Set<ConstraintViolation<ParticipantDTO>> violations = this.validator.validate(participant);
		assertEquals(1, violations.size());
		assertEquals("error.invalid.nino.format", violations.iterator().next().getMessage());
		
		// Invalid Contract Id
		participant.setNino("SJ196058B");participant.setContractId(null);
		violations = this.validator.validate(participant);
		assertEquals(1, violations.size());
		assertEquals("error.contractId.null", violations.iterator().next().getMessage());
		participant.setContractId(4001115);participant.setProviderId(null);
		
		// Invalid Provider Id
		violations = this.validator.validate(participant);
		assertEquals(1, violations.size());
		assertEquals("error.providerId.null", violations.iterator().next().getMessage());
		participant.setProviderId(1111111);participant.setDob(null);
		
		// Invalid DOB Id
		violations = this.validator.validate(participant);
		assertEquals(1, violations.size());
		assertEquals("error.dob.null", violations.iterator().next().getMessage());
		
		// Valid NI
		participant.setDob(dateFormat.parse("1982-12-16"));
		violations = this.validator.validate(participant);
		assertEquals(0, violations.size());
			
		// Test the toString() method
		final String value = "ParticipantDTO [participantId=56dffaa6097d9818d8b455a7, providerId=1111111, contactDetails=null, nino=SJ196058B, dob=Thu Dec 16 00:00:00 GMT 1982, title=null, forename=null, surname=null, match=null, disabled=null, ethnicity=null, labourMarketStatus=null, gender=null, postcode=null, longTermUnemployed=null, basicSkills=null, iscedLevel=null, homeless=null, exOffender=null, householdType=null, creationDate=" +  dateFormat.parse(LocalDate.now().toString()).toString() + ", updatedDate=" +  dateFormat.parse(LocalDate.now().toString()).toString() + ", recordState=CREATED, startDate=null, proposedExitDate=null, exitDate=null, contractId=4001115, exitEmpStatus=null, exitTraining=null, exitSkills=null, exitQualification=null, exitChildcare=null, deliveryPostcode=null, fundingAggrement=null, cor=null, priorityAxis=null, exitlabourMarketStatus=null, exitQualificationLevel=null, exitISCEDLevel=null]";
		assertThat(participant.toString(), is(value));
	}
	
	@Test
	public void basicParticipantsViewTest() throws IOException, ParseException{
		// Set Participant details for Basic Participant Json View
		final ParticipantDTO participant = new ParticipantDTO();
		participant.setParticipantId("56dffaa6097d9818d8b455a7");participant.setProviderId(1111111);
		participant.setContractId(4001115);participant.setNino("SJ1960589");participant.setDob(dateFormat.parse("1982-12-16"));
		participant.setCreationDate(dateFormat.parse(dateFormat.format(new Date())));participant.setUpdatedDate(dateFormat.parse(dateFormat.format(new Date())));participant.setRecordState(RecordState.CREATED);
		participant.setLabourMarketStatus("C001");participant.setPostcode("M40 1SS");
		
		participant.setExOffender(false);
		
		mapper.setSerializationInclusion(Include.NON_NULL);
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		
		String participantView =  mapper.writerWithView(View.SummaryWithBasicParticipants.class).writeValueAsString(participant);
		String viewValue = "{\"participantId\":\"56dffaa6097d9818d8b455a7\",\"providerId\":1111111,\"nino\":\"SJ1960589\",\"dob\":\"1982-12-16\",\"postcode\":\"M40 1SS\",\"creationDate\":\"" + LocalDate.now().toString() + "\",\"updatedDate\":\"" + LocalDate.now().toString() + "\",\"recordState\":\"CREATED\",\"contractId\":4001115}";
		assertEquals(viewValue, participantView);		
	}
	
	@Test
	public void fullParticipantsViewTest() throws IOException, ParseException{
		final ParticipantDTO participant = new ParticipantDTO();
		participant.setParticipantId("56dffaa6097d9818d8b455a7");participant.setProviderId(1111111);
		participant.setContractId(4001115);participant.setNino("SJ1960589");participant.setDob(dateFormat.parse("1982-12-16"));
		participant.setCreationDate(dateFormat.parse(dateFormat.format(new Date())));participant.setUpdatedDate(dateFormat.parse(dateFormat.format(new Date())));participant.setRecordState(RecordState.CREATED);
		
		// Create Address object		
		final ContactDetails contactDetails = new ContactDetails.ContactDetailsBuilder("Apartment 33","Sheffield","S1 4GG")
				.addressLine4("South Yorkshire").addressLine3("Royal Plaza").countryCodeID("UK")
                .build();
		
		// Set Participant details for Full Participant Json View
		participant.setContactDetails(contactDetails);participant.setEthnicity(Ethnicity.WHITE_ENGLISH);
		participant.setMatch(true);participant.setGender("MALE");
		participant.setBasicSkills(true);participant.setIscedLevel(3);
		participant.setExOffender(false);;participant.setHouseholdType(HouseHoldType.JOBLESS_HOUSEHOLD);
		participant.setDisabled(true);participant.setLongTermUnemployed(false);
		participant.setHomeless(false);participant.setExitTraining(false);
		participant.setExitSkills(true);participant.setExitQualification(false);participant.setExitChildcare(true);
		participant.setExitlabourMarketStatus("exit market");
		participant.setExitQualificationLevel(0);participant.setExitISCEDLevel(1);
		participant.setStartDate(dateFormat.parse(LocalDate.now().plusDays(14).toString()));participant.setProposedExitDate(dateFormat.parse(LocalDate.now().plusDays(74).toString()));participant.setExitDate(dateFormat.parse(LocalDate.now().plusDays(75).toString()));
		participant.setDeliveryPostcode("S1 4GG");participant.setFundingAggrement("funds");
		participant.setCor(Category.LESS_DEVELOPED);participant.setPriorityAxis(1.4);participant.setExitEmpStatus(ExitEmpStatus.JOBSEEKING);
		participant.setLabourMarketStatus("C001");participant.setPostcode("M40 1SS");participant.setTitle("Mr");participant.setForename("geoff");participant.setSurname("geoff");
		
		mapper.setSerializationInclusion(Include.NON_NULL);
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		
		String participantView =  mapper.writerWithView(View.SummaryWithFullParticipants.class).writeValueAsString(participant);
		String viewValue = "{\"participantId\":\"56dffaa6097d9818d8b455a7\",\"providerId\":1111111,\"contactDetails\":{\"addressLine1\":\"Apartment 33\",\"addressLine2\":\"Sheffield\",\"addressLine3\":\"Royal Plaza\",\"addressLine4\":\"South Yorkshire\",\"postcode\":\"S1 4GG\",\"countryCodeID\":\"UK\"},\"nino\":\"SJ1960589\",\"dob\":\"1982-12-16\",\"title\":\"Mr\",\"forename\":\"geoff\",\"surname\":\"geoff\",\"match\":true,\"disabled\":true,\"ethnicity\":\"WHITE_ENGLISH\",\"labourMarketStatus\":\"C001\",\"gender\":\"MALE\",\"postcode\":\"M40 1SS\",\"longTermUnemployed\":false,\"basicSkills\":true,\"iscedLevel\":3,\"homeless\":false,\"exOffender\":false,\"householdType\":\"JOBLESS_HOUSEHOLD\",\"creationDate\":\"" + LocalDate.now().toString() + "\",\"updatedDate\":\"" + LocalDate.now().toString() + "\",\"recordState\":\"CREATED\",\"startDate\":\"" + LocalDate.now().plusDays(14).toString() + "\",\"proposedExitDate\":\"" + LocalDate.now().plusDays(74).toString() + "\",\"exitDate\":\"" + LocalDate.now().plusDays(75).toString() + "\",\"contractId\":4001115,\"exitEmpStatus\":\"JOBSEEKING\",\"exitTraining\":false,\"exitSkills\":true,\"exitQualification\":false,\"exitChildcare\":true,\"deliveryPostcode\":\"S1 4GG\",\"fundingAggrement\":\"funds\",\"cor\":\"LESS_DEVELOPED\",\"priorityAxis\":1.4,\"exitlabourMarketStatus\":\"exit market\",\"exitQualificationLevel\":0,\"exitISCEDLevel\":1}";
		assertEquals(viewValue, participantView);
	}

}
