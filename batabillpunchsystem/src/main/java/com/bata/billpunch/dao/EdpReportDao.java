package com.bata.billpunch.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bata.billpunch.model.BillPunchEdpReportModel;

@Repository
public interface EdpReportDao extends JpaRepository<BillPunchEdpReportModel, Long>{
	
	@Query(nativeQuery = true, value = "select a.* from  TL_BILL_EDP_REPORT_DTLS a where a.WEEK like ?1 and a.YEAR like ?2  order by a.INVOICE_NO,a.PARTY_CODE,a.RDC_CODE desc ")
	public List<BillPunchEdpReportModel> findWithBillReportByWeekEdp(String wk, String year);

	
	
	
	
}
