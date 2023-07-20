package com.bata.billpunch.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.bata.billpunch.dao.ApprovalDetailsDao;
import com.bata.billpunch.dao.BillPunchDetailsDao;
import com.bata.billpunch.dao.BillPunchDetailsDemoDao;
import com.bata.billpunch.dao.BillPunchDetailsImportDao;
import com.bata.billpunch.dao.GstReportDao;
import com.bata.billpunch.dao.OrdersMasterDao;
import com.bata.billpunch.dao.ShopMasterDao;
import com.bata.billpunch.dao.StrazaReportDao;
import com.bata.billpunch.dao.SupplyReportDao;
import com.bata.billpunch.dao.WeekMasterDao;
import com.bata.billpunch.model.ApprovalDetailsModel;
import com.bata.billpunch.model.BillPunchDetailsImportDataModel;
import com.bata.billpunch.model.BillPunchDetailsModel;
import com.bata.billpunch.model.BillPunchGstReportModel;
import com.bata.billpunch.model.BillPunchSupplyReportModel;
import com.bata.billpunch.model.OrdersMasterModel;
import com.bata.billpunch.model.WeekMasterModel;
import com.bata.billpunch.model.dto.BillCloseStatusDto;
import com.bata.billpunch.model.dto.BillPunchDto;
import com.bata.billpunch.model.dto.BillPunchResponseInterface;
import com.bata.billpunch.model.dto.ChartsInterface;
import com.bata.billpunch.model.dto.OrderPair;
import com.bata.billpunch.model.dto.PartyResponseDto;
import com.bata.billpunch.model.dto.PriceInterface;
import com.bata.billpunch.model.dto.RdcDetailsDto;
import com.bata.billpunch.model.dto.ReceivingLocDto;
import com.bata.billpunch.model.dto.StrazaBillPunchDto;

@Service
@Transactional
public class BillPunchServicesImpl {

	@Autowired
	private BillPunchDetailsImportDao rdcdao;

	@Autowired
	private ApprovalDetailsDao apdao;

	@Autowired
	private ShopMasterDao sdao;

	@Autowired
	private StrazaReportDao stdao;

	@Autowired
	private BillPunchDetailsDao bdao;

	@Autowired
	private OrdersMasterDao odao;

	@Autowired
	private WeekMasterDao wdao;

	@Autowired
	private BillPunchDetailsDemoDao ddao;

	@Autowired
	private SupplyReportDao splydao;

	@Autowired
	private GstReportDao gdao;

