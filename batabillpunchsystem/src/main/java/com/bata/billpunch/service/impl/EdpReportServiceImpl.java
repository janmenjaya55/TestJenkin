package com.bata.billpunch.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bata.billpunch.dao.EdpReportDao;
import com.bata.billpunch.model.BillPunchEdpReportModel;

@Service
@Transactional
public class EdpReportServiceImpl {

	@Autowired
	private EdpReportDao edao;

	
	public void saveAll(List<BillPunchEdpReportModel> list) {
		
		  edao.saveAll(list);
	}

	
	
}
