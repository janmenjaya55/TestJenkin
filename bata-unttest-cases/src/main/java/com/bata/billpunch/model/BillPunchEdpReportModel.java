package com.bata.billpunch.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity(name = "BillPunchEdpReportModel")
@Table(name = "TL_BILL_EDP_REPORT_DTLS")
public class BillPunchEdpReportModel implements Serializable {

	private static final long serialVersionUID = 13332225667L;

	@Id
	@Column(name = "EDP_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long edpId;

	@Column(name = "WEEK")
	private String week;

	@Column(name = "YEAR")
	private String year;

	@Column(name = "RDC_CODE")
	private String rdcCode;

	@Column(name = "INVOICE_NO")
	private String invoiceNo;

	@Column(name = "DIST_NO")
	private String distNo;

	@Column(name = "PACKCASE_T")
	private String packCaset;

	@Column(name = "PAIR")
	private String pair;

	@Column(name = "AMT_ONE")
	private String amtone;

	@Column(name = "AMT_TWO")
	private String amttwo;

	@Column(name = "AMT_WSP")
	private String amtwsp;

	@Column(name = "AMT_NEW")
	private String amtnew;

	@Column(name = "PARTY_CODE")
	private String partyCode;

	@Column(name = "CN_NO")
	private String cnNo;

	@Column(name = "DATE_ONE")
	private String dateOne;

	@Column(name = "DATE_TWO")
	private String dateTwo;

	@Column(name = "DATE_THREE")
	private String dateThree;

	@Column(name = "TRANSPORT_CODE")
	private String transportCode;

	@Column(name = "PERMIT_NO")
	private String permitNo;

	@Column(name = "STATE_CODE")
	private String stateCode;

	@Column(name = "INV_AMT")
	private String invAmt;

	@Column(name = "GR_NO")
	private String grNo;

	@Column(name = "DATE_TWO_ONE")
	private String dateTwoOne;

	@Column(name = "DATE_TWO_TWO")
	private String dateTwoTwo;

	@Column(name = "DATE_TWO_THREE")
	private String dateTwoThree;

	@Column(name = "DATE_THREE_ONE")
	private String dateThreeOne;

	@Column(name = "DATE_THREE_TWO")
	private String dateThreeTwo;

	@Column(name = "DATE_THREE_THREE")
	private String dateThreeThree;

	@Column(name = "RESUME_INV_NO")
	private String resumeInvNO;

	@Column(name = "DATE_FOUR_ONE")
	private String dateFourOne;

	@Column(name = "DATE_FOUR_TWO")
	private String dateFourTwo;

	@Column(name = "DATE_FOUR_THREE")
	private String dateFourThree;

	@Column(name = "RESUME_INV_NO_TWO")
	private String resumeInvNOTwo;

	@Column(name = "AMT_THREE")
	private String amtThree;

	@Column(name = "CGST_AMT")
	private String cgstamt;

	@Column(name = "SGST_AMT")
	private String sgstamt;

	@Column(name = "IGST_AMT")
	private String igstamt;

	@Column(name = "BILL_ORD_NO")
	private String billOrdNo;

	// @Basic
	// @UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_ON")
	private Calendar createdOn;

	public Calendar getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Calendar createdOn) {
		this.createdOn = createdOn;
	}

	public Long getEdpId() {
		return edpId;
	}

	public void setEdpId(Long edpId) {
		this.edpId = edpId;
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

	public String getRdcCode() {
		return rdcCode;
	}

	public void setRdcCode(String rdcCode) {
		this.rdcCode = rdcCode;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getDistNo() {
		return distNo;
	}

	public void setDistNo(String distNo) {
		this.distNo = distNo;
	}

	public String getPackCaset() {
		return packCaset;
	}

	public void setPackCaset(String packCaset) {
		this.packCaset = packCaset;
	}

	public String getPair() {
		return pair;
	}

	public void setPair(String pair) {
		this.pair = pair;
	}

	public String getAmtone() {
		return amtone;
	}

	public void setAmtone(String amtone) {
		this.amtone = amtone;
	}

	public String getAmttwo() {
		return amttwo;
	}

	public void setAmttwo(String amttwo) {
		this.amttwo = amttwo;
	}

	public String getAmtnew() {
		return amtnew;
	}

	public void setAmtnew(String amtnew) {
		this.amtnew = amtnew;
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

	public String getDateTwoOne() {
		return dateTwoOne;
	}

	public void setDateTwoOne(String dateTwoOne) {
		this.dateTwoOne = dateTwoOne;
	}

	public String getDateTwoTwo() {
		return dateTwoTwo;
	}

	public void setDateTwoTwo(String dateTwoTwo) {
		this.dateTwoTwo = dateTwoTwo;
	}

	public String getDateTwoThree() {
		return dateTwoThree;
	}

	public void setDateTwoThree(String dateTwoThree) {
		this.dateTwoThree = dateTwoThree;
	}

	public String getDateThreeOne() {
		return dateThreeOne;
	}

	public void setDateThreeOne(String dateThreeOne) {
		this.dateThreeOne = dateThreeOne;
	}

	public String getDateThreeTwo() {
		return dateThreeTwo;
	}

	public void setDateThreeTwo(String dateThreeTwo) {
		this.dateThreeTwo = dateThreeTwo;
	}

	public String getDateThreeThree() {
		return dateThreeThree;
	}

	public void setDateThreeThree(String dateThreeThree) {
		this.dateThreeThree = dateThreeThree;
	}

	public String getResumeInvNO() {
		return resumeInvNO;
	}

	public void setResumeInvNO(String resumeInvNO) {
		this.resumeInvNO = resumeInvNO;
	}

	public String getDateFourOne() {
		return dateFourOne;
	}

	public void setDateFourOne(String dateFourOne) {
		this.dateFourOne = dateFourOne;
	}

	public String getDateFourTwo() {
		return dateFourTwo;
	}

	public void setDateFourTwo(String dateFourTwo) {
		this.dateFourTwo = dateFourTwo;
	}

	public String getDateFourThree() {
		return dateFourThree;
	}

	public void setDateFourThree(String dateFourThree) {
		this.dateFourThree = dateFourThree;
	}

	public String getResumeInvNOTwo() {
		return resumeInvNOTwo;
	}

	public void setResumeInvNOTwo(String resumeInvNOTwo) {
		this.resumeInvNOTwo = resumeInvNOTwo;
	}

	public String getAmtThree() {
		return amtThree;
	}

	public void setAmtThree(String amtThree) {
		this.amtThree = amtThree;
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

	public String getBillOrdNo() {
		return billOrdNo;
	}

	public void setBillOrdNo(String billOrdNo) {
		this.billOrdNo = billOrdNo;
	}

	public String getAmtwsp() {
		return amtwsp;
	}

	public void setAmtwsp(String amtwsp) {
		this.amtwsp = amtwsp;
	}

}
