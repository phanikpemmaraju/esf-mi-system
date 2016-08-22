package uk.gov.dwp.esf.mi.exceptions;

/**
 * @Author Phani Krishna
*/
@SuppressWarnings({"squid:S1165"})
public class DataException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private String code;
	private String message;
	
	public DataException(){		
		// Do Nothing
	}
	
	public DataException(String code,String message){
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
