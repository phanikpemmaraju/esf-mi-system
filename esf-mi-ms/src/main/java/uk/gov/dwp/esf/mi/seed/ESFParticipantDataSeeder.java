package uk.gov.dwp.esf.mi.seed;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import uk.gov.dwp.esf.mi.common.Ethnicity;
import uk.gov.dwp.esf.mi.common.RecordState;
import uk.gov.dwp.esf.mi.model.Participant;

/**
 * @Author Phani Krishna
*/

public class ESFParticipantDataSeeder {
	
	private ESFParticipantDataSeeder(){		
	}

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		MongoTemplate mongoTemplate = new MongoTemplate(new MongoClient("localhost"),"esf-mi");

    	final Participant participant1 = new Participant.ParticipantBuilder(null, 1111111, "SL123457B",dateFormat.parse("1983-01-22"),4001111)
    			.creationDate(dateFormat.parse("2015-05-12")).updatedDate(dateFormat.parse(dateFormat.format(new Date()))).recordState(RecordState.CREATED).ethnicity(Ethnicity.WHITE_ENGLISH)
				.build();

    	final Participant participant2 = new Participant.ParticipantBuilder(null, 1111111, "SL123456A",dateFormat.parse("1993-05-27"),4001111)
    			.creationDate(dateFormat.parse("2015-12-16")).updatedDate(dateFormat.parse(dateFormat.format(new Date()))).recordState(RecordState.CREATED).build();

    	final Participant participant3 = new Participant.ParticipantBuilder(null, 1111111, "AB123456A",dateFormat.parse("1990-09-09"),4001113)
    			.creationDate(dateFormat.parse("2015-01-17")).updatedDate(dateFormat.parse(dateFormat.format(new Date()))).recordState(RecordState.CREATED).build();


    	final Participant participant4 = new Participant.ParticipantBuilder(null, 1111111, "AB123458A",dateFormat.parse("1991-07-15"),4001112)
    			.creationDate(dateFormat.parse("2016-01-05")).updatedDate(dateFormat.parse(dateFormat.format(new Date()))).recordState(RecordState.CREATED)
    			.priorityAxis(1.4).build();

		final Participant participant5 = new Participant.ParticipantBuilder(null, 1111111, "AB123459A",dateFormat.parse("1992-08-15"),4001112)
    			.creationDate(dateFormat.parse("2016-01-05")).updatedDate(dateFormat.parse(dateFormat.format(new Date()))).recordState(RecordState.CREATED)
    			.priorityAxis(1.4).build();

		final Participant participant6 = new Participant.ParticipantBuilder(null, 2222222, "AB111111B",dateFormat.parse("1992-08-15"),4444444)
    			.creationDate(dateFormat.parse("2016-01-05")).updatedDate(dateFormat.parse(dateFormat.format(new Date()))).recordState(RecordState.CREATED)
    			.priorityAxis(1.4).build();

        final Participant participant7 = new Participant.ParticipantBuilder(null, 2222222, "AB222222B",dateFormat.parse("1992-10-15"),4444444)
    			.creationDate(dateFormat.parse("2016-01-05")).updatedDate(dateFormat.parse(dateFormat.format(new Date()))).recordState(RecordState.CREATED)
    			.priorityAxis(1.4).build();

        final Participant participant8 = new Participant.ParticipantBuilder(null, 2222222, "AB333333B",dateFormat.parse("1985-08-12"),5555555)
    			.creationDate(dateFormat.parse("2016-02-05")).updatedDate(dateFormat.parse(dateFormat.format(new Date()))).recordState(RecordState.CREATED)
    			.priorityAxis(1.4).build();

        final Participant participant9 = new Participant.ParticipantBuilder(null, 2222222, "AB444444B",dateFormat.parse("1990-07-15"),5555555)
    			.creationDate(dateFormat.parse("2015-02-05")).updatedDate(dateFormat.parse(dateFormat.format(new Date()))).recordState(RecordState.CREATED)
    			.priorityAxis(1.4).build();

        final Participant participant10 = new Participant.ParticipantBuilder(null, 2222222, "AB555555B",dateFormat.parse("1990-07-15"),5555555)
    			.creationDate(dateFormat.parse("2015-01-05")).updatedDate(dateFormat.parse(dateFormat.format(new Date()))).recordState(RecordState.CREATED)
    			.priorityAxis(1.4).build();

    	mongoTemplate.insert(participant1);mongoTemplate.insert(participant2);mongoTemplate.insert(participant3);mongoTemplate.insert(participant4);
		mongoTemplate.insert(participant5);
		mongoTemplate.insert(participant6);
		mongoTemplate.insert(participant7);
		mongoTemplate.insert(participant8);
		mongoTemplate.insert(participant9);
		mongoTemplate.insert(participant10);
	}

}
