package uk.gov.dwp.esf.mi.exceptions;

import java.util.List;

/**
 * @Author Phani Krishna
*/
@SuppressWarnings({"squid:S1165"})
public class PredicateException extends DataException{

	public PredicateException(String code, String message) {
		super(code, message);
	}

	public PredicateException() {
		// Do Nothing
	}

	private static final long serialVersionUID = 1L;
	private List<DataException> exceptions;
	
	public List<DataException> getExceptions() {
		return exceptions;
	}

	public void setExceptions(List<DataException> exceptions) {
		this.exceptions = exceptions;
	}
	

}	
