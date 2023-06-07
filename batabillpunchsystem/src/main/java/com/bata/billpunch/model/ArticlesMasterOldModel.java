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
@Entity(name = "ArticlesMasterOldModel")
@Table(name = "TM_OLD_ARTICLES_MASTER_DTLS")
public class ArticlesMasterOldModel implements Serializable {

	private static final long serialVersionUID = 13332225667L;

	@Id
	@Column(name = "ART_OLD_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long artOldId;

	@Column(name = "ART_NAME")
	private String artname;

	@Column(name = "ART_NO")
	private String artno;

	@Column(name = "ART_TYPE")
	private String arttype;

	@Column(name = "ART_CATG_KEY")
	private String artcatgkey;

	@Column(name = "ART_PRICE_MRP")
	private String artpricemrp;

	@Column(name = "ART_PRICE_WSL")
	private String artpricewsl;

	@Column(name = "ART_PRICE_FACTORY")
	private String artpricefactory;

	@Column(name = "ART_PRICE_RTL")
	private String artpricertl;

	@Column(name = "ART_PRICE_PREV")
	private String artpriceprev;

	@Column(name = "ART_SEQUENCE_NO")
	private String artsequenceno;

	@Column(name = "ART_STATE_CODE")
	private String artstatecode;

	@Column(name = "ART_PROD_UNIT")
	private String artprodunit;

	@Column(name = "ART_STND_COST")
	private String artstndcost;

	@Column(name = "ART_BRAND_CODE")
	private String artbrandcode;

	@Column(name = "ART_BIL_LAST_DEL_YR_WK")
	private String artbillastdelyrwk;

	@Column(name = "ART_RTL_WSL_TYPE")
	private String artrtlwsltype;

	@Column(name = "ART_UOM")
	private String artuom;

	@Column(name = "ART_INTRO_YR_WK")
	private String artintroyrwk;

	@Column(name = "HSN_CODE")
	private String hsncode;

	@Column(name = "GST_PCNT")
	private String gstpcnt;

	@Column(name = "CGST_PCNT")
	private String cgstpcnt;

	@Column(name = "SGST_PCNT")
	private String sgstpcnt;

	@Column(name = "IGST_PCNT")
	private String igstpcnt;

	@Column(name = "UOM_SH")
	private String uomsh;

	@Column(name = "HSN_CODE_DESC")
	private String hsncodedesc;

	@Column(name = "UPDATE_DT")
	private Date updateDate;

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Long getArtOldId() {
		return artOldId;
	}

	public void setArtOldId(Long artOldId) {
		this.artOldId = artOldId;
	}

	public String getArtname() {
		return artname;
	}

	public void setArtname(String artname) {
		this.artname = artname;
	}

	public String getArtno() {
		return artno;
	}

	public void setArtno(String artno) {
		this.artno = artno;
	}

	public String getArttype() {
		return arttype;
	}

	public void setArttype(String arttype) {
		this.arttype = arttype;
	}

	public String getArtcatgkey() {
		return artcatgkey;
	}

	public void setArtcatgkey(String artcatgkey) {
		this.artcatgkey = artcatgkey;
	}

	public String getArtpricemrp() {
		return artpricemrp;
	}

	public void setArtpricemrp(String artpricemrp) {
		this.artpricemrp = artpricemrp;
	}

	public String getArtpricewsl() {
		return artpricewsl;
	}

	public void setArtpricewsl(String artpricewsl) {
		this.artpricewsl = artpricewsl;
	}

	public String getArtpricefactory() {
		return artpricefactory;
	}

	public void setArtpricefactory(String artpricefactory) {
		this.artpricefactory = artpricefactory;
	}

	public String getArtpricertl() {
		return artpricertl;
	}

	public void setArtpricertl(String artpricertl) {
		this.artpricertl = artpricertl;
	}

	public String getArtpriceprev() {
		return artpriceprev;
	}

	public void setArtpriceprev(String artpriceprev) {
		this.artpriceprev = artpriceprev;
	}

	public String getArtsequenceno() {
		return artsequenceno;
	}

	public void setArtsequenceno(String artsequenceno) {
		this.artsequenceno = artsequenceno;
	}

	public String getArtstatecode() {
		return artstatecode;
	}

	public void setArtstatecode(String artstatecode) {
		this.artstatecode = artstatecode;
	}

	public String getArtprodunit() {
		return artprodunit;
	}

	public void setArtprodunit(String artprodunit) {
		this.artprodunit = artprodunit;
	}

	public String getArtstndcost() {
		return artstndcost;
	}

	public void setArtstndcost(String artstndcost) {
		this.artstndcost = artstndcost;
	}

	public String getArtbrandcode() {
		return artbrandcode;
	}

	public void setArtbrandcode(String artbrandcode) {
		this.artbrandcode = artbrandcode;
	}

	public String getArtbillastdelyrwk() {
		return artbillastdelyrwk;
	}

	public void setArtbillastdelyrwk(String artbillastdelyrwk) {
		this.artbillastdelyrwk = artbillastdelyrwk;
	}

	public String getArtrtlwsltype() {
		return artrtlwsltype;
	}

	public void setArtrtlwsltype(String artrtlwsltype) {
		this.artrtlwsltype = artrtlwsltype;
	}

	public String getArtuom() {
		return artuom;
	}

	public void setArtuom(String artuom) {
		this.artuom = artuom;
	}

	public String getArtintroyrwk() {
		return artintroyrwk;
	}

	public void setArtintroyrwk(String artintroyrwk) {
		this.artintroyrwk = artintroyrwk;
	}

	public String getHsncode() {
		return hsncode;
	}

	public void setHsncode(String hsncode) {
		this.hsncode = hsncode;
	}

	public String getGstpcnt() {
		return gstpcnt;
	}

	public void setGstpcnt(String gstpcnt) {
		this.gstpcnt = gstpcnt;
	}

	public String getCgstpcnt() {
		return cgstpcnt;
	}

	public void setCgstpcnt(String cgstpcnt) {
		this.cgstpcnt = cgstpcnt;
	}

	public String getSgstpcnt() {
		return sgstpcnt;
	}

	public void setSgstpcnt(String sgstpcnt) {
		this.sgstpcnt = sgstpcnt;
	}

	public String getIgstpcnt() {
		return igstpcnt;
	}

	public void setIgstpcnt(String igstpcnt) {
		this.igstpcnt = igstpcnt;
	}

	public String getUomsh() {
		return uomsh;
	}

	public void setUomsh(String uomsh) {
		this.uomsh = uomsh;
	}

	public String getHsncodedesc() {
		return hsncodedesc;
	}

	public void setHsncodedesc(String hsncodedesc) {
		this.hsncodedesc = hsncodedesc;
	}

}
