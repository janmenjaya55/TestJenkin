package com.bata.billpunch.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.bata.billpunch.dao.UserDesignationDao;
import com.bata.billpunch.model.UserDesignationModel;

@Service
@Transactional
public class UserDesignationServiceimpl  {
	@Autowired
	private UserDesignationDao dao;

	public UserDesignationModel save(UserDesignationModel entity) {
		UserDesignationModel m = null;
		UserDesignationModel cm = dao.findByUserDegn(entity.getUserDegn());
		if (Optional.ofNullable(cm).isPresent()) {
			throw new ResourceAccessException("Sorry user depertment already exist, Please try with different depertment.");
		} else {
			if(Optional.ofNullable(entity.getUserDegn()).isPresent()) {
				String xm = entity.getUserDegn(); 
				String vm = xm.replaceAll(" ", "_").toLowerCase();
				
				entity.setUserDegnKey(vm);
				m = dao.save(entity);
			}else {
				throw new ResourceAccessException(" Please try with valid Designation.");
			}
			

		}

		return m;
	}

	public UserDesignationModel getById(Long deptId) {

		UserDesignationModel cm = null;
		if (Optional.ofNullable(deptId).isPresent())
			cm = dao.findById(deptId).get();

		return cm;

	}

	public List<UserDesignationModel> getAll() {

		return dao.findAll();
	}

	public UserDesignationModel update(UserDesignationModel entity) {
		UserDesignationModel cm = null;
		if (Optional.ofNullable(entity.getDegnId()).isPresent()) {
			Optional<UserDesignationModel> op = dao.findById(entity.getDegnId());
			if (op.isPresent()) {
				cm = dao.save(entity);
			} else {
				throw new ResourceAccessException("Data not found for the given Id.");
			}

		} else {
			throw new ResourceAccessException("Given id is not present.");
		}

		return cm;
	}

}
