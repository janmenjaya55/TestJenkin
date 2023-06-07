package com.bata.billpunch.model.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class RdcDetailsDto {

	private Long billId;

	private String rdcPairC;

	private Integer rdcPair;

	private String weekYear;

	private String articleCode;

	private String articleName;

	private Double pairAmount;

	private Date receiveDate;

	private String noOfCartons;
	
	private String igst;

	private String cgst;

	private String sgst;

	public Long getBillId() {
		return billId;
	}

	public void setBillId(Long billId) {
		this.billId = billId;
	}

	public String getRdcPairC() {
		return rdcPairC;
	}

	public void setRdcPairC(String rdcPairC) {
		this.rdcPairC = rdcPairC;
	}

	public Integer getRdcPair() {
		return rdcPair;
	}

	public void setRdcPair(Integer rdcPair) {
		this.rdcPair = rdcPair;
	}

	public String getWeekYear() {
		return weekYear;
	}

	public void setWeekYear(String weekYear) {
		this.weekYear = weekYear;
	}

	public String getArticleCode() {
		return articleCode;
	}

	public void setArticleCode(String articleCode) {
		this.articleCode = articleCode;
	}

	public String getArticleName() {
		return articleName;
	}

	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}

	public Double getPairAmount() {
		return pairAmount;
	}

	public void setPairAmount(Double pairAmount) {
		this.pairAmount = pairAmount;
	}

	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	public String getNoOfCartons() {
		return noOfCartons;
	}

	public void setNoOfCartons(String noOfCartons) {
		this.noOfCartons = noOfCartons;
	}

	public String getIgst() {
		return igst;
	}

	public void setIgst(String igst) {
		this.igst = igst;
	}

	public String getCgst() {
		return cgst;
	}

	public void setCgst(String cgst) {
		this.cgst = cgst;
	}

	public String getSgst() {
		return sgst;
	}

	public void setSgst(String sgst) {
		this.sgst = sgst;
	}

	

}