	@SuppressWarnings("all")
	public List<BillPunchDetailsModel> save(BillPunchDto pm, String userName) {
		BillPunchDetailsModel vm = null;
		List<BillPunchDetailsModel> bm = new ArrayList<>();

		if (bdao.findwithInvnoAndPartyCode(pm.getInvoiceNO(), pm.getBillOrderNo(), pm.getPartyCode()).size() > 1) {
			throw new ResourceAccessException(" Please fill with different Invoice no and partycode");
		} else {

			if (Optional.ofNullable(pm.getFormtype()).isPresent() && pm.getFormtype().equalsIgnoreCase("manual")) {

				if (!pm.getRdcList().isEmpty()) {
					StringBuilder sb1 = new StringBuilder("");
					if (Optional.ofNullable(bdao.findwithLastBillEntry()).isPresent()) {

						BillPunchDetailsModel s = bdao.findwithLastBillEntry();
						String v = s.getBillUniqueCode();
						String[] p = v.split("_");
						Integer number = Integer.valueOf(p[p.length - 1]) + 1;

						sb1.append("BL").append("_").append(number);

					} else {

						Integer number = 01;
						sb1.append("BL").append("_").append(number);

					}
					String rdc = null;
					if (Optional.ofNullable(pm.getBillId()).isPresent()) {
						try {
							if (!Optional.ofNullable(pm.getRdcCode()).isPresent()) {
								rdc = bdao.findById(pm.getBillId()).get().getRdcCode();
							} else {
								rdc = pm.getRdcCode();
							}
							bdao.deleteById(pm.getBillId());
						} catch (Exception e) {
							e.printStackTrace();
						}

					} else {
						rdc = pm.getRdcCode();
					}

					for (RdcDetailsDto lm : pm.getRdcList()) {
						if (Optional.ofNullable(lm.getBillId()).isPresent()) {

							try {
								if (!Optional.ofNullable(pm.getRdcCode()).isPresent()) {
									rdc = bdao.findById(lm.getBillId()).get().getRdcCode();
								} else {
									rdc = pm.getRdcCode();
								}
								bdao.deleteById(lm.getBillId());

							} catch (Exception e) {
								e.printStackTrace();
							}

						} else {
							if (rdc == null) {
								rdc = pm.getRdcCode();
							}

						}

					}
					if (Optional.ofNullable(pm.getBillOrderNo()).isPresent()) {

						for (RdcDetailsDto lm : pm.getRdcList()) {
							BillPunchDetailsModel entity = new BillPunchDetailsModel();
							entity.setBillUniqueCode(sb1.toString());

							try {
								List<WeekMasterModel> wk = wdao.findAll();
								for (WeekMasterModel km : wk) {
									Calendar cal = Calendar.getInstance();
									SimpleDateFormat d11 = new SimpleDateFormat("yyyy-MM-dd");
									String d2 = d11.format(cal.getTime());
									Date d22 = km.getWeekSdate();
									String d1 = d11.format(d22);
									Date d33 = km.getWeekEdate();
									String d3 = d11.format(d33);

									if (d2.compareTo(d1) >= 0 && d2.compareTo(d3) <= 0) {
										entity.setBataWeek(km.getBataWeek());
									}

								}

							} catch (Exception e) {
								e.printStackTrace();
							}
							Calendar ss = Calendar.getInstance();
							entity.setBataYear(String.valueOf(ss.get(Calendar.YEAR)));
							entity.setBillWeekYear(String.valueOf(ss.get(Calendar.YEAR)));
							if (Optional.ofNullable(lm.getArticleCode()).isPresent()
									|| Optional.ofNullable(lm.getReceiveDate()).isPresent()
									|| Optional.ofNullable(lm.getPairAmount()).isPresent()
									|| Optional.ofNullable(lm.getRdcPair()).isPresent()
									|| Optional.ofNullable(lm.getWeekYear()).isPresent()) {

								if (Optional.ofNullable(pm.getDiscountAmtVal()).isPresent()) {
									entity.setDiscountAmt(pm.getDiscountAmt());
									entity.setDiscountAmtVal(pm.getDiscountAmtVal());
								} else {
									entity.setDiscountAmt("0");
									entity.setDiscountAmtVal(0.0);
								}
								if (Optional.ofNullable(pm.getTcsValue()).isPresent()) {
									entity.setTcsPercent(pm.getTcsPercent());
									entity.setTcsValue(pm.getTcsValue());
								} else {
									entity.setTcsPercent("0");
									entity.setTcsValue(0.0);
								}

								if (Optional.ofNullable(pm.getFreight()).isPresent() && !pm.getFreight().isEmpty()) {
									entity.setFreight(pm.getFreight());
								} else {
									entity.setFreight("0");
								}
								if (Optional.ofNullable(pm.getCreditNote()).isPresent()) {
									entity.setCreditNote(pm.getCreditNote());
								} else {
									entity.setCreditNote(0.0);
								}
								entity.setPackCaseB(0);
								entity.setPackCaseS(0);
								entity.setPackCaseC(0);
								entity.setPackCaseM(0);
								entity.setPackCaseT(0);
								entity.setBillCloseStatus(pm.getBillCloseStatus());
								if (Optional.ofNullable(rdc).isPresent()) {
									entity.setRdcCode(StringUtils.leftPad(rdc, 5, "0"));
								}
								entity.setRdcPairC(lm.getRdcPairC());
								entity.setPurchaseCost(pm.getPurchaseCost());
								entity.setInvoiceCost(pm.getInvoiceCost());
								entity.setTcsApplicable(pm.getTcsApplicable());
								entity.setBillOrderDate(pm.getBillOrderDate());
								entity.setBillOrderNo(pm.getBillOrderNo());
								entity.setBillWeekYear(pm.getBillWeekYear());
								entity.setShopName(pm.getShopName());
								entity.setShopNo(pm.getShopNo());
								entity.setCreatedOn(Calendar.getInstance());
								entity.setRecptInvDate(pm.getRecptInvDate());
								entity.setPair(pm.getPair());
								entity.setPartyCode(pm.getPartyCode());
								entity.setPartyName(pm.getPartyName());
								entity.setReceiveLoc(pm.getReceiveLoc());
								entity.setRegion(pm.getRegion());
								entity.setStateName(pm.getStateName());
								entity.setStateCode(pm.getStateCode());
								entity.setShopName(pm.getShopName());
								entity.setStatus(pm.getStatus());
								// entity.setGstamt(Double.valueOf(pm.getIgst()+pm.getCgst()+pm.getSgst()));
								entity.setIgst(pm.getIgst());
								entity.setCgst(pm.getCgst());
								entity.setSgst(pm.getSgst());
								entity.setIgstamt(pm.getIgstamt());
								entity.setCgstamt(pm.getCgstamt());
								entity.setSgstamt(pm.getSgstamt());
								entity.setTotalCost(pm.getTotalCost());
								entity.setFormtype(pm.getFormtype());
								entity.setInvoiceNO(pm.getInvoiceNO());
								entity.setGrNo(pm.getGrNo());
								entity.setGrDate(pm.getGrDate());
								entity.setCnNo(pm.getCnNo());
								entity.setCnDate(pm.getCnDate());
								entity.setCreatedBy(userName);
								entity.setWeekYear(lm.getWeekYear());
								entity.setArticleCode(lm.getArticleCode());
								entity.setArticleName(lm.getArticleName());
								entity.setPairAmount(lm.getPairAmount());
								entity.setRdcPair(lm.getRdcPair());
								entity.setReceiveDate(lm.getReceiveDate());
								entity.setNoOfCartons(lm.getNoOfCartons());
								entity.setCreatedOn(Calendar.getInstance());

								vm = bdao.save(entity);

								bm.add(vm);
							}
						}
					} else {
						throw new ResourceAccessException(" Please fill up the BillOrderNo.");
					}

				} else {

					StringBuilder sb1 = new StringBuilder("");
					if (Optional.ofNullable(bdao.findwithLastBillEntry()).isPresent()) {

						BillPunchDetailsModel s = bdao.findwithLastBillEntry();
						String v = s.getBillUniqueCode();
						String[] p = v.split("_");
						Integer number = Integer.valueOf(p[p.length - 1]) + 1;

						sb1.append("BL").append("_").append(number);
						// entity.setBillUniqueCode(sb1.toString());

					} else {

						Integer number = 01;
						sb1.append("BL").append("_").append(number);

					}
					String rdc = null;
					if (Optional.ofNullable(pm.getBillId()).isPresent()) {
						if (!Optional.ofNullable(pm.getRdcCode()).isPresent()) {
							rdc = bdao.findById(pm.getBillId()).get().getRdcCode();
						} else {
							rdc = pm.getRdcCode();
						}

						bdao.deleteById(pm.getBillId());

					} else {
						rdc = pm.getRdcCode();
					}
					if (Optional.ofNullable(pm.getBillOrderNo()).isPresent()) {

						BillPunchDetailsModel entity = new BillPunchDetailsModel();
						entity.setBillUniqueCode(sb1.toString());

						try {
							List<WeekMasterModel> wk = wdao.findAll();
							for (WeekMasterModel km : wk) {
								Calendar cal = Calendar.getInstance();
								SimpleDateFormat d11 = new SimpleDateFormat("yyyy-MM-dd");
								String d2 = d11.format(cal.getTime());
								Date d22 = km.getWeekSdate();
								String d1 = d11.format(d22);
								Date d33 = km.getWeekEdate();
								String d3 = d11.format(d33);

								if (d2.compareTo(d1) >= 0 && d2.compareTo(d3) <= 0) {
									entity.setBataWeek(km.getBataWeek());
								}

							}

						} catch (Exception e) {
							e.printStackTrace();
						}

						if (Optional.ofNullable(pm.getDiscountAmtVal()).isPresent()) {
							entity.setDiscountAmt(pm.getDiscountAmt());
							entity.setDiscountAmtVal(pm.getDiscountAmtVal());
						} else {
							entity.setDiscountAmt("0");
							entity.setDiscountAmtVal(0.0);
						}
						if (Optional.ofNullable(pm.getTcsValue()).isPresent()) {
							entity.setTcsPercent(pm.getTcsPercent());
							entity.setTcsValue(pm.getTcsValue());
						} else {
							entity.setTcsPercent("0");
							entity.setTcsValue(0.0);
						}
						if (Optional.ofNullable(pm.getFreight()).isPresent() && !pm.getFreight().isEmpty()) {
							entity.setFreight(pm.getFreight());
						} else {
							entity.setFreight("0");
						}
						if (Optional.ofNullable(pm.getCreditNote()).isPresent()) {
							entity.setCreditNote(pm.getCreditNote());
						} else {
							entity.setCreditNote(0.0);
						}
						entity.setPackCaseB(0);
						entity.setPackCaseS(0);
						entity.setPackCaseC(0);
						entity.setPackCaseM(0);
						entity.setPackCaseT(0);
						Calendar ss = Calendar.getInstance();
						entity.setBataYear(String.valueOf(ss.get(Calendar.YEAR)));
						entity.setBillWeekYear(String.valueOf(ss.get(Calendar.YEAR)));
						entity.setCreditNote(pm.getCreditNote());
						entity.setBillCloseStatus(pm.getBillCloseStatus());
						entity.setPurchaseCost(pm.getPurchaseCost());
						entity.setInvoiceCost(pm.getInvoiceCost());
						if (Optional.ofNullable(rdc).isPresent()) {
							entity.setRdcCode(StringUtils.leftPad(rdc, 5, "0"));
						}
						entity.setTcsApplicable(pm.getTcsApplicable());

						entity.setBillOrderDate(pm.getBillOrderDate());
						entity.setRecptInvDate(pm.getRecptInvDate());
						entity.setBillOrderNo(pm.getBillOrderNo());
						entity.setBillWeekYear(pm.getBillWeekYear());
						entity.setPair(pm.getPair());
						entity.setShopName(pm.getShopName());
						entity.setShopNo(pm.getShopNo());
						entity.setCreatedOn(Calendar.getInstance());

						entity.setPair(pm.getPair());
						entity.setReceiveLoc(pm.getReceiveLoc());
						entity.setPartyCode(pm.getPartyCode());
						entity.setPartyName(pm.getPartyName());
						entity.setRegion(pm.getRegion());
						entity.setStateName(pm.getStateName());
						entity.setStateCode(pm.getStateCode());
						entity.setShopName(pm.getShopName());
						entity.setShopNo(pm.getShopNo());
						entity.setStatus(pm.getStatus());
						// entity.setGstamt(Double.valueOf(pm.getIgst()+pm.getCgst()+pm.getSgst()));
						entity.setIgst(pm.getIgst());
						entity.setCgst(pm.getCgst());
						entity.setSgst(pm.getSgst());

						entity.setIgstamt(pm.getIgstamt());
						entity.setCgstamt(pm.getCgstamt());
						entity.setSgstamt(pm.getSgstamt());
						entity.setTotalCost(pm.getTotalCost());
						entity.setFormtype(pm.getFormtype());
						entity.setInvoiceNO(pm.getInvoiceNO());
						entity.setGrNo(pm.getGrNo());
						entity.setGrDate(pm.getGrDate());
						entity.setCnNo(pm.getCnNo());
						entity.setCnDate(pm.getCnDate());
						entity.setCreatedBy(userName);
						entity.setCreatedOn(Calendar.getInstance());

						vm = bdao.save(entity);

						bm.add(vm);
					} else {
						throw new ResourceAccessException(" Please fill up the BillOrderNo.");
					}

				}
			} else {

				for (RdcDetailsDto lm : pm.getRdcList()) {
					if (Optional.ofNullable(lm.getBillId()).isPresent()) {
						BillPunchDetailsModel entity = bdao.findById(lm.getBillId()).get();

						entity.setBillCloseStatus(pm.getBillCloseStatus());
						entity.setPurchaseCost(pm.getPurchaseCost());
						entity.setInvoiceCost(pm.getInvoiceCost());
						entity.setDiscountAmt(pm.getDiscountAmt());
						entity.setDiscountAmtVal(pm.getDiscountAmtVal());
						entity.setTcsApplicable(pm.getTcsApplicable());
						entity.setTcsPercent(pm.getTcsPercent());
						entity.setTcsValue(pm.getTcsValue());
						entity.setRecptInvDate(pm.getRecptInvDate());
						if (Optional.ofNullable(pm.getFreight()).isPresent()) {
							entity.setFreight(pm.getFreight());
						} else {
							entity.setFreight("0");
						}
						entity.setPair(pm.getPair());
						entity.setStatus(pm.getStatus());
						entity.setTotalCost(pm.getTotalCost());
						entity.setInvoiceNO(pm.getInvoiceNO());
						entity.setGrNo(pm.getGrNo());
						entity.setGrDate(pm.getGrDate());
						entity.setCnNo(pm.getCnNo());
						entity.setCnDate(pm.getCnDate());
						entity.setCreditNote(pm.getCreditNote());
						entity.setCreatedBy(userName);
						entity.setRdcPairC(lm.getRdcPairC());
						entity.setWeekYear(lm.getWeekYear());
						entity.setPairAmount(lm.getPairAmount());
						entity.setReceiveDate(lm.getReceiveDate());
						entity.setNoOfCartons(lm.getNoOfCartons());
						entity.setCreatedOn(Calendar.getInstance());

						entity.setIgst(pm.getIgst());
						entity.setCgst(pm.getCgst());
						entity.setSgst(pm.getSgst());
						try {
							if (Optional.ofNullable(pm.getIgstamt()).isPresent() && pm.getIgstamt() != 0.0) {
								entity.setIgstamt(pm.getIgstamt());

							} else {
								entity.setIgstamt(0.0);
							}

							if (Optional.ofNullable(pm.getCgstamt()).isPresent() && pm.getCgstamt() != 0.0) {
								entity.setCgstamt(pm.getCgstamt());

							} else {
								entity.setCgstamt(0.0);
							}

							if (Optional.ofNullable(pm.getSgstamt()).isPresent() && pm.getSgstamt() != 0.0) {
								entity.setSgstamt(pm.getSgstamt());

							}

							else {
								entity.setSgstamt(0.0);
							}
							entity.setGstamt(pm.getCgstamt() + pm.getSgstamt() + pm.getIgstamt());

						} catch (Exception e) {
							e.printStackTrace();
						}

						try {
							List<WeekMasterModel> wk = wdao.findAll();
							for (WeekMasterModel km : wk) {
								Calendar cal = Calendar.getInstance();
								SimpleDateFormat d11 = new SimpleDateFormat("yyyy-MM-dd");
								String d2 = d11.format(cal.getTime());
								Date d22 = km.getWeekSdate();
								String d1 = d11.format(d22);
								Date d33 = km.getWeekEdate();
								String d3 = d11.format(d33);

								if (d2.compareTo(d1) >= 0 && d2.compareTo(d3) <= 0) {
									entity.setBataWeek(km.getBataWeek());
								}

							}

						} catch (Exception e) {
							e.printStackTrace();
						}
						Calendar ss = Calendar.getInstance();
						entity.setBataYear(String.valueOf(ss.get(Calendar.YEAR)));
						vm = bdao.save(entity);
						bm.add(vm);

					}

				}
			}
		}

		return bm;

	}

