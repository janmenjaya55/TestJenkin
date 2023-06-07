package com.bata.billpunch.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bata.billpunch.model.BillPunchDetailsModel;
import com.bata.billpunch.model.dto.AdonisFileDetailsInterface;
import com.bata.billpunch.model.dto.BillCloseStatusDto;
import com.bata.billpunch.model.dto.BillPunchEdpInterface;
import com.bata.billpunch.model.dto.BillPunchResponseInterface;
import com.bata.billpunch.model.dto.BillPurchaseStatusInterface;
import com.bata.billpunch.model.dto.ChartsInterface;
import com.bata.billpunch.model.dto.PartyResponseDto;
import com.bata.billpunch.model.dto.PurchaseCostInterface;
import com.bata.billpunch.model.dto.ReceivingLocDto;
import com.bata.billpunch.model.dto.StrazaBillPunchDto;
import com.bata.billpunch.model.dto.TotalAmtInterface;

@Repository
public interface BillPunchDetailsDao extends JpaRepository<BillPunchDetailsModel, Long> {

	@Query(nativeQuery = true, value = "select a.* from  angular_service.tt_bill_punch_dtls_one a where  a.RCPT_INV_NO=:invoiceNO and  a.PARTY_CODE=:partycode and  a.ORD_NO =:billOrderNo and  a.STATUS=:status ")
	public List<BillPunchDetailsModel> findWithBillNoHistoryOne(@Param("invoiceNO") String invoiceNO,
			@Param("partycode") String partycode, @Param("billOrderNo") String billOrderNo,
			@Param("status") String status);

	public void save(List<BillPunchDetailsModel> mn);

	@Query(nativeQuery = true, value = " select a. igstamt, a. cgstamt,a. sgstamt,a.gstamt,a.tcsValue, a.freight,   a. grnoOne,a. grdateNew,a. year, a. totalCost,a. billOrderDate , a. billOrderNo ,a. grDate ,a. invoiceNO ,a. partyCode ,a. partyName,a. purchaseCost  ,a. pair ,a.shopName ,a. shopNo,a.invoiceCost ,a. grNo ,a. bookWeek,a. receiveWk ,a.recptInvDate,a.receiveLoc  from  EDIT_REPORT_VIEW a  where a.STATUS='APPROVED'  order by a.BILLORDERNO")
	public List<StrazaBillPunchDto> findWithAll();

	@Modifying
	@Query(nativeQuery = true, value = "TRUNCATE TABLE angular_service.tt_bill_punch_dtls_one")
	void findWithDeleteAll();

	@Query(nativeQuery = true, value = "select a.PARTY,a.IGST_AMT as igstamt,a.CGST_AMT as cgstamt,a.SGST_AMT as sgstamt ,a.RCPT_INV_NO as invoiceNO,a.ORD_NO as billOrderNo,a.WEEK as billWeek,a.YEAR as yr from  angular_service.tt_bill_punch_dtls_one a where a.WEEK like ?1 and a.YEAR like ?2  and a.STATUS in ('CLOSED') group by a.PARTY,a.RCPT_INV_NO ,a.ORD_NO,a.IGST_AMT , a.CGST_AMT ,a.SGST_AMT,a.WEEK,a.YEAR")
	public List<BillPunchResponseInterface> findWithAllApprovedForSupplyReport(String wk, String year);

	@Query(nativeQuery = true, value = "select a.* from  angular_service.tt_bill_punch_dtls_one a where a.PARTY_CODE like ?1 and a.RCPT_INV_NO like ?2")
	public List<BillPunchDetailsModel> findWithAllApprovedForSupplyReportFilter(String partycode, String invno);

	@Query(nativeQuery = true, value = "select a.* from  angular_service.tt_bill_punch_dtls_one a where a.PARTY_CODE like ?1 and a.RCPT_INV_NO like ?2 and a.ORD_NO like ?3 and a.STATUS like ?4 ")
	public List<BillPunchDetailsModel> findWithAllApprovedForSupplyReportFilternewOne(String partycode, String invno,
			String orderNo, String status);
	
	@Query(nativeQuery = true, value = "select a.* from  angular_service.tt_bill_punch_dtls_one a where a.PARTY_CODE like ?1 and a.RCPT_INV_NO like ?2 and a.ORD_NO like ?3  ")
	public List<BillPunchDetailsModel> findWithAllApprovedForSupplyReportFilternewTWO(String partycode, String invno,
			String orderNo);

	@Query(nativeQuery = true, value = "select a.* from  angular_service.tt_bill_punch_dtls_one a where a.PARTY_CODE like ?1 and a.RCPT_INV_NO like ?2 and a.ORD_NO like ?3 and a.STATUS like ?4 and a.week like ?5 and a.year like ?6  and a.grno like ?7 ")
	public List<BillPunchDetailsModel> findWithAllApprovedForSupplyReportFilternew(String partycode, String invno,
			String orderNo, String status, String week, String year, String grno);

	@Query(nativeQuery = true, value = "select a.* from  angular_service.tt_bill_punch_dtls_one a where a.PARTY_CODE like ?1 and a.RCPT_INV_NO like ?2 and a.ORD_NO like ?3")
	public List<BillPunchDetailsModel> findWithAllApprovedForSupplyReportFilternewmanual(String partycode, String invno,
			String orderNo);

	@Query(nativeQuery = true, value = "select a.* from  angular_service.tt_bill_punch_dtls_one a where a.PARTY_CODE like ?1 and a.RCPT_INV_NO like ?2 and a.ORD_NO like ?3 and a.STATUS like ?4 and a.YR like ?5 and a.WEEK like ?6 and a.GRNO like ?6")
	public List<BillPunchDetailsModel> findWithAllApprovedForSupplyReportFilternewold(String partycode, String invno,
			String orderNo, String status, String yr, String wk, String grNo);

	@Query(nativeQuery = true, value = "select  a.RCPT_INV_NO as invoiceNO,a.ORD_NO as billOrderNo,a.ART_CODE as articleCode from  angular_service.tt_bill_punch_dtls_one a where  a.WEEK like ?1 and a.YEAR like ?2  and a.STATUS in ('CLOSED') group by a.RCPT_INV_NO ,a.ORD_NO ,a.ART_CODE ")
	public List<BillPunchResponseInterface> findWithAllApprovedForGst(String wk, String yr);

	@Query(nativeQuery = true, value = "select distinct a.PARTY_CODE as partyCode,a.PARTY as party,a.RCPT_INV_NO as invoiceNo,a.ORD_NO as billOrderNo from  angular_service.tt_bill_punch_dtls_one a where  a.RCPT_INV_NO like ?1 and a.ORD_NO like ?2 ")
	public List<BillPunchResponseInterface> findWithPartycodeAndParty(String invno, String orderno);

