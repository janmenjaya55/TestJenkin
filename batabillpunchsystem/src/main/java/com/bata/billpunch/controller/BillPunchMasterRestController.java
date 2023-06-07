package com.bata.billpunch.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bata.billpunch.config.TokenRequest;
import com.bata.billpunch.config.TokenResponse;
import com.bata.billpunch.constant.ReraMessageConstants;
import com.bata.billpunch.constant.ResponseModel;
import com.bata.billpunch.model.ArticlesMasterModel;
import com.bata.billpunch.model.BillPunchDetailsModel;
import com.bata.billpunch.model.CategoriesMasterModel;
import com.bata.billpunch.model.OrdersMasterModel;
import com.bata.billpunch.model.ShopMasterModel;
import com.bata.billpunch.model.StateMasterModel;
import com.bata.billpunch.model.WeekMasterModel;
import com.bata.billpunch.model.dto.PartyNameCodeDto;
import com.bata.billpunch.service.impl.BillPunchMasterServicesImpl;

@RestController
@RequestMapping("/bill-punch")
@CrossOrigin(origins = "*")
public class BillPunchMasterRestController {

	@Autowired
	private BillPunchMasterServicesImpl service;

	@Value("${TOKEN_URL}")
	private String tokenurl;

	/********************************************************************************************
	 * Find All article master data
	 ********************************************************************************************/
	@PostMapping("/getall-article-master-details")
	public ResponseEntity<ResponseModel> getAllArticleDetails(HttpServletRequest req,
			@RequestBody BillPunchDetailsModel entity) {

		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();

		if (response.getStatus().contentEquals("True")) {
			List<ArticlesMasterModel> cm = service.findWithArticleDetails(entity.getArticleName(),
					entity.getInvoiceNO(), entity.getPartyCode(), entity.getBillOrderNo());

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
	 * Find All article master data
	 ********************************************************************************************/
	@GetMapping("/getall-article-master")
	public ResponseEntity<ResponseModel> getAllArticles(HttpServletRequest req) {

		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();

		if (response.getStatus().contentEquals("True")) {
			List<ArticlesMasterModel> cm = service.findAll();

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
	 * Find All article master data
	 ********************************************************************************************/
	@GetMapping("/getall-party-master")
	public ResponseEntity<ResponseModel> getAllPartyNameAndCode(HttpServletRequest req) {

		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();

		if (response.getStatus().contentEquals("True")) {
			List<PartyNameCodeDto> cm = service.findAllParty();

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
	 * Find All state master data
	 ********************************************************************************************/
	@GetMapping("/getall-state-master-details/{statename}")
	public ResponseEntity<ResponseModel> getAllStateDetails(HttpServletRequest req,
			@PathVariable("statename") String statename) {

		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();

		if (response.getStatus().contentEquals("True")) {
			List<StateMasterModel> cm = service.findWithStateDetails(statename);

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
	 * Find All state master data
	 ********************************************************************************************/
	@GetMapping("/getall-state-master")
	public ResponseEntity<ResponseModel> getAllStateS(HttpServletRequest req) {

		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();

		if (response.getStatus().contentEquals("True")) {
			List<StateMasterModel> cm = service.findAllDetails();

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
	 * Find All region or shop master data
	 ********************************************************************************************/
	@GetMapping("/getall-shop-master-details/{shopname}")
	public ResponseEntity<ResponseModel> getAllShopDetails(HttpServletRequest req,
			@PathVariable("shopname") String shopname) {

		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();

		if (response.getStatus().contentEquals("True")) {
			List<ShopMasterModel> cm = service.findWithShopDetails(shopname);

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
	 * Find All region or shop master data
	 ********************************************************************************************/
	@GetMapping("/getall-shop-master")
	public ResponseEntity<ResponseModel> getAllShop(HttpServletRequest req) {

		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();

		if (response.getStatus().contentEquals("True")) {
			List<ShopMasterModel> cm = service.getAll();

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
	 * Find All category or shop master data
	 ********************************************************************************************/
	@GetMapping("/getall-category-master-details/{catname}")
	public ResponseEntity<ResponseModel> getAllCategoryDetails(HttpServletRequest req,
			@PathVariable("catname") String catname) {

		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();

		if (response.getStatus().contentEquals("True")) {
			List<CategoriesMasterModel> cm = service.findWithCatagoryDetails(catname);

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
	 * Find All category or shop master data
	 ********************************************************************************************/
	@GetMapping("/getall-category-master")
	public ResponseEntity<ResponseModel> getAllCategory(HttpServletRequest req) {

		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();

		if (response.getStatus().contentEquals("True")) {
			List<CategoriesMasterModel> cm = service.getAllDetails();

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
	 * Find All week master data
	 ********************************************************************************************/
	@GetMapping("/getall-week-master-details/{weekcode}")
	public ResponseEntity<ResponseModel> getAllWeekDetails(HttpServletRequest req,
			@PathVariable("weekcode") String weekcode) {

		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();

		if (response.getStatus().contentEquals("True")) {
			List<WeekMasterModel> cm = service.findWithWeekDetails(weekcode);

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
	 * Find All week master data
	 ********************************************************************************************/
	@GetMapping("/getall-week-master")
	public ResponseEntity<ResponseModel> getAllWeek(HttpServletRequest req) {

		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();

		if (response.getStatus().contentEquals("True")) {
			List<WeekMasterModel> cm = service.findDetails();

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

	public String getToken(HttpServletRequest req) {

		final String requestTokenHeader = req.getHeader("Authorization");
		String jwtToken = null;
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {

			jwtToken = requestTokenHeader.substring(7);
		}
		return jwtToken;
	}

	/********************************************************************************************
	 * Find All week master data
	 ********************************************************************************************/
	@GetMapping("/getall-order-master-details/{orderno}")
	public ResponseEntity<ResponseModel> getAllOrderDetails(HttpServletRequest req,
			@PathVariable("orderno") String orderno) {

		ResponseModel rs = new ResponseModel();
		RestTemplate restTemplate = new RestTemplate();
		TokenRequest request = new TokenRequest();
		request.setToken(getToken(req));
		TokenResponse response = restTemplate.postForEntity(tokenurl, request, TokenResponse.class).getBody();

		if (response.getStatus().contentEquals("True")) {
			List<OrdersMasterModel> cm = service.findWithOrderDetails(orderno);

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
}
