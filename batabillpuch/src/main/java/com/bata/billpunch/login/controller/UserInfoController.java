package com.bata.billpunch.login.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bata.billpunch.login.bean.ResponseModel;
import com.bata.billpunch.login.bean.UserRequest;
import com.bata.billpunch.login.bean.UserResponse;
import com.bata.billpunch.login.common.BillPunchConstant;
import com.bata.billpunch.login.config.JwtTokenUtil;
import com.bata.billpunch.login.config.SendMail;
import com.bata.billpunch.login.exception.ValidationException;
import com.bata.billpunch.login.model.UserModel;
import com.bata.billpunch.login.repository.UserRepository;
import com.bata.billpunch.login.service.LoginService;

@Component
@RestController
@CrossOrigin(origins = "*")
public class UserInfoController {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private SendMail sentemail;

	@Autowired
	private UserRepository userInfoRepository;

	@Autowired
	LoginService serviceImpl;

	@Autowired
	RestTemplate restTemplate;

	@PostMapping("/batabps/user/adduser-original")
	public ResponseEntity<UserResponse> createOriginal(@RequestBody UserRequest userReq, HttpServletRequest request) {

		String loggedinUser = getUserDetail(request);

		UserModel userModel = new UserModel();

		String username = userReq.getUsername();
		if (Boolean.TRUE.equals(userInfoRepository.existsByUsername(username))) {

			throw new ValidationException("Username already existed");

		}

		String password = userReq.getPassword();
		String encodedPassword = new BCryptPasswordEncoder().encode(password);

		userModel.setFullname(userReq.getFullname());
		userModel.setUserrole(userReq.getUserrole());
		userModel.setDepartment(userReq.getDepartment());
		userModel.setDesignation(userReq.getDesignation());
		userModel.setEmailId(userReq.getEmailId());
		userModel.setEmployId(userReq.getEmployId());
		userModel.setMobNo(userReq.getMobNo());
		userModel.setPassword(encodedPassword);
		userModel.setLoginId(userReq.getUsername());
		userModel.setUsername(userReq.getUsername());
		userModel.setStatus(BillPunchConstant.ACTIVE);
		userModel.setCreatedBy(loggedinUser);
		userModel.setUserLevel(userReq.getUserLevel());

		userInfoRepository.save(userModel);

		UserResponse resp = new UserResponse();
		resp.setFullname(userModel.getFullname());
		resp.setUserrole(userModel.getUserrole());
		resp.setDepartment(userModel.getDepartment());
		resp.setDesignation(userModel.getDesignation());
		resp.setEmailId(userModel.getEmailId());
		resp.setUserLevel(userModel.getUserLevel());
		resp.setMobNo(userModel.getMobNo());

		return ResponseEntity.ok(resp);
	}

	@PostMapping("/batabps/user/adduser")
	public ResponseEntity<UserResponse> create(@RequestBody UserRequest userReq, HttpServletRequest request)
			throws Exception {

		String loggedinUser = getUserDetail(request);

		UserModel userModel = new UserModel();

		String username = userReq.getUsername();
		if (Boolean.TRUE.equals(userInfoRepository.existsByUsername(username))) {

			throw new ValidationException("Username already existed");

		}

		String password = userReq.getPassword();
		// String encodedPassword = new BCryptPasswordEncoder().encode(password);
		String encodedPassword = jwtTokenUtil.getEncriptPassword(password);

		userModel.setFullname(userReq.getFullname());
		userModel.setUserrole(userReq.getUserrole());
		userModel.setDepartment(userReq.getDepartment());
		userModel.setDesignation(userReq.getDesignation());
		userModel.setEmailId(userReq.getEmailId());
		userModel.setEmployId(userReq.getEmployId());
		userModel.setMobNo(userReq.getMobNo());
		userModel.setPassword(encodedPassword);
		userModel.setLoginId(userReq.getUsername());
		userModel.setUsername(userReq.getUsername());
		userModel.setStatus(BillPunchConstant.ACTIVE);
		userModel.setCreatedBy(loggedinUser);
		userModel.setUserLevel(userReq.getUserLevel());
		userModel.setFilename(userReq.getFilename());

		userInfoRepository.save(userModel);

		UserResponse resp = new UserResponse();
		resp.setFullname(userModel.getFullname());
		resp.setUserrole(userModel.getUserrole());
		resp.setDepartment(userModel.getDepartment());
		resp.setDesignation(userModel.getDesignation());
		resp.setEmailId(userModel.getEmailId());
		resp.setUserLevel(userModel.getUserLevel());
		resp.setMobNo(userModel.getMobNo());
		resp.setFilename(userModel.getFilename());
		return ResponseEntity.ok(resp);
	}

