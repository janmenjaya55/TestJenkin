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
@Entity(name = "WeekMasterModel")
@Table(name = "TM_WEEK_MASTER_DTLS")
public class WeekMasterModel implements Serializable {

	private static final long serialVersionUID = 13332225667L;

	@Id
	@Column(name = "WEEK_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long weekId;

	@Column(name = "BATA_WEEK")
	private String bataWeek;

	@Column(name = "BATA_FORT")
	private String bataFort;

	@Column(name = "BATA_MONTH")
	private String bataMonth;

	@Column(name = "BATA_YEAR")
	private String bataYear;

	@Column(name = "FORT_SDATE")
	private Date fortSdate;

	@Column(name = "FORT_EDATE")
	private Date fortEdate;

	@Column(name = "WEEK_SDATE")
	private Date weekSdate;

	@Column(name = "WEEK_EDATE")
	private Date weekEdate;
	
	@Column(name = "UPDATE_DT")
	private Date updateDate;
	
	

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Long getWeekId() {
		return weekId;
	}

	public void setWeekId(Long weekId) {
		this.weekId = weekId;
	}

	public String getBataWeek() {
		return bataWeek;
	}

	public void setBataWeek(String bataWeek) {
		this.bataWeek = bataWeek;
	}

	public String getBataFort() {
		return bataFort;
	}

	public void setBataFort(String bataFort) {
		this.bataFort = bataFort;
	}

	public String getBataMonth() {
		return bataMonth;
	}

	public void setBataMonth(String bataMonth) {
		this.bataMonth = bataMonth;
	}

	public String getBataYear() {
		return bataYear;
	}

	public void setBataYear(String bataYear) {
		this.bataYear = bataYear;
	}

	public Date getFortSdate() {
		return fortSdate;
	}

	public void setFortSdate(Date fortSdate) {
		this.fortSdate = fortSdate;
	}

	public Date getFortEdate() {
		return fortEdate;
	}

	public void setFortEdate(Date fortEdate) {
		this.fortEdate = fortEdate;
	}

	public Date getWeekSdate() {
		return weekSdate;
	}

	public void setWeekSdate(Date weekSdate) {
		this.weekSdate = weekSdate;
	}

	public Date getWeekEdate() {
		return weekEdate;
	}

	public void setWeekEdate(Date weekEdate) {
		this.weekEdate = weekEdate;
	}
	
	

}
