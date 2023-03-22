package com.bata.billpunch.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity(name = "BillPunchDetailsImportDataModel")
@Table(name = "TT_BILL_PUNCH_DTLS_IMPORT")
public class BillPunchDetailsImportDataModel implements Serializable {

	private static final long serialVersionUID = 13332225667L;

	@Id
	@Column(name = "BIL_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bilId;

	@Column(name = "RDC_CODE")
	private String rdcCode;

	@Column(name = "C_YEAR")
	private String billCloseYear;

	@Column(name = "C_WEEK")
	private String billCloseWeek;

	@Column(name = "YR")
	private String billWeekYear;

	@Column(name = "WK")
	private String weekYear;

	@Column(name = "C_WEEK_DAY")
	private String billWeekDay;

	@Column(name = "TRAN_DATE")
	private Date tranDate;

	@Column(name = "TRAN_CODE")
	private String tranCode;

	@Column(name = "TR_INV_NO")
	private String trInvNO;

	@Column(name = "ART_CODE")
	private String articleCode;

	@Column(name = "SIZE_CODE")
	private String sizeCode;

	@Column(name = "DEST_SHOP_NO")
	private String shopNo;

	@Column(name = "DISTRC_CODE")
	private String distcode;

	@Column(name = "INV_TYPE")
	private String invType;

	@Column(name = "TOT_PAIRS")
	private String pair;

	@Column(name = "NO_PAIRS")
	private String rdcPair;

	@Column(name = "PACKING_CASES_T")
	private String packCaseT;

	@Column(name = "PACKING_CASES_B")
	private String packCaseB;

	@Column(name = "PACKING_CASES_M")
	private String packCaseM;

	@Column(name = "PACKING_CASES_S")
	private String packCaseS;

	@Column(name = "PACKING_CASES_C")
	private String packCaseC;

	@Column(name = "DC_CODE")
	private String dcCode;

	@Column(name = "REC_STAT")
	private String recStatus;

	@Column(name = "SEQ_NO")
	private String billUniqueCode;

	@Column(name = "PARTY")
	private String party;

	@Column(name = "PARTY_CODE")
	private String partyCode;

	@Column(name = "PARTY_NAME")
	private String partyName;

	@Column(name = "CN_NO")
	private String cnNo;

	@Column(name = "CN_DATE")
	private String cnDate;

	@Column(name = "TRNSPRT_CODE")
	private String trnsportCode;

	@Column(name = "RD_PERMIT_NO")
	private String rdpermitNo;

	@Column(name = "STATE_NAME")
	private String stateName;

	@Column(name = "PARTY_STATE")
	private String stateCode;

	@Column(name = "PRCH_BIL_VAL")
	private String invoiceCost;

	@Column(name = "RCPT_INV_DATE")
	private String recptInvDate;

	@Column(name = "RCPT_INV_NO")
	private String invoiceNO;

	@Column(name = "RESUP_INVNO")
	private String resumeInvNo;

	@Column(name = "RD_PERMIT_NO_2")
	private String resumeInvNoTwo;

	@Column(name = "RESUP_INV_DATE")
	private String resumeInvDate;

	@Column(name = "ORD_NO")
	private String billOrderNo;

	@Column(name = "HSN_CODE")
	private String hsnCode;

	@Column(name = "IGST_RT")
	private String igst;

	@Column(name = "CGST_RT")
	private String cgst;

	@Column(name = "SGST_RT")
	private String sgst;

	@Column(name = "IGST_AMT")
	private String igstamt;

	@Column(name = "CGST_AMT")
	private String cgstamt;

	@Column(name = "SGST_AMT")
	private String sgstamt;

	@Column(name = "GST_AMT")
	private String gstamt;

	@Column(name = "TRANS_VAL")
	private String pairAmount;

	@Column(name = "FROM_STATE")
	private String fromState;

	@Column(name = "TO_STATE")
	private String toState;

	@Column(name = "FORM_TYPE")
	private String formtype;

	@Column(name = "BILL_CLOSE_STATUS")
	private String billCloseStatus;

	@Column(name = "BILL_ORDER_DATE")
	private String billOrderDate;

	@Column(name = "REGION")
	private String region;

	@Column(name = "SHOP_NAME")
	private String shopName;

	@Column(name = "FREIGHT")
	private String freight;

	@Column(name = "TOTAL_COST")
	private String totalCost;

	@Column(name = "C_PAIR")
	private String cPair;

	@Column(name = "GRNO")
	private String grNo;

	@Column(name = "GR_DATE")
	private String grDate;

	@Column(name = "GRN_DATE")
	private String grnDate;

	@Column(name = "RECEIVE_DATE")
	private String receiveDate;

	@Column(name = "ARTICLE_NAME")
	private String articleName;

	@Column(name = "DISCOUNT_AMT")
	private String discountAmt;

	@Column(name = "TCS_PERCENT")
	private String tcsPercent;

	@Column(name = "CREDIT_NOTE")
	private String creditNote;

	@Column(name = "RDC_PAIR_C ")
	private String rdcPairC;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "PURCHASE_COST")
	private String purchaseCost;

	@Column(name = "TCS_VALUE ")
	private String tcsValue;

	@Column(name = "DIS_AMT_VAL")
	private String discountAmtVal;

	@Column(name = "NO_OF_CARTONS")
	private String noOfCartons;