	@PostMapping("/batabps/user/updateuser")
	public ResponseEntity<UserResponse> update(@RequestBody UserRequest userReq) {

		UserModel userModel = userInfoRepository.findByUsername(userReq.getUsername());

		if (userModel != null && userReq.getUsername() != null) {
			userModel.setFullname(userReq.getFullname());
			userModel.setUserrole(userReq.getUserrole());
			userModel.setDepartment(userReq.getDepartment());
			userModel.setDesignation(userReq.getDesignation());
			userModel.setEmailId(userReq.getEmailId());
			userModel.setEmployId(userReq.getEmployId());
			userModel.setMobNo(userReq.getMobNo());
			userModel.setUserLevel(userReq.getUserLevel());
			userInfoRepository.save(userModel);

			UserResponse resp = new UserResponse();
			resp.setFullname(userModel.getFullname());
			resp.setUserrole(userModel.getUserrole());
			resp.setDepartment(userModel.getDepartment());
			resp.setDesignation(userModel.getDesignation());
			resp.setEmailId(userModel.getEmailId());
			resp.setUserLevel(userModel.getUserLevel());
			resp.setMobNo(userModel.getMobNo());
			resp.setUsername(userModel.getUsername());
			return ResponseEntity.ok(resp);

		} else if (userReq.getUsername() == null) {

			throw new ValidationException("Not a valid Username");
		} else {
			throw new ValidationException("Username already existed");
		}

	}

	@GetMapping("/batabps/user/allusers")
	public ResponseEntity<List<UserResponse>> allUsers() {

		List<UserModel> userList = userInfoRepository.findAll();
		List<UserResponse> userResponse = new ArrayList<>();

		userList.forEach(responseList -> {
			UserResponse resp = new UserResponse();
			resp.setFullname(responseList.getFullname());
			resp.setUserrole(responseList.getUserrole());
			resp.setDepartment(responseList.getDepartment());
			resp.setDesignation(responseList.getDesignation());
			resp.setEmailId(responseList.getEmailId());
			resp.setUserLevel(responseList.getUserLevel());
			resp.setMobNo(responseList.getMobNo());
			resp.setUsername(responseList.getUsername());
			resp.setFilename(responseList.getFilename());
			userResponse.add(resp);
		});

		return ResponseEntity.ok(userResponse);
	}

	@GetMapping("/batabps/user/checkuser")
	public ResponseEntity<UserResponse> checkUser(HttpServletRequest req) {
		//String sx = serviceImpl.ftechGitDetails("http://192.168.56.13:8014/github", restTemplate);
		String sx = serviceImpl.ftechGitDetails("http://localhost:5014/github/test", restTemplate,req ).substring(0, 3);
		
		System.out.println("bata github check user>>>>>>>>details"+sx);
		//UserModel responseList = userInfoRepository.findByUsername(sx);
		
		UserResponse resp = new UserResponse();
	//	if (sx!=null && sx.equalsIgnoreCase(responseList.getUsername())) {
			if (sx!=null ) {

			//resp.setUserrole(responseList.getUserrole());
			resp.setUserrole(sx);

		} else {
			resp.setUserrole("Not Valid");
		}

		return ResponseEntity.ok(resp);
	}

