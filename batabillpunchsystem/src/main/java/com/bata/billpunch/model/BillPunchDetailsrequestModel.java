package com.bata.billpunch.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;

public class BillPunchDetailsrequestModel {

	private Long bilId;

	private String rdcCode;

	private Integer billCloseYear;

	private Integer billCloseWeek;

	private String billWeekYear;

	private String weekYear;

	private Integer billWeekDay;

	private Date tranDate;

	private Integer tranCode;

	private String invoiceNO;

	private String articleCode;

	private Integer sizeCode;

	private String shopNo;

	private Integer distcode;

	private Integer invType;

	private Integer pair;

	private Integer rdcPair;

	private Integer packCaseT;

	private Integer packCaseB;

	private Integer packCaseM;

	private Integer packCaseS;

	private Integer packCaseC;

	private String dcCode;

	private String recStatus;

	private String billUniqueCode;

	private String party;

	private String partyCode;

	private String partyName;
	
	private String receiveLoc;

	@Column(name = "CN_NO")
	private String cnNo;

	@Column(name = "CN_DATE")
	private Date cnDate;

	private Integer trnsportCode;

	private String rdpermitNo;

	private String stateName;

	private String stateCode;

	private Double invoiceCost;

	private Date recptInvDate;

	private String recptInvNo;

	private String resumeInvNo;

	private String resumeInvNoTwo;

	private Date resumeInvDate;

	private String billOrderNo;

	private String hsnCode;

	private String igst;

	private String cgst;

	private String sgst;

	private Double igstamt;

	private Double cgstamt;

	private Double sgstamt;

	private Double gstamt;

	private Double pairAmount;

	private Integer fromState;

	private Integer toState;

	private String formtype;

	private String billCloseStatus;

	private Date billOrderDate;

	private String region;

	private String shopName;

	private String freight;

	private Double totalCost;

	private Integer cPair;

	private String grNo;

	private Date grDate;

	private Date grnDate;

	private Date receiveDate;

	private String articleName;

	private String discountAmt;

	private String tcsPercent;

	private Double creditNote;

	private String rdcPairC;

	private String status;

	private Double purchaseCost;

	private Double tcsValue;

	private Double discountAmtVal;

	private String noOfCartons;

	private String tcsApplicable;

	private String createdBy;
	
	private String grnFromDate;

	private String grnToDate;
	
	public List<String> week;

	
	private String year;

	public Long getBilId() {
		return bilId;
	}

	public void setBilId(Long bilId) {
		this.bilId = bilId;
	}

	public String getRdcCode() {
		return rdcCode;
	}

	public void setRdcCode(String rdcCode) {
		this.rdcCode = rdcCode;
	}

	public Integer getBillCloseYear() {
		return billCloseYear;
	}

	public void setBillCloseYear(Integer billCloseYear) {
		this.billCloseYear = billCloseYear;
	}

	public Integer getBillCloseWeek() {
		return billCloseWeek;
	}

	public void setBillCloseWeek(Integer billCloseWeek) {
		this.billCloseWeek = billCloseWeek;
	}

	public String getBillWeekYear() {
		return billWeekYear;
	}

	public void setBillWeekYear(String billWeekYear) {
		this.billWeekYear = billWeekYear;
	}

	public String getWeekYear() {
		return weekYear;
	}

	public void setWeekYear(String weekYear) {
		this.weekYear = weekYear;
	}

	public Integer getBillWeekDay() {
		return billWeekDay;
	}

	public void setBillWeekDay(Integer billWeekDay) {
		this.billWeekDay = billWeekDay;
	}

	public Date getTranDate() {
		return tranDate;
	}

	public void setTranDate(Date tranDate) {
		this.tranDate = tranDate;
	}

	public Integer getTranCode() {
		return tranCode;
	}

	public void setTranCode(Integer tranCode) {
		this.tranCode = tranCode;
	}

	public String getInvoiceNO() {
		return invoiceNO;
	}

	public void setInvoiceNO(String invoiceNO) {
		this.invoiceNO = invoiceNO;
	}

	public String getArticleCode() {
		return articleCode;
	}

	public void setArticleCode(String articleCode) {
		this.articleCode = articleCode;
	}

	public Integer getSizeCode() {
		return sizeCode;
	}

	public void setSizeCode(Integer sizeCode) {
		this.sizeCode = sizeCode;
	}

