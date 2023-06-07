package com.bata.billpunch.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity(name = "BillPunchTransReportModel")
@Table(name = "TL_BILL_TRANS_REPORT_DTLS")
public class BillPunchTransReportModel implements Serializable {

	private static final long serialVersionUID = 13332225667L;

	@Id
	@Column(name = "TRANS_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transId;

	@Column(name = "WEEK")
	private String week;

	@Column(name = "YEAR")
	private String year;

	@Column(name = "INVOICE_NO")
	private String invoiceNo;

	@Column(name = "ARTICLE_CODE")
	private String articleCode;

	@Column(name = "SIZE_CODE")
	private String sizeCode;

	@Column(name = "RDC_CODE")
	private String rdcCode;

	@Column(name = "DIST_NO")
	private String distNo;

	@Column(name = "PAIR")
	private String pair;

	@Column(name = "TOTAL_PAIR")
	private String totalPair;

	@Column(name = "PACKCASE_B")
	private String packCaseb;

	@Column(name = "PACKCASE_M")
	private String packCasem;

	@Column(name = "PACKCASE_S")
	private String packCases;

	@Column(name = "PACKCASE_C")
	private String packCasec;

	@Column(name = "PACKCASE_T")
	private String packCaset;

	@Column(name = "DC_CODE")
	private String dcCode;

	@Column(name = "ART_SEQUENCE")
	private String artSequence;

	@Column(name = "PARTY_CODE")
	private String partyCode;

	@Column(name = "CN_NO")
	private String cnNo;

	@Column(name = "CN_DATE")
	private String cnDate;

	@Column(name = "TRANSPORT_CODE")
	private String transportCode;

	@Column(name = "PERMIT_NO")
	private String permitNo;

	@Column(name = "STATE_CODE")
	private String stateCode;

	@Column(name = "ANV_AMOUNT")
	private String invAmt;

	@Column(name = "GR_NO")
	private String grNo;

	@Column(name = "REC_DATE")
	private String recDate;

	@Column(name = "RESUME_INV_NO")
	private String resumeInvNO;

	@Column(name = "DATE_ONE")
	private String dateOne;

	@Column(name = "DATE_TWO")
	private String dateTwo;

	@Column(name = "DATE_THREE")
	private String dateThree;

	@Column(name = "RESUME_INV_NO_TWO")
	private String resumeInvNOTwo;

	@Column(name = "BILL_ORD_NO")
	private String billOrdNo;

	@Column(name = "BILL_ORD_NO_ONE")
	private String billOrdNoOne;

	@Column(name = "ART_COST")
	private String artCost;

	@Column(name = "CGST")
	private String cgst;

	@Column(name = "SGST")
	private String sgst;

	@Column(name = "IGST")
	private String igst;

	@Column(name = "CGST_AMT")
	private String cgstamt;

	@Column(name = "SGST_AMT")
	private String sgstamt;

	@Column(name = "IGST_AMT")
	private String igstamt;

	//@Basic
	//@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_ON")
	private Calendar createdOn;
	

	public Calendar getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Calendar createdOn) {
		this.createdOn = createdOn;
	}

	public Long getTransId() {
		return transId;
	}

