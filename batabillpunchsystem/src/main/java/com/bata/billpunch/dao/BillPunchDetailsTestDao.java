package com.bata.billpunch.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bata.billpunch.model.BillPunchDetailsModel;
@Repository
public interface BillPunchDetailsTestDao extends JpaRepository<BillPunchDetailsModel, Long>{
	
	
	@Query(nativeQuery = true,value = "SELECT a.* FROM TT_BILL_PUNCH_DTLS_TEST_ONE a where (COALESCE(:invoiceNO, null) is null or a.INV_NO in :invoiceNO) and (COALESCE(:partycode, null) is null or a.PARTY_CODE in :partycode) and (COALESCE(:orderno, null) is null or a.ORD_NO in :orderno) and (COALESCE(:uniquecode, null) is null or a.SEQ_NO in :uniquecode) and (COALESCE(:status, null) is null or a.STATUS in :status)")
	public List<BillPunchDetailsModel> findWithBillNoPartyCodeAndOrderNoTest(@Param("invoiceNO")String invoiceNO,@Param("partycode")String partycode,@Param("orderno")String orderno,@Param("uniquecode")String uniquecode,@Param("status")String status);
	


}
