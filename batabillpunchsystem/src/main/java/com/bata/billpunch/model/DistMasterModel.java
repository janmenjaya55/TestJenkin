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
@Entity(name = "DistMasterModel")
@Table(name = "TM_DIST_MASTER_DTLS")
public class DistMasterModel implements Serializable {

	private static final long serialVersionUID = 13332225667L;

	@Id
	@Column(name = "DIST_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long distId;

	@Column(name = "DIST_REGION_CONTROLLING_GROUP")
	private String distregioncontrollinggroup;

	@Column(name = "DISTRICT_CODE")
	private String districtcode;
	
	@Column(name = "DIST_CONCEPT_CONTROLLING_GROUP")
	private String distconceptcontrollinggroup;

	@Column(name = "DIST_CONCEPT_NAME")
	private String distconceptname;
	
	@Column(name = "DIST_CONCEPT_CODE")
	private String distconceptcode;

	@Column(name = "DIST_REMARKS")
	private String distremarks;
	
	@Column(name = "DIST_REGION_CODE")
	private String distregioncode;
	
	@Column(name = "UPDATE_DT")
	private Date updateDate;
	
	

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Long getDistId() {
		return distId;
	}

	public void setDistId(Long distId) {
		this.distId = distId;
	}

	public String getDistregioncontrollinggroup() {
		return distregioncontrollinggroup;
	}

	public void setDistregioncontrollinggroup(String distregioncontrollinggroup) {
		this.distregioncontrollinggroup = distregioncontrollinggroup;
	}

	public String getDistrictcode() {
		return districtcode;
	}

	public void setDistrictcode(String districtcode) {
		this.districtcode = districtcode;
	}

	public String getDistconceptcontrollinggroup() {
		return distconceptcontrollinggroup;
	}

	public void setDistconceptcontrollinggroup(String distconceptcontrollinggroup) {
		this.distconceptcontrollinggroup = distconceptcontrollinggroup;
	}

	

	public String getDistconceptname() {
		return distconceptname;
	}

	public void setDistconceptname(String distconceptname) {
		this.distconceptname = distconceptname;
	}

	public String getDistconceptcode() {
		return distconceptcode;
	}

	public void setDistconceptcode(String distconceptcode) {
		this.distconceptcode = distconceptcode;
	}

	public String getDistremarks() {
		return distremarks;
	}

	public void setDistremarks(String distremarks) {
		this.distremarks = distremarks;
	}

	public String getDistregioncode() {
		return distregioncode;
	}

	public void setDistregioncode(String distregioncode) {
		this.distregioncode = distregioncode;
	}

	
}
