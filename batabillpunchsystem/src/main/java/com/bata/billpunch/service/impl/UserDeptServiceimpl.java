package com.bata.billpunch.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.bata.billpunch.dao.UserDeptDao;
import com.bata.billpunch.model.UserDeptModel;

@Service
@Transactional
public class UserDeptServiceimpl  {
	@Autowired
	private UserDeptDao dao;

	public UserDeptModel save(UserDeptModel entity) {
		UserDeptModel m = null;
		UserDeptModel cm = dao.findByUserDept(entity.getUserDept());
		if (Optional.ofNullable(cm).isPresent()) {
			throw new ResourceAccessException("Sorry user depertment already exist, Please try with different depertment.");
		} else {
			if(Optional.ofNullable(entity.getUserDept()).isPresent()) {
				String xm = entity.getUserDept(); 
				String vm = xm.replaceAll(" ", "_").toLowerCase();
				
				entity.setUserDeptKey(vm);
				m = dao.save(entity);
			}else {
				throw new ResourceAccessException(" Please try with valid depertment.");
			}
			

		}

		return m;
	}

	public UserDeptModel getById(Long deptId) {

		UserDeptModel cm = null;
		if (Optional.ofNullable(deptId).isPresent())
			cm = dao.findById(deptId).get();

		return cm;

	}

	public List<UserDeptModel> getAll() {

		return dao.findAll();
	}

	public UserDeptModel update(UserDeptModel entity) {
		UserDeptModel cm = null;
		if (Optional.ofNullable(entity.getDeptId()).isPresent()) {
			Optional<UserDeptModel> op = dao.findById(entity.getDeptId());
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
