package com.bata.billpunch.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bata.billpunch.model.BillPunchTransReportModel;

@Repository
public interface TransReportDao extends JpaRepository<BillPunchTransReportModel, Long>{
	
	@Query(nativeQuery = true, value = "select a.* from  TL_BILL_TRANS_REPORT_DTLS a where a.WEEK like ?1 and a.YEAR like ?2 and SUBSTR(a.PARTY_CODE,0,1) like ?3 order by a.INVOICE_NO,a.PARTY_CODE,a.RDC_CODE desc ")
	public List<BillPunchTransReportModel> findWithBillReportByWeekTrans(String wk, String year, String partycode);

	
	
	
	
}
