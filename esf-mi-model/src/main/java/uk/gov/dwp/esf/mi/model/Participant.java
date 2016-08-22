package uk.gov.dwp.esf.mi.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

/*
 *  @Author : Phani Krishna
 *  @Description : Represents Participant object.All of the participants information
 *  			   is stored in this object.
 *  @Version : 1.0
 *  
 *  We have created Setters for the Participant entity because of the Model Mapper.
 *  The Model mapper maps the DTOs to Entity Objects and Vice Versa.
 *  We would require this as we are not storing the Address and Personal details of Participant in the backend.
 * 
 */

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import uk.gov.dwp.esf.mi.common.Category;
import uk.gov.dwp.esf.mi.common.Ethnicity;
import uk.gov.dwp.esf.mi.common.ExitEmpStatus;
import uk.gov.dwp.esf.mi.common.HouseHoldType;
import uk.gov.dwp.esf.mi.common.RecordState;

@Document(collection="participants")
public class Participant {
	@Id
	private String participantId;
	@NotNull
	private Integer providerId;
	@NotNull
	private String nino;
	@NotNull
	@DateTimeFormat(iso=ISO.DATE)
	private Date dob;	
	private Boolean match;
	private Boolean disabled;
	private Ethnicity ethnicity;
	private String labourMarketStatus;
	private String gender;
	private String postcode;
	private Boolean longTermUnemployed;
	private Boolean basicSkills;
	private Integer iscedLevel;
	private Boolean homeless;
	private Boolean exOffender;
	private HouseHoldType householdType;
	@DateTimeFormat(iso=ISO.DATE)
	private Date creationDate;
	@DateTimeFormat(iso=ISO.DATE)
	private Date updatedDate;
	private RecordState recordState;
	@DateTimeFormat(iso=ISO.DATE)
	private Date startDate;
	@DateTimeFormat(iso=ISO.DATE)
	private Date proposedExitDate;
	@DateTimeFormat(iso=ISO.DATE)
	private Date exitDate;
	@NotNull
	private Integer contractId;
	private ExitEmpStatus exitEmpStatus;
	private Boolean exitTraining;
	private Boolean exitSkills;
	private Boolean exitQualification;
	private Boolean exitChildcare;
	private String deliveryPostcode;
	private String fundingAggrement;
	private Category cor;
	private Double priorityAxis;
	private String exitlabourMarketStatus;
	private Integer exitQualificationLevel;
	private Integer exitISCEDLevel;
	
	public Participant(){		
		// Do Nothing. Model Mapper uses the default constructor
	}	

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
		return "Participant [participantId=" + participantId + ", providerId=" + providerId + ", nino=" + nino
				+ ", dob=" + dob + ", match=" + match + ", disabled=" + disabled + ", ethnicity=" + ethnicity
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

	private Participant(ParticipantBuilder builder) {
		this.participantId = builder.participantId;
		this.providerId = builder.providerId;
		this.nino = builder.nino;
		this.dob = builder.dob;
		this.match = builder.match;
		this.disabled = builder.disabled;
		this.ethnicity = builder.ethnicity;
		this.labourMarketStatus = builder.labourMarketStatus;
		this.gender = builder.gender;
		this.postcode = builder.postcode;
		this.longTermUnemployed = builder.longTermUnemployed;		
		this.basicSkills = builder.basicSkills;
		this.iscedLevel = builder.iscedLevel;
		this.homeless=builder.homeless;
		this.exOffender=builder.exOffender;
		this.householdType = builder.householdType;
		this.creationDate = builder.creationDate;
		this.updatedDate = builder.updatedDate;
		this.recordState = builder.recordState;
		this.startDate = builder.startDate;
		this.proposedExitDate = builder.proposedExitDate;
		this.exitDate = builder.exitDate;
		this.contractId = builder.contractId;
		this.exitEmpStatus = builder.exitEmpStatus;
		this.exitTraining = builder.exitTraining;
		this.exitSkills = builder.exitSkills;
		this.exitQualification = builder.exitQualification;
		this.exitChildcare = builder.exitChildcare;
		this.deliveryPostcode = builder.deliveryPostcode;
		this.fundingAggrement=builder.fundingAggrement;
		this.cor=builder.cor;
		this.priorityAxis=builder.priorityAxis;
		this.exitlabourMarketStatus = builder.exitlabourMarketStatus;
		this.exitQualificationLevel = builder.exitQualificationLevel;		
		this.exitISCEDLevel = builder.exitISCEDLevel;
	}
	
	public static class ParticipantBuilder {
		
		private final String participantId;
		private final Integer providerId;
		private final String nino;
		private final Date dob;
		private final Integer contractId;		
		private String postcode;
		private Boolean longTermUnemployed;
		private Boolean basicSkills;
		private Integer iscedLevel;
		private Boolean homeless;
		private Boolean exOffender;
		private HouseHoldType householdType;
		private Date creationDate;
		private Date updatedDate;
		private Boolean match;
		private Boolean disabled;
		private Ethnicity ethnicity;
		private String labourMarketStatus;
		private String gender;

		private RecordState recordState;
		private Date startDate;
		private Date proposedExitDate;
		private Date exitDate;
		private ExitEmpStatus exitEmpStatus;
		private Boolean exitTraining;
		private Double priorityAxis;
		private String exitlabourMarketStatus;
		private Boolean exitSkills;
		private Boolean exitQualification;
		private Boolean exitChildcare;
		private String deliveryPostcode;
		private String fundingAggrement;
		private Category cor;
		private Integer exitQualificationLevel;
		private Integer exitISCEDLevel;
				
