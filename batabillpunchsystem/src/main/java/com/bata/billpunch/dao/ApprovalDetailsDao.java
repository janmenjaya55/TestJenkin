package com.bata.billpunch.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bata.billpunch.model.ApprovalDetailsModel;
import com.bata.billpunch.model.dto.BillPunchResponseInterface;

@Repository
public interface ApprovalDetailsDao extends JpaRepository<ApprovalDetailsModel, Long>{
	
	
	@Query(nativeQuery = true,value = "SELECT a.* FROM angular_service.tl_approval_details a where a.BILL_UNIQUE_CODE like ?1 and a.FIN_USER_STATUS like ?2")
	public List<ApprovalDetailsModel> findwithApprovalDetailsForValidate(String uniqueId,String status);

	
	@Query(nativeQuery = true, value = "select a.* from  angular_service.tl_approval_details a where (COALESCE(:invoiceNO, null) is null or a.RCPT_INV_NO in :invoiceNO) and (COALESCE(:partycode, null) is null or a.PARTY_CODE in :partycode) and (COALESCE(:billOrderNo, null) is null or a.ORD_NO in :billOrderNo) and (COALESCE(:uniquecode, null) is null or a.BILL_UNIQUE_CODE in :uniquecode) and (COALESCE(:status, null) is null or a.STATUS in :status) and TO_CHAR(a.CREATED_ON,'DD-MM-YY')=:createdon and  a.STATUS not in ('RECORD_RECEIVED','INVALID_RECORDS') ")
	public List<ApprovalDetailsModel> findWithBillNoHistory(@Param("invoiceNO") String invoiceNO,
			@Param("partycode") String partycode, @Param("billOrderNo") String billOrderNo,
			@Param("uniquecode") String uniquecode, @Param("status") String status,@Param("createdon") String createdon);
	
	@Query(nativeQuery = true, value = "select a.* from  angular_service.tl_approval_details a where  a.RCPT_INV_NO=:invoiceNO and  a.PARTY_CODE=:partycode and  a.ORD_NO =:billOrderNo and  a.STATUS=:status ")
	public List<ApprovalDetailsModel> findWithBillNoHistoryOne(@Param("invoiceNO") String invoiceNO,
			@Param("partycode") String partycode, @Param("billOrderNo") String billOrderNo,
			 @Param("status") String status);
	
	@Query(nativeQuery = true,value = "SELECT  a.* FROM angular_service.tl_approval_details a where a.RCPT_INV_NO like ?1 and a.ORD_NO like ?2 and a.STATUS='RECORD_RECEIVED'")
	public List<ApprovalDetailsModel> findwithRdcDetailsStatus(String invno,String ordno);
	
	@Query(nativeQuery = true, value = "select  TO_CHAR(a.CREATED_ON,'DD-MM-YY') as createdDate,a.PARTY_CODE as partyCode,a.STATUS as status,a.RCPT_INV_NO as invoiceNO,a.ORD_NO as billOrderNo from   angular_service.tl_approval_details a where (COALESCE(:invoiceNO, null) is null or a.RCPT_INV_NO in :invoiceNO) and (COALESCE(:orderNo, null) is null or a.ORD_NO in :orderNo) and a.STATUS not in ('RECORD_RECEIVED','INVALID_RECORDS')   group by a.RCPT_INV_NO,a.ORD_NO,a.STATUS, a.PARTY_CODE,TO_CHAR(a.CREATED_ON,'DD-MM-YY') order by TO_CHAR(a.CREATED_ON,'DD-MM-YY') asc ")
	public List<BillPunchResponseInterface> findWithBillNoHistoryrows(@Param("invoiceNO") String invoiceNO,
			@Param("orderNo") String orderNo);


}
