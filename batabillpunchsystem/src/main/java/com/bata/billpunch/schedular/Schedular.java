package com.bata.billpunch.schedular;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bata.billpunch.dao.RdcMappingDao;
import com.bata.billpunch.model.BillPunchDetailsImportDataModel;
import com.bata.billpunch.model.BillPunchDetailsModel;
import com.bata.billpunch.model.ShopMasterModel;
import com.bata.billpunch.service.impl.ArticleMasterServiceImpl;
import com.bata.billpunch.service.impl.BillPunchMasterServicesImpl;
import com.bata.billpunch.service.impl.BillPunchServicesImpl;
import com.bata.billpunch.service.impl.PartyMasterServiceImpl;
import com.bata.billpunch.service.impl.ShopMasterServiceImpl;
import com.bata.billpunch.service.impl.StateMasterServiceImpl;

@Component
public class Schedular {

	@Autowired
	private BillPunchServicesImpl services;

	@Autowired
	private BillPunchMasterServicesImpl mservices;

	@Autowired
	private ArticleMasterServiceImpl aservices;

	@Autowired
	private ShopMasterServiceImpl sservices;

	@Autowired
	private PartyMasterServiceImpl pservices;

	@Autowired
	private StateMasterServiceImpl stservices;

	@Autowired
	private RdcMappingDao rdcmapdao;

	@Value("${TOKEN_URL}")
	private String tokenurl;