	// @Query(nativeQuery = true, value = "SELECT a.* FROM
	// angular_service.tt_bill_punch_dtls_one a
	// where a.ART_CODE like ?1 and a.ORD_NO like ?2 and a.RCPT_INV_NO like ?3 and
	// ROWNUM=1 ")
	@Query(nativeQuery = true, value = "SELECT a.* FROM angular_service.tt_bill_punch_dtls_one a where a.ART_CODE like ?1 and  a.ORD_NO like ?2 and  a.RCPT_INV_NO like ?3 and a.STATUS='CLOSED' ")
	public List<BillPunchDetailsModel> findWithPairByOrderNoAndArtno(String artno, String orderno, String invno);

	@Query(nativeQuery = true, value = "SELECT a.* FROM angular_service.tt_bill_punch_dtls_one a where a.PARTY like ?1 and  a.ORD_NO like ?2 and  a.RCPT_INV_NO like ?3 and a.STATUS='CLOSED' ")
	public List<BillPunchDetailsModel> findWithPairByOrderNoAndArtnoSupply(String party, String orderno, String invno);

	@Query(nativeQuery = true, value = "SELECT sum(a.NO_PAIRS) as pair,sum(a.TRANS_VAL) as invAmount FROM angular_service.tt_bill_punch_dtls_one a where a.ART_CODE like ?1 and  a.ORD_NO like ?2 and  a.RCPT_INV_NO like ?3 and  a.STATUS='CLOSED' group by a.RCPT_INV_NO, a.ORD_NO,a.ART_CODE ")
	public BillPunchResponseInterface findWithPairByOrderNoAndArtnoForGst(String artno, String orderno, String invno);

	@Query(nativeQuery = true, value = "SELECT sum(a.NO_PAIRS) as pair,sum(a.TRANS_VAL) as invAmount FROM angular_service.tt_bill_punch_dtls_one a where  a.ORD_NO like ?1 and  a.RCPT_INV_NO like ?2  and  a.STATUS='CLOSED' group by a.RCPT_INV_NO, a.ORD_NO ")
	public BillPunchResponseInterface findWithPairByOrderNoAndArtnoForSupply(String orderno, String invno);

	@Query(nativeQuery = true, value = "SELECT sum(a.NO_PAIRS) as pair,sum(a.TRANS_VAL) as invAmount FROM angular_service.tt_bill_punch_dtls_one a where  a.ORD_NO like ?1 and  a.RCPT_INV_NO like ?2 and  a.PARTY like ?3  and  a.STATUS='CLOSED' group by a.RCPT_INV_NO, a.ORD_NO ")
	public BillPunchResponseInterface findWithPairByOrderNoAndArtnoForSupplyReport(String orderno, String invno,
			String party);

	// @Query(nativeQuery = true, value = "select a.pairs as pair, a.purchaseCost ,
	// a.rdcAmount , a.recLoc,a. billOrderDate, a. cnNo,a. cnDate ,a. rdcCode,a.
	// invdate,a. grNo,a. tcsPercent , a. status,a. billWeek,a.grnDate,a.
	// tcsApplicable, a. discountAmt , a. invoiceNO,a. billOrderNo, a. partyCode,a.
	// partyName,a.yr from rdc_view_final a where (COALESCE(:invoiceNO, null) is
	// null or a.INVOICENO in :invoiceNO) and (COALESCE(:partycode, null) is null or
	// a.PARTYCODE in :partycode) and (COALESCE(:billOrderNo, null) is null or
	// a.BILLORDERNO in :billOrderNo) and (COALESCE(:status, null) is null or
	// a.STATUS in :status) and TO_CHAR(a.GRNDATE, 'YYYY-MM-DD') >=:grfromdate and
	// TO_CHAR(a.GRNDATE, 'YYYY-MM-DD') <=:grtodate and (COALESCE(:week, null) is
	// null or a.BILLWEEK in :week) and (COALESCE(:year, null) is null or a.YR in
	// :year) and (COALESCE(:loc, null) is null or a.RECLOC in :loc) ")
	@Query(nativeQuery = true, value = "select a.pairs as pair, a.purchaseCost , a.rdcAmount , a.recLoc,a. billOrderDate, a. cnNo,a. cnDate ,a. rdcCode,a. invdate,a. grNo,a. tcsPercent , a. status,a. billWeek,a.grnDate,a. tcsApplicable, a. discountAmt  , a. invoiceNO,a. billOrderNo,  a. partyCode,a. partyName,a.yr  from rdc_view_final a  where   a.STATUS='RECORD_RECEIVED'  and a.YR='2022' ; ")
	public List<BillPunchResponseInterface> findWithBillNoPartyCodeAndOrderNoTest(@Param("invoiceNO") String invoiceNO,
			@Param("partycode") String partycode, @Param("billOrderNo") String billOrderNo,
			@Param("status") String status, @Param("grfromdate") String grfromdate, @Param("grtodate") String grtodate,
			@Param("week") List<String> week, @Param("year") String year, @Param("loc") String loc);

	@Query(nativeQuery = true, value = "select  sum(a.NO_PAIRS) as pair,a.RCPT_INV_DATE as invdate , a.STATUS as status,a.PRCH_BIL_VAL as invAmount ,a.WEEK as billWeek,a.TCS_VALUE as tcsPercent,a.DISCOUNT_AMT as discountAmt,a.TOTAL_COST as totalCost,a.IGST_AMT as igstamt, a.CGST_AMT as cgstamt,a.SGST_AMT  as sgstamt,a.RCPT_INV_NO as invoiceNO,a.ORD_NO as billOrderNo, a.PARTY_CODE as partyCode,a.party_name as partyName,a.bill_order_date as billOrderDate  from  angular_service.tt_bill_punch_dtls_one a  where (COALESCE(:invoiceNO, null) is null or a.RCPT_INV_NO in :invoiceNO) and (COALESCE(:partycode, null) is null or a.PARTY_CODE in :partycode) and (COALESCE(:billOrderNo, null) is null or a.ORD_NO in :billOrderNo) and (COALESCE(:uniquecode, null) is null or a.SEQ_NO in :uniquecode) and (COALESCE(:status, null) is null or a.STATUS in :status) and  TO_CHAR(a.GR_DATE, 'YYYY-MM-DD')  >=:grfromdate and TO_CHAR(a.GR_DATE, 'YYYY-MM-DD') <=:grtodate and (COALESCE(:week, null) is null or a.WEEK in :week) and (COALESCE(:year, null) is null or a.YEAR in :year) and (COALESCE(:loc, null) is null or a.RECEIVE_LOC in :loc) and a.STATUS='SUBMITTED'  group by a.TOTAL_COST, a.RCPT_INV_DATE , a.STATUS ,a.WEEK  ,a.TCS_VALUE , a.DISCOUNT_AMT  ,a.PRCH_BIL_VAL  ,a.RCPT_INV_NO ,a.ORD_NO , a.PARTY_CODE ,a.party_name ,a.bill_order_date,a.IGST_AMT, a.CGST_AMT,a.SGST_AMT  order by a.ORD_NO desc ")
	public List<BillPunchResponseInterface> findWithForApprovalPage(@Param("invoiceNO") String invoiceNO,
			@Param("partycode") String partycode, @Param("billOrderNo") String billOrderNo,
			@Param("uniquecode") String uniquecode, @Param("status") String status,
			@Param("grfromdate") String grfromdate, @Param("grtodate") String grtodate,
			@Param("week") List<String> week, @Param("year") String year, @Param("loc") String loc);

