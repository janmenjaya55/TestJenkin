package com.bata.billpunch.model.dto;

import java.util.List;

public class StrazaReqModel {

	
public List<String> partycode;
	
	public String fromwk;
	
	public String towk;
	
	public String yr;
	
	public String constant;

	public List<String> getPartycode() {
		return partycode;
	}

	public void setPartycode(List<String> partycode) {
		this.partycode = partycode;
	}

	public String getFromwk() {
		return fromwk;
	}

	public void setFromwk(String fromwk) {
		this.fromwk = fromwk;
	}

	public String getTowk() {
		return towk;
	}

	public void setTowk(String towk) {
		this.towk = towk;
	}

	public String getYr() {
		return yr;
	}

	public void setYr(String yr) {
		this.yr = yr;
	}

	public String getConstant() {
		return constant;
	}

	public void setConstant(String constant) {
		this.constant = constant;
	}
	
	
	
}
