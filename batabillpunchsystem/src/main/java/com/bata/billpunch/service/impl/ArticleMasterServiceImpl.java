package com.bata.billpunch.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bata.billpunch.dao.ArticlesMasterDao;
import com.bata.billpunch.model.ArticlesMasterModel;
import com.bata.billpunch.model.dto.BillPunchResponseInterface;

@Service
@Transactional
public class ArticleMasterServiceImpl {

	@Autowired
	private ArticlesMasterDao ordao;

	
	public ArticlesMasterModel getArticleDetails(String artcode) {
		
		 return ordao.findWithArticleDetailsByCode(artcode);
	}
	
	public BillPunchResponseInterface getArticleDetailsReport(String artcode) {
		
		 return ordao.findWithArticleDetailsByCodeReport(artcode);
	}
	
	public BillPunchResponseInterface getArticleDetailsedpReport(String invno,String partyCode,String grno) {
		
		 return ordao.findWithArticleDetailsEdpReport(invno,partyCode,grno);
	}
	
	public BillPunchResponseInterface getArticleDetailsedpReportNew(String artno,String pair) {
		
		 return ordao.findWithArticleDetailsByCodeReportNew(artno,pair);
	}
	
	public BillPunchResponseInterface getArticleDetailsReportstraza(String invno,String partyCode,String ordno) {
		
		 return ordao.findWithArticleDetailsStrazaReport(invno,partyCode,ordno);
	}
	
	public List<ArticlesMasterModel> getAllArticleDetails() {
		
		 return ordao.findAll();
	}
	

}
