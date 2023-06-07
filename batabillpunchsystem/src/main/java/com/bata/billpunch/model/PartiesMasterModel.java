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
@Entity(name = "PartiesMasterModel")
@Table(name = "TM_PARTIES_MASTER_DTLS")
public class PartiesMasterModel implements Serializable {

	private static final long serialVersionUID = 13332225667L;

	@Id
	@Column(name = "PART_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long partId;

	@Column(name = "PARTY_NO")
	private String partyno;

	@Column(name = "PARTY_SHORT_NAME")
	private String partyshortname;

	@Column(name = "PARTY_STATE_CODE")
	private String partystatecode;

	@Column(name = "PARTY_FULL_NAME")
	private String partyfullname;

	@Column(name = "PARTY_PAN_CODE")
	private String partypancode;

	@Column(name = "PARTY_GSTIN_NO")
	private String partygstinno;

	@Column(name = "PARTY_DISCOUNT")
	private String partydiscount;

	@Column(name = "PARTY_PAYMENT_TERMS")
	private String partypaymentterms;
	
	@Column(name = "ADONIS_CODE")
	private String adonisCode;
	
	@Column(name = "UPDATE_DT")
	private Date updateDate;
	
	

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Long getPartId() {
		return partId;
	}

	public void setPartId(Long partId) {
		this.partId = partId;
	}

	public String getPartyno() {
		return partyno;
	}

	public void setPartyno(String partyno) {
		this.partyno = partyno;
	}

	
	public String getPartyshortname() {
		return partyshortname;
	}

	public void setPartyshortname(String partyshortname) {
		this.partyshortname = partyshortname;
	}

	public String getPartystatecode() {
		return partystatecode;
	}

	public void setPartystatecode(String partystatecode) {
		this.partystatecode = partystatecode;
	}

	public String getPartyfullname() {
		return partyfullname;
	}

	public void setPartyfullname(String partyfullname) {
		this.partyfullname = partyfullname;
	}

	public String getPartypancode() {
		return partypancode;
	}

	public void setPartypancode(String partypancode) {
		this.partypancode = partypancode;
	}

	public String getPartygstinno() {
		return partygstinno;
	}

	public void setPartygstinno(String partygstinno) {
		this.partygstinno = partygstinno;
	}

	public String getPartydiscount() {
		return partydiscount;
	}

	public void setPartydiscount(String partydiscount) {
		this.partydiscount = partydiscount;
	}

	public String getPartypaymentterms() {
		return partypaymentterms;
	}

	public void setPartypaymentterms(String partypaymentterms) {
		this.partypaymentterms = partypaymentterms;
	}

	public String getAdonisCode() {
		return adonisCode;
	}

	public void setAdonisCode(String adonisCode) {
		this.adonisCode = adonisCode;
	}

	
}
