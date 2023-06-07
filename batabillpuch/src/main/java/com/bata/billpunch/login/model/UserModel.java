package com.bata.billpunch.login.model;


import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.sun.istack.Nullable;

@Entity
public class UserModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 13332225667L;
	
	@Id
	@Column(name = "USER_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long userId;
	
	private String username;
	
    private String password;
    
    private String fullname;
    
    private String userrole;
    
	private String employId;
	
    private String emailId;
    
    private String loginId;
    
    private String mobNo;
    
    private String designation;
    
    private String department;
    
    private String role;
    
    private String createdBy;
    
    private String status;
    
    private String filename;
    
    private String userOtp;
    
    @Nullable
    private int userLevel;
    
    
    @CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar createdOn;


    
    public String getFilename() {
		return filename;
	}


	public void setFilename(String filename) {
		this.filename = filename;
	}


	public UserModel() {
    }


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getFullname() {
		return fullname;
	}


	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
    
	public String getUserrole() {
		return userrole;
	}


	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}


	public String getEmployId() {
		return employId;
	}


	public void setEmployId(String employId) {
		this.employId = employId;
	}


	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public String getLoginId() {
		return loginId;
	}


	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}


	public String getMobNo() {
		return mobNo;
	}


	public void setMobNo(String mobNo) {
		this.mobNo = mobNo;
	}


	public String getDesignation() {
		return designation;
	}


	public void setDesignation(String designation) {
		this.designation = designation;
	}


	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Calendar getCreatedOn() {
		return createdOn;
	}


	public void setCreatedOn(Calendar createdOn) {
		this.createdOn = createdOn;
	}


	public int getUserLevel() {
		return userLevel;
	}


	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}


	public String getUserOtp() {
		return userOtp;
	}


	public void setUserOtp(String userOtp) {
		this.userOtp = userOtp;
	}


	public UserModel(String username, String password, String fullname, String userrole) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.userrole = userrole;
    }
    
}
