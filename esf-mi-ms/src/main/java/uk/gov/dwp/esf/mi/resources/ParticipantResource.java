package uk.gov.dwp.esf.mi.resources;

/*
 *  @Author : Phani Krishna
 *  @Description : ESFParticipantResource uses the Spring HATEOAS Resource Support for
 *  			   providing Hyper Links/Hyper Media formats
 *  @Version : 1.0
 * 
 */

import uk.gov.dwp.esf.mi.dtos.ParticipantDTO;
import uk.gov.dwp.esf.mi.model.Participant;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

@Relation(value="participant", collectionRelation="participants")
public class ParticipantResource extends ResourceSupport {
	
	public ParticipantResource(){	
		// Do Nothing
	}
	
	private ParticipantDTO participant;
	
	public ParticipantResource(ParticipantDTO participant){	
		this.participant = participant;
	}

	public ParticipantDTO getParticipant() {
		return participant;
	}

	public void setParticipant(ParticipantDTO participant) {
		this.participant = participant;
	}

	@Override
	public String toString() {
		return "ParticipantResource [participant=" + participant + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((participant.getContractId() == null) ? 0 : participant.getContractId().hashCode());
		result = prime * result + ((participant.getNino() == null) ? 0 : participant.getNino().hashCode());
		result = prime * result + ((participant.getProviderId() == null) ? 0 : participant.getProviderId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;

		Participant other = (Participant) obj;
		if (participant.getContractId() == null) {
			if (other.getContractId() != null)
				return false;
		} else if (!participant.getContractId().equals(other.getContractId()))
			return false;
		if (participant.getNino() == null) {
			if (other.getNino() != null)
				return false;
		} else if (!participant.getNino().equals(other.getNino()))
			return false;
		if (participant.getProviderId() == null) {
			if (other.getProviderId() != null)
				return false;
		} else if (!participant.getProviderId().equals(other.getProviderId()))
			return false;
		return true;
	}

	
}