	@Query(nativeQuery = true, value = "select a.RCPT_INV_NO as invoiceNO,a.ORD_NO as billOrderNo from  TL_APPROVAL_DETAILS a  where (COALESCE(:invoiceNO, null) is null or a.RCPT_INV_NO in :invoiceNO) and (COALESCE(:partycode, null) is null or a.PARTY_CODE in :partycode) and (COALESCE(:billOrderNo, null) is null or a.ORD_NO in :billOrderNo) and (COALESCE(:uniquecode, null) is null or a.BILL_UNIQUE_CODE in :uniquecode) and (COALESCE(:status, null) is null or a.STATUS in :status) and  TO_CHAR(a.GR_DATE, 'YYYY-MM-DD')  >=:grfromdate and TO_CHAR(a.GR_DATE, 'YYYY-MM-DD') <=:grtodate and (COALESCE(:week, null) is null or a.WEEK in :week) and (COALESCE(:year, null) is null or a.YEAR in :year) and (COALESCE(:loc, null) is null or a.RECEIVE_LOC in :loc)  group by   a.RCPT_INV_NO ,a.ORD_NO ")
	public List<BillPunchResponseInterface> findWithHistory(@Param("invoiceNO") String invoiceNO,
			@Param("partycode") String partycode, @Param("billOrderNo") String billOrderNo,
			@Param("uniquecode") String uniquecode, @Param("status") String status,
			@Param("grfromdate") String grfromdate, @Param("grtodate") String grtodate,
			@Param("week") List<String> week, @Param("year") String year, @Param("loc") String loc);

	@Query(nativeQuery = true, value = "select a.GRNO as grNo ,a.TCS_PERCENT as tcsPercent,a.STATUS as status,a.PURCHASE_COST as purchaseoffValue,a.TOT_PAIRS as pair,a.WK as billWeek,a.GR_DATE as cnDate,a.TCS_APPLICABLE as tcsApplicable, a.RCPT_INV_DATE as invdate , a.DISCOUNT_AMT as discountAmt ,a.PRCH_BIL_VAL as rdcAmount ,a.RCPT_INV_NO as invoiceNO,a.ORD_NO as billOrderNo,a.form_type as formtype, a.PARTY_CODE as partyCode,a.party_name as partyName,a.bill_order_date as billOrderDate  from  angular_service.tt_bill_punch_dtls_one a  where (COALESCE(:invoiceNO, null) is null or a.RCPT_INV_NO in :invoiceNO) and (COALESCE(:partycode, null) is null or a.PARTY_CODE in :partycode) and (COALESCE(:billOrderNo, null) is null or a.ORD_NO in :billOrderNo) and (COALESCE(:uniquecode, null) is null or a.SEQ_NO in :uniquecode) and (COALESCE(:status, null) is null or a.STATUS in :status) and  TO_CHAR(a.GR_DATE, 'YYYY-MM-DD')  >=:grfromdate and TO_CHAR(a.GR_DATE, 'YYYY-MM-DD') <=:grtodate  and (COALESCE(:week, null) is null or a.WK in :week) and (COALESCE(:year, null) is null or a.YR in :year) and (COALESCE(:loc, null) is null or a.RECEIVE_LOC in :loc) group by a.GRNO,a.TCS_PERCENT, a.PURCHASE_COST,a.PRCH_BIL_VAL,a.RCPT_INV_NO,a.ORD_NO,a.form_type,a.PARTY_CODE,a.party_name,a.status,a.bill_order_date,a.DISCOUNT_AMT, a.RCPT_INV_DATE,a.TCS_APPLICABLE,a.GR_DATE,a.WK,a.TOT_PAIRS")
	public List<BillPunchResponseInterface> findWithBillNoPartyCodeAndOrderNoManual(
			@Param("invoiceNO") String invoiceNO, @Param("partycode") String partycode,
			@Param("billOrderNo") String billOrderNo, @Param("uniquecode") String uniquecode,
			@Param("status") String status, @Param("grfromdate") String grfromdate, @Param("grtodate") String grtodate,
			@Param("week") List<String> week, @Param("year") String year, @Param("loc") String loc);

	@Query(nativeQuery = true, value = "select distinct c.PURPRICE as purchaseCost,c.DELDATE_TO as receiveDate,c.APP_DATE as billOrderDate from TM_ORDERS_MASTER_DTLS c where c.ORDERNO like ?1 and c.PARTY_CODE like ?2 and c.RDCNO like ?3 and c.ARTNO like ?4")
	public PurchaseCostInterface findWithPurchaseCostFromOrder(String billOrderNo, String partycode, String rdcCode,
			String artNo);

	@Query(nativeQuery = true, value = "select distinct c.DELDATE_TO as receiveDate,c.APP_DATE as billOrderDate from TM_ORDERS_MASTER_DTLS c where c.ORDERNO like ?1 and c.PARTY_CODE like ?2 and c.RDCNO like ?3 and c.ARTNO like ?4 ")
	public PurchaseCostInterface findWithDateFromOrder(String billOrderNo, String partycode, String rdcCode,
			String artCode);

	@Query(nativeQuery = true, value = "select a.* from  angular_service.tt_bill_punch_dtls_one a where (COALESCE(:invoiceNO, null) is null or a.RCPT_INV_NO in :invoiceNO) and (COALESCE(:partycode, null) is null or a.PARTY_CODE in :partycode) and (COALESCE(:billOrderNo, null) is null or a.ORD_NO in :billOrderNo) and (COALESCE(:uniquecode, null) is null or a.SEQ_NO in :uniquecode) and (COALESCE(:status, null) is null or a.STATUS in :status)")
	public List<BillPunchDetailsModel> findWithBillNoPartyCodeAndOrderNo(@Param("invoiceNO") String invoiceNO,
			@Param("partycode") String partycode, @Param("billOrderNo") String billOrderNo,
			@Param("uniquecode") String uniquecode, @Param("status") String status);

