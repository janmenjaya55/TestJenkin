package com.bata.billpunch.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bata.billpunch.model.BillPunchEdpReportModel;
import com.bata.billpunch.model.BillPunchStrazaReportModel;

@Repository
public interface StrazaReportDao extends JpaRepository<BillPunchStrazaReportModel, Long>{
	
	@Query(nativeQuery = true, value = "select a.* from  TL_BILL_STRAZA_REPORT_DTLS a where a.WEEK like ?1 and a.YEAR like ?2 and SUBSTR(a.PARTY_CODE,0,1) like ?3  order by a.INVOICE_NO desc ")
	public List<BillPunchStrazaReportModel> findWithBillReportByWeekEdp(String wk, String year, String partycode);
	
	
}
