package com.bata.billpunch.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bata.billpunch.dao.DistMasterDao;
import com.bata.billpunch.model.DistMasterModel;

@Service
@Transactional
public class DistMasterServiceImpl {

	@Autowired
	private DistMasterDao ddao;

	public DistMasterModel getDistDetails(String distcode) {

		return ddao.findwithDistCode(distcode);
	}

}