	@Query(nativeQuery = true, value = "select a.* from  angular_service.tt_bill_punch_dtls_one a where (COALESCE(:invoiceNO, null) is null or a.RCPT_INV_NO in :invoiceNO) and (COALESCE(:partycode, null) is null or a.PARTY_CODE in :partycode) and (COALESCE(:billOrderNo, null) is null or a.ORD_NO in :billOrderNo) ")
	public List<BillPunchDetailsModel> findWithInvnoAndPartyCodeAndOrderNo(@Param("invoiceNO") String invoiceNO,
			@Param("partycode") String partycode, @Param("billOrderNo") String billOrderNo);

	/*
	 * @Query(nativeQuery = true, value =
	 * "select a.* from  TL_APPROVAL_DETAILS a where (COALESCE(:invoiceNO, null) is null or a.RCPT_INV_NO in :invoiceNO) and (COALESCE(:partycode, null) is null or a.PARTY_CODE in :partycode) and (COALESCE(:billOrderNo, null) is null or a.ORD_NO in :billOrderNo) and (COALESCE(:uniquecode, null) is null or a.BILL_UNIQUE_CODE in :uniquecode) and (COALESCE(:status, null) is null or a.STATUS in :status)"
	 * ) public List<ApprovalDetailsModel> findWithBillNoHistory(@Param("invoiceNO")
	 * String invoiceNO,
	 * 
	 * @Param("partycode") String partycode, @Param("billOrderNo") String
	 * billOrderNo,
	 * 
	 * @Param("uniquecode") String uniquecode, @Param("status") String status);
	 */

	@Query(nativeQuery = true, value = "select a.ART_CODE as articleCode,sum(a.NO_PAIRS) as pair from  angular_service.tt_bill_punch_dtls_one a where (COALESCE(:invoiceNO, null) is null or a.RCPT_INV_NO in :invoiceNO) and (COALESCE(:billNO, null) is null or a.ORD_NO in :billNO)  group by a.ART_CODE")
	public List<BillPunchResponseInterface> findWithBillNoPartyCodeAndOrderNoAndPartycodeList(
			@Param("invoiceNO") String invoiceNO, @Param("billNO") String billNO);

	@Query(nativeQuery = true, value = "select a.ART_CODE as articleCode,sum(a.NO_PAIRS) as pair from  angular_service.tt_bill_punch_dtls_one a where (COALESCE(:invoiceNO, null) is null or a.RCPT_INV_NO in :invoiceNO) and (COALESCE(:billNO, null) is null or a.ORD_NO in :billNO) and a.STATUS='CLOSED' group by a.ART_CODE ")
	public List<BillPunchResponseInterface> findWithBillNoPartyCodeAndOrderNoAndPartycode(
			@Param("invoiceNO") String invoiceNO, @Param("billNO") String billNO);

	@Query(nativeQuery = true, value = "select a.ART_CODE as articleCode,sum(a.NO_PAIRS) as pair from  angular_service.tt_bill_punch_dtls_one a where a.ART_CODE like ?1 and a.ORD_NO like ?2 and  a.RCPT_INV_NO like ?3 group by a.ART_CODE,a.RCPT_INV_NO,a.ORD_NO")
	public BillPunchResponseInterface findWithBillNoArtCodeAndOrderNo(String artcode, String ordNo, String invoiceNO);

	@Query(nativeQuery = true, value = "select distinct TO_CHAR(a.rcpt_inv_date,'YYYY-MM-DD') as recptInvDate,TO_CHAR(a.bill_order_date,'YYYY-MM-DD') as billOrderDateOne ,TO_CHAR(a.GR_DATE,'YYYY-MM-DD') as grDate,TO_CHAR(a.CN_DATE,'YYYY-MM-DD') as cnDate  from  angular_service.tt_bill_punch_dtls_one a where a.PARTY_CODE like ?1 and a.ORD_NO like ?2 and  a.RCPT_INV_NO like ?3")
	public List<BillPunchResponseInterface> findWithBillNoArtCodeAndOrderNoList(String partycode, String ordNo,
			String invoiceNO);

	@Query(nativeQuery = true, value = "select sum(x.purchasePrice*x.pair)as purchaseCost from (select c.PURPRICE as purchasePrice,a.TOT_PAIRS as pair from  angular_service.tt_bill_punch_dtls_one a  join TM_ORDERS_MASTER_DTLS c on a.ORD_NO=c.ORDERNO and a.PARTY_CODE=c.PARTY_CODE and a.RDC_CODE=c.RDCNO and a.ART_CODE=c.ARTNO where (COALESCE(:invoiceNO, null) is null or a.RCPT_INV_NO in :invoiceNO) and (COALESCE(:partycode, null) is null or a.PARTY_CODE in :partycode) and (COALESCE(:billOrderNo, null) is null or a.ORD_NO in :billOrderNo) and (COALESCE(:uniquecode, null) is null or a.SEQ_NO in :uniquecode) and (COALESCE(:status, null) is null or a.STATUS in :status)  group by  a.ORD_NO,a.PARTY_CODE,a.TOT_PAIRS ,a.ART_CODE,a.RDC_CODE,c.PURPRICE)x")
	public PurchaseCostInterface findWithPurchaseCost(@Param("invoiceNO") String invoiceNO,
			@Param("partycode") String partycode, @Param("billOrderNo") String billOrderNo,
			@Param("uniquecode") String uniquecode, @Param("status") String status);

	@Query(nativeQuery = true, value = "SELECT * FROM angular_service.tt_bill_punch_dtls_one where rownum <=1 order by BIL_ID desc ")
	public BillPunchDetailsModel findwithLastBillEntry();

	@Query(nativeQuery = true, value = "SELECT a.* FROM angular_service.tt_bill_punch_dtls_one a where a.RCPT_INV_NO like ?1 and a.ORD_NO  like ?2 and a.PARTY_CODE like ?3 and a.STATUS='RECORD_RECEIVED'")
	public List<BillPunchDetailsModel> findwithAllDetailsByInvOrd(String invoiceNo, String orderNo, String partyCode);

