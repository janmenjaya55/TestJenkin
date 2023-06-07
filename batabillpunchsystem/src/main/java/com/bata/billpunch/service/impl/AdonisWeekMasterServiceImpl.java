package com.bata.billpunch.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bata.billpunch.dao.AdonisWeekMasterDao;
import com.bata.billpunch.model.AdonisWeekMasterModel;

@Service
@Transactional
public class AdonisWeekMasterServiceImpl {

	@Autowired
	private AdonisWeekMasterDao wdao;
	
	public List<AdonisWeekMasterModel> getAllWeek(){
		
		return  wdao.findAll();
		
	}


}
