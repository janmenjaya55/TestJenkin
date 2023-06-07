package com.bata.billpunch.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bata.billpunch.model.BillPunchGstReportModel;

@Repository
public interface GstReportDao extends JpaRepository<BillPunchGstReportModel, Long>{
	
	@Query(nativeQuery = true, value = "select a.* from  TL_BILL_GST_REPORT_DTLS a where SUBSTR(a.PARTY_CODE,0,1)=:partycode and TO_CHAR(a.CREATED_ON, 'YYYY-MM-DD')  >=:fromdate and TO_CHAR(a.CREATED_ON, 'YYYY-MM-DD') <=:todate  order by a.INVOICE_NO desc ")
	public List<BillPunchGstReportModel> findWithBillReportByWeekGst( String partycode, String fromdate, String todate);

	
	
	
	
}
