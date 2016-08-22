package uk.gov.dwp.esf.mi.common;

/**
 * @Author Phani Krishna
*/

public class ContactDetails {
	
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String addressLine4;
	private String addressLine5;
	private String postcode;
	private String countryCodeID;
		
	public ContactDetails(){		
		// Do nothing . Model Mapper class will use it.
	}
	
	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getAddressLine3() {
		return addressLine3;
	}

	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}

	public String getAddressLine4() {
		return addressLine4;
	}

	public void setAddressLine4(String addressLine4) {
		this.addressLine4 = addressLine4;
	}

	public String getAddressLine5() {
		return addressLine5;
	}


	public void setAddressLine5(String addressLine5) {
		this.addressLine5 = addressLine5;
	}

	public String getPostcode() {
		return postcode;
	}


	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}


	public String getCountryCodeID() {
		return countryCodeID;
	}

	public void setCountryCodeID(String countryCodeID) {
		this.countryCodeID = countryCodeID;
	}
	
	@Override
	public String toString() {
		return "ContactDetails [addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2 + ", addressLine3="
				+ addressLine3 + ", addressLine4=" + addressLine4 + ", addressLine5=" + addressLine5 + ", postcode="
				+ postcode + "]";
	}


	private ContactDetails(ContactDetailsBuilder builder){		
		this.addressLine1 = builder.addressLine1;
		this.addressLine2 = builder.addressLine2;
		this.addressLine3 = builder.addressLine3;
		this.addressLine4 = builder.addressLine4;
		this.addressLine5 = builder.addressLine5;
		this.postcode = builder.postcode;
		this.countryCodeID = builder.countryCodeID;
	}
	
	public static class ContactDetailsBuilder{
		
		private final String addressLine1;
		private final String addressLine2;
		private String addressLine3;
		private String addressLine4;
		private String addressLine5;
		private String postcode;
		private String countryCodeID;
		
		public ContactDetailsBuilder(String addressLine1,String addressLine2,String postcode){
			this.addressLine1 = addressLine1;
			this.addressLine2 = addressLine2;
			this.postcode = postcode;
		}
				
		public ContactDetailsBuilder addressLine3(String addressLine3){
			this.addressLine3 = addressLine3;
			return this;
		}
		
		public ContactDetailsBuilder addressLine4(String addressLine4){
			this.addressLine4 = addressLine4;
			return this;
		}
		
		public ContactDetailsBuilder addressLine5(String addressLine5){
			this.addressLine5 = addressLine5;
			return this;
		}
		
		public ContactDetailsBuilder countryCodeID(String countryCodeID){
			this.countryCodeID = countryCodeID;
			return this;
		}
				
		public ContactDetails build() {
			ContactDetails address = new ContactDetails(this);
			return address;
		}
		
	}
	
}
