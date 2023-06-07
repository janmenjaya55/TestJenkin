package com.bata.billpunch.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bata.billpunch.dao.SupplyReportDao;
import com.bata.billpunch.model.BillPunchSupplyReportModel;

@Service
@Transactional
public class SupplyReportServiceImpl {

	@Autowired
	private SupplyReportDao sdao;

	
	public void saveAll(List<BillPunchSupplyReportModel> list) {
		
		  sdao.saveAll(list);
	}

	
	
}
