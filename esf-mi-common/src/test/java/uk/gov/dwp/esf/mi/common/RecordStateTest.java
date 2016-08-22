package uk.gov.dwp.esf.mi.common;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * @Author Phani Krishna
*/

public class RecordStateTest {
	
	@Test
    public void assertRecordStateEnum() {        
        assertThat(RecordState.CREATED, is(notNullValue()));
        assertThat(RecordState.STARTED, is(notNullValue()));
        assertThat(RecordState.ENDED, is(notNullValue()));
        assertThat(RecordState.COMPLETED, is(notNullValue()));
    }

}