	@Modifying
	@Query(nativeQuery = true, value = "update angular_service.tt_bill_punch_dtls_one a set a.STATUS='INVALID_RECORDS' where a.RCPT_INV_NO like ?1 and a.ORD_NO  like ?2 and a.PARTY_CODE like ?3 and a.STATUS='RECORD_RECEIVED'")
	public List<BillPunchDetailsModel> updateDetailsByInvOrd(String invoiceNo, String orderNo, String partyCode);

	@Query(nativeQuery = true, value = "select a.ART_CODE as articleCode,sum(a.NO_PAIRS) as pair from  angular_service.tt_bill_punch_dtls_one a where a.RCPT_INV_NO like ?1 and a.GRNO like ?2 and a.PARTY_CODE like ?3 and TO_CHAR(a.RCPT_INV_DATE, 'YYYY-MM-DD')=?4 group by a.ART_CODE")
	public List<BillPunchResponseInterface> getDetailsForEdp(String invoiceNo, String grNo, String partyCode,
			String date);

	@Query(nativeQuery = true, value = "SELECT a.* FROM angular_service.tt_bill_punch_dtls_one a where a.RCPT_INV_NO like ?1 and a.ORD_NO  like ?2 and a.PARTY_CODE like ?3 and a.STATUS='SUBMITTED'")
	public List<BillPunchDetailsModel> findwithAllDetailsByInvOrdApproval(String invoiceNo, String orderNo,
			String partyCode);

	@Query(nativeQuery = true, value = "SELECT a.* FROM angular_service.tt_bill_punch_dtls_one a where a.RCPT_INV_NO like ?1 and a.PARTY_CODE like ?2 and a.STATUS='RECORD_RECEIVED'")
	public List<BillPunchDetailsModel> findwithAllDetailsByInvParty(String invoiceNo, String partyCode);

	@Query(nativeQuery = true, value = "SELECT a.* FROM angular_service.tt_bill_punch_dtls_one a where a.RCPT_INV_NO like ?1 and a.ORD_NO like ?2 and a.STATUS='APPROVED'")
	public List<BillPunchDetailsModel> findwithInvNo(String invno, String ordno);

	@Query(nativeQuery = true, value = "SELECT a.* FROM angular_service.tt_bill_punch_dtls_one a where a.ORD_NO like ?1")
	public List<BillPunchDetailsModel> findwithAllDetailsByOrdNo(String ordno);

	@Query(nativeQuery = true, value = "SELECT a.* FROM angular_service.tt_bill_punch_dtls_one a where a.ORD_NO like ?1 and a.RCPT_INV_NO like ?2")
	public BillPunchDetailsModel findwithAllDetailsByOrderAndInvoice(String orderno, String invno);

	@Query(nativeQuery = true, value = "SELECT distinct a.FORM_TYPE as formType FROM angular_service.tt_bill_punch_dtls_one a where a.ORD_NO like ?1 and a.RCPT_INV_NO like ?2 and a.PARTY_CODE like ?3 ")
	public List<BillPurchaseStatusInterface> findwithOrderNo(String orderno, String invno, String partycode);

	@Query(nativeQuery = true, value = "SELECT distinct  a.PARTY_CODE as partycode,a.PARTY_NAME as partyname FROM angular_service.tt_bill_punch_dtls_one a where a.STATUS='APPROVED'")
	public List<PartyResponseDto> findAllPartycodeAndPartyName();

	@Query(nativeQuery = true, value = "SELECT count(*) as count,party_code as data FROM angular_service.tt_bill_punch_dtls_one group by party_code")
	public List<ChartsInterface> findAllPartycodeAndCountCharts();

	@Query(nativeQuery = true, value = "SELECT count(*) as count,party_code as data FROM angular_service.tt_bill_punch_dtls_one where date(created_on)>=?1 and date(created_on)<=?2 group by party_code ")
	public List<ChartsInterface> findAllPartycodeAndCountCharts(String year1, String year2);

	@Query(nativeQuery = true, value = "SELECT distinct  a.PARTY_CODE as partycode,a.PARTY_NAME as partyname FROM angular_service.tt_bill_punch_dtls_one a where a.STATUS='CLOSED'")
	public List<PartyResponseDto> findAllPartycodeAndPartyNameForStraza();

	@Query(nativeQuery = true, value = "SELECT distinct  a.RDC_CODE as receiveLocCode,a.RECEIVE_LOC as receiveLocName FROM angular_service.tt_bill_punch_dtls_one a where a.RECEIVE_LOC is not null and a.RDC_CODE is not null")
	public List<ReceivingLocDto> findAllReceivingLoc();

	@Query(nativeQuery = true, value = "SELECT a.* FROM angular_service.tt_bill_punch_dtls_one a where a.PARTY like ?1 and a.PARTY_NAME like ?2 and a.STATUS='APPROVED'")
	public List<BillPunchDetailsModel> findAllPartycodeAndPartyNameDetails(String partycode, String partyname);

	@Query(nativeQuery = true, value = "SELECT distinct a.CN_DATE as grDate,a.RCPT_INV_DATE as invoiceDate, a.TOTAL_COST as totalCost,a.PRCH_BIL_VAL as invAmount,a.CN_NO as grNo,a.PARTY_CODE as partyCode,a.PARTY_NAME as partyName,a.ORD_NO as billOrderNo,a.PURCHASE_COST as purchaseCost,a.RCPT_INV_NO as invoiceNO,a.WEEK as billCloseWeek,a.YEAR as yr ,a.STATUS as status FROM angular_service.tt_bill_punch_dtls_one a  where a.STATUS='APPROVED' and a.WEEK like ?1 ")
	public List<BillCloseStatusDto> findWithApprovedRecords(String bataweek);

	@Query(nativeQuery = true, value = "SELECT a.* FROM angular_service.tt_bill_punch_dtls_one a where (COALESCE(:invoiceNO, null) is null or a.RCPT_INV_NO in :invoiceNO) and (COALESCE(:partycode, null) is null or a.PARTY_CODE in :partycode) and (COALESCE(:billOrderNo, null) is null or a.ORD_NO in :billOrderNo) and (COALESCE(:status, null) is null or a.STATUS in :status)  and  TO_CHAR(a.TRAN_DATE, 'YYYY-MM-DD')  >=:grfromdate and TO_CHAR(a.TRAN_DATE, 'YYYY-MM-DD') <=:grtodate and (COALESCE(:week, null) is null or a.WEEK in :week) and (COALESCE(:year, null) is null or a.YEAR in :year) and (COALESCE(:loc, null) is null or a.RECEIVE_LOC in :loc)  and a.STATUS not in ('RECORD_RECEIVED','CLOSED','SAVE_AS_DRAFT','REJECTED','INVALID_RECORDS')  order by a.STATUS desc ,a.CREATED_ON desc ")
	public List<BillPunchDetailsModel> finfWithStatus(@Param("invoiceNO") String invoiceNO,
			@Param("partycode") String partycode, @Param("billOrderNo") String billOrderNo,
			@Param("status") String status, @Param("grfromdate") String grfromdate, @Param("grtodate") String grtodate,
			@Param("week") List<String> week, @Param("year") String year, @Param("loc") String loc);