	public List<BillPunchDetailsModel> getAll() {
		return bdao.findAll();
	}

	public List<ChartsInterface> getAllCharts(String year1, String year2) {
		List<ChartsInterface> chatslist = null;
		if (Optional.ofNullable(year1).isPresent()) {

			chatslist = bdao.findAllPartycodeAndCountCharts(year1, year2);
		} else {
			chatslist = bdao.findAllPartycodeAndCountCharts();
		}
		return chatslist;
	}

	public List<BillPunchDetailsModel> findAll() {

		return bdao.findAll();
	}

	public List<BillPunchSupplyReportModel> findAllApprovedDetails(String wk, String year, String partycode) {

		return splydao.findWithAllApproved(wk, year, partycode);
	}

	public List<BillPunchResponseInterface> findAllApprovedDetailsForSupplyReport(String wk, String year) {

		return bdao.findWithAllApprovedForSupplyReport(wk, year);
	}

	public BillPunchDetailsModel saveOne(BillPunchDetailsModel m) {

		return bdao.save(m);
	}

	public List<BillPunchResponseInterface> findAllApprovedDetailsForGst(String wk, String yr) {

		return bdao.findWithAllApprovedForGst(wk, yr);
	}

	public List<BillPunchGstReportModel> findAllApprovedDetailsForGstReport(String fromyear, String fromweek,
			String toyear, String toweek, String partycode) {

		return gdao.findWithBillReportByWeekGst(partycode, fromyear, fromweek, toyear, toweek);
	}

