package uk.gov.dwp.esf.mi.commons;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import com.mysema.query.types.expr.BooleanExpression;
import com.mysema.query.types.path.BooleanPath;
import com.mysema.query.types.path.DateTimePath;
import uk.gov.dwp.esf.mi.model.Participant;

/**
 * @Author Phani Krishna
*/

public class ParticipantPredicateTest {
	
	private ParticipantPredicate predicate;
	
	@Before
	public void setUp(){
		predicate = new ParticipantPredicate();
	}
	
	@Test
	public void testGetPredicateForNumberExpressions(){
		assertNull(predicate.getCriteria());
		SearchCriteria criteria = new SearchCriteria("dummyId","=","1234,4567");
		assertNotNull(criteria.getOperation()); predicate.setCriteria(criteria);
		
		BooleanExpression expression = predicate.getPredicate();
		assertNotNull(expression); assertEquals("failure - expected are actual are not equal", expression.toString() , "participant.dummyId = 1234 || participant.dummyId = 4567");
		
		criteria = new SearchCriteria("dummyId","!=","1234"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria); expression = predicate.getPredicate();
		assertNotNull(expression); assertEquals("failure - expected are actual are not equal", expression.toString() , "participant.dummyId != 1234");
		
		criteria = new SearchCriteria("dummyId","!!=","1234"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria); expression = predicate.getPredicate();
		assertNull(expression);		
		
		criteria = new SearchCriteria("dummyId","!=","1234,4567"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria); expression = predicate.getPredicate();
		assertNotNull(expression); assertEquals("failure - expected are actual are not equal", expression.toString() , "participant.dummyId != 1234 && participant.dummyId != 4567");
		
		criteria = new SearchCriteria("dummyId","<>","1234,4567"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria); expression = predicate.getPredicate();
		assertNotNull(expression);
				
		criteria = new SearchCriteria("providerId",">","1111111"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria); expression = predicate.getPredicate();
		assertNotNull(expression); assertEquals("failure - expected are actual are not equal", expression.toString() , "participant.providerId > 1111111");
		
		criteria = new SearchCriteria("providerId",">=","1111111"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria); expression = predicate.getPredicate();
		assertNotNull(expression); assertEquals("failure - expected are actual are not equal", expression.toString() , "participant.providerId >= 1111111");

		criteria = new SearchCriteria("providerId","_gt","1111111"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria); expression = predicate.getPredicate();
		assertNotNull(expression); assertEquals("failure - expected are actual are not equal", expression.toString() , "participant.providerId > 1111111");
		
		criteria = new SearchCriteria("providerId","_gt=","1111111"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria); expression = predicate.getPredicate();
		assertNotNull(expression); assertEquals("failure - expected are actual are not equal", expression.toString() , "participant.providerId >= 1111111");
		
		criteria = new SearchCriteria("providerId","<","1111111"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria); expression = predicate.getPredicate();
		assertNotNull(expression); assertEquals("failure - expected are actual are not equal", expression.toString() , "participant.providerId < 1111111");

		criteria = new SearchCriteria("providerId","<=","1111111"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria); expression = predicate.getPredicate();
		assertNotNull(expression); assertEquals("failure - expected are actual are not equal", expression.toString() , "participant.providerId <= 1111111");

		criteria = new SearchCriteria("providerId","_lt","1111111"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria); assertNotNull(criteria.getOperation()); expression = predicate.getPredicate();
		assertNotNull(expression); assertEquals("failure - expected are actual are not equal", expression.toString() , "participant.providerId < 1111111");
		
		criteria = new SearchCriteria("providerId","_lt=","1111111"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria); expression = predicate.getPredicate();
		assertNotNull(expression); assertEquals("failure - expected are actual are not equal", expression.toString() , "participant.providerId <= 1111111");
		
		
		criteria = new SearchCriteria("providerId","_in=","1111111,1111112"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria); expression = predicate.getPredicate();
		assertNotNull(expression); assertEquals("failure - expected are actual are not equal", expression.toString() , "participant.providerId = 1111111 || participant.providerId = 1111112");
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void testGetPredicateForDateTimeExpressions(){
		
		SearchCriteria criteria =  new SearchCriteria("creationDate","_between=","1991-01-12:2011-11-04");
		assertNotNull(criteria.getOperation()); predicate.setCriteria(criteria);
		BooleanExpression expression = predicate.getPredicate();expression = predicate.getPredicate();assertNotNull(expression);
		predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria); expression = predicate.getPredicate();
		assertNotNull(expression); assertNotNull(predicate.toString());
		
		criteria = new SearchCriteria("creationDate","=","1991-01-12"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria); expression = predicate.getPredicate();
		assertNotNull(expression); assertNotNull(predicate.toString());
		
		criteria = new SearchCriteria("creationDate","!=","1991-01-12"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria); expression = predicate.getPredicate();
		assertNotNull(expression); assertNotNull(predicate.toString());
		
		criteria = new SearchCriteria("creationDate","!=","1991-01-12,1985-11-05"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria); expression = predicate.getPredicate();
		assertNotNull(expression); assertNotNull(predicate.toString());
		
		criteria = new SearchCriteria("creationDate",">","1991-01-12"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria); expression = predicate.getPredicate();
		assertNotNull(expression); assertNotNull(predicate.toString());
		
		criteria = new SearchCriteria("creationDate","<","1991-01-12"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria); expression = predicate.getPredicate();
		assertNotNull(expression); assertNotNull(predicate.toString());
		
		criteria = new SearchCriteria("creationDate",">=","1991-01-12"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria); expression = predicate.getPredicate();
		assertNotNull(expression); assertNotNull(predicate.toString());
		
		criteria = new SearchCriteria("creationDate","<=","1991-01-12"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria); expression = predicate.getPredicate();
		assertNotNull(expression); assertNotNull(predicate.toString());
		
		criteria = new SearchCriteria("creationDate","_lt=","1991-01-12"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria); expression = predicate.getPredicate();
		assertNotNull(expression); assertNotNull(predicate.toString());
		
		criteria = new SearchCriteria("creationDate","_lt","1991-01-12"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria); expression = predicate.getPredicate();
		assertNotNull(expression); assertNotNull(predicate.toString());
		
		criteria = new SearchCriteria("creationDate","_gt=","1991-01-12"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria); expression = predicate.getPredicate();
		assertNotNull(expression); assertNotNull(predicate.toString());
		
		criteria = new SearchCriteria("creationDate","_gt","1991-01-12"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria); expression = predicate.getPredicate();
		assertNotNull(expression); assertNotNull(predicate.toString());
		
		criteria = new SearchCriteria("creationDate","!=","1991-01-12,"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria); expression = predicate.getPredicate();
		assertNotNull(expression); assertNotNull(predicate.toString());
		
		criteria = new SearchCriteria("creationDate","<>","1991-01-12,"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria); expression = predicate.getPredicate();
		assertNotNull(expression); assertNotNull(predicate.toString());
		
		criteria = new SearchCriteria("creationDate","%","1991-01-12"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria); expression = predicate.getPredicate();
		assertNull(expression); 
		
		criteria = new SearchCriteria("creationDate","=","01/12/1991"); predicate = new ParticipantPredicate();
		assertNull(predicate.dateExpressions("01/12/1991", criteria, new DateTimePath(Participant.class,"participant")));
				
		criteria = new SearchCriteria("creationDate","_in=","1991-01-12,1992-10-22"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria); expression = predicate.getPredicate();
		assertNotNull(expression); assertNotNull(criteria.getOperation()); assertNotNull(predicate.toString());
		assertEquals("failure - expected are actual are not equal", expression.toString() , "participant.creationDate = Sat Jan 12 00:00:00 GMT 1991 || participant.creationDate = Thu Oct 22 00:00:00 BST 1992");		
		
		criteria = new SearchCriteria("creationDate","!=","1991-01-12,1992-10-22"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria); expression = predicate.getPredicate();
		assertNotNull(expression); assertNotNull(criteria.getOperation()); assertNotNull(predicate.toString());
		assertEquals("failure - expected are actual are not equal", expression.toString() , "participant.creationDate != Sat Jan 12 00:00:00 GMT 1991 && participant.creationDate != Thu Oct 22 00:00:00 BST 1992");
		
		criteria = new SearchCriteria("creationDate","<>","1991-01-12,1992-10-22"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria); expression = predicate.getPredicate();
		assertNotNull(expression); assertNotNull(criteria.getOperation()); assertNotNull(predicate.toString());
		assertEquals("failure - expected are actual are not equal", expression.toString() , "participant.creationDate != Sat Jan 12 00:00:00 GMT 1991 && participant.creationDate != Thu Oct 22 00:00:00 BST 1992");
	}
	
