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
@Entity(name = "BillPunchStrazaReportModel")
@Table(name = "TL_BILL_STRAZA_REPORT_DTLS")
public class BillPunchStrazaReportModel implements Serializable {

	private static final long serialVersionUID = 13332225667L;

	@Id
	@Column(name = "STRAZA_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long strazaId;

	@Column(name = "WEEK")
	private String bookWeek;

	@Column(name = "YEAR")
	private String year;

	@Column(name = "GR_NO")
	private String grnoOne;

	@Column(name = "GR_DATE")
	private String grdateNew;

	@Column(name = "TOTAL_COST")
	private String totalcost;

	@Column(name = "FREIGHT")
	private String freight;

	@Column(name = "BILL_ORDER_DATE")
	private String billOrderDate;

	@Column(name = "ORD_NO")
	private String billOrderNo;

	@Column(name = "RCPT_INV_DATE")
	private String grDate;

	@Column(name = "PARTY_CODE")
	private String partyCode;

	@Column(name = "INVOICE_NO")
	private String invoiceNO;

	@Column(name = "PARTY_NAME")
	private String partyName;

	@Column(name = "PURCHASE_COST")
	private String purchaseCost;

	@Column(name = "RDC_CODE")
	private String rdcCode;

	@Column(name = "PAIR")
	private String pair;

	@Column(name = "SHOP_NAME")
	private String shopName;

	@Column(name = "DEST_SHOP_NO")
	private String shopNo;

	@Column(name = "PRCH_BIL_VAL")
	private String invoiceCost;

	@Column(name = "CN_NO")
	private String grNo;

	@Column(name = "IGST_AMT")
	private String igstamt;

	@Column(name = "CGST_AMT")
	private String cgstamt;

	@Column(name = "SGST_AMT")
	private String sgstamt;

	@Column(name = "WK")
	private String receiveWk;

	@Column(name = "RCPT_INV_DATE_ONE")
	private String recptInvDate;

	@Column(name = "RECEIVE_LOC")
	private String receiveLoc;

	@Column(name = "MRP")
	private String mrp;

	@Column(name = "STDCOST")
	private String stdcost;

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

	public Long getStrazaId() {
		return strazaId;
	}

	public void setStrazaId(Long strazaId) {
		this.strazaId = strazaId;
	}

	public String getBookWeek() {
		return bookWeek;
	}

	public void setBookWeek(String bookWeek) {
		this.bookWeek = bookWeek;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getGrnoOne() {
		return grnoOne;
	}

	public void setGrnoOne(String grnoOne) {
		this.grnoOne = grnoOne;
	}

	public String getGrdateNew() {
		return grdateNew;
	}

	public void setGrdateNew(String grdateNew) {
		this.grdateNew = grdateNew;
	}

	public String getTotalcost() {
		return totalcost;
	}

	public void setTotalcost(String totalcost) {
		this.totalcost = totalcost;
	}

	public String getFreight() {
		return freight;
	}

	public void setFreight(String freight) {
		this.freight = freight;
	}

	public String getBillOrderDate() {
		return billOrderDate;
	}

	public void setBillOrderDate(String billOrderDate) {
		this.billOrderDate = billOrderDate;
	}

	public String getBillOrderNo() {
		return billOrderNo;
	}

	public void setBillOrderNo(String billOrderNo) {
		this.billOrderNo = billOrderNo;
	}

	public String getGrDate() {
		return grDate;
	}

	public void setGrDate(String grDate) {
		this.grDate = grDate;
	}

	public String getPartyCode() {
		return partyCode;
	}

	public void setPartyCode(String partyCode) {
		this.partyCode = partyCode;
	}

	public String getInvoiceNO() {
		return invoiceNO;
	}

	public void setInvoiceNO(String invoiceNO) {
		this.invoiceNO = invoiceNO;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public String getPurchaseCost() {
		return purchaseCost;
	}

	public void setPurchaseCost(String purchaseCost) {
		this.purchaseCost = purchaseCost;
	}

	public String getRdcCode() {
		return rdcCode;
	}

	public void setRdcCode(String rdcCode) {
		this.rdcCode = rdcCode;
	}

	public String getPair() {
		return pair;
	}

	public void setPair(String pair) {
		this.pair = pair;
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

	public String getInvoiceCost() {
		return invoiceCost;
	}

	public void setInvoiceCost(String invoiceCost) {
		this.invoiceCost = invoiceCost;
	}

	public String getGrNo() {
		return grNo;
	}

	public void setGrNo(String grNo) {
		this.grNo = grNo;
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

	public String getReceiveWk() {
		return receiveWk;
	}

	public void setReceiveWk(String receiveWk) {
		this.receiveWk = receiveWk;
	}

	public String getRecptInvDate() {
		return recptInvDate;
	}

	public void setRecptInvDate(String recptInvDate) {
		this.recptInvDate = recptInvDate;
	}

	public String getReceiveLoc() {
		return receiveLoc;
	}

	public void setReceiveLoc(String receiveLoc) {
		this.receiveLoc = receiveLoc;
	}

	public String getMrp() {
		return mrp;
	}

	public void setMrp(String mrp) {
		this.mrp = mrp;
	}

	public String getStdcost() {
		return stdcost;
	}

	public void setStdcost(String stdcost) {
		this.stdcost = stdcost;
	}

}
