package uk.gov.dwp.esf.mi.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import org.junit.Test;

import uk.gov.dwp.esf.mi.common.Category;
import uk.gov.dwp.esf.mi.common.Ethnicity;
import uk.gov.dwp.esf.mi.common.ExitEmpStatus;
import uk.gov.dwp.esf.mi.common.HouseHoldType;
import uk.gov.dwp.esf.mi.common.RecordState;

/**
 * @Author Phani Krishna
*/

public class ParticipantTest {
	
	final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	/*
	 *  Using Builder Pattern to create Participant default Object
	 */	
	@Test
	public void shouldReturnParticipantBuilderFromFactory() {
		assertThat(new Participant.ParticipantBuilder(null, null, null, null,null),isA(Participant.ParticipantBuilder.class));
	}
	
	/*
	 *  Using Builder Pattern to create Participant default Object
	 */	
	@Test
	public void shouldReturnParticipantFromFactory() {
		assertThat(new Participant.ParticipantBuilder(null, null, null, null,null).build(),isA(Participant.class));
	}
	
	/*
	 *  Using Builder Pattern to create Participant Object with all mandatory fields
	 */
	@Test
	public void buildAParticipantWithMandatoryFields() throws ParseException {
		Participant participant = new Participant.ParticipantBuilder("56dffaa6097d9818d8b455a7", 1111111, "SJ196058B", dateFormat.parse("1982-12-16"), 4001115)
									  .build();
		
		assertThat(participant.getParticipantId(), is("56dffaa6097d9818d8b455a7"));
        assertThat(participant.getProviderId(), is(1111111));
        assertThat(participant.getNino(), is("SJ196058B"));
        assertThat(participant.getDob(), is(dateFormat.parse("1982-12-16")));
        assertThat(participant.getContractId(), is(4001115));        
        assertNotNull(participant.toString());
	}
	