	@PostMapping("/batabps/user/changepwd")
	public Boolean changepwd(@RequestBody UserRequest userReq, HttpServletRequest request) throws Exception {

		// String loggedinUser=getUserDetail(request);
		// System.out.println("String ###################;>>>>>>>>>>>>"+loggedinUser);
		String password = userReq.getPassword();
		System.out.println("String password = userReq.getPassword();>>>>>>>>>>>>" + password);
		UserModel userModel = null;
		try {
			userModel = userInfoRepository.findByUsername(userReq.getUsername());
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("String password = userReq.getPassword();>>>>>>>>>>>>emIlid" + userModel.getPassword());
		System.out.println(
				"String password = userReq.getPassword();>>>>>>>>>>>>userReq.getPassword()" + userReq.getPassword());
		String encodedPassword = jwtTokenUtil.getEncriptPassword(userReq.getPassword());

		System.out.println("String password = userReq.getPassword();>>>>>>>>>>>>encodedPassword" + encodedPassword);
		userModel.setPassword(encodedPassword);
		userInfoRepository.save(userModel);

		return true;
	}

	@PostMapping("/batabps/user/forgotpassword")
	public ResponseEntity<ResponseModel> forgotpassword(@RequestBody UserRequest userReq) throws Exception {
		 String ps="";
		 System.out.println("getUsername>>>>>>" + userReq.getUsername());
		 System.out.println("getUserOtp>>>>>>" + userReq.getUserOtp());
		UserModel userModel = userInfoRepository.findByEmailId(userReq.getUsername());
      if (userReq.getUserOtp().equalsIgnoreCase(userModel.getUserOtp())) {
    	 ps = jwtTokenUtil.getDecriptPassword(userModel.getPassword());
		}else {
			ps="Wrong user access.";
		}
		
		System.out.println("bnnnnnnnnnnnnnnnnnnnnn" + ps);
		ResponseModel resp = new ResponseModel();

		resp.setMessage("SUCCESS");
		resp.setStatus("200");
		resp.setData(ps);

		return ResponseEntity.ok(resp);

	}
	
	
	@PostMapping("/batabps/user/getotpvalidation")
	public ResponseEntity<ResponseModel> getotpvalidation(@RequestBody UserRequest userReq) throws Exception {

		UserModel userModel = userInfoRepository.findByEmailId(userReq.getUsername());
		Random rnd = new Random();
	    int number = rnd.nextInt(999999);
		userModel.setUserOtp(String.format("%06d", number));
		UserModel resultmodel =userInfoRepository.save(userModel);
		try {
			sentemail.sentEmail(resultmodel.getEmailId(),resultmodel.getUserOtp());
		} catch (Exception e) {
			System.out.println("Exception in sent email"+e);
		}
        
		ResponseModel resp = new ResponseModel();
if(resultmodel!=null) {
	resp.setMessage("SUCCESS");
	resp.setStatus("200");
	resp.setData(resultmodel);
}else {
	resp.setMessage("FAIL");
	resp.setStatus("300");
	resp.setData(resultmodel);
}
		

		return ResponseEntity.ok(resp);

	}

	private String getUserDetail(HttpServletRequest request) {
		final String requestTokenHeader = request.getHeader("Authorization");
		String loggedinUser = null;
		System.out.println("1111111122requestTokenHeader>>>>>>>>>>>>" + requestTokenHeader);
		String jwtToken = null;
		System.out.println("1111111111111111111111111" + requestTokenHeader);
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			System.out.println("11111111222222222222222222222222" + requestTokenHeader);
			jwtToken = requestTokenHeader.substring(7);
			System.out.println("33333333333333333333" + jwtToken);

			try {
				loggedinUser = jwtTokenUtil.getUsernameFromToken(jwtToken);

			} catch (Exception e) {
				System.out.println("Unable to get JWT Token");
				e.printStackTrace();
			}

		}
		return loggedinUser;
	}

	public static void main(String[] args) {
		/*
		 * //final String requestTokenHeader = request.getHeader("Authorization");
		 * String loggedinUser = null; String jwtToken = null;
		 * 
		 * 
		 * jwtToken =
		 * "eyJhbGciOiJIUzUxMiJ9.eyJSb2xlIjoiRmluYW5jZSBVc2VyIiwic3ViIjoic3VyZW5kcmFzaW5naCIsIkZ1bGxOYW1lIjoiU3VyZW5kcmEgU2luZ2giLCJleHAiOjE2NzAzNDAzNjIsImlhdCI6MTY3MDMyMjM2Mn0._FYt2fIB0QBZ_WR83btlYGHG4Jyz2GlCM2b-8RuXP_oKHOSzYZ_VrT5uCsxv1SY-cNLfNV5pjBVbTNtFc-EfgA";
		 * try { loggedinUser = jwtTokenUtil.getUsernameFromToken(jwtToken); } catch
		 * (Exception e) { System.out.println("Unable to get JWT Token"); }
		 * 
		 * System.out.println("Unable to get JWT Token>>>>>>>>>>>"+loggedinUser);
		 */}

}