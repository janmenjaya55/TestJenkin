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

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity(name = "RdcMasterModel")
@Table(name = "TM_RDC_MASTER_DTLS")
public class RdcMasterModel implements Serializable {

	private static final long serialVersionUID = 13332225667L;

	@Id
	@Column(name = "RDC_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long rdcId;

	@Column(name = "RDC_NAME")
	private String rdcName;

	@Column(name = "RDC_LOCATION")
	private String rdcLocation;

	@Column(name = "RDC_CODE")
	private String rdcCode;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SUBMISSION_DATE")
	private Calendar submissionDate;

	public Long getRdcId() {
		return rdcId;
	}

	public void setRdcId(Long rdcId) {
		this.rdcId = rdcId;
	}

	public String getRdcName() {
		return rdcName;
	}

	public void setRdcName(String rdcName) {
		this.rdcName = rdcName;
	}

	public String getRdcLocation() {
		return rdcLocation;
	}

	public void setRdcLocation(String rdcLocation) {
		this.rdcLocation = rdcLocation;
	}

	public String getRdcCode() {
		return rdcCode;
	}

	public void setRdcCode(String rdcCode) {
		this.rdcCode = rdcCode;
	}

	public Calendar getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(Calendar submissionDate) {
		this.submissionDate = submissionDate;
	}

}
