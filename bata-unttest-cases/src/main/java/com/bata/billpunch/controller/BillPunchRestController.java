package com.bata.billpunch.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bata.billpunch.config.TokenRequest;
import com.bata.billpunch.config.TokenResponse;
import com.bata.billpunch.constant.ReraMessageConstants;
import com.bata.billpunch.constant.ResponseModel;
import com.bata.billpunch.model.ApprovalDetailsModel;
import com.bata.billpunch.model.BillPunchDetailsModel;
import com.bata.billpunch.model.StrazaPesponseDetailsModel;
import com.bata.billpunch.model.WeekMasterModel;
import com.bata.billpunch.model.dto.BillCloseStatusDto;
import com.bata.billpunch.model.dto.BillPunchDto;
import com.bata.billpunch.model.dto.BillPunchResponseInterface;
import com.bata.billpunch.model.dto.PartyResponseDto;
import com.bata.billpunch.model.dto.PriceInterface;
import com.bata.billpunch.model.dto.RdcDetailsDto;
import com.bata.billpunch.model.dto.ReceivingLocDto;
import com.bata.billpunch.model.dto.StrazaBillPunchDto;
import com.bata.billpunch.model.dto.StrazaReqModel;
import com.bata.billpunch.service.impl.BillPunchServicesImpl;
import com.bata.billpunch.service.impl.WeekMasterServiceImpl;

@RestController
@RequestMapping("/bill-punch")
@CrossOrigin(origins = "*")
public class BillPunchRestController {
	   InputStream is = BillPunchRestController.class.getResourceAsStream("songs.wav");
	@Autowired
	private BillPunchServicesImpl services;

	

	@Autowired
	private WeekMasterServiceImpl wservice;

	

	
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

		if ("True".contentEquals("True")) {

			

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
								try {}

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
		// request.setToken(getToken(req));
		// TokenResponse response = restTemplate.postForEntity(tokenurl, request,
		// TokenResponse.class).getBody();

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
		//TokenResponse response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();
//response.getStatus()
		if ("True".contentEquals("True")) {
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
                    System.out.println("Adonis week from master data>>>>>"+km.getBataWeek());
                  
					if (d2.compareTo(d1) >= 0 && d2.compareTo(d3) <= 0) {
						
						week = km.getBataWeek();
					}
					  System.out.println("Week Master details for master data"+week);  
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
	 * Find All bill punch RDC data
	 ********************************************************************************************/
	@GetMapping("/getall")
	public ResponseEntity<ResponseModel> getAllBillDetailsFromRdc(HttpServletRequest req) {

		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
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
				//	ApprovalDetailsModel xm = services.getApprovalDetails(mn);
					//list1.add(xm);

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
	 * Find All bill punch partycode and partyname data
	 ********************************************************************************************/
	@GetMapping("/getall-receiving-locations")
	public ResponseEntity<ResponseModel> getAllReceivingLocationName(HttpServletRequest req) {

		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
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

	
	
	@PostMapping("/getvalidate-by-partycode-party")
	public ResponseEntity<ResponseModel> getvalidatepartyAndPartyCode(@RequestBody BillPunchDetailsModel em,
			HttpServletRequest req) {
		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
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
	 * Find All bill punch data
	 ********************************************************************************************/
	@GetMapping("/play-music-player")
	public ResponseEntity<ResponseModel> getMusicPlay(HttpServletRequest req) {

		

		try {
			Clip sound = AudioSystem.getClip();
			System.out.println(">>>>>>>>>>>>>>>>>>>>."+is);
			/*
			 * ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			 * InputStream is = classloader.getResourceAsStream(file);
			 */
		
		    //InputStream in = this.getClass().getResourceAsStream("/SomeTextFile.txt");   

			
		        System.out.println(">>>>>>>>>>>>>>>>>>>>.InputStream"+is);
		        System.out.println("stream>>>>>>>>>>"+is);
		        
		        

			sound.open(AudioSystem.getAudioInputStream(new BufferedInputStream(is)));
					//getClass().getResource(file)
			System.out.println(">>>>>>>>>>>>>>>>>>>>.###########");
			sound.start();
			sound.flush();
			is.close();
		} 
		catch (UnsupportedAudioFileException e1) { }
		catch (IOException e2) { 
			e2.printStackTrace();
		} 
		catch (LineUnavailableException e3) { }
	
		ResponseModel rs = new ResponseModel();

		rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
		rs.setMessage(ReraMessageConstants.SUCCESS_MESSAGE);
		rs.setData("start.");
		return ResponseEntity.ok(rs);

	}
	
	

	/********************************************************************************************
	 * Find All bill punch RDC data
	 ********************************************************************************************/
	@GetMapping("/getall-mock")
	public ResponseEntity<ResponseModel> getAllMock(HttpServletRequest req) {

		ResponseModel rs = new ResponseModel();
		if ("True".contentEquals("True")) {

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
	 * save billpunch details
	 ********************************************************************************************/
	@PostMapping("/save-details-mock")
	public ResponseEntity<ResponseModel> saveBillPunchDetailsMock(@RequestBody BillPunchDetailsModel entity,
			HttpServletRequest req) {
		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		// request.setToken(getToken(req));
		TokenResponse response = null;

		// response = restTemplate.postForEntity(tokenurl, request,
		// TokenResponse.class).getBody();

		if ("True".contentEquals("True")) {

			

			BillPunchDetailsModel cm = services.saveMock(entity);
			if (cm!=null) {
				rs.setData(cm);
				rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
				rs.setMessage(ReraMessageConstants.SUCCESS_MESSAGE);
				
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
	 * update billpunch details
	 ********************************************************************************************/
	@PutMapping("/update-details-mock")
	public ResponseEntity<ResponseModel> updateBillPunchDetailsMock(@RequestBody BillPunchDetailsModel entity,
			HttpServletRequest req) {
		ResponseModel rs = new ResponseModel();
		// request.setToken(getToken(req));
		TokenResponse response = null;

		// response = restTemplate.postForEntity(tokenurl, request,
		// TokenResponse.class).getBody();

		if ("True".contentEquals("True")) {
		
			BillPunchDetailsModel old = services.getById(entity.getBilId());
			old.setArticleCode(entity.getArticleCode());
			
			BillPunchDetailsModel cm = services.saveMock(old);
			if (cm!=null) {
				rs.setData(cm);
				rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
				rs.setMessage(ReraMessageConstants.SUCCESS_MESSAGE);
				
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
	
	

	
}
