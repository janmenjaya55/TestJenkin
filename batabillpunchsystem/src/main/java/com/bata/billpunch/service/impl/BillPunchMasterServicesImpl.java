package com.bata.billpunch.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bata.billpunch.constant.ReraMessageConstants;
import com.bata.billpunch.dao.ApprovalDetailsDao;
import com.bata.billpunch.dao.ArticlesMasterDao;
import com.bata.billpunch.dao.BillPunchDetailsDao;
import com.bata.billpunch.dao.BillPunchDetailsTestDao;
import com.bata.billpunch.dao.CategoriesMasterDao;
import com.bata.billpunch.dao.EdpReportDao;
import com.bata.billpunch.dao.OrdersMasterDao;
import com.bata.billpunch.dao.PartiesMasterDao;
import com.bata.billpunch.dao.RdcMappingDao;
import com.bata.billpunch.dao.ShopMasterDao;
import com.bata.billpunch.dao.StateMasterDao;
import com.bata.billpunch.dao.TcsMasterDao;
import com.bata.billpunch.dao.TransReportDao;
import com.bata.billpunch.dao.WeekMasterDao;
import com.bata.billpunch.model.ApprovalDetailsModel;
import com.bata.billpunch.model.ArticlesMasterModel;
import com.bata.billpunch.model.BillPunchDetailsModel;
import com.bata.billpunch.model.BillPunchEdpReportModel;
import com.bata.billpunch.model.BillPunchTransReportModel;
import com.bata.billpunch.model.CategoriesMasterModel;
import com.bata.billpunch.model.OrdersMasterModel;
import com.bata.billpunch.model.RdcMappingModel;
import com.bata.billpunch.model.ShopMasterModel;
import com.bata.billpunch.model.StateMasterModel;
import com.bata.billpunch.model.TCSmasterModel;
import com.bata.billpunch.model.WeekMasterModel;
import com.bata.billpunch.model.dto.AdonisFileDetailsInterface;
import com.bata.billpunch.model.dto.BillPunchCheckBoxDto;
import com.bata.billpunch.model.dto.BillPunchEdpInterface;
import com.bata.billpunch.model.dto.BillPunchResponse;
import com.bata.billpunch.model.dto.BillPunchResponseInterface;
import com.bata.billpunch.model.dto.BillPurchaseCostInterface;
import com.bata.billpunch.model.dto.BillPurchaseStatusInterface;
import com.bata.billpunch.model.dto.PartyNameCodeDto;
import com.bata.billpunch.model.dto.PartyResponseDto;
import com.bata.billpunch.model.dto.PurchaseCostInterface;
import com.bata.billpunch.model.dto.StrazaBillPunchDto;
import com.bata.billpunch.model.dto.TotalAmtInterface;

@Service
@Transactional
public class BillPunchMasterServicesImpl {

	@Autowired
	private EdpReportDao edao;

	@Autowired
	private TransReportDao transdao;

	@Autowired
	private TcsMasterDao tcsdao;

	@Autowired
	private BillPunchDetailsDao bdao;

	@Autowired
	private ArticlesMasterDao ardao;

	@Autowired
	private PartiesMasterDao pdao;

	@Autowired
	private StateMasterDao stdao;

	@Autowired
	private ShopMasterDao shdao;

	@Autowired
	private BillPunchDetailsTestDao tdao;

	@Autowired
	private CategoriesMasterDao catdao;

	@Autowired
	private WeekMasterDao wdao;

	@Autowired
	private OrdersMasterDao ordao;

	@Autowired
	private ApprovalDetailsDao apdao;

	@Autowired
	private RdcMappingDao rdcmapdao;

	public List<ArticlesMasterModel> findWithArticleDetails(String artno, String billno, String partycode,
			String orderno) {

		return ardao.findWithArticleDetails(artno);

	}

	public List<StateMasterModel> findWithStateDetails(String statename) {
		return stdao.findWithStateDetails(statename);
	}

	public List<ShopMasterModel> findWithShopDetails(String shopname) {

		/*
		 * StateMasterModel msc =
		 * stdao.findWithStateName(StringUtils.leftPad(String.valueOf(xm.getToState()),
		 * 2, "0"));
		 * 
		 * if (Optional.ofNullable(msc.getStateName()).isPresent()) { if
		 * (msc.getStateName().equalsIgnoreCase("WEST BENGAL")) { xm.setRegion("EAST");
		 * 
		 * } else if (msc.getStateName().equalsIgnoreCase("HARYANA")) {
		 * xm.setRegion("NORTH"); }
		 * 
		 * else if (msc.getStateName().equalsIgnoreCase("MAHARASHTRA")) {
		 * xm.setRegion("WEST"); }
		 * 
		 * else if (msc.getStateName().equalsIgnoreCase("KARNATAKA") ||
		 * msc.getStateName().equalsIgnoreCase("TAMIL NADU")) { xm.setRegion("SOUTH"); }
		 * } else { xm.setRegion("NA"); }
		 */

		return shdao.findWithShopDetails("%" + shopname + "%");
	}

