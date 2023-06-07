package com.bata.billpunch.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bata.billpunch.dao.TransReportDao;
import com.bata.billpunch.model.BillPunchTransReportModel;

@Service
@Transactional
public class TransReportServiceImpl {

	@Autowired
	private TransReportDao sdao;

	
	public void saveAll(List<BillPunchTransReportModel> list) {
		
		  sdao.saveAll(list);
	}

	
	
}