	public List<BillPunchDetailsModel> updatePurchaseCostDetails(String invno, String ordno) {

		return bdao.findwithInvNo(invno, ordno);
	}

	@SuppressWarnings("all")
	public BillPunchDetailsModel getById(Long id) {

		return bdao.findById(id).get();
	}

	public ApprovalDetailsModel getApprovalDetails(ApprovalDetailsModel cm) {

		return apdao.save(cm);
	}

	public List<ApprovalDetailsModel> getRdcDetailsStatus(String invno, String ordno) {

		return apdao.findwithRdcDetailsStatus(invno, ordno);

	}

	public BillPunchDetailsModel save(BillPunchDetailsModel vm) {
		return bdao.save(vm);
	}

	public BillPunchDetailsModel saveXl(BillPunchDetailsModel list) {
		return ddao.save(list);
	}

	public List<BillPunchDetailsModel> getByInvOrd(String invoiceNo, String orderNo, String PartyCode) {
		return bdao.findwithAllDetailsByInvOrd(invoiceNo, orderNo, PartyCode);
	}

	public List<BillPunchDetailsModel> getUpdateByInvOrd(String invoiceNo, String orderNo, String PartyCode) {
		return bdao.updateDetailsByInvOrd(invoiceNo, orderNo, PartyCode);
	}

