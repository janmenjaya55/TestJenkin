
package com.bata.billpunch.controller;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bata.billpunch.dao.ArticlesMasterOldDao;
import com.bata.billpunch.dao.EdpReportDao;
import com.bata.billpunch.dao.GstReportDao;
import com.bata.billpunch.dao.RdcMappingDao;
import com.bata.billpunch.dao.StrazaReportDao;
import com.bata.billpunch.dao.SupplyReportDao;
import com.bata.billpunch.dao.TransReportDao;
import com.bata.billpunch.model.ArticlesMasterModel;
import com.bata.billpunch.model.ArticlesMasterOldModel;
import com.bata.billpunch.model.BillPunchDetailsModel;
import com.bata.billpunch.model.BillPunchEdpReportModel;
import com.bata.billpunch.model.BillPunchGstReportModel;
import com.bata.billpunch.model.BillPunchStrazaReportModel;
import com.bata.billpunch.model.BillPunchSupplyReportModel;
import com.bata.billpunch.model.BillPunchTransReportModel;
import com.bata.billpunch.model.PartiesMasterModel;
import com.bata.billpunch.model.RdcMappingModel;
import com.bata.billpunch.model.ShopMasterModel;
import com.bata.billpunch.model.StateMasterModel;
import com.bata.billpunch.model.dto.BillPunchResponseInterface;
import com.bata.billpunch.model.dto.PriceInterface;
import com.bata.billpunch.model.dto.StrazaBillPunchDto;
import com.bata.billpunch.service.impl.ArticleMasterOldServiceImpl;
import com.bata.billpunch.service.impl.ArticleMasterServiceImpl;
import com.bata.billpunch.service.impl.BillPunchMasterServicesImpl;
import com.bata.billpunch.service.impl.BillPunchServicesImpl;
import com.bata.billpunch.service.impl.PartyMasterServiceImpl;
import com.bata.billpunch.service.impl.ShopMasterServiceImpl;
import com.bata.billpunch.service.impl.StateMasterServiceImpl;
import com.bata.billpunch.service.impl.WeekMasterServiceImpl;

@RestController

@RequestMapping("/bill-punch")

@CrossOrigin(origins = "*")
public class BillReportImportRestController {

	@Autowired
	private BillPunchServicesImpl services;

	@Autowired
	private BillPunchMasterServicesImpl mservices;

	@Autowired
	private WeekMasterServiceImpl wservice;

	@Autowired
	private RdcMappingDao rdcmapdao;

	@Autowired
	private SupplyReportDao splydao;

	@Autowired
	private TransReportDao transdao;

	@Autowired
	private StrazaReportDao stdao;

	@Autowired
	private ArticlesMasterOldDao atdao;

	@Autowired
	private EdpReportDao edao;

	@Autowired
	private GstReportDao gdao;

	@Autowired
	private ArticleMasterServiceImpl artservices;

	@Autowired
	private ArticleMasterServiceImpl aservices;

	@Autowired
	private ArticleMasterOldServiceImpl arOldservices;

	@Autowired
	private ShopMasterServiceImpl sservices;

	@Autowired
	private PartyMasterServiceImpl pservices;

	@Autowired
	private StateMasterServiceImpl stservices;

