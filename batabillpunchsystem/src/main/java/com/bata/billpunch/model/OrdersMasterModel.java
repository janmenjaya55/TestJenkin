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
@Entity(name = "OrdersMasterModel")
@Table(name = "TM_ORDERS_MASTER_DTLS")
public class OrdersMasterModel implements Serializable {
	

	private static final long serialVersionUID = 13332225667L;

	@Id
	@Column(name = "ORDER_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;

	@Column(name = "CONC")
	private String conc;
	
	@Column(name = "ORDER_TYPE")
	private String orderType;
	
	@Column(name = "ORDERNO")
	private String orderno;
	
	@Column(name = "ORDER_YRWK")
	private String orderYrwk;
	
	@Column(name = "DELDATE_FROM")
	private Date deldateFrom;
	
	@Column(name = "DELDATE_TO")
	private Date deldateTo;
	
	@Column(name = "DELYRWK_FROM")
	private String delyrwkFrom;
	
	@Column(name = "DELYRWK_TO")
	private String delyrwkTo;
	
	@Column(name = "ARTNO")
	private String artno;
	
	@Column(name = "RDCNO")
	private String rdcno;
	
	@Column(name = "PARTY_CODE")
	private String partyCode;
	
	@Column(name = "ORDER_PR")
	private String orderPr;
	
	@Column(name = "SUPPLY_PR")
	private String supplyPr;
	
	@Column(name = "RDCRECV_PR")
	private String rdcrecvPr;
	
	@Column(name = "UNITBAL_PR")
	private String unitbalPr;
	
	@Column(name = "RDCBAL_PR")
	private String rdcbalPr;
	
	@Column(name = "CANCEL_PR")
	private String cancelPr;
	
	@Column(name = "APP_STAT")
	private String appStat;
	
	@Column(name = "APP_DATE")
	private Date appDate;
	
	@Column(name = "PURPRICE")
	private String purprice;
	
	@Column(name = "MRP")
	private String mrp;
	
	@Column(name = "STDCOST")
	private String stdcost;
	
	@Column(name = "UPDATE_DT")
	private Date updateDt;
	
	@Column(name = "UPDATE_WEEK")
	private String updateWk;
	
	@Column(name = "UPDATE_YEAR")
	private String updateYr;
	
	@Column(name = "UPDATE_DATETIME")
	private Date updateDate;
	
	

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getConc() {
		return conc;
	}

	public void setConc(String conc) {
		this.conc = conc;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public String getOrderYrwk() {
		return orderYrwk;
	}

	public void setOrderYrwk(String orderYrwk) {
		this.orderYrwk = orderYrwk;
	}

	public Date getDeldateFrom() {
		return deldateFrom;
	}

	public void setDeldateFrom(Date deldateFrom) {
		this.deldateFrom = deldateFrom;
	}

	public Date getDeldateTo() {
		return deldateTo;
	}

	public void setDeldateTo(Date deldateTo) {
		this.deldateTo = deldateTo;
	}

	public String getDelyrwkFrom() {
		return delyrwkFrom;
	}

	public void setDelyrwkFrom(String delyrwkFrom) {
		this.delyrwkFrom = delyrwkFrom;
	}

	public String getDelyrwkTo() {
		return delyrwkTo;
	}

	public void setDelyrwkTo(String delyrwkTo) {
		this.delyrwkTo = delyrwkTo;
	}

	public String getArtno() {
		return artno;
	}

	public void setArtno(String artno) {
		this.artno = artno;
	}

	public String getRdcno() {
		return rdcno;
	}

	public void setRdcno(String rdcno) {
		this.rdcno = rdcno;
	}

	public String getPartyCode() {
		return partyCode;
	}

	public void setPartyCode(String partyCode) {
		this.partyCode = partyCode;
	}

	public String getOrderPr() {
		return orderPr;
	}

	public void setOrderPr(String orderPr) {
		this.orderPr = orderPr;
	}

	public String getSupplyPr() {
		return supplyPr;
	}

	public void setSupplyPr(String supplyPr) {
		this.supplyPr = supplyPr;
	}

	public String getRdcrecvPr() {
		return rdcrecvPr;
	}

	public void setRdcrecvPr(String rdcrecvPr) {
		this.rdcrecvPr = rdcrecvPr;
	}

	public String getUnitbalPr() {
		return unitbalPr;
	}

	public void setUnitbalPr(String unitbalPr) {
		this.unitbalPr = unitbalPr;
	}

	public String getRdcbalPr() {
		return rdcbalPr;
	}

	public void setRdcbalPr(String rdcbalPr) {
		this.rdcbalPr = rdcbalPr;
	}

	public String getCancelPr() {
		return cancelPr;
	}

	public void setCancelPr(String cancelPr) {
		this.cancelPr = cancelPr;
	}

	public String getAppStat() {
		return appStat;
	}

	public void setAppStat(String appStat) {
		this.appStat = appStat;
	}

	public Date getAppDate() {
		return appDate;
	}

	public void setAppDate(Date appDate) {
		this.appDate = appDate;
	}

	public String getPurprice() {
		return purprice;
	}

	public void setPurprice(String purprice) {
		this.purprice = purprice;
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

	public Date getUpdateDt() {
		return updateDt;
	}

	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}

	public String getUpdateWk() {
		return updateWk;
	}

	public void setUpdateWk(String updateWk) {
		this.updateWk = updateWk;
	}

	public String getUpdateYr() {
		return updateYr;
	}

	public void setUpdateYr(String updateYr) {
		this.updateYr = updateYr;
	}
	
	

}