	// @Scheduled(fixedDelay = 1000 * 30)
	// @Scheduled(cron = "0 5 */7 * * ?", zone = "Indian/Maldives")
	// @Scheduled(cron = "0 35 14 * * ?")
	public void getXlDataForImport() throws IOException, ParseException {
		String excelFilePath = null;
		try {
			System.out.println("Schedular start");
			// excelFilePath = "C://report//RDC_NEW.xls";
			excelFilePath = "/var/log/batabillpunch/RDC_RECV.xls";
			System.out.println(excelFilePath);
		} catch (Exception e) {
		}

		FileInputStream inputStream = new FileInputStream(excelFilePath);

		Workbook workbook = new HSSFWorkbook(inputStream);
		// Workbook workbook = new XSSFWorkbook(inputStream);
		if (Optional.ofNullable(workbook).isPresent()) {

			for (Sheet sh : workbook) {

				for (Row r : sh) {
					if (r.getRowNum() == 0) {

					} else {
						try {

							String x1 = r.getCell(0).getStringCellValue();

							Integer x2 = Integer.parseInt(r.getCell(1).getStringCellValue());

							Integer x3 = Integer.parseInt(r.getCell(2).getStringCellValue());

							Integer x4 = Integer.parseInt(r.getCell(3).getStringCellValue());

							Date x5 = r.getCell(4).getDateCellValue();

							Integer x6 = Integer.parseInt(r.getCell(5).getStringCellValue());

							String x7 = r.getCell(6).getStringCellValue().trim();

							String x8 = r.getCell(7).getStringCellValue();

							Integer x9 = Integer.parseInt(r.getCell(8).getStringCellValue());

							String x10 = r.getCell(9).getStringCellValue();

							String x11 = r.getCell(10).getStringCellValue();

							String x12 = r.getCell(11).getStringCellValue();

							Integer x13 = Integer.parseInt(r.getCell(12).getStringCellValue());

							Integer x14 = Integer.parseInt(r.getCell(13).getStringCellValue());

							Integer x15 = Integer.parseInt(r.getCell(14).getStringCellValue());

							Integer x16 = Integer.parseInt(r.getCell(15).getStringCellValue());

							Integer x17 = Integer.parseInt(r.getCell(16).getStringCellValue());

							Integer x18 = Integer.parseInt(r.getCell(17).getStringCellValue());

							Integer x19 = Integer.parseInt(r.getCell(18).getStringCellValue());

							Integer x20 = Integer.parseInt(r.getCell(19).getStringCellValue());

							Integer x21 = Integer.parseInt(r.getCell(20).getStringCellValue());

							String x22 = r.getCell(21).getStringCellValue();

							String x24 = r.getCell(23).getStringCellValue();

							String x25 = r.getCell(24).getStringCellValue();

							String x28 = r.getCell(27).getStringCellValue();

							String d29 = r.getCell(28).getStringCellValue();
							Date x29 = null;
							if (Optional.ofNullable(d29).isPresent()) {
								String s2 = d29.substring(2, 4);
								String s3 = d29.substring(4, 8);
								String sn = d29.substring(0, 2).concat("-") + s2.concat("-") + s3;
								x29 = new SimpleDateFormat("dd-MM-yyyy").parse(sn);
							}

							String x30 = r.getCell(29).getStringCellValue();

							String x33 = r.getCell(32).getStringCellValue();

							Double x34 = Double.valueOf(r.getCell(33).getStringCellValue()) / 100;

							String x35 = r.getCell(34).getStringCellValue().trim();

							String d36 = r.getCell(35).getStringCellValue();
							Date x36 = null;
							if (Optional.ofNullable(d29).isPresent()) {
								String s22 = d36.substring(2, 4);
								String s33 = d36.substring(4, 8);
								String snn = d36.substring(0, 2).concat("-") + s22.concat("-") + s33;
								x36 = new SimpleDateFormat("dd-MM-yyyy").parse(snn);
							}

							String x40 = r.getCell(39).getStringCellValue().trim();

							System.out.println(r.getCell(40).getStringCellValue());
							String x41 = r.getCell(40).getStringCellValue();

							Double x42 = Double.valueOf(r.getCell(41).getStringCellValue());

							String x43 = r.getCell(42).getStringCellValue();

							Double x44 = Double.valueOf(r.getCell(43).getStringCellValue());

							String x45 = r.getCell(44).getStringCellValue();

							Double x46 = Double.valueOf(r.getCell(45).getStringCellValue());

							String x47 = r.getCell(46).getStringCellValue();

							Double x48 = Double.valueOf(r.getCell(47).getStringCellValue());

							Double x49 = Double.valueOf(r.getCell(48).getStringCellValue()) / 100;
							Integer x50 = Integer.parseInt(r.getCell(49).getStringCellValue());

							Integer x51 = Integer.parseInt(r.getCell(50).getStringCellValue());

							BillPunchDetailsModel xm = new BillPunchDetailsModel();

							xm.setRdcCode(x1);
							xm.setBillCloseWeek(x2);
							xm.setBillCloseYear(x3);
							xm.setBillWeekDay(x4);

							xm.setTranCode(x6);
							xm.setInvoiceNO(x35);
							xm.setArticleCode(x8);
							try {
								xm.setArticleName(
										aservices.getArticleDetails(r.getCell(7).getStringCellValue()).getArtname());
							} catch (Exception e) {
							}

							try {

								if (Optional.ofNullable(
										sservices.getShopDetails(r.getCell(0).getStringCellValue()).getShopname())
										.isPresent()) {
									xm.setReceiveLoc(sservices.getShopDetails(r.getCell(0).getStringCellValue())
											.getShopname().trim());
								} else {
									xm.setReceiveLoc(sservices.getShopDetails(rdcmapdao
											.findByMergerdccode(r.getCell(0).getStringCellValue()).get(0).getRdcno())
											.getShopname().trim());

								}
							} catch (Exception e) {
							}

							xm.setSizeCode(x9);
							xm.setBillWeekYear(x10);
							xm.setWeekYear(x11);
							xm.setShopNo(x12);
							try {
								xm.setShopName(
										sservices.getShopDetails(r.getCell(11).getStringCellValue()).getShopname());
							} catch (Exception e) {
							}

							xm.setDistcode(x13);
							xm.setInvType(x14);
							xm.setRdcPair(x15);
							xm.setPair(x16);
							xm.setPackCaseB(x17);
							xm.setPackCaseM(x18);
							xm.setPackCaseS(x19);
							xm.setPackCaseC(x20);
							xm.setPackCaseT(x21);
							xm.setDcCode(x22);
							xm.setBillUniqueCode(x24);
							xm.setParty(x25);

							try {
								xm.setPartyCode(mservices.getOrderDetails(x40).get(0).getPartyCode());

							} catch (Exception e) {
							}

							try {
								xm.setPartyName(pservices
										.getPartiesDetails(
												StringUtils.leftPad(r.getCell(24).getStringCellValue(), 4, "0"))
										.getPartyfullname());
							} catch (Exception e) {
							}

							xm.setCnNo(x28);
							if (Optional.ofNullable(x29).isPresent()) {
								xm.setCnDate(x29);
								xm.setGrnDate(x29);
								xm.setBillOrderDate(x29);
							}

							xm.setTrnsportCode(x30);
							xm.setStateCode(x33);
							try {
								xm.setStateName(
										stservices.getStateDetails(r.getCell(32).getStringCellValue()).getStateName());
							} catch (Exception e) {
							}

							xm.setInvoiceCost(x34);
							// xm.setRecptInvNo(x35);
							xm.setGrNo(x7);
							if (Optional.ofNullable(x36).isPresent()) {
								xm.setRecptInvDate(x36);
							}

							if (Optional.ofNullable(x5).isPresent()) {
								xm.setGrDate(x5);
								xm.setTranDate(x5);
							}

							xm.setBillOrderNo(x40);

							xm.setHsnCode(x41);
							xm.setGstamt(x42);
							// xm.setGstamt(x44+x46+x48);
							xm.setCgst(String.valueOf(Double.parseDouble(x43) / 100));
							xm.setCgstamt(x44 / 100);
							xm.setSgst(String.valueOf(Double.parseDouble(x45) / 100));
							xm.setSgstamt(x46 / 100);
							xm.setIgst(String.valueOf(Double.parseDouble(x47) / 100));
							xm.setIgstamt(x48 / 100);
							xm.setPairAmount(x49);
							xm.setFromState(x50);
							xm.setToState(x51);
							xm.setStatus("RECORD_RECEIVED");
							services.saveXl(xm);

						} catch (Exception e) {
							e.printStackTrace();
						}
					}

				}

			}

		}
		System.out.println("Schedular end");
	}

