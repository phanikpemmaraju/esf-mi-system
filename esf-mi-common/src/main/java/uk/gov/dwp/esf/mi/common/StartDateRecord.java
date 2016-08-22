package uk.gov.dwp.esf.mi.common;

import java.util.Date;

/**
 * @Author Phani Krishna
*/

public class StartDateRecord {
	
	private String nino;
	private Integer contractId;
	private Date startDate;
	private String status;
	
	public StartDateRecord(){		
		// Do Nothing.
	}
	
	public StartDateRecord(String nino,Integer contractId,Date startDate){
		this.nino = nino;
		this.contractId = contractId;
		this.startDate = startDate;		
	}
	
	public String getNino() {
		return nino;
	}
	public void setNino(String nino) {
		this.nino = nino;
	}
	public Integer getContractId() {
		return contractId;
	}
	public void setContractId(Integer contractId) {
		this.contractId = contractId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "StartDateRecord [nino=" + nino + ", contractId=" + contractId + ", startDate=" + startDate + ", status="
				+ status + "]";
	}
	
}
