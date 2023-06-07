package com.bata.billpunch.model.dto;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class BillPunchDto {

	private String grNo;

	private Date grDate;

	private Double igstamt;

	private Double cgstamt;

	private Double sgstamt;

	private String invoiceNO;

	private String tcsApplicable;

	private String tcsPercent;

	private String cnNo;

	private Date cnDate;

	private Double invoiceCost;

	private Long billId;

	private String billUniqueCode;

	private String billWeekYear;

	private String receiveLoc;

	private Integer billWeek;

	private String formtype;

	private String igst;

	private String cgst;

	private String sgst;

	private Integer billCloseWeek;

	private Date grnDate;

	private String billCloseStatus;

	private String partyCode;

	private String partyName;

	private Date billDate;

	private Date billOrderDate;

	private Date recptInvDate;

	private String stateName;

	private String stateCode;

	private String region;

	private String shopName;

	private String shopNo;

	private String billOrderNo;

	private Integer pair;

	private Double purchaseCost;

	private String discountAmt;

	private String saleTax;

	private String freight;

	private Double totalCost;

	private Integer cPair;

	private Double creditNote;

	private String cPairCost;

	private String status;

	private String createdBy;

	private Calendar createdOn;

	private Double tcsValue;

	private Double discountAmtVal;
	
	private String rdcCode;


	private List<RdcDetailsDto> rdcList;

	private ApprovalDetailsDto appmodel;
	
	

	public String getRdcCode() {
		return rdcCode;
	}

	public void setRdcCode(String rdcCode) {
		this.rdcCode = rdcCode;
	}

	public String getGrNo() {
		return grNo;
	}

	public void setGrNo(String grNo) {
		this.grNo = grNo;
	}

	public Date getGrDate() {
		return grDate;
	}

	public void setGrDate(Date grDate) {
		this.grDate = grDate;
	}

	public String getInvoiceNO() {
		return invoiceNO;
	}

	public void setInvoiceNO(String invoiceNO) {
		this.invoiceNO = invoiceNO;
	}

	public String getTcsApplicable() {
		return tcsApplicable;
	}

	public void setTcsApplicable(String tcsApplicable) {
		this.tcsApplicable = tcsApplicable;
	}

	public Double getInvoiceCost() {
		return invoiceCost;
	}

	public void setInvoiceCost(Double invoiceCost) {
		this.invoiceCost = invoiceCost;
	}

	public Long getBillId() {
		return billId;
	}

	public void setBillId(Long billId) {
		this.billId = billId;
	}

	public String getBillUniqueCode() {
		return billUniqueCode;
	}

	public void setBillUniqueCode(String billUniqueCode) {
		this.billUniqueCode = billUniqueCode;
	}

	public Integer getBillWeek() {
		return billWeek;
	}

	public void setBillWeek(Integer billWeek) {
		this.billWeek = billWeek;
	}

	public String getFormtype() {
		return formtype;
	}

	public void setFormtype(String formtype) {
		this.formtype = formtype;
	}

	public Integer getBillCloseWeek() {
		return billCloseWeek;
	}

	public void setBillCloseWeek(Integer billCloseWeek) {
		this.billCloseWeek = billCloseWeek;
	}

	public Date getGrnDate() {
		return grnDate;
	}

	public void setGrnDate(Date grnDate) {
		this.grnDate = grnDate;
	}

	public String getBillCloseStatus() {
		return billCloseStatus;
	}

	public void setBillCloseStatus(String billCloseStatus) {
		this.billCloseStatus = billCloseStatus;
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

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public Date getBillOrderDate() {
		return billOrderDate;
	}

	public void setBillOrderDate(Date billOrderDate) {
		this.billOrderDate = billOrderDate;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopNo() {
		return shopNo;
	}

	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}

	public String getBillOrderNo() {
		return billOrderNo;
	}

	public void setBillOrderNo(String billOrderNo) {
		this.billOrderNo = billOrderNo;
	}

	public Integer getPair() {
		return pair;
	}

	public void setPair(Integer pair) {
		this.pair = pair;
	}

	public Double getPurchaseCost() {
		return purchaseCost;
	}

	public void setPurchaseCost(Double purchaseCost) {
		this.purchaseCost = purchaseCost;
	}

	public String getDiscountAmt() {
		return discountAmt;
	}

	public void setDiscountAmt(String discountAmt) {
		this.discountAmt = discountAmt;
	}

	public String getSaleTax() {
		return saleTax;
	}

	public void setSaleTax(String saleTax) {
		this.saleTax = saleTax;
	}

	public String getFreight() {
		return freight;
	}

	public void setFreight(String freight) {
		this.freight = freight;
	}

	public Double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}

	public Integer getcPair() {
		return cPair;
	}

	public void setcPair(Integer cPair) {
		this.cPair = cPair;
	}

	public Double getCreditNote() {
		return creditNote;
	}

	public void setCreditNote(Double creditNote) {
		this.creditNote = creditNote;
	}

	public String getcPairCost() {
		return cPairCost;
	}

	public void setcPairCost(String cPairCost) {
		this.cPairCost = cPairCost;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Calendar getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Calendar createdOn) {
		this.createdOn = createdOn;
	}

	public Double getTcsValue() {
		return tcsValue;
	}

	public void setTcsValue(Double tcsValue) {
		this.tcsValue = tcsValue;
	}

	public Double getDiscountAmtVal() {
		return discountAmtVal;
	}

	public void setDiscountAmtVal(Double discountAmtVal) {
		this.discountAmtVal = discountAmtVal;
	}

	public List<RdcDetailsDto> getRdcList() {
		return rdcList;
	}

	public void setRdcList(List<RdcDetailsDto> rdcList) {
		this.rdcList = rdcList;
	}

	public ApprovalDetailsDto getAppmodel() {
		return appmodel;
	}

	public void setAppmodel(ApprovalDetailsDto appmodel) {
		this.appmodel = appmodel;
	}

	public String getBillWeekYear() {
		return billWeekYear;
	}

	public void setBillWeekYear(String billWeekYear) {
		this.billWeekYear = billWeekYear;
	}

	public String getTcsPercent() {
		return tcsPercent;
	}

	public void setTcsPercent(String tcsPercent) {
		this.tcsPercent = tcsPercent;
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

	public Double getIgstamt() {
		return igstamt;
	}

	public void setIgstamt(Double igstamt) {
		this.igstamt = igstamt;
	}

	public Double getCgstamt() {
		return cgstamt;
	}

	public void setCgstamt(Double cgstamt) {
		this.cgstamt = cgstamt;
	}

	public Double getSgstamt() {
		return sgstamt;
	}

	public void setSgstamt(Double sgstamt) {
		this.sgstamt = sgstamt;
	}

	public String getReceiveLoc() {
		return receiveLoc;
	}

	public void setReceiveLoc(String receiveLoc) {
		this.receiveLoc = receiveLoc;
	}

	public Date getRecptInvDate() {
		return recptInvDate;
	}

	public void setRecptInvDate(Date recptInvDate) {
		this.recptInvDate = recptInvDate;
	}

	public String getCnNo() {
		return cnNo;
	}

	public void setCnNo(String cnNo) {
		this.cnNo = cnNo;
	}

	public Date getCnDate() {
		return cnDate;
	}

	public void setCnDate(Date cnDate) {
		this.cnDate = cnDate;
	}

}
