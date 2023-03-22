package com.bata.billpunch.model.dto;

import java.util.List;

import com.bata.billpunch.model.BillPunchDetailsModel;

public class BillPunchCheckBoxDto {
	
	private String message;
	
	private List<BillPunchDetailsModel> listdata;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<BillPunchDetailsModel> getListdata() {
		return listdata;
	}

	public void setListdata(List<BillPunchDetailsModel> listdata) {
		this.listdata = listdata;
	}
	
	
}
