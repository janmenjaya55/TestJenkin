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

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity(name = "ApprovalDetailsModel")
@Table(name = "TL_APPROVAL_DETAILS")
public class ApprovalDetailsModel implements Serializable {

	private static final long serialVersionUID = 13332225667L;

	@Id
	@Column(name = "APP_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long appId;

	@Column(name = "BILL_ID_FK")
	private Long billpunchFk;

	@Column(name = "LOGIN_ID")
	private Long loginId;

	@Column(name = "APPROVER_ID")
	private Long approverId;
	
	@Column(name = "USER_ROLE")
	private String userRole;
	
	@Column(name = "BILL_UNIQUE_CODE")
	private String billUniqueCode;
	
	@Column(name = "FIN_USER_NAME")
	private String finUserName;
	
	@Column(name = "APPROVER_NAME")
	private String approverName;
	
	@Column(name = "ADMIN_OFF_NAME")
	private String adminOffName;
	
	@Column(name = "REMARKS")
	private String remarks;

	@Column(name = "FIN_USER_STATUS")
	private String finUserStatus;
	
	@Column(name = "APPROVER_STATUS")
	private String approverStatus;
	
	@Column(name = "ADMIN_STATUS")
	private String adminStatus;

	@Column(name = "MODIFIED_ON")
	private Calendar modifiedOn;
	
	
	@Column(name = "RDC_CODE")
	private String rdcCode;

	@Column(name = "C_YEAR")
	private Integer billCloseYear;

	@Column(name = "C_WEEK")
	private Integer billCloseWeek;

	@Column(name = "YR")
	private String billWeekYear;

	@Column(name = "WK")
	private String weekYear;

	@Column(name = "C_WEEK_DAY")
	private Integer billWeekDay;

	@Column(name = "TRAN_DATE")
	private Date tranDate;

	@Column(name = "TRAN_CODE")
	private Integer tranCode;

	@Column(name = "TR_INV_NO")
	private String trInvNO;

	@Column(name = "ART_CODE")
	private String articleCode;

	@Column(name = "SIZE_CODE")
	private Integer sizeCode;

	@Column(name = "DEST_SHOP_NO")
	private String shopNo;

	@Column(name = "DISTRC_CODE")
	private Integer distcode;

	@Column(name = "INV_TYPE")
	private Integer invType;

	@Column(name = "TOT_PAIRS")
	private Integer pair;

	@Column(name = "NO_PAIRS")
	private Integer rdcPair;

	@Column(name = "PACKING_CASES_T")
	private Integer packCaseT;

	@Column(name = "PACKING_CASES_B")
	private Integer packCaseB;

	@Column(name = "PACKING_CASES_M")
	private Integer packCaseM;

	@Column(name = "PACKING_CASES_S")
	private Integer packCaseS;

	@Column(name = "PACKING_CASES_C")
	private Integer packCaseC;

	@Column(name = "DC_CODE")
	private String dcCode;

	@Column(name = "REC_STAT")
	private String recStatus;

	@Column(name = "PARTY")
	private String party;
	
	@Column(name = "PARTY_CODE")
	private String partyCode;

	@Column(name = "PARTY_NAME")
	private String partyName;

	@Column(name = "CN_NO")
	private String cnNo;

	@Column(name = "CN_DATE")
	private Date cnDate;

	@Column(name = "TRNSPRT_CODE")
	private Integer trnsportCode;

	@Column(name = "RD_PERMIT_NO")
	private String rdpermitNo;

	@Column(name = "STATE_NAME")
	private String stateName;

	@Column(name = "PARTY_STATE")
	private String stateCode;

	@Column(name = "PRCH_BIL_VAL")
	private Double invoiceCost;

	@Column(name = "RCPT_INV_DATE")
	private Date recptInvDate;

	@Column(name = "RCPT_INV_NO")
	private String invoiceNO;

	@Column(name = "RESUP_INVNO")
	private String resumeInvNo;

	@Column(name = "RD_PERMIT_NO_2")
	private String resumeInvNoTwo;

	@Column(name = "RESUP_INV_DATE")
	private Date resumeInvDate;

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
	private Double igstamt;

	@Column(name = "CGST_AMT")
	private Double cgstamt;

	@Column(name = "SGST_AMT")
	private Double sgstamt;

	@Column(name = "GST_AMT")
	private Double gstamt;

	@Column(name = "TRANS_VAL")
	private Double pairAmount;

	@Column(name = "FROM_STATE")
	private Integer fromState;

	@Column(name = "TO_STATE")
	private Integer toState;

	@Column(name = "FORM_TYPE")
	private String formtype;

	@Column(name = "BILL_CLOSE_STATUS")
	private String billCloseStatus;

	@Column(name = "BILL_ORDER_DATE")
	private Date billOrderDate;

	@Column(name = "REGION")
	private String region;

	@Column(name = "SHOP_NAME")
	private String shopName;

	@Column(name = "FREIGHT")
	private String freight;

	@Column(name = "TOTAL_COST")
	private Double totalCost;

	@Column(name = "C_PAIR")
	private Integer cPair;

	@Column(name = "GRNO")
	private String grNo;

	@Column(name = "GR_DATE")
	private Date grDate;

	@Column(name = "GRN_DATE")
	private Date grnDate;

	@Column(name = "RECEIVE_DATE")
	private Date receiveDate;

	@Column(name = "ARTICLE_NAME")
	private String articleName;


	@Column(name = "DISCOUNT_AMT")
	private String discountAmt;
	
	@Column(name = "TCS_PERCENT")
	private String tcsPercent;

	@Column(name = "CREDIT_NOTE")
	private Double creditNote;

	@Column(name = "RDC_PAIR_C ")
	private String rdcPairC;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "PURCHASE_COST")
	private Double purchaseCost;