	public List<CategoriesMasterModel> findWithCatagoryDetails(String catname) {
		return catdao.findWithCatDetails(catname);
	}

	public List<WeekMasterModel> findWithWeekDetails(String weekcode) {
		return wdao.findWithWeekDetails(weekcode);
	}

	public List<BillPunchResponseInterface> getDetailsByBillNo(String invoiceNO, String partycode, String orederno,
			String status, String grfromdate, String grtodate, List<String> week, String year, String loc) {

		return bdao.findWithBillNoPartyCodeAndOrderNoTest(invoiceNO, partycode, orederno, status, grfromdate, grtodate,
				week, year, loc);
	}

	public List<BillPunchResponseInterface> getDetailsForApprovalPage(String invoiceNO, String partycode,
			String orederno, String uniquecode, String status, String grfromdate, String grtodate, List<String> week,
			String year, String loc) {

		return bdao.findWithForApprovalPage(invoiceNO, partycode, orederno, uniquecode, status, grfromdate, grtodate,
				week, year, loc);
	}

	public List<BillPunchResponseInterface> getDetailsByFilter(String invoiceNO, String partycode, String orederno,
			String uniquecode, String status, String grfromdate, String grtodate, List<String> week, String year,
			String loc) {

		return bdao.findWithHistory(invoiceNO, partycode, orederno, uniquecode, status, grfromdate, grtodate, week,
				year, loc);
	}

	public List<BillPunchResponseInterface> getDetailsByBillNoManual(String invoiceNO, String partycode,
			String orederno, String uniquecode, String status, String grfromdate, String grtodate, List<String> week,
			String year, String loc) {

		return bdao.findWithBillNoPartyCodeAndOrderNoManual(invoiceNO, partycode, orederno, uniquecode, status,
				grfromdate, grtodate, week, year, loc);
	}

	public BillPunchDetailsModel getDetailsByOrderNoAndInvoiceNo(String orderno, String invno) {

		return bdao.findwithAllDetailsByOrderAndInvoice(orderno, invno);
	}

	public List<BillPurchaseStatusInterface> getStatusByOrder(String orderno, String invno, String partycode) {

		if (orderno == null) {
			orderno = "%";
		}
		if (invno == null) {
			invno = "%";
		}

		if (partycode == null) {
			partycode = "%";
		}

		return bdao.findwithOrderNo(orderno, invno, partycode);
	}

	public List<ArticlesMasterModel> findAll() {
		return ardao.findAll();
	}

	public List<StateMasterModel> findAllDetails() {
		return stdao.findAll();
	}

	public List<ShopMasterModel> getAll() {
		return shdao.findAll();
	}

	public List<CategoriesMasterModel> getAllDetails() {
		return catdao.findAll();
	}

	public List<WeekMasterModel> findDetails() {
		return wdao.findAll();
	}

