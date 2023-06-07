package com.bata.billpunch.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity(name = "RdcMappingModel")
@Table(name = "TL_RDC_MAPPING_DTLS")
public class RdcMappingModel implements Serializable {

	private static final long serialVersionUID = 13332225667L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "RDCNO")
	private String rdcno;

	@Column(name = "RDCNAME")
	private String rdcname;

	@Column(name = "CONCEPT")
	private String concept;

	@Column(name = "REGION")
	private String region;

	@Column(name = "ACTI_FLAG")
	private String actiflag;

	@Column(name = "MERGE_RDC_CODE")
	private String mergerdccode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRdcno() {
		return rdcno;
	}

	public void setRdcno(String rdcno) {
		this.rdcno = rdcno;
	}

	public String getRdcname() {
		return rdcname;
	}

	public void setRdcname(String rdcname) {
		this.rdcname = rdcname;
	}

	public String getConcept() {
		return concept;
	}

	public void setConcept(String concept) {
		this.concept = concept;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getActiflag() {
		return actiflag;
	}

	public void setActiflag(String actiflag) {
		this.actiflag = actiflag;
	}

	public String getMergerdccode() {
		return mergerdccode;
	}

	public void setMergerdccode(String mergerdccode) {
		this.mergerdccode = mergerdccode;
	}

}