	@Test
	public void testGetPredicateForBooleanExpressions(){
		SearchCriteria criteria = new SearchCriteria("gender","=","null"); assertNotNull(criteria.getOperation()); predicate.setCriteria(criteria);
		assertNotNull(criteria.getOperation());  assertNotNull(predicate.booleanExpressions(Boolean.TRUE, criteria, new BooleanPath("participant")));		
		BooleanExpression expression = predicate.getPredicate();assertNotNull(expression);
		predicate = new ParticipantPredicate();
		
		assertEquals("failure - expected are actual are not equal", expression.toString() , "participant.gender is null || empty(participant.gender)");
		
		criteria = new SearchCriteria("disabled","=","true"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria); assertNotNull(criteria.getOperation()); expression = predicate.getPredicate();
		assertNotNull(expression); assertEquals("failure - expected are actual are not equal", expression.toString() , "participant.disabled = true");
				
		criteria = new SearchCriteria("disabled","=","true,"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria); expression = predicate.getPredicate();
		assertNotNull(expression); assertEquals("failure - expected are actual are not equal", expression.toString() , "participant.disabled = true");
		
		criteria = new SearchCriteria("disabled","=","false,"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria); expression = predicate.getPredicate();
		assertNotNull(expression); assertEquals("failure - expected are actual are not equal", expression.toString() , "participant.disabled = false");

		criteria = new SearchCriteria("disabled","=","TRUE,true"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria); expression = predicate.getPredicate();
		assertNotNull(expression); assertEquals("failure - expected are actual are not equal", expression.toString() , "participant.disabled = true");
		
		criteria = new SearchCriteria("disabled","=","false"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria); expression = predicate.getPredicate();
		assertNotNull(expression); assertEquals("failure - expected are actual are not equal", expression.toString() , "participant.disabled = false");
		
		criteria = new SearchCriteria("disabled","=","true,false"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria); expression = predicate.getPredicate();
		assertNotNull(expression); assertEquals("failure - expected are actual are not equal", expression.toString() , "participant.disabled = false");
				
		criteria = new SearchCriteria("disabled","=","FALSE"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria); expression = predicate.getPredicate();
		assertNotNull(expression); assertEquals("failure - expected are actual are not equal", expression.toString() , "participant.disabled = FALSE");			
		
		criteria = new SearchCriteria("disabled","!=","true"); predicate = new ParticipantPredicate();
		assertNull(predicate.booleanExpressions(Boolean.TRUE, criteria, new BooleanPath("participant")));
		
		
	}
	