	@Column(name = "TCS_VALUE ")
	private Double tcsValue;

	@Column(name = "DIS_AMT_VAL")
	private Double discountAmtVal;

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

	@CreationTimestamp
	//@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_ON")
	private Calendar createdOn;
	
	@Column(name = "UPDATE_DT")
	private Date updateDate;
	
	

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public Long getBillpunchFk() {
		return billpunchFk;
	}

	public void setBillpunchFk(Long billpunchFk) {
		this.billpunchFk = billpunchFk;
	}

	public Long getLoginId() {
		return loginId;
	}

	public void setLoginId(Long loginId) {
		this.loginId = loginId;
	}

	public Long getApproverId() {
		return approverId;
	}

	public void setApproverId(Long approverId) {
		this.approverId = approverId;
	}

	public String getFinUserName() {
		return finUserName;
	}

	public void setFinUserName(String finUserName) {
		this.finUserName = finUserName;
	}

	public String getApproverName() {
		return approverName;
	}

	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}

	public String getAdminOffName() {
		return adminOffName;
	}

	public void setAdminOffName(String adminOffName) {
		this.adminOffName = adminOffName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getFinUserStatus() {
		return finUserStatus;
	}

	public void setFinUserStatus(String finUserStatus) {
		this.finUserStatus = finUserStatus;
	}

	public String getApproverStatus() {
		return approverStatus;
	}

	public void setApproverStatus(String approverStatus) {
		this.approverStatus = approverStatus;
	}

	public String getAdminStatus() {
		return adminStatus;
	}

	public void setAdminStatus(String adminStatus) {
		this.adminStatus = adminStatus;
	}

	public Calendar getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Calendar modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public Calendar getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Calendar createdOn) {
		this.createdOn = createdOn;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getBillUniqueCode() {
		return billUniqueCode;
	}

	public void setBillUniqueCode(String billUniqueCode) {
		this.billUniqueCode = billUniqueCode;
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



}
