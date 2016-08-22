package uk.gov.dwp.esf.mi.common;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * @Author Phani Krishna
*/

public class CategoryTest {
	
	@Test
	 public void assertCategoryEnum() {		
		assertThat(Category.LESS_DEVELOPED,is(notNullValue()));
		assertThat(Category.TRANSITIONAL,is(notNullValue()));
		assertThat(Category.MORE_DEVELOPED,is(notNullValue()));
		
	}

}
