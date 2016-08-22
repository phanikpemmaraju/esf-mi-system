package uk.gov.dwp.esf.mi.dtos;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import uk.gov.dwp.esf.mi.common.ContactDetails;
import uk.gov.dwp.esf.mi.common.Category;
import uk.gov.dwp.esf.mi.common.Ethnicity;
import uk.gov.dwp.esf.mi.common.ExitEmpStatus;
import uk.gov.dwp.esf.mi.common.HouseHoldType;
import uk.gov.dwp.esf.mi.common.RecordState;
import uk.gov.dwp.esf.mi.common.View;

/**
 * @Author Phani Krishna
*/

public class ParticipantDTO {	
	@JsonView(View.SummaryWithBasicParticipants.class)
	private String participantId;
	@NotNull(message="error.providerId.null")
	@JsonView(View.SummaryWithBasicParticipants.class)
	private Integer providerId;
	@JsonView(View.SummaryWithFullParticipants.class)
	private ContactDetails contactDetails;
	@NotNull(message="error.nino.null")
	@Pattern(regexp="^[A-Z]{2}[0-9]{6}[A-D]{1}$",message="error.invalid.nino.format")
	@JsonView(View.SummaryWithBasicParticipants.class)
	private String nino;
	@NotNull(message="error.dob.null")
	@JsonView(View.SummaryWithBasicParticipants.class)
	@JsonDeserialize(using = CustomDateDeSerializer.class)
	@JsonSerialize(using = CustomDateSerializer.class)
	private Date dob;
	@JsonView(View.SummaryWithFullParticipants.class)
	private String title;
	@JsonView(View.SummaryWithFullParticipants.class)
	private String forename; 
	@JsonView(View.SummaryWithFullParticipants.class)
	private String surname;
	@JsonView(View.SummaryWithFullParticipants.class)
	private Boolean match;
	@JsonView(View.SummaryWithFullParticipants.class)
	private Boolean disabled;
	@JsonView(View.SummaryWithFullParticipants.class)
	private Ethnicity ethnicity;
	@JsonView(View.SummaryWithFullParticipants.class)
	private String labourMarketStatus;
	@JsonView(View.SummaryWithFullParticipants.class)
	private String gender;
	@JsonView(View.SummaryWithBasicParticipants.class)
	private String postcode;	
	@JsonView(View.SummaryWithFullParticipants.class)
	private Boolean longTermUnemployed;	
	@JsonView(View.SummaryWithFullParticipants.class)
	private Boolean basicSkills;
	@JsonView(View.SummaryWithFullParticipants.class)
	private Integer iscedLevel;
	@JsonView(View.SummaryWithFullParticipants.class)
	private Boolean homeless;
	@JsonView(View.SummaryWithFullParticipants.class)
	private Boolean exOffender;
	@JsonView(View.SummaryWithFullParticipants.class)
	private HouseHoldType householdType;
	@JsonView(View.SummaryWithBasicParticipants.class)
	@JsonDeserialize(using = CustomDateDeSerializer.class)
	@JsonSerialize(using = CustomDateSerializer.class)
	private Date creationDate;
	@JsonView(View.SummaryWithBasicParticipants.class)
	@JsonDeserialize(using = CustomDateDeSerializer.class)
	@JsonSerialize(using = CustomDateSerializer.class)
	private Date updatedDate;
	@JsonView(View.SummaryWithBasicParticipants.class)
	private RecordState recordState;
	@JsonView(View.SummaryWithFullParticipants.class)
	@JsonDeserialize(using = CustomDateDeSerializer.class)
	@JsonSerialize(using = CustomDateSerializer.class)
	private Date startDate;
	@JsonView(View.SummaryWithFullParticipants.class)
	@JsonDeserialize(using = CustomDateDeSerializer.class)
	@JsonSerialize(using = CustomDateSerializer.class)
	private Date proposedExitDate;
	@JsonView(View.SummaryWithFullParticipants.class)
	@JsonDeserialize(using = CustomDateDeSerializer.class)
	@JsonSerialize(using = CustomDateSerializer.class)
	private Date exitDate;
	@NotNull(message="error.contractId.null")
	@JsonView(View.SummaryWithBasicParticipants.class)
	private Integer contractId;
	@JsonView(View.SummaryWithFullParticipants.class)
	private ExitEmpStatus exitEmpStatus;
	@JsonView(View.SummaryWithFullParticipants.class)
	private Boolean exitTraining;
	@JsonView(View.SummaryWithFullParticipants.class)
	private Boolean exitSkills;
	@JsonView(View.SummaryWithFullParticipants.class)
	private Boolean exitQualification;
	@JsonView(View.SummaryWithFullParticipants.class)
	private Boolean exitChildcare;
	@JsonView(View.SummaryWithFullParticipants.class)
	private String deliveryPostcode;
	@JsonView(View.SummaryWithFullParticipants.class)
	private String fundingAggrement;
	@JsonView(View.SummaryWithFullParticipants.class)
	private Category cor;
	@JsonView(View.SummaryWithFullParticipants.class)
	private Double priorityAxis;
	@JsonView(View.SummaryWithFullParticipants.class)
	private String exitlabourMarketStatus;
	@JsonView(View.SummaryWithFullParticipants.class)
	private Integer exitQualificationLevel;
	@JsonView(View.SummaryWithFullParticipants.class)
	private Integer exitISCEDLevel;		