	@SuppressWarnings("all")
	public BillPunchCheckBoxDto updateByCheckBox(List<BillPunchResponse> entity, String userName) {
		List<BillPunchDetailsModel> list = new ArrayList<>();

		Double amtt = 0d;
		Double amtone = 0d;
		Double amx = 0d;
		Double amy = 0d;
		String m = "ok";

		for (BillPunchResponse xm : entity) {

			List<BillPunchResponseInterface> cm = bdao.findWithPartycodeAndParty(xm.getInvoiceNO(),
					xm.getBillOrderNo());

			if (cm.get(0).getpartyCode().substring(2, 5).equalsIgnoreCase(cm.get(0).getparty())) {
				List<BillPunchDetailsModel> vn = bdao.findWithInvnoAndPartyCodeAndOrderNo(xm.getInvoiceNO(),
						xm.getPartyCode(), xm.getBillOrderNo());
				amtt = 0d;
				amtone = 0d;
				amx = 0d;
				amy = 0d;

				PurchaseCostInterface ch = null;
				try {
					List<BillPunchResponseInterface> sm = bdao
							.findWithBillNoPartyCodeAndOrderNoAndPartycodeList(xm.getInvoiceNO(), xm.getBillOrderNo());
					for (BillPunchResponseInterface pr : sm) {

						ch = bdao.findWithPurchaseCostFromOrder(xm.getBillOrderNo(), xm.getPartyCode(),
								vn.get(0).getRdcCode(), pr.getarticleCode());

						if (Optional.ofNullable(ch).isPresent()) {
							Double mt = Double.parseDouble(ch.getpurchaseCost()) * Integer.parseInt(pr.getpair());
							amy += mt;
						} else {

							List<RdcMappingModel> xn = rdcmapdao.findByMergerdccode(vn.get(0).getRdcCode());

							for (RdcMappingModel bm : xn) {
								ch = bdao.findWithPurchaseCostFromOrder(xm.getBillOrderNo(), xm.getPartyCode(),
										bm.getRdcno(), pr.getarticleCode());
								if (Optional.ofNullable(ch).isPresent()) {
									Double mt = Double.parseDouble(ch.getpurchaseCost())
											* Integer.parseInt(pr.getpair());
									amy += mt;
								}

							}

						}

					}
				} catch (Exception e) {
					// TODO: handle exception
				}

				for (BillPunchDetailsModel vmm : vn) {

					if (Optional.ofNullable(vmm.getPairAmount()).isPresent() && Double.valueOf(vmm.getIgst()) != 0.0) {
						Double mt = (vmm.getPairAmount() * Double.valueOf(vmm.getIgst())) / 100;
						amtt += mt;

					}

					if (Optional.ofNullable(vmm.getPairAmount()).isPresent() && Double.valueOf(vmm.getCgst()) != 0.0) {
						Double mt = (vmm.getPairAmount() * Double.valueOf(vmm.getCgst())) / 100;
						amtone += mt;

					}

					if (Optional.ofNullable(vmm.getPairAmount()).isPresent()) {
						Double mt = vmm.getPairAmount();
						amx += mt;

					}

				}
				if (amx <= amy) {
					for (BillPunchDetailsModel vm : vn) {
						try {
							List<WeekMasterModel> wk = wdao.findAll();
							for (WeekMasterModel lm : wk) {
								Calendar cal = Calendar.getInstance();
								SimpleDateFormat d11 = new SimpleDateFormat("yyyy-MM-dd");
								String d2 = d11.format(cal.getTime());
								Date d22 = lm.getWeekSdate();
								String d1 = d11.format(d22);
								Date d33 = lm.getWeekEdate();
								String d3 = d11.format(d33);

								if (d2.compareTo(d1) >= 0) {
									if (d2.compareTo(d3) <= 0) {
										vm.setBataWeek(lm.getBataWeek());
									}
								}

							}

						} catch (Exception e) {
							e.printStackTrace();
						}
						Calendar ss = Calendar.getInstance();
						vm.setBataYear(String.valueOf(ss.get(Calendar.YEAR)));
						vm.setStatus(xm.getStatus());
						vm.setCreatedOn(Calendar.getInstance());
						vm.setCreatedBy(userName);
						vm.setCreatedOn(Calendar.getInstance());
						vm.setIgstamt(amtt);
						vm.setCgstamt(amtone);
						vm.setSgstamt(amtone);
						if (Optional.ofNullable(xm.getDiscountAmt()).isPresent()) {
							if (!"no".equalsIgnoreCase(xm.getDiscountAmt())) {
								vm.setDiscountAmt(xm.getDiscountAmt());
								vm.setDiscountAmtVal((amx * Double.parseDouble(xm.getDiscountAmt())) / 100);
							}
						}

						if (!Optional.ofNullable(vm.getIgst()).isPresent()) {
							vm.setIgst("0");
						}
						if (!Optional.ofNullable(vm.getCgst()).isPresent()) {
							vm.setCgst("0");
						}
						if (!Optional.ofNullable(vm.getSgst()).isPresent()) {
							vm.setSgst("0");
						}
						if (!Optional.ofNullable(vm.getFreight()).isPresent()) {
							vm.setFreight("0");
						}
						if (!Optional.ofNullable(vm.getCreditNote()).isPresent()) {
							vm.setCreditNote(0d);
						}
						if (Optional.ofNullable(xm.getTcsApplicable()).isPresent()) {

							if ("yes".equalsIgnoreCase(xm.getTcsApplicable())) {

								vm.setTcsApplicable(xm.getTcsApplicable());

								Double amt = amx + amtt + amtone + amtone + vm.getCreditNote()
										+ Integer.parseInt(vm.getFreight());
								TCSmasterModel tcs = tcsdao.findAll().get(0);
								vm.setTcsPercent(tcs.getTcsVaule());
								vm.setPurchaseCost(amy);
								vm.setInvoiceCost(amx);
								vm.setTcsValue((amt * Double.parseDouble(tcs.getTcsVaule())) / 100);
								vm.setTotalCost(amt + (amt * Double.parseDouble(tcs.getTcsVaule())) / 100);
							} else {
								Double amt = amx + amtt + amtone + amtone + vm.getCreditNote()
										+ Integer.parseInt(vm.getFreight());
								// TCSmasterModel tcs = tcsdao.findAll().get(0);
								// vm.setTcsPercent(tcs.getTcsVaule());
								vm.setPurchaseCost(amy);
								vm.setInvoiceCost(amx);
								// vm.setTcsValue((amt * Double.parseDouble(tcs.getTcsVaule())) / 100);
								vm.setTotalCost(amt);

							}
						} else {
							Double amt = amx + amtt + amtone + amtone + vm.getCreditNote()
									+ Integer.parseInt(vm.getFreight());
							vm.setPurchaseCost(amy);
							vm.setInvoiceCost(amx);
							vm.setTotalCost(amt);

						}

						BillPunchDetailsModel pm = bdao.save(vm);
						list.add(pm);

					}
				}

			} else {
				m = "Please check Partycode and Partyno are not match for some invoices.";
			}

		}
		BillPunchCheckBoxDto data = new BillPunchCheckBoxDto();
		data.setListdata(list);
		data.setMessage(m);
		return data;
	}

