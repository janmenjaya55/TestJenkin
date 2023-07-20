package com.bata.billpunch.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bata.billpunch.config.TokenRequest;
import com.bata.billpunch.config.TokenResponse;
import com.bata.billpunch.constant.ReraMessageConstants;
import com.bata.billpunch.constant.ResponseModel;
import com.bata.billpunch.dao.RdcMappingDao;
import com.bata.billpunch.model.AdonisMasterModel;
import com.bata.billpunch.model.AdonisWeekMasterModel;
import com.bata.billpunch.model.BillPunchDetailsImportDataModel;
import com.bata.billpunch.model.BillPunchDetailsModel;
import com.bata.billpunch.model.BillPunchEdpReportModel;
import com.bata.billpunch.model.BillPunchGstReportModel;
import com.bata.billpunch.model.BillPunchSupplyReportModel;
import com.bata.billpunch.model.BillPunchTransReportModel;
import com.bata.billpunch.model.DistMasterModel;
import com.bata.billpunch.model.PartiesMasterModel;
import com.bata.billpunch.model.ShopMasterModel;
import com.bata.billpunch.model.dto.AdonisFileDetailsInterface;
import com.bata.billpunch.model.dto.BillPunchEdpInterface;
import com.bata.billpunch.model.dto.BillPunchReportResponse;
import com.bata.billpunch.model.dto.BillPunchResponseInterface;
import com.bata.billpunch.model.dto.PurchaseCostInterface;
import com.bata.billpunch.model.dto.TotalAmtInterface;
import com.bata.billpunch.service.impl.AdonisMasterServiceImpl;
import com.bata.billpunch.service.impl.AdonisWeekMasterServiceImpl;
import com.bata.billpunch.service.impl.ArticleMasterServiceImpl;
import com.bata.billpunch.service.impl.BillPunchMasterServicesImpl;
import com.bata.billpunch.service.impl.BillPunchServicesImpl;
import com.bata.billpunch.service.impl.DistMasterServiceImpl;
import com.bata.billpunch.service.impl.PartyMasterServiceImpl;
import com.bata.billpunch.service.impl.ShopMasterServiceImpl;
import com.bata.billpunch.service.impl.StateMasterServiceImpl;

@RestController
@RequestMapping("/bill-punch")
@CrossOrigin(origins = "*")
public class BillReportRestController {

	@Autowired
	private BillPunchServicesImpl services;

	@Autowired
	private ArticleMasterServiceImpl artservices;

	@Autowired
	private AdonisMasterServiceImpl adservices;

	@Autowired
	private AdonisWeekMasterServiceImpl adwservice;

	@Autowired
	private BillPunchMasterServicesImpl mservices;

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

	@Autowired
	private RdcMappingDao rdcmapdao;

	@Value("${TOKEN_URL}")
	private String tokenurl;

	public String getToken(HttpServletRequest req) {

		final String requestTokenHeader = req.getHeader("Authorization");
		String jwtToken = null;
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {

			jwtToken = requestTokenHeader.substring(7);
		}
		return jwtToken;
	}

