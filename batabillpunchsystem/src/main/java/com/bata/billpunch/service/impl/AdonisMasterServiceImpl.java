package com.bata.billpunch.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bata.billpunch.dao.AdonisMasterDao;
import com.bata.billpunch.model.AdonisMasterModel;

@Service
@Transactional
public class AdonisMasterServiceImpl {

	@Autowired
	private AdonisMasterDao adao;

	
	public AdonisMasterModel getAdonisDetails() {
		
		 return adao.findAll().get(0);
	}

	
	public AdonisMasterModel save(AdonisMasterModel ad) {
		
		 return adao.save(ad);
	}
}
