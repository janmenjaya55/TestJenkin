package com.bata.billpunch.login.service;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bata.billpunch.login.bean.ResponseModel;
import com.bata.billpunch.login.model.UserModel;
import com.bata.billpunch.login.repository.UserRepository;
import com.bata.billpunch.login.service.impl.UserDetailsImpl;

@Transactional
@Service
public class LoginService implements UserDetailsService {

	@Autowired
	private UserRepository userInfoRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {

		System.out.println("In LoginService loadUserByUsername===" + username);

		UserModel user = userInfoRepository.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		/*
		 * return new
		 * org.springframework.security.core.userdetails.User(user.getUsername(),
		 * user.getPassword(), new ArrayList<>());
		 */
		return UserDetailsImpl.build(user);
	}

	public static String decrypt(String str) {

		System.out.println(">>>>>>>>>>>>>>str" + str);
		str = revertTxt(str);
		String s = str.substring(0, 2);
		int idx = Integer.parseInt(s);
		str = str.substring(2);
		str = revertTxt(str, idx);
		String result = "";
		for (int i = 0; i < str.length(); i += 3) {
			String hex = str.substring(i + 1, i + 3);
			result += (char) (Integer.parseInt(hex, 16) ^ (Integer.parseInt(String.valueOf(str.charAt(i)))));
		}
		return result;
	}

	private static String revertTxt(String enctxt) {
		StringBuilder sb = new StringBuilder();
		char[] ch = enctxt.toCharArray();
		int xx = 0;
		for (char c : ch) {
			sb.append(indexString.charAt(keyArr[xx].indexOf("" + c)));
			xx++;
			if (xx > 31)
				xx = 0;

		}
		return sb.toString();
	}

	private static String revertTxt(String enctxt, int idx) {
		StringBuilder sb = new StringBuilder();
		char[] ch = enctxt.toCharArray();
		int xx = 0;
		for (char c : ch) {
			sb.append(indexString.charAt(keyArr[xx].indexOf("" + c)));
			xx++;
			if (xx > 31)
				xx = 0;

		}
		return sb.toString();
	}