	@Scheduled(cron = "0 0 3 * * ?", zone = "Indian/Maldives")
	 //@Scheduled(cron = "0 53 7 * * ?")
	public void getImportDataFromRdcXl() {
		System.out.println("Schedular Start ######################################################################");
		List<BillPunchDetailsImportDataModel> cm = services.getallRdcData();

		if (!cm.isEmpty()) {
			for (BillPunchDetailsImportDataModel sh : cm) {

				try {

					BillPunchDetailsModel xm = new BillPunchDetailsModel();

					xm.setRdcCode(sh.getRdcCode());
					xm.setBillCloseWeek(Integer.valueOf(sh.getBillCloseWeek()));
					xm.setBillCloseYear(Integer.valueOf(sh.getBillCloseYear()));
					xm.setBillWeekDay(Integer.valueOf(sh.getBillWeekDay()));
					xm.setTranDate(sh.getTranDate());
					xm.setTranCode(Integer.valueOf(sh.getTranCode()));
					xm.setInvoiceNO(sh.getInvoiceNO().trim());
					xm.setArticleCode(sh.getArticleCode());

					try {
						xm.setArticleName(aservices.getArticleDetails(sh.getArticleCode()).getArtname().trim());
					} catch (Exception e) {
					}

					try {

						if (Optional.ofNullable(sh.getRdcCode()).isPresent()) {
							xm.setReceiveLoc(sservices.getShopDetails(sh.getRdcCode()).getShopname().trim());
						}

						/*
						 * else { xm.setReceiveLoc(
						 * sservices.getShopDetails(rdcmapdao.findByMergerdccode(sh.getRdcCode()).get(0)
						 * .getRdcno()) .getShopname().trim());
						 * 
						 * }
						 */

					} catch (Exception e) {
					}

					xm.setSizeCode(Integer.valueOf(sh.getSizeCode()));
					xm.setBillWeekYear(sh.getBillWeekYear());
					xm.setWeekYear(sh.getWeekYear());
					xm.setShopNo(sh.getShopNo());
					xm.setUpdateDate(sh.getUpdateDate());
					try {
						xm.setShopName(sservices.getShopDetails(sh.getShopNo()).getShopname().trim());
					} catch (Exception e) {
					}

					xm.setDistcode(Integer.valueOf(sh.getDistcode()));
					xm.setInvType(Integer.valueOf(sh.getInvType()));
					xm.setRdcPair(Integer.valueOf(sh.getRdcPair()));
					xm.setPair(Integer.valueOf(sh.getPair()));
					xm.setPackCaseB(Integer.valueOf(sh.getPackCaseB()));
					xm.setPackCaseM(Integer.valueOf(sh.getPackCaseM()));
					xm.setPackCaseS(Integer.valueOf(sh.getPackCaseS()));
					xm.setPackCaseC(Integer.valueOf(sh.getPackCaseC()));
					xm.setPackCaseT(Integer.valueOf(sh.getPackCaseT()));
					xm.setDcCode(sh.getDcCode());
					xm.setBillUniqueCode(sh.getBillUniqueCode());
					xm.setParty(sh.getParty());

					try {
						xm.setPartyCode(mservices.getOrderDetails(sh.getBillOrderNo().trim()).get(0).getPartyCode());

					} catch (Exception e) {
					}

					try {
						xm.setPartyName(
								pservices
										.getPartiesDetails(StringUtils
												.leftPad(mservices.getOrderDetails(sh.getBillOrderNo().trim()).get(0)
														.getPartyCode().substring(2, 5), 4, "0"))
										.getPartyfullname().trim());

					} catch (Exception e) {
					}

					xm.setCnNo(sh.getCnNo().trim());

					Date x29 = null;
					try {
						if (Optional.ofNullable(sh.getCnDate()).isPresent()) {
							String s2 = sh.getCnDate().substring(2, 4);
							String s3 = sh.getCnDate().substring(4, 8);
							String sn = sh.getCnDate().substring(0, 2).concat("-") + s2.concat("-") + s3;
							x29 = new SimpleDateFormat("dd-MM-yyyy").parse(sn);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

					xm.setCnDate(x29);
					xm.setGrnDate(x29);
					xm.setTrnsportCode(sh.getTrnsportCode());
					xm.setStateCode(sh.getStateCode());
					ShopMasterModel sx = null;
					try {
						sx = sservices.getShopDetails(sh.getRdcCode());
						if (Optional.ofNullable(sx).isPresent()) {

						}

						/*
						 * else {
						 * 
						 * sx =
						 * sservices.getShopDetails(rdcmapdao.findByMergerdccode(sh.getRdcCode()).get(0)
						 * .getRdcno());
						 * 
						 * }
						 */

					} catch (Exception e) {
					}
					try {
						xm.setStateName(stservices.getStateDetails(sx.getStatecode()).getStateName().trim());
					} catch (Exception e) {
					}
					Date x36 = null;
					try {
						if (Optional.ofNullable(sh.getRecptInvDate().trim()).isPresent()) {
							String s22 = sh.getRecptInvDate().trim().substring(2, 4);
							String s33 = sh.getRecptInvDate().trim().substring(4, 8);
							String snn = sh.getRecptInvDate().trim().substring(0, 2).concat("-") + s22.concat("-")
									+ s33;
							x36 = new SimpleDateFormat("dd-MM-yyyy").parse(snn);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

					xm.setInvoiceCost(Double.parseDouble(sh.getInvoiceCost()) / 100);
					xm.setGrNo(sh.getTrInvNO().trim());
					xm.setTrInvNO(sh.getTrInvNO().trim());
					xm.setRecptInvDate(x36);
					/*
					 * String sx = d5.substring(3, 5); String sy = d5.substring(6, 10); String sw =
					 * d5.substring(0, 2).concat("-") + sx.concat("-") + sy; Date x5 = new
					 * SimpleDateFormat("dd-MM-yyyy").parse(sw);
					 */
					xm.setGrDate(sh.getTranDate());
					xm.setTranDate(sh.getTranDate());
					xm.setBillOrderNo(sh.getBillOrderNo().trim());
					xm.setBillOrderDate(mservices.getOrderDetails(sh.getBillOrderNo().trim()).get(0).getAppDate());
					xm.setHsnCode(sh.getHsnCode());
					xm.setGstamt(Double.valueOf(sh.getGstamt()));
					xm.setCgst(String.valueOf(Double.parseDouble(sh.getCgst()) / 100));
					xm.setCgstamt(Double.valueOf(sh.getCgstamt()) / 100);
					xm.setSgst(String.valueOf(Double.parseDouble(sh.getSgst()) / 100));
					xm.setSgstamt(Double.parseDouble(sh.getSgstamt()) / 100);
					xm.setIgst(String.valueOf(Double.parseDouble(sh.getIgst()) / 100));
					xm.setIgstamt(Double.parseDouble(sh.getIgstamt()) / 100);
					xm.setPairAmount(Double.parseDouble(sh.getPairAmount()) / 100);
					xm.setFromState(Integer.valueOf(sh.getFromState()));
					xm.setToState(Integer.valueOf(sh.getToState()));
					// xm.setUpdateDate(sh.getUpdateDate());
					xm.setStatus("RECORD_RECEIVED");
					services.saveXl(xm);

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		}
		System.out.println("Schedular End######################################################################");
	}

	// @Scheduled(cron = "0 55 14 * * ?")
	public void getXlDataForImportNew() throws IOException, ParseException {

		String excelFilePath = null;
		try {
			System.out.println("Schedular start");
			// excelFilePath = "C://report//RDC_NEW.xls";
			excelFilePath = "/var/log/batabillpunch/RDC_NEW.xls";
			System.out.println(excelFilePath);
		} catch (Exception e) {
		}

		FileInputStream inputStream = new FileInputStream(excelFilePath);

		Workbook workbook = new HSSFWorkbook(inputStream);
		if (Optional.ofNullable(workbook).isPresent()) {

			for (Sheet sh : workbook) {

				for (Row r : sh) {
					if (r.getRowNum() == 0) {

					} else {
						try {

							String x1 = r.getCell(0).getStringCellValue();

							Integer x2 = Integer.parseInt(r.getCell(1).getStringCellValue());

							Integer x3 = (int) r.getCell(2).getNumericCellValue();

							Integer x4 = Integer.parseInt(r.getCell(3).getStringCellValue());

							String d5 = r.getCell(4).getStringCellValue();

							String sx = d5.substring(3, 5);
							String sy = d5.substring(6, 10);
							String sw = d5.substring(0, 2).concat("-") + sx.concat("-") + sy;
							Date x5 = new SimpleDateFormat("dd-MM-yyyy").parse(sw);

							Integer x6 = (int) r.getCell(5).getNumericCellValue();

							String x7 = r.getCell(6).getStringCellValue().trim();

							String x8 = r.getCell(7).getStringCellValue();

							Integer x9 = Integer.parseInt(r.getCell(8).getStringCellValue());

							String x10 = r.getCell(9).getStringCellValue();

							String x11 = r.getCell(10).getStringCellValue();

							String x12 = r.getCell(11).getStringCellValue();

							Integer x13 = Integer.parseInt(r.getCell(12).getStringCellValue());

							Integer x14 = (int) r.getCell(13).getNumericCellValue();

							Integer x15 = Integer.parseInt(r.getCell(14).getStringCellValue());

							Integer x16 = Integer.parseInt(r.getCell(15).getStringCellValue());

							Integer x17 = Integer.parseInt(r.getCell(16).getStringCellValue());

							Integer x18 = Integer.parseInt(r.getCell(17).getStringCellValue());

							Integer x19 = Integer.parseInt(r.getCell(18).getStringCellValue());

							Integer x20 = Integer.parseInt(r.getCell(19).getStringCellValue());

							Integer x21 = Integer.parseInt(r.getCell(20).getStringCellValue());

							String x22 = r.getCell(21).getStringCellValue();

							String x24 = r.getCell(23).getStringCellValue();

							String x25 = r.getCell(24).getStringCellValue();

							String x28 = r.getCell(27).getStringCellValue();

							String d29 = r.getCell(28).getStringCellValue();

							String s2 = d29.substring(2, 4);
							String s3 = d29.substring(4, 8);
							String sn = d29.substring(0, 2).concat("-") + s2.concat("-") + s3;
							Date x29 = new SimpleDateFormat("dd-MM-yyyy").parse(sn);

							String x30 = r.getCell(29).getStringCellValue();

							String x33 = r.getCell(32).getStringCellValue();

							Double x34 = Double.valueOf(r.getCell(33).getStringCellValue()) / 100;

							String x35 = r.getCell(34).getStringCellValue().trim();

							String d36 = r.getCell(35).getStringCellValue();

							String s22 = d36.substring(2, 4);
							String s33 = d36.substring(4, 8);
							String snn = d36.substring(0, 2).concat("-") + s22.concat("-") + s33;
							Date x36 = new SimpleDateFormat("dd-MM-yyyy").parse(snn);

							String x40 = r.getCell(39).getStringCellValue().trim();

							String x41 = r.getCell(40).getStringCellValue();

							Double x42 = Double.valueOf(r.getCell(41).getStringCellValue());

							String x43 = r.getCell(42).getStringCellValue();

							Double x44 = Double.valueOf(r.getCell(43).getStringCellValue());

							String x45 = r.getCell(44).getStringCellValue();

							Double x46 = Double.valueOf(r.getCell(45).getStringCellValue());

							String x47 = r.getCell(46).getStringCellValue();

							Double x48 = Double.valueOf(r.getCell(47).getStringCellValue());

							Double x49 = Double.valueOf(r.getCell(48).getStringCellValue()) / 100;

							Integer x50 = Integer.parseInt(r.getCell(49).getStringCellValue());

							Integer x51 = Integer.parseInt(r.getCell(50).getStringCellValue());

							BillPunchDetailsModel xm = new BillPunchDetailsModel();

							xm.setRdcCode(x1);
							xm.setBillCloseWeek(x2);
							xm.setBillCloseYear(x3);
							xm.setBillWeekDay(x4);
							// xm.setTranDate(x5);
							xm.setTranCode(x6);
							xm.setInvoiceNO(x35);
							xm.setArticleCode(x8);
							try {
								xm.setArticleName(
										aservices.getArticleDetails(r.getCell(7).getStringCellValue()).getArtname());
							} catch (Exception e) {
							}

							xm.setSizeCode(x9);
							xm.setBillWeekYear(x10);
							xm.setWeekYear(x11);
							xm.setShopNo(x12);
							try {
								xm.setShopName(
										sservices.getShopDetails(r.getCell(11).getStringCellValue()).getShopname());
							} catch (Exception e) {
							}

							try {

								if (Optional.ofNullable(
										sservices.getShopDetails(r.getCell(0).getStringCellValue()).getShopname())
										.isPresent()) {
									xm.setReceiveLoc(sservices.getShopDetails(r.getCell(0).getStringCellValue())
											.getShopname().trim());
								} else {
									xm.setReceiveLoc(sservices.getShopDetails(rdcmapdao
											.findByMergerdccode(r.getCell(0).getStringCellValue()).get(0).getRdcno())
											.getShopname().trim());

								}
							} catch (Exception e) {
							}

							xm.setDistcode(x13);
							xm.setInvType(x14);
							xm.setRdcPair(x15);
							xm.setPair(x16);
							xm.setPackCaseB(x17);
							xm.setPackCaseM(x18);
							xm.setPackCaseS(x19);
							xm.setPackCaseC(x20);
							xm.setPackCaseT(x21);
							xm.setDcCode(x22);
							xm.setBillUniqueCode(x24);
							xm.setParty(x25);

							try {
								xm.setPartyCode(mservices.getOrderDetails(x40).get(0).getPartyCode());

							} catch (Exception e) {
							}

							try {
								xm.setPartyName(pservices
										.getPartiesDetails(
												StringUtils.leftPad(r.getCell(24).getStringCellValue(), 4, "0"))
										.getPartyfullname());
							} catch (Exception e) {
							}

							xm.setCnNo(x28);
							xm.setCnDate(x29);
							xm.setGrnDate(x29);
							// xm.setTrnsportCode(x30);
							xm.setStateCode(x33);
							try {
								xm.setStateName(
										stservices.getStateDetails(r.getCell(32).getStringCellValue()).getStateName());
							} catch (Exception e) {
							}

							xm.setInvoiceCost(x34);
							xm.setGrNo(x7);
							if (Optional.ofNullable(x36).isPresent()) {
								xm.setRecptInvDate(x36);
							}
							xm.setTrnsportCode(x30);
							if (Optional.ofNullable(x5).isPresent()) {
								xm.setGrDate(x5);
								xm.setTranDate(x5);
							}
							xm.setBillOrderNo(x40);
							xm.setBillOrderDate(x29);
							xm.setHsnCode(x41);
							xm.setGstamt(x42); //
							xm.setGstamt(x44 + x46 + x48);
							xm.setCgst(String.valueOf(Double.parseDouble(x43) / 100));
							xm.setCgstamt(x44 / 100);
							xm.setSgst(String.valueOf(Double.parseDouble(x45) / 100));
							xm.setSgstamt(x46 / 100);
							xm.setIgst(String.valueOf(Double.parseDouble(x47) / 100));
							xm.setIgstamt(x48 / 100);
							xm.setPairAmount(x49);
							xm.setFromState(x50);
							xm.setToState(x51);
							xm.setStatus("RECORD_RECEIVED");
							services.saveXl(xm);

						} catch (Exception e) {
							e.printStackTrace();
						}
					}

				}

			}

		}
		System.out.println("Schedular end");
	}
}