	@Query(nativeQuery = true, value = "SELECT distinct a.WEEK as billCloseWeek,a.YEAR as yr FROM angular_service.tt_bill_punch_dtls_one a  where a.STATUS='APPROVED' order by  a.WEEK DESC ")
	public List<BillCloseStatusDto> findWithBillCloseWeek();

	@Query(nativeQuery = true, value = "select a. igstamt, a. cgstamt,a. sgstamt,a.gstamt,a.tcsValue,a.freight,   a. grnoOne,a. grdateNew,a. year, a. totalCost,a. billOrderDate , a. billOrderNo ,a. grDate ,a. invoiceNO ,a. partyCode ,a. partyName,a. purchaseCost  ,a. pair ,a.shopName ,a. shopNo,a.invoiceCost ,a. grNo ,a. bookWeek,a. receiveWk,a.recptInvDate,a.receiveLoc from  EDIT_REPORT_VIEW a where (COALESCE(:partycode, null) is null or a.PARTYCODE in :partycode) and a.BOOKWEEK >=:fromwk and a.BOOKWEEK <=:towk and (COALESCE(:year, null) is null or a.YEAR in :year) and  a.STATUS='APPROVED'   order by a.BILLORDERNO")
	public List<StrazaBillPunchDto> findWithDetailsEditReport(@Param("partycode") List<String> partycode,
			@Param("fromwk") String fromwk, @Param("towk") String towk, @Param("year") String year);

	@Query(nativeQuery = true, value = "select a.GR_NO as grnoOne,a.GR_DATE as grdateNew,a.YEAR as year, a.TOTAL_COST as totalCost,a.FREIGHT as freight,a.BILL_ORDER_DATE as billOrderDate , a.ORD_NO as billOrderNo ,a.RCPT_INV_DATE as grDate ,a.INVOICE_NO as invoiceNO ,a.PARTY_CODE as partyCode ,a.PARTY_NAME as partyName,a.PURCHASE_COST as purchaseCost ,a.RDC_CODE as rdcCode ,a. pair ,a.SHOP_NAME as  shopName ,a.DEST_SHOP_NO as shopNo,a.PRCH_BIL_VAL as invoiceCost ,a.CN_NO as grNo ,a.IGST_AMT as igstamt, a.CGST_AMT as cgstamt,a.SGST_AMT  as sgstamt, a.WEEK as bookWeek,a.WK as receiveWk ,a.RCPT_INV_DATE as recptInvDate,a.RECEIVE_LOC as receiveLoc ,a.stdcost,a.mrp    from  TL_BILL_STRAZA_REPORT_DTLS a where (COALESCE(:partycode, null) is null or a.PARTY_CODE in :partycode) and a.WEEK >=:fromwk and a.WEEK <=:towk and (COALESCE(:year, null) is null or a.YEAR in :year)   order by a.ORD_NO desc ")
	public List<StrazaBillPunchDto> findWithDetailsStrazaReport(@Param("partycode") List<String> partycode,
			@Param("fromwk") String fromwk, @Param("towk") String towk, @Param("year") String year);

	@Query(nativeQuery = true, value = "select  x.status,x.GRNO as grnoOne,x.GR_DATE as grdateNew,x.YEAR as year, x.TOTAL_COST as totalCost,x.FREIGHT as freight,x.BILL_ORDER_DATE as billOrderDate , x.ORD_NO as billOrderNo ,x.RCPT_INV_DATE as grDate ,x.RCPT_INV_NO as invoiceNO ,x.PARTY_CODE as partyCode ,x.PARTY_NAME as partyName,x.PURCHASE_COST as purchaseCost ,x.RDC_CODE as rdcCode ,sum(x.NO_PAIRS) as pair ,x.SHOP_NAME as  shopName ,x.DEST_SHOP_NO as shopNo,x.PRCH_BIL_VAL as invoiceCost ,x.CN_NO as grNo ,x.IGST_AMT as igstamt, x.CGST_AMT as cgstamt,x.SGST_AMT  as sgstamt, x.WEEK as bookWeek,x.WK as receiveWk ,x.RCPT_INV_DATE as recptInvDate,x.RECEIVE_LOC as receiveLoc  from angular_service.tt_bill_punch_dtls_one x where x.WEEK like ?1 and x.YEAR like ?2 and   x.STATUS='CLOSED' group by  x.STATUS,x.GRNO ,x.GR_DATE ,x.YEAR , x.TOTAL_COST,x.FREIGHT ,x.BILL_ORDER_DATE  , x.ORD_NO  ,x.RCPT_INV_DATE  ,x.RCPT_INV_NO  ,x.PARTY_CODE ,x.PARTY_NAME ,x.PURCHASE_COST ,x.RDC_CODE   ,x.SHOP_NAME  ,x.DEST_SHOP_NO ,x.PRCH_BIL_VAL  ,x.CN_NO  ,x.IGST_AMT , x.CGST_AMT ,x.SGST_AMT,WEEK ,x.WK ,x.RCPT_INV_DATE ,x.RECEIVE_LOC  order by x.ORD_NO desc ")
	public List<StrazaBillPunchDto> findWithDetailsStrazaReportAllrecords(String wk, String year);

	@Query(nativeQuery = true, value = " select a. igstamt, a. cgstamt,a. sgstamt,a.gstamt,a.tcsValue,a.freight,   a. grnoOne,a. grdateNew,a. year, a. totalCost,a. billOrderDate , a. billOrderNo ,a. grDate ,a. invoiceNO ,a. partyCode ,a. partyName,a. purchaseCost  ,a. pair ,a.shopName ,a. shopNo,a.invoiceCost ,a. grNo ,a. bookWeek,a. receiveWk ,a.recptInvDate,a.receiveLoc from  EDIT_REPORT_VIEW a where (COALESCE(:partycode, null) is null or a.PARTYCODE in :partycode) and (COALESCE(:year, null) is null or a.YEAR in :year)  and a.STATUS='APPROVED'  order by a.BILLORDERNO")
	public List<StrazaBillPunchDto> findWithDetailsEditReportforAllWK(@Param("partycode") List<String> partycode,
			@Param("year") String year);

