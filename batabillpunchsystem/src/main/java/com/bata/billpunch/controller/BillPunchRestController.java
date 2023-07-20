package com.bata.billpunch.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bata.billpunch.config.TokenRequest;
import com.bata.billpunch.config.TokenResponse;
import com.bata.billpunch.constant.ReraMessageConstants;
import com.bata.billpunch.constant.ResponseModel;
import com.bata.billpunch.dao.EdpReportDao;
import com.bata.billpunch.dao.GstReportDao;
import com.bata.billpunch.dao.RdcMappingDao;
import com.bata.billpunch.dao.StrazaReportDao;
import com.bata.billpunch.dao.SupplyReportDao;
import com.bata.billpunch.dao.TransReportDao;
import com.bata.billpunch.model.ApprovalDetailsModel;
import com.bata.billpunch.model.ArticlesMasterModel;
import com.bata.billpunch.model.BillPunchDetailsModel;
import com.bata.billpunch.model.BillPunchDetailsrequestModel;
import com.bata.billpunch.model.BillPunchEdpReportModel;
import com.bata.billpunch.model.BillPunchGstReportModel;
import com.bata.billpunch.model.BillPunchStrazaReportModel;
import com.bata.billpunch.model.BillPunchSupplyReportModel;
import com.bata.billpunch.model.BillPunchTransReportModel;
import com.bata.billpunch.model.PartiesMasterModel;
import com.bata.billpunch.model.RdcMappingModel;
import com.bata.billpunch.model.ShopMasterModel;
import com.bata.billpunch.model.StateMasterModel;
import com.bata.billpunch.model.StrazaPesponseDetailsModel;
import com.bata.billpunch.model.WeekMasterModel;
import com.bata.billpunch.model.dto.BillCloseStatusDto;
import com.bata.billpunch.model.dto.BillPunchCheckBoxDto;
import com.bata.billpunch.model.dto.BillPunchDto;
import com.bata.billpunch.model.dto.BillPunchListDto;
import com.bata.billpunch.model.dto.BillPunchResponse;
import com.bata.billpunch.model.dto.BillPunchResponseDto;
import com.bata.billpunch.model.dto.BillPunchResponseInterface;
import com.bata.billpunch.model.dto.BillPurchaseCostInterface;
import com.bata.billpunch.model.dto.ChartsInterface;
import com.bata.billpunch.model.dto.OrderPair;
import com.bata.billpunch.model.dto.PartyResponseDto;
import com.bata.billpunch.model.dto.PriceInterface;
import com.bata.billpunch.model.dto.PurchaseCostInterface;
import com.bata.billpunch.model.dto.RdcDetailsDto;
import com.bata.billpunch.model.dto.ReceivingLocDto;
import com.bata.billpunch.model.dto.StrazaBillPunchDto;
import com.bata.billpunch.model.dto.StrazaReqModel;
import com.bata.billpunch.service.impl.AdonisMasterServiceImpl;
import com.bata.billpunch.service.impl.AdonisWeekMasterServiceImpl;
import com.bata.billpunch.service.impl.ArticleMasterServiceImpl;
import com.bata.billpunch.service.impl.BillPunchMasterServicesImpl;
import com.bata.billpunch.service.impl.BillPunchServicesImpl;
import com.bata.billpunch.service.impl.DistMasterServiceImpl;
import com.bata.billpunch.service.impl.PartyMasterServiceImpl;
import com.bata.billpunch.service.impl.ShopMasterServiceImpl;
import com.bata.billpunch.service.impl.StateMasterServiceImpl;
import com.bata.billpunch.service.impl.WeekMasterServiceImpl;

@RestController
@RequestMapping("/bill-punch")
@CrossOrigin(origins = "*")
public class BillPunchRestController {
	InputStream is = BillPunchRestController.class.getResourceAsStream("songs.wav");
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
	private EdpReportDao edao;

	@Autowired
	private GstReportDao gdao;

	@Autowired
	private ArticleMasterServiceImpl artservices;

	@Autowired
	private AdonisMasterServiceImpl adservices;

	@Autowired
	private AdonisWeekMasterServiceImpl adwservice;

	@Autowired
	private DistMasterServiceImpl dservices;

	@Autowired
	private ArticleMasterServiceImpl aservices;

	@Autowired
	private ShopMasterServiceImpl sservices;

	@Autowired
	private PartyMasterServiceImpl pservices;

	@Autowired
	private StateMasterServiceImpl stservices;

	@Value("${TOKEN_URL}")
	private String tokenurl;

	/********************************************************************************************
	 * save billpunch details
	 ********************************************************************************************/
	@PostMapping("/save-details")
	public ResponseEntity<ResponseModel> saveBillPunchDetails(@RequestBody BillPunchDto entity,
			HttpServletRequest req) {
		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		// request.setToken(getToken(req));
		TokenResponse response = null;

		// response = restTemplate.postForEntity(tokenurl, request,
		// TokenResponse.class).getBody();
		// int myInt = 4;
		// String myIntString = Integer.toString(myInt);
		if ("True".contentEquals("True")) {

			List<ApprovalDetailsModel> jb = services.getRdcDetailsStatus(entity.getInvoiceNO(),
					entity.getBillOrderNo());
			if (jb.isEmpty()) {

				List<BillPunchDetailsModel> db = services.getByInvOrd(entity.getInvoiceNO(), entity.getBillOrderNo(),
						entity.getPartyCode());
				for (BillPunchDetailsModel pm : db) {
					if (Optional.ofNullable(pm).isPresent()) {
						ApprovalDetailsModel wp = new ApprovalDetailsModel();

						Calendar ss = pm.getCreatedOn();
						wp.setBataWeek(StringUtils.leftPad(String.valueOf(ss.get(Calendar.WEEK_OF_YEAR)), 2, "0"));
						// String.valueOf(ss.get(Calendar.WEEK_OF_YEAR));
						wp.setBataYear(String.valueOf(ss.get(Calendar.YEAR)));
						wp.setBillCloseStatus(pm.getBillCloseStatus());
						wp.setRdcCode(pm.getRdcCode());
						wp.setPurchaseCost(pm.getPurchaseCost());
						wp.setPairAmount(pm.getPairAmount());
						wp.setInvoiceCost(pm.getInvoiceCost());
						wp.setDiscountAmt(pm.getDiscountAmt());
						wp.setDiscountAmtVal(pm.getDiscountAmtVal());
						wp.setBillOrderDate(pm.getBillOrderDate());
						wp.setRecptInvDate(pm.getRecptInvDate());
						wp.setBillOrderNo(pm.getBillOrderNo());
						wp.setBillWeekYear(pm.getBillWeekYear());
						wp.setBillCloseWeek(pm.getBillCloseWeek());
						wp.setFreight(pm.getFreight());
						wp.setParty(pm.getParty());
						wp.setPartyCode(pm.getPartyCode());
						wp.setPartyName(pm.getPartyName());
						wp.setPair(pm.getPair());
						wp.setRdcPair(pm.getRdcPair());
						wp.setInvType(pm.getInvType());
						wp.setRegion(pm.getRegion());
						wp.setReceiveLoc(pm.getReceiveLoc());
						wp.setStateName(pm.getStateName());
						wp.setStateCode(pm.getStateCode());
						wp.setShopName(pm.getShopName());
						wp.setShopNo(pm.getShopNo());
						wp.setStatus(pm.getStatus());
						wp.setIgst(pm.getIgst());
						wp.setCgst(pm.getCgst());
						wp.setSgst(pm.getSgst());
						wp.setIgstamt(pm.getIgstamt());
						wp.setCgstamt(pm.getCgstamt());
						wp.setSgstamt(pm.getSgstamt());
						wp.setInvoiceNO(pm.getInvoiceNO());
						wp.setGrNo(pm.getGrNo());
						wp.setGrDate(pm.getGrDate());
						wp.setCnNo(pm.getCnNo());
						wp.setCnDate(pm.getCnDate());
						wp.setRdcPairC(pm.getRdcPairC());
						wp.setWeekYear(pm.getWeekYear());
						wp.setBillWeekYear(pm.getBillWeekYear());
						wp.setBillWeekDay(pm.getBillWeekDay());
						wp.setPackCaseB(pm.getPackCaseB());
						wp.setPackCaseB(pm.getPackCaseC());
						wp.setPackCaseS(pm.getPackCaseS());
						wp.setPackCaseM(pm.getPackCaseM());
						wp.setPackCaseT(pm.getPackCaseT());
						wp.setDcCode(pm.getDcCode());
						wp.setRegion(pm.getRegion());
						wp.setArticleCode(pm.getArticleCode());
						wp.setArticleName(pm.getArticleName());
						wp.setPairAmount(pm.getPairAmount());
						wp.setReceiveDate(pm.getReceiveDate());
						wp.setCreatedOn(pm.getCreatedOn());
						wp.setSizeCode(pm.getSizeCode());
						services.getApprovalDetails(wp);

					}

				}

			}

			List<BillPunchDetailsModel> cm = services.save(entity, response.getUsername());
			if (!cm.isEmpty()) {
				try {
					for (BillPunchDetailsModel pm : cm) {
						try {
							if (Optional.ofNullable(pm.getFormtype()).isPresent()
									&& pm.getFormtype().equalsIgnoreCase("manual")) {

								try {
									if (Optional.ofNullable(pm.getIgstamt()).isPresent() && pm.getIgstamt() != 0.0) {
										System.out.println("Igst amt value" + pm.getIgstamt());
										pm.setIgst(String
												.valueOf(Math.round((pm.getIgstamt() / pm.getInvoiceCost()) * 100)));

									} else {
										pm.setIgstamt(0.0);
									}

									if (Optional.ofNullable(pm.getCgstamt()).isPresent() && pm.getCgstamt() != 0.0) {
										System.out.println("cgst amt value" + pm.getCgstamt());

										pm.setCgst(String
												.valueOf(Math.round((pm.getCgstamt() / pm.getInvoiceCost()) * 100)));

									} else {
										pm.setCgstamt(0.0);
									}

									if (Optional.ofNullable(pm.getSgstamt()).isPresent() && pm.getSgstamt() != 0.0) {
										System.out.println("sgst amt value" + pm.getSgstamt());
										pm.setSgst(String
												.valueOf(Math.round((pm.getSgstamt() / pm.getInvoiceCost()) * 100)));

									}

									else {
										pm.setSgstamt(0.0);
									}
									pm.setGstamt(pm.getCgstamt() + pm.getSgstamt() + pm.getIgstamt());

								} catch (Exception e) {
									e.printStackTrace();
								}
								try {
									ShopMasterModel sh = sservices
											.getShopDetailsFormanual(StringUtils.leftPad(pm.getRdcCode(), 5, "0"));
									pm.setDistcode(Integer.parseInt(sh.getDistrictno()));
									pm.setDcCode(sh.getWhousecode());
								}

								catch (Exception e) {
									e.printStackTrace();
								}

								// PartiesMasterModel cv =
								// pservices.getPartiesDetails(StringUtils.leftPad(pm.getPartyCode().substring(2,
								// 5), 4, "0"));

								if (pm.getPartyCode().substring(0, 1).equalsIgnoreCase("9")) {
									pm.setShopName("CONTRACT");
									pm.setSizeCode(80);
								} else if (pm.getPartyCode().substring(0, 1).equalsIgnoreCase("8")) {
									pm.setShopName("NON-FOOTWEAR");
									pm.setSizeCode(950);
								} else if (pm.getPartyCode().substring(0, 1).equalsIgnoreCase("7")) {
									pm.setShopName("SANDAK");
									pm.setSizeCode(80);
								}
								// pm.setRdcCode(sservices.getrdcNoFormanual("%"+pm.getReceiveLoc()+"%").getShopno());

								pm.setInvType(1);
								pm.setParty(pm.getPartyCode().substring(2, 5));
								pm.setGrnDate(pm.getCnDate());
								pm.setBillWeekYear(pm.getBataYear());

								services.save(pm);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}

						try {
							ApprovalDetailsModel mn = new ApprovalDetailsModel();
							List<ApprovalDetailsModel> list1 = new ArrayList<>();

							mn.setBataWeek(pm.getBataWeek());
							mn.setBataYear(pm.getBataYear());
							mn.setBillUniqueCode(pm.getBillUniqueCode());

							mn.setFinUserStatus(pm.getStatus());
							if (Optional.ofNullable(response.getUsername()).isPresent()) {
								mn.setFinUserName(response.getUsername());
								mn.setUserRole(response.getUserrole());
							}
							mn.setBillCloseStatus(pm.getBillCloseStatus());
							mn.setCreditNote(pm.getCreditNote());
							mn.setRdcPairC(pm.getRdcPairC());
							mn.setPurchaseCost(pm.getPurchaseCost());
							mn.setInvoiceCost(pm.getInvoiceCost());
							mn.setDiscountAmt(pm.getDiscountAmt());
							mn.setDiscountAmtVal(pm.getDiscountAmtVal());
							mn.setTcsApplicable(pm.getTcsApplicable());
							mn.setTcsPercent(pm.getTcsPercent());
							mn.setTcsValue(pm.getTcsValue());
							mn.setBillOrderDate(pm.getBillOrderDate());
							mn.setBillOrderNo(pm.getBillOrderNo());
							mn.setBillWeekYear(pm.getBillWeekYear());
							mn.setShopName(pm.getShopName());
							mn.setShopNo(pm.getShopNo());
							mn.setCreatedOn(Calendar.getInstance());
							mn.setRecptInvDate(pm.getRecptInvDate());
							mn.setFreight(pm.getFreight());
							mn.setPair(pm.getPair());
							mn.setPartyCode(pm.getPartyCode());
							mn.setPartyName(pm.getPartyName());
							mn.setReceiveLoc(pm.getReceiveLoc());
							mn.setRegion(pm.getRegion());
							mn.setStateName(pm.getStateName());
							mn.setStateCode(pm.getStateCode());
							mn.setShopNo(pm.getShopNo());
							mn.setShopName(pm.getShopName());
							mn.setStatus(pm.getStatus());
							mn.setRdcCode(pm.getRdcCode());
							mn.setIgst(pm.getIgst());
							mn.setCgst(pm.getCgst());
							mn.setSgst(pm.getSgst());
							mn.setIgstamt(pm.getIgstamt());
							mn.setCgstamt(pm.getCgstamt());
							mn.setSgstamt(pm.getSgstamt());
							mn.setTotalCost(pm.getTotalCost());
							mn.setFormtype(pm.getFormtype());
							mn.setInvoiceNO(pm.getInvoiceNO());
							mn.setGrNo(pm.getGrNo());
							mn.setGrDate(pm.getGrDate());
							mn.setCnNo(pm.getCnNo());
							mn.setCnDate(pm.getCnDate());
							mn.setCreatedBy(pm.getCreatedBy());
							mn.setWeekYear(pm.getWeekYear());
							mn.setArticleCode(pm.getArticleCode());
							mn.setArticleName(pm.getArticleName());
							mn.setPairAmount(pm.getPairAmount());
							mn.setRdcPair(pm.getRdcPair());
							mn.setReceiveDate(pm.getReceiveDate());
							mn.setNoOfCartons(pm.getNoOfCartons());
							mn.setCreatedOn(pm.getCreatedOn());
							mn.setTotalCost(pm.getTotalCost());
							mn.setBillWeekYear(pm.getBillWeekYear());
							mn.setBillWeekDay(pm.getBillWeekDay());
							mn.setPackCaseB(pm.getPackCaseB());
							mn.setPackCaseB(pm.getPackCaseC());
							mn.setPackCaseS(pm.getPackCaseS());
							mn.setPackCaseM(pm.getPackCaseM());
							mn.setPackCaseT(pm.getPackCaseT());
							mn.setDcCode(pm.getDcCode());
							mn.setRegion(pm.getRegion());
							mn.setParty(pm.getParty());
							mn.setSizeCode(pm.getSizeCode());
							ApprovalDetailsModel xm = services.getApprovalDetails(mn);
							list1.add(xm);

						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
				rs.setMessage(ReraMessageConstants.SUCCESS_MESSAGE);
				rs.setData(cm);
			} else {
				rs.setData(null);
				rs.setStatus(ReraMessageConstants.FAILED_STATUS);
				rs.setMessage(ReraMessageConstants.FAILED_MESSAGE);

			}

		} else {
			rs.setData(null);
			rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
			rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);
		}
		return ResponseEntity.ok(rs);

	}

	/********************************************************************************************
	 * Find All bill punch data
	 ********************************************************************************************/
	@GetMapping("/getall-billpunch-details")
	public ResponseEntity<ResponseModel> getAll(HttpServletRequest req) {

		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		// request.setToken(getToken(req));
		// TokenResponse response = restTemplate.postForEntity(tokenurl, request,
		// TokenResponse.class).getBody();

		if ("True".contentEquals("True")) {
			List<BillPunchDetailsModel> cm = services.getAll();

			if (!cm.isEmpty()) {
				rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
				rs.setMessage(ReraMessageConstants.SUCCESS_MESSAGE);
				rs.setData(cm);
			} else {
				rs.setData(null);
				rs.setStatus(ReraMessageConstants.FAILED_STATUS);
				rs.setMessage(ReraMessageConstants.FAILED_MESSAGE);

			}
		} else {
			rs.setData(null);
			rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
			rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);
		}

		return ResponseEntity.ok(rs);

	}

	/********************************************************************************************
	 * Find All bill punch data
	 ********************************************************************************************/
	@GetMapping("/getall-billpunch-details-one")
	public ResponseEntity<ResponseModel> getAllOne(HttpServletRequest req) {

		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();

		if ("True".contentEquals("True")) {
			BillPunchDetailsModel cm = services.getAll().get(0);

			if (cm != null) {
				rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
				rs.setMessage(ReraMessageConstants.SUCCESS_MESSAGE);
				rs.setData(cm);
			} else {
				rs.setData(null);
				rs.setStatus(ReraMessageConstants.FAILED_STATUS);
				rs.setMessage(ReraMessageConstants.FAILED_MESSAGE);

			}
		} else {
			rs.setData(null);
			rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
			rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);
		}

		return ResponseEntity.ok(rs);

	}

	/********************************************************************************************
	 * Find All bill punch data based on Unique code
	 ********************************************************************************************/
	@SuppressWarnings("all")
	@GetMapping("/getall-details")
	public ResponseEntity<ResponseModel> getAllBillDetails(HttpServletRequest req) {

		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();

		if (response.getStatus().contentEquals("True")) {
			List<BillPunchDetailsModel> cm = services.getAll();

			Map<String, List<BillPunchDto>> map1 = new HashMap<>();
			List<BillPunchDto> list1 = new ArrayList<>();

			for (BillPunchDetailsModel pm : cm) {

				List<RdcDetailsDto> list11 = new ArrayList<>();
				RdcDetailsDto mn = new RdcDetailsDto();
				BillPunchDto entity = new BillPunchDto();
				if (Optional.ofNullable(pm.getBillUniqueCode()).isPresent()) {

					entity.setRdcList(list11);
					if (!list1.contains(pm.getBillOrderNo())) {
						entity.setBillCloseStatus(pm.getBillCloseStatus());
						entity.setPurchaseCost(pm.getPurchaseCost());
						entity.setFormtype(pm.getFormtype());
						entity.setBillOrderDate(pm.getBillOrderDate());
						entity.setBillOrderNo(pm.getBillOrderNo());
						entity.setBillWeekYear(pm.getBillWeekYear());
						entity.setcPair(pm.getPair());
						entity.setTotalCost(pm.getTotalCost());
						entity.setCreatedOn(Calendar.getInstance());
						entity.setFreight(pm.getFreight());
						entity.setStatus(pm.getStatus());
						entity.setShopName(pm.getShopName());
						entity.setShopNo(pm.getShopNo());
						entity.setPair(pm.getPair());
						entity.setStateCode(pm.getStateCode());
						entity.setIgst(pm.getIgst());
						entity.setCgst(pm.getCgst());
						entity.setSgst(pm.getSgst());
						entity.setPartyCode(pm.getPartyCode());
						entity.setPartyName(pm.getPartyName());
						entity.setRegion(pm.getRegion());
						entity.setStateName(pm.getStateName());
						entity.setStatus(pm.getStatus());
						entity.setInvoiceNO(pm.getInvoiceNO());
						entity.setGrNo(pm.getGrNo());
						entity.setGrDate(pm.getGrDate());
						entity.setCreditNote(pm.getCreditNote());

						list1.add(entity);
						mn.setRdcPair(pm.getRdcPair());
						mn.setRdcPairC(pm.getRdcPairC());
						mn.setWeekYear(pm.getWeekYear());
						mn.setArticleCode(pm.getArticleCode());
						mn.setPairAmount(pm.getPairAmount());
						mn.setReceiveDate(pm.getReceiveDate());
						list11.add(mn);
					} else {
						mn.setRdcPair(pm.getRdcPair());
						mn.setRdcPair(pm.getRdcPair());
						mn.setWeekYear(pm.getWeekYear());
						mn.setArticleCode(pm.getArticleCode());
						mn.setPairAmount(pm.getPairAmount());
						mn.setReceiveDate(pm.getReceiveDate());
						list11.add(mn);
					}

					map1.put(pm.getBillUniqueCode(), list1);
				}

			}
			if (!cm.isEmpty()) {
				rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
				rs.setMessage(ReraMessageConstants.SUCCESS_MESSAGE);
				rs.setData(list1);
			} else {
				rs.setData(null);
				rs.setStatus(ReraMessageConstants.FAILED_STATUS);
				rs.setMessage(ReraMessageConstants.FAILED_MESSAGE);

			}

		} else {
			rs.setData(null);
			rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
			rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);
		}

		return ResponseEntity.ok(rs);

	}