	public void setTransId(Long transId) {
		this.transId = transId;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getArticleCode() {
		return articleCode;
	}

	public void setArticleCode(String articleCode) {
		this.articleCode = articleCode;
	}

	public String getSizeCode() {
		return sizeCode;
	}

	public void setSizeCode(String sizeCode) {
		this.sizeCode = sizeCode;
	}

	public String getRdcCode() {
		return rdcCode;
	}

	public void setRdcCode(String rdcCode) {
		this.rdcCode = rdcCode;
	}

	public String getDistNo() {
		return distNo;
	}

	public void setDistNo(String distNo) {
		this.distNo = distNo;
	}

	public String getPair() {
		return pair;
	}

	public void setPair(String pair) {
		this.pair = pair;
	}

	public String getTotalPair() {
		return totalPair;
	}

	public void setTotalPair(String totalPair) {
		this.totalPair = totalPair;
	}

	public String getPackCaseb() {
		return packCaseb;
	}

	public void setPackCaseb(String packCaseb) {
		this.packCaseb = packCaseb;
	}

	public String getPackCasem() {
		return packCasem;
	}

	public void setPackCasem(String packCasem) {
		this.packCasem = packCasem;
	}

	public String getPackCases() {
		return packCases;
	}

	public void setPackCases(String packCases) {
		this.packCases = packCases;
	}

	public String getPackCasec() {
		return packCasec;
	}

	public void setPackCasec(String packCasec) {
		this.packCasec = packCasec;
	}

	public String getPackCaset() {
		return packCaset;
	}

	public void setPackCaset(String packCaset) {
		this.packCaset = packCaset;
	}

	public String getDcCode() {
		return dcCode;
	}

	public void setDcCode(String dcCode) {
		this.dcCode = dcCode;
	}

	public String getArtSequence() {
		return artSequence;
	}

	public void setArtSequence(String artSequence) {
		this.artSequence = artSequence;
	}

	public String getPartyCode() {
		return partyCode;
	}

	public void setPartyCode(String partyCode) {
		this.partyCode = partyCode;
	}

	public String getCnNo() {
		return cnNo;
	}

	public void setCnNo(String cnNo) {
		this.cnNo = cnNo;
	}

	public String getCnDate() {
		return cnDate;
	}

	public void setCnDate(String cnDate) {
		this.cnDate = cnDate;
	}

	public String getTransportCode() {
		return transportCode;
	}

	public void setTransportCode(String transportCode) {
		this.transportCode = transportCode;
	}

	public String getPermitNo() {
		return permitNo;
	}

	public void setPermitNo(String permitNo) {
		this.permitNo = permitNo;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getInvAmt() {
		return invAmt;
	}

	public void setInvAmt(String invAmt) {
		this.invAmt = invAmt;
	}

	public String getGrNo() {
		return grNo;
	}

	public void setGrNo(String grNo) {
		this.grNo = grNo;
	}

	public String getRecDate() {
		return recDate;
	}

	public void setRecDate(String recDate) {
		this.recDate = recDate;
	}

	public String getResumeInvNO() {
		return resumeInvNO;
	}

	public void setResumeInvNO(String resumeInvNO) {
		this.resumeInvNO = resumeInvNO;
	}

	public String getDateOne() {
		return dateOne;
	}

	public void setDateOne(String dateOne) {
		this.dateOne = dateOne;
	}

	public String getDateTwo() {
		return dateTwo;
	}

	public void setDateTwo(String dateTwo) {
		this.dateTwo = dateTwo;
	}

	public String getDateThree() {
		return dateThree;
	}

	public void setDateThree(String dateThree) {
		this.dateThree = dateThree;
	}

	public String getResumeInvNOTwo() {
		return resumeInvNOTwo;
	}

	public void setResumeInvNOTwo(String resumeInvNOTwo) {
		this.resumeInvNOTwo = resumeInvNOTwo;
	}

	public String getBillOrdNo() {
		return billOrdNo;
	}

	public void setBillOrdNo(String billOrdNo) {
		this.billOrdNo = billOrdNo;
	}

	public String getBillOrdNoOne() {
		return billOrdNoOne;
	}

	public void setBillOrdNoOne(String billOrdNoOne) {
		this.billOrdNoOne = billOrdNoOne;
	}

	public String getArtCost() {
		return artCost;
	}

	public void setArtCost(String artCost) {
		this.artCost = artCost;
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

	public String getIgst() {
		return igst;
	}

	public void setIgst(String igst) {
		this.igst = igst;
	}

	public String getCgstamt() {
		return cgstamt;
	}

	public void setCgstamt(String cgstamt) {
		this.cgstamt = cgstamt;
	}

	public String getSgstamt() {
		return sgstamt;
	}

	public void setSgstamt(String sgstamt) {
		this.sgstamt = sgstamt;
	}

	public String getIgstamt() {
		return igstamt;
	}

	public void setIgstamt(String igstamt) {
		this.igstamt = igstamt;
	}

}