		public ParticipantBuilder(String participantId, Integer providerId, String nino , Date dob , Integer contractId) {
			this.participantId = participantId;
			this.providerId = providerId;
			this.nino = nino;
			this.dob = dob;
			this.contractId = contractId;
		}

		public ParticipantBuilder isMatch(Boolean match) {
			this.match = match;
			return this;
		}
		
		public ParticipantBuilder isDisabled(Boolean disabled) {
			this.disabled = disabled;
			return this;
		}
		
		public ParticipantBuilder ethnicity(Ethnicity ethnicity) {
			this.ethnicity = ethnicity;
			return this;
		}
		
		public ParticipantBuilder labourMarketStatus(String labourMarketStatus){
			this.labourMarketStatus = labourMarketStatus;
			return this;
		}
		
		public ParticipantBuilder gender(String gender) {
			this.gender = gender;
			return this;
		}
		
		public ParticipantBuilder postcode(String postcode){
			this.postcode = postcode;
			return this;
		}
		

		public ParticipantBuilder longTermUnemployed(Boolean longTermUnemployed) {
			this.longTermUnemployed = longTermUnemployed;
			return this;
		}
		
		public ParticipantBuilder basicSkills(Boolean basicSkills) {
			this.basicSkills = basicSkills;
			return this;
		}
						
		public ParticipantBuilder iscedLevel(Integer iscedLevel) {
			this.iscedLevel = iscedLevel;
			return this;
		}
						
		public ParticipantBuilder isHomeless(Boolean homeless) {
			this.homeless = homeless;
			return this;
		}

		public ParticipantBuilder exOffender(Boolean exOffender) {
			this.exOffender = exOffender;
			return this;
		}

		
		public ParticipantBuilder householdType(HouseHoldType householdType) {
			this.householdType = householdType;
			return this;
		}
		
		public ParticipantBuilder creationDate(Date creationDate) {
			this.creationDate = creationDate;
			return this;
		}
		
		public ParticipantBuilder updatedDate(Date updatedDate) {
			this.updatedDate = updatedDate;
			return this;
		}
		
		public ParticipantBuilder recordState(RecordState recordState) {
			this.recordState = recordState;
			return this;
		}
		
		public ParticipantBuilder startDate(Date startDate) {
			this.startDate = startDate;
			return this;
		}
		
		public ParticipantBuilder proposedExitDate(Date proposedExitDate) {
			this.proposedExitDate = proposedExitDate;
			return this;
		}
		
		public ParticipantBuilder exitDate(Date exitDate) {
			this.exitDate = exitDate;
			return this;
		}		
		
		public ParticipantBuilder exitEmpStatus(ExitEmpStatus exitEmpStatus) {
			this.exitEmpStatus = exitEmpStatus;
			return this;
		}
		
		public ParticipantBuilder exitTraining(Boolean exitTraining) {
			this.exitTraining = exitTraining;
			return this;
		}
		
		public ParticipantBuilder exitSkills(Boolean exitSkills) {
			this.exitSkills = exitSkills;
			return this;
		}
		
		public ParticipantBuilder exitQualification(Boolean exitQualification) {
			this.exitQualification = exitQualification;
			return this;
		}
		
		public ParticipantBuilder exitChildcare(Boolean exitChildcare) {
			this.exitChildcare = exitChildcare;
			return this;
		}
		
		public ParticipantBuilder deliveryPostcode(String deliveryPostcode) {
			this.deliveryPostcode = deliveryPostcode;
			return this;
		}
		
		public ParticipantBuilder fundingAggrement(String fundingAggrement) {
			this.fundingAggrement = fundingAggrement;
			return this;
		}
		
		public ParticipantBuilder cor(Category cor) {
			this.cor = cor;
			return this;
		}
		
		public ParticipantBuilder priorityAxis(Double priorityAxis) {
			this.priorityAxis = priorityAxis;
			return this;
		}
		
		public ParticipantBuilder exitlabourMarketStatus(String exitlabourMarketStatus){
			this.exitlabourMarketStatus = exitlabourMarketStatus;
			return this;
		}
		
		public ParticipantBuilder exitQualificationLevel(Integer exitQualificationLevel) {
			this.exitQualificationLevel = exitQualificationLevel;
			return this;
		}
		
		public ParticipantBuilder exitISCEDLevel(Integer exitISCEDLevel) {
			this.exitISCEDLevel = exitISCEDLevel;
			return this;
		}
				
		public Participant build() {
			Participant participant = new Participant(this);
			//call to validation method
			return participant;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contractId == null) ? 0 : contractId.hashCode());
		result = prime * result + ((nino == null) ? 0 : nino.hashCode());
		result = prime * result + ((providerId == null) ? 0 : providerId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;

		Participant other = (Participant) obj;
		if (contractId == null) {
			if (other.contractId == null)
				return false;
		} else if (!contractId.equals(other.contractId))
			return false;
		if (nino == null) {
			if (other.nino == null)
				return false;
		} else if (!nino.equals(other.nino))
			return false;
		if (providerId == null) {
			if (other.providerId == null)
				return false;
		} else if (!providerId.equals(other.providerId))
			return false;
		return true;
	}
	
	
	
	
}