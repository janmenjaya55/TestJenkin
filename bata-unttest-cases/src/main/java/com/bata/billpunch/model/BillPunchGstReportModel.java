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
@Entity(name = "BillPunchGstReportModel")
@Table(name = "TL_BILL_GST_REPORT_DTLS")
public class BillPunchGstReportModel implements Serializable {

	private static final long serialVersionUID = 13332225667L;

	@Id
	@Column(name = "GST_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long gstId;

	@Column(name = "WEEK")
	private String week;

	@Column(name = "YEAR")
	private String year;

	@Column(name = "PO_NO")
	private String poNo;

	@Column(name = "PO_DATE")
	private String poDate;

	@Column(name = "CN_NO")
	private String cnNo;

	@Column(name = "CN_DATE")
	private String cnDate;

	@Column(name = "GR_NO")
	private String grNo;

	@Column(name = "GR_DATE")
	private String grDate;

	@Column(name = "RDC_NO")
	private String rdcNo;

	@Column(name = "RDC_NAME")
	private String rdcName;

	@Column(name = "RDC_STATE_NAME")
	private String rdcStateName;

	@Column(name = "RETURN_PR")
	private String returnPeriod;

	@Column(name = "RDC_GSTIN")
	private String rdcGstin;

	@Column(name = "DOCTYPE")
	private String doctype;

	@Column(name = "INVOICE_NO")
	private String invoiceNo;

	@Column(name = "PARTY_CODE")
	private String partyCode;

	@Column(name = "CREATED_ON")
	private Calendar createdOn;

	@Column(name = "INVOICE_DATE")
	private String invoiceDate;

	@Column(name = "SUPPLY_GSTN")
	private String supplyGstin;

	@Column(name = "SUPPLY_NAME")
	private String supplyName;

	@Column(name = "SUPPLY_CODE")
	private String suplyCode;

	@Column(name = "SUPPLYER_STATENAME")
	private String splyStateName;

	@Column(name = "HSN")
	private String hsn;

	@Column(name = "ITEM_CODE")
	private String itemCode;

	@Column(name = "ITEM_DESC")
	private String itemDesc;

	@Column(name = "UNIT_MES")
	private String unitMeasure;

	@Column(name = "QNTY")
	private String quantity;

	@Column(name = "TAXABLE_VALUE")
	private String taxableValue;

	@Column(name = "NET_AMOUNT")
	private String netAmount;

	@Column(name = "IGST_RT")
	private String igstrt;

	@Column(name = "IGST_AMT")
	private String igstamt;

	@Column(name = "CGST_RT")
	private String cgstrt;

	@Column(name = "CGST_AMT")
	private String cgstamt;

	@Column(name = "SGST_RT")
	private String sgstrt;

	@Column(name = "SGST_AMT")
	private String sgstamt;

	@Column(name = "CESS_AMOUNT")
	private String cessamt;

	@Column(name = "INVOICE_VALUE")
	private String invamt;

	@Column(name = "MRP")
	private String mrp;

	@Column(name = "REVERSE_CHARGE_FLAG")
	private String rcflag;

	@Column(name = "ELIGIBILITY_INDICATOR")
	private String elgIndicator;

	@Column(name = "DOCUMENT_NUMBER")
	private String docNo;

	@Column(name = "VOUCHER_NO")
	private String vocherNo;

	@Column(name = "VOUCHER_DATE")
	private String voucherDate;

	@Column(name = "GL_DATE")
	private String glDate;

	@Column(name = "PAYMENT_STATUS")
	private String pmtStatus;

	@Column(name = "CHEQUE_NO")
	private String cqNo;

	@Column(name = "CHEQUE_DATE")
	private String cqDate;

	@Column(name = "PAID_LAST_MONTH")
	private String paidLastMnth;

	@Column(name = "OPENING_UNPAID")
	private String openUnpaid;

	@Column(name = "PAID_DURING_THE_MONTH")
	private String duringmnth;

	@Column(name = "PAID_TILL_DATE")
	private String paidtilldate;

	@Column(name = "UNPAID")
	private String unpaid;

	@Column(name = "UNPAID_CLOSING_BALANCE")
	private String unpaidclBal;

	@Column(name = "IGST_GL_CODE")
	private String igstglCode;

	@Column(name = "CGST_GL_CODE")
	private String cgstglCode;

	@Column(name = "SGST_GL_CODE")
	private String sgstglCode;

	@Column(name = "CLAIM_DISCOUNT")
	private String claimdst;

	@Column(name = "TYPE_ITC")
	private String itcType;

	@Column(name = "BILL_RDC_STATENAME")
	private String billrdcStateName;

	@Column(name = "SHIPPED_RDC_STATENAME")
	private String shippedStaeName;

	@Column(name = "BILL_OF_ENTRY_NO")
	private String entryNo;

	@Column(name = "BILL_OF_ENTRY_DATE")
	private String entryDate;

	@Column(name = "VALUE_BILL_ENTRY")
	private String valueBill;

	@Column(name = "TAXABLE_VALUE_BOE")
	private String taxableBoe;

	@Column(name = "PORT_CODE")
	private String portCode;

	@Column(name = "SIZE_GOODS")
	private String sizeGoods;

	// @Basic
	// @UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE")
	private Calendar createdDate;

	public Calendar getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Calendar createdDate) {
		this.createdDate = createdDate;
	}

