package com.bata.billpunch.model.dto;

public class BillPunchResponse {

	private String partyCode;

	private String billOrderNo;

	private String status;

	private String billNo;
	
	private String invoiceNO;
	
	private String discountAmt;
	
	private String tcsApplicable;

	private String receiveLoc;
	
	public String getReceiveLoc() {
		return receiveLoc;
	}

	public void setReceiveLoc(String receiveLoc) {
		this.receiveLoc = receiveLoc;
	}

	public String getPartyCode() {
		return partyCode;
	}

	public void setPartyCode(String partyCode) {
		this.partyCode = partyCode;
	}

	public String getBillOrderNo() {
		return billOrderNo;
	}

	public void setBillOrderNo(String billOrderNo) {
		this.billOrderNo = billOrderNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getDiscountAmt() {
		return discountAmt;
	}

	public void setDiscountAmt(String discountAmt) {
		this.discountAmt = discountAmt;
	}

	public String getTcsApplicable() {
		return tcsApplicable;
	}

	public void setTcsApplicable(String tcsApplicable) {
		this.tcsApplicable = tcsApplicable;
	}

	public String getInvoiceNO() {
		return invoiceNO;
	}

	public void setInvoiceNO(String invoiceNO) {
		this.invoiceNO = invoiceNO;
	}
	
	

}
