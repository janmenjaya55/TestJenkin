package com.bata.billpunch.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bata.billpunch.dao.ArticlesMasterDao;
import com.bata.billpunch.dao.ArticlesMasterOldDao;
import com.bata.billpunch.model.ArticlesMasterModel;
import com.bata.billpunch.model.ArticlesMasterOldModel;
import com.bata.billpunch.model.dto.BillPunchResponseInterface;

@Service
@Transactional
public class ArticleMasterOldServiceImpl {

	@Autowired
	private ArticlesMasterOldDao oddao;

	
	public ArticlesMasterOldModel getArticleDetails(String artcode) {
		
		 return oddao.findWithArticleDetailsByCode(artcode);
	}
	
	public BillPunchResponseInterface getArticleDetailsReport(String artcode) {
		
		 return oddao.findWithArticleDetailsByCodeReport(artcode);
	}
	
	public BillPunchResponseInterface getArticleDetailsedpReport(String invno,String partyCode,String grno) {
		
		 return oddao.findWithArticleDetailsEdpReport(invno,partyCode,grno);
	}
	
	public BillPunchResponseInterface getArticleDetailsedpReportNew(String artno,String pair) {
		
		 return oddao.findWithArticleDetailsByCodeReportNew(artno,pair);
	}
	
	public BillPunchResponseInterface getArticleDetailsReportstraza(String invno,String partyCode,String ordno) {
		
		 return oddao.findWithArticleDetailsStrazaReport(invno,partyCode,ordno);
	}
	
	public List<ArticlesMasterOldModel> getAllArticleDetails() {
		
		 return oddao.findAll();
	}
	

}