	public String getParticipantId() {
		return participantId;
	}

	public void setParticipantId(String participantId) {
		this.participantId = participantId;
	}

	public Integer getProviderId() {
		return providerId;
	}

	public void setProviderId(Integer providerId) {
		this.providerId = providerId;
	}

	public ContactDetails getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(ContactDetails contactDetails) {
		this.contactDetails = contactDetails;
	}

	public String getNino() {
		return nino;
	}

	public void setNino(String nino) {
		this.nino = nino;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getForename() {
		return forename;
	}

	public void setForename(String forename) {
		this.forename = forename;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Boolean getMatch() {
		return match;
	}

	public void setMatch(Boolean match) {
		this.match = match;
	}

	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	public Ethnicity getEthnicity() {
		return ethnicity;
	}

	public void setEthnicity(Ethnicity ethnicity) {
		this.ethnicity = ethnicity;
	}

	public String getLabourMarketStatus() {
		return labourMarketStatus;
	}

	public void setLabourMarketStatus(String labourMarketStatus) {
		this.labourMarketStatus = labourMarketStatus;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public Boolean getLongTermUnemployed() {
		return longTermUnemployed;
	}

	public void setLongTermUnemployed(Boolean longTermUnemployed) {
		this.longTermUnemployed = longTermUnemployed;
	}

	public Boolean getBasicSkills() {
		return basicSkills;
	}

	public void setBasicSkills(Boolean basicSkills) {
		this.basicSkills = basicSkills;
	}

	public Integer getIscedLevel() {
		return iscedLevel;
	}

	public void setIscedLevel(Integer iscedLevel) {
		this.iscedLevel = iscedLevel;
	}

	public Boolean getHomeless() {
		return homeless;
	}

	public void setHomeless(Boolean homeless) {
		this.homeless = homeless;
	}	
	
	public Boolean getExOffender() {
		return exOffender;
	}

	public void setExOffender(Boolean exOffender) {
		this.exOffender = exOffender;
	}

	public HouseHoldType getHouseholdType() {
		return householdType;
	}

	public void setHouseholdType(HouseHoldType householdType) {
		this.householdType = householdType;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public RecordState getRecordState() {
		return recordState;
	}

	public void setRecordState(RecordState recordState) {
		this.recordState = recordState;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getProposedExitDate() {
		return proposedExitDate;
	}
	
	public void setProposedExitDate(Date proposedExitDate) {
		this.proposedExitDate = proposedExitDate;
	}

	public Date getExitDate() {
		return exitDate;
	}
	
	public void setExitDate(Date exitDate) {
		this.exitDate = exitDate;
	}

	public Integer getContractId() {
		return contractId;
	}

	public void setContractId(Integer contractId) {
		this.contractId = contractId;
	}

	public ExitEmpStatus getExitEmpStatus() {
		return exitEmpStatus;
	}

	public void setExitEmpStatus(ExitEmpStatus exitEmpStatus) {
		this.exitEmpStatus = exitEmpStatus;
	}

	public Boolean getExitTraining() {
		return exitTraining;
	}

	public void setExitTraining(Boolean exitTraining) {
		this.exitTraining = exitTraining;
	}

	public Boolean getExitSkills() {
		return exitSkills;
	}

	public void setExitSkills(Boolean exitSkills) {
		this.exitSkills = exitSkills;
	}

	public Boolean getExitQualification() {
		return exitQualification;
	}

	public void setExitQualification(Boolean exitQualification) {
		this.exitQualification = exitQualification;
	}

	public Boolean getExitChildcare() {
		return exitChildcare;
	}

	public void setExitChildcare(Boolean exitChildcare) {
		this.exitChildcare = exitChildcare;
	}

	public String getDeliveryPostcode() {
		return deliveryPostcode;
	}


	public void setDeliveryPostcode(String deliveryPostcode) {
		this.deliveryPostcode = deliveryPostcode;
	}

	public String getFundingAggrement() {
		return fundingAggrement;
	}

	public void setFundingAggrement(String fundingAggrement) {
		this.fundingAggrement = fundingAggrement;
	}


	public Category getCor() {
		return cor;
	}

	public void setCor(Category cor) {
		this.cor = cor;
	}

	public Double getPriorityAxis() {
		return priorityAxis;
	}


	public void setPriorityAxis(Double priorityAxis) {
		this.priorityAxis = priorityAxis;
	}

	public String getExitlabourMarketStatus() {
		return exitlabourMarketStatus;
	}

	public void setExitlabourMarketStatus(String exitlabourMarketStatus) {
		this.exitlabourMarketStatus = exitlabourMarketStatus;
	}

	public Integer getExitQualificationLevel() {
		return exitQualificationLevel;
	}

	public void setExitQualificationLevel(Integer exitQualificationLevel) {
		this.exitQualificationLevel = exitQualificationLevel;
	}

	public Integer getExitISCEDLevel() {
		return exitISCEDLevel;
	}

	public void setExitISCEDLevel(Integer exitISCEDLevel) {
		this.exitISCEDLevel = exitISCEDLevel;
	}

	@Override
	public String toString() {
		return "ParticipantDTO [participantId=" + participantId + ", providerId=" + providerId + ", contactDetails="
				+ contactDetails + ", nino=" + nino + ", dob=" + dob + ", title=" + title + ", forename=" + forename
				+ ", surname=" + surname + ", match=" + match + ", disabled=" + disabled + ", ethnicity=" + ethnicity
				+ ", labourMarketStatus=" + labourMarketStatus + ", gender=" + gender + ", postcode=" + postcode
				+ ", longTermUnemployed=" + longTermUnemployed + ", basicSkills=" + basicSkills + ", iscedLevel="
				+ iscedLevel + ", homeless=" + homeless + ", exOffender=" + exOffender + ", householdType="
				+ householdType + ", creationDate=" + creationDate + ", updatedDate=" + updatedDate + ", recordState="
				+ recordState + ", startDate=" + startDate + ", proposedExitDate=" + proposedExitDate + ", exitDate="
				+ exitDate + ", contractId=" + contractId + ", exitEmpStatus=" + exitEmpStatus + ", exitTraining="
				+ exitTraining + ", exitSkills=" + exitSkills + ", exitQualification=" + exitQualification
				+ ", exitChildcare=" + exitChildcare + ", deliveryPostcode=" + deliveryPostcode + ", fundingAggrement="
				+ fundingAggrement + ", cor=" + cor + ", priorityAxis=" + priorityAxis + ", exitlabourMarketStatus="
				+ exitlabourMarketStatus + ", exitQualificationLevel=" + exitQualificationLevel + ", exitISCEDLevel="
				+ exitISCEDLevel + "]";
	}
	
	
	
}