	static final String indexString = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	static final String[] keyArr = { "ijklmnop0aKzABCDEFGHIQRSTUqrsJ234LMuvwefghNOPWX1bcdytx56789VYZ",
			"BCo9RabcdefxyGHIJKLzAFMghijVWpqS456stuvQ23klmn78XwYZTDE10rNOPU",
			"wabcdefghijklmnopqrstuvRSTUVWXYZxyzABCDEFGHIJKLMNOPQ1234567890",
			"bcHIYZUVWXlmnopqrs1234567defghijkJKLMNOPQRSTtuvwxyzABCDEFG890a",
			"XYZFGHIopqrabcdefghijklstzABCDE5678wxyTUVWJK90mnuvLMNOPQRS1234",
			"HIJbcdeSTUVWXYZKLMNOPQR12345fghijklmnopqrstuvwxyzABCDEFG67890a",
			"vwxyzABCDEFGH123456OPQRSTUVhijklmnopqrstuWXYZ7890abcdefgIJKLMN",
			"KLMfghijklmnopqWXYZ123xyzArsNOPQRSTUVBCDEFGHIJ4567890abcdetuvw",
			"stuvwxyzABCJKLMNOPQ123456HIbcdefghiDEFGWXYZjklmnopqrRSTUV7890a",
			"roNOPQRSTUVxyzABCLMWXYZuvwcdefghijklmnFGHIstJKbpq1234567890aDE",
			"yzABCDEFGHINOPWX1bcd56789VYZQRSTUqrsJ234txijklmnop0aKLMuvwefgh",
			"TDE10abcdMghijVWpqrNOPUefxyGHIJKLzAFBCo9RS456stuvQ23klmn78XwYZ",
			"890abcdefghijklmnopqrswxyzABCDEFGHIJKLMNOYZPQ1234567tuvRSTUVWX",
			"tuvwxyzABCDEFGHIY67890abZUVWXlmnopqrs12345cdefghijkJKLMNOPQRST",
			"uvLMNOPQRSyTUVWJK9abcdefgh1234zABCDE5678wxijklst0mnXYZFGHIopqr",
			"ZKLMN7890afghijklmnopqrstuvwxyzABCDEOPQR123456FGHIJbcdeSTUVWXY",
			"TUVWqrstuvwxyzABCDEFGH1234XYZ7890abcdefgIJKLMNhijklmnop56OPQRS",
			"mn45GHIJKLMfghijklvw67890abcdetuxyzArsNOPQRSTUVBopqWXYZ123CDEF",
			"stu56YZwxyzABCDEFGHIbcJKLM7890avdefghijklmnopqrRSTUVWXNOPQ1234",
			"uvwcqroxyzABCQRSTUVWXdefghijklmnFGHIJKbpYZ1234567890aDLMNOPEst",
			"ijkrsJ234tx5678nop0aKLMuvwefghNOPWX1bcdZyzlmGHIQRSTUqABCDEF9VY",
			"B23klmn78XwYZTDE10abcdefxyGHICo9RS45LzAFMghijVWpqrNO6stuvQJKPU",
			"wxghijklmnstuvRSTUVyzABCDEFGHIJKLMNOPQ1234567890abcopqrdefWXYZ",
			"aqrs123456fghijkJKLbcdeVWXlmnopIYZU7890MNOPQRSTtuvwxyzABCDEFGH",
			"rabcdefghijklstuE5678wxyTUVWJK90mnXYZFGHIopqRS1234vLMNOPQzABCD",
			"4567klmnopqrstuvwxyzABCDEFGfghijOPQR123HIJbcdeSTUVWXYZKLMN890a",
			"hWXYZ7890abcdefgIJKijklmnopqrstuvwxyzABCDEFGH123456OPQRSTUVLMN",
			"xMfghijklm0abcdetuvwnopqWXYyzArsNOPQRSTUVBCDEFGHIJKLZ123456789",
			"stuSTUVWXYZJKLIbcdefghijklmnopqrR67890aMNOPQ12345vwxyzABCDEFGH",
			"qroxyzABCLMNOPQRFGHIJKbpZ1234567890aDEstSTUVWXYuvwcdefghijklmn",
			"BCDEFGHIQRcdyzA56789VYZSTUqrsJ234txijklmnop0aKLMuvwefghNOPWX1b",
			"S456stuvQ23klmnDE10abcdefxyGHIBCo9RJKLzAFMghijVWpqrNOPU78XwYZT",
			"CDEFGHIJKLMNOPQuvRSTUVWXYZ1234567890abcdewxyzABfghijklmnopqrst",
			"bcqrs1234567890afghijkJKLMdeUVWXlmnopNOPQRSTtuvwxyzABCDEFGHIYZ",
			"zopqra34jklstuvLMNOPQABCDbcdefghi90mnXYZFGHIRS12E5678wxyTUVWJK",
			"KLMNOPQR1234lmnopqrsfghijkTUVWXYZ567890atuvwxyzABCDEFGHIJbcdeS",
			"OPQRSTUVWXYZ7890ahijklmno56bcdefgIJKLMNpqrstuvwxyzABCDEFGH1234",
			"sNOPQRSTUVBCDEFGHIJKLMftuvwghijklmnopqxyzArWXYZ1234567890abcde",
			"BCDEFGHIbcde7890ajklmnopqrRSTUVWXYZfghi123456JstuvwxyzAKLMNOPQ",
			"hijklmnFGHIJKbpqrox7890aDEstyzABCLMNOPQRSTUuvwcdefgVWXYZ123456" };

	public static String ftechGitDetails(String url, RestTemplate restTemplate,HttpServletRequest req) {

		//ResponseModel sx = restTemplate.getForObject(url, ResponseModel.class);
		
		//return (String) sx.getData();

		ResponseEntity<?> response = restTemplate.exchange(url,HttpMethod.GET, null, Object.class);
		URI xb=response.getHeaders().getLocation();
		
		String json = response.getStatusCode().toString();
		System.out.println("bata github check user restTemplate.getForObject(url, ResponseModel.class);>>>>>>>>details"
				+ json);
		return json;
		
		 

	}

}
