package com.bata.billpunch.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

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
@Entity(name = "BillPunchSupplyReportModel")
@Table(name = "TL_BILL_SUPPLY_REPORT_DTLS")
public class BillPunchSupplyReportModel implements Serializable {

	private static final long serialVersionUID = 13332225667L;

	@Id
	@Column(name = "SUPPLY_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long supplyId;

	@Column(name = "WEEK")
	private String week;

	@Column(name = "YEAR")
	private String year;

	@Column(name = "FROM_STATE_CODE")
	private String fromStateCode;

	@Column(name = "FROM_STATE_NAME")
	private String fromStateName;

	@Column(name = "PARTY_CODE")
	private String partyCode;

	@Column(name = "PARTY_NAME")
	private String partyName;

	@Column(name = "TO_STATE_CODE")
	private String toStateCode;

	@Column(name = "TO_STATE_NAME")
	private String toStateName;

	@Column(name = "TO_LOCATION_CODE")
	private String tolocationCode;

	@Column(name = "RECEVING_LOCATION_NAME")
	private String recLocName;

	@Column(name = "DEPT")
	private String dept;

	@Column(name = "REGION")
	private String region;

	@Column(name = "INVOICE_NO")
	private String invoiceNo;

	@Column(name = "INVOICE_DATE")
	private String invoiceDate;

	@Column(name = "GRN_NO")
	private String grnNo;

	@Column(name = "GRN_DATE")
	private String grnDate;

	@Column(name = "BOOKING")
	private String booking;

	@Column(name = "PAIRS")
	private String pairs;

	@Column(name = "TAXABLE_VALUE")
	private String taxableValue;

	@Column(name = "CGST")
	private String cgst;

	@Column(name = "SGST")
	private String sgst;

	@Column(name = "IGST")
	private String igst;

	@Column(name = "FREIGHT")
	private String freight;

	@Column(name = "TCS")
	private String tcs;

	@Column(name = "NET_AMOUNT")
	private String netAmount;

	@Column(name = "GR_NO")
	private String grNo;

	@Column(name = "GR_DATE")
	private String grDate;

	@Column(name = "PO_NO")
	private String poNo;

	@Column(name = "PO_DATE")
	private String poDate;

	@Column(name = "VENDOR_GST_NO")
	private String venGstNo;

	@Column(name = "MRP")
	private String mrp;

	@Column(name = "WSP")
	private String wsp;

	@Column(name = "RECEIVER_GST_NO")
	private String receiverGstNo;

	@Column(name = "NO_CLAIM_DIST")
	private String noclaim;
	
	
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


	public Long getSupplyId() {
		return supplyId;
	}

	public void setSupplyId(Long supplyId) {
		this.supplyId = supplyId;
	}

	public String getFromStateCode() {
		return fromStateCode;
	}

	public void setFromStateCode(String fromStateCode) {
		this.fromStateCode = fromStateCode;
	}

	public String getFromStateName() {
		return fromStateName;
	}

	public void setFromStateName(String fromStateName) {
		this.fromStateName = fromStateName;
	}

	public String getPartyCode() {
		return partyCode;
	}

	public void setPartyCode(String partyCode) {
		this.partyCode = partyCode;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public String getToStateCode() {
		return toStateCode;
	}

	public void setToStateCode(String toStateCode) {
		this.toStateCode = toStateCode;
	}

	public String getToStateName() {
		return toStateName;
	}

	public void setToStateName(String toStateName) {
		this.toStateName = toStateName;
	}

	public String getRecLocName() {
		return recLocName;
	}

	public void setRecLocName(String recLocName) {
		this.recLocName = recLocName;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getGrnNo() {
		return grnNo;
	}

	public void setGrnNo(String grnNo) {
		this.grnNo = grnNo;
	}

	public String getGrnDate() {
		return grnDate;
	}

	public void setGrnDate(String grnDate) {
		this.grnDate = grnDate;
	}

	public String getBooking() {
		return booking;
	}

	public void setBooking(String booking) {
		this.booking = booking;
	}

	public String getPairs() {
		return pairs;
	}

	public void setPairs(String pairs) {
		this.pairs = pairs;
	}

	public String getTaxableValue() {
		return taxableValue;
	}

	public void setTaxableValue(String taxableValue) {
		this.taxableValue = taxableValue;
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

	public String getFreight() {
		return freight;
	}

	public void setFreight(String freight) {
		this.freight = freight;
	}

	public String getTcs() {
		return tcs;
	}

	public void setTcs(String tcs) {
		this.tcs = tcs;
	}

	public String getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(String netAmount) {
		this.netAmount = netAmount;
	}

	public String getGrNo() {
		return grNo;
	}

	public void setGrNo(String grNo) {
		this.grNo = grNo;
	}

	public String getGrDate() {
		return grDate;
	}

	public void setGrDate(String grDate) {
		this.grDate = grDate;
	}

	public String getPoNo() {
		return poNo;
	}

	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}

	public String getPoDate() {
		return poDate;
	}

	public void setPoDate(String poDate) {
		this.poDate = poDate;
	}

	public String getVenGstNo() {
		return venGstNo;
	}

	public void setVenGstNo(String venGstNo) {
		this.venGstNo = venGstNo;
	}

	public String getMrp() {
		return mrp;
	}

	public void setMrp(String mrp) {
		this.mrp = mrp;
	}

	public String getWsp() {
		return wsp;
	}

	public void setWsp(String wsp) {
		this.wsp = wsp;
	}

	public String getReceiverGstNo() {
		return receiverGstNo;
	}

	public void setReceiverGstNo(String receiverGstNo) {
		this.receiverGstNo = receiverGstNo;
	}

	public String getNoclaim() {
		return noclaim;
	}

	public void setNoclaim(String noclaim) {
		this.noclaim = noclaim;
	}

	public String getTolocationCode() {
		return tolocationCode;
	}

	public void setTolocationCode(String tolocationCode) {
		this.tolocationCode = tolocationCode;
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
	

}