	public String getShopNo() {
		return shopNo;
	}

	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}

	public Integer getDistcode() {
		return distcode;
	}

	public void setDistcode(Integer distcode) {
		this.distcode = distcode;
	}

	public Integer getInvType() {
		return invType;
	}

	public void setInvType(Integer invType) {
		this.invType = invType;
	}

	public Integer getPair() {
		return pair;
	}

	public void setPair(Integer pair) {
		this.pair = pair;
	}

	public Integer getRdcPair() {
		return rdcPair;
	}

	public void setRdcPair(Integer rdcPair) {
		this.rdcPair = rdcPair;
	}

	public Integer getPackCaseT() {
		return packCaseT;
	}

	public void setPackCaseT(Integer packCaseT) {
		this.packCaseT = packCaseT;
	}

	public Integer getPackCaseB() {
		return packCaseB;
	}

	public void setPackCaseB(Integer packCaseB) {
		this.packCaseB = packCaseB;
	}

	public Integer getPackCaseM() {
		return packCaseM;
	}

	public void setPackCaseM(Integer packCaseM) {
		this.packCaseM = packCaseM;
	}

	public Integer getPackCaseS() {
		return packCaseS;
	}

	public void setPackCaseS(Integer packCaseS) {
		this.packCaseS = packCaseS;
	}

	public Integer getPackCaseC() {
		return packCaseC;
	}

	public void setPackCaseC(Integer packCaseC) {
		this.packCaseC = packCaseC;
	}

	public String getDcCode() {
		return dcCode;
	}

	public void setDcCode(String dcCode) {
		this.dcCode = dcCode;
	}

	public String getRecStatus() {
		return recStatus;
	}

	public void setRecStatus(String recStatus) {
		this.recStatus = recStatus;
	}

	public String getBillUniqueCode() {
		return billUniqueCode;
	}

	public void setBillUniqueCode(String billUniqueCode) {
		this.billUniqueCode = billUniqueCode;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
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

	public Integer getTrnsportCode() {
		return trnsportCode;
	}

	public void setTrnsportCode(Integer trnsportCode) {
		this.trnsportCode = trnsportCode;
	}

	public String getRdpermitNo() {
		return rdpermitNo;
	}

	public void setRdpermitNo(String rdpermitNo) {
		this.rdpermitNo = rdpermitNo;
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

	public Double getInvoiceCost() {
		return invoiceCost;
	}

	public void setInvoiceCost(Double invoiceCost) {
		this.invoiceCost = invoiceCost;
	}

	public Date getRecptInvDate() {
		return recptInvDate;
	}

	public void setRecptInvDate(Date recptInvDate) {
		this.recptInvDate = recptInvDate;
	}

	public String getRecptInvNo() {
		return recptInvNo;
	}

	public void setRecptInvNo(String recptInvNo) {
		this.recptInvNo = recptInvNo;
	}

	public String getResumeInvNo() {
		return resumeInvNo;
	}

	public void setResumeInvNo(String resumeInvNo) {
		this.resumeInvNo = resumeInvNo;
	}

	public String getResumeInvNoTwo() {
		return resumeInvNoTwo;
	}

	public void setResumeInvNoTwo(String resumeInvNoTwo) {
		this.resumeInvNoTwo = resumeInvNoTwo;
	}

	public Date getResumeInvDate() {
		return resumeInvDate;
	}

	public void setResumeInvDate(Date resumeInvDate) {
		this.resumeInvDate = resumeInvDate;
	}

	public String getBillOrderNo() {
		return billOrderNo;
	}

	public void setBillOrderNo(String billOrderNo) {
		this.billOrderNo = billOrderNo;
	}

	public String getHsnCode() {
		return hsnCode;
	}

	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
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

	public Double getGstamt() {
		return gstamt;
	}

	public void setGstamt(Double gstamt) {
		this.gstamt = gstamt;
	}

	public Double getPairAmount() {
		return pairAmount;
	}

	public void setPairAmount(Double pairAmount) {
		this.pairAmount = pairAmount;
	}

	public Integer getFromState() {
		return fromState;
	}

	public void setFromState(Integer fromState) {
		this.fromState = fromState;
	}

	public Integer getToState() {
		return toState;
	}

	public void setToState(Integer toState) {
		this.toState = toState;
	}

	public String getFormtype() {
		return formtype;
	}

	public void setFormtype(String formtype) {
		this.formtype = formtype;
	}

	public String getBillCloseStatus() {
		return billCloseStatus;
	}

	public void setBillCloseStatus(String billCloseStatus) {
		this.billCloseStatus = billCloseStatus;
	}

	public Date getBillOrderDate() {
		return billOrderDate;
	}

	public void setBillOrderDate(Date billOrderDate) {
		this.billOrderDate = billOrderDate;
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

	public Date getGrnDate() {
		return grnDate;
	}

	public void setGrnDate(Date grnDate) {
		this.grnDate = grnDate;
	}

	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	public String getArticleName() {
		return articleName;
	}

	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}

	public String getDiscountAmt() {
		return discountAmt;
	}

	public void setDiscountAmt(String discountAmt) {
		this.discountAmt = discountAmt;
	}

	public String getTcsPercent() {
		return tcsPercent;
	}

	public void setTcsPercent(String tcsPercent) {
		this.tcsPercent = tcsPercent;
	}

	public Double getCreditNote() {
		return creditNote;
	}

	public void setCreditNote(Double creditNote) {
		this.creditNote = creditNote;
	}

	public String getRdcPairC() {
		return rdcPairC;
	}

	public void setRdcPairC(String rdcPairC) {
		this.rdcPairC = rdcPairC;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getPurchaseCost() {
		return purchaseCost;
	}

	public void setPurchaseCost(Double purchaseCost) {
		this.purchaseCost = purchaseCost;
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

	public String getNoOfCartons() {
		return noOfCartons;
	}

	public void setNoOfCartons(String noOfCartons) {
		this.noOfCartons = noOfCartons;
	}

	public String getTcsApplicable() {
		return tcsApplicable;
	}

	public void setTcsApplicable(String tcsApplicable) {
		this.tcsApplicable = tcsApplicable;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getGrnFromDate() {
		return grnFromDate;
	}

	public void setGrnFromDate(String grnFromDate) {
		this.grnFromDate = grnFromDate;
	}

	public String getGrnToDate() {
		return grnToDate;
	}

	public void setGrnToDate(String grnToDate) {
		this.grnToDate = grnToDate;
	}

	
	public List<String> getWeek() {
		return week;
	}

	public void setWeek(List<String> week) {
		this.week = week;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getReceiveLoc() {
		return receiveLoc;
	}

	public void setReceiveLoc(String receiveLoc) {
		this.receiveLoc = receiveLoc;
	}
	
	

}
