package com.bata.billpunch.mock.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bata.billpunch.dao.BillPunchDetailsDao;
import com.bata.billpunch.model.BillPunchDetailsModel;
import com.bata.billpunch.service.impl.BillPunchServicesImpl;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TestMockitoService {
	
	@Autowired
	private BillPunchServicesImpl mockservice;
	
	@MockBean
	private BillPunchDetailsDao mockdao;
	
	
	//public List<BillPunchDetailsModel> getAll()
		@Test
		public void getAll() {
			
			BillPunchDetailsModel bata1=new BillPunchDetailsModel();
			bata1.setArticleName("JJ");
			bata1.setArticleCode("23");
			
			BillPunchDetailsModel bata2=new BillPunchDetailsModel();
			bata2.setArticleName("BATA");
			bata2.setArticleCode("2323");
			
			when(mockdao.findWithAllMock()).thenReturn(Stream.of(bata1,bata2).collect(Collectors.toList()));
		    assertThat(2).isEqualTo (mockservice.getAll().size());
			
		}
		
		//public BillPunchDetailsModel saveMock(BillPunchDetailsModel pm)
		@Test
		public void saveMock() {
			
			BillPunchDetailsModel bata1=new BillPunchDetailsModel();
			bata1.setArticleName("JJ");
			bata1.setArticleCode("23");
		
			
			when(mockdao.save(bata1)).thenReturn(bata1);
		    assertThat(bata1.getArticleName()).isEqualTo (mockservice.saveMock(bata1).getArticleName());
			
		}
		
		
		//public BillPunchDetailsModel getById(Long id) 
				@Test
				public void getById() {
					
					BillPunchDetailsModel bata1=new BillPunchDetailsModel();
					bata1.setArticleName("JJ");
					bata1.setArticleCode("23");
				
					
					when(mockdao.findWithIdMock(bata1.getBilId())).thenReturn(bata1);//get dummy data from repo layer without connect real db
				    assertThat(bata1.getArticleCode()).isEqualTo (mockservice.getById(bata1.getBilId()).getArticleCode());
					
				}
				

}
