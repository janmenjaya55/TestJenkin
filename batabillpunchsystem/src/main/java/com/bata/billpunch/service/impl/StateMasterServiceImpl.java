package com.bata.billpunch.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bata.billpunch.dao.StateMasterDao;
import com.bata.billpunch.model.StateMasterModel;

@Service
@Transactional
public class StateMasterServiceImpl {

	@Autowired
	private StateMasterDao stdao;

	
	public StateMasterModel getStateDetails(String statecode) {
		
		 return stdao.findWithStateName(statecode);
	}

}
