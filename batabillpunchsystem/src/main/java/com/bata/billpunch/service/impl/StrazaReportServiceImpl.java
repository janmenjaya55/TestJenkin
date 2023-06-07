package com.bata.billpunch.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bata.billpunch.dao.StrazaReportDao;
import com.bata.billpunch.model.BillPunchStrazaReportModel;

@Service
@Transactional
public class StrazaReportServiceImpl {

	@Autowired
	private StrazaReportDao edao;

	
	public void saveAll(List<BillPunchStrazaReportModel> list) {
		
		  edao.saveAll(list);
	}

	
	
}