	public List<PartyNameCodeDto> findAllParty() {
		List<PartyNameCodeDto> list = new ArrayList<>();
		List<PartyResponseDto> xm = pdao.findWithAllPartycodeAndPartyName();
		for (PartyResponseDto vm : xm) {
			PartyNameCodeDto cm = new PartyNameCodeDto();
			String s = vm.getpartycode().concat(" ").concat("-").concat(" ").concat(vm.getpartyname());
			cm.setPartycode(vm.getpartycode());
			cm.setPartyname(vm.getpartyname());
			cm.setPartynamecode(s);
			list.add(cm);
		}

		return list;

	}

	@SuppressWarnings("all")
	public List<BillPunchDetailsModel> getDetailsByBillNoTest(String invno, String xyz, String ordno, String pk,
			String ss) {
		return tdao.findWithBillNoPartyCodeAndOrderNoTest(null, null, "L0D1075", null, null);
	}

	public BillPurchaseCostInterface getOrderPurchaseCost(String rdcNo, String billOrderNo, String partyCode,
			String articleCode) {
		return ordao.findWithPurchaseCost(rdcNo, billOrderNo, partyCode, articleCode);
	}

	public List<OrdersMasterModel> getOrderDetails(String billOrderNo) {
		return ordao.findWithBillOrderDetailsByOrderNo(billOrderNo);
	}

	public List<BillPunchResponseInterface> getDetailsByFilterRdc(String invoiceNO, String billNO) {

		return bdao.findWithBillNoPartyCodeAndOrderNoAndPartycode(invoiceNO, billNO);

	}
	
	public List<BillPunchResponseInterface> getDetailsByFilterRdcList(String invoiceNO, String billNO) {

		return bdao.findWithBillNoPartyCodeAndOrderNoAndPartycodeList(invoiceNO, billNO);

	}

	public BillPunchResponseInterface getAllAtrnoAndOrdnoAndInvNoDetails(String atrno, String ordno, String invno) {

		return bdao.findWithBillNoArtCodeAndOrderNo(atrno, ordno, invno);
	}
	
	public List<BillPunchResponseInterface> getAllAtrnoAndOrdnoAndInvNoDetailsForList(String partycode, String ordno, String invno) {

		return bdao.findWithBillNoArtCodeAndOrderNoList(partycode, ordno, invno);
	}

	public List<BillPunchDetailsModel> getDetailsByFilter(String invoiceNO, String partyCode, String billOrderNo,
			String billUniqueCode, String status) {

		return bdao.findWithBillNoPartyCodeAndOrderNo(invoiceNO, partyCode, billOrderNo, billUniqueCode, status);

	}

	public List<ApprovalDetailsModel> getDetailsByFilterHistory(String invoiceNO, String partyCode, String billOrderNo,
			String billUniqueCode, String status, String createdon) {

		return apdao.findWithBillNoHistory(invoiceNO, partyCode, billOrderNo, billUniqueCode, status, createdon);

	}
	
