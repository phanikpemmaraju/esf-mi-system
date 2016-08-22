package uk.gov.dwp.esf.mi.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;
import uk.gov.dwp.esf.mi.model.Participant;

/**
 * @Author Phani Krishna
*/

@Repository
public interface ParticipantRepository extends MongoRepository<Participant, String> , QueryDslPredicateExecutor<Participant> {
		
	Page<Participant> findByParticipantId(String participantId,Pageable pageable);
	
	Participant findByParticipantId(String participantId);
	
	Page<Participant> findByContractId(Integer contractId,Pageable pageable);
	
	Page<Participant> findByNino(String nino,Pageable pageable);
	
	List<Participant> findByContractIdAndNino(Integer contractId, String nino);
							
}
