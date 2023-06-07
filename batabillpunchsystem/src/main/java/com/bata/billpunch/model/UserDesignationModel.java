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

@Entity(name = "UserDesignationModel")
@Table(name = "TM_USER_DESIGNATION")
public class UserDesignationModel implements Serializable {

	private static final long serialVersionUID = 187767777L;

	
	@Id
	@Column(name = "DEGN_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long degnId;

	@Column(name = "USER_DEGN_KEY")
	private String userDegnKey;
	
	@Column(name = "USER_DEGN")
	private String userDegn;
	
	@Column(name = "UPDATE_DT")
	private Date updateDate;
	
	

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Long getDegnId() {
		return degnId;
	}

	public void setDegnId(Long degnId) {
		this.degnId = degnId;
	}

	public String getUserDegnKey() {
		return userDegnKey;
	}

	public void setUserDegnKey(String userDegnKey) {
		this.userDegnKey = userDegnKey;
	}

	public String getUserDegn() {
		return userDegn;
	}

	public void setUserDegn(String userDegn) {
		this.userDegn = userDegn;
	}

	
}