	public Long getGstId() {
		return gstId;
	}

	public void setGstId(Long gstId) {
		this.gstId = gstId;
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

	public String getRdcNo() {
		return rdcNo;
	}

	public void setRdcNo(String rdcNo) {
		this.rdcNo = rdcNo;
	}

	public String getRdcName() {
		return rdcName;
	}

	public void setRdcName(String rdcName) {
		this.rdcName = rdcName;
	}

	public String getRdcStateName() {
		return rdcStateName;
	}

	public void setRdcStateName(String rdcStateName) {
		this.rdcStateName = rdcStateName;
	}

	public String getReturnPeriod() {
		return returnPeriod;
	}

	public void setReturnPeriod(String returnPeriod) {
		this.returnPeriod = returnPeriod;
	}

	public String getRdcGstin() {
		return rdcGstin;
	}

	public void setRdcGstin(String rdcGstin) {
		this.rdcGstin = rdcGstin;
	}

	public String getDoctype() {
		return doctype;
	}

	public void setDoctype(String doctype) {
		this.doctype = doctype;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getPartyCode() {
		return partyCode;
	}

	public void setPartyCode(String partyCode) {
		this.partyCode = partyCode;
	}

	public Calendar getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Calendar createdOn) {
		this.createdOn = createdOn;
	}

	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getSupplyGstin() {
		return supplyGstin;
	}

	public void setSupplyGstin(String supplyGstin) {
		this.supplyGstin = supplyGstin;
	}

	public String getSupplyName() {
		return supplyName;
	}

	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}

	public String getSuplyCode() {
		return suplyCode;
	}

	public void setSuplyCode(String suplyCode) {
		this.suplyCode = suplyCode;
	}

	public String getSplyStateName() {
		return splyStateName;
	}

	public void setSplyStateName(String splyStateName) {
		this.splyStateName = splyStateName;
	}

	public String getHsn() {
		return hsn;
	}

