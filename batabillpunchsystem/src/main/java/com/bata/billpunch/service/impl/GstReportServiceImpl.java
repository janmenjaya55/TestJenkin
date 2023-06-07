package com.bata.billpunch.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bata.billpunch.dao.GstReportDao;
import com.bata.billpunch.model.BillPunchGstReportModel;

@Service
@Transactional
public class GstReportServiceImpl {

	@Autowired
	private GstReportDao gdao;

	
	public void saveAll(List<BillPunchGstReportModel> list) {
		
		  gdao.saveAll(list);
	}

	
	
}