	@Test
	public void testGetPredicateForStringExpressions(){		
		SearchCriteria criteria = new SearchCriteria("participantId","!=","569e0fed07d46fd2f7fc525a,569e0fed07d46fd2f7fc525b");
		assertNotNull(criteria.getOperation());
		predicate.setCriteria(criteria);
		assertNotNull(criteria.getOperation()); 
		BooleanExpression expression = predicate.getPredicate();expression = predicate.getPredicate();assertNotNull(expression);
		predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria); expression = predicate.getPredicate();
		assertNotNull(expression); assertEquals("failure - expected are actual are not equal", expression.toString() , "participant.participantId != 569e0fed07d46fd2f7fc525a && participant.participantId != 569e0fed07d46fd2f7fc525b");
				
		criteria = new SearchCriteria("participantId","<>","569e0fed07d46fd2f7fc525a,569e0fed07d46fd2f7fc525b"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria); expression = predicate.getPredicate();
		assertNotNull(expression);
		
		criteria = new SearchCriteria("participantId","!=","569e0fed07d46fd2f7fc525a"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria); expression = predicate.getPredicate();
		assertNotNull(expression); assertEquals("failure - expected are actual are not equal", expression.toString() , "participant.participantId != 569e0fed07d46fd2f7fc525a");
		
		criteria = new SearchCriteria("gender","!=","null"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria); expression = predicate.getPredicate();
		assertNotNull(expression); assertEquals("failure - expected are actual are not equal", expression.toString() , "participant.gender is not null && !empty(participant.gender)");

		criteria = new SearchCriteria("gender","<>","null"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria); expression = predicate.getPredicate();
		assertNotNull(expression); assertEquals("failure - expected are actual are not equal", expression.toString() , "participant.gender is not null && !empty(participant.gender)");
		
		criteria = new SearchCriteria("gender","_in=","MALE,FEMALE"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria); expression = predicate.getPredicate();
		assertNotNull(expression); assertEquals("failure - expected are actual are not equal", expression.toString() , "participant.gender = MALE || participant.gender = FEMALE");
		
		criteria = new SearchCriteria("gender","_any=","MALE,FEMALE"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria);
		expression = predicate.getPredicate();
		assertNull(expression);
		
		criteria = new SearchCriteria("gender","!=","MALE"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria); expression = predicate.getPredicate();
		assertNotNull(expression); assertEquals("failure - expected are actual are not equal", expression.toString() , "participant.gender != MALE");
		
		criteria = new SearchCriteria("gender","<>","MALE"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria); expression = predicate.getPredicate();
		assertNotNull(expression); assertEquals("failure - expected are actual are not equal", expression.toString() , "participant.gender != MALE");

		criteria = new SearchCriteria("fundingAggrement","_like=","funds"); predicate = new ParticipantPredicate();
		predicate.setCriteria(criteria); expression = predicate.getPredicate();
		assertNotNull(expression); assertEquals("failure - expected are actual are not equal", expression.toString() , "contains(participant.fundingAggrement,funds)");

	}
	
}