	/********************************************************************************************
	 * Find All bill punch edp reports
	 * 
	 * @throws IOException
	 * @throws ParseException
	 ********************************************************************************************/
	@SuppressWarnings("all")
	@GetMapping("/get-bill-punch-gst-xl-report-details/{fromdate}/{todate}")
	public void getGstXlReport(HttpServletRequest req, HttpServletResponse response,
			@PathVariable("fromdate") String fromdateval, @PathVariable("todate") String todateval)
			throws IOException, ParseException {
		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse respons = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();
		
		String toyear = null;
		String toweek = null;

		String fromyear = null;
		String fromweek = null;

		String fromdate = fromdateval;
		String todate = todateval;

		String format = "yyyy-MM-dd";

		SimpleDateFormat df = new SimpleDateFormat(format);
		Date datefrom = df.parse(fromdate);
		Date dateto = df.parse(todate);

		Calendar fromcal = Calendar.getInstance();
		fromcal.setTime(datefrom);
		int yr = fromcal.get(Calendar.YEAR);
		int week = fromcal.get(Calendar.WEEK_OF_YEAR);

		Calendar tocal = Calendar.getInstance();
		tocal.setTime(dateto);
		int toyr = tocal.get(Calendar.YEAR);
		int weekto = tocal.get(Calendar.WEEK_OF_YEAR);

		if (!Optional.ofNullable(todate).isPresent()) {
			toyear = "2040";
			fromyear = "01";
		} else {

			toyear = StringUtils.leftPad(String.valueOf(toyr), 4, "0");
			toweek = StringUtils.leftPad(String.valueOf(weekto), 2, "0");
		}
		if (!Optional.ofNullable(fromdate).isPresent()) {
			fromyear = "2015";
			fromweek = "01";

		} else {
			fromyear = StringUtils.leftPad(String.valueOf(yr), 4, "0");
			fromweek = StringUtils.leftPad(String.valueOf(week), 2, "0");
		}

		if (respons.getStatus().contentEquals("True")) {
			String app = null;
			List<BillPunchGstReportModel> xm = null;
			List<String> s = new ArrayList<String>();
			s.add("9");
			s.add("8");
			s.add("7");

			Calendar call = Calendar.getInstance();
			SimpleDateFormat d23 = new SimpleDateFormat("ddMM");
			String dd = d23.format(call.getTime());
			List<String> files = new ArrayList<String>();
			for (String vm : s) {
				app = null;
				if (vm.equalsIgnoreCase("8")) {

					xm = services.findAllApprovedDetailsForGstReport(fromyear,fromweek,toyear, toweek, "8");
					if (!xm.isEmpty()) {
						app = "APPAREL";
					}

				} else if (vm.equalsIgnoreCase("9")) {

					xm = services.findAllApprovedDetailsForGstReport(fromyear,fromweek,toyear, toweek,  "9");
					if (!xm.isEmpty()) {
						app = "CONTRACT";
					}

				} else if (vm.equalsIgnoreCase("7")) {

					xm = services.findAllApprovedDetailsForGstReport(fromyear,fromweek,toyear, toweek,  "7");
					if (!xm.isEmpty()) {
						app = "SANDAK";
					}

				}

				if (Optional.ofNullable(app).isPresent()) {
					File fn = new File("/var/log/batabillpunch//" + "GST" + app + dd + ".xlsx");
					fn.delete();

					String excelFilePath = "/var/log/batabillpunch//" + "GST" + app + dd + ".xlsx";

					try {

						XSSFWorkbook workbook = new XSSFWorkbook();
						XSSFSheet sheet = workbook.createSheet("XlTest");

						writeHeaderLineOne(sheet);

						writeDataLinesOne(xm, sheet);

						FileOutputStream outputStream = new FileOutputStream(excelFilePath);
						workbook.write(outputStream);
						workbook.close();

					} catch (SQLException e) {
						System.out.println("Datababse error:");
						e.printStackTrace();
					}
					files.add("/var/log/batabillpunch//" + "GST" + app + dd + ".xlsx");

				}

			}
			if (Optional.ofNullable(files).isPresent()) {

				ZipOutputStream zipOut = new ZipOutputStream(response.getOutputStream());
				for (String fileName : files) {
					FileSystemResource resource = new FileSystemResource(fileName);
					ZipEntry zipEntry = new ZipEntry(resource.getFilename());
					zipEntry.setSize(resource.contentLength());
					zipOut.putNextEntry(zipEntry);
					StreamUtils.copy(resource.getInputStream(), zipOut);
					zipOut.closeEntry();
				}
				zipOut.finish();
				zipOut.close();
				response.setStatus(HttpServletResponse.SC_OK);
				response.addHeader(HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filename=\"" + "/var/log/batabillpunch//XLZIP" + dd + ".xlxs" + "\"");
				rs.setStatus(ReraMessageConstants.SUCCESS);
				rs.setMessage(ReraMessageConstants.SUCCESS_STATUS);
				rs.setData("XLZIP" + dd + ".xlxs");
			}

		}

		else {
			rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
			rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);
		}

	}

	@SuppressWarnings("all")
	private void writeHeaderLineOne(XSSFSheet sheet) {

		Row headerRow = sheet.createRow(0);

		List<String> list1 = new ArrayList<>();
		list1.addAll(Arrays.asList("PO NO", "PO DATE", "GR NO", "GR DATE", "GRN NO", "GRN DATE", "RDC NO", "RDC NAME",
				"RDC STATE NAME", "RETURN PERIOD", "RDC GSTIN", "DOCTYPE", "INVOICE NO", "INVOICE DATE",
				"SUPPLIER GSTIN", "SUPPLIER NAME", "SUPPLIER CODE", "SUPPLIER'S STATE NAME", "HSN OR SAC", "ITEM CODE",
				"ITEM DESCRIPTION", "UNIT OF MEASUREMENT", "QUANTITY", "TAXABLEVALUE", "INTEGRATEDTAXRATE",
				"INTEGRATEDTAXAMOUNT", "CENTRALTAXRATE", "CENTRAL TAX AMOUNT", "STATE UT-TAX RATE",
				"STATE UT-TAX AMOUNT", "CESS AMOUNT", "INVOICE VALUE", "MRP", "REVERSE CHARGE FLAG",
				"ELIGIBILITY INDICATOR", "DOCUMENT NUMBER", "VOUCHER NO.", "VOUCHER DATE", "GL DATE", "PAYMENT STATUS",
				"CHEQUE NO.", "CHEQUE DATE", "PAID UPTO LAST MONTH", "OPENING UNPAID", "PAID DURING THE MONTH",
				"PAID TILL DATE", "UNPAID CLOSING BALANCE", "IGST GL CODE", "CGST GL CODE", "SGST/UTGST GL CODE",
				" CLAIM DISCOUNT", "TYPE OF ITC ", "BILL RDC STATENAME", "SHIPPED RDC STATENAME", "BILL OF ENTRY NO.",
				"BILL OF ENTRY DATE", "VALUE OF BILL OF ENTRY", "TAXABLE VALUE OF BOE", "PORT CODE + NO. BE",
				"SEZ OF GOODS"));

		for (int i = 0; i < list1.size(); i++) {
			Cell headerCell = headerRow.createCell(i);
			headerCell.setCellValue(list1.get(i));
		}

	}

	@SuppressWarnings("all")
	private void writeHeaderLineTwo(XSSFSheet sheet) {

		Row headerRow = sheet.createRow(0);

		List<String> list1 = new ArrayList<>();
		list1.addAll(Arrays.asList("WEEK YEAR", "SUPLLIER TYPE", "TOTAL PAIRS", "TOTAL INVOICE VALUE"));

		for (int i = 0; i < list1.size(); i++) {
			Cell headerCell = headerRow.createCell(i);
			headerCell.setCellValue(list1.get(i));
		}

	}

	@SuppressWarnings("all")
	private void writeDataLinesOne(List<BillPunchGstReportModel> result, XSSFSheet sheet) throws SQLException {

		int rowCount = 1;

		for (BillPunchGstReportModel xm : result) {
			Row row = sheet.createRow(rowCount++);
			List<String> list1 = new ArrayList<>();

			list1.addAll(Arrays.asList(xm.getPoNo(), xm.getPoDate(), xm.getCnNo(), xm.getCnDate(), xm.getGrNo(),
					xm.getGrDate(), xm.getRdcNo(), xm.getRdcName(), xm.getRdcStateName(), xm.getReturnPeriod(),
					xm.getRdcGstin(), xm.getDoctype(), xm.getInvoiceNo(), xm.getInvoiceDate(), xm.getSupplyGstin(),
					xm.getSupplyName(), xm.getSuplyCode(), xm.getSplyStateName(), xm.getHsn(), xm.getItemCode(),
					xm.getItemDesc(), xm.getUnitMeasure(), xm.getQuantity(), xm.getTaxableValue(), xm.getIgstrt(),
					xm.getIgstamt(), xm.getCgstrt(), xm.getCgstamt(), xm.getSgstrt(), xm.getSgstamt(), "",
					xm.getInvamt(), xm.getMrp(), "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
					xm.getClaimdst(), "", xm.getBillrdcStateName(), xm.getShippedStaeName(), "", "", "", "", "", "",
					""));
			for (int i = 0; i < 60; i++) {
				Cell cell = row.createCell(i);
				cell.setCellValue(list1.get(i));

			}

		}

	}

	@SuppressWarnings("all")
	private void writeDataLinesTwo(List<BillPunchReportResponse> result, String week, String year, List<String> type,
			XSSFSheet sheet) throws SQLException {

		int rowCount = 1;
		for (BillPunchReportResponse vm : result) {
			Row row = sheet.createRow(rowCount++);

			List<String> list1 = new ArrayList<>();

			list1.addAll(Arrays.asList(week.concat("/").concat(year), type.get(rowCount - 2), vm.getTotalpairs(),
					vm.getTotalamt()));
			for (int i = 0; i < 4; i++) {
				Cell cell = row.createCell(i);
				cell.setCellValue(list1.get(i));

			}
		}

	}

	/********************************************************************************************
	 * Find All bill punch edp reports bill punch
	 * 
	 * @throws IOException
	 ********************************************************************************************/
	@SuppressWarnings("all")
	@GetMapping("/get-bill-punch-price-report-details/{wk}/{years}")
	public void getPriceReport(HttpServletRequest req, @PathVariable("wk") String wk,
			@PathVariable("years") String years, HttpServletResponse response) throws IOException {
		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse respons = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();

		List<Integer> list = new ArrayList<Integer>();
		list.addAll(Arrays.asList(7, 9, 4, 2));

		try {
			if (respons.getStatus().contentEquals("True")) {

				List<String> sx = new ArrayList<String>();
				sx.add("9");
				sx.add("8");
				sx.add("7");

				Calendar call = Calendar.getInstance();
				SimpleDateFormat d23 = new SimpleDateFormat("ddMM");
				String dd = d23.format(call.getTime());
				List<String> files = new ArrayList<String>();
				for (String vnm : sx) {
					String app = null;
					List<BillPunchResponseInterface> xm = null;

					app = null;
					if (vnm.equalsIgnoreCase("9")) {
						xm = mservices.getPriceReportDetailsByWeek(wk, years, "9");
						if (!xm.isEmpty()) {
							app = "9PP".concat(years.substring(3, 4)) + wk;
						}

					} else if (vnm.equalsIgnoreCase("8")) {
						xm = mservices.getPriceReportDetailsByWeek(wk, years, "8");
						if (!xm.isEmpty()) {
							app = "8PP".concat(years.substring(3, 4)) + wk;
						}

					} else if (vnm.equalsIgnoreCase("7")) {
						xm = mservices.getPriceReportDetailsByWeek(wk, years, "7");
						if (!xm.isEmpty()) {
							app = "7PP".concat(years.substring(3, 4)) + wk;
						}

					}

					PurchaseCostInterface ch = null;
					try {

						if (Optional.ofNullable(xm).isPresent() && Optional.ofNullable(app).isPresent()) {
							File fn = new File("/var/log/batabillpunch//" + app + ".txt");
							fn.delete();

							File f = new File("/var/log/batabillpunch//" + app + ".txt");
							FileWriter writer = new FileWriter(f, true);
							String w1 = null;
							for (BillPunchResponseInterface vm : xm) {

								List<String> list1 = new ArrayList<>();
								list1.addAll(Arrays.asList(vm.getarticleCode(),
										StringUtils.leftPad(vm.getpurchaseCostreport(), 9, "0"), vm.getyr(),
										vm.getbillWeek()));

								for (int i = 0; i < list1.size(); i++) {
									String s1 = list1.get(i);
									int size = list.get(i);

									if (Optional.ofNullable(s1).isPresent()) {
										String s = s1;
										StringBuilder buf = new StringBuilder(s);
										if (buf.length() == size) {
											String output = buf.toString();
											w1 = output;
										} else {

											if (buf.length() < size) {

												while (buf.length() < size) {
													buf.insert(0, '0');
													String output = buf.toString();
													w1 = output;
												}
											} else {
												String sxm = buf.substring(0, size);
												w1 = sxm;
											}
										}

									} else {
										StringBuilder buf = new StringBuilder(0);
										while (buf.length() < size) {
											buf.insert(0, '0');
											String output = buf.toString();
											w1 = output;
										}
									}
									writer.write(w1);

								}
								writer.write("\r\n");
							}

							writer.close();
						}

						System.out.println("Write success!");

					} catch (Exception e) {
						e.printStackTrace();
					}

					if (Optional.ofNullable(app).isPresent()) {
						files.add("/var/log/batabillpunch//" + app + ".txt");
					}

				}

				if (Optional.ofNullable(files).isPresent()) {

					ZipOutputStream zipOut = new ZipOutputStream(response.getOutputStream());
					for (String fileName : files) {
						FileSystemResource resource = new FileSystemResource(fileName);
						ZipEntry zipEntry = new ZipEntry(resource.getFilename());
						zipEntry.setSize(resource.contentLength());
						zipOut.putNextEntry(zipEntry);
						StreamUtils.copy(resource.getInputStream(), zipOut);
						zipOut.closeEntry();
					}
					zipOut.finish();
					zipOut.close();
					response.setStatus(HttpServletResponse.SC_OK);
					response.addHeader(HttpHeaders.CONTENT_DISPOSITION,
							"attachment; filename=\"" + "/var/log/batabillpunch//PRICEZIP" + dd + ".txt" + "\"");

					rs.setStatus(ReraMessageConstants.SUCCESS);
					rs.setMessage(ReraMessageConstants.SUCCESS_STATUS);
					rs.setData("PRICEZIP" + dd + ".txt");

				}

			}

			else {
				rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
				rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);
			}
		} catch (Exception e) {

			System.out.println("Error ########################################");
			e.printStackTrace();
		}

	}

	/********************************************************************************************
	 * Find All bill punch edp reports bill punch
	 * 
	 * @throws IOException
	 ********************************************************************************************/
	@SuppressWarnings("all")
	@GetMapping("/get-bill-punch-edp-report-details/{wk}/{years}")
	public void getEdpReport(HttpServletRequest req, HttpServletResponse response, @PathVariable("wk") String wk,
			@PathVariable("years") String years) throws IOException {
		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		// TokenRequest request = new TokenRequest();
		// request.setToken(getToken(req));
		// TokenResponse respons = restTemplate.postForEntity(tokenurl, request,
		// TokenResponse.class).getBody();

		List<Integer> list = new ArrayList<Integer>();
		list.addAll(Arrays.asList(1, 2, 4, 1, 1, 3, 5, 15, 3, 5, 5, 5, 11, 11, 7, 11, 3, 1, 1, 4, 2, 15, 2, 2, 4, 20,
				10, 2, 11, 15, 2, 2, 4, 2, 2, 4, 2, 5, 2, 2, 4, 10, 5, 2, 11, 11, 11, 11, 5));

		try {
			if ("True".contentEquals("True")) {

				List<BillPunchEdpReportModel> xm = null;

				BillPunchEdpInterface xmm = null;
				BillPunchEdpInterface xmn = null;

				String app = null;
				List<String> ss = new ArrayList<String>();
				ss.add("9");
				ss.add("8");
				ss.add("7");

				Calendar call = Calendar.getInstance();
				SimpleDateFormat d23 = new SimpleDateFormat("ddMM");
				String dd = d23.format(call.getTime());
				// String dd = d34.replace(':', '_');
				List<String> files = new ArrayList<String>();

				List<String> type = new ArrayList<String>();

				List<BillPunchReportResponse> depxl = new ArrayList<>();
				for (String vmm : ss) {
					app = null;
					String w = null;
					if (vmm.equalsIgnoreCase("9")) {
						xm = mservices.findAllApprovedDetailsEdp(wk, years);
						xmn = mservices.getReportDetailsByWeekEdpXlPairs(wk, years);
						xmm = mservices.getReportDetailsByWeekEdpXl(wk, years);
						BillPunchReportResponse obj = new BillPunchReportResponse();
						if (xmm != null && xmn != null) {
							obj.setTotalamt(xmm.gettotalamt());
							obj.setTotalpairs(xmn.gettotalpairs());
						}

						if (!xm.isEmpty()) {
							depxl.add(obj);
							type.add("CONTRACT");
							app = "CONTRACT";
							w = "9";

						}

					} else if (vmm.equalsIgnoreCase("8")) {
						xm = mservices.findAllApprovedDetailsEdp(wk, years);
						xmn = mservices.getReportDetailsByWeekEdpXlPairs(wk, years);
						xmm = mservices.getReportDetailsByWeekEdpXl(wk, years);
						BillPunchReportResponse obj = new BillPunchReportResponse();

						if (xmm != null && xmn != null) {
							obj.setTotalamt(xmm.gettotalamt());
							obj.setTotalpairs(xmn.gettotalpairs());
						}

						if (!xm.isEmpty()) {
							depxl.add(obj);
							type.add("APPAREL");
							app = "APPAREL";
							w = "8";
						}

					} else if (vmm.equalsIgnoreCase("7")) {
						xm = mservices.findAllApprovedDetailsEdp(wk, years);
						xmn = mservices.getReportDetailsByWeekEdpXlPairs(wk, years);
						xmm = mservices.getReportDetailsByWeekEdpXl(wk, years);
						BillPunchReportResponse obj = new BillPunchReportResponse();
						if (xmm != null && xmn != null) {
							obj.setTotalamt(xmm.gettotalamt());
							obj.setTotalpairs(xmn.gettotalpairs());
						}

						if (!xm.isEmpty()) {
							depxl.add(obj);
							type.add("SANDAK");
							app = "SANDAK";
							w = "7";
						}

					}
					try {

						if (Optional.ofNullable(app).isPresent()) {
							File fn = new File("D:/batabillpunchreport//" + "EXPDT" + wk + w + ".EDP");
							fn.delete();

							File f = new File("D:/batabillpunchreport//" + "EXPDT" + wk + w + ".EDP");
							FileWriter writer = new FileWriter(f, true);
							if (Optional.ofNullable(xm).isPresent()) {
								String w1 = null;
								Double artamtone = 0d;
								Double artamttwo = 0d;
								Double artamtthree = 0d;

								for (BillPunchEdpReportModel vm : xm) {

									String af1 = null;
									String af2 = null;
									if (app.equalsIgnoreCase("APPAREL")) {
										af1 = "00000";
										af2 = String.valueOf(vm.getPair());
									} else if (app.equalsIgnoreCase("CONTRACT")) {
										af1 = String.valueOf(vm.getPair());
										af2 = "00000";
									} else if (app.equalsIgnoreCase("SANDAK")) {
										af1 = String.valueOf(vm.getPair());
										af2 = "00000";
									}
									String price = null;
									if (vm.getDistNo().substring(0, 1).equalsIgnoreCase("6")) {
										price = vm.getAmtwsp();
									} else {
										price = vm.getAmtone();
									}

									List<String> list1 = new ArrayList<>();
									list1.addAll(Arrays.asList(w, wk, years, "2", "2", "999", vm.getRdcCode(),
											StringUtils.rightPad(vm.getInvoiceNo(), 15, " "), vm.getDistNo(), af1, af2,
											vm.getPackCaset(), StringUtils.leftPad(vm.getAmtone(), 11, "0"),
											StringUtils.leftPad(vm.getAmttwo(), 11, "0"), "0000000",
											StringUtils.leftPad(price, 11, "0"), vm.getPartyCode().substring(2, 5), "1",
											"9", years, wk, vm.getCnNo(), vm.getDateOne(), vm.getDateTwo(),
											vm.getDateThree(), StringUtils.leftPad(vm.getTransportCode(), 20, " "),
											vm.getPermitNo(), vm.getStateCode(),
											StringUtils.leftPad(vm.getInvAmt(), 11, "0"),
											StringUtils.rightPad(vm.getGrNo(), 15, " "), vm.getDateTwoOne(),
											vm.getDateTwoTwo(), vm.getDateTwoThree(), vm.getDateThreeOne(),
											vm.getDateThreeTwo(), vm.getDateThreeThree(), vm.getResumeInvNO(), "     ",
											vm.getDateFourOne(), vm.getDateFourTwo(), vm.getDateFourThree(),
											vm.getResumeInvNOTwo(), "     ", "  ",
											StringUtils.leftPad(vm.getAmtThree(), 11, "0"),
											StringUtils.leftPad(vm.getCgstamt(), 11, "0"),
											StringUtils.leftPad(vm.getSgstamt(), 11, "0"),
											StringUtils.leftPad(vm.getIgstamt(), 11, "0"), "     "));

									for (int i = 0; i < list1.size(); i++) {
										String s1 = list1.get(i);
										int size = list.get(i);

										if (Optional.ofNullable(s1).isPresent()) {
											String s = s1;
											StringBuilder buf = new StringBuilder(s);
											if (buf.length() == size) {
												String output = buf.toString();
												w1 = output;
											} else {

												if (buf.length() < size) {

													while (buf.length() < size) {
														buf.insert(0, '0');
														String output = buf.toString();
														w1 = output;
													}
												} else {
													String sx = buf.substring(0, size);
													w1 = sx;
												}
											}

										} else {
											StringBuilder buf = new StringBuilder(0);
											while (buf.length() < size) {
												buf.insert(0, '0');
												String output = buf.toString();
												w1 = output;
											}
										}
										writer.write(w1);

									}
									writer.write("\r\n");
								}

								writer.close();
							}
							files.add("D:/batabillpunchreport//" + "EXPDT" + wk + w + ".EDP");
						}

						System.out.println("Write success!");

					} catch (Exception e) {
						e.printStackTrace();
					}

				}

				File fn = new File("D:/batabillpunchreport//" + "EXPDTXL" + dd + ".xlsx");
				fn.delete();

				String excelFilePath = "D:/batabillpunchreport//" + "EXPDTXL" + dd + ".xlsx";

				try {

					XSSFWorkbook workbook = new XSSFWorkbook();
					XSSFSheet sheet = workbook.createSheet("EdpXlTest");

					writeHeaderLineTwo(sheet);

					writeDataLinesTwo(depxl, wk, years, type, sheet);

					FileOutputStream outputStream = new FileOutputStream(excelFilePath);
					workbook.write(outputStream);
					workbook.close();

				} catch (SQLException e) {
					System.out.println("Datababse error:");
					e.printStackTrace();
				}
				files.add("D:/batabillpunchreport//" + "EXPDTXL" + dd + ".xlsx");

				if (Optional.ofNullable(files).isPresent()) {

					ZipOutputStream zipOut = new ZipOutputStream(response.getOutputStream());
					for (String fileName : files) {
						FileSystemResource resource = new FileSystemResource(fileName);
						ZipEntry zipEntry = new ZipEntry(resource.getFilename());
						zipEntry.setSize(resource.contentLength());
						zipOut.putNextEntry(zipEntry);
						StreamUtils.copy(resource.getInputStream(), zipOut);
						zipOut.closeEntry();
					}
					zipOut.finish();
					zipOut.close();
					response.setStatus(HttpServletResponse.SC_OK);
					response.addHeader(HttpHeaders.CONTENT_DISPOSITION,
							"attachment; filename=\"" + "D:/batabillpunchreport//EDPZIP" + dd + ".EDP" + "\"");

					rs.setStatus(ReraMessageConstants.SUCCESS);
					rs.setMessage(ReraMessageConstants.SUCCESS_STATUS);
					rs.setData("EDPZIP" + dd + ".EDP");

				}
			}
			rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
			rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);

		} catch (Exception e) {

			System.out.println("Error ########################################");
			e.printStackTrace();
		}

	}

	/********************************************************************************************
	 * Find All bill punch trans reports
	 * 
	 * @throws IOException
	 ********************************************************************************************/
	@SuppressWarnings("all")
	@GetMapping("/get-bill-punch-trans-report-details/{wk}/{years}")
	public void getTransReport(HttpServletRequest req, HttpServletResponse response, @PathVariable("wk") String wk,
			@PathVariable("years") String years) throws IOException {
		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse respons = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();
		List<Integer> list = new ArrayList<>();
		list.addAll(Arrays.asList(1, 2, 4, 1, 15, 7, 3, 4, 2, 5, 3, 1, 5, 6, 5, 5, 5, 5, 5, 1, 1, 7, 3, 2, 15, 8, 20,
				10, 2, 11, 15, 8, 7, 2, 2, 4, 10, 7, 8, 7, 11, 5, 4, 11, 4, 11, 4, 11));

		if (respons.getStatus().contentEquals("True")) {

			List<BillPunchTransReportModel> xm = null;
			String app = null;
			List<String> ss = new ArrayList<String>();
			ss.add("9");
			ss.add("8");
			ss.add("7");

			Calendar call = Calendar.getInstance();
			SimpleDateFormat d23 = new SimpleDateFormat("ddMM");
			String dd = d23.format(call.getTime());
			// String dd = d34.replace(':', '_');
			List<String> files = new ArrayList<String>();
			for (String vmm : ss) {
				app = null;
				String w = null;
				if (vmm.equalsIgnoreCase("9")) {
					xm = mservices.findAllApprovedDetailsForTransReport(wk, years, "9");
					if (!xm.isEmpty()) {
						app = "CONTRACT";
						w = "9";

					}

				} else if (vmm.equalsIgnoreCase("8")) {
					xm = mservices.findAllApprovedDetailsForTransReport(wk, years, "8");
					if (!xm.isEmpty()) {
						app = "APPAREL";
						w = "8";
					}

				} else if (vmm.equalsIgnoreCase("7")) {
					xm = mservices.findAllApprovedDetailsForTransReport(wk, years, "7");
					if (!xm.isEmpty()) {
						app = "SANDAK";
						w = "7";
					}

				}

				try {

					if (Optional.ofNullable(app).isPresent()) {
						File fn = new File("/var/log/batabillpunch//" + "TRANS" + wk + w + ".EDP");
						fn.delete();

						File f = new File("/var/log/batabillpunch//" + "TRANS" + wk + w + ".EDP");// curedatewith
						FileWriter writer = new FileWriter(f, true);
						if (Optional.ofNullable(xm).isPresent()) {
							String w1 = null;
							for (BillPunchTransReportModel vm : xm) {

								List<String> list1 = new ArrayList<>();
								list1.addAll(Arrays.asList(w, wk, years, "2",
										StringUtils.rightPad(vm.getInvoiceNo(), 15, " "), vm.getArticleCode(),
										vm.getSizeCode(), years, wk, vm.getRdcCode(), vm.getDistNo(), "1", vm.getPair(),
										vm.getTotalPair(), vm.getPackCaseb(), vm.getPackCasem(), vm.getPackCases(),
										vm.getPackCasec(), vm.getPackCaset(), vm.getDcCode(), " ", vm.getArtSequence(),
										vm.getPartyCode().substring(2, 5), "  ", vm.getCnNo(), vm.getCnDate(),
										vm.getTransportCode(), vm.getPermitNo(), vm.getStateCode(),
										StringUtils.leftPad(vm.getInvAmt(), 11, "0"),
										StringUtils.rightPad(vm.getGrNo(), 15, " "), vm.getRecDate(),
										vm.getResumeInvNO(), vm.getDateOne(), vm.getDateTwo(), vm.getDateThree(),
										vm.getResumeInvNOTwo(), vm.getBillOrdNo(), "        ", vm.getBillOrdNoOne(),
										StringUtils.leftPad(vm.getArtCost(), 11, "0"), "     ",
										StringUtils.leftPad(vm.getCgst(), 4, "0"),
										StringUtils.leftPad(vm.getCgstamt(), 11, "0"),
										StringUtils.leftPad(vm.getSgst(), 4, "0"),
										StringUtils.leftPad(vm.getSgstamt(), 11, "0"),
										StringUtils.leftPad(vm.getIgst(), 4, "0"),
										StringUtils.leftPad(vm.getIgstamt(), 11, "0")));
								for (int i = 0; i < list1.size(); i++) {
									String s1 = list1.get(i);
									int size = list.get(i);

									if (Optional.ofNullable(s1).isPresent()) {
										String s = s1;
										StringBuilder buf = new StringBuilder(s);
										if (buf.length() == size) {
											String output = buf.toString();
											w1 = output;
										} else {

											if (buf.length() < size) {

												while (buf.length() < size) {
													buf.insert(0, '0');
													String output = buf.toString();
													w1 = output;
												}
											} else {
												String sx = buf.substring(0, size);
												w1 = sx;
											}
										}

									} else {
										StringBuilder buf = new StringBuilder(0);
										while (buf.length() < size) {
											buf.insert(0, '0');
											String output = buf.toString();
											w1 = output;
										}
									}
									writer.write(w1);
								}

								writer.write("\r\n");
							}

							writer.close();
						}
						files.add("/var/log/batabillpunch//" + "TRANS" + wk + w + ".EDP");
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
			if (Optional.ofNullable(files).isPresent()) {

				ZipOutputStream zipOut = new ZipOutputStream(response.getOutputStream());
				for (String fileName : files) {
					FileSystemResource resource = new FileSystemResource(fileName);
					ZipEntry zipEntry = new ZipEntry(resource.getFilename());
					zipEntry.setSize(resource.contentLength());
					zipOut.putNextEntry(zipEntry);
					StreamUtils.copy(resource.getInputStream(), zipOut);
					zipOut.closeEntry();
				}
				zipOut.finish();
				zipOut.close();
				response.setStatus(HttpServletResponse.SC_OK);
				response.addHeader(HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filename=\"" + "/var/log/batabillpunch//TRANSZIP" + dd + ".EDP" + "\"");

				rs.setStatus(ReraMessageConstants.SUCCESS);
				rs.setMessage(ReraMessageConstants.SUCCESS_STATUS);
				rs.setData("TRANSZIP" + dd + ".EDP");

			}
		}

		else {
			rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
			rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);
		}

	}

	/********************************************************************************************
	 * Find All bill punch contract batch reports
	 * 
	 * @throws IOException
	 ********************************************************************************************/
	@SuppressWarnings("all")
	@GetMapping("/get-bill-punch-contract-batch-report-details/{wk}/{year}")
	public void getReportForContractBatchFile(HttpServletRequest req, HttpServletResponse response,
			@PathVariable("wk") String wk, @PathVariable("year") String year) throws IOException {
		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse respons = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();
		if (respons.getStatus().contentEquals("True")) {
			String w1 = null;
			List<AdonisFileDetailsInterface> xm = null;
			TotalAmtInterface totalamt = null;
			AdonisMasterModel ad = null;
			String app = null;
			String batchno = null;
			List<String> ss = new ArrayList<String>();
			ss.add("9");
			ss.add("8");
			ss.add("7");

			Calendar call = Calendar.getInstance();
			SimpleDateFormat d23 = new SimpleDateFormat("ddMM");
			String dd = d23.format(call.getTime());
			// String dd = d34.replace(':', '_');
			List<String> files = new ArrayList<String>();
			for (String vmm : ss) {
				ad = adservices.getAdonisDetails();
				app = null;
				String abc = null;
				String abx = null;
				String x = null;
				String y = null;
				if (vmm.equalsIgnoreCase("9")) {
					totalamt = mservices.getTotalAmountForAdonis(wk, year, "9");
					xm = mservices.getAdonisDetails(wk, year, "9");
					if (!xm.isEmpty()) {
						app = "CONTRACT";
						abc = "686";
						abx = "686";
						x = "1101C  ";
						y = "01C    ";
						batchno = ad.getConBatchNo();

					}

				} else if (vmm.equalsIgnoreCase("8")) {
					totalamt = mservices.getTotalAmountForAdonis(wk, year, "8");
					xm = mservices.getAdonisDetails(wk, year, "8");
					if (!xm.isEmpty()) {
						app = "APPAREL";
						abc = "611";
						abx = "611";
						x = "1102  ";
						y = "02    ";
						batchno = ad.getAppBatchNo();
					}

				} else if (vmm.equalsIgnoreCase("7")) {
					totalamt = mservices.getTotalAmountForAdonis(wk, year, "7");
					xm = mservices.getAdonisDetails(wk, year, "7");
					if (!xm.isEmpty()) {
						app = "SANDAK";
						abc = "686";
						abx = "696";

						x = "1101  ";
						y = "07    ";
						batchno = ad.getSandBatchNo();
					}

				}

				if (Optional.ofNullable(app).isPresent()) {
					String pr = null;
					String xv = String.format("%.2f", Double.valueOf(totalamt.gettotalcost()));

					File fn = new File("/var/log/batabillpunch//" + app.substring(0, 3) + dd + ".txt");
					fn.delete();

					File f = new File("/var/log/batabillpunch//" + app.substring(0, 3) + dd + ".txt");
					FileWriter writer = new FileWriter(f, true);

					List<Integer> list = new ArrayList<>();
					list.addAll(Arrays.asList(1, 7, 6, 10, 10, 7, 2, 50, 6, 15, 2, 15, 2, 132));

					List<Integer> list2 = new ArrayList<>();
					list2.addAll(Arrays.asList(1, 4, 4, 8, 6, 5, 4, 4, 30, 1, 15, 2, 30, 10, 3, 14, 10, 23, 28, 10, 6,
							7, 12, 11, 11, 1, 4, 1, 15, 2));
					String week = null;
					for (AdonisFileDetailsInterface vm : xm) {

						try {
							List<AdonisWeekMasterModel> wks = adwservice.getAllWeek();
							for (AdonisWeekMasterModel km : wks) {
								AdonisFileDetailsInterface add = mservices.getAdonisDetailsweek(vm.getordno(),
										vm.getinvno(), wk);
								Date cal = add.getcreatedOn();
								SimpleDateFormat d11 = new SimpleDateFormat("yyyy-MM-dd");
								String d2 = d11.format(cal);
								Date d22 = km.getFromdate();
								String d1 = d11.format(d22);
								Date d33 = km.getTodate();
								String d3 = d11.format(d33);

								if (d2.compareTo(d1) >= 0) {
									if (d2.compareTo(d3) <= 0) {
										pr = km.getPeriod();
										week = km.getWeek();
										if (week == null) {
											week = wk;
										}
									}
								}
							}

						} catch (Exception e) {
							e.printStackTrace();
						}
						break;
					}

					try {

						String pattern = "dd/MM/yyyy";
						String cdate = new SimpleDateFormat(pattern).format(new Date());
						// String pat = "yyyyMM";
						// yr = new SimpleDateFormat(pat).format(new Date());
						List<String> list1 = new ArrayList<>();
						list1.addAll(Arrays.asList("C", "ADP3COM", batchno, cdate, "RIMS      ",
								pr.substring(0, 4).concat("/").concat(pr.substring(4, 6)),
								StringUtils.leftPad("", 2, " "),
								StringUtils.rightPad(app.concat(" BILL ACCOUNTING, WEEK ").concat(week), 50, " "),

								StringUtils.leftPad(String.valueOf(xm.size() * 4), 6, "0"), xv, "00", xv, "00",
								StringUtils.leftPad("", 132, " ")));

						for (int i = 0; i < list1.size(); i++) {
							String s1 = list1.get(i);
							int size = list.get(i);

							if (Optional.ofNullable(s1).isPresent()) { // for present value
								String s = s1;
								StringBuilder buf = new StringBuilder(s);
								if (buf.length() == size) { // for equal size
									String output = buf.toString();
									w1 = output;
								} else {

									if (buf.length() < size) { // for less size

										while (buf.length() < size) {
											buf.insert(0, '0');
											String output = buf.toString();
											w1 = output;
										}
									} else { // for greater size
										String sx = buf.substring(0, size);
										w1 = sx;
									}
								}

							} else { // for null value
								StringBuilder buf = new StringBuilder(0);
								while (buf.length() < size) {
									buf.insert(0, '0');
									String output = buf.toString();
									w1 = output;
								}
							}
							writer.write(w1);

						}
						writer.write("\r\n");

					} catch (Exception e) {
						e.printStackTrace();
					}
					int j = 0;
					for (AdonisFileDetailsInterface vm : xm) {

						ShopMasterModel sp = sservices.getShopDetails(vm.getrdcno());
						DistMasterModel dt = dservices.getDistDetails(sp.getDistrictno());

						PartiesMasterModel sf = pservices.getPartiesDetails(StringUtils.leftPad(vm.getparty(), 4, "0"));

						String pattern = "dd/MM/yyyy";
						String curdate = new SimpleDateFormat(pattern).format(new Date());
						String date1 = "00/00/0000";
						DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

						try {
							if (Optional.ofNullable(vm.getinvdate()).isPresent()) {
								Date d1 = vm.getinvdate();
								date1 = dateFormat.format(d1);
							}
						} catch (Exception e) {
						}
						Date d2 = vm.getgrndate();
						String date2 = dateFormat.format(d2);

						List<String> list3 = new ArrayList<>();
						list3.addAll(Arrays.asList("D", "0001", StringUtils.rightPad(abc, 4, " "), "1041    ",
								StringUtils.rightPad(" ", 6, " "), StringUtils.rightPad("", 5, " "),
								StringUtils.rightPad("", 4, " "), StringUtils.rightPad("", 4, " "),
								StringUtils.rightPad(vm.getinvno(), 30, " "), "C",
								String.format("%.2f", Double.valueOf(vm.getinvtotalcost())), "00",
								"Dt. " + date1 + " " + "Gdt. " + date2, StringUtils.rightPad("", 10, " "), "INR",
								StringUtils.rightPad(" ", 14, " "), curdate, StringUtils.rightPad("", 23, " "),
								StringUtils.rightPad("", 28, " "), StringUtils.rightPad("", 10, "0"),
								StringUtils.rightPad("", 6, " "), StringUtils.rightPad("", 7, "0"),
								StringUtils.leftPad(vm.getpairs(), 12, "0"),
								StringUtils.rightPad(sf.getAdonisCode(), 11, " "), StringUtils.rightPad("", 11, " "),
								"V", StringUtils.rightPad("", 4, " "), "I",
								String.format("%.2f", Double.valueOf(vm.getinvcost())), "00"));

						for (int i = 0; i < list3.size(); i++) {
							String s1 = list3.get(i);
							int size = list2.get(i);

							if (Optional.ofNullable(s1).isPresent()) {
								String s = s1;
								StringBuilder buf = new StringBuilder(s);
								if (buf.length() == size) {
									String output = buf.toString();
									w1 = output;
								} else {

									if (buf.length() < size) {

										while (buf.length() < size) {
											buf.insert(0, '0');
											String output = buf.toString();
											w1 = output;
										}
									} else {
										String sx = buf.substring(0, size);
										w1 = sx;
									}
								}

							} else {
								StringBuilder buf = new StringBuilder(0);
								while (buf.length() < size) {
									buf.insert(0, '0');
									String output = buf.toString();
									w1 = output;
								}
							}
							writer.write(w1);

						}
						writer.write("\r\n");

						List<String> list4 = new ArrayList<>();
						list4.addAll(Arrays.asList("D", "0002", StringUtils.rightPad(abc, 4, " "), "1199    ", x,
								StringUtils.rightPad(abx, 5, " "), StringUtils.rightPad("", 4, " "), "07  ",
								StringUtils.rightPad(vm.getinvno(), 30, " "), "D",
								String.format("%.2f", Double.valueOf(vm.getinvtotalcost())), "00",
								"Dt. " + date1 + " " + "Gdt. " + date2, StringUtils.rightPad("", 10, " "), "INR",
								StringUtils.rightPad("", 14, " "), curdate, StringUtils.rightPad("", 23, " "),
								StringUtils.rightPad("", 28, " "), StringUtils.rightPad("", 10, "0"),
								StringUtils.rightPad("", 6, " "), StringUtils.rightPad("", 7, "0"),
								StringUtils.leftPad(vm.getpairs(), 12, "0"), StringUtils.rightPad("", 11, " "),
								StringUtils.rightPad("", 11, " "), "G", StringUtils.rightPad("", 4, " "),
								StringUtils.rightPad("", 1, " "),
								String.format("%.2f", Double.valueOf(vm.getinvcost())), "00"));

						for (int i = 0; i < list4.size(); i++) {
							String s1 = list4.get(i);
							int size = list2.get(i);

							if (Optional.ofNullable(s1).isPresent()) {
								String s = s1;
								StringBuilder buf = new StringBuilder(s);
								if (buf.length() == size) {
									String output = buf.toString();
									w1 = output;
								} else {

									if (buf.length() < size) {

										while (buf.length() < size) {
											buf.insert(0, '0');
											String output = buf.toString();
											w1 = output;
										}
									} else {
										String sx = buf.substring(0, size);
										w1 = sx;
									}
								}

							} else {
								StringBuilder buf = new StringBuilder(0);
								while (buf.length() < size) {
									buf.insert(0, '0');
									String output = buf.toString();
									w1 = output;
								}
							}
							writer.write(w1);

						}

						writer.write("\r\n");
						List<String> list5 = new ArrayList<>();
						list5.addAll(Arrays.asList("D", "0003", StringUtils.rightPad(abc, 4, " "), "1500    ", y,
								StringUtils.rightPad(abx, 5, " "), StringUtils.rightPad("", 4, " "), "07  ",
								StringUtils.rightPad(vm.getinvno(), 30, " "), "C",
								String.format("%.2f", Double.valueOf(vm.getinvtotalcost())), "00",
								"Dt. " + date1 + " " + "Gdt. " + date2, StringUtils.rightPad("", 10, " "), "INR",
								StringUtils.rightPad("", 14, " "), curdate, StringUtils.rightPad("", 23, " "),
								StringUtils.rightPad("", 28, " "), StringUtils.rightPad("", 10, "0"),
								StringUtils.rightPad("", 6, " "), StringUtils.rightPad("", 7, "0"),
								StringUtils.leftPad(vm.getpairs(), 12, "0"), StringUtils.rightPad("", 11, " "),
								StringUtils.rightPad("", 11, " "), "G", StringUtils.rightPad("", 4, " "),
								StringUtils.rightPad("", 1, " "),
								String.format("%.2f", Double.valueOf(vm.getinvcost())), "00"));

						for (int i = 0; i < list5.size(); i++) {
							String s1 = list5.get(i);
							int size = list2.get(i);

							if (Optional.ofNullable(s1).isPresent()) {
								String s = s1;
								StringBuilder buf = new StringBuilder(s);
								if (buf.length() == size) {
									String output = buf.toString();
									w1 = output;
								} else {

									if (buf.length() < size) {

										while (buf.length() < size) {
											buf.insert(0, '0');
											String output = buf.toString();
											w1 = output;
										}
									} else {
										String sx = buf.substring(0, size);
										w1 = sx;
									}
								}

							} else {
								StringBuilder buf = new StringBuilder(0);
								while (buf.length() < size) {
									buf.insert(0, '0');
									String output = buf.toString();
									w1 = output;
								}
							}
							writer.write(w1);

						}

						writer.write("\r\n");
						List<String> list6 = new ArrayList<>();
						list6.addAll(Arrays.asList("D", "0004", StringUtils.rightPad(abc, 4, " "), "1057    ",
								StringUtils.rightPad(dt.getDistconceptcontrollinggroup(), 6, " "),
								StringUtils.rightPad("", 5, " "), StringUtils.rightPad("", 4, " "),
								StringUtils.rightPad("", 4, " "), StringUtils.rightPad(vm.getinvno(), 30, " "), "D",
								String.format("%.2f", Double.valueOf(vm.getinvtotalcost())), "00",
								"Dt. " + date1 + " " + "Gdt. " + date2, StringUtils.rightPad("", 10, " "), "INR",
								StringUtils.rightPad("", 14, " "), curdate, StringUtils.rightPad("", 23, " "),
								StringUtils.rightPad("", 28, " "), StringUtils.rightPad("", 10, "0"),
								StringUtils.rightPad("", 6, " "), StringUtils.rightPad("", 7, "0"),
								StringUtils.leftPad(vm.getpairs(), 12, "0"), StringUtils.rightPad("", 11, " "),
								StringUtils.rightPad("", 11, " "), "G", StringUtils.rightPad("", 4, " "),
								StringUtils.rightPad("", 1, " "),
								String.format("%.2f", Double.valueOf(vm.getinvcost())), "00"));

						for (int i = 0; i < list6.size(); i++) {
							String s1 = list6.get(i);
							int size = list2.get(i);

							if (Optional.ofNullable(s1).isPresent()) {
								String s = s1;
								StringBuilder buf = new StringBuilder(s);
								if (buf.length() == size) {
									String output = buf.toString();
									w1 = output;
								} else {

									if (buf.length() < size) {

										while (buf.length() < size) {
											buf.insert(0, '0');
											String output = buf.toString();
											w1 = output;
										}
									} else {
										String sx = buf.substring(0, size);
										w1 = sx;
									}
								}

							} else {
								StringBuilder buf = new StringBuilder(0);
								while (buf.length() < size) {
									buf.insert(0, '0');
									String output = buf.toString();
									w1 = output;
								}
							}
							writer.write(w1);

						}

						if (j++ != xm.size() - 1) {
							writer.write("\r\n");
						} else {
							writer.write("\r\n");
						}

					}
					writer.close();
					Integer nt = 0;
					if (app.equalsIgnoreCase("APPAREL")) {
						nt = Integer.parseInt(ad.getAppBatchNo()) + 1;
						ad.setAppBatchNo(String.valueOf(nt));
					} else if (app.equalsIgnoreCase("CONTRACT")) {
						nt = Integer.parseInt(ad.getConBatchNo()) + 1;
						ad.setConBatchNo(String.valueOf(nt));
					} else if (app.equalsIgnoreCase("SANDAK")) {
						nt = Integer.parseInt(ad.getSandBatchNo()) + 1;
						ad.setSandBatchNo(String.valueOf(nt));
					}

					adservices.save(ad);

					files.add("/var/log/batabillpunch//" + app.substring(0, 3) + dd + ".txt");

				}

			}
			if (Optional.ofNullable(files).isPresent()) {

				ZipOutputStream zipOut = new ZipOutputStream(response.getOutputStream());
				for (String fileName : files) {
					FileSystemResource resource = new FileSystemResource(fileName);
					ZipEntry zipEntry = new ZipEntry(resource.getFilename());
					zipEntry.setSize(resource.contentLength());
					zipOut.putNextEntry(zipEntry);
					StreamUtils.copy(resource.getInputStream(), zipOut);
					zipOut.closeEntry();
				}
				zipOut.finish();
				zipOut.close();
				response.setStatus(HttpServletResponse.SC_OK);
				response.addHeader(HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filename=\"" + "/var/log/batabillpunch//ADONISZIP" + dd + ".txt" + "\"");

				rs.setStatus(ReraMessageConstants.SUCCESS);
				rs.setMessage(ReraMessageConstants.SUCCESS_STATUS);
				rs.setData("ADONISZIP" + dd + ".xlxs");

			}

		}

		else

		{
			rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
			rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);
		}
	}

	/********************************************************************************************
	 * Find All bill punch edp reports
	 * 
	 * @throws IOException
	 ********************************************************************************************/
	@SuppressWarnings("all")
	@GetMapping("/get-bill-punch-xl-report-details-for-supply/{wk}/{year}")
	public void getXlReport(HttpServletRequest req, HttpServletResponse response, @PathVariable("wk") String wk,
			@PathVariable("year") String year) throws IOException {
		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse respons = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();

		if (respons.getStatus().contentEquals("True")) {
			String app = null;
			List<BillPunchSupplyReportModel> xm = null;
			List<String> s = new ArrayList<String>();
			s.add("9");
			s.add("8");
			s.add("7");

			Calendar call = Calendar.getInstance();
			SimpleDateFormat d23 = new SimpleDateFormat("ddMM");
			String dd = d23.format(call.getTime());
			List<String> files = new ArrayList<String>();
			for (String vm : s) {
				app = null;
				if (vm.equalsIgnoreCase("8")) {
					xm = services.findAllApprovedDetails(wk, year, "8");
					if (!xm.isEmpty()) {
						app = "APPAREL";
					}

				} else if (vm.equalsIgnoreCase("9")) {
					xm = services.findAllApprovedDetails(wk, year, "9");
					if (!xm.isEmpty()) {
						app = "CONTRACT";
					}

				} else if (vm.equalsIgnoreCase("7")) {
					xm = services.findAllApprovedDetails(wk, year, "7");
					if (!xm.isEmpty()) {
						app = "SANDAK";
					}

				}

				if (Optional.ofNullable(app).isPresent()) {
					File fn = new File("/var/log/batabillpunch//" + "SUPPLY" + app + dd + ".xlsx");
					fn.delete();

					String excelFilePath = "/var/log/batabillpunch//" + "SUPPLY" + app + dd + ".xlsx";

					try {

						XSSFWorkbook workbook = new XSSFWorkbook();
						XSSFSheet sheet = workbook.createSheet("XlTest");

						writeHeaderLine(sheet);

						writeDataLines(xm, sheet);

						FileOutputStream outputStream = new FileOutputStream(excelFilePath);
						workbook.write(outputStream);
						workbook.close();

					} catch (SQLException e) {
						System.out.println("Datababse error:");
						e.printStackTrace();
					}
					files.add("/var/log/batabillpunch//" + "SUPPLY" + app + dd + ".xlsx");

				}

			}
			if (Optional.ofNullable(files).isPresent()) {

				ZipOutputStream zipOut = new ZipOutputStream(response.getOutputStream());
				for (String fileName : files) {
					FileSystemResource resource = new FileSystemResource(fileName);
					ZipEntry zipEntry = new ZipEntry(resource.getFilename());
					zipEntry.setSize(resource.contentLength());
					zipOut.putNextEntry(zipEntry);
					StreamUtils.copy(resource.getInputStream(), zipOut);
					zipOut.closeEntry();
				}
				zipOut.finish();
				zipOut.close();
				response.setStatus(HttpServletResponse.SC_OK);
				response.addHeader(HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filename=\"" + "/var/log/batabillpunch//XLZIP" + dd + ".xlxs" + "\"");
				rs.setStatus(ReraMessageConstants.SUCCESS);
				rs.setMessage(ReraMessageConstants.SUCCESS_STATUS);
				rs.setData("XLZIP" + dd + ".xlxs");
			}

		}

		else {
			rs.setStatus(ReraMessageConstants.NOT_AUTHORIZED);
			rs.setMessage(ReraMessageConstants.NOT_AUTHORIZED_MESSAGE);
		}

	}

	@SuppressWarnings("all")
	private void writeHeaderLine(XSSFSheet sheet) {

		Row headerRow = sheet.createRow(0);

		List<String> list1 = new ArrayList<>();
		list1.addAll(Arrays.asList("FROM STATE CODE", "FROM STATE NAME", "PARTY CODE", "PARTY NAME", "TO STATE CODE",
				"TO STATE NAME", "TO LOCATION CODE", "RECEVING LOCATION NAME", "DEPT", "REGION", "INVOICE NO",
				"INVOICE DATE", "GRN NO", "GRN DATE", "BOOKING", "PAIRS", "TAXABLE VALUE", "CGST", "SGST", "IGST",
				"FREIGHT", "TCS", "NET AMOUNT", "GR NO", "GR DATE", "PO NO", "PO DATE", "VENDOR GST NO", "MRP", "WSP",
				"RECEIVER GST NO", "NO CLAIM DIST", "FORM TYPE"));

		for (int i = 0; i < list1.size(); i++) {
			Cell headerCell = headerRow.createCell(i);
			headerCell.setCellValue(list1.get(i));
		}

	}

	// added formtype for supply reports from 2023 week onwards.
	@SuppressWarnings("all")
	private void writeDataLines(List<BillPunchSupplyReportModel> result, XSSFSheet sheet) throws SQLException {
		int rowCount = 1;

		for (BillPunchSupplyReportModel sb : result) {

			Row row = sheet.createRow(rowCount++);

			List<String> list1 = new ArrayList<>();
			list1.addAll(Arrays.asList(sb.getFromStateCode(), sb.getFromStateName(), sb.getPartyCode(),
					sb.getPartyName(), sb.getToStateCode(), sb.getToStateName(), sb.getTolocationCode(),
					sb.getRecLocName(), sb.getDept(), sb.getRegion(), sb.getInvoiceNo(), sb.getInvoiceDate(),
					sb.getGrNo(), sb.getGrnDate(), sb.getBooking(), sb.getPairs(), sb.getTaxableValue(), sb.getCgst(),
					sb.getSgst(), sb.getIgst(), sb.getFreight(), sb.getTcs(), sb.getNetAmount(), sb.getGrnNo(),
					sb.getGrDate(), sb.getPoNo(), sb.getPoDate(), sb.getVenGstNo(), sb.getMrp(), sb.getWsp(),
					sb.getReceiverGstNo(), sb.getNoclaim(), sb.getFormtype()));
			for (int i = 0; i < 33; i++) {
				Cell cell = row.createCell(i);
				cell.setCellValue(list1.get(i));

			}

		}

	}

	/********************************************************************************************
	 * Find
	 * 
	 * @throws IOException
	 * @throws ParseException
	 ********************************************************************************************/
	@GetMapping("/get-import-rdc-records-from-remote-location")
	public void getImportDataFromRdc() {

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
						xm.setArticleName(aservices.getArticleDetails(sh.getArticleCode()).getArtname());
					} catch (Exception e) {
					}

					try {

						if (Optional.ofNullable(sservices.getShopDetails(sh.getRdcCode()).getShopname()).isPresent()) {
							xm.setReceiveLoc(sservices.getShopDetails(sh.getRdcCode()).getShopname().trim());
						} else {
							xm.setReceiveLoc(
									sservices.getShopDetails(rdcmapdao.findByRdcno(sh.getRdcCode()).getMergerdccode())
											.getShopname().trim());

						}
					} catch (Exception e) {
					}

					xm.setSizeCode(Integer.valueOf(sh.getSizeCode()));
					xm.setBillWeekYear(sh.getBillWeekYear());
					xm.setWeekYear(sh.getWeekYear());
					xm.setShopNo(sh.getShopNo());
					xm.setUpdateDate(sh.getUpdateDate());
					try {
						xm.setShopName(sservices.getShopDetails(sh.getShopNo()).getShopname());
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
						xm.setPartyName(pservices.getPartiesDetails(StringUtils.leftPad(sh.getParty(), 4, "0"))
								.getPartyfullname());
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
						if (Optional.ofNullable(sh).isPresent()) {

						} else {
							sx = sservices.getShopDetails(rdcmapdao.findByRdcno(sh.getRdcCode()).getMergerdccode());

						}
					} catch (Exception e) {
					}
					try {
						xm.setStateName(stservices.getStateDetails(sx.getStatecode()).getStateName());
					} catch (Exception e) {
					}
					Date x36 = null;
					try {
						if (Optional.ofNullable(sh.getRecptInvDate()).isPresent()) {
							String s22 = sh.getRecptInvDate().substring(2, 4);
							String s33 = sh.getRecptInvDate().substring(4, 8);
							String snn = sh.getRecptInvDate().substring(0, 2).concat("-") + s22.concat("-") + s33;
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
					xm.setStatus("RECORD_RECEIVED");
					services.saveXl(xm);

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		}
	}
}
