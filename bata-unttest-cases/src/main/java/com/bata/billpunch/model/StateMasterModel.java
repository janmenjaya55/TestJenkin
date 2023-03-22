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
@Entity(name = "StateMasterModel")
@Table(name = "TM_STATE_MASTER_DTLS")
public class StateMasterModel implements Serializable {

	private static final long serialVersionUID = 13332225667L;

	@Id
	@Column(name = "STATE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long stateId;

	@Column(name = "STATENAME")
	private String stateName;
	
	@Column(name = "BATACODE")
	private String bataCode;

	@Column(name = "STATECODE")
	private String stateCode;
	
	@Column(name = "GSTIN")
	private String gstIn;

	@Column(name = "GSTDATE")
	private String gstDate;
	
	@Column(name = "ST_SHTCODE")
	private String stShtCode;

	@Column(name = "PPOB_ADD1")
	private String ppobAddOne;
	
	@Column(name = "PPOB_ADD2")
	private String ppobAddTwo;

	@Column(name = "PPOB_ADD3")
	private String ppobAddThree;
	
	@Column(name = "PPOB_ADD4")
	private String ppobAddFour;

	@Column(name = "PPOB_CITY")
	private String ppobCity;
	
	@Column(name = "PPOB_PIN")
	private String ppobPin;

	@Column(name = "ST_TYPE")
	private String stType;
	
	@Column(name = "UPDATE_DT")
	private Date updateDate;
	
	

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getBataCode() {
		return bataCode;
	}

	public void setBataCode(String bataCode) {
		this.bataCode = bataCode;
	}

	public String getGstIn() {
		return gstIn;
	}

	public void setGstIn(String gstIn) {
		this.gstIn = gstIn;
	}

	

	public String getGstDate() {
		return gstDate;
	}

	public void setGstDate(String gstDate) {
		this.gstDate = gstDate;
	}

	public String getStShtCode() {
		return stShtCode;
	}

	public void setStShtCode(String stShtCode) {
		this.stShtCode = stShtCode;
	}

	public String getPpobAddOne() {
		return ppobAddOne;
	}

	public void setPpobAddOne(String ppobAddOne) {
		this.ppobAddOne = ppobAddOne;
	}

	public String getPpobAddTwo() {
		return ppobAddTwo;
	}

	public void setPpobAddTwo(String ppobAddTwo) {
		this.ppobAddTwo = ppobAddTwo;
	}

	public String getPpobAddThree() {
		return ppobAddThree;
	}

	public void setPpobAddThree(String ppobAddThree) {
		this.ppobAddThree = ppobAddThree;
	}

	public String getPpobAddFour() {
		return ppobAddFour;
	}

	public void setPpobAddFour(String ppobAddFour) {
		this.ppobAddFour = ppobAddFour;
	}

	public String getPpobCity() {
		return ppobCity;
	}

	public void setPpobCity(String ppobCity) {
		this.ppobCity = ppobCity;
	}

	public String getPpobPin() {
		return ppobPin;
	}

	public void setPpobPin(String ppobPin) {
		this.ppobPin = ppobPin;
	}

	public String getStType() {
		return stType;
	}

	public void setStType(String stType) {
		this.stType = stType;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	

}
