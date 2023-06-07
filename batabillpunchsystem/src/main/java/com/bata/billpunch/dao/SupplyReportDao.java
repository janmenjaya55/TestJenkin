package com.bata.billpunch.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bata.billpunch.model.BillPunchSupplyReportModel;

@Repository
public interface SupplyReportDao extends JpaRepository<BillPunchSupplyReportModel, Long>{
	
	@Query(nativeQuery = true, value = "select a.* from  TL_BILL_SUPPLY_REPORT_DTLS a where a.WEEK like ?1 and a.YEAR like ?2 and SUBSTR(a.PARTY_CODE,0,1) like ?3 ")
	public List<BillPunchSupplyReportModel> findWithAllApproved(String wk, String year, String partycode);

	
	
	
	
}
