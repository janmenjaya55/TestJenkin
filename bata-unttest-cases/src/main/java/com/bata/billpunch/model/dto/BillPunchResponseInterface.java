package com.bata.billpunch.model.dto;

import java.util.Calendar;
import java.util.Date;

public interface BillPunchResponseInterface {

	public String getformtype();
	
	public String getrecLoc();

	public String getpartyCode();

	public String getpartyName();

	public Date getbillOrderDate();
	
	public String getbillOrderDateOne();

	public Calendar getcreatedOn();

	public String getcreatedDate();

	public String getartstdCostedp();

	public String getartpricefactoryedp();

	public String getartpricemrpedp();

	public String getartpricewspedp();

	public Double getartstdCost();

	public String getartseq();

	public Double getartpricefactory();

	public Double getartpricemrp();

	public String getbillOrderNo();

	public Double getinvAmount();

	public Double getrdcAmount();

	public String getstatus();

	public String getrdcCode();

	public String getarticleCode();

	public String getbillUniqueCode();

	public String getpurchaseCost();

	public Double getpurchaseCostone();

	public String getinvamtreport();

	public String getpurchaseCostreport();

	public String gettotalCost();

	public String getgstamt();

	public String getpurchaseoffValue();

	public String getinvoiceNO();

	public String getgrNo();

	public String getpair();

	public String gettotalpair();

	public String getdiscountAmt();

	public String getinvdate();

	public String getbillWeek();

	public String getcnDate();
	
	public Date getcnDateOne();

	public Date getcnDateone();

	public Date getrecptInvDateone();
	
	public String getrecptInvDate();
	
	public String getgrDate();

	public Date getresumeInvDateone();

	public String getgrnDate();

	public String getcnNo();

	public String gettcsApplicable();

	public String gettcsPercent();

	public String getparty();

	public Calendar getcreatedon();

	public String getstatename();

	public String getigst();

	public String getcgst();

	public String getsgst();

	public String getigstamt();

	public String getcgstamt();

	public String getsgstamt();

	public String getfreight();

	public String getpackCaseT();

	public String getpackCaseB();

	public String getpackCaseS();

	public String getpackCaseC();

	public String getpackCaseM();

	public String gettrnsportCode();

	public String getrdpermitNo();

	public String getresumeInvNo();

	public String getresumeInvNoTwo();

	public String gettranCodeone();

	public String getsizeCode();

	public String getdcCode();

	public String getrecStatus();

	public String getyr();

}