	@Query(nativeQuery = true, value = "select a.GR_NO as grnoOne,a.GR_DATE as grdateNew,a.YEAR as year, a.TOTAL_COST as totalCost,a.FREIGHT as freight,a.BILL_ORDER_DATE as billOrderDate , a.ORD_NO as billOrderNo ,a.RCPT_INV_DATE as grDate ,a.INVOICE_NO as invoiceNO ,a.PARTY_CODE as partyCode ,a.PARTY_NAME as partyName,a.PURCHASE_COST as purchaseCost ,a.RDC_CODE as rdcCode ,a. pair ,a.SHOP_NAME as  shopName ,a.DEST_SHOP_NO as shopNo,a.PRCH_BIL_VAL as invoiceCost ,a.CN_NO as grNo ,a.IGST_AMT as igstamt, a.CGST_AMT as cgstamt,a.SGST_AMT  as sgstamt, a.WEEK as bookWeek,a.WK as receiveWk ,a.RCPT_INV_DATE as recptInvDate,a.RECEIVE_LOC as receiveLoc ,a.stdcost,a.mrp  from  TL_BILL_STRAZA_REPORT_DTLS a where (COALESCE(:partycode, null) is null or a.PARTY_CODE in :partycode) and (COALESCE(:year, null) is null or a.YEAR in :year)   order by a.ORD_NO desc ")
	public List<StrazaBillPunchDto> findWithDetailsStrazaReportforAllWK(@Param("partycode") List<String> partycode,
			@Param("year") String year);

	// @Query(nativeQuery = true, value = "SELECT distinct a.* FROM
	// angular_service.tt_bill_punch_dtls_one a where a.C_WEEK like ?1 and
	// a.STATUS='APPROVED'")
	@Query(nativeQuery = true, value = "SELECT  a. articleCode,a.purchaseCostreport,a.billweek,a.yr FROM PRICE_VIEW a  where a.BILLWEEK like ?1 and a.YR like ?2 and  SUBSTR(a.PARTYCODE,0,1) like ?3 ")
	public List<BillPunchResponseInterface> findWithBillPriceReportByWeek(String wk, String year, String PartyCode);

	@Query(nativeQuery = true, value = "SELECT distinct a.* FROM angular_service.tt_bill_punch_dtls_one a  where  a.WEEK like ?1 and a.YEAR like ?2 and SUBSTR(a.PARTY_CODE,0,1) like ?3 and a.STATUS in ('APPROVED','CLOSED') order by a.bill_order_date desc ")
	public List<BillPunchDetailsModel> findWithBillReportByWeek(String wk, String year, String partycode);

	@Query(nativeQuery = true, value = "select  sum(a.NO_PAIRS) as pair,max(a.RCPT_INV_DATE) as invdate ,round(max(a.PRCH_BIL_VAL),2)*100 as invamtreport ,round(max(a.IGST_AMT),2)*100 as igstamt, round(max(a.CGST_AMT),2)*100 as cgstamt,round(max(a.SGST_AMT),2)*100  as sgstamt,a.RCPT_INV_NO as invoiceNO, a.PARTY_CODE as partyCode, round(max(a.PURCHASE_COST),2)*100 as purchaseCostreport,max(a.CN_DATE) as cnDateone,max(a.RCPT_INV_DATE) as recptInvDateone,max(a.RESUP_INV_DATE) as resumeInvDateone,a.RDC_CODE as rdcCode,max(a.CN_NO) as cnNo,max(a.PACKING_CASES_T) as packCaseT ,a.TRNSPRT_CODE as trnsportCode,a.RD_PERMIT_NO as rdpermitNo,a.GRNO as grNo,a.RESUP_INVNO as resumeInvNo,a.RD_PERMIT_NO_2 as resumeInvNoTwo,a.WEEK as billWeek , a.YEAR as yr from  angular_service.tt_bill_punch_dtls_one a  where  a.WEEK like ?1 and a.YEAR like ?2 and a.STATUS in ('CLOSED')  group by a.RCPT_INV_NO , a.PARTY_CODE  ,a.RESUP_INV_DATE ,a.RDC_CODE,a.TRNSPRT_CODE,a.RD_PERMIT_NO,a.GRNO,a.RESUP_INVNO ,a.RD_PERMIT_NO_2,a.WEEK  , a.YEAR  order by a.RCPT_INV_NO desc ")
	public List<BillPunchResponseInterface> findWithBillReportByWeekEdp(String wk, String year);

	@Query(nativeQuery = true, value = "select round(a.IGST_RT ,2)*100 as igst,round(a.SGST_RT ,2)*100 as sgst,round(a.CGST_RT ,2)*100 as cgst,sum(a.NO_PAIRS) as pair,a.RCPT_INV_DATE as invdate ,round(max(a.PRCH_BIL_VAL),2)*100 as invamtreport ,round(sum(a.IGST_AMT),2)*100 as igstamt, round(sum(a.CGST_AMT),2)*100 as cgstamt,round(sum(a.SGST_AMT),2)*100  as sgstamt,a.RCPT_INV_NO as invoiceNO,a.ORD_NO as billOrderNo, a.PARTY_CODE as partyCode,a.ART_CODE as articleCode,round(max(a.PURCHASE_COST)*100,2) as purchaseCostreport,a.CN_DATE as cnDateone,a.RCPT_INV_DATE as recptInvDateone,a.RESUP_INV_DATE as resumeInvDateone,a.RDC_CODE as rdcCode,a.CN_NO as cnNo,round(max(a.PACKING_CASES_T),2) as packCaseT ,round(max(a.PACKING_CASES_S),2) as packCaseS ,round(max(a.PACKING_CASES_B),2) as packCaseB ,round(max(a.PACKING_CASES_C),2) as packCaseC ,round(max(a.PACKING_CASES_M),2) as packCaseM,a.TRNSPRT_CODE as trnsportCode,a.RD_PERMIT_NO as rdpermitNo,a.RESUP_INVNO as resumeInvNo,a.RD_PERMIT_NO_2 as resumeInvNoTwo ,a.TRAN_CODE as tranCodeone,a.SIZE_CODE as sizeCode,a.DC_CODE as dcCode,a.REC_STAT as recStatus,a.GRNO as grNo,a.WEEK as billWeek,a.YEAR as yr from  angular_service.tt_bill_punch_dtls_one a  where   a.WEEK like ?1 and a.YEAR like ?2  and a.STATUS in ('CLOSED')   group by   a.GRNO,a.RCPT_INV_DATE ,a.RCPT_INV_NO ,a.ORD_NO , a.PARTY_CODE ,a.ART_CODE ,a.CN_DATE ,a.RCPT_INV_DATE ,a.RESUP_INV_DATE,a.RDC_CODE ,a.CN_NO ,a.TRNSPRT_CODE ,a.RD_PERMIT_NO  ,a.RESUP_INVNO ,a.RD_PERMIT_NO_2  ,a.TRAN_CODE ,a.SIZE_CODE ,a.DC_CODE,a.REC_STAT ,a.IGST_RT,a.SGST_RT,a.CGST_RT ,a.WEEK ,a.YEAR order by a.ORD_NO desc")
	public List<BillPunchResponseInterface> findWithBillReportByWeekTrans(String wk, String year);

