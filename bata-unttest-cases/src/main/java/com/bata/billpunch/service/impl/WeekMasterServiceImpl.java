package com.bata.billpunch.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bata.billpunch.dao.WeekMasterDao;
import com.bata.billpunch.model.WeekMasterModel;

@Service
@Transactional
public class WeekMasterServiceImpl {

	@Autowired
	private WeekMasterDao wdao;
	
	public List<WeekMasterModel> getAllWeek(){
		
		return  wdao.findAll();
		
	}


}
