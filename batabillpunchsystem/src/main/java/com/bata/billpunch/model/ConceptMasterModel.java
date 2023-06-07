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
@Entity(name = "ConceptMasterModel")
@Table(name = "TM_CONCEPT_MASTER_DTLS")
public class ConceptMasterModel implements Serializable {

	private static final long serialVersionUID = 13332225667L;

	@Id
	@Column(name = "CON_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long conId;

	@Column(name = "CONCEPT_ID")
	private Long conceptid;

	@Column(name = "DIVISION")
	private String division;

	@Column(name = "CONCEPT")
	private String concept;

	@Column(name = "CONCEPT_CODE")
	private String conceptcode;
	
	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "ORDER_PREFIX_IN_PO_SYSTEM")
	private String orderprefixinposystem;
	
	@Column(name = "CONC_IN_JDE_E1")
	private String concinjdee1;

	@Column(name = "ORDER_PREFIX_JDE_E1")
	private String orderprefixjdee1;
	
	@Column(name = "USED_ON_PO")
	private String usedonpo;

	@Column(name = "USED_ON_SO")
	private String usedonso;
	
	@Column(name = "DIST_CONCEPT_CODE")
	private String distconceptcode;
	
	@Column(name = "UPDATE_DT")
	private Date updateDate;
	
	

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Long getConId() {
		return conId;
	}

	public void setConId(Long conId) {
		this.conId = conId;
	}

	

	public Long getConceptid() {
		return conceptid;
	}

	public void setConceptid(Long conceptid) {
		this.conceptid = conceptid;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	

	public String getConcept() {
		return concept;
	}

	public void setConcept(String concept) {
		this.concept = concept;
	}

	public String getConceptcode() {
		return conceptcode;
	}

	public void setConceptcode(String conceptcode) {
		this.conceptcode = conceptcode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOrderprefixinposystem() {
		return orderprefixinposystem;
	}

	public void setOrderprefixinposystem(String orderprefixinposystem) {
		this.orderprefixinposystem = orderprefixinposystem;
	}

	public String getConcinjdee1() {
		return concinjdee1;
	}

	public void setConcinjdee1(String concinjdee1) {
		this.concinjdee1 = concinjdee1;
	}

	public String getOrderprefixjdee1() {
		return orderprefixjdee1;
	}

	public void setOrderprefixjdee1(String orderprefixjdee1) {
		this.orderprefixjdee1 = orderprefixjdee1;
	}

	public String getUsedonpo() {
		return usedonpo;
	}

	public void setUsedonpo(String usedonpo) {
		this.usedonpo = usedonpo;
	}

	public String getUsedonso() {
		return usedonso;
	}

	public void setUsedonso(String usedonso) {
		this.usedonso = usedonso;
	}

	public String getDistconceptcode() {
		return distconceptcode;
	}

	public void setDistconceptcode(String distconceptcode) {
		this.distconceptcode = distconceptcode;
	}
	
	
}
