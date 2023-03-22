package com.bata.billpunch.model.dto;

import org.springframework.stereotype.Component;

@Component
public class ApprovalDetailsDto {

	private Long billpunchFk;

	private Long loginId;

	private Long approverId;

	private String finUserName;

	private String approverName;

	private String adminOffName;

	private String remarks;

	private String finUserStatus;

	private String approverStatus;

	private String adminStatus;

	public Long getBillpunchFk() {
		return billpunchFk;
	}

	public void setBillpunchFk(Long billpunchFk) {
		this.billpunchFk = billpunchFk;
	}

	public Long getLoginId() {
		return loginId;
	}

	public void setLoginId(Long loginId) {
		this.loginId = loginId;
	}

	public Long getApproverId() {
		return approverId;
	}

	public void setApproverId(Long approverId) {
		this.approverId = approverId;
	}

	public String getFinUserName() {
		return finUserName;
	}

	public void setFinUserName(String finUserName) {
		this.finUserName = finUserName;
	}

	public String getApproverName() {
		return approverName;
	}

	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}

	public String getAdminOffName() {
		return adminOffName;
	}

	public void setAdminOffName(String adminOffName) {
		this.adminOffName = adminOffName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getFinUserStatus() {
		return finUserStatus;
	}

	public void setFinUserStatus(String finUserStatus) {
		this.finUserStatus = finUserStatus;
	}

	public String getApproverStatus() {
		return approverStatus;
	}

	public void setApproverStatus(String approverStatus) {
		this.approverStatus = approverStatus;
	}

	public String getAdminStatus() {
		return adminStatus;
	}

	public void setAdminStatus(String adminStatus) {
		this.adminStatus = adminStatus;
	}
	
	

}