	@Query(nativeQuery = true, value = "select sum(a.NO_PAIRS) as totalpair from  angular_service.tt_bill_punch_dtls_one a where  a.RCPT_INV_NO like ?1")
	public BillPunchResponseInterface findWithBillReportByWeekTransPair(String invNo);

	@Query(nativeQuery = true, value = "select sum(a.NO_PAIRS) as totalpair from  angular_service.tt_bill_punch_dtls_one a where  a.RCPT_INV_NO like ?1 and a.PARTY_CODE like ?2 and a.GRNO LIKE ?3 and a.STATUS='CLOSED' group by a.RCPT_INV_NO ,a.PARTY_CODE,a.GRNO")
	public BillPunchResponseInterface findWithBillReportByWeekTransTotalPair(String invNo, String PartyCode,
			String grno);

	@Query(nativeQuery = true, value = "select sum(a.NO_PAIRS) as totalpair from  angular_service.tt_bill_punch_dtls_one a where  a.RCPT_INV_NO like ?1 and a.PARTY_CODE like ?2 and a.RDC_CODE like ?3 and a.WEEK like ?4  and a.STATUS='CLOSED' group by a.RCPT_INV_NO ,a.PARTY_CODE,a.RDC_CODE")
	public BillPunchResponseInterface findWithBillReportByWeekTransTotalPairTrans(String invNo, String PartyCode,
			String rdcCode, String week);

	@Query(nativeQuery = true, value = "SELECT  max(a.PRCH_BIL_VAL) as totalamt FROM angular_service.tt_bill_punch_dtls_one a  where  a.WEEK like ?1 and a.YEAR like ?2 and a.STATUS in ('CLOSED') group by a.RCPT_INV_NO,a.ORD_NO,a.PARTY ")
	public BillPunchEdpInterface findWithBillReportByWeekEdpXl(String wk, String year);

	@Query(nativeQuery = true, value = "SELECT sum(a.NO_PAIRS)as totalpairs FROM angular_service.tt_bill_punch_dtls_one a  where  a.WEEK like ?1 and a.YEAR like ?2 and a.STATUS in ('CLOSED') ")
	public BillPunchEdpInterface findWithBillReportByWeekEdpXlPairs(String wk, String year);

	// @Query(nativeQuery = true, value = "SELECT a.* FROM
	// angular_service.tt_bill_punch_dtls_one a
	// where a.STATUS='APPROVED'")
	@Query(nativeQuery = true, value = "SELECT SUM(SQ.COST*2)as totalcost FROM (SELECT DISTINCT a.RCPT_INV_NO as RCPT_INV_NO,a.ORD_NO as orderNo,round(a.TOTAL_COST,2) as COST,a.PARTY FROM angular_service.tt_bill_punch_dtls_one a where a.WEEK like ?1 and a.YEAR like ?2 and SUBSTR(a.PARTY_CODE,0,1) like ?3 and a.STATUS='CLOSED' ) SQ")
	public TotalAmtInterface findWithTotalAmt(String wk, String year, String partycode);

	// @Query(nativeQuery = true, value = "SELECT a.RCPT_INV_NO as
	// invno,a.INVOICE_COST as invcost, a.INV_TYPE as invtype FROM
	// angular_service.tt_bill_punch_dtls_one a where a.STATUS='APPROVED' group by
	// a.RCPT_INV_NO,a.INVOICE_COST, a.INV_TYPE")
	@Query(nativeQuery = true, value = "SELECT round(a.TOTAL_COST,2) as invtotalcost ,a.RDC_CODE as rdcno,a.PARTY as party,a.DISTRC_CODE as distcode,a.ORD_NO as ordno,a.RCPT_INV_NO as invno,round(a.PRCH_BIL_VAL,2) as invcost, a.INV_TYPE as invtype,a.RCPT_INV_DATE as invdate,a.CN_DATE as grndate,sum(a.NO_PAIRS) as pairs FROM angular_service.tt_bill_punch_dtls_one a where a.WEEK like ?1 and a.YEAR like ?2 and SUBSTR(a.PARTY_CODE,0,1) like ?3 and   a.RCPT_INV_NO is not null and a.STATUS='CLOSED' group by a.RCPT_INV_NO,a.PRCH_BIL_VAL, a.INV_TYPE,a.RCPT_INV_DATE,a.CN_DATE,a.PARTY ,a.DISTRC_CODE,a.ORD_NO,a.RDC_CODE,a.TOTAL_COST ")
	public List<AdonisFileDetailsInterface> findWithAdonisFileDetails(String wk, String year, String partycode);

	@Query(nativeQuery = true, value = "SELECT x.createdOn FROM (     SELECT a.CREATED_ON as createdOn FROM angular_service.tt_bill_punch_dtls_one a  where a.ORD_NO like ?1 and  a.RCPT_INV_NO like ?2 and a.WEEK like ?3 and a.STATUS='CLOSED' ORDER BY a.CREATED_ON DESC )x WHERE ROWNUM = 1")
	public AdonisFileDetailsInterface findWithAdonisFileDetailsWeek(String orderNo, String InvNo, String week);

	@Query(nativeQuery = true, value = "SELECT  a.RCPT_INV_NO as invno ,a.PARTY_CODE as party,a.ORD_NO as ordno FROM angular_service.tt_bill_punch_dtls_one a where a.RCPT_INV_NO like ?1 and a.ORD_NO like ?2 and a.PARTY_CODE like ?3 and a.STATUS not in('RECORD_RECEIVED','SAVE_AS_DRAFT','REJECTED','INVALID_RECORDS') ")
	public List<AdonisFileDetailsInterface> findwithInvnoAndPartyCode(String invno, String orderno, String partycode);

	@Query(nativeQuery = true, value = "select  a.* from  angular_service.tt_bill_punch_dtls_one a where  a.RCPT_INV_NO like ?1 and a.ORD_NO like ?2 and a.status in('RECORD_RECEIVED','REJECTED')")
	public List<BillPunchDetailsModel> findWithInvoiceAndOrdNo(String invno, String orderno);

}