	public List<BillPunchResponseInterface> getRdcDetailsForEdp(String invoiceNo, String grNo, String PartyCode,
			String date) {
		return bdao.getDetailsForEdp(invoiceNo, grNo, PartyCode, date);
	}

	public List<BillPunchDetailsModel> getByInvOrdApproval(String invoiceNo, String orderNo, String PartyCode) {
		return bdao.findwithAllDetailsByInvOrdApproval(invoiceNo, orderNo, PartyCode);
	}

	public List<BillPunchDetailsModel> getByInvparty(String invoiceNo, String PartyCode) {
		return bdao.findwithAllDetailsByInvParty(invoiceNo, PartyCode);
	}

	public List<BillPunchDetailsModel> getByOrdNo(String ordno) {
		return bdao.findwithAllDetailsByOrdNo(ordno);
	}

	@SuppressWarnings("all")
	public List<BillPunchDetailsModel> update(BillPunchDto pm) {
		List<BillPunchDetailsModel> lm = new ArrayList<>();
		BillPunchDetailsModel vm = null;
		if (!pm.getRdcList().isEmpty()) {
			for (RdcDetailsDto xm : pm.getRdcList()) {
				BillPunchDetailsModel entity = bdao.findById(xm.getBillId()).get();
				try {
					List<WeekMasterModel> wk = wdao.findAll();
					for (WeekMasterModel lmm : wk) {
						Calendar cal = Calendar.getInstance();
						SimpleDateFormat d11 = new SimpleDateFormat("yyyy-MM-dd");
						String d2 = d11.format(cal.getTime());
						Date d22 = lmm.getWeekSdate();
						String d1 = d11.format(d22);
						Date d33 = lmm.getWeekEdate();
						String d3 = d11.format(d33);

						if (d2.compareTo(d1) >= 0 && d2.compareTo(d3) <= 0) {
							entity.setBataWeek(lmm.getBataWeek());
						}

					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				Calendar ss = Calendar.getInstance();
				entity.setBataYear(String.valueOf(ss.get(Calendar.YEAR)));
				entity.setCreditNote(pm.getCreditNote());
				entity.setRdcPairC(xm.getRdcPairC());
				entity.setBillCloseStatus(pm.getBillCloseStatus());
				entity.setFormtype(pm.getFormtype());
				entity.setPurchaseCost(pm.getPurchaseCost());
				entity.setInvoiceCost(pm.getInvoiceCost());
				entity.setDiscountAmt(pm.getDiscountAmt());
				entity.setDiscountAmtVal(pm.getDiscountAmtVal());
				entity.setTcsApplicable(pm.getTcsApplicable());
				entity.setTcsValue(pm.getTcsValue());
				entity.setBillOrderDate(pm.getBillOrderDate());
				entity.setBillOrderNo(pm.getBillOrderNo());
				entity.setBillWeekYear(pm.getBillWeekYear());
				entity.setCreatedOn(Calendar.getInstance());
				entity.setFreight(pm.getFreight());
				entity.setPartyCode(pm.getPartyCode());
				entity.setPartyName(pm.getPartyName());
				entity.setPair(pm.getPair());
				entity.setShopName(pm.getShopName());
				entity.setShopNo(pm.getShopNo());
				entity.setRegion(pm.getRegion());
				entity.setStateName(pm.getStateName());
				entity.setStateCode(pm.getStateCode());
				entity.setShopName(pm.getShopName());
				entity.setShopNo(pm.getShopNo());
				entity.setStatus(pm.getStatus());
				entity.setIgst(pm.getIgst());
				entity.setCgst(pm.getCgst());
				entity.setSgst(pm.getSgst());
				entity.setIgstamt(pm.getIgstamt());
				entity.setCgstamt(pm.getCgstamt());
				entity.setSgstamt(pm.getSgstamt());
				entity.setTotalCost(pm.getTotalCost());
				entity.setGrDate(pm.getGrDate());
				entity.setCnDate(pm.getGrnDate());
				entity.setGrNo(pm.getGrNo());
				entity.setInvoiceNO(pm.getInvoiceNO());
				entity.setWeekYear(xm.getWeekYear());
				entity.setArticleCode(xm.getArticleCode());
				entity.setPairAmount(xm.getPairAmount());
				entity.setRdcPair(xm.getRdcPair());
				entity.setArticleName(xm.getArticleName());
				entity.setReceiveDate(xm.getReceiveDate());
				entity.setNoOfCartons(xm.getNoOfCartons());

				vm = bdao.save(entity);
				lm.add(vm);

			}
		}
		return lm;

	}

	public List<StrazaBillPunchDto> getAllRdcDetails() {
		return bdao.findWithAll();
	}

	public List<PartyResponseDto> getAllPartycodeAndPartyName(String constant) {
		List<PartyResponseDto> cm = null;
		if (constant.equalsIgnoreCase("EDIT")) {
			cm = bdao.findAllPartycodeAndPartyName();
		} else if (constant.equalsIgnoreCase("STRAZA")) {
			cm = bdao.findAllPartycodeAndPartyNameForStraza();
		}

		return cm;

	}

	public List<ReceivingLocDto> getAllReceivingLoc() {

		return bdao.findAllReceivingLoc();

	}

	public List<ReceivingLocDto> getAllReceivingLocManual() {

		return sdao.findAllReceivingLocManual();

	}

	public List<BillPunchDetailsModel> getAllPartycodeAndPartyNameDetails(String partycode, String partyname) {

		if (partycode == null) {
			partycode = "%";
		}
		if (partyname == null) {
			partyname = "%";
		}

		return bdao.findAllPartycodeAndPartyNameDetails(partycode, partyname);
	}

	public List<BillCloseStatusDto> getApprovedRecords(String billcloseweek) {
		return bdao.findWithApprovedRecords(billcloseweek);
	}

	public List<BillPunchDetailsModel> getAllDetailsByStatus(String invoiceNO, String partycode, String orederno,
			String status, String grfromdate, String grtodate, List<String> week, String year, String loc) {

		return bdao.finfWithStatus(invoiceNO, partycode, orederno, status, grfromdate, grtodate, week, year, loc);
	}

	public List<ApprovalDetailsModel> getApprovalDetailsForValidate(String billUniqueCode, String status) {
		return apdao.findwithApprovalDetailsForValidate(billUniqueCode, status);
	}

	@SuppressWarnings("all")
	public List<BillPunchDetailsModel> updateRecords(List<BillPunchDto> entity) {
		List<BillPunchDetailsModel> lm = new ArrayList<>();

		BillPunchDetailsModel vm = null;
		String week = null;
		for (BillPunchDto xm : entity) {
			List<BillPunchDetailsModel> kmm = bdao.findwithInvNo(xm.getInvoiceNO(), xm.getBillOrderNo());
			try {
				List<WeekMasterModel> wk = wdao.findAll();

				for (WeekMasterModel km : wk) {
					Calendar cal = Calendar.getInstance();
					SimpleDateFormat d11 = new SimpleDateFormat("yyyy-MM-dd");
					String d2 = d11.format(cal.getTime());
					Date d22 = km.getWeekSdate();
					String d1 = d11.format(d22);
					Date d33 = km.getWeekEdate();
					String d3 = d11.format(d33);

					if (d2.compareTo(d1) >= 0 && d2.compareTo(d3) <= 0) {
						week = km.getBataWeek();
					}

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			Calendar ss = Calendar.getInstance();
			String yr = String.valueOf(ss.get(Calendar.YEAR));

			for (BillPunchDetailsModel pm : kmm) {
				pm.setBataWeek(week);
				pm.setBataYear(yr);
				pm.setCreatedOn(Calendar.getInstance());
				pm.setStatus("CLOSED");
				vm = bdao.save(pm);
				lm.add(vm);

			}

		}
		return lm;

	}

	public List<BillCloseStatusDto> getBillCloseWeek() {
		return bdao.findWithBillCloseWeek();
	}

	public OrderPair getAllAtrnoAndOrdnoDetails(String atrno, String ordno, String invno) {

		return odao.findWithPairByOrderNoAndArtno(atrno, ordno, StringUtils.leftPad(invno.substring(2, 6), 5, "0"));
	}

	public List<BillPunchDetailsModel> getAllAtrnoAndOrdnoDetailsForGst(String atrno, String ordno, String invno) {

		return bdao.findWithPairByOrderNoAndArtno(atrno, ordno, invno);
	}

	public List<BillPunchDetailsModel> getAllAtrnoAndOrdnoDetailsForSupply(String party, String ordno, String invno) {

		return bdao.findWithPairByOrderNoAndArtnoSupply(party, ordno, invno);
	}

	public BillPunchResponseInterface getAllAtrnoAndOrdnoDetailsForGstPair(String atrno, String ordno, String invno) {

		return bdao.findWithPairByOrderNoAndArtnoForGst(atrno, ordno, invno);
	}

	public BillPunchResponseInterface getAllAtrnoAndOrdnoDetailsForSupply(String ordno, String invno) {

		return bdao.findWithPairByOrderNoAndArtnoForSupply(ordno, invno);
	}

	public BillPunchResponseInterface getAllAtrnoAndOrdnoDetailsForSupplyReport(String ordno, String invno,
			String party) {

		return bdao.findWithPairByOrderNoAndArtnoForSupplyReport(ordno, invno, party);
	}

	public List<BillPunchResponseInterface> getvalidatepartyCodeAndParty(String invno, String ordno) {

		return bdao.findWithPartycodeAndParty(invno, ordno);
	}

	public List<OrdersMasterModel> getDetailsAtrnoAndOrdnoDetails(String atrno, String ordno, String partycode,
			String rdcno) {

		return odao.findWithByOrderNoAndArtno(atrno, ordno, partycode, rdcno);
	}

	public PriceInterface getDetailsOrdnoDetails(String ordno, String partycode, String rdcno, String artno) {

		return odao.findWithByOrderNoPartyCodeRdcCode(ordno, partycode, rdcno, artno);
	}

	public PriceInterface getPriceDetailsByartno(String atrno, String orderno) {
		return odao.findWithPriceByArtno(atrno, orderno);
	}

	public List<StrazaBillPunchDto> getDetailsStrazaReport(List<String> partycode, String fromwk, String towk,
			String yr, String constant) {

		List<StrazaBillPunchDto> cm = null;

		if (constant.equalsIgnoreCase("EDIT")) {
			if (fromwk == null && towk == null) {
				cm = bdao.findWithDetailsEditReportforAllWK(partycode, yr);
			}

			else {
				cm = bdao.findWithDetailsEditReport(partycode, StringUtils.leftPad(fromwk, 2, "0"),
						StringUtils.leftPad(towk, 2, "0"), yr);
			}
		} else if (constant.equalsIgnoreCase("STRAZA")) {
			if (fromwk == null && towk == null) {
				cm = bdao.findWithDetailsStrazaReportforAllWK(partycode, yr);
			}

			else {
				cm = bdao.findWithDetailsStrazaReport(partycode, StringUtils.leftPad(fromwk, 2, "0"),
						StringUtils.leftPad(towk, 2, "0"), yr);
			}
		}

		return cm;

	}

	public List<BillPunchDetailsImportDataModel> getallRdcData() {
		return rdcdao.findWithAllDetails();
	}

	public List<BillPunchDetailsModel> getallRdcRecords(String invno, String ordno) {
		List<BillPunchDetailsModel> lm = new ArrayList<>();

		BillPunchDetailsModel vm = null;
		List<BillPunchDetailsModel> xm = bdao.findWithInvoiceAndOrdNo(invno, ordno);
		for (BillPunchDetailsModel pm : xm) {
			pm.setStatus("INVALID_RECORDS");
			vm = bdao.save(pm);
			lm.add(vm);

		}
		return lm;
	}

	public List<BillPunchDetailsModel> findAllApprovedDetailsForSupplyReportFilter(String partycode, String invno) {

		return bdao.findWithAllApprovedForSupplyReportFilter(partycode, invno);
	}

	public List<BillPunchDetailsModel> findAllApprovedDetailsForSupplyReportFilternew(String partycode, String invno,
			String orderNo, String status) {

		if ("null".equalsIgnoreCase(orderNo)) {
			orderNo = "%";
		}

		if ("null".equalsIgnoreCase(status)) {
			status = "%";
		}

		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>" + status + ">>>>>>>>>>>>>>>>" + orderNo);
		return bdao.findWithAllApprovedForSupplyReportFilternewOne(partycode, invno, orderNo, status);
	}

	public List<BillPunchDetailsModel> findAllApprovedDetailsForSupplyReportFilternewTWO(String partycode, String invno,
			String orderNo) {

		if ("null".equalsIgnoreCase(orderNo)) {
			orderNo = "%";
		}

		System.out.println(">>>>>>>>>>>>>>>>>>>> + " + orderNo);
		return bdao.findWithAllApprovedForSupplyReportFilternewTWO(partycode, invno, orderNo);
	}

	public List<BillPunchDetailsModel> getDetailsByFilterHistoryone(String invoiceNO, String partyCode,
			String billOrderNo, String status) {

		return bdao.findWithBillNoHistoryOne(invoiceNO, partyCode, billOrderNo, status);

	}

	public List<BillPunchDetailsModel> findAllApprovedDetailsForSupplyReportFilternewmanual(String partycode,
			String invno, String orderNo) {

		if ("null".equalsIgnoreCase(orderNo)) {
			orderNo = "%";
		}

		return bdao.findWithAllApprovedForSupplyReportFilternewmanual(partycode, invno, orderNo);
	}

	public List<BillPunchDetailsModel> findAllApprovedDetailsForSupplyReportFilternewold(String partycode, String invno,
			String orderNo, String status, String yr, String wk, String grNo) {
		System.out.println("status>>>>>>>>>>>>>>>>>>>>" + status + "ZZZZZZZZZZZZZZ" + orderNo + "CCCCCCCCCCCCCCC"
				+ partycode + "VVVVVVVVVVVV" + invno);
		if ("null".equalsIgnoreCase(orderNo)) {
			orderNo = "%";
		}

		if ("null".equalsIgnoreCase(status)) {
			status = "%";
		}

		if ("null".equalsIgnoreCase(yr)) {
			yr = "%";
		}
		if ("null".equalsIgnoreCase(wk)) {
			wk = "%";
		}
		if ("null".equalsIgnoreCase(grNo)) {
			grNo = "%";
		}
		if ("null".equalsIgnoreCase(invno)) {
			invno = "%";
		}

		if ("null".equalsIgnoreCase(partycode)) {
			partycode = "%";
		}

		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>" + status + ">>>>>>>>>>>>>>>>" + orderNo + ">>>>>>>>>>>>>>" + wk
				+ ">>>>>>>>>>>>>>>>" + yr);
		return bdao.findWithAllApprovedForSupplyReportFilternew(partycode, invno, orderNo, status, wk, yr, grNo);
	}

}