	@Column(name = "TCS_APPLICABLE")
	private String tcsApplicable;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "RECEIVE_LOC")
	private String receiveLoc;

	@Column(name = "WEEK")
	private String bataWeek;

	@Column(name = "YEAR")
	private String bataYear;

	@Column(name = "CREATED_ON")
	private Date createdOn;
	
	@Column(name = "UPDATE_DT")
	private Date updateDate;
	
	

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

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

	public String getBillCloseYear() {
		return billCloseYear;
	}

	public void setBillCloseYear(String billCloseYear) {
		this.billCloseYear = billCloseYear;
	}

	public String getBillCloseWeek() {
		return billCloseWeek;
	}

	public void setBillCloseWeek(String billCloseWeek) {
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

	public String getBillWeekDay() {
		return billWeekDay;
	}

	public void setBillWeekDay(String billWeekDay) {
		this.billWeekDay = billWeekDay;
	}

	public Date getTranDate() {
		return tranDate;
	}

	public void setTranDate(Date tranDate) {
		this.tranDate = tranDate;
	}

	public String getTranCode() {
		return tranCode;
	}

	public void setTranCode(String tranCode) {
		this.tranCode = tranCode;
	}

	public String getTrInvNO() {
		return trInvNO;
	}

	public void setTrInvNO(String trInvNO) {
		this.trInvNO = trInvNO;
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

	public String getShopNo() {
		return shopNo;
	}

	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}

	public String getDistcode() {
		return distcode;
	}

	public void setDistcode(String distcode) {
		this.distcode = distcode;
	}

	public String getInvType() {
		return invType;
	}

	public void setInvType(String invType) {
		this.invType = invType;
	}

	public String getPair() {
		return pair;
	}

	public void setPair(String pair) {
		this.pair = pair;
	}

	public String getRdcPair() {
		return rdcPair;
	}

	public void setRdcPair(String rdcPair) {
		this.rdcPair = rdcPair;
	}

	public String getPackCaseT() {
		return packCaseT;
	}

	public void setPackCaseT(String packCaseT) {
		this.packCaseT = packCaseT;
	}

	public String getPackCaseB() {
		return packCaseB;
	}

	public void setPackCaseB(String packCaseB) {
		this.packCaseB = packCaseB;
	}

	public String getPackCaseM() {
		return packCaseM;
	}

	public void setPackCaseM(String packCaseM) {
		this.packCaseM = packCaseM;
	}

	public String getPackCaseS() {
		return packCaseS;
	}

	public void setPackCaseS(String packCaseS) {
		this.packCaseS = packCaseS;
	}

	public String getPackCaseC() {
		return packCaseC;
	}

	public void setPackCaseC(String packCaseC) {
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

	public String getCnDate() {
		return cnDate;
	}

	public void setCnDate(String cnDate) {
		this.cnDate = cnDate;
	}

	public String getTrnsportCode() {
		return trnsportCode;
	}

	public void setTrnsportCode(String trnsportCode) {
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

	public String getInvoiceCost() {
		return invoiceCost;
	}

	public void setInvoiceCost(String invoiceCost) {
		this.invoiceCost = invoiceCost;
	}

	public String getRecptInvDate() {
		return recptInvDate;
	}

	public void setRecptInvDate(String recptInvDate) {
		this.recptInvDate = recptInvDate;
	}

	public String getInvoiceNO() {
		return invoiceNO;
	}

	public void setInvoiceNO(String invoiceNO) {
		this.invoiceNO = invoiceNO;
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

	public String getResumeInvDate() {
		return resumeInvDate;
	}

	public void setResumeInvDate(String resumeInvDate) {
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

	public String getIgstamt() {
		return igstamt;
	}

	public void setIgstamt(String igstamt) {
		this.igstamt = igstamt;
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

	public String getGstamt() {
		return gstamt;
	}

	public void setGstamt(String gstamt) {
		this.gstamt = gstamt;
	}

	public String getPairAmount() {
		return pairAmount;
	}

	public void setPairAmount(String pairAmount) {
		this.pairAmount = pairAmount;
	}

	public String getFromState() {
		return fromState;
	}

	public void setFromState(String fromState) {
		this.fromState = fromState;
	}

	public String getToState() {
		return toState;
	}

	public void setToState(String toState) {
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

	public String getBillOrderDate() {
		return billOrderDate;
	}

	public void setBillOrderDate(String billOrderDate) {
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

	public String getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(String totalCost) {
		this.totalCost = totalCost;
	}

	public String getcPair() {
		return cPair;
	}

	public void setcPair(String cPair) {
		this.cPair = cPair;
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

	public String getGrnDate() {
		return grnDate;
	}

	public void setGrnDate(String grnDate) {
		this.grnDate = grnDate;
	}

	public String getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(String receiveDate) {
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

	public String getCreditNote() {
		return creditNote;
	}

	public void setCreditNote(String creditNote) {
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

	public String getPurchaseCost() {
		return purchaseCost;
	}

	public void setPurchaseCost(String purchaseCost) {
		this.purchaseCost = purchaseCost;
	}

	public String getTcsValue() {
		return tcsValue;
	}

	public void setTcsValue(String tcsValue) {
		this.tcsValue = tcsValue;
	}

	public String getDiscountAmtVal() {
		return discountAmtVal;
	}

	public void setDiscountAmtVal(String discountAmtVal) {
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

	public String getReceiveLoc() {
		return receiveLoc;
	}

	public void setReceiveLoc(String receiveLoc) {
		this.receiveLoc = receiveLoc;
	}

	public String getBataWeek() {
		return bataWeek;
	}

	public void setBataWeek(String bataWeek) {
		this.bataWeek = bataWeek;
	}

	public String getBataYear() {
		return bataYear;
	}

	public void setBataYear(String bataYear) {
		this.bataYear = bataYear;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	
}
