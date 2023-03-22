package com.bata.billpunch.mock.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.io.UnsupportedEncodingException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.bata.billpunch.controller.BillPunchRestController;
import com.bata.billpunch.dao.BillPunchDetailsDao;
import com.bata.billpunch.model.BillPunchDetailsModel;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TestMockitoController {

	@Autowired
	private BillPunchRestController mockcontroller; //call the methods through the layers to reach the mock repository.

	@MockBean
	private BillPunchDetailsDao mockdao;//create the mock bean and no need to connect the real db.

	@Mock
	HttpServletRequest mockRequest;
	@Mock
	HttpServletResponse mockResponse;

	@Mock
	RestTemplate resttemplet;

	// @GetMapping("/getall-billpunch-details")//get all data
	//@Test
	public void getAll() throws UnsupportedEncodingException {
      //change the token if any error for this end point  for expire token.
		MockHttpServletRequest requestq = new MockHttpServletRequest();
		requestq.setParameter("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJSb2xlIjoiRmluYW5jZSBVc2VyIiwic3ViIjoic3VyZW5kcmFzaW5naCIsIkZ1bGxOYW1lIjoiU3VyZW5kcmEgU2luZ2giLCJleHAiOjE2NzQyMTMwNzIsImlhdCI6MTY3NDE5NTA3Mn0.B07d8BmkLG7MqL1zUdhs0Etyeq9HJpm-f6Hsv7gt_MKO8JWUHyi2FrvX849KHXa2iq5WDOpZj5DyFPhCpFmd9g");

		MockHttpServletResponse response = new MockHttpServletResponse();
		response.getContentAsString();

		BillPunchDetailsModel bata1 = new BillPunchDetailsModel();
		bata1.setArticleName("JJ");
		bata1.setArticleCode("23");

		BillPunchDetailsModel bata2 = new BillPunchDetailsModel();
		bata2.setArticleName("BATA");
		bata2.setArticleCode("2323");
        String status=mockcontroller.getAll(requestq).getBody().getStatus();
		when(mockdao.findWithAllMock()).thenReturn(Stream.of(bata1, bata2).collect(Collectors.toList()));//this condition for get the dummy data from mock repo directly and need to be compair with next line code.
		assertThat("200").isEqualTo(status);//this condition for get the dummy data through the step by step layer to reach mock repo and get the the data.

	}
	
	
	  // @PostMapping("/save-details") //save model
		@Test
		public void saveBillPunchDetails() throws UnsupportedEncodingException {

			MockHttpServletRequest requestq = new MockHttpServletRequest();
			requestq.setParameter("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJSb2xlIjoiRmluYW5jZSBVc2VyIiwic3ViIjoic3VyZW5kcmFzaW5naCIsIkZ1bGxOYW1lIjoiU3VyZW5kcmEgU2luZ2giLCJleHAiOjE2NzQyMTMwNzIsImlhdCI6MTY3NDE5NTA3Mn0.B07d8BmkLG7MqL1zUdhs0Etyeq9HJpm-f6Hsv7gt_MKO8JWUHyi2FrvX849KHXa2iq5WDOpZj5DyFPhCpFmd9g");

			MockHttpServletResponse response = new MockHttpServletResponse();
			response.getContentAsString();

			BillPunchDetailsModel bata1 = new BillPunchDetailsModel();
			bata1.setArticleName("BATA");
			bata1.setArticleCode("27883");

			
			when(mockdao.save(bata1)).thenReturn(bata1);
			assertThat("200").isEqualTo(mockcontroller.saveBillPunchDetailsMock(bata1,requestq).getBody().getStatus());

		}
		//@GetMapping("/getdetails-by-id/{id}") //get model by id
		@Test
		public void getDetailsById() throws UnsupportedEncodingException {

			MockHttpServletRequest requestq = new MockHttpServletRequest();
			requestq.setParameter("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJSb2xlIjoiRmluYW5jZSBVc2VyIiwic3ViIjoic3VyZW5kcmFzaW5naCIsIkZ1bGxOYW1lIjoiU3VyZW5kcmEgU2luZ2giLCJleHAiOjE2NzQyMTMwNzIsImlhdCI6MTY3NDE5NTA3Mn0.B07d8BmkLG7MqL1zUdhs0Etyeq9HJpm-f6Hsv7gt_MKO8JWUHyi2FrvX849KHXa2iq5WDOpZj5DyFPhCpFmd9g");

			MockHttpServletResponse response = new MockHttpServletResponse();
			response.getContentAsString();

			BillPunchDetailsModel bata1 = new BillPunchDetailsModel();
			bata1.setBilId(12l);
			bata1.setArticleName("BATA");
			bata1.setArticleCode("23");

			
			when(mockdao.findWithIdMock(bata1.getBilId())).thenReturn(bata1);
			assertThat("200").isEqualTo(mockcontroller.getDetailsById(bata1.getBilId(), requestq).getBody().getStatus());

		}
	
		
		//@PutMapping("/update-details-mock")//update model 
				@Test
				public void updateBillPunchDetailsMock() throws UnsupportedEncodingException {

					MockHttpServletRequest requestq = new MockHttpServletRequest();
					requestq.setParameter("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJSb2xlIjoiRmluYW5jZSBVc2VyIiwic3ViIjoic3VyZW5kcmFzaW5naCIsIkZ1bGxOYW1lIjoiU3VyZW5kcmEgU2luZ2giLCJleHAiOjE2NzQyMTMwNzIsImlhdCI6MTY3NDE5NTA3Mn0.B07d8BmkLG7MqL1zUdhs0Etyeq9HJpm-f6Hsv7gt_MKO8JWUHyi2FrvX849KHXa2iq5WDOpZj5DyFPhCpFmd9g");

					MockHttpServletResponse response = new MockHttpServletResponse();
					response.getContentAsString();

					BillPunchDetailsModel bata1 = new BillPunchDetailsModel();
					bata1.setBilId(12l);
					bata1.setArticleName("BATA");
					bata1.setArticleCode("23");
					
					BillPunchDetailsModel bata2 = new BillPunchDetailsModel();
					bata1.setBilId(12l);
					bata1.setArticleName("BILLBATA");
					bata1.setArticleCode("23");

					
					when(mockdao.findWithIdMock(bata1.getBilId())).thenReturn(bata1);
					when(mockdao.save(bata1)).thenReturn(bata2);
					assertThat("200").isEqualTo(mockcontroller.updateBillPunchDetailsMock(bata1, requestq).getBody().getStatus());

				}

}