	public void setHsn(String hsn) {
		this.hsn = hsn;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public String getUnitMeasure() {
		return unitMeasure;
	}

	public void setUnitMeasure(String unitMeasure) {
		this.unitMeasure = unitMeasure;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getTaxableValue() {
		return taxableValue;
	}

	public void setTaxableValue(String taxableValue) {
		this.taxableValue = taxableValue;
	}

	public String getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(String netAmount) {
		this.netAmount = netAmount;
	}

	public String getIgstrt() {
		return igstrt;
	}

	public void setIgstrt(String igstrt) {
		this.igstrt = igstrt;
	}

	public String getIgstamt() {
		return igstamt;
	}

	public void setIgstamt(String igstamt) {
		this.igstamt = igstamt;
	}

	public String getCgstrt() {
		return cgstrt;
	}

	public void setCgstrt(String cgstrt) {
		this.cgstrt = cgstrt;
	}

	public String getCgstamt() {
		return cgstamt;
	}

	public void setCgstamt(String cgstamt) {
		this.cgstamt = cgstamt;
	}

	public String getSgstrt() {
		return sgstrt;
	}

	public void setSgstrt(String sgstrt) {
		this.sgstrt = sgstrt;
	}

	public String getSgstamt() {
		return sgstamt;
	}

	public void setSgstamt(String sgstamt) {
		this.sgstamt = sgstamt;
	}

	public String getCessamt() {
		return cessamt;
	}

	public void setCessamt(String cessamt) {
		this.cessamt = cessamt;
	}

	public String getInvamt() {
		return invamt;
	}

	public void setInvamt(String invamt) {
		this.invamt = invamt;
	}

	public String getMrp() {
		return mrp;
	}

	public void setMrp(String mrp) {
		this.mrp = mrp;
	}

	public String getRcflag() {
		return rcflag;
	}

	public void setRcflag(String rcflag) {
		this.rcflag = rcflag;
	}

	public String getElgIndicator() {
		return elgIndicator;
	}

	public void setElgIndicator(String elgIndicator) {
		this.elgIndicator = elgIndicator;
	}

	public String getDocNo() {
		return docNo;
	}

	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}

	public String getVocherNo() {
		return vocherNo;
	}

	public void setVocherNo(String vocherNo) {
		this.vocherNo = vocherNo;
	}

	public String getVoucherDate() {
		return voucherDate;
	}

	public void setVoucherDate(String voucherDate) {
		this.voucherDate = voucherDate;
	}

	public String getGlDate() {
		return glDate;
	}

	public void setGlDate(String glDate) {
		this.glDate = glDate;
	}

	public String getPmtStatus() {
		return pmtStatus;
	}

	public void setPmtStatus(String pmtStatus) {
		this.pmtStatus = pmtStatus;
	}

	public String getCqNo() {
		return cqNo;
	}

	public void setCqNo(String cqNo) {
		this.cqNo = cqNo;
	}

	public String getCqDate() {
		return cqDate;
	}

	public void setCqDate(String cqDate) {
		this.cqDate = cqDate;
	}

	public String getPaidLastMnth() {
		return paidLastMnth;
	}

	public void setPaidLastMnth(String paidLastMnth) {
		this.paidLastMnth = paidLastMnth;
	}

	public String getOpenUnpaid() {
		return openUnpaid;
	}

	public void setOpenUnpaid(String openUnpaid) {
		this.openUnpaid = openUnpaid;
	}

	public String getDuringmnth() {
		return duringmnth;
	}

	public void setDuringmnth(String duringmnth) {
		this.duringmnth = duringmnth;
	}

	public String getPaidtilldate() {
		return paidtilldate;
	}

	public void setPaidtilldate(String paidtilldate) {
		this.paidtilldate = paidtilldate;
	}

	public String getUnpaid() {
		return unpaid;
	}

	public void setUnpaid(String unpaid) {
		this.unpaid = unpaid;
	}

	public String getUnpaidclBal() {
		return unpaidclBal;
	}

	public void setUnpaidclBal(String unpaidclBal) {
		this.unpaidclBal = unpaidclBal;
	}

	public String getIgstglCode() {
		return igstglCode;
	}

	public void setIgstglCode(String igstglCode) {
		this.igstglCode = igstglCode;
	}

	public String getCgstglCode() {
		return cgstglCode;
	}

	public void setCgstglCode(String cgstglCode) {
		this.cgstglCode = cgstglCode;
	}

	public String getSgstglCode() {
		return sgstglCode;
	}

	public void setSgstglCode(String sgstglCode) {
		this.sgstglCode = sgstglCode;
	}

	public String getClaimdst() {
		return claimdst;
	}

	public void setClaimdst(String claimdst) {
		this.claimdst = claimdst;
	}

	public String getItcType() {
		return itcType;
	}

	public void setItcType(String itcType) {
		this.itcType = itcType;
	}

	public String getBillrdcStateName() {
		return billrdcStateName;
	}

	public void setBillrdcStateName(String billrdcStateName) {
		this.billrdcStateName = billrdcStateName;
	}

	public String getShippedStaeName() {
		return shippedStaeName;
	}

	public void setShippedStaeName(String shippedStaeName) {
		this.shippedStaeName = shippedStaeName;
	}

	public String getEntryNo() {
		return entryNo;
	}

	public void setEntryNo(String entryNo) {
		this.entryNo = entryNo;
	}

	public String getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	public String getValueBill() {
		return valueBill;
	}

	public void setValueBill(String valueBill) {
		this.valueBill = valueBill;
	}

	public String getTaxableBoe() {
		return taxableBoe;
	}

	public void setTaxableBoe(String taxableBoe) {
		this.taxableBoe = taxableBoe;
	}

	public String getPortCode() {
		return portCode;
	}

	public void setPortCode(String portCode) {
		this.portCode = portCode;
	}

	public String getSizeGoods() {
		return sizeGoods;
	}

	public void setSizeGoods(String sizeGoods) {
		this.sizeGoods = sizeGoods;
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

}