	public List<ApprovalDetailsModel> getDetailsByFilterHistoryone(String invoiceNO, String partyCode, String billOrderNo,
			 String status) {

		return apdao.findWithBillNoHistoryOne(invoiceNO, partyCode, billOrderNo, status);

	}

	public List<BillPunchResponseInterface> getDetailsByFilterHistoryRows(String invoiceNO, String orderNo) {

		return apdao.findWithBillNoHistoryrows(invoiceNO, orderNo);

	}

	public PurchaseCostInterface getPurchaseCost(String invoiceNO, String partyCode, String billOrderNo,
			String billUniqueCode, String status) {

		return bdao.findWithPurchaseCost(invoiceNO, partyCode, billOrderNo, billUniqueCode, status);

	}

	public PurchaseCostInterface getPurchaseCostFromOrder(String billOrderNo, String partyCode, String rdcCode,
			String artNo) {

		if (billOrderNo == null) {
			billOrderNo = "%";
		}
		if (partyCode == null) {
			partyCode = "%";
		}

		if (rdcCode == null) {
			rdcCode = "%";
		}

		if (artNo == null) {
			artNo = "%";
		}

		return bdao.findWithPurchaseCostFromOrder(billOrderNo, partyCode, rdcCode, artNo);

	}

	public PurchaseCostInterface getDateFromOrder(String billOrderNo, String partyCode, String rdcCode,
			String artCode) {

		return bdao.findWithDateFromOrder(billOrderNo, partyCode, rdcCode, artCode);

	}

	public List<BillPunchDetailsModel> getReportDetailsByWeek(String wk, String year, String partycode) {
		return bdao.findWithBillReportByWeek(wk, year, partycode);
	}

	public List<BillPunchResponseInterface> getReportDetailsByWeekTrans(String wk, String year) {
		return bdao.findWithBillReportByWeekTrans(wk, year);
	}

	public List<BillPunchResponseInterface> getReportDetailsByWeekEdp(String wk, String year) {
		return bdao.findWithBillReportByWeekEdp(wk, year);
	}

	public List<StrazaBillPunchDto> findWithDetailsStrazaReportAllrecords(String wk, String year) {
		return bdao.findWithDetailsStrazaReportAllrecords(wk, year);
	}

	public List<BillPunchTransReportModel> findAllApprovedDetailsForTransReport(String wk, String year,
			String partycode) {

		return transdao.findWithBillReportByWeekTrans(wk, year, partycode);
	}

	public BillPunchResponseInterface getReportPairDetails(String invNo) {
		return bdao.findWithBillReportByWeekTransPair(invNo);
	}

	public BillPunchResponseInterface getReportTotalPairDetails(String invNo, String partycode, String grno) {
		return bdao.findWithBillReportByWeekTransTotalPair(invNo, partycode, grno);
	}
	
	public BillPunchResponseInterface getReportTotalPairDetailsTrans(String invNo, String partycode,String rdcCode,String week) {
		return bdao.findWithBillReportByWeekTransTotalPairTrans(invNo, partycode,rdcCode,week);
	}

	public List<BillPunchEdpReportModel> findAllApprovedDetailsEdp(String wk, String year) {

		return edao.findWithBillReportByWeekEdp(wk, year);
	}

	public BillPunchEdpInterface getReportDetailsByWeekEdpXl(String wk, String year) {
		return bdao.findWithBillReportByWeekEdpXl(wk, year);
	}

	public BillPunchEdpInterface getReportDetailsByWeekEdpXlPairs(String wk, String year) {
		return bdao.findWithBillReportByWeekEdpXlPairs(wk, year);
	}

	public List<BillPunchResponseInterface> getPriceReportDetailsByWeek(String wk, String year, String partycode) {
		return bdao.findWithBillPriceReportByWeek(wk, year, partycode);
	}

	public TotalAmtInterface getTotalAmountForAdonis(String wk, String year, String partycode) {
		return bdao.findWithTotalAmt(wk, year, partycode);
	}

	public List<AdonisFileDetailsInterface> getAdonisDetails(String wk, String year, String partycode) {
		return bdao.findWithAdonisFileDetails(wk, year, partycode);
	}

	public AdonisFileDetailsInterface getAdonisDetailsweek(String orderNo, String InvNo, String week) {
		return bdao.findWithAdonisFileDetailsWeek(orderNo, InvNo,week);
	}

	public List<OrdersMasterModel> findWithOrderDetails(String orderno) {
		return ordao.findWithBillOrderDetailsByOrderNo(orderno);
	}

}