	/*
	 *  Using Builder Pattern to create Participant Object with all fields
	 */
	@Test
	public void buildAParticipantWithAllFields() throws ParseException {
		Participant participant = new Participant.ParticipantBuilder("56dffaa6097d9818d8b455a7", 1111111, "SJ196058B",  dateFormat.parse("1982-12-16"), 4001115)
				.ethnicity(Ethnicity.WHITE_ENGLISH).isMatch(true).exOffender(false).isDisabled(false).gender("MALE")
				.longTermUnemployed(false).basicSkills(true).iscedLevel(3)
				.isHomeless(false).householdType(HouseHoldType.JOBLESS_HOUSEHOLD)
				.exitTraining(false).exitSkills(false).exitQualification(false).exitChildcare(false)
				.deliveryPostcode("S1 4GG").fundingAggrement("funds").recordState(RecordState.CREATED)
				.cor(Category.LESS_DEVELOPED).priorityAxis(1.4).exitEmpStatus(ExitEmpStatus.JOBSEEKING)
				.creationDate(dateFormat.parse(dateFormat.format(new Date())))
				.postcode("bb87hz").labourMarketStatus("C0001")
				.exitlabourMarketStatus("exit labour").exitQualificationLevel(0).exitISCEDLevel(0)
				.updatedDate(dateFormat.parse(dateFormat.format(new Date())))
				.startDate(dateFormat.parse(LocalDate.now().plusDays(14).toString()))
				.exitDate(dateFormat.parse(LocalDate.now().plusDays(75).toString()))
				.proposedExitDate(dateFormat.parse(LocalDate.now().plusDays(74).toString()))
				.build();
		
		assertThat(participant.getParticipantId(), is("56dffaa6097d9818d8b455a7"));
		assertThat(participant.getCreationDate(), is(dateFormat.parse(LocalDate.now().toString())));
		assertThat(participant.getUpdatedDate(), is(dateFormat.parse(LocalDate.now().toString())));
		assertThat(participant.getProposedExitDate(), is(dateFormat.parse(LocalDate.now().plusDays(74).toString())));
		assertThat(participant.getStartDate(), is(dateFormat.parse(LocalDate.now().plusDays(14).toString())));
		assertThat(participant.getExitDate(), is(dateFormat.parse(LocalDate.now().plusDays(75).toString())));
		
		
		assertThat(participant.getGender(), is("MALE"));
		assertThat(participant.getDeliveryPostcode(), is("S1 4GG"));
		assertThat(participant.getFundingAggrement(), is("funds"));
		assertThat(participant.getCor(), is(Category.LESS_DEVELOPED));
		assertThat(participant.getPriorityAxis(), is(1.4));

		assertThat(participant.getEthnicity(), is(Ethnicity.WHITE_ENGLISH));
		assertThat(participant.getHouseholdType(), is(HouseHoldType.JOBLESS_HOUSEHOLD));
		assertThat(participant.getIscedLevel(), is(3));
		assertThat(participant.getExitEmpStatus(), is(ExitEmpStatus.JOBSEEKING));
		assertThat(participant.getRecordState(), is(RecordState.CREATED));
		
		assertThat(participant.getMatch(), is(true));
		assertThat(participant.getExOffender(), is(false));
		assertThat(participant.getDisabled(), is(false));
		assertThat(participant.getLongTermUnemployed(), is(false));
		assertThat(participant.getBasicSkills(), is(true));
		
		assertThat(participant.getHomeless(), is(false));
		assertThat(participant.getExitTraining(), is(false));
		assertThat(participant.getExitSkills(), is(false));
		assertThat(participant.getExitQualification(), is(false));
		assertThat(participant.getExitChildcare(), is(false));				

		assertThat(participant.getPostcode(),is("bb87hz"));
		assertThat(participant.getLabourMarketStatus(),is("C0001"));
		assertThat(participant.getExitlabourMarketStatus(),is("exit labour"));
		assertThat(participant.getExitQualificationLevel(),is(0));
		assertThat(participant.getExitISCEDLevel(),is(0));
		
	}
	
	
	/*
	 *  Test Participant Builder object
	 */
	@Test
	public void buildParticipantBuilderWithAllFields() throws ParseException{
		Participant.ParticipantBuilder builder = new Participant.ParticipantBuilder("56dffaa6097d9818d8b455a7", 1111111, "SJ196058B",  dateFormat.parse("1982-12-16"), 4001115)
				.ethnicity(Ethnicity.WHITE_ENGLISH).isMatch(true).exOffender(false).isDisabled(false).gender("MALE")
				.longTermUnemployed(false).basicSkills(true).iscedLevel(3)
				.isHomeless(false).householdType(HouseHoldType.JOBLESS_HOUSEHOLD)
				.creationDate(dateFormat.parse(dateFormat.format(new Date()))).updatedDate(dateFormat.parse(dateFormat.format(new Date())))
				.startDate(dateFormat.parse(LocalDate.now().plusDays(14).toString()))
				.exitDate(dateFormat.parse(LocalDate.now().plusDays(75).toString()))
				.proposedExitDate(dateFormat.parse(LocalDate.now().plusDays(74).toString()))
				.exitTraining(false).exitSkills(false).exitQualification(false).exitChildcare(false)
				.deliveryPostcode("S1 4GG").fundingAggrement("funds").recordState(RecordState.CREATED)
				.cor(Category.MORE_DEVELOPED).priorityAxis(1.1).exitEmpStatus(ExitEmpStatus.JOBSEEKING);
		
		assertNotNull(builder);
	}
	
	
	/*
	 *  Create the Participant object using Setters.
	 *  We have created Setters for the Participant entity because of the Model Mapper.
	 *  The Model mapper maps the DTOs to Entity Objects and Vice Versa.
	 *  We would require this as we are not storing the Address and Personal details of Participant in the backend.
	 */
	@Test
	public void buildParticipant() throws ParseException{
		final Participant participant = new Participant();
		participant.setParticipantId("56dffaa6097d9818d8b455a7");participant.setProviderId(1111111);
		participant.setContractId(4001115);participant.setNino("SJ1960589");participant.setDob( dateFormat.parse("1982-12-16"));
		participant.setCreationDate(dateFormat.parse(dateFormat.format(new Date())));participant.setUpdatedDate(dateFormat.parse(dateFormat.format(new Date())));participant.setRecordState(RecordState.CREATED);
		
		// Set Participant details for Full Participant Json View
		participant.setEthnicity(Ethnicity.WHITE_ENGLISH);participant.setExitEmpStatus(ExitEmpStatus.JOBSEEKING);
		participant.setMatch(true);participant.setExOffender(false);participant.setDisabled(false);
		participant.setGender("MALE");participant.setLongTermUnemployed(false);
		participant.setIscedLevel(3);participant.setHomeless(false);
		participant.setHouseholdType(HouseHoldType.JOBLESS_HOUSEHOLD);
		participant.setStartDate(dateFormat.parse(LocalDate.now().plusDays(14).toString()));participant.setProposedExitDate(dateFormat.parse(LocalDate.now().plusDays(74).toString()));participant.setExitDate(dateFormat.parse(LocalDate.now().plusDays(75).toString()));
		participant.setExitTraining(false);participant.setExitSkills(false);participant.setExitQualification(false);participant.setExitChildcare(false);
		participant.setDeliveryPostcode("S1 4GG");participant.setFundingAggrement("funds");
		participant.setCor(Category.TRANSITIONAL);participant.setPriorityAxis(1.1);participant.setExitISCEDLevel(0);
		
		participant.setLabourMarketStatus("C0001");participant.setPostcode("bb87hz");
		participant.setExitlabourMarketStatus("exit labour");participant.setBasicSkills(true);participant.setExitQualificationLevel(0);
		
				
		assertNotNull(participant);
		
		assertThat(participant.getPriorityAxis(), is(1.1));
		assertThat(participant.getPostcode(),is("bb87hz"));
		assertThat(participant.getLabourMarketStatus(),is("C0001"));
		assertThat(participant.getExitlabourMarketStatus(),is("exit labour"));
		assertThat(participant.getExitQualificationLevel(),is(0));
		assertThat(participant.getExitISCEDLevel(),is(0));
		assertThat(participant.getBasicSkills(),is(true));
		
		assertTrue(participant.toString().contains("56dffaa6097d9818d8b455a7"));
		assertFalse(participant.toString().contains("SJ1960589B1"));		
		assertEquals(participant.hashCode(),-48750268);
		
		Participant other = new Participant();
		other.setContractId(4001115);other.setProviderId(1111111);other.setNino("SJ1960589");
		assertTrue(participant.equals(other));
		
		other.setContractId(4001116);
		assertFalse(participant.equals(other));
		other.setContractId(4001115);other.setProviderId(1111112);
		assertFalse(participant.equals(other));
		other.setProviderId(1111111);;other.setNino("SJ1960590");
		assertFalse(participant.equals(other));
	}
	
}