	/********************************************************************************************
	 * Find bill punch data by passing primarykey
	 ********************************************************************************************/
	@GetMapping("/getdetails-by-id/{id}")
	public ResponseEntity<ResponseModel> getDetailsById(@PathVariable(value = "id") Long id, HttpServletRequest req) {

		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();

		if (response.getStatus().contentEquals("True")) {
			BillPunchDetailsModel cm = services.getById(id);

			if (Optional.ofNullable(cm).isPresent()) {
				rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
				rs.setMessage(ReraMessageConstants.SUCCESS_MESSAGE);
				rs.setData(cm);
			} else {
				rs.setData(null);
				rs.setStatus(ReraMessageConstants.FAILED_STATUS);
				rs.setMessage(ReraMessageConstants.FAILED_MESSAGE);

			}
		} else {
			rs.setData(null);
			rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
			rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);
		}

		return ResponseEntity.ok(rs);

	}

	/********************************************************************************************
	 * Find All bill punch data by passing
	 ********************************************************************************************/
	@SuppressWarnings("all")
	@PostMapping("/get-details-by-billno-orderno-partycode")
	public ResponseEntity<ResponseModel> getDetailsByBillNo(@RequestBody BillPunchDetailsrequestModel em,
			HttpServletRequest req) {
		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();
		List<BillPunchResponseDto> list = new ArrayList<>();
		BillPurchaseCostInterface vm = null;

		if (response.getStatus().contentEquals("True")) {
			if (!Optional.ofNullable(em.getGrnFromDate()).isPresent()) {
				em.setGrnFromDate("2015-01-01");

			}
			if (!Optional.ofNullable(em.getGrnToDate()).isPresent()) {
				em.setGrnToDate("2040-01-01");
			}

			List<BillPunchResponseInterface> cm = mservices.getDetailsByBillNo(em.getInvoiceNO(), em.getPartyCode(),
					em.getBillOrderNo(), em.getStatus(), em.getGrnFromDate(), em.getGrnToDate(), em.getWeek(),
					em.getYear(), em.getReceiveLoc());
			if (Optional.ofNullable(cm).isPresent()) {
				for (BillPunchResponseInterface xm : cm) {

					BillPunchResponseDto m = new BillPunchResponseDto();
					m.setPairs(xm.getpair());
					m.setInvoiceNO(xm.getinvoiceNO());
					m.setBillOrderNo(xm.getbillOrderNo());
					m.setFormtype(xm.getformtype());
					m.setInvdate(xm.getinvdate());
					m.setGrNo(xm.getgrNo());
					m.setGrnDate(xm.getgrnDate());
					m.setCnNO(xm.getcnNo());
					m.setCnDate(xm.getcnDate());
					m.setBillWeek(xm.getbillWeek());
					m.setInvAmount(xm.getrdcAmount());
					m.setPartyCode(xm.getpartyCode());
					m.setPartyName(xm.getpartyName());
					m.setTcsApplicable(xm.gettcsApplicable());
					m.setRecLoc(xm.getrecLoc());
					m.setStatus(xm.getstatus());
					if (Optional.ofNullable(xm.gettcsPercent()).isPresent()) {
						String s1 = xm.gettcsPercent() + "%";
						m.setTcsPercent(s1);
					}
					if (Optional.ofNullable(xm.getdiscountAmt()).isPresent()) {
						String s = xm.getdiscountAmt() + "%";
						m.setDiscountAmt(s);
					}

					m.setPurchaseCost(xm.getpurchaseCost());
					m.setBillOrderDate(xm.getbillOrderDate());

					list.add(m);

				}

			} else {
				rs.setData(null);
				rs.setStatus(ReraMessageConstants.FAILED_STATUS);
				rs.setMessage(ReraMessageConstants.FAILED_MESSAGE);

			}

			rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
			rs.setMessage(ReraMessageConstants.SUCCESS_MESSAGE);
			rs.setData(list);

		} else

		{
			rs.setData(null);
			rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
			rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);
		}
		return ResponseEntity.ok(rs);
	}

	/********************************************************************************************
	 * Find All bill punch data by passing
	 ********************************************************************************************/
	@SuppressWarnings("all")
	@PostMapping("/get-list-populate-by-billno-orderno-partycode")
	public ResponseEntity<ResponseModel> getBillPunchDetailsForPopulate(@RequestBody BillPunchDetailsModel em,
			HttpServletRequest req) {
		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		// request.setToken(getToken(req));
		// TokenResponse response = restTemplate.postForEntity(tokenurl, request,
		// TokenResponse.class).getBody();

		if ("True".contentEquals("True")) {
			BillPurchaseCostInterface vm = null;
			PurchaseCostInterface ch = null;
			PurchaseCostInterface cs = null;
			List<BillPunchDetailsModel> cm = mservices.getDetailsByFilter(em.getInvoiceNO(), em.getPartyCode(),
					em.getBillOrderNo(), em.getBillUniqueCode(), em.getStatus());
			if (Optional.ofNullable(cm).isPresent()) {
				List<BillPunchListDto> list1 = new ArrayList<>();
				List<RdcDetailsDto> list11 = new ArrayList<>();
				Map<String, List<BillPunchListDto>> map1 = new HashMap<>();
				BillPunchListDto entity = new BillPunchListDto();
				Double dd = 0d;
				Double d1 = 0d;
				Double d2 = 0d;
				for (BillPunchDetailsModel pm : cm) {
					RdcDetailsDto mn = new RdcDetailsDto();

					if (Optional.ofNullable(pm.getIgstamt()).isPresent() && pm.getFormtype() == null) {
						Double ss = pm.getIgstamt();
						dd += ss;
					} else {
						dd = pm.getIgstamt();
					}
					if (Optional.ofNullable(pm.getCgstamt()).isPresent() && pm.getFormtype() == null) {
						Double s1 = pm.getCgstamt();
						d1 += s1;
					} else {
						d1 = pm.getCgstamt();
					}
					if (Optional.ofNullable(pm.getSgstamt()).isPresent() && pm.getFormtype() == null) {
						Double s2 = pm.getSgstamt();
						d2 += s2;
					} else {
						d2 = pm.getSgstamt();
					}

					entity.setIgstamt(dd);
					entity.setCgstamt(d1);
					entity.setSgstamt(d2);

					if (!map1.containsKey(pm.getBillOrderNo())) {
						entity.setBillId(pm.getBilId());
						entity.setBillCloseStatus(pm.getBillCloseStatus());
						entity.setShopName(pm.getShopName());
						entity.setShopNo(pm.getRdcCode());
						entity.setBillCloseWeek(pm.getBillCloseWeek());
						if (pm.getFormtype() == null) {
							try {
								List<BillPunchResponseInterface> sm = mservices
										.getDetailsByFilterRdcList(pm.getInvoiceNO(), pm.getBillOrderNo());

								Double amt = 0d;
								for (BillPunchResponseInterface pr : sm) {

									ch = mservices.getPurchaseCostFromOrder(pm.getBillOrderNo(), pm.getPartyCode(),
											pm.getRdcCode(), pr.getarticleCode());

									if (Optional.ofNullable(ch).isPresent()) {
										Double mt = Double.parseDouble(ch.getpurchaseCost())
												* Integer.parseInt(pr.getpair());
										amt += mt;
									} else {

										List<RdcMappingModel> xn = rdcmapdao.findByMergerdccode(pm.getRdcCode());

										for (RdcMappingModel bm : xn) {
											ch = mservices.getPurchaseCostFromOrder(pm.getBillOrderNo(),
													pm.getPartyCode(), bm.getRdcno(), pr.getarticleCode());
											if (Optional.ofNullable(ch).isPresent()) {
												Double mt = Double.parseDouble(ch.getpurchaseCost())
														* Integer.parseInt(pr.getpair());
												amt += mt;
											}

										}

									}

								}
								entity.setPurchaseCost(amt);
							} catch (Exception e) {
								e.printStackTrace();
							}
						} else {
							if (Optional.ofNullable(pm.getPurchaseCost()).isPresent()) {
								entity.setPurchaseCost(pm.getPurchaseCost());
							} else {

								try {
									List<BillPunchResponseInterface> sm = mservices
											.getDetailsByFilterRdcList(pm.getInvoiceNO(), pm.getBillOrderNo());

									Double amt = 0d;
									for (BillPunchResponseInterface pr : sm) {

										ch = mservices.getPurchaseCostFromOrder(pm.getBillOrderNo(), pm.getPartyCode(),
												pm.getRdcCode(), pr.getarticleCode());

										if (Optional.ofNullable(ch).isPresent()) {
											Double mt = Double.parseDouble(ch.getpurchaseCost())
													* Integer.parseInt(pr.getpair());
											amt += mt;
										} else {

											List<RdcMappingModel> xn = rdcmapdao.findByMergerdccode(pm.getRdcCode());

											for (RdcMappingModel bm : xn) {
												ch = mservices.getPurchaseCostFromOrder(pm.getBillOrderNo(),
														pm.getPartyCode(), bm.getRdcno(), pr.getarticleCode());
												if (Optional.ofNullable(ch).isPresent()) {
													Double mt = Double.parseDouble(ch.getpurchaseCost())
															* Integer.parseInt(pr.getpair());
													amt += mt;
												}

											}

										}

									}
									entity.setPurchaseCost(amt);
								} catch (Exception e) {
									e.printStackTrace();
								}

							}

						}
						List<BillPunchResponseInterface> dt = mservices.getAllAtrnoAndOrdnoAndInvNoDetailsForList(
								pm.getPartyCode(), pm.getBillOrderNo(), pm.getInvoiceNO());
						entity.setBillUniqueCode(pm.getBillUniqueCode());
						entity.setBillOrderDate(dt.get(0).getbillOrderDateOne());

						entity.setBillOrderNo(pm.getBillOrderNo());
						entity.setBillWeekYear(pm.getBillWeekYear());
						entity.setFormtype(pm.getFormtype());
						entity.setStateCode(pm.getStateCode());
						entity.setTotalCost(pm.getTotalCost());
						entity.setInvoiceCost(pm.getInvoiceCost());
						entity.setStatus(pm.getStatus());
						entity.setReceiveLoc(pm.getReceiveLoc());
						entity.setIgst(pm.getIgst());
						entity.setCgst(pm.getCgst());
						entity.setSgst(pm.getSgst());
						/*
						 * entity.setIgstamt(pm.getIgstamt()); entity.setCgstamt(pm.getCgstamt());
						 * entity.setSgstamt(pm.getSgstamt());
						 */
						entity.setCreatedOn(Calendar.getInstance());
						entity.setFreight(pm.getFreight());
						entity.setPair(pm.getPair());
						entity.setPartyCode(pm.getPartyCode());
						entity.setPartyName(pm.getPartyName());
						entity.setRegion(pm.getRegion());
						entity.setStateName(pm.getStateName());
						entity.setInvoiceNO(pm.getInvoiceNO());
						entity.setRecptInvDate(dt.get(0).getrecptInvDate());
						entity.setGrNo(pm.getGrNo());
						entity.setGrDate(dt.get(0).getgrDate());
						entity.setGrnDate(dt.get(0).getgrDate());
						entity.setCnNo(pm.getCnNo());
						entity.setCnDate(dt.get(0).getcnDate());
						entity.setCreditNote(pm.getCreditNote());
						entity.setDiscountAmt(pm.getDiscountAmt());
						entity.setDiscountAmtVal(pm.getDiscountAmtVal());
						entity.setTcsValue(pm.getTcsValue());
						entity.setTcsApplicable(pm.getTcsApplicable());
						entity.setTcsPercent(pm.getTcsPercent());
						list1.add(entity);
						mn.setIgst(pm.getIgst());
						mn.setCgst(pm.getCgst());
						mn.setSgst(pm.getSgst());
						mn.setRdcPairC(pm.getRdcPairC());
						mn.setBillId(pm.getBilId());
						mn.setRdcPair(pm.getRdcPair());
						mn.setWeekYear(pm.getWeekYear());
						mn.setArticleCode(pm.getArticleCode());
						mn.setArticleName(pm.getArticleName());
						mn.setPairAmount(pm.getPairAmount());

						if ("manual".equalsIgnoreCase(pm.getFormtype())) {
							cs = mservices.getDateFromOrder(pm.getBillOrderNo(), pm.getPartyCode(), pm.getRdcCode(),
									pm.getArticleCode());
							if (!Optional.ofNullable(cs).isPresent()) {
								List<RdcMappingModel> xn = rdcmapdao.findByMergerdccode(pm.getRdcCode());

								for (RdcMappingModel bm : xn) {
									cs = mservices.getDateFromOrder(pm.getBillOrderNo(), pm.getPartyCode(),
											bm.getRdcno(), pm.getArticleCode());
									if (Optional.ofNullable(cs).isPresent()) {
										break;
									}

								}

							}

							if (Optional.ofNullable(cs).isPresent()) {
								mn.setReceiveDate(cs.getreceiveDate());
							}

						} else {
							cs = mservices.getDateFromOrder(pm.getBillOrderNo(), pm.getPartyCode(), pm.getRdcCode(),
									pm.getArticleCode());
							if (!Optional.ofNullable(cs).isPresent()) {
								List<RdcMappingModel> xn = rdcmapdao.findByMergerdccode(pm.getRdcCode());
								for (RdcMappingModel bm : xn) {

									cs = mservices.getDateFromOrder(pm.getBillOrderNo(), pm.getPartyCode(),
											bm.getRdcno(), pm.getArticleCode());
									if (Optional.ofNullable(cs).isPresent()) {
										break;
									}

								}

							}
							if (Optional.ofNullable(cs).isPresent()) {
								mn.setReceiveDate(cs.getreceiveDate());
							}

						}

						mn.setNoOfCartons(pm.getNoOfCartons());
						if (Optional.ofNullable(mn.getArticleCode()).isPresent()
								|| Optional.ofNullable(mn.getReceiveDate()).isPresent()
								|| Optional.ofNullable(mn.getPairAmount()).isPresent()
								|| Optional.ofNullable(mn.getRdcPair()).isPresent()
								|| Optional.ofNullable(mn.getWeekYear()).isPresent()) {
							list11.add(mn);
						}

						map1.put(pm.getBillOrderNo(), list1);
					} else {
						mn.setIgst(pm.getIgst());
						mn.setCgst(pm.getCgst());
						mn.setSgst(pm.getSgst());
						mn.setRdcPairC(pm.getRdcPairC());
						mn.setBillId(pm.getBilId());
						mn.setRdcPair(pm.getRdcPair());
						mn.setWeekYear(pm.getWeekYear());
						mn.setArticleCode(pm.getArticleCode());
						mn.setArticleName(pm.getArticleName());
						mn.setPairAmount(pm.getPairAmount());
						if (Optional.ofNullable(cs).isPresent()) {
							mn.setReceiveDate(cs.getreceiveDate());
						}

						mn.setNoOfCartons(pm.getNoOfCartons());
						if (Optional.ofNullable(mn.getArticleCode()).isPresent()
								|| Optional.ofNullable(mn.getReceiveDate()).isPresent()
								|| Optional.ofNullable(mn.getPairAmount()).isPresent()
								|| Optional.ofNullable(mn.getRdcPair()).isPresent()
								|| Optional.ofNullable(mn.getWeekYear()).isPresent()) {
							list11.add(mn);
						}

					}
					entity.setRdcList(list11);

				}

				rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
				rs.setMessage(ReraMessageConstants.SUCCESS_MESSAGE);
				rs.setData(entity);
			} else {
				rs.setData(null);
				rs.setStatus(ReraMessageConstants.FAILED_STATUS);
				rs.setMessage(ReraMessageConstants.FAILED_MESSAGE);

			}
		} else {
			rs.setData(null);
			rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
			rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);
		}
		return ResponseEntity.ok(rs);
	}

	public String getToken(HttpServletRequest req) {

		final String requestTokenHeader = req.getHeader("Authorization");
		String jwtToken = null;
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {

			jwtToken = requestTokenHeader.substring(7);
		}
		return jwtToken;
	}

	/********************************************************************************************
	 * Approval details bill punch reports.
	 ********************************************************************************************/
	@SuppressWarnings("all")
	@PostMapping("/action-approval-bill-punch-details")
	public ResponseEntity<ResponseModel> getAllApprovalDetails(HttpServletRequest req,
			@RequestBody List<ApprovalDetailsModel> app) {
		BillPunchDetailsModel pm = null;
		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();

		if (response.getStatus().contentEquals("True")) {
			List<BillPunchDetailsModel> list = new ArrayList<>();
			List<ApprovalDetailsModel> list1 = new ArrayList<>();
			String week = null;
			try {
				List<WeekMasterModel> wk = wservice.getAllWeek();
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

			for (ApprovalDetailsModel entity : app) {

				if (Optional.ofNullable(entity).isPresent()) {
					List<BillPunchDetailsModel> vm = services.getByInvOrdApproval(entity.getInvoiceNO(),
							entity.getBillOrderNo(), entity.getPartyCode());

					for (BillPunchDetailsModel model : vm) {
						/*
						 * List<BillPunchDetailsModel> xm=services.getByInvparty(model.getInvoiceNO(),
						 * model.getPartyCode()); if(!xm.isEmpty()) { try { throw new
						 * ResourceAccessException(" Please first Submit for the remaining "+model.
						 * getInvoiceNO()+" and "+model.getPartyCode()+" records."); } catch (Exception
						 * e) { e.printStackTrace(); }
						 * 
						 * 
						 * }else {
						 */

						if (Optional.ofNullable(entity.getApproverStatus()).isPresent()) {
							model.setStatus(entity.getApproverStatus());
						} else if (Optional.ofNullable(entity.getFinUserStatus()).isPresent()) {
							model.setStatus(entity.getFinUserStatus());
						} else if (Optional.ofNullable(entity.getAdminStatus()).isPresent()) {
							model.setStatus(entity.getAdminStatus());
						}
						model.setBataWeek(week);
						model.setBataYear(yr);
						model.setCreatedOn(Calendar.getInstance());
						pm = services.save(model);
						list.add(pm);
						ApprovalDetailsModel cm = new ApprovalDetailsModel();
						cm.setBataWeek(pm.getBataWeek());
						cm.setBataYear(pm.getBataYear());
						cm.setBillUniqueCode(entity.getBillUniqueCode());
						cm.setRemarks(entity.getRemarks());
						if (Optional.ofNullable(entity.getApproverStatus()).isPresent()) {
							cm.setApproverStatus(entity.getApproverStatus());
						} else if (Optional.ofNullable(entity.getFinUserStatus()).isPresent()) {
							cm.setApproverStatus(entity.getFinUserStatus());
						} else if (Optional.ofNullable(entity.getAdminStatus()).isPresent()) {
							cm.setAdminStatus(entity.getAdminStatus());
						}
						if (Optional.ofNullable(response.getUsername()).isPresent()) {
							cm.setApproverName(response.getUsername());
							cm.setUserRole(response.getUserrole());
						}
						cm.setBillCloseStatus(pm.getBillCloseStatus());
						cm.setCreditNote(pm.getCreditNote());
						cm.setRdcPairC(pm.getRdcPairC());
						cm.setPurchaseCost(pm.getPurchaseCost());
						cm.setInvoiceCost(pm.getInvoiceCost());
						cm.setDiscountAmt(pm.getDiscountAmt());
						cm.setDiscountAmtVal(pm.getDiscountAmtVal());
						cm.setTcsApplicable(pm.getTcsApplicable());
						cm.setTcsPercent(pm.getTcsPercent());
						cm.setTcsValue(pm.getTcsValue());
						cm.setBillOrderDate(pm.getBillOrderDate());
						cm.setBillOrderNo(pm.getBillOrderNo());
						cm.setBillWeekYear(pm.getBillWeekYear());
						cm.setShopName(pm.getShopName());
						cm.setShopNo(pm.getShopNo());
						cm.setCreatedOn(Calendar.getInstance());
						cm.setRecptInvDate(pm.getRecptInvDate());
						cm.setFreight(pm.getFreight());
						cm.setPair(pm.getPair());
						cm.setPartyCode(pm.getPartyCode());
						cm.setPartyName(pm.getPartyName());
						cm.setReceiveLoc(pm.getReceiveLoc());
						cm.setRegion(pm.getRegion());
						cm.setStateName(pm.getStateName());
						cm.setStateCode(pm.getStateCode());
						cm.setShopName(pm.getShopName());
						cm.setShopNo(pm.getShopNo());
						cm.setRdcCode(pm.getRdcCode());
						cm.setStatus(pm.getStatus());
						cm.setBataWeek(pm.getBataWeek());
						cm.setBataYear(pm.getBataYear());
						// entity.setGstamt(Double.valueOf(pm.getIgst()+pm.getCgst()+pm.getSgst()));
						cm.setIgst(pm.getIgst());
						cm.setCgst(pm.getCgst());
						cm.setSgst(pm.getSgst());
						cm.setIgstamt(pm.getIgstamt());
						cm.setCgstamt(pm.getCgstamt());
						cm.setSgstamt(pm.getSgstamt());
						cm.setTotalCost(pm.getTotalCost());
						cm.setFormtype(pm.getFormtype());
						cm.setInvoiceNO(pm.getInvoiceNO());
						cm.setGrNo(pm.getGrNo());
						cm.setGrDate(pm.getGrDate());
						cm.setCnNo(pm.getCnNo());
						cm.setCnDate(pm.getCnDate());
						cm.setCreatedBy(pm.getCreatedBy());
						cm.setWeekYear(pm.getWeekYear());
						cm.setArticleCode(pm.getArticleCode());
						cm.setArticleName(pm.getArticleName());
						cm.setPairAmount(pm.getPairAmount());
						cm.setRdcPair(pm.getRdcPair());
						cm.setReceiveDate(pm.getReceiveDate());
						cm.setNoOfCartons(pm.getNoOfCartons());
						cm.setCreatedOn(pm.getCreatedOn());
						cm.setTotalCost(pm.getTotalCost());
						cm.setParty(pm.getParty());
						cm.setSizeCode(pm.getSizeCode());
						ApprovalDetailsModel xmm = services.getApprovalDetails(cm);
						list1.add(xmm);

					}

				}

			}

			// }

			if (!list.isEmpty()) {
				rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
				rs.setMessage(ReraMessageConstants.SUCCESS_MESSAGE);
				rs.setData(list);

			} else {
				rs.setData(null);
				rs.setStatus(ReraMessageConstants.FAILED_STATUS);
				rs.setMessage(ReraMessageConstants.FAILED_MESSAGE);
			}

		} else {
			rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
			rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);
		}
		return ResponseEntity.ok(rs);

	}

	/********************************************************************************************
	 * Update billpunch details by check box
	 ********************************************************************************************/
	@PostMapping("/update-billpunch-details-by-checkbox")
	public ResponseEntity<ResponseModel> updateBillPunchDetailsByCheckBox(@RequestBody List<BillPunchResponse> entity,
			HttpServletRequest req) {
		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = null;
		response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();

		if (response.getStatus().contentEquals("True")) {
			for (BillPunchResponse rb : entity) {
				List<ApprovalDetailsModel> jb = services.getRdcDetailsStatus(rb.getInvoiceNO(), rb.getBillOrderNo());
				if (jb.isEmpty()) {
					List<BillPunchDetailsModel> db = services.getByInvOrd(rb.getInvoiceNO(), rb.getBillOrderNo(),
							rb.getPartyCode());
					for (BillPunchDetailsModel pm : db) {
						if (Optional.ofNullable(pm).isPresent()) {
							ApprovalDetailsModel wp = new ApprovalDetailsModel();
							Calendar ss = pm.getCreatedOn();
							wp.setBataWeek(StringUtils.leftPad("53", 2, "0"));
							wp.setBataYear(String.valueOf(ss.get(Calendar.YEAR)));
							wp.setBillCloseStatus(pm.getBillCloseStatus());
							wp.setRdcCode(pm.getRdcCode());
							wp.setPurchaseCost(pm.getPurchaseCost());
							wp.setPairAmount(pm.getPairAmount());
							wp.setInvoiceCost(pm.getInvoiceCost());
							wp.setDiscountAmt(pm.getDiscountAmt());
							wp.setDiscountAmtVal(pm.getDiscountAmtVal());
							wp.setBillOrderDate(pm.getBillOrderDate());
							wp.setRecptInvDate(pm.getRecptInvDate());
							wp.setBillOrderNo(pm.getBillOrderNo());
							wp.setBillWeekYear(pm.getBillWeekYear());
							wp.setBillCloseWeek(pm.getBillCloseWeek());
							wp.setFreight(pm.getFreight());
							wp.setPartyCode(pm.getPartyCode());
							wp.setPartyName(pm.getPartyName());
							wp.setPair(pm.getPair());
							wp.setRegion(pm.getRegion());
							wp.setReceiveLoc(pm.getReceiveLoc());
							wp.setStateName(pm.getStateName());
							wp.setStateCode(pm.getStateCode());
							wp.setShopName(pm.getShopName());
							wp.setShopNo(pm.getShopNo());
							wp.setStatus(pm.getStatus());
							wp.setIgst(pm.getIgst());
							wp.setCgst(pm.getCgst());
							wp.setSgst(pm.getSgst());
							wp.setIgstamt(pm.getIgstamt());
							wp.setCgstamt(pm.getCgstamt());
							wp.setSgstamt(pm.getSgstamt());
							wp.setInvoiceNO(pm.getInvoiceNO());
							wp.setGrNo(pm.getGrNo());
							wp.setGrDate(pm.getGrDate());
							wp.setCnNo(pm.getCnNo());
							wp.setCnDate(pm.getCnDate());
							wp.setRdcPairC(pm.getRdcPairC());
							wp.setWeekYear(pm.getWeekYear());
							wp.setBillWeekYear(pm.getBillWeekYear());
							wp.setBillWeekDay(pm.getBillWeekDay());
							wp.setPackCaseB(pm.getPackCaseB());
							wp.setPackCaseB(pm.getPackCaseC());
							wp.setPackCaseS(pm.getPackCaseS());
							wp.setPackCaseM(pm.getPackCaseM());
							wp.setPackCaseT(pm.getPackCaseT());
							wp.setDcCode(pm.getDcCode());
							wp.setRegion(pm.getRegion());
							wp.setArticleCode(pm.getArticleCode());
							wp.setArticleName(pm.getArticleName());
							wp.setPairAmount(pm.getPairAmount());
							wp.setReceiveDate(pm.getReceiveDate());
							wp.setCreatedOn(pm.getCreatedOn());
							wp.setParty(pm.getParty());
							wp.setSizeCode(pm.getSizeCode());
							services.getApprovalDetails(wp);

						}

					}
				}
			}

			BillPunchCheckBoxDto cm = mservices.updateByCheckBox(entity, response.getUsername());
			if (!cm.getListdata().isEmpty()) {
				for (BillPunchDetailsModel pm : cm.getListdata()) {

					try {
						ApprovalDetailsModel mn = new ApprovalDetailsModel();
						List<ApprovalDetailsModel> list1 = new ArrayList<>();
						mn.setBataWeek(pm.getBataWeek());
						mn.setBataYear(pm.getBataYear());
						mn.setBillUniqueCode(pm.getBillUniqueCode());

						mn.setFinUserStatus(pm.getStatus());
						if (Optional.ofNullable(response.getUsername()).isPresent()) {
							mn.setFinUserName(response.getUsername());
							mn.setUserRole(response.getUserrole());
						}
						mn.setBillCloseStatus(pm.getBillCloseStatus());
						mn.setCreditNote(pm.getCreditNote());
						mn.setRdcPairC(pm.getRdcPairC());
						mn.setPurchaseCost(pm.getPurchaseCost());
						mn.setInvoiceCost(pm.getInvoiceCost());
						mn.setDiscountAmt(pm.getDiscountAmt());
						mn.setDiscountAmtVal(pm.getDiscountAmtVal());
						mn.setTcsApplicable(pm.getTcsApplicable());
						mn.setTcsPercent(pm.getTcsPercent());
						mn.setTcsValue(pm.getTcsValue());
						mn.setBillOrderDate(pm.getBillOrderDate());
						mn.setBillOrderNo(pm.getBillOrderNo());
						mn.setBillWeekYear(pm.getBillWeekYear());
						mn.setShopName(pm.getShopName());
						mn.setShopNo(pm.getShopNo());
						mn.setCreatedOn(Calendar.getInstance());
						mn.setRecptInvDate(pm.getRecptInvDate());
						mn.setFreight(pm.getFreight());
						mn.setPair(pm.getPair());
						mn.setPartyCode(pm.getPartyCode());
						mn.setPartyName(pm.getPartyName());
						mn.setReceiveLoc(pm.getReceiveLoc());
						mn.setRegion(pm.getRegion());
						mn.setStateName(pm.getStateName());
						mn.setStateCode(pm.getStateCode());
						mn.setShopName(pm.getShopName());
						mn.setShopNo(pm.getShopNo());
						mn.setRdcCode(pm.getRdcCode());
						mn.setStatus(pm.getStatus());
						// entity.setGstamt(Double.valueOf(pm.getIgst()+pm.getCgst()+pm.getSgst()));
						mn.setIgst(pm.getIgst());
						mn.setCgst(pm.getCgst());
						mn.setSgst(pm.getSgst());
						mn.setIgstamt(pm.getIgstamt());
						mn.setCgstamt(pm.getCgstamt());
						mn.setSgstamt(pm.getSgstamt());
						mn.setTotalCost(pm.getTotalCost());
						mn.setFormtype(pm.getFormtype());
						mn.setInvoiceNO(pm.getInvoiceNO());
						mn.setGrNo(pm.getGrNo());
						mn.setGrDate(pm.getGrDate());
						mn.setCnNo(pm.getCnNo());
						mn.setCnDate(pm.getCnDate());
						mn.setCreatedBy(pm.getCreatedBy());
						mn.setWeekYear(pm.getWeekYear());
						mn.setArticleCode(pm.getArticleCode());
						mn.setArticleName(pm.getArticleName());
						mn.setPairAmount(pm.getPairAmount());
						mn.setRdcPair(pm.getRdcPair());
						mn.setReceiveDate(pm.getReceiveDate());
						mn.setNoOfCartons(pm.getNoOfCartons());
						mn.setCreatedOn(pm.getCreatedOn());
						mn.setTotalCost(pm.getTotalCost());
						mn.setParty(pm.getParty());
						mn.setSizeCode(pm.getSizeCode());
						ApprovalDetailsModel xm = services.getApprovalDetails(mn);
						list1.add(xm);

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
				rs.setMessage(cm.getMessage());
				rs.setData(cm);
			} else {
				rs.setData(null);
				rs.setMessage(ReraMessageConstants.FAILED_MESSAGE);

			}

		} else {
			rs.setData(null);
			rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
			rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);
		}
		return ResponseEntity.ok(rs);

	}

	/********************************************************************************************
	 * Find All bill punch RDC data
	 ********************************************************************************************/
	@GetMapping("/getall")
	public ResponseEntity<ResponseModel> getAllBillDetailsFromRdc(HttpServletRequest req) {

		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();
		if (response.getStatus().contentEquals("True")) {

			List<StrazaBillPunchDto> cm = services.getAllRdcDetails();

			if (!cm.isEmpty()) {
				rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
				rs.setMessage(ReraMessageConstants.SUCCESS_MESSAGE);
				rs.setData(cm);
			} else {
				rs.setData(null);
				rs.setStatus(ReraMessageConstants.FAILED_STATUS);
				rs.setMessage(ReraMessageConstants.FAILED_MESSAGE);

			}

		} else {
			rs.setData(null);
			rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
			rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);
		}

		return ResponseEntity.ok(rs);

	}

	/********************************************************************************************
	 * Find All bill punch partycode and partyname data
	 ********************************************************************************************/
	@GetMapping("/getall-partycode-partyname/{constant}")
	public ResponseEntity<ResponseModel> getAllPartycodeAndPartyName(@PathVariable("constant") String constant,
			HttpServletRequest req) {

		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();

		if (response.getStatus().contentEquals("True")) {
			List<PartyResponseDto> cm = services.getAllPartycodeAndPartyName(constant);

			if (!cm.isEmpty()) {
				rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
				rs.setMessage(ReraMessageConstants.SUCCESS_MESSAGE);
				rs.setData(cm);
			} else {
				rs.setData(null);
				rs.setStatus(ReraMessageConstants.FAILED_STATUS);
				rs.setMessage(ReraMessageConstants.FAILED_MESSAGE);

			}

		} else {
			rs.setData(null);
			rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
			rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);
		}

		return ResponseEntity.ok(rs);

	}

	/********************************************************************************************
	 * Find All bill punch partycode and partyname data
	 ********************************************************************************************/
	@GetMapping("/getdetails-by-partycode-partyname/{partycode}/{partyname}")
	public ResponseEntity<ResponseModel> getAllPartycodeAndPartyNameDetails(HttpServletRequest req,
			@PathVariable("partycode") String partycode, @PathVariable("partyname") String partyname) {

		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();

		if (response.getStatus().contentEquals("True")) {
			List<BillPunchDetailsModel> cm = services.getAllPartycodeAndPartyNameDetails(partycode, partyname);

			if (!cm.isEmpty()) {
				rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
				rs.setMessage(ReraMessageConstants.SUCCESS_MESSAGE);
				rs.setData(cm);
			} else {
				rs.setData(null);
				rs.setStatus(ReraMessageConstants.FAILED_STATUS);
				rs.setMessage(ReraMessageConstants.FAILED_MESSAGE);

			}

		} else {
			rs.setData(null);
			rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
			rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);
		}

		return ResponseEntity.ok(rs);

	}

	/********************************************************************************************
	 * Find All bill punch partycode and partyname ,year and week
	 ********************************************************************************************/
	@PostMapping("/getdetails-for-straza-report")
	public ResponseEntity<ResponseModel> getDetailsStrazaReport(@RequestBody StrazaReqModel model,
			HttpServletRequest req) {

		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();
		List<StrazaPesponseDetailsModel> list = new ArrayList<>();
		PriceInterface ch = null;
		if (response.getStatus().contentEquals("True")) {
			List<StrazaBillPunchDto> cm = services.getDetailsStrazaReport(model.getPartycode(), model.getFromwk(),
					model.getTowk(), model.getYr(), model.getConstant());
			try {
				for (StrazaBillPunchDto xm : cm) {
					StrazaPesponseDetailsModel vm = new StrazaPesponseDetailsModel();

					vm.setGrnoOne(xm.getgrnoOne());
					vm.setGrdateNew(xm.getgrdateNew());
					vm.setPartyName(xm.getpartyName());
					vm.setBillOrderNo(xm.getbillOrderNo());
					vm.setPartyCode(xm.getpartyCode());
					vm.setPartyName(xm.getpartyName());
					vm.setRecptInvDate(xm.getrecptInvDate());
					vm.setInvoiceNO(xm.getinvoiceNO());
					vm.setGrNo(xm.getgrNo());
					vm.setReceiveWk(xm.getreceiveWk());
					vm.setBookWeek(xm.getbookWeek());
					vm.setFreight(xm.getfreight());
					vm.setTcsValue(xm.gettcsValue());
					vm.setBillWeek(xm.getreceiveWk());
					vm.setReceiveLoc(xm.getreceiveLoc());
					vm.setPair(xm.getpair());
					vm.setYear(xm.getyear());
					vm.setTotalCost(xm.gettotalCost());
					vm.setIgstamt(xm.getigstamt());
					vm.setCgstamt(xm.getcgstamt());
					vm.setSgstamt(xm.getsgstamt());
					vm.setShopName(xm.getshopName());
					vm.setInvoiceCost(xm.getinvoiceCost());
					vm.setGstamt(xm.getgstamt());
					vm.setPurchaseCost(xm.getpurchaseCost());
					vm.setGrnDate(xm.getgrDate());
					vm.setBillOrderDate(xm.getbillOrderDate());
					vm.setGrDate(xm.getgrDate());
					vm.setShopNo(xm.getshopNo());
					vm.setMrp(xm.getmrp());
					vm.setStdcost(xm.getstdcost());

					list.add(vm);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (!list.isEmpty()) {
				rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
				rs.setMessage(ReraMessageConstants.SUCCESS_MESSAGE);
				rs.setData(list);
			} else {
				rs.setData(null);
				rs.setStatus(ReraMessageConstants.FAILED_STATUS);
				rs.setMessage(ReraMessageConstants.FAILED_MESSAGE);

			}

		} else {
			rs.setData(null);
			rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
			rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);
		}

		return ResponseEntity.ok(rs);

	}

	/********************************************************************************************
	 * Find current week status
	 ********************************************************************************************/

	@GetMapping("/get-all-bill-close-week")
	public ResponseEntity<ResponseModel> getBillCloseWeek(HttpServletRequest req) {

		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();

		if (response.getStatus().contentEquals("True")) {

			List<BillCloseStatusDto> cm = services.getBillCloseWeek();

			if (!cm.isEmpty()) {
				rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
				rs.setMessage(ReraMessageConstants.SUCCESS_MESSAGE);
				rs.setData(cm);
			} else {
				rs.setData(null);
				rs.setStatus(ReraMessageConstants.FAILED_STATUS);
				rs.setMessage(ReraMessageConstants.FAILED_MESSAGE);

			}

		} else {
			rs.setData(null);
			rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
			rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);
		}

		return ResponseEntity.ok(rs);

	}

	/********************************************************************************************
	 * Find current week status
	 ********************************************************************************************/

	@GetMapping("/get-approved-records-for-final-close/{billcloseweek}")
	public ResponseEntity<ResponseModel> getCurrentWeekStatus(HttpServletRequest req,
			@PathVariable("billcloseweek") String billcloseweek) {

		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();

		if (response.getStatus().contentEquals("True")) {

			List<BillCloseStatusDto> cm = services.getApprovedRecords(billcloseweek);

			if (!cm.isEmpty()) {
				rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
				rs.setMessage(ReraMessageConstants.SUCCESS_MESSAGE);
				rs.setData(cm);
			} else {
				rs.setData(null);
				rs.setStatus(ReraMessageConstants.FAILED_STATUS);
				rs.setMessage(ReraMessageConstants.FAILED_MESSAGE);

			}

		} else {
			rs.setData(null);
			rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
			rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);
		}

		return ResponseEntity.ok(rs);

	}

	/********************************************************************************************
	 * update closed bill status
	 * 
	 * @throws SQLException
	 ********************************************************************************************/

	@PostMapping("/update-approved-records-for-final-close")
	public ResponseEntity<ResponseModel> updateRecordsFinalClose(HttpServletRequest req,
			@RequestBody List<BillPunchDto> entity) throws SQLException {

		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();

		if (response.getStatus().contentEquals("True")) {

			List<BillPunchDetailsModel> cm = services.updateRecords(entity);

			for (BillPunchDetailsModel pm : cm) {

				try {
					ApprovalDetailsModel mn = new ApprovalDetailsModel();
					List<ApprovalDetailsModel> list1 = new ArrayList<>();
					mn.setBataWeek(pm.getBataWeek());
					mn.setBataYear(pm.getBataYear());
					mn.setBillUniqueCode(pm.getBillUniqueCode());

					mn.setFinUserStatus(pm.getStatus());
					if (Optional.ofNullable(response.getUsername()).isPresent()) {
						mn.setFinUserName(response.getUsername());
						mn.setUserRole(response.getUserrole());
					}
					mn.setBillCloseStatus(pm.getBillCloseStatus());
					mn.setCreditNote(pm.getCreditNote());
					mn.setRdcPairC(pm.getRdcPairC());
					mn.setPurchaseCost(pm.getPurchaseCost());
					mn.setInvoiceCost(pm.getInvoiceCost());
					mn.setDiscountAmt(pm.getDiscountAmt());
					mn.setDiscountAmtVal(pm.getDiscountAmtVal());
					mn.setTcsApplicable(pm.getTcsApplicable());
					mn.setTcsPercent(pm.getTcsPercent());
					mn.setTcsValue(pm.getTcsValue());
					mn.setBillOrderDate(pm.getBillOrderDate());
					mn.setBillOrderNo(pm.getBillOrderNo());
					mn.setBillWeekYear(pm.getBillWeekYear());
					mn.setShopName(pm.getShopName());
					mn.setShopNo(pm.getShopNo());
					mn.setCreatedOn(Calendar.getInstance());
					mn.setRecptInvDate(pm.getRecptInvDate());
					mn.setFreight(pm.getFreight());
					mn.setPair(pm.getPair());
					mn.setPartyCode(pm.getPartyCode());
					mn.setPartyName(pm.getPartyName());
					mn.setReceiveLoc(pm.getReceiveLoc());
					mn.setRegion(pm.getRegion());
					mn.setStateName(pm.getStateName());
					mn.setStateCode(pm.getStateCode());
					mn.setShopName(pm.getShopName());
					mn.setShopNo(pm.getShopNo());
					mn.setStatus(pm.getStatus());
					mn.setRdcCode(pm.getRdcCode());
					// entity.setGstamt(Double.valueOf(pm.getIgst()+pm.getCgst()+pm.getSgst()));
					mn.setIgst(pm.getIgst());
					mn.setCgst(pm.getCgst());
					mn.setSgst(pm.getSgst());
					mn.setIgstamt(pm.getIgstamt());
					mn.setCgstamt(pm.getCgstamt());
					mn.setSgstamt(pm.getSgstamt());
					mn.setTotalCost(pm.getTotalCost());
					mn.setFormtype(pm.getFormtype());
					mn.setInvoiceNO(pm.getInvoiceNO());
					mn.setGrNo(pm.getGrNo());
					mn.setGrDate(pm.getGrDate());
					mn.setCnNo(pm.getCnNo());
					mn.setCnDate(pm.getCnDate());
					mn.setCreatedBy(pm.getCreatedBy());
					mn.setWeekYear(pm.getWeekYear());
					mn.setArticleCode(pm.getArticleCode());
					mn.setArticleName(pm.getArticleName());
					mn.setPairAmount(pm.getPairAmount());
					mn.setRdcPair(pm.getRdcPair());
					mn.setReceiveDate(pm.getReceiveDate());
					mn.setNoOfCartons(pm.getNoOfCartons());
					mn.setCreatedOn(pm.getCreatedOn());
					mn.setTotalCost(pm.getTotalCost());
					mn.setParty(pm.getParty());
					mn.setSizeCode(pm.getSizeCode());
					ApprovalDetailsModel xm = services.getApprovalDetails(mn);
					list1.add(xm);

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			if (!cm.isEmpty()) {
				// writeDataLinesSupplyReport(cm.get(0).getBataWeek(), cm.get(0).getBataYear());
				// writeDataLinesTransReport(cm.get(0).getBataWeek(), cm.get(0).getBataYear());
				// writeDataLinesEdpReport(cm.get(0).getBataWeek(), cm.get(0).getBataYear());
				// writeDataLinesGstReport(cm.get(0).getBataWeek(), cm.get(0).getBataYear());

				rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
				rs.setMessage(ReraMessageConstants.SUCCESS_MESSAGE_ONE);
				rs.setData(cm);
			} else {
				rs.setData(null);
				rs.setStatus(ReraMessageConstants.FAILED_STATUS);
				rs.setMessage(ReraMessageConstants.FAILED_MESSAGE);

			}

		} else {
			rs.setData(null);
			rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
			rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);
		}

		return ResponseEntity.ok(rs);

	}

	/********************************************************************************************
	 * Find All bill punch data which are submitted and accepted
	 ********************************************************************************************/
	@PostMapping("/get-all-details-by-status")
	public ResponseEntity<ResponseModel> getAllDetailsByStatus(@RequestBody BillPunchDetailsrequestModel em,
			HttpServletRequest req) {
		List<BillPunchDetailsModel> ls = new ArrayList<>();
		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();

		if (response.getStatus().contentEquals("True")) {

			if (!Optional.ofNullable(em.getGrnFromDate()).isPresent()) {
				em.setGrnFromDate("2015-01-01");

			}
			if (!Optional.ofNullable(em.getGrnToDate()).isPresent()) {
				// Calendar cal = Calendar.getInstance();
				// SimpleDateFormat d11 = new SimpleDateFormat("yyyy-MM-dd");
				// em.setGrnToDate(d11.format(cal.getTime()));
				em.setGrnToDate("2040-01-01");
			}
			/*
			 * List<BillPunchDetailsModel> cm =
			 * services.getAllDetailsByStatus(em.getInvoiceNO(), em.getPartyCode(),
			 * em.getBillOrderNo(), em.getStatus(), em.getGrnFromDate(), em.getGrnToDate(),
			 * em.getWeek(), em.getYear(), em.getReceiveLoc());
			 */

			List<BillPunchResponseInterface> cm = mservices.getDetailsForApprovalPage(em.getInvoiceNO(),
					em.getPartyCode(), em.getBillOrderNo(), em.getBillUniqueCode(), em.getStatus(), em.getGrnFromDate(),
					em.getGrnToDate(), em.getWeek(), em.getYear(), em.getReceiveLoc());

			/*
			 * Map<String, List<BillPunchDetailsModel>> map1 = new HashMap<>();
			 * List<BillPunchDetailsModel> list1 = new ArrayList<>(); for
			 * (BillPunchDetailsModel xm : cm) {
			 * 
			 * if (!map1.isEmpty()) { if (!map1.containsKey(xm.getBillUniqueCode())) {
			 * map1.put(xm.getBillUniqueCode(), cm); list1.add(xm); } } else {
			 * map1.put(xm.getBillUniqueCode(), cm); list1.add(xm);
			 * 
			 * }
			 * 
			 * }
			 */
			if (!cm.isEmpty()) {
				rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
				rs.setMessage(ReraMessageConstants.SUCCESS_MESSAGE);
				rs.setData(cm);
			} else {
				rs.setData(null);
				rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
				rs.setMessage(ReraMessageConstants.SUCCESS_MESSAGE);
				rs.setData(ls);

			}

		} else {
			rs.setData(null);
			rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
			rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);
		}

		return ResponseEntity.ok(rs);

	}

	/********************************************************************************************
	 * Find All bill punch artno and ordno data fetch
	 ********************************************************************************************/
	@GetMapping("/get-pair-details-by-atrno-ordno/{atrno}/{ordno}/{invno}")
	public ResponseEntity<ResponseModel> getAllArtNoAndOrdNoDetails(HttpServletRequest req,
			@PathVariable("atrno") String atrno, @PathVariable("ordno") String ordno,
			@PathVariable("invno") String invno) {

		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();

		if (response.getStatus().contentEquals("True")) {
			OrderPair cm = services.getAllAtrnoAndOrdnoDetails(atrno, ordno, invno);

			if (Optional.ofNullable(cm).isPresent()) {
				rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
				rs.setMessage(ReraMessageConstants.SUCCESS_MESSAGE);
				rs.setData(cm);
			} else {
				rs.setData(null);
				rs.setStatus(ReraMessageConstants.FAILED_STATUS);
				rs.setMessage(ReraMessageConstants.FAILED_MESSAGE);

			}

		} else {
			rs.setData(null);
			rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
			rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);
		}

		return ResponseEntity.ok(rs);

	}

	/********************************************************************************************
	 * Find price by passing artno data
	 ********************************************************************************************/
	@GetMapping("/get-price-details-by-atrno/{atrno}/{orderno}")
	public ResponseEntity<ResponseModel> getPriceDetailsByArtno(HttpServletRequest req,
			@PathVariable("atrno") String atrno, @PathVariable("orderno") String orderno) {

		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();
		List<PriceInterface> list = new ArrayList<PriceInterface>();
		if (response.getStatus().contentEquals("True")) {
			PriceInterface cm = services.getPriceDetailsByartno(atrno, orderno);

			if (Optional.ofNullable(cm).isPresent()) {
				rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
				rs.setMessage(ReraMessageConstants.SUCCESS_MESSAGE);
				rs.setData(cm);
			} else {
				rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
				rs.setMessage(ReraMessageConstants.SUCCESS_MESSAGE);
				rs.setData(list);

			}

		} else {
			rs.setData(null);
			rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
			rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);
		}

		return ResponseEntity.ok(rs);

	}

	/********************************************************************************************
	 * Find All bill punch partycode and partyname data
	 ********************************************************************************************/
	@GetMapping("/getall-receiving-locations")
	public ResponseEntity<ResponseModel> getAllReceivingLocationName(HttpServletRequest req) {

		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();

		if (response.getStatus().contentEquals("True")) {
			List<ReceivingLocDto> cm = services.getAllReceivingLoc();

			if (!cm.isEmpty()) {
				rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
				rs.setMessage(ReraMessageConstants.SUCCESS_MESSAGE);
				rs.setData(cm);
			} else {
				rs.setData(null);
				rs.setStatus(ReraMessageConstants.FAILED_STATUS);
				rs.setMessage(ReraMessageConstants.FAILED_MESSAGE);

			}

		} else {
			rs.setData(null);
			rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
			rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);
		}

		return ResponseEntity.ok(rs);

	}

	/********************************************************************************************
	 * Find All bill punch partycode and partyname data
	 ********************************************************************************************/
	@GetMapping("/getall-receiving-locations-for-manual")
	public ResponseEntity<ResponseModel> getAllReceivingLocationNameForManual(HttpServletRequest req) {

		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();

		if (response.getStatus().contentEquals("True")) {
			List<ReceivingLocDto> cm = services.getAllReceivingLocManual();

			if (!cm.isEmpty()) {
				rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
				rs.setMessage(ReraMessageConstants.SUCCESS_MESSAGE);
				rs.setData(cm);
			} else {
				rs.setData(null);
				rs.setStatus(ReraMessageConstants.FAILED_STATUS);
				rs.setMessage(ReraMessageConstants.FAILED_MESSAGE);

			}

		} else {
			rs.setData(null);
			rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
			rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);
		}

		return ResponseEntity.ok(rs);

	}

	/********************************************************************************************
	 * Find All bill punch data by passing filter
	 ********************************************************************************************/
	@SuppressWarnings("all")
	@PostMapping("/get-details-by-billno-orderno-partycode-for-history")
	public ResponseEntity<ResponseModel> getDetailsFromHistory(@RequestBody BillPunchDetailsrequestModel em,
			HttpServletRequest req) {
		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();
		List<BillPunchResponseDto> list = new ArrayList<>();

		if (response.getStatus().contentEquals("True")) {
			if (!Optional.ofNullable(em.getGrnFromDate()).isPresent()) {
				em.setGrnFromDate("2015-01-01");

			}
			if (!Optional.ofNullable(em.getGrnToDate()).isPresent()) {
				em.setGrnToDate("2040-01-01");
			}

			List<BillPunchResponseInterface> cm = mservices.getDetailsByFilter(em.getInvoiceNO(), em.getPartyCode(),
					em.getBillOrderNo(), em.getBillUniqueCode(), em.getStatus(), em.getGrnFromDate(), em.getGrnToDate(),
					em.getWeek(), em.getYear(), em.getReceiveLoc());
			if (Optional.ofNullable(cm).isPresent()) {
				for (BillPunchResponseInterface xm : cm) {

					BillPunchResponseDto m = new BillPunchResponseDto();
					m.setInvoiceNO(xm.getinvoiceNO());
					m.setBillOrderNo(xm.getbillOrderNo());
					list.add(m);

				}

			} else {
				rs.setData(null);
				rs.setStatus(ReraMessageConstants.FAILED_STATUS);
				rs.setMessage(ReraMessageConstants.FAILED_MESSAGE);

			}

			rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
			rs.setMessage(ReraMessageConstants.SUCCESS_MESSAGE);
			rs.setData(list);

		} else

		{
			rs.setData(null);
			rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
			rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);
		}
		return ResponseEntity.ok(rs);
	}

	/********************************************************************************************
	 * Find All bill punch data by passing
	 ********************************************************************************************/
	@SuppressWarnings("all")
	@PostMapping("/get-history-list-populate-by-billno")
	public ResponseEntity<ResponseModel> getBillPunchDetailsForHistory(@RequestBody BillPunchDetailsModel em,
			HttpServletRequest req) {
		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		// request.setToken(getToken(req));
		// TokenResponse response = restTemplate.postForEntity(tokenurl, request,
		// TokenResponse.class).getBody();

		if ("True".contentEquals("True")) {
			BillPurchaseCostInterface vm = null;
			PurchaseCostInterface ch = null;
			PurchaseCostInterface cs = null;
			List<ApprovalDetailsModel> cm = mservices.getDetailsByFilterHistory(em.getInvoiceNO(), em.getPartyCode(),
					em.getBillOrderNo(), em.getBillUniqueCode(), em.getStatus(), em.getReceiveLoc());
			if (Optional.ofNullable(cm).isPresent()) {
				List<BillPunchDto> list1 = new ArrayList<>();
				List<RdcDetailsDto> list11 = new ArrayList<>();
				Map<String, List<BillPunchDto>> map1 = new HashMap<>();
				BillPunchDto entity = new BillPunchDto();
				for (ApprovalDetailsModel pm : cm) {
					RdcDetailsDto mn = new RdcDetailsDto();
					entity.setIgstamt(pm.getIgstamt());
					entity.setCgstamt(pm.getCgstamt());
					entity.setSgstamt(pm.getSgstamt());

					if (!map1.containsKey(pm.getBillOrderNo())) {
						// entity.setBillId(pm.getBilId());
						entity.setBillCloseStatus(pm.getBillCloseStatus());
						entity.setShopName(pm.getShopName());
						entity.setShopNo(pm.getRdcCode());
						entity.setBillCloseWeek(pm.getBillCloseWeek());
						entity.setPurchaseCost(pm.getPurchaseCost());
						entity.setBillUniqueCode(pm.getBillUniqueCode());
						entity.setBillOrderDate(pm.getBillOrderDate());
						entity.setBillOrderNo(pm.getBillOrderNo());
						entity.setBillWeekYear(pm.getBillWeekYear());
						entity.setFormtype(pm.getFormtype());
						entity.setStateCode(pm.getStateCode());
						entity.setTotalCost(pm.getTotalCost());
						entity.setInvoiceCost(pm.getInvoiceCost());
						entity.setStatus(pm.getStatus());
						entity.setReceiveLoc(pm.getReceiveLoc());
						entity.setIgst(pm.getIgst());
						entity.setCgst(pm.getCgst());
						entity.setSgst(pm.getSgst());
						entity.setCreatedOn(pm.getCreatedOn());
						entity.setFreight(pm.getFreight());
						entity.setPair(pm.getPair());
						entity.setPartyCode(pm.getPartyCode());
						entity.setPartyName(pm.getPartyName());
						entity.setRegion(pm.getRegion());
						entity.setStateName(pm.getStateName());
						entity.setInvoiceNO(pm.getInvoiceNO());
						entity.setRecptInvDate(pm.getRecptInvDate());
						entity.setGrNo(pm.getGrNo());
						entity.setGrDate(pm.getGrDate());
						entity.setGrnDate(pm.getGrDate());
						entity.setCnNo(pm.getCnNo());
						entity.setCnDate(pm.getCnDate());
						entity.setCreditNote(pm.getCreditNote());
						entity.setDiscountAmt(pm.getDiscountAmt());
						entity.setDiscountAmtVal(pm.getDiscountAmtVal());
						entity.setTcsValue(pm.getTcsValue());
						entity.setTcsApplicable(pm.getTcsApplicable());
						entity.setTcsPercent(pm.getTcsPercent());
						list1.add(entity);
						mn.setIgst(pm.getIgst());
						mn.setCgst(pm.getCgst());
						mn.setSgst(pm.getSgst());
						mn.setRdcPairC(pm.getRdcPairC());
						// mn.setBillId(pm.getBilId());
						mn.setRdcPair(pm.getRdcPair());
						mn.setWeekYear(pm.getWeekYear());
						mn.setArticleCode(pm.getArticleCode());
						mn.setArticleName(pm.getArticleName());
						mn.setPairAmount(pm.getPairAmount());
						mn.setReceiveDate(pm.getReceiveDate());
						mn.setNoOfCartons(pm.getNoOfCartons());
						if (Optional.ofNullable(mn.getArticleCode()).isPresent()
								|| Optional.ofNullable(mn.getReceiveDate()).isPresent()
								|| Optional.ofNullable(mn.getPairAmount()).isPresent()
								|| Optional.ofNullable(mn.getRdcPair()).isPresent()
								|| Optional.ofNullable(mn.getWeekYear()).isPresent()) {
							list11.add(mn);
						}

						map1.put(pm.getBillOrderNo(), list1);
					} else {
						mn.setIgst(pm.getIgst());
						mn.setCgst(pm.getCgst());
						mn.setSgst(pm.getSgst());
						mn.setRdcPairC(pm.getRdcPairC());
						// mn.setBillId(pm.getBilId());
						mn.setRdcPair(pm.getRdcPair());
						mn.setWeekYear(pm.getWeekYear());
						mn.setArticleCode(pm.getArticleCode());
						mn.setArticleName(pm.getArticleName());
						mn.setPairAmount(pm.getPairAmount());
						if (Optional.ofNullable(cs).isPresent()) {
							mn.setReceiveDate(cs.getreceiveDate());
						}

						mn.setNoOfCartons(pm.getNoOfCartons());
						if (Optional.ofNullable(mn.getArticleCode()).isPresent()
								|| Optional.ofNullable(mn.getReceiveDate()).isPresent()
								|| Optional.ofNullable(mn.getPairAmount()).isPresent()
								|| Optional.ofNullable(mn.getRdcPair()).isPresent()
								|| Optional.ofNullable(mn.getWeekYear()).isPresent()) {
							list11.add(mn);
						}

					}
					entity.setRdcList(list11);

				}

				rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
				rs.setMessage(ReraMessageConstants.SUCCESS_MESSAGE);
				rs.setData(entity);
			} else {
				rs.setData(null);
				rs.setStatus(ReraMessageConstants.FAILED_STATUS);
				rs.setMessage(ReraMessageConstants.FAILED_MESSAGE);

			}
		} else {
			rs.setData(null);
			rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
			rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);
		}
		return ResponseEntity.ok(rs);

	}

	/********************************************************************************************
	 * Find All bill punch data by passing
	 ********************************************************************************************/
	@SuppressWarnings("all")
	@PostMapping("/get-history-rows-by-billno")
	public ResponseEntity<ResponseModel> getBillPunchDetailsForHistoryRaws(@RequestBody BillPunchDetailsModel em,
			HttpServletRequest req) {
		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();

		if (response.getStatus().contentEquals("True")) {
			List<BillPunchResponseInterface> cm = mservices.getDetailsByFilterHistoryRows(em.getInvoiceNO(),
					em.getBillOrderNo());
			if (Optional.ofNullable(cm).isPresent()) {
				rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
				rs.setMessage(ReraMessageConstants.SUCCESS_MESSAGE);
				rs.setData(cm);
			} else {
				rs.setData(null);
				rs.setStatus(ReraMessageConstants.FAILED_STATUS);
				rs.setMessage(ReraMessageConstants.FAILED_MESSAGE);

			}
		} else {
			rs.setData(null);
			rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
			rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);
		}
		return ResponseEntity.ok(rs);

	}

	@PostMapping("/getvalidate-by-partycode-party")
	public ResponseEntity<ResponseModel> getvalidatepartyAndPartyCode(@RequestBody BillPunchDetailsModel em,
			HttpServletRequest req) {
		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();

		if (response.getStatus().contentEquals("True")) {
			List<BillPunchResponseInterface> cm = services.getvalidatepartyCodeAndParty(em.getInvoiceNO(),
					em.getBillOrderNo());

			if (cm.get(0).getpartyCode().substring(2, 5).equalsIgnoreCase(cm.get(0).getparty())) {
				rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
				rs.setMessage("ok");
			} else {
				rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
				rs.setMessage("Please check Partycode and Partyno are not match.");
				services.getUpdateByInvOrd(cm.get(0).getinvoiceNO(), cm.get(0).getbillOrderNo(),
						cm.get(0).getpartyCode());

			}
		} else {
			rs.setData(null);
			rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
			rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);
		}
		return ResponseEntity.ok(rs);

	}

	/********************************************************************************************
	 * SUPPLY REPORT
	 ********************************************************************************************/
	// @Scheduled(cron = "0 09 00 * * ?")
	// private void writeDataLinesSupplyReport() throws SQLException {
	// private void writeDataLinesSupplyReport(String wk, String yr) throws
	// SQLException {

	// @GetMapping("/import-data-for-supply-report/{wk}/{yr}")
	private void writeDataLinesSupplyReport(@PathVariable("wk") String wk, @PathVariable("yr") String yr,
			HttpServletRequest req) throws SQLException {
		System.out.println("###################Schedular start for supply report###################################");
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = null;
		response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();

		if (response.getStatus().contentEquals("True")) {

			List<BillPunchResponseInterface> result = services.findAllApprovedDetailsForSupplyReport(wk, yr);

			List<BillPunchSupplyReportModel> list = new ArrayList<BillPunchSupplyReportModel>();

			Double amt = 0d;

			Double artamt = 0d;
			Double mrpamt = 0d;
			for (BillPunchResponseInterface sb : result) {
				String s = "0";

				String s1 = "0";
				BillPunchDetailsModel xm = services
						.getAllAtrnoAndOrdnoDetailsForGst("%", sb.getbillOrderNo(), sb.getinvoiceNO()).get(0);

				BillPunchResponseInterface fx = services.getAllAtrnoAndOrdnoDetailsForSupply(sb.getbillOrderNo(),
						sb.getinvoiceNO());

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
								ch = services.getDetailsOrdnoDetails(xm.getBillOrderNo(), xm.getPartyCode(),
										bm.getRdcno(), pr.getarticleCode());

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

				String date22 = "NA";
				try {
					if (Optional.ofNullable(xm.getGrDate()).isPresent()) {
						Date d11 = xm.getGrDate();
						date22 = dateFormat.format(d11);
					}

				} catch (Exception e) {
				}

				Date d2 = xm.getGrnDate();
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
				md.setTolocationCode(xm.getRdcCode());
				md.setRecLocName(xm.getReceiveLoc());
				md.setDept(xm.getShopName());
				md.setRegion(xm.getRegion());
				md.setInvoiceNo(xm.getInvoiceNO());
				md.setInvoiceDate(date1);
				md.setGrnNo(xm.getGrNo());
				md.setGrnDate(date22);
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
				md.setWeek(wk);
				md.setYear(yr);
				list.add(md);

			}
			splydao.saveAll(list);
			System.out.println("###################Schedular end for supply report###################################");
		}
	}

	/********************************************************************************************
	 * TRANS REPORT
	 ********************************************************************************************/
	// @Scheduled(cron = "0 09 20 * * ?")
	// private void writeDataLinesTransReport() throws SQLException {
	// private void writeDataLinesTransReport(String wk, String yr) throws
	// SQLException {

	// @GetMapping("/import-data-for-trans-report/{wk}/{yr}")
	private void writeDataLinesTransReport(@PathVariable("wk") String wk, @PathVariable("yr") String yr,
			HttpServletRequest req) throws SQLException {

		System.out.println("###################Schedular start for trans report###################################");
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = null;
		response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();

		if (response.getStatus().contentEquals("True")) {

			List<BillPunchResponseInterface> result = mservices.getReportDetailsByWeekTrans(wk, yr);

			List<BillPunchTransReportModel> list = new ArrayList<BillPunchTransReportModel>();
			String amt = null;
			for (BillPunchResponseInterface vm : result) {
				BillPunchResponseInterface pr = mservices.getReportTotalPairDetailsTrans(vm.getinvoiceNO(),
						vm.getpartyCode(), vm.getrdcCode(), wk);

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
				try {
					recdate = dateFormats.format(vm.getrecptInvDateone());
				} catch (Exception e) {
				}

				BillPunchResponseInterface art = aservices.getArticleDetailsReport(String.valueOf(vm.getarticleCode()));

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

				md.setStateCode(sh.getStatecode());
				md.setInvAmt(amt);
				md.setGrNo(vm.getgrNo());
				md.setRecDate(recdate);
				md.setResumeInvNO(vm.getresumeInvNo());
				md.setDateOne(date.substring(0, 2));
				md.setDateTwo(date.substring(3, 5));
				md.setDateThree(date.substring(6, 10));
				md.setResumeInvNOTwo(vm.getresumeInvNoTwo());
				md.setBillOrdNo(vm.getbillOrderNo());
				md.setBillOrdNoOne(vm.getbillOrderNo());
				md.setArtCost(String
						.valueOf(Integer.valueOf((int) (art.getartstdCost() * 100 * Integer.valueOf(vm.getpair())))));
				md.setCgst(m11);
				md.setCgstamt(m1);
				md.setSgst(m23);
				md.setSgstamt(m2);
				md.setIgst(m33);
				md.setIgstamt(m3);
				md.setWeek(wk);
				md.setYear(yr);
				list.add(md);
			}
			transdao.saveAll(list);
			System.out.println("###################Schedular end for trans report###################################");
		}
	}

	/********************************************************************************************
	 * EDP REPORT
	 ********************************************************************************************/
	// @Scheduled(cron = "0 12 20 * * ?")
	// private void writeDataLinesEdpReport() throws SQLException {
	// private void writeDataLinesEdpReport(String wk, String yr) throws
	// SQLException {

	// @GetMapping("/import-data-for-edp-report/{wk}/{yr}")
	private void writeDataLinesEdpReport(@PathVariable("wk") String wk, @PathVariable("yr") String yr,
			HttpServletRequest req) throws SQLException {
		System.out.println("###################Schedular start for edp report###################################");
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = null;
		response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();

		if (response.getStatus().contentEquals("True")) {

			List<BillPunchResponseInterface> result = mservices.getReportDetailsByWeekEdp(wk, yr);

			List<BillPunchEdpReportModel> list = new ArrayList<BillPunchEdpReportModel>();

			String amtnew = null;
			String amt = null;

			for (BillPunchResponseInterface vm : result) {

				BillPunchResponseInterface art = aservices.getArticleDetailsedpReport(vm.getinvoiceNO(),
						vm.getpartyCode(), vm.getgrNo());
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
				md.setWeek(wk);
				md.setYear(yr);
				md.setBillOrdNo(vm.getbillOrderNo());
				list.add(md);

			}
			edao.saveAll(list);
			System.out.println("###################Schedular end for epd report###################################");
		}
	}

	/********************************************************************************************
	 * GST REPORT
	 ********************************************************************************************/
	// @Scheduled(cron = "0 15 00 * * ?")
	// private void writeDataLinesGstReport() throws SQLException {
	// private void writeDataLinesGstReport(String wk, String yr) throws
	// SQLException {

	// @GetMapping("/import-data-for-gst-report/{wk}/{yr}")
	private void writeDataLinesGstReport(@PathVariable("wk") String wk, @PathVariable("yr") String yr,
			HttpServletRequest req) throws SQLException {
		System.out.println("###################Schedular start for gst report###################################");
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = null;
		response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();

		if (response.getStatus().contentEquals("True")) {
			List<BillPunchResponseInterface> result = services.findAllApprovedDetailsForGst(wk, yr);

			List<BillPunchGstReportModel> list = new ArrayList<BillPunchGstReportModel>();

			for (BillPunchResponseInterface pm : result) {
				List<BillPunchDetailsModel> xm = services.getAllAtrnoAndOrdnoDetailsForGst(pm.getarticleCode(),
						pm.getbillOrderNo(), pm.getinvoiceNO());
				ShopMasterModel sh = null;

				StateMasterModel st = null;

				StateMasterModel stt = null;

				PartiesMasterModel pt = null;

				ArticlesMasterModel art = null;
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

					art = artservices.getArticleDetails(xm.get(0).getArticleCode());
					if (Optional.ofNullable(art).isPresent()) {

					} else {
						art = new ArticlesMasterModel();
						art.setHsncode("");
						art.setArtuom("");
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
					md.setWeek(wk);
					md.setYear(yr);
					list.add(md);

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
			gdao.saveAll(list);
			System.out
					.println("###################Schedular stendart for gst report###################################");
		}
	}

	/********************************************************************************************
	 * STRAZA REPORT
	 ********************************************************************************************/

	// @GetMapping("/import-data-for-straza-report/{wk}/{yr}")
	private void writeDataLinesStrazaReport(@PathVariable("wk") String wk, @PathVariable("yr") String yr,
			HttpServletRequest req) throws SQLException {

		System.out.println("###################Schedular start for straza report###################################");
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = null;
		response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();

		if (response.getStatus().contentEquals("True")) {

			List<StrazaBillPunchDto> result = mservices.findWithDetailsStrazaReportAllrecords(wk, yr);

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
								art = services.getDetailsOrdnoDetails(vm.getbillOrderNo(), vm.getpartyCode(),
										bm.getRdcno(), pr.getarticleCode());

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
				md.setBookWeek(wk);
				md.setYear(yr);
				md.setBillOrderNo(vm.getbillOrderNo());
				list.add(md);

			}
			stdao.saveAll(list);
			System.out.println("###################Schedular end for straza report###################################");

		}
	}

	/********************************************************************************************
	 * Find All bill punch partycode and partyname data
	 ********************************************************************************************/
	@PutMapping("/update-records-to-invalid")
	public ResponseEntity<ResponseModel> updateInvalidRecords(@RequestBody BillPunchDto entity,
			HttpServletRequest req) {

		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();

		if (response.getStatus().contentEquals("True")) {
			List<BillPunchDetailsModel> cm = services.getallRdcRecords(entity.getInvoiceNO(), entity.getBillOrderNo());

			if (!cm.isEmpty()) {
				rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
				rs.setMessage(ReraMessageConstants.SUCCESS_MESSAGE);
				rs.setData(cm);
			} else {
				rs.setData(null);
				rs.setStatus(ReraMessageConstants.FAILED_STATUS);
				rs.setMessage(ReraMessageConstants.FAILED_MESSAGE);

			}

		} else {
			rs.setData(null);
			rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
			rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);
		}

		return ResponseEntity.ok(rs);

	}

	/********************************************************************************************
	 * save billpunch details
	 * 
	 * @throws Exception
	 ********************************************************************************************/
	@PostMapping("/save-details-one")
	public ResponseEntity<ResponseModel> saveBillPunchDetailsOne(@RequestBody BillPunchDetailsModel entity,
			HttpServletRequest req) throws Exception {
		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		 request.setToken(getToken(req));
		TokenResponse response = null;
		BillPunchDetailsModel cm = null;
		response = restTemplate.postForEntity(tokenurl, request,
		 TokenResponse.class).getBody();

		if (response.getStatus().contentEquals("True")) {

			List<BillPunchDetailsModel> m = services.findAllApprovedDetailsForSupplyReportFilternew(
					entity.getPartyCode(), entity.getInvoiceNO(), entity.getBillOrderNo(),"SUBMITTED");
			if (m.isEmpty()) {
				BillPunchDetailsModel pm = services.findAllApprovedDetailsForSupplyReportFilternewTWO(
						entity.getPartyCode(), entity.getInvoiceNO(), entity.getBillOrderNo()).get(0);
				pm.setStatus("SUBMITTED");
				cm = services.saveOne(pm);
			} else {
				throw new Exception("Same invoice and OrderNo already exist.");
			}

			rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
			rs.setMessage(ReraMessageConstants.SUCCESS_MESSAGE);
			rs.setData(cm);
		} else {
			rs.setData(null);
			rs.setStatus(ReraMessageConstants.FAILED_STATUS);
			rs.setMessage(ReraMessageConstants.FAILED_MESSAGE);

		}

		return ResponseEntity.ok(rs);

	}

	@PostMapping("/save-details-manual")
	public ResponseEntity<ResponseModel> saveBillPunchDetailsOnemanual(@RequestBody BillPunchDetailsModel entity,
			HttpServletRequest req) throws Exception {
		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
	
		BillPunchDetailsModel cm = null;
		TokenResponse response  = restTemplate.postForEntity(tokenurl, request,
	 TokenResponse.class).getBody();

		if (response.getStatus().contentEquals("True")) {

			List<BillPunchDetailsModel> m = services.findAllApprovedDetailsForSupplyReportFilternewmanual(
					entity.getPartyCode(), entity.getInvoiceNO(), entity.getBillOrderNo());
			if (m.isEmpty()) {
				cm = services.saveOne(entity);
			} else {
				throw new Exception("Same invoice and OrderNo already exist.");
			}

			rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
			rs.setMessage(ReraMessageConstants.SUCCESS_MESSAGE);
			rs.setData(cm);
		} else {
			rs.setData(null);
			rs.setStatus(ReraMessageConstants.FAILED_STATUS);
			rs.setMessage(ReraMessageConstants.FAILED_MESSAGE);

		}

		return ResponseEntity.ok(rs);

	}

	/********************************************************************************************
	 * Find All bill punch data
	 ********************************************************************************************/
	@GetMapping("/getall-billpunch-details-filter/{partycode}/{invno}/{orderNo}/{status}/{yr}/{wk}/{grNo}")
	public ResponseEntity<ResponseModel> getAllFilter(HttpServletRequest req,
			@PathVariable("partycode") String partycode, @PathVariable("invno") String invno,
			@PathVariable("orderNo") String orderNo, @PathVariable("status") String status,
			@PathVariable("yr") String yr, @PathVariable("wk") String wk, @PathVariable("grNo") String grNo) {

		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		 request.setToken(getToken(req));
		 TokenResponse response = restTemplate.postForEntity(tokenurl, request,
		 TokenResponse.class).getBody();

		if (response.getStatus() .contentEquals("True")) {
			List<BillPunchDetailsModel> cm = services.findAllApprovedDetailsForSupplyReportFilternewold(partycode,
					invno, orderNo, status, yr, wk, grNo);
			// BillPunchDetailsModel cm =
			// services.findAllApprovedDetailsForSupplyReportFilter(partycode,invno).get(0);

			if (!cm.isEmpty()) {
				rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
				rs.setMessage(ReraMessageConstants.SUCCESS_MESSAGE);
				rs.setData(cm);
			} else {
				rs.setData(null);
				rs.setStatus(ReraMessageConstants.FAILED_STATUS);
				rs.setMessage(ReraMessageConstants.FAILED_MESSAGE);

			}
		} else {
			rs.setData(null);
			rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
			rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);
		}

		return ResponseEntity.ok(rs);

	}

	// @GetMapping("/getall-billpunch-details-filter-list/{partycode}/{invno}/{orderNo}/{status}")
	@RequestMapping(value = "/getall-billpunch-details-filter-list/{partycode}/{invno}/{orderNo}/{status}", method = RequestMethod.OPTIONS)
	public ResponseEntity<ResponseModel> getAllFilterList(HttpServletRequest req,
			@PathVariable("partycode") String partycode, @PathVariable("invno") String invno,
			@PathVariable("orderNo") String orderNo, @PathVariable("status") String status) {

		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		 request.setToken(getToken(req));
		 TokenResponse response = restTemplate.postForEntity(tokenurl, request,
		 TokenResponse.class).getBody();

		if (response.getStatus() .contentEquals("True")) {
			List<BillPunchDetailsModel> cm = services.findAllApprovedDetailsForSupplyReportFilternew(partycode, invno,
					orderNo, status);

			if (cm != null) {
				rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
				rs.setMessage(ReraMessageConstants.SUCCESS_MESSAGE);
				rs.setData(cm);
			} else {
				rs.setData(null);
				rs.setStatus(ReraMessageConstants.FAILED_STATUS);
				rs.setMessage(ReraMessageConstants.FAILED_MESSAGE);

			}
		} else {
			rs.setData(null);
			rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
			rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);
		}

		return ResponseEntity.ok(rs);

	}

	@SuppressWarnings("all")
	@PostMapping("/get-history-list-populate-by-billno-test")
	public ResponseEntity<ResponseModel> getBillPunchDetailsForHistoryone(@RequestBody BillPunchDetailsModel em,
			HttpServletRequest req) {
		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		 request.setToken(getToken(req));
		 TokenResponse response = restTemplate.postForEntity(tokenurl, request,
		 TokenResponse.class).getBody();

		if (response.getStatus() .contentEquals("True")) {
			BillPurchaseCostInterface vm = null;
			PurchaseCostInterface ch = null;
			PurchaseCostInterface cs = null;
			List<BillPunchDetailsModel> cm = services.getDetailsByFilterHistoryone(em.getInvoiceNO(), em.getPartyCode(),
					em.getBillOrderNo(), em.getStatus());
			if (Optional.ofNullable(cm).isPresent()) {
				List<BillPunchDto> list1 = new ArrayList<>();
				List<RdcDetailsDto> list11 = new ArrayList<>();
				Map<String, List<BillPunchDto>> map1 = new HashMap<>();
				BillPunchDto entity = new BillPunchDto();
				for (BillPunchDetailsModel pm : cm) {
					RdcDetailsDto mn = new RdcDetailsDto();
					entity.setIgstamt(pm.getIgstamt());
					entity.setCgstamt(pm.getCgstamt());
					entity.setSgstamt(pm.getSgstamt());

					if (!map1.containsKey(pm.getBillOrderNo())) {
						//entity.setBillId(pm.getBilId());
						entity.setBillCloseStatus(pm.getBillCloseStatus());
						entity.setShopName(pm.getShopName());
						entity.setShopNo(pm.getRdcCode());
						entity.setBillCloseWeek(pm.getBillCloseWeek());
						entity.setPurchaseCost(pm.getPurchaseCost());
						entity.setBillUniqueCode(pm.getBillUniqueCode());
						entity.setBillOrderDate(pm.getBillOrderDate());
						entity.setBillOrderNo(pm.getBillOrderNo());
						entity.setBillWeekYear(pm.getBillWeekYear());
						entity.setFormtype(pm.getFormtype());
						entity.setStateCode(pm.getStateCode());
						entity.setTotalCost(pm.getTotalCost());
						entity.setInvoiceCost(pm.getInvoiceCost());
						entity.setStatus(pm.getStatus());
						entity.setReceiveLoc(pm.getReceiveLoc());
						entity.setIgst(pm.getIgst());
						entity.setCgst(pm.getCgst());
						entity.setSgst(pm.getSgst());
						entity.setCreatedOn(pm.getCreatedOn());
						entity.setFreight(pm.getFreight());
						entity.setPair(pm.getPair());
						entity.setPartyCode(pm.getPartyCode());
						entity.setPartyName(pm.getPartyName());
						entity.setRegion(pm.getRegion());
						entity.setStateName(pm.getStateName());
						entity.setInvoiceNO(pm.getInvoiceNO());
						entity.setRecptInvDate(pm.getRecptInvDate());
						entity.setGrNo(pm.getGrNo());
						entity.setGrDate(pm.getGrDate());
						entity.setGrnDate(pm.getGrDate());
						entity.setCnNo(pm.getCnNo());
						entity.setCnDate(pm.getCnDate());
						entity.setCreditNote(pm.getCreditNote());
						entity.setDiscountAmt(pm.getDiscountAmt());
						entity.setDiscountAmtVal(pm.getDiscountAmtVal());
						entity.setTcsValue(pm.getTcsValue());
						entity.setTcsApplicable(pm.getTcsApplicable());
						entity.setTcsPercent(pm.getTcsPercent());
						list1.add(entity);
						mn.setIgst(pm.getIgst());
						mn.setCgst(pm.getCgst());
						mn.setSgst(pm.getSgst());
						mn.setRdcPairC(pm.getRdcPairC());
						// mn.setBillId(pm.getBilId());
						mn.setRdcPair(pm.getRdcPair());
						mn.setWeekYear(pm.getWeekYear());
						mn.setArticleCode(pm.getArticleCode());
						mn.setArticleName(pm.getArticleName());
						mn.setPairAmount(pm.getPairAmount());
						mn.setReceiveDate(pm.getReceiveDate());
						mn.setNoOfCartons(pm.getNoOfCartons());
						if (Optional.ofNullable(mn.getArticleCode()).isPresent()
								|| Optional.ofNullable(mn.getReceiveDate()).isPresent()
								|| Optional.ofNullable(mn.getPairAmount()).isPresent()
								|| Optional.ofNullable(mn.getRdcPair()).isPresent()
								|| Optional.ofNullable(mn.getWeekYear()).isPresent()) {
							list11.add(mn);
						}

						map1.put(pm.getBillOrderNo(), list1);
					} else {
						mn.setIgst(pm.getIgst());
						mn.setCgst(pm.getCgst());
						mn.setSgst(pm.getSgst());
						mn.setRdcPairC(pm.getRdcPairC());
						// mn.setBillId(pm.getBilId());
						mn.setRdcPair(pm.getRdcPair());
						mn.setWeekYear(pm.getWeekYear());
						mn.setArticleCode(pm.getArticleCode());
						mn.setArticleName(pm.getArticleName());
						mn.setPairAmount(pm.getPairAmount());
						if (Optional.ofNullable(cs).isPresent()) {
							mn.setReceiveDate(cs.getreceiveDate());
						}

						mn.setNoOfCartons(pm.getNoOfCartons());
						if (Optional.ofNullable(mn.getArticleCode()).isPresent()
								|| Optional.ofNullable(mn.getReceiveDate()).isPresent()
								|| Optional.ofNullable(mn.getPairAmount()).isPresent()
								|| Optional.ofNullable(mn.getRdcPair()).isPresent()
								|| Optional.ofNullable(mn.getWeekYear()).isPresent()) {
							list11.add(mn);
						}

					}
					entity.setRdcList(list11);

				}

				rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
				rs.setMessage(ReraMessageConstants.SUCCESS_MESSAGE);
				rs.setData(entity);
			} else {
				rs.setData(null);
				rs.setStatus(ReraMessageConstants.FAILED_STATUS);
				rs.setMessage(ReraMessageConstants.FAILED_MESSAGE);

			}
		} else {
			rs.setData(null);
			rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
			rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);
		}
		return ResponseEntity.ok(rs);

	}

	/********************************************************************************************
	 * Find All bill punch data
	 ********************************************************************************************/
	@GetMapping("/getall-billpunch-details-oneee")
	public ResponseEntity<ResponseModel> getAllOnetest(HttpServletRequest req) {

		
		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		 request.setToken(getToken(req));
		 TokenResponse response = restTemplate.postForEntity(tokenurl, request,
		 TokenResponse.class).getBody();

		if (response.getStatus() .contentEquals("True")) {
			// BillPunchDetailsModel cm = services.getAll().get(0);

			if (true) {
				rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
				rs.setMessage(ReraMessageConstants.SUCCESS_MESSAGE);
				rs.setData("Service working fine");
			} else {
				rs.setData(null);
				rs.setStatus(ReraMessageConstants.FAILED_STATUS);
				rs.setMessage(ReraMessageConstants.FAILED_MESSAGE);

			}
		} else {
			rs.setData("Service working fine");
			rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
			rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);
		}

		return ResponseEntity.ok(rs);

	}

	@GetMapping("/get-billpunch-charts-one")
	public ResponseEntity<ResponseModel> getAllOneCharts(HttpServletRequest req) {

		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		 request.setToken(getToken(req));
		 TokenResponse response = restTemplate.postForEntity(tokenurl, request,
		 TokenResponse.class).getBody();

		
		String fyear = "null";
		String year1 = null;
		String year2 = null;

		if (fyear.equals("null")) {

			year1 = null;
			year2 = null;
		} else {
			String year = fyear;
			String[] s1 = year.split("-");
			int y1 = Integer.parseInt(s1[0]);
			int y2 = y1 + 1;
			year1 = y1 + "-04-01";
			year2 = y2 + "-03-31";
		}

		if (response.getStatus() .contentEquals("True")) {
			List<ChartsInterface> cm = services.getAllCharts(year1, year2);

			if (!cm.isEmpty()) {
				rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
				rs.setMessage(ReraMessageConstants.SUCCESS_MESSAGE);
				rs.setData(cm);
			} else {
				rs.setData(null);
				rs.setStatus(ReraMessageConstants.FAILED_STATUS);
				rs.setMessage(ReraMessageConstants.FAILED_MESSAGE);

			}
		} else {
			rs.setData(null);
			rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
			rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);
		}

		return ResponseEntity.ok(rs);

	}

	@GetMapping("/get-billpunch-charts-one-test/{fyear}")
	public ResponseEntity<ResponseModel> getAllOneChartsTest(HttpServletRequest req,
			@PathVariable("fyear") String fyear) {

		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		 request.setToken(getToken(req));
		 TokenResponse response = restTemplate.postForEntity(tokenurl, request,
		 TokenResponse.class).getBody();

		

		String year1 = null;
		String year2 = null;

		if (fyear.equals("null")) {

			year1 = null;
			year2 = null;
		} else {
			String year = fyear;
			String[] s1 = year.split("-");
			int y1 = Integer.parseInt(s1[0]);
			int y2 = y1 + 1;
			year1 = y1 + "-04-01";
			year2 = y2 + "-03-31";
		}

		if (response.getStatus() .contentEquals("True")) {
			List<ChartsInterface> cm = services.getAllCharts(year1, year2);

			if (!cm.isEmpty()) {
				rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
				rs.setMessage(ReraMessageConstants.SUCCESS_MESSAGE);
				rs.setData(cm);
			} else {
				rs.setData(null);
				rs.setStatus(ReraMessageConstants.FAILED_STATUS);
				rs.setMessage(ReraMessageConstants.FAILED_MESSAGE);

			}
		} else {
			rs.setData(null);
			rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
			rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);
		}

		return ResponseEntity.ok(rs);

	}

	/********************************************************************************************
	 * Find All bill punch data
	 ********************************************************************************************/
	@GetMapping("/play-music-player")
	public ResponseEntity<ResponseModel> getMusicPlay(HttpServletRequest req) {

		try {
			Clip sound = AudioSystem.getClip();
			System.out.println(">>>>>>>>>>>>>>>>>>>>." + is);
			/*
			 * ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			 * InputStream is = classloader.getResourceAsStream(file);
			 */

			// InputStream in = this.getClass().getResourceAsStream("/SomeTextFile.txt");

			System.out.println(">>>>>>>>>>>>>>>>>>>>.InputStream" + is);
			System.out.println("stream>>>>>>>>>>" + is);

			sound.open(AudioSystem.getAudioInputStream(new BufferedInputStream(is)));
			// getClass().getResource(file)
			System.out.println(">>>>>>>>>>>>>>>>>>>>.###########");
			sound.start();
			sound.flush();
			is.close();
		} catch (UnsupportedAudioFileException e1) {
		} catch (IOException e2) {
			e2.printStackTrace();
		} catch (LineUnavailableException e3) {
		}

		ResponseModel rs = new ResponseModel();

		rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
		rs.setMessage(ReraMessageConstants.SUCCESS_MESSAGE);
		rs.setData("start.");
		return ResponseEntity.ok(rs);

	}

	@GetMapping("/github")
	public ResponseEntity<String> msgGithub() {
		// return "First step login succesfully done by " + principle.getName() + " ";
		System.out.println("String github username =");
		return ResponseEntity.ok("jj");
	}
}
