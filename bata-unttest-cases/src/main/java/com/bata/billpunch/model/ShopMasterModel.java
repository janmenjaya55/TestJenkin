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
@Entity(name = "ShopMasterModel")
@Table(name = "TM_SHOP_MASTER_DTLS")
public class ShopMasterModel implements Serializable {

	private static final long serialVersionUID = 13332225667L;

	@Id
	@Column(name = "SHOP_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long shopId;

	@Column(name = "DISTRICT_NO")
	private String districtno;

	@Column(name = "SHOP_NO")
	private String shopno;

	@Column(name = "SHOP_NAME")
	private String shopname;

	@Column(name = "SHOP_AGENCY_CODE")
	private String shopagencycode;

	@Column(name = "SHOP_OPEN_YR_WK")
	private String shopopenyrwk;

	@Column(name = "SHOP_CLOSE_YR_WK")
	private String shopcloseyrwk;

	@Column(name = "SHOP_ADDRESS_1")
	private String shopaddressone;

	@Column(name = "SHOP_ADDRESS_2")
	private String shopaddresstwo;
	
	@Column(name = "SHOP_ADDRESS_3")
	private String shopaddressthree;
	
	@Column(name = "SHOP_ADDRESS_4")
	private String shopaddressfour;
	
	@Column(name = "SHOP_ADDRESS_5")
	private String shopaddressfive;
	
	@Column(name = "SHOP_ADDRESS_6")
	private String shopaddresssix;
	
	@Column(name = "STATE_CODE")
	private String statecode;
	
	@Column(name = "NEW_DIST_ZONE_CODE")
	private String newdistzonecode;
	
	@Column(name = "W_HOUSE_CODE")
	private String whousecode;
	
	@Column(name = "STORE_CLASS")
	private String storeclass;
	
	@Column(name = "SHOP_CITY_NAME")
	private String shopcityname;
	
	@Column(name = "E1_LOC_NO")
	private String eonelocno;
	
	@Column(name = "OPERATION_CLOSE_YRWK")
	private String operationcloseyrwk;
	
	@Column(name = "UPDATE_DT")
	private Date updateDate;
	
	

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getDistrictno() {
		return districtno;
	}

	public void setDistrictno(String districtno) {
		this.districtno = districtno;
	}

	public String getShopno() {
		return shopno;
	}

	public void setShopno(String shopno) {
		this.shopno = shopno;
	}

	public String getShopname() {
		return shopname;
	}

	public void setShopname(String shopname) {
		this.shopname = shopname;
	}

	public String getShopagencycode() {
		return shopagencycode;
	}

	public void setShopagencycode(String shopagencycode) {
		this.shopagencycode = shopagencycode;
	}

	public String getShopopenyrwk() {
		return shopopenyrwk;
	}

	public void setShopopenyrwk(String shopopenyrwk) {
		this.shopopenyrwk = shopopenyrwk;
	}

	public String getShopcloseyrwk() {
		return shopcloseyrwk;
	}

	public void setShopcloseyrwk(String shopcloseyrwk) {
		this.shopcloseyrwk = shopcloseyrwk;
	}

	public String getShopaddressone() {
		return shopaddressone;
	}

	public void setShopaddressone(String shopaddressone) {
		this.shopaddressone = shopaddressone;
	}

	public String getShopaddresstwo() {
		return shopaddresstwo;
	}

	public void setShopaddresstwo(String shopaddresstwo) {
		this.shopaddresstwo = shopaddresstwo;
	}

	public String getShopaddressthree() {
		return shopaddressthree;
	}

	public void setShopaddressthree(String shopaddressthree) {
		this.shopaddressthree = shopaddressthree;
	}

	public String getShopaddressfour() {
		return shopaddressfour;
	}

	public void setShopaddressfour(String shopaddressfour) {
		this.shopaddressfour = shopaddressfour;
	}

	public String getShopaddressfive() {
		return shopaddressfive;
	}

	public void setShopaddressfive(String shopaddressfive) {
		this.shopaddressfive = shopaddressfive;
	}

	public String getShopaddresssix() {
		return shopaddresssix;
	}

	public void setShopaddresssix(String shopaddresssix) {
		this.shopaddresssix = shopaddresssix;
	}

	public String getStatecode() {
		return statecode;
	}

	public void setStatecode(String statecode) {
		this.statecode = statecode;
	}

	public String getNewdistzonecode() {
		return newdistzonecode;
	}

	public void setNewdistzonecode(String newdistzonecode) {
		this.newdistzonecode = newdistzonecode;
	}

	public String getWhousecode() {
		return whousecode;
	}

	public void setWhousecode(String whousecode) {
		this.whousecode = whousecode;
	}

	public String getStoreclass() {
		return storeclass;
	}

	public void setStoreclass(String storeclass) {
		this.storeclass = storeclass;
	}

	public String getShopcityname() {
		return shopcityname;
	}

	public void setShopcityname(String shopcityname) {
		this.shopcityname = shopcityname;
	}

	public String getEonelocno() {
		return eonelocno;
	}

	public void setEonelocno(String eonelocno) {
		this.eonelocno = eonelocno;
	}

	public String getOperationcloseyrwk() {
		return operationcloseyrwk;
	}

	public void setOperationcloseyrwk(String operationcloseyrwk) {
		this.operationcloseyrwk = operationcloseyrwk;
	}

	
}
