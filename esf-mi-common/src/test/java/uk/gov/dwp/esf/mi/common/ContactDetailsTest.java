package uk.gov.dwp.esf.mi.common;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

/**
 * @Author Phani Krishna
*/

public class ContactDetailsTest {
	
	@Test
    public void shouldReturnAddressBuilder() {
		assertThat(new ContactDetails.ContactDetailsBuilder(null, null, null),isA(ContactDetails.ContactDetailsBuilder.class));
    }
	
	@Test
    public void shouldBuildADefaultAddress() {
        assertThat(new ContactDetails.ContactDetailsBuilder(null, null, null).build(), isA(ContactDetails.class));
    }
	
	@Test
    public void shouldBuildAnAddressWithBuilder() {
        ContactDetails address = new ContactDetails.ContactDetailsBuilder("Apartment 33","Sheffield","S1 4GG")
                .build();

        assertThat(address.getAddressLine1(), is("Apartment 33"));
        assertThat(address.getAddressLine2(), is("Sheffield"));
        assertThat(address.getPostcode(), is("S1 4GG"));
        
        ContactDetails addressNew = new ContactDetails.ContactDetailsBuilder("Apartment 33","Sheffield","S1 4GG")
				.addressLine4("South Yorkshire").addressLine3("Royal Plaza").addressLine5("Yorkshire").countryCodeID("UK")
                .build();
        
        assertThat(addressNew.getAddressLine1(), is("Apartment 33"));
        assertThat(addressNew.getAddressLine2(), is("Sheffield"));
        assertThat(addressNew.getPostcode(), is("S1 4GG"));
        assertThat(addressNew.getCountryCodeID(), is("UK"));
        assertThat(addressNew.getAddressLine4(), is("South Yorkshire"));
        assertThat(addressNew.getAddressLine5(), is("Yorkshire"));
        assertThat(addressNew.getAddressLine3(), is("Royal Plaza"));
        
        final String value = "ContactDetails [addressLine1=Apartment 33, addressLine2=Sheffield, addressLine3=Royal Plaza, addressLine4=South Yorkshire, addressLine5=Yorkshire, postcode=S1 4GG]";
        assertThat(addressNew.toString(), is(value));
    }
	
	@Test
    public void shouldBuildAnAddress() {
        final ContactDetails address = new ContactDetails();
        address.setAddressLine1("Apartment 33");
        address.setAddressLine2("Sheffield");
        address.setPostcode("S1 4GG");

        assertThat(address.getAddressLine1(), is("Apartment 33"));
        assertThat(address.getAddressLine2(), is("Sheffield"));
        assertThat(address.getPostcode(), is("S1 4GG"));
        
        final ContactDetails addressNew = new ContactDetails();
        addressNew.setAddressLine1("Apartment 33");
        addressNew.setAddressLine2("Sheffield");
        addressNew.setPostcode("S1 4GG");
        addressNew.setCountryCodeID("UK");
        addressNew.setAddressLine4("South Yorkshire");
        addressNew.setAddressLine5("Yorkshire");
        addressNew.setAddressLine3("Royal Plaza");

        
        assertThat(addressNew.getAddressLine1(), is("Apartment 33"));
        assertThat(addressNew.getAddressLine2(), is("Sheffield"));
        assertThat(addressNew.getPostcode(), is("S1 4GG"));
        assertThat(addressNew.getCountryCodeID(), is("UK"));
        assertThat(addressNew.getAddressLine4(), is("South Yorkshire"));
        assertThat(addressNew.getAddressLine3(), is("Royal Plaza"));
        assertThat(addressNew.getAddressLine5(), is("Yorkshire"));
        
        final String value = "ContactDetails [addressLine1=Apartment 33, addressLine2=Sheffield, addressLine3=Royal Plaza, addressLine4=South Yorkshire, addressLine5=Yorkshire, postcode=S1 4GG]";
        assertThat(addressNew.toString(), is(value));
    }
	
}