	/********************************************************************************************
	 * SUPPLY REPORT
	 ********************************************************************************************/
	// @Scheduled(cron = "0 32 16 * * ?")
	private void writeDataLinesSupplyReportImport() throws SQLException {

		System.out.println("###################Schedular start for supply report###################################");

		List<BillPunchResponseInterface> result = services.findAllApprovedDetailsForSupplyReport("09", "2022");

		List<BillPunchSupplyReportModel> list = new ArrayList<BillPunchSupplyReportModel>();
		Double amt = 0d;

		Double artamt = 0d;
		Double mrpamt = 0d;
		for (BillPunchResponseInterface sb : result) {

			String s = "0";

			String s1 = "0";
			BillPunchDetailsModel xm = services
					.getAllAtrnoAndOrdnoDetailsForSupply(sb.getparty(), sb.getbillOrderNo(), sb.getinvoiceNO()).get(0);

			BillPunchResponseInterface fx = services.getAllAtrnoAndOrdnoDetailsForSupplyReport(sb.getbillOrderNo(),
					sb.getinvoiceNO(), sb.getparty());

			try {

				s1 = pservices.getPartiesDetails(StringUtils.leftPad(xm.getParty(), 4, "0")).getPartystatecode();
			} catch (Exception e) {
			}
			StateMasterModel ms = stservices.getStateDetails(StringUtils.leftPad(String.valueOf(s1), 2, "0"));

			try {

				if (Optional.ofNullable(sservices.getShopDetails(xm.getRdcCode()).getStatecode()).isPresent()) {
					s = sservices.getShopDetails(xm.getRdcCode()).getStatecode();
				} else {
					s = sservices.getShopDetails(rdcmapdao.findByMergerdccode(xm.getRdcCode()).get(0).getRdcno())
							.getStatecode();

				}
			} catch (Exception e) {
			}

			StateMasterModel msc = stservices.getStateDetails(StringUtils.leftPad(String.valueOf(s), 2, "0"));
			StateMasterModel st = null;
			PriceInterface ch = null;
			amt = 0d;

			artamt = 0d;
			mrpamt = 0d;
			PartiesMasterModel pt = null;
			ArticlesMasterModel art = null;
			ShopMasterModel sh = null;

			try {

				pt = pservices.getPartiesDetails(StringUtils.leftPad(xm.getParty(), 4, "0"));
				if (Optional.ofNullable(pt).isPresent()) {

				} else {
					pt = new PartiesMasterModel();
					pt.setPartygstinno("0");
				}

			} catch (Exception e) {

			}

			try {
				sh = sservices.getShopDetails(xm.getRdcCode());
				if (Optional.ofNullable(sh).isPresent()) {

				} else {
					sh = sservices.getShopDetails(rdcmapdao.findByMergerdccode(xm.getRdcCode()).get(0).getRdcno());

				}
			} catch (Exception e) {
			}
			try {
				// stt = stservices.getStateDetails(StringUtils.leftPad(sh.getStatecode(), 2,
				// "0"));
				st = stservices.getStateDetails(sh.getStatecode());
				if (Optional.ofNullable(st).isPresent()) {

				} else {
					st = new StateMasterModel();
					st.setGstIn("");
				}

			} catch (Exception e) {
			}

			try {
				List<BillPunchResponseInterface> sm = mservices.getDetailsByFilterRdc(xm.getInvoiceNO(),
						xm.getBillOrderNo());

				for (BillPunchResponseInterface pr : sm) {

					ch = services.getDetailsOrdnoDetails(xm.getBillOrderNo(), xm.getPartyCode(), xm.getRdcCode(),
							pr.getarticleCode());

					if (Optional.ofNullable(ch).isPresent()) {
						Double mt = Double.parseDouble(ch.getmrp()) * Integer.parseInt(pr.getpair());
						amt += mt;

					} else {

						List<RdcMappingModel> xn = rdcmapdao.findByMergerdccode(xm.getRdcCode());

						for (RdcMappingModel bm : xn) {
							ch = services.getDetailsOrdnoDetails(xm.getBillOrderNo(), xm.getPartyCode(), bm.getRdcno(),
									pr.getarticleCode());

							if (Optional.ofNullable(ch).isPresent()) {
								Double mt = Double.parseDouble(ch.getmrp()) * Integer.parseInt(pr.getpair());
								amt += mt;
							}

						}

					}

				}
			} catch (Exception e) {
			}
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			String date1 = "NA";
			try {
				if (Optional.ofNullable(xm.getRecptInvDate()).isPresent()) {
					Date d1 = xm.getRecptInvDate();
					date1 = dateFormat.format(d1);
				}

			} catch (Exception e) {
			}

			try {
				if (Optional.ofNullable(xm.getRecptInvDate()).isPresent()
						&& date1.substring(6, 10).equalsIgnoreCase("2021")) {
					BillPunchResponseInterface artt = arOldservices.getArticleDetailsReportstraza(xm.getInvoiceNO(),
							xm.getPartyCode(), xm.getBillOrderNo());
					if (Optional.ofNullable(artt).isPresent()) {

						Double mx = Double.parseDouble(artt.getartpricewspedp());
						artamt += mx;
						Double mxx = Double.parseDouble(artt.getartpricemrpedp());
						mrpamt += mxx;

					} else {
						artamt = 0d;
						mrpamt = 0d;
					}

				} else {
					BillPunchResponseInterface artt = aservices.getArticleDetailsReportstraza(xm.getInvoiceNO(),
							xm.getPartyCode(), xm.getBillOrderNo());
					if (Optional.ofNullable(artt).isPresent()) {

						Double mx = Double.parseDouble(artt.getartpricewspedp());
						artamt += mx;
						Double mxx = Double.parseDouble(artt.getartpricemrpedp());
						mrpamt += mxx;

					} else {
						artamt = 0d;
						mrpamt = 0d;
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			String date23 = "NA";
			try {
				if (Optional.ofNullable(xm.getGrDate()).isPresent()) {
					Date d11 = xm.getGrDate();
					date23 = dateFormat.format(d11);
				}

			} catch (Exception e) {
			}

			Date d2 = xm.getCnDate();
			String date2 = dateFormat.format(d2);
			Date d3 = xm.getBillOrderDate();
			String date3 = dateFormat.format(d3);
			if (!Optional.ofNullable(xm.getFreight()).isPresent()) {
				xm.setFreight("0");
			}

			if (!Optional.ofNullable(xm.getDiscountAmtVal()).isPresent()) {
				xm.setDiscountAmtVal(0.0d);
			}
			if (!Optional.ofNullable(xm.getTcsValue()).isPresent()) {
				xm.setTcsValue(0.0d);
			}

			if (Optional.ofNullable(msc.getStateName()).isPresent()) {
				if (msc.getStateName().equalsIgnoreCase("WEST BENGAL")) {
					xm.setRegion("EAST");

				} else if (msc.getStateName().equalsIgnoreCase("HARYANA")) {
					xm.setRegion("NORTH");
				}

				else if (msc.getStateName().equalsIgnoreCase("MAHARASHTRA")) {
					xm.setRegion("WEST");
				}

				else if (msc.getStateName().equalsIgnoreCase("KARNATAKA")
						|| msc.getStateName().equalsIgnoreCase("TAMIL NADU")) {
					xm.setRegion("SOUTH");
				}
			} else {
				xm.setRegion("NA");
			}

			BillPunchSupplyReportModel md = new BillPunchSupplyReportModel();
			md.setFromStateCode(s1);
			md.setFromStateName(ms.getStateName());
			md.setPartyCode(xm.getPartyCode());
			md.setPartyName(xm.getPartyName());
			md.setToStateCode(s);
			md.setToStateName(msc.getStateName());
			// md.setTolocationCode(xm.getShopNo());
			md.setTolocationCode(xm.getRdcCode());
			md.setRecLocName(xm.getReceiveLoc());
			md.setDept(xm.getShopName());
			md.setRegion(xm.getRegion());
			md.setInvoiceNo(xm.getInvoiceNO());
			md.setInvoiceDate(date1);
			md.setGrnNo(xm.getGrNo());
			md.setGrnDate(date23);
			md.setBooking(xm.getBataWeek() + "/" + xm.getBataYear());
			md.setPairs(fx.getpair());
			md.setTaxableValue(String.format("%.2f", xm.getInvoiceCost()));
			md.setCgst(String.format("%.2f", Double.valueOf(sb.getcgstamt())));
			md.setSgst(String.format("%.2f", Double.valueOf(sb.getsgstamt())));
			md.setIgst(String.format("%.2f", Double.valueOf(sb.getigstamt())));
			md.setFreight(String.format("%.2f", Double.valueOf(xm.getFreight())));
			md.setTcs(String.format("%.2f", xm.getTcsValue()));
			md.setNetAmount(String.format("%.2f", xm.getTotalCost()));
			md.setGrNo(xm.getCnNo());
			md.setGrDate(date2);
			md.setPoNo(xm.getBillOrderNo());
			md.setPoDate(date3);
			md.setVenGstNo(pt.getPartygstinno());
			md.setMrp(String.format("%.2f", mrpamt));
			md.setWsp(String.format("%.2f", artamt));
			md.setReceiverGstNo(st.getGstIn());
			md.setNoclaim(String.format("%.2f", xm.getDiscountAmtVal()));
			md.setWeek("09");
			md.setYear("2022");
			list.add(md);

		}
		splydao.saveAll(list);
		System.out.println("###################Schedular end for supply report###################################");
	}

	/********************************************************************************************
	 * TRANS REPORT
	 ********************************************************************************************/
	// @Scheduled(cron = "0 44 10 * * ?")
	private void writeDataLinesTransReportImport() throws SQLException {

		System.out.println("###################Schedular start for trans report###################################");

		List<BillPunchResponseInterface> result = mservices.getReportDetailsByWeekTrans("14", "2022");

		List<BillPunchTransReportModel> list = new ArrayList<BillPunchTransReportModel>();
		String amt = null;
		for (BillPunchResponseInterface vm : result) {
			BillPunchResponseInterface pr = mservices.getReportTotalPairDetailsTrans(vm.getinvoiceNO(),
					vm.getpartyCode(), vm.getrdcCode(), "14");

			amt = null;
			if (Optional.ofNullable(vm.getinvamtreport()).isPresent()) {
				amt = vm.getinvamtreport();

			} else {
				amt = vm.getpurchaseCostreport();
			}

			String date = "00-00-0000";
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			try {
				if (Optional.ofNullable(vm.getresumeInvDateone()).isPresent()) {
					Date d1 = vm.getresumeInvDateone();
					date = dateFormat.format(d1);
				}
			} catch (Exception e) {
			}
			String m11 = null;
			String m23 = null;
			String m33 = null;
			String m1 = vm.getcgstamt();

			String m2 = vm.getsgstamt();

			String m3 = vm.getigstamt();
			if (Optional.ofNullable(vm.getcgst()).isPresent()) {
				m11 = vm.getcgst();

				m23 = vm.getsgst();
			} else {
				m11 = "0";

				m23 = "0";
			}
			if (Optional.ofNullable(vm.getcgst()).isPresent()) {
				m33 = vm.getigst();
			} else {
				m33 = "0";
			}

			DateFormat dateFormats = new SimpleDateFormat("ddMMyyyy");
			String cndate = dateFormats.format(vm.getcnDateone());
			String recdate = null;
			BillPunchResponseInterface art = null;
			try {
				recdate = dateFormats.format(vm.getrecptInvDateone());
			} catch (Exception e) {
			}

			DateFormat dateFormatt = new SimpleDateFormat("dd-MM-yyyy");

			String date11 = "00-00-0000";
			try {
				if (Optional.ofNullable(vm.getrecptInvDateone()).isPresent()) {
					Date d2 = vm.getrecptInvDateone();
					date11 = dateFormatt.format(d2);
				}
			} catch (Exception e) {
			}
			try {
				if (Optional.ofNullable(vm.getrecptInvDateone()).isPresent()
						&& date11.substring(6, 10).equalsIgnoreCase("2021")) {
					System.out.println(
							"###################Schedular enter in old article mastaer for trans report###################################");
					art = arOldservices.getArticleDetailsReport(String.valueOf(vm.getarticleCode()));
				} else {
					art = aservices.getArticleDetailsReport(String.valueOf(vm.getarticleCode()));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			ShopMasterModel sh = sservices.getShopDetails(vm.getrdcCode());
			String st = vm.gettrnsportCode();
			if (st == null) {
				st = "0";
			}

			BillPunchTransReportModel md = new BillPunchTransReportModel();
			md.setInvoiceNo(vm.getinvoiceNO());
			md.setArticleCode(vm.getarticleCode());
			if (Optional.ofNullable(vm.getsizeCode()).isPresent()) {
				md.setSizeCode(String.valueOf(vm.getsizeCode()));
			} else {
				md.setSizeCode("0");
			}

			md.setRdcCode(vm.getrdcCode());
			md.setDistNo(String.valueOf(sh.getDistrictno()));
			md.setPair(String.valueOf(vm.getpair()));
			md.setTotalPair(String.valueOf(pr.gettotalpair()));
			if (Optional.ofNullable(vm.getpackCaseB()).isPresent()) {
				md.setPackCaseb(String.valueOf(vm.getpackCaseB()));
			} else {
				md.setPackCaseb("0");
			}
			if (Optional.ofNullable(vm.getpackCaseM()).isPresent()) {
				md.setPackCasem(String.valueOf(vm.getpackCaseM()));
			} else {
				md.setPackCasem("0");
			}
			if (Optional.ofNullable(vm.getpackCaseS()).isPresent()) {
				md.setPackCases(String.valueOf(vm.getpackCaseS()));
			} else {
				md.setPackCases("0");
			}
			if (Optional.ofNullable(vm.getpackCaseC()).isPresent()) {
				md.setPackCasec(String.valueOf(vm.getpackCaseC()));
			} else {
				md.setPackCasec("0");
			}
			if (Optional.ofNullable(vm.getpackCaseT()).isPresent()) {
				md.setPackCaset(String.valueOf(vm.getpackCaseT()));
			} else {
				md.setPackCaset("0");
			}
			md.setDcCode(vm.getdcCode());
			md.setArtSequence(art.getartseq());
			md.setPartyCode(vm.getpartyCode());
			md.setCnNo(vm.getcnNo());
			md.setCnDate(cndate);
			if (Optional.ofNullable(st).isPresent()) {
				md.setTransportCode(String.valueOf(st));
			} else {
				md.setTransportCode("0");
			}

			if (Optional.ofNullable(vm.getrdpermitNo()).isPresent()) {
				md.setPermitNo(vm.getrdpermitNo());
			} else {
				md.setPermitNo("0");
			}

			if (Optional.ofNullable(vm.getresumeInvNoTwo()).isPresent()) {
				md.setResumeInvNOTwo(vm.getresumeInvNoTwo());
			} else {
				md.setResumeInvNOTwo("0");
			}

			if (Optional.ofNullable(vm.getresumeInvNo()).isPresent()) {
				md.setResumeInvNO(vm.getresumeInvNo());
			} else {
				md.setResumeInvNO("0");
			}

			md.setStateCode(sh.getStatecode());
			md.setInvAmt(amt);
			md.setGrNo(vm.getgrNo());
			md.setRecDate(recdate);

			md.setDateOne(date.substring(0, 2));
			md.setDateTwo(date.substring(3, 5));
			md.setDateThree(date.substring(6, 10));

			md.setBillOrdNo(vm.getbillOrderNo());
			md.setBillOrdNoOne(vm.getbillOrderNo());
			md.setArtCost(
					String.valueOf(Integer.valueOf((int) (art.getartstdCost() * 100 * Integer.valueOf(vm.getpair())))));
			md.setCgst(m11);
			md.setCgstamt(m1);
			md.setSgst(m23);
			md.setSgstamt(m2);
			md.setIgst(m33);
			md.setIgstamt(m3);
			md.setWeek("14");
			md.setYear("2022");
			list.add(md);
		}
		transdao.saveAll(list);
		System.out.println("###################Schedular end for trans report###################################");
	}

	/********************************************************************************************
	 * EDP REPORT
	 ********************************************************************************************/
	// @Scheduled(cron = "0 5 18 * * ?")
	private void writeDataLinesEdpReportImport() throws SQLException {

		System.out.println("###################Schedular start for edp report###################################");

		List<BillPunchResponseInterface> result = mservices.getReportDetailsByWeekEdp("15", "2022");

		List<BillPunchEdpReportModel> list = new ArrayList<BillPunchEdpReportModel>();

		String amtnew = null;
		String amt = null;
		BillPunchResponseInterface art = null;

		for (BillPunchResponseInterface vm : result) {

			amtnew = null;
			amt = null;
			if (Optional.ofNullable(vm.getpurchaseCostreport()).isPresent()) {
				amtnew = vm.getpurchaseCostreport();

			} else {
				amtnew = vm.getinvamtreport();
			}

			if (Optional.ofNullable(vm.getinvamtreport()).isPresent()) {
				amt = vm.getinvamtreport();

			} else {
				amt = vm.getpurchaseCostreport();
			}

			Date d1 = vm.getcnDateone();
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			String date1 = dateFormat.format(d1);
			String date111 = "00-00-0000";
			String date11 = "00-00-0000";
			try {
				if (Optional.ofNullable(vm.getrecptInvDateone()).isPresent()) {
					Date d2 = vm.getrecptInvDateone();
					date11 = dateFormat.format(d2);
				}
			} catch (Exception e) {
			}
			try {
				if (Optional.ofNullable(vm.getrecptInvDateone()).isPresent()
						&& date11.substring(6, 10).equalsIgnoreCase("2021")) {
					art = arOldservices.getArticleDetailsedpReport(vm.getinvoiceNO(), vm.getpartyCode(), vm.getgrNo());
				} else {
					art = aservices.getArticleDetailsedpReport(vm.getinvoiceNO(), vm.getpartyCode(), vm.getgrNo());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				if (Optional.ofNullable(vm.getresumeInvDateone()).isPresent()) {
					Date d3 = vm.getresumeInvDateone();
					date111 = dateFormat.format(d3);
				}
			} catch (Exception e) {
			}

			String m1 = vm.getcgstamt();

			String m2 = vm.getsgstamt();

			String m3 = vm.getigstamt();
			ShopMasterModel sh = sservices.getShopDetails(vm.getrdcCode());

			String st = vm.gettrnsportCode();
			if (st == null) {
				st = "0";
			}

			BillPunchEdpReportModel md = new BillPunchEdpReportModel();
			md.setRdcCode(vm.getrdcCode());
			md.setInvoiceNo(vm.getinvoiceNO());
			md.setRdcCode(vm.getrdcCode());
			md.setDistNo(String.valueOf(sh.getDistrictno()));
			md.setPair(String.valueOf(vm.getpair()));
			if (Optional.ofNullable(vm.getpackCaseT()).isPresent()) {
				md.setPackCaset(String.valueOf(vm.getpackCaseT()));
			} else {
				md.setPackCaset("0");
			}
			md.setAmtone(art.getartpricemrpedp());
			md.setAmttwo(art.getartpricefactoryedp());
			md.setAmtwsp(art.getartpricewspedp());
			md.setAmtnew(amtnew);
			md.setPartyCode(vm.getpartyCode());
			md.setCnNo(vm.getcnNo());
			md.setDateOne(date1.substring(0, 2));
			md.setDateTwo(date1.substring(3, 5));
			md.setDateThree(date1.substring(6, 10));

			if (Optional.ofNullable(st).isPresent()) {
				md.setTransportCode(String.valueOf(st));
			} else {

				md.setTransportCode("0");
			}
			if (Optional.ofNullable(vm.getrdpermitNo()).isPresent()) {
				md.setPermitNo(vm.getrdpermitNo());
			} else {

				md.setPermitNo("0");
			}
			if (Optional.ofNullable(vm.getresumeInvNoTwo()).isPresent()) {
				md.setResumeInvNOTwo(vm.getresumeInvNoTwo());
			} else {
				md.setResumeInvNOTwo("0");
			}

			if (Optional.ofNullable(vm.getresumeInvNo()).isPresent()) {
				md.setResumeInvNO(vm.getresumeInvNo());
			} else {
				md.setResumeInvNO("0");
			}
			md.setStateCode(sh.getStatecode());
			md.setInvAmt(amt);
			md.setGrNo(vm.getgrNo());
			md.setDateTwoOne(date11.substring(0, 2));
			md.setDateTwoTwo(date11.substring(3, 5));
			md.setDateTwoThree(date11.substring(6, 10));
			md.setDateThreeOne(date11.substring(0, 2));
			md.setDateThreeTwo(date11.substring(3, 5));
			md.setDateThreeThree(date11.substring(6, 10));

			md.setDateFourOne(date111.substring(0, 2));
			md.setDateFourTwo(date111.substring(3, 5));
			md.setDateFourThree(date111.substring(6, 10));
			md.setAmtThree(art.getartstdCostedp());
			md.setCgstamt(m1);
			md.setSgstamt(m2);
			md.setIgstamt(m3);
			md.setWeek("15");
			md.setYear("2022");
			md.setBillOrdNo(vm.getbillOrderNo());
			list.add(md);

		}
		edao.saveAll(list);
		System.out.println("###################Schedular end for epd report###################################");
	}

	/********************************************************************************************
	 * GST REPORT
	 ********************************************************************************************/
	// @Scheduled(cron = "0 15 12 * * ?")
	private void writeDataLinesGstReportImport() throws SQLException {

		System.out.println("###################Schedular start for gst report###################################");
		try {
			List<BillPunchResponseInterface> result = services.findAllApprovedDetailsForGst("13", "2022");

			List<BillPunchGstReportModel> list = new ArrayList<BillPunchGstReportModel>();

			for (BillPunchResponseInterface pm : result) {
				List<BillPunchDetailsModel> xm = services.getAllAtrnoAndOrdnoDetailsForGst(pm.getarticleCode(),
						pm.getbillOrderNo(), pm.getinvoiceNO());
				ShopMasterModel sh = null;

				StateMasterModel st = null;

				StateMasterModel stt = null;

				PartiesMasterModel pt = null;

				ArticlesMasterModel art = null;
				ArticlesMasterOldModel artt = null;
				BillPunchResponseInterface pr = null;
				Double mtr = 0.0d;
				Double mtrone = 0.0d;

				try {

					pr = services.getAllAtrnoAndOrdnoDetailsForGstPair(pm.getarticleCode(), pm.getbillOrderNo(),
							pm.getinvoiceNO());

				} catch (Exception e) {
					e.printStackTrace();
				}

				try {

					PriceInterface ch = services.getDetailsOrdnoDetails(pm.getbillOrderNo(), xm.get(0).getPartyCode(),
							xm.get(0).getRdcCode(), pm.getarticleCode());

					if (Optional.ofNullable(ch).isPresent()) {
						mtr = Double.parseDouble(ch.getmrp()) * Integer.parseInt(pr.getpair());

					} else {

						List<RdcMappingModel> xn = rdcmapdao.findByMergerdccode(xm.get(0).getRdcCode());

						for (RdcMappingModel bm : xn) {
							ch = services.getDetailsOrdnoDetails(pm.getbillOrderNo(), xm.get(0).getPartyCode(),
									bm.getRdcno(), pm.getarticleCode());

							if (Optional.ofNullable(ch).isPresent()) {
								mtrone = Double.parseDouble(ch.getmrp()) * Integer.parseInt(pr.getpair());
								mtr += mtrone;

							}

						}

					}

				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					sh = sservices.getShopDetails(xm.get(0).getRdcCode());
					if (Optional.ofNullable(sh).isPresent()) {

					} else {
						sh = sservices
								.getShopDetails(rdcmapdao.findByMergerdccode(xm.get(0).getRdcCode()).get(0).getRdcno());

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					// stt = stservices.getStateDetails(StringUtils.leftPad(sh.getStatecode(), 2,
					// "0"));
					st = stservices.getStateDetails(sh.getStatecode());
					if (Optional.ofNullable(st).isPresent()) {

					} else {
						st = new StateMasterModel();
						st.setStateName("");
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

				Calendar c1 = xm.get(0).getCreatedOn();
				DateFormat dateFormats = new SimpleDateFormat("dd-MM-yyyy");
				String bookdate = dateFormats.format(c1.getTime());
				String bookdates = null;
				if (bookdate.substring(3, 5).equalsIgnoreCase("01")) {
					bookdates = "JAN" + " " + bookdate.substring(6, 10);
				} else if (bookdate.substring(3, 5).equalsIgnoreCase("02")) {
					bookdates = "FEB" + " " + bookdate.substring(6, 10);
				} else if (bookdate.substring(3, 5).equalsIgnoreCase("03")) {
					bookdates = "MARCH" + " " + bookdate.substring(6, 10);
				} else if (bookdate.substring(3, 5).equalsIgnoreCase("04")) {
					bookdates = "APRIL" + " " + bookdate.substring(6, 10);
				} else if (bookdate.substring(3, 5).equalsIgnoreCase("05")) {
					bookdates = "MAY" + " " + bookdate.substring(6, 10);
				} else if (bookdate.substring(3, 5).equalsIgnoreCase("06")) {
					bookdates = "JUNE" + " " + bookdate.substring(6, 10);
				} else if (bookdate.substring(3, 5).equalsIgnoreCase("07")) {
					bookdates = "JULY" + " " + bookdate.substring(6, 10);
				} else if (bookdate.substring(3, 5).equalsIgnoreCase("08")) {
					bookdates = "AUG" + " " + bookdate.substring(6, 10);
				} else if (bookdate.substring(3, 5).equalsIgnoreCase("09")) {
					bookdates = "SEPT" + " " + bookdate.substring(6, 10);
				} else if (bookdate.substring(3, 5).equalsIgnoreCase("10")) {
					bookdates = "OCT" + " " + bookdate.substring(6, 10);
				} else if (bookdate.substring(3, 5).equalsIgnoreCase("11")) {
					bookdates = "NOV" + " " + bookdate.substring(6, 10);
				} else if (bookdate.substring(3, 5).equalsIgnoreCase("12")) {
					bookdates = "DEC" + " " + bookdate.substring(6, 10);
				} else {
					bookdates = "NA";
				}
				String date1 = "NA";
				try {
					if (Optional.ofNullable(xm.get(0).getRecptInvDate()).isPresent()) {
						Date d1 = xm.get(0).getRecptInvDate();
						DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
						date1 = dateFormat.format(d1);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				String date5 = "NA";
				try {
					if (Optional.ofNullable(xm.get(0).getGrDate()).isPresent()) {
						Date d5 = xm.get(0).getGrDate();
						DateFormat dateFormatqs = new SimpleDateFormat("dd-MM-yyyy");
						date5 = dateFormatqs.format(d5);

					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				String date3 = "NA";
				try {
					Date d3 = xm.get(0).getBillOrderDate();
					DateFormat dateFormatt = new SimpleDateFormat("dd-MM-yyyy");
					date3 = dateFormatt.format(d3);
				} catch (Exception e) {
					e.printStackTrace();
				}
				String date4 = "NA";

				try {
					Date d4 = xm.get(0).getCnDate();
					DateFormat dateFormatq = new SimpleDateFormat("dd-MM-yyyy");
					date4 = dateFormatq.format(d4);
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {

					pt = pservices.getPartiesDetails(StringUtils.leftPad(xm.get(0).getParty(), 4, "0"));
					if (Optional.ofNullable(pt).isPresent()) {

					} else {
						pt = new PartiesMasterModel();
						pt.setPartygstinno("");
						pt.setPartyno("");
						pt.setPartyfullname("");
					}
					// stt = stservices.getStateDetails(StringUtils.leftPad(pt.getPartystatecode(),
					// 2, "0"));
					stt = stservices.getStateDetails(pt.getPartystatecode());
					if (Optional.ofNullable(stt).isPresent()) {

					} else {
						stt = new StateMasterModel();
						stt.setStateName("");
						stt.setGstIn("");
					}

				} catch (Exception e) {

				}

				try {

					if (Optional.ofNullable(xm.get(0).getRecptInvDate()).isPresent()
							&& date1.substring(6, 10).equalsIgnoreCase("2021")) {
						artt = arOldservices.getArticleDetails(xm.get(0).getArticleCode());
						if (Optional.ofNullable(art).isPresent()) {

						} else {
							artt = new ArticlesMasterOldModel();
							artt.setHsncode("");
							artt.setArtuom("");
						}
					} else {
						art = artservices.getArticleDetails(xm.get(0).getArticleCode());
						if (Optional.ofNullable(art).isPresent()) {

						} else {
							art = new ArticlesMasterModel();
							art.setHsncode("");
							art.setArtuom("");
						}
					}

				} catch (Exception e) {
				}

				Double amtt = 0d;
				Double amtnew = 0d;
				Double amtone = 0d;
				Double totalcost = 0.0d;
				for (BillPunchDetailsModel vmm : xm) {
					try {
						if (Optional.ofNullable(vmm.getPairAmount()).isPresent()) {
							Double mt = vmm.getPairAmount();
							amtnew += mt;

						}
						if (Optional.ofNullable(vmm.getPairAmount()).isPresent()
								&& Double.valueOf(vmm.getIgst()) != 0.0) {
							Double mt = (vmm.getPairAmount() * Double.valueOf(vmm.getIgst())) / 100;
							amtt += mt;

						}

						if (Optional.ofNullable(vmm.getPairAmount()).isPresent()
								&& Double.valueOf(vmm.getCgst()) != 0.0) {
							Double mt = (vmm.getPairAmount() * Double.valueOf(vmm.getCgst())) / 100;
							amtone += mt;

						}

						if (!Optional.ofNullable(vmm.getFreight()).isPresent()) {
							vmm.setFreight("0");
						}

						if (!Optional.ofNullable(vmm.getDiscountAmtVal()).isPresent()) {
							vmm.setDiscountAmtVal(0.0d);
						}
						if (!Optional.ofNullable(vmm.getTcsValue()).isPresent()) {
							vmm.setTcsValue(0.0d);
						}
					} catch (Exception e) {
						// TODO: handle exception
					}

					try {
						totalcost = amtnew + amtt + amtone + amtone + vmm.getTcsValue()
								+ Integer.parseInt(vmm.getFreight());
					} catch (Exception e) {
					}

				}

				Double disamt = 0.0d;
				try {
					if (!Optional.ofNullable(pr.getinvAmount()).isPresent()
							&& (!Optional.ofNullable(xm.get(0).getDiscountAmt()).isPresent()
									|| xm.get(0).getDiscountAmt().equalsIgnoreCase("null"))) {
						disamt = 0.0d;
					} else {
						if ((Optional.ofNullable(xm.get(0).getDiscountAmt()).isPresent()
								|| !xm.get(0).getDiscountAmt().equalsIgnoreCase("null"))) {
							disamt = (Double.valueOf(xm.get(0).getDiscountAmt()) * pr.getinvAmount()) / 100;
						}

						// disamt = xm.get(0).getDiscountAmtVal();
					}
				} catch (Exception e) {
				}
				try {
					BillPunchGstReportModel md = new BillPunchGstReportModel();
					md.setPoNo(xm.get(0).getBillOrderNo());
					md.setPoDate(date3);
					md.setCnNo(xm.get(0).getCnNo());
					md.setCnDate(date4);
					md.setGrNo(xm.get(0).getGrNo());
					md.setGrDate(date5);
					md.setRdcNo(xm.get(0).getRdcCode());
					md.setRdcName(sh.getShopname().trim());
					md.setRdcStateName(st.getStateName());
					md.setReturnPeriod(bookdates);
					md.setRdcGstin(st.getGstIn());
					md.setDoctype(xm.get(0).getGrNo().substring(0, 2));
					md.setInvoiceNo(xm.get(0).getInvoiceNO());
					md.setPartyCode(xm.get(0).getPartyCode());
					md.setCreatedOn(xm.get(0).getCreatedOn());
					md.setInvoiceDate(date1);
					md.setSupplyGstin(pt.getPartygstinno());
					md.setSupplyName(pt.getPartyfullname());
					md.setSuplyCode(pt.getPartyno());
					md.setSplyStateName(stt.getStateName());
					md.setHsn(xm.get(0).getHsnCode());
					if (Optional.ofNullable(art).isPresent()) {
						md.setItemCode(art.getArtno());
						md.setItemDesc(art.getArtname());
						md.setUnitMeasure(art.getArtuom());
					} else {
						md.setItemCode(artt.getArtno());
						md.setItemDesc(artt.getArtname());
						md.setUnitMeasure(artt.getArtuom());
					}

					md.setQuantity(pr.getpair());
					md.setTaxableValue(String.format("%.2f", pr.getinvAmount()));
					md.setIgstrt(xm.get(0).getIgst());
					md.setIgstamt(String.format("%.2f", amtt));
					md.setCgstrt(xm.get(0).getCgst());
					md.setCgstamt(String.format("%.2f", amtone));
					md.setSgstrt(xm.get(0).getSgst());
					md.setSgstamt(String.format("%.2f", amtone));
					md.setInvamt(String.format("%.2f", totalcost));
					md.setMrp(String.format("%.2f", mtr));
					try {
						if (Optional.ofNullable(disamt).isPresent()) {
							md.setClaimdst(String.format("%.2f", disamt));
						} else {
							md.setClaimdst("0");
						}

					} catch (Exception e) {
						// TODO: handle exception
					}

					md.setBillrdcStateName(st.getStateName());
					md.setShippedStaeName(st.getStateName());
					md.setWeek("13");
					md.setYear("2022");
					list.add(md);

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
			gdao.saveAll(list);
			System.out
					.println("###################Schedular stendart for gst report###################################");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/********************************************************************************************
	 * STRAZA REPORT
	 ********************************************************************************************/
	// @Scheduled(cron = "0 15 11 * * ?")
	private void writeDataLinesStrazaReportImport() throws SQLException {

		System.out.println("###################Schedular start for straza report###################################");

		List<StrazaBillPunchDto> result = mservices.findWithDetailsStrazaReportAllrecords("46", "2021");

		List<BillPunchStrazaReportModel> list = new ArrayList<BillPunchStrazaReportModel>();
		PriceInterface art = null;
		Double amt = 0d;
		Double amtstd = 0d;
		for (StrazaBillPunchDto vm : result) {

			try {
				List<BillPunchResponseInterface> sm = mservices.getDetailsByFilterRdc(vm.getinvoiceNO(),
						vm.getbillOrderNo());

				for (BillPunchResponseInterface pr : sm) {

					amt = 0d;
					amtstd = 0d;

					art = services.getDetailsOrdnoDetails(vm.getbillOrderNo(), vm.getpartyCode(), vm.getrdcCode(),
							pr.getarticleCode());

					if (Optional.ofNullable(art).isPresent()) {
						Double mt = Double.parseDouble(art.getmrp()) * Integer.parseInt(pr.getpair());
						amt += mt;

						Double mtt = Double.parseDouble(art.getstdcost()) * Integer.parseInt(pr.getpair());
						amtstd += mtt;

					} else {

						List<RdcMappingModel> xn = rdcmapdao.findByMergerdccode(vm.getrdcCode());

						for (RdcMappingModel bm : xn) {
							art = services.getDetailsOrdnoDetails(vm.getbillOrderNo(), vm.getpartyCode(), bm.getRdcno(),
									pr.getarticleCode());

							if (Optional.ofNullable(art).isPresent()) {
								Double mt = Double.parseDouble(art.getmrp()) * Integer.parseInt(pr.getpair());
								amt += mt;

								Double mtt = Double.parseDouble(art.getstdcost()) * Integer.parseInt(pr.getpair());
								amtstd += mtt;
							}

						}

					}

				}
			} catch (Exception e) {
			}

			BillPunchStrazaReportModel md = new BillPunchStrazaReportModel();
			md.setBillOrderDate(vm.getbillOrderDate());
			md.setFreight(vm.getfreight());
			md.setGrDate(vm.getgrDate());
			md.setGrdateNew(vm.getgrdateNew());
			md.setGrnoOne(vm.getgrnoOne());
			md.setInvoiceCost(vm.getinvoiceCost());
			md.setMrp(String.format("%.2f", amt));
			md.setStdcost(String.format("%.2f", amtstd));
			md.setPartyName(vm.getpartyName());
			md.setPurchaseCost(vm.getpurchaseCost());
			md.setReceiveLoc(vm.getreceiveLoc());
			md.setReceiveWk(vm.getreceiveWk());
			md.setRecptInvDate(vm.getrecptInvDate());
			md.setShopName(vm.getshopName());
			md.setShopNo(vm.getshopNo());
			md.setTotalcost(vm.gettotalCost());
			md.setInvoiceNO(vm.getinvoiceNO());
			md.setRdcCode(vm.getrdcCode());
			md.setPair(vm.getpair());
			md.setPartyCode(vm.getpartyCode());
			md.setGrNo(vm.getgrNo());
			md.setCgstamt(vm.getcgstamt());
			md.setSgstamt(vm.getsgstamt());
			md.setIgstamt(vm.getigstamt());
			md.setBookWeek("46");
			md.setYear("2021");
			md.setBillOrderNo(vm.getbillOrderNo());
			list.add(md);

		}
		stdao.saveAll(list);
		System.out.println("###################Schedular end for straza report###################################");
	}

	/********************************************************************************************
	 * SUPPLY REPORT ######################### EVERY SUNDAY
	 * START####################
	 * 
	 ********************************************************************************************/
	@Scheduled(cron = "0 10 18 * * SAT")
	private void writeDataLinesSupplyReportImportNew() throws SQLException {

		System.out.println("###################Schedular start for supply report###################################");
		Calendar ss = Calendar.getInstance();
		// String.valueOf(ss.get(Calendar.YEAR))
		// String.valueOf(ss.get(Calendar.WEEK_OF_YEAR)+1)
		List<BillPunchResponseInterface> result = services.findAllApprovedDetailsForSupplyReport(
				StringUtils.leftPad(String.valueOf(ss.get(Calendar.WEEK_OF_YEAR)), 2, "0"),
				String.valueOf(ss.get(Calendar.YEAR)));

		List<BillPunchSupplyReportModel> list = new ArrayList<BillPunchSupplyReportModel>();
		Double amt = 0d;

		Double artamt = 0d;
		Double mrpamt = 0d;
		for (BillPunchResponseInterface sb : result) {

			String s = "0";

			String s1 = "0";
			BillPunchDetailsModel xm = services
					.getAllAtrnoAndOrdnoDetailsForSupply(sb.getparty(), sb.getbillOrderNo(), sb.getinvoiceNO()).get(0);

			BillPunchResponseInterface fx = services.getAllAtrnoAndOrdnoDetailsForSupplyReport(sb.getbillOrderNo(),
					sb.getinvoiceNO(), sb.getparty());

			try {

				s1 = pservices.getPartiesDetails(StringUtils.leftPad(xm.getParty(), 4, "0")).getPartystatecode();
			} catch (Exception e) {
			}
			StateMasterModel ms = stservices.getStateDetails(StringUtils.leftPad(String.valueOf(s1), 2, "0"));

			try {

				if (Optional.ofNullable(sservices.getShopDetails(xm.getRdcCode()).getStatecode()).isPresent()) {
					s = sservices.getShopDetails(xm.getRdcCode()).getStatecode();
				} else {
					s = sservices.getShopDetails(rdcmapdao.findByMergerdccode(xm.getRdcCode()).get(0).getRdcno())
							.getStatecode();

				}
			} catch (Exception e) {
			}

			StateMasterModel msc = stservices.getStateDetails(StringUtils.leftPad(String.valueOf(s), 2, "0"));
			StateMasterModel st = null;
			PriceInterface ch = null;
			amt = 0d;

			artamt = 0d;
			mrpamt = 0d;
			PartiesMasterModel pt = null;
			ArticlesMasterModel art = null;
			ShopMasterModel sh = null;

			try {

				pt = pservices.getPartiesDetails(StringUtils.leftPad(xm.getParty(), 4, "0"));
				if (Optional.ofNullable(pt).isPresent()) {

				} else {
					pt = new PartiesMasterModel();
					pt.setPartygstinno("0");
				}

			} catch (Exception e) {

			}

			try {
				sh = sservices.getShopDetails(xm.getRdcCode());
				if (Optional.ofNullable(sh).isPresent()) {

				} else {
					sh = sservices.getShopDetails(rdcmapdao.findByMergerdccode(xm.getRdcCode()).get(0).getRdcno());

				}
			} catch (Exception e) {
			}
			try {
				// stt = stservices.getStateDetails(StringUtils.leftPad(sh.getStatecode(), 2,
				// "0"));
				st = stservices.getStateDetails(sh.getStatecode());
				if (Optional.ofNullable(st).isPresent()) {

				} else {
					st = new StateMasterModel();
					st.setGstIn("");
				}

			} catch (Exception e) {
			}

			try {
				List<BillPunchResponseInterface> sm = mservices.getDetailsByFilterRdc(xm.getInvoiceNO(),
						xm.getBillOrderNo());

				for (BillPunchResponseInterface pr : sm) {

					ch = services.getDetailsOrdnoDetails(xm.getBillOrderNo(), xm.getPartyCode(), xm.getRdcCode(),
							pr.getarticleCode());

					if (Optional.ofNullable(ch).isPresent()) {
						Double mt = Double.parseDouble(ch.getmrp()) * Integer.parseInt(pr.getpair());
						amt += mt;

					} else {

						List<RdcMappingModel> xn = rdcmapdao.findByMergerdccode(xm.getRdcCode());

						for (RdcMappingModel bm : xn) {
							ch = services.getDetailsOrdnoDetails(xm.getBillOrderNo(), xm.getPartyCode(), bm.getRdcno(),
									pr.getarticleCode());

							if (Optional.ofNullable(ch).isPresent()) {
								Double mt = Double.parseDouble(ch.getmrp()) * Integer.parseInt(pr.getpair());
								amt += mt;
							}

						}

					}

				}
			} catch (Exception e) {
			}
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			String date1 = "NA";
			try {
				if (Optional.ofNullable(xm.getRecptInvDate()).isPresent()) {
					Date d1 = xm.getRecptInvDate();
					date1 = dateFormat.format(d1);
				}

			} catch (Exception e) {
			}

			try {
				if (Optional.ofNullable(xm.getRecptInvDate()).isPresent()
						&& date1.substring(6, 10).equalsIgnoreCase("2021")) {
					System.out.println(
							"###################Schedular enter in old article mastaer for supply report###################################");
					BillPunchResponseInterface artt = arOldservices.getArticleDetailsReportstraza(xm.getInvoiceNO(),
							xm.getPartyCode(), xm.getBillOrderNo());
					if (Optional.ofNullable(artt).isPresent()) {

						Double mx = Double.parseDouble(artt.getartpricewspedp());
						artamt += mx;
						Double mxx = Double.parseDouble(artt.getartpricemrpedp());
						mrpamt += mxx;

					} else {
						artamt = 0d;
						mrpamt = 0d;
					}

				} else {
					BillPunchResponseInterface artt = aservices.getArticleDetailsReportstraza(xm.getInvoiceNO(),
							xm.getPartyCode(), xm.getBillOrderNo());
					if (Optional.ofNullable(artt).isPresent()) {

						Double mx = Double.parseDouble(artt.getartpricewspedp());
						artamt += mx;
						Double mxx = Double.parseDouble(artt.getartpricemrpedp());
						mrpamt += mxx;

					} else {
						artamt = 0d;
						mrpamt = 0d;
					}
				}

			} catch (Exception e) {
			}

			String date23 = "NA";
			try {
				if (Optional.ofNullable(xm.getGrDate()).isPresent()) {
					Date d11 = xm.getGrDate();
					date23 = dateFormat.format(d11);
				}

			} catch (Exception e) {
			}

			Date d2 = xm.getCnDate();
			String date2 = dateFormat.format(d2);
			Date d3 = xm.getBillOrderDate();
			String date3 = dateFormat.format(d3);
			if (!Optional.ofNullable(xm.getFreight()).isPresent()) {
				xm.setFreight("0");
			}

			if (!Optional.ofNullable(xm.getDiscountAmtVal()).isPresent()) {
				xm.setDiscountAmtVal(0.0d);
			}
			if (!Optional.ofNullable(xm.getTcsValue()).isPresent()) {
				xm.setTcsValue(0.0d);
			}

			if (Optional.ofNullable(msc.getStateName()).isPresent()) {
				if (msc.getStateName().equalsIgnoreCase("WEST BENGAL")) {
					xm.setRegion("EAST");

				} else if (msc.getStateName().equalsIgnoreCase("HARYANA")) {
					xm.setRegion("NORTH");
				}

				else if (msc.getStateName().equalsIgnoreCase("MAHARASHTRA")) {
					xm.setRegion("WEST");
				}

				else if (msc.getStateName().equalsIgnoreCase("KARNATAKA")
						|| msc.getStateName().equalsIgnoreCase("TAMIL NADU")) {
					xm.setRegion("SOUTH");
				}
			} else {
				xm.setRegion("NA");
			}

			BillPunchSupplyReportModel md = new BillPunchSupplyReportModel();
			md.setFromStateCode(s1);
			md.setFromStateName(ms.getStateName());
			md.setPartyCode(xm.getPartyCode());
			md.setPartyName(xm.getPartyName());
			md.setToStateCode(s);
			md.setToStateName(msc.getStateName());
			// md.setTolocationCode(xm.getShopNo());
			md.setTolocationCode(xm.getRdcCode());
			md.setRecLocName(xm.getReceiveLoc());
			md.setDept(xm.getShopName());
			md.setRegion(xm.getRegion());
			md.setInvoiceNo(xm.getInvoiceNO());
			md.setInvoiceDate(date1);
			md.setGrnNo(xm.getGrNo());
			md.setGrnDate(date23);
			md.setBooking(xm.getBataWeek() + "/" + xm.getBataYear());
			md.setPairs(fx.getpair());
			md.setTaxableValue(String.format("%.2f", xm.getInvoiceCost()));
			md.setCgst(String.format("%.2f", Double.valueOf(sb.getcgstamt())));
			md.setSgst(String.format("%.2f", Double.valueOf(sb.getsgstamt())));
			md.setIgst(String.format("%.2f", Double.valueOf(sb.getigstamt())));
			md.setFreight(String.format("%.2f", Double.valueOf(xm.getFreight())));
			md.setTcs(String.format("%.2f", xm.getTcsValue()));
			md.setNetAmount(String.format("%.2f", xm.getTotalCost()));
			md.setGrNo(xm.getCnNo());
			md.setGrDate(date2);
			md.setPoNo(xm.getBillOrderNo());
			md.setPoDate(date3);
			md.setVenGstNo(pt.getPartygstinno());
			md.setMrp(String.format("%.2f", mrpamt));
			md.setWsp(String.format("%.2f", artamt));
			md.setReceiverGstNo(st.getGstIn());
			md.setNoclaim(String.format("%.2f", xm.getDiscountAmtVal()));
			md.setWeek(StringUtils.leftPad(String.valueOf(ss.get(Calendar.WEEK_OF_YEAR)), 2, "0"));
			md.setYear(String.valueOf(ss.get(Calendar.YEAR)));
			list.add(md);

		}
		splydao.saveAll(list);
		System.out.println("###################Schedular end for supply report###################################");
	}

	/********************************************************************************************
	 * TRANS REPORT
	 ********************************************************************************************/
	@Scheduled(cron = "0 12 18 * * SAT")
	private void writeDataLinesTransReportImportNew() throws SQLException {

		System.out.println("###################Schedular start for trans report###################################");
		Calendar ss = Calendar.getInstance();
		List<BillPunchResponseInterface> result = mservices.getReportDetailsByWeekTrans(
				StringUtils.leftPad(String.valueOf(ss.get(Calendar.WEEK_OF_YEAR)), 2, "0"),
				String.valueOf(ss.get(Calendar.YEAR)));

		List<BillPunchTransReportModel> list = new ArrayList<BillPunchTransReportModel>();
		String amt = null;
		for (BillPunchResponseInterface vm : result) {
			BillPunchResponseInterface pr = mservices.getReportTotalPairDetailsTrans(vm.getinvoiceNO(),
					vm.getpartyCode(), vm.getrdcCode(),
					StringUtils.leftPad(String.valueOf(ss.get(Calendar.WEEK_OF_YEAR)), 2, "0"));

			amt = null;
			if (Optional.ofNullable(vm.getinvamtreport()).isPresent()) {
				amt = vm.getinvamtreport();

			} else {
				amt = vm.getpurchaseCostreport();
			}

			String date = "00-00-0000";
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			try {
				if (Optional.ofNullable(vm.getresumeInvDateone()).isPresent()) {
					Date d1 = vm.getresumeInvDateone();
					date = dateFormat.format(d1);
				}
			} catch (Exception e) {
			}
			String m11 = null;
			String m23 = null;
			String m33 = null;
			String m1 = vm.getcgstamt();

			String m2 = vm.getsgstamt();

			String m3 = vm.getigstamt();
			if (Optional.ofNullable(vm.getcgst()).isPresent()) {
				m11 = vm.getcgst();

				m23 = vm.getsgst();
			} else {
				m11 = "0";

				m23 = "0";
			}
			if (Optional.ofNullable(vm.getcgst()).isPresent()) {
				m33 = vm.getigst();
			} else {
				m33 = "0";
			}

			DateFormat dateFormats = new SimpleDateFormat("ddMMyyyy");
			String cndate = dateFormats.format(vm.getcnDateone());
			String recdate = null;
			BillPunchResponseInterface art = null;
			try {
				recdate = dateFormats.format(vm.getrecptInvDateone());
			} catch (Exception e) {
			}

			DateFormat dateFormatt = new SimpleDateFormat("dd-MM-yyyy");

			String date11 = "00-00-0000";
			try {
				if (Optional.ofNullable(vm.getrecptInvDateone()).isPresent()) {
					Date d2 = vm.getrecptInvDateone();
					date11 = dateFormatt.format(d2);
				}
			} catch (Exception e) {
			}
			try {
				if (Optional.ofNullable(vm.getrecptInvDateone()).isPresent()
						&& date11.substring(6, 10).equalsIgnoreCase("2021")) {
					System.out.println(
							"###################Schedular enter in old article mastaer for trans report###################################");
					art = arOldservices.getArticleDetailsReport(String.valueOf(vm.getarticleCode()));
				} else {
					art = aservices.getArticleDetailsReport(String.valueOf(vm.getarticleCode()));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			ShopMasterModel sh = sservices.getShopDetails(vm.getrdcCode());
			String st = vm.gettrnsportCode();
			if (st == null) {
				st = "0";
			}

			BillPunchTransReportModel md = new BillPunchTransReportModel();
			md.setInvoiceNo(vm.getinvoiceNO());
			md.setArticleCode(vm.getarticleCode());
			if (Optional.ofNullable(vm.getsizeCode()).isPresent()) {
				md.setSizeCode(String.valueOf(vm.getsizeCode()));
			} else {
				md.setSizeCode("0");
			}

			md.setRdcCode(vm.getrdcCode());
			md.setDistNo(String.valueOf(sh.getDistrictno()));
			md.setPair(String.valueOf(vm.getpair()));
			md.setTotalPair(String.valueOf(pr.gettotalpair()));
			if (Optional.ofNullable(vm.getpackCaseB()).isPresent()) {
				md.setPackCaseb(String.valueOf(vm.getpackCaseB()));
			} else {
				md.setPackCaseb("0");
			}
			if (Optional.ofNullable(vm.getpackCaseM()).isPresent()) {
				md.setPackCasem(String.valueOf(vm.getpackCaseM()));
			} else {
				md.setPackCasem("0");
			}
			if (Optional.ofNullable(vm.getpackCaseS()).isPresent()) {
				md.setPackCases(String.valueOf(vm.getpackCaseS()));
			} else {
				md.setPackCases("0");
			}
			if (Optional.ofNullable(vm.getpackCaseC()).isPresent()) {
				md.setPackCasec(String.valueOf(vm.getpackCaseC()));
			} else {
				md.setPackCasec("0");
			}
			if (Optional.ofNullable(vm.getpackCaseT()).isPresent()) {
				md.setPackCaset(String.valueOf(vm.getpackCaseT()));
			} else {
				md.setPackCaset("0");
			}
			md.setDcCode(vm.getdcCode());
			md.setArtSequence(art.getartseq());
			md.setPartyCode(vm.getpartyCode());
			md.setCnNo(vm.getcnNo());
			md.setCnDate(cndate);
			if (Optional.ofNullable(st).isPresent()) {
				md.setTransportCode(String.valueOf(st));
			} else {
				md.setTransportCode("0");
			}

			if (Optional.ofNullable(vm.getrdpermitNo()).isPresent()) {
				md.setPermitNo(vm.getrdpermitNo());
			} else {
				md.setPermitNo("0");
			}

			if (Optional.ofNullable(vm.getresumeInvNoTwo()).isPresent()) {
				md.setResumeInvNOTwo(vm.getresumeInvNoTwo());
			} else {
				md.setResumeInvNOTwo("0");
			}

			if (Optional.ofNullable(vm.getresumeInvNo()).isPresent()) {
				md.setResumeInvNO(vm.getresumeInvNo());
			} else {
				md.setResumeInvNO("0");
			}

			md.setStateCode(sh.getStatecode());
			md.setInvAmt(amt);
			md.setGrNo(vm.getgrNo());
			md.setRecDate(recdate);

			md.setDateOne(date.substring(0, 2));
			md.setDateTwo(date.substring(3, 5));
			md.setDateThree(date.substring(6, 10));

			md.setBillOrdNo(vm.getbillOrderNo());
			md.setBillOrdNoOne(vm.getbillOrderNo());
			md.setArtCost(
					String.valueOf(Integer.valueOf((int) (art.getartstdCost() * 100 * Integer.valueOf(vm.getpair())))));
			md.setCgst(m11);
			md.setCgstamt(m1);
			md.setSgst(m23);
			md.setSgstamt(m2);
			md.setIgst(m33);
			md.setIgstamt(m3);
			md.setWeek(StringUtils.leftPad(String.valueOf(ss.get(Calendar.WEEK_OF_YEAR)), 2, "0"));
			md.setYear(String.valueOf(ss.get(Calendar.YEAR)));
			list.add(md);
		}
		transdao.saveAll(list);
		System.out.println("###################Schedular end for trans report###################################");
	}

	/********************************************************************************************
	 * EDP REPORT
	 ********************************************************************************************/
	@Scheduled(cron = "0 15 18 * * SAT")
	private void writeDataLinesEdpReportImportNew() throws SQLException {

		System.out.println("###################Schedular start for edp report###################################");
		Calendar ss = Calendar.getInstance();
		List<BillPunchResponseInterface> result = mservices.getReportDetailsByWeekEdp(
				StringUtils.leftPad(String.valueOf(ss.get(Calendar.WEEK_OF_YEAR)), 2, "0"),
				String.valueOf(ss.get(Calendar.YEAR)));

		List<BillPunchEdpReportModel> list = new ArrayList<BillPunchEdpReportModel>();

		String amtnew = null;
		String amt = null;
		BillPunchResponseInterface art = null;

		for (BillPunchResponseInterface vm : result) {

			amtnew = null;
			amt = null;
			if (Optional.ofNullable(vm.getpurchaseCostreport()).isPresent()) {
				amtnew = vm.getpurchaseCostreport();

			} else {
				amtnew = vm.getinvamtreport();
			}

			if (Optional.ofNullable(vm.getinvamtreport()).isPresent()) {
				amt = vm.getinvamtreport();

			} else {
				amt = vm.getpurchaseCostreport();
			}

			Date d1 = vm.getcnDateone();
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			String date1 = dateFormat.format(d1);
			String date111 = "00-00-0000";
			String date11 = "00-00-0000";
			try {
				if (Optional.ofNullable(vm.getrecptInvDateone()).isPresent()) {
					Date d2 = vm.getrecptInvDateone();
					date11 = dateFormat.format(d2);
				}
			} catch (Exception e) {
			}
			try {
				if (Optional.ofNullable(vm.getresumeInvDateone()).isPresent()) {
					Date d3 = vm.getresumeInvDateone();
					date111 = dateFormat.format(d3);
				}
			} catch (Exception e) {
			}

			String m1 = vm.getcgstamt();

			String m2 = vm.getsgstamt();

			String m3 = vm.getigstamt();
			ShopMasterModel sh = sservices.getShopDetails(vm.getrdcCode());

			String st = vm.gettrnsportCode();
			if (st == null) {
				st = "0";
			}
			try {
				if (Optional.ofNullable(vm.getrecptInvDateone()).isPresent()
						&& date11.substring(6, 10).equalsIgnoreCase("2021")) {
					System.out.println(
							"###################Schedular enter in old article mastaer for edp report###################################");

					art = arOldservices.getArticleDetailsedpReport(vm.getinvoiceNO(), vm.getpartyCode(), vm.getgrNo());
				} else {
					art = aservices.getArticleDetailsedpReport(vm.getinvoiceNO(), vm.getpartyCode(), vm.getgrNo());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			BillPunchEdpReportModel md = new BillPunchEdpReportModel();
			md.setRdcCode(vm.getrdcCode());
			md.setInvoiceNo(vm.getinvoiceNO());
			md.setRdcCode(vm.getrdcCode());
			md.setDistNo(String.valueOf(sh.getDistrictno()));
			md.setPair(String.valueOf(vm.getpair()));
			if (Optional.ofNullable(vm.getpackCaseT()).isPresent()) {
				md.setPackCaset(String.valueOf(vm.getpackCaseT()));
			} else {
				md.setPackCaset("0");
			}
			md.setAmtone(art.getartpricemrpedp());
			md.setAmttwo(art.getartpricefactoryedp());
			md.setAmtwsp(art.getartpricewspedp());
			md.setAmtnew(amtnew);
			md.setPartyCode(vm.getpartyCode());
			md.setCnNo(vm.getcnNo());
			md.setDateOne(date1.substring(0, 2));
			md.setDateTwo(date1.substring(3, 5));
			md.setDateThree(date1.substring(6, 10));

			if (Optional.ofNullable(st).isPresent()) {
				md.setTransportCode(String.valueOf(st));
			} else {

				md.setTransportCode("0");
			}
			if (Optional.ofNullable(vm.getrdpermitNo()).isPresent()) {
				md.setPermitNo(vm.getrdpermitNo());
			} else {

				md.setPermitNo("0");
			}
			if (Optional.ofNullable(vm.getresumeInvNoTwo()).isPresent()) {
				md.setResumeInvNOTwo(vm.getresumeInvNoTwo());
			} else {
				md.setResumeInvNOTwo("0");
			}

			if (Optional.ofNullable(vm.getresumeInvNo()).isPresent()) {
				md.setResumeInvNO(vm.getresumeInvNo());
			} else {
				md.setResumeInvNO("0");
			}
			md.setStateCode(sh.getStatecode());
			md.setInvAmt(amt);
			md.setGrNo(vm.getgrNo());
			md.setDateTwoOne(date11.substring(0, 2));
			md.setDateTwoTwo(date11.substring(3, 5));
			md.setDateTwoThree(date11.substring(6, 10));
			md.setDateThreeOne(date11.substring(0, 2));
			md.setDateThreeTwo(date11.substring(3, 5));
			md.setDateThreeThree(date11.substring(6, 10));

			md.setDateFourOne(date111.substring(0, 2));
			md.setDateFourTwo(date111.substring(3, 5));
			md.setDateFourThree(date111.substring(6, 10));
			md.setAmtThree(art.getartstdCostedp());
			md.setCgstamt(m1);
			md.setSgstamt(m2);
			md.setIgstamt(m3);
			md.setWeek(StringUtils.leftPad(String.valueOf(ss.get(Calendar.WEEK_OF_YEAR)), 2, "0"));
			md.setYear(String.valueOf(ss.get(Calendar.YEAR)));
			md.setBillOrdNo(vm.getbillOrderNo());
			list.add(md);

		}
		edao.saveAll(list);
		System.out.println("###################Schedular end for epd report###################################");
	}

	/********************************************************************************************
	 * GST REPORT
	 ********************************************************************************************/
	@Scheduled(cron = "0 18 18 * * SAT")
	private void writeDataLinesGstReportImportNew() throws SQLException {

		System.out.println("###################Schedular start for gst report###################################");
		Calendar ss = Calendar.getInstance();
		try {
			List<BillPunchResponseInterface> result = services.findAllApprovedDetailsForGst(
					StringUtils.leftPad(String.valueOf(ss.get(Calendar.WEEK_OF_YEAR)), 2, "0"),
					String.valueOf(ss.get(Calendar.YEAR)));

			List<BillPunchGstReportModel> list = new ArrayList<BillPunchGstReportModel>();

			for (BillPunchResponseInterface pm : result) {
				List<BillPunchDetailsModel> xm = services.getAllAtrnoAndOrdnoDetailsForGst(pm.getarticleCode(),
						pm.getbillOrderNo(), pm.getinvoiceNO());
				ShopMasterModel sh = null;

				StateMasterModel st = null;

				StateMasterModel stt = null;

				PartiesMasterModel pt = null;

				ArticlesMasterModel art = null;
				ArticlesMasterOldModel artt = null;
				BillPunchResponseInterface pr = null;
				Double mtr = 0.0d;
				Double mtrone = 0.0d;

				try {

					pr = services.getAllAtrnoAndOrdnoDetailsForGstPair(pm.getarticleCode(), pm.getbillOrderNo(),
							pm.getinvoiceNO());

				} catch (Exception e) {
					e.printStackTrace();
				}

				try {

					PriceInterface ch = services.getDetailsOrdnoDetails(pm.getbillOrderNo(), xm.get(0).getPartyCode(),
							xm.get(0).getRdcCode(), pm.getarticleCode());

					if (Optional.ofNullable(ch).isPresent()) {
						mtr = Double.parseDouble(ch.getmrp()) * Integer.parseInt(pr.getpair());

					} else {

						List<RdcMappingModel> xn = rdcmapdao.findByMergerdccode(xm.get(0).getRdcCode());

						for (RdcMappingModel bm : xn) {
							ch = services.getDetailsOrdnoDetails(pm.getbillOrderNo(), xm.get(0).getPartyCode(),
									bm.getRdcno(), pm.getarticleCode());

							if (Optional.ofNullable(ch).isPresent()) {
								mtrone = Double.parseDouble(ch.getmrp()) * Integer.parseInt(pr.getpair());
								mtr += mtrone;

							}

						}

					}

				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					sh = sservices.getShopDetails(xm.get(0).getRdcCode());
					if (Optional.ofNullable(sh).isPresent()) {

					} else {
						sh = sservices
								.getShopDetails(rdcmapdao.findByMergerdccode(xm.get(0).getRdcCode()).get(0).getRdcno());

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					// stt = stservices.getStateDetails(StringUtils.leftPad(sh.getStatecode(), 2,
					// "0"));
					st = stservices.getStateDetails(sh.getStatecode());
					if (Optional.ofNullable(st).isPresent()) {

					} else {
						st = new StateMasterModel();
						st.setStateName("");
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

				Calendar c1 = xm.get(0).getCreatedOn();
				DateFormat dateFormats = new SimpleDateFormat("dd-MM-yyyy");
				String bookdate = dateFormats.format(c1.getTime());
				String bookdates = null;
				if (bookdate.substring(3, 5).equalsIgnoreCase("01")) {
					bookdates = "JAN" + " " + bookdate.substring(6, 10);
				} else if (bookdate.substring(3, 5).equalsIgnoreCase("02")) {
					bookdates = "FEB" + " " + bookdate.substring(6, 10);
				} else if (bookdate.substring(3, 5).equalsIgnoreCase("03")) {
					bookdates = "MARCH" + " " + bookdate.substring(6, 10);
				} else if (bookdate.substring(3, 5).equalsIgnoreCase("04")) {
					bookdates = "APRIL" + " " + bookdate.substring(6, 10);
				} else if (bookdate.substring(3, 5).equalsIgnoreCase("05")) {
					bookdates = "MAY" + " " + bookdate.substring(6, 10);
				} else if (bookdate.substring(3, 5).equalsIgnoreCase("06")) {
					bookdates = "JUNE" + " " + bookdate.substring(6, 10);
				} else if (bookdate.substring(3, 5).equalsIgnoreCase("07")) {
					bookdates = "JULY" + " " + bookdate.substring(6, 10);
				} else if (bookdate.substring(3, 5).equalsIgnoreCase("08")) {
					bookdates = "AUG" + " " + bookdate.substring(6, 10);
				} else if (bookdate.substring(3, 5).equalsIgnoreCase("09")) {
					bookdates = "SEPT" + " " + bookdate.substring(6, 10);
				} else if (bookdate.substring(3, 5).equalsIgnoreCase("10")) {
					bookdates = "OCT" + " " + bookdate.substring(6, 10);
				} else if (bookdate.substring(3, 5).equalsIgnoreCase("11")) {
					bookdates = "NOV" + " " + bookdate.substring(6, 10);
				} else if (bookdate.substring(3, 5).equalsIgnoreCase("12")) {
					bookdates = "DEC" + " " + bookdate.substring(6, 10);
				} else {
					bookdates = "NA";
				}
				String date1 = "NA";
				try {
					if (Optional.ofNullable(xm.get(0).getRecptInvDate()).isPresent()) {
						Date d1 = xm.get(0).getRecptInvDate();
						DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
						date1 = dateFormat.format(d1);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				String date5 = "NA";
				try {
					if (Optional.ofNullable(xm.get(0).getGrDate()).isPresent()) {
						Date d5 = xm.get(0).getGrDate();
						DateFormat dateFormatqs = new SimpleDateFormat("dd-MM-yyyy");
						date5 = dateFormatqs.format(d5);

					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				String date3 = "NA";
				try {
					Date d3 = xm.get(0).getBillOrderDate();
					DateFormat dateFormatt = new SimpleDateFormat("dd-MM-yyyy");
					date3 = dateFormatt.format(d3);
				} catch (Exception e) {
					e.printStackTrace();
				}
				String date4 = "NA";

				try {
					Date d4 = xm.get(0).getCnDate();
					DateFormat dateFormatq = new SimpleDateFormat("dd-MM-yyyy");
					date4 = dateFormatq.format(d4);
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {

					pt = pservices.getPartiesDetails(StringUtils.leftPad(xm.get(0).getParty(), 4, "0"));
					if (Optional.ofNullable(pt).isPresent()) {

					} else {
						pt = new PartiesMasterModel();
						pt.setPartygstinno("");
						pt.setPartyno("");
						pt.setPartyfullname("");
					}
					// stt = stservices.getStateDetails(StringUtils.leftPad(pt.getPartystatecode(),
					// 2, "0"));
					stt = stservices.getStateDetails(pt.getPartystatecode());
					if (Optional.ofNullable(stt).isPresent()) {

					} else {
						stt = new StateMasterModel();
						stt.setStateName("");
						stt.setGstIn("");
					}

				} catch (Exception e) {

				}

				try {

					if (Optional.ofNullable(xm.get(0).getRecptInvDate()).isPresent()
							&& date1.substring(6, 10).equalsIgnoreCase("2021")) {
						System.out.println(
								"###################Schedular enter in old article mastaer for gst report###################################");
						artt = arOldservices.getArticleDetails(xm.get(0).getArticleCode());
						if (Optional.ofNullable(art).isPresent()) {

						} else {
							artt = new ArticlesMasterOldModel();
							artt.setHsncode("");
							artt.setArtuom("");
						}
					} else {
						art = artservices.getArticleDetails(xm.get(0).getArticleCode());
						if (Optional.ofNullable(art).isPresent()) {

						} else {
							art = new ArticlesMasterModel();
							art.setHsncode("");
							art.setArtuom("");
						}
					}

				} catch (Exception e) {
				}

				Double amtt = 0d;
				Double amtnew = 0d;
				Double amtone = 0d;
				Double totalcost = 0.0d;
				for (BillPunchDetailsModel vmm : xm) {
					try {
						if (Optional.ofNullable(vmm.getPairAmount()).isPresent()) {
							Double mt = vmm.getPairAmount();
							amtnew += mt;

						}
						if (Optional.ofNullable(vmm.getPairAmount()).isPresent()
								&& Double.valueOf(vmm.getIgst()) != 0.0) {
							Double mt = (vmm.getPairAmount() * Double.valueOf(vmm.getIgst())) / 100;
							amtt += mt;

						}

						if (Optional.ofNullable(vmm.getPairAmount()).isPresent()
								&& Double.valueOf(vmm.getCgst()) != 0.0) {
							Double mt = (vmm.getPairAmount() * Double.valueOf(vmm.getCgst())) / 100;
							amtone += mt;

						}

						if (!Optional.ofNullable(vmm.getFreight()).isPresent()) {
							vmm.setFreight("0");
						}

						if (!Optional.ofNullable(vmm.getDiscountAmtVal()).isPresent()) {
							vmm.setDiscountAmtVal(0.0d);
						}
						if (!Optional.ofNullable(vmm.getTcsValue()).isPresent()) {
							vmm.setTcsValue(0.0d);
						}
					} catch (Exception e) {
						// TODO: handle exception
					}

					try {
						totalcost = amtnew + amtt + amtone + amtone + vmm.getTcsValue()
								+ Integer.parseInt(vmm.getFreight());
					} catch (Exception e) {
					}

				}

				Double disamt = 0.0d;
				try {
					if (!Optional.ofNullable(pr.getinvAmount()).isPresent()
							&& (!Optional.ofNullable(xm.get(0).getDiscountAmt()).isPresent()
									|| xm.get(0).getDiscountAmt().equalsIgnoreCase("null"))) {
						disamt = 0.0d;
					} else {
						if ((Optional.ofNullable(xm.get(0).getDiscountAmt()).isPresent()
								|| !xm.get(0).getDiscountAmt().equalsIgnoreCase("null"))) {
							disamt = (Double.valueOf(xm.get(0).getDiscountAmt()) * pr.getinvAmount()) / 100;
						}

						// disamt = xm.get(0).getDiscountAmtVal();
					}
				} catch (Exception e) {
				}
				try {
					BillPunchGstReportModel md = new BillPunchGstReportModel();
					md.setPoNo(xm.get(0).getBillOrderNo());
					md.setPoDate(date3);
					md.setCnNo(xm.get(0).getCnNo());
					md.setCnDate(date4);
					md.setGrNo(xm.get(0).getGrNo());
					md.setGrDate(date5);
					md.setRdcNo(xm.get(0).getRdcCode());
					md.setRdcName(sh.getShopname().trim());
					md.setRdcStateName(st.getStateName());
					md.setReturnPeriod(bookdates);
					md.setRdcGstin(st.getGstIn());
					md.setDoctype(xm.get(0).getGrNo().substring(0, 2));
					md.setInvoiceNo(xm.get(0).getInvoiceNO());
					md.setPartyCode(xm.get(0).getPartyCode());
					md.setCreatedOn(xm.get(0).getCreatedOn());
					md.setInvoiceDate(date1);
					md.setSupplyGstin(pt.getPartygstinno());
					md.setSupplyName(pt.getPartyfullname());
					md.setSuplyCode(pt.getPartyno());
					md.setSplyStateName(stt.getStateName());
					md.setHsn(xm.get(0).getHsnCode());
					md.setItemCode(art.getArtno());
					md.setItemDesc(art.getArtname());
					md.setUnitMeasure(art.getArtuom());
					md.setQuantity(pr.getpair());
					md.setTaxableValue(String.format("%.2f", pr.getinvAmount()));
					md.setIgstrt(xm.get(0).getIgst());
					md.setIgstamt(String.format("%.2f", amtt));
					md.setCgstrt(xm.get(0).getCgst());
					md.setCgstamt(String.format("%.2f", amtone));
					md.setSgstrt(xm.get(0).getSgst());
					md.setSgstamt(String.format("%.2f", amtone));
					md.setInvamt(String.format("%.2f", totalcost));
					md.setMrp(String.format("%.2f", mtr));
					try {
						if (Optional.ofNullable(disamt).isPresent()) {
							md.setClaimdst(String.format("%.2f", disamt));
						} else {
							md.setClaimdst("0");
						}

					} catch (Exception e) {
						// TODO: handle exception
					}

					md.setBillrdcStateName(st.getStateName());
					md.setShippedStaeName(st.getStateName());
					md.setWeek(StringUtils.leftPad(String.valueOf(ss.get(Calendar.WEEK_OF_YEAR)), 2, "0"));
					md.setYear(String.valueOf(ss.get(Calendar.YEAR)));
					list.add(md);

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
			gdao.saveAll(list);
			System.out
					.println("###################Schedular stendart for gst report###################################");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/********************************************************************************************
	 * STRAZA REPORT
	 ********************************************************************************************/
	@Scheduled(cron = "0 22 18 * * SAT")
	private void writeDataLinesStrazaReportImportNew() throws SQLException {

		System.out.println("###################Schedular start for straza report###################################");
		Calendar ss = Calendar.getInstance();
		List<StrazaBillPunchDto> result = mservices.findWithDetailsStrazaReportAllrecords(
				StringUtils.leftPad(String.valueOf(ss.get(Calendar.WEEK_OF_YEAR)), 2, "0"),
				String.valueOf(ss.get(Calendar.YEAR)));

		List<BillPunchStrazaReportModel> list = new ArrayList<BillPunchStrazaReportModel>();
		PriceInterface art = null;
		Double amt = 0d;
		Double amtstd = 0d;
		for (StrazaBillPunchDto vm : result) {

			try {
				List<BillPunchResponseInterface> sm = mservices.getDetailsByFilterRdc(vm.getinvoiceNO(),
						vm.getbillOrderNo());

				for (BillPunchResponseInterface pr : sm) {

					amt = 0d;
					amtstd = 0d;

					art = services.getDetailsOrdnoDetails(vm.getbillOrderNo(), vm.getpartyCode(), vm.getrdcCode(),
							pr.getarticleCode());

					if (Optional.ofNullable(art).isPresent()) {
						Double mt = Double.parseDouble(art.getmrp()) * Integer.parseInt(pr.getpair());
						amt += mt;

						Double mtt = Double.parseDouble(art.getstdcost()) * Integer.parseInt(pr.getpair());
						amtstd += mtt;

					} else {

						List<RdcMappingModel> xn = rdcmapdao.findByMergerdccode(vm.getrdcCode());

						for (RdcMappingModel bm : xn) {
							art = services.getDetailsOrdnoDetails(vm.getbillOrderNo(), vm.getpartyCode(), bm.getRdcno(),
									pr.getarticleCode());

							if (Optional.ofNullable(art).isPresent()) {
								Double mt = Double.parseDouble(art.getmrp()) * Integer.parseInt(pr.getpair());
								amt += mt;

								Double mtt = Double.parseDouble(art.getstdcost()) * Integer.parseInt(pr.getpair());
								amtstd += mtt;
							}

						}

					}

				}
			} catch (Exception e) {
			}

			BillPunchStrazaReportModel md = new BillPunchStrazaReportModel();
			md.setBillOrderDate(vm.getbillOrderDate());
			md.setFreight(vm.getfreight());
			md.setGrDate(vm.getgrDate());
			md.setGrdateNew(vm.getgrdateNew());
			md.setGrnoOne(vm.getgrnoOne());
			md.setInvoiceCost(vm.getinvoiceCost());
			md.setMrp(String.format("%.2f", amt));
			md.setStdcost(String.format("%.2f", amtstd));
			md.setPartyName(vm.getpartyName());
			md.setPurchaseCost(vm.getpurchaseCost());
			md.setReceiveLoc(vm.getreceiveLoc());
			md.setReceiveWk(vm.getreceiveWk());
			md.setRecptInvDate(vm.getrecptInvDate());
			md.setShopName(vm.getshopName());
			md.setShopNo(vm.getshopNo());
			md.setTotalcost(vm.gettotalCost());
			md.setInvoiceNO(vm.getinvoiceNO());
			md.setRdcCode(vm.getrdcCode());
			md.setPair(vm.getpair());
			md.setPartyCode(vm.getpartyCode());
			md.setGrNo(vm.getgrNo());
			md.setCgstamt(vm.getcgstamt());
			md.setSgstamt(vm.getsgstamt());
			md.setIgstamt(vm.getigstamt());
			md.setBookWeek(StringUtils.leftPad(String.valueOf(ss.get(Calendar.WEEK_OF_YEAR)), 2, "0"));
			md.setYear(String.valueOf(ss.get(Calendar.YEAR)));
			md.setBillOrderNo(vm.getbillOrderNo());
			list.add(md);

		}
		stdao.saveAll(list);
		System.out.println("###################Schedular end for straza report###################################");
	}

	/********************************************************************************************
	 * OLD ARTICLE DATA IMPORT
	 ********************************************************************************************/
	// @Scheduled(cron = "0 50 09 * * ?")
	private void ArticleOldImport() throws SQLException {

		System.out.println("###################Schedular start for trans report###################################");

		List<ArticlesMasterModel> result = artservices.getAllArticleDetails();

		List<ArticlesMasterOldModel> list = new ArrayList<ArticlesMasterOldModel>();
		for (ArticlesMasterModel vm : result) {

			ArticlesMasterOldModel md = new ArticlesMasterOldModel();
			md.setArtbillastdelyrwk(vm.getArtbillastdelyrwk());
			md.setArtbrandcode(vm.getArtbrandcode());
			md.setArtcatgkey(vm.getArtcatgkey());
			md.setArtintroyrwk(vm.getArtintroyrwk());
			md.setArtname(vm.getArtname());
			md.setArtno(vm.getArtno());
			md.setArtpricefactory(vm.getArtpricefactory());
			md.setArtpricemrp(vm.getArtpricemrp());
			md.setArtpriceprev(vm.getArtpriceprev());
			md.setArtpricertl(vm.getArtpricertl());
			md.setArtpricewsl(vm.getArtpricewsl());
			md.setArtprodunit(vm.getArtprodunit());
			md.setArtrtlwsltype(vm.getArtrtlwsltype());
			md.setArtsequenceno(vm.getArtsequenceno());
			md.setArtstatecode(vm.getArtstatecode());
			md.setArttype(vm.getArttype());
			md.setArtuom(vm.getArtuom());
			md.setCgstpcnt(vm.getCgstpcnt());
			md.setGstpcnt(vm.getHsncode());
			md.setHsncode(vm.getHsncode());
			md.setHsncodedesc(vm.getHsncodedesc());
			md.setIgstpcnt(vm.getIgstpcnt());
			md.setSgstpcnt(vm.getSgstpcnt());
			md.setUomsh(vm.getUomsh());
			md.setUpdateDate(vm.getUpdateDate());
			md.setArtstndcost(vm.getArtstndcost());

			list.add(md);
		}
		atdao.saveAll(list);
		System.out.println("###################Schedular end for trans report###################################");
	}

}
