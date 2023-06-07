package com.bata.billpunch.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.bata.billpunch.dao.UserRoleDao;
import com.bata.billpunch.model.UserRoleModel;

@Service
@Transactional
public class UserRoleServiceImpl  {

	@Autowired
	private UserRoleDao dao;

	public UserRoleModel save(UserRoleModel entity) {
		UserRoleModel m = null;
		UserRoleModel cm = dao.findByUserRole(entity.getUserRole());
		if (Optional.ofNullable(cm).isPresent()) {
			throw new ResourceAccessException("Sorry user role already exist, Please try with different role.");
		} else {
			if(Optional.ofNullable(entity.getUserRole()).isPresent()) {
				String xm = entity.getUserRole();
				String vm = xm.replaceAll(" ", "_").toLowerCase();
				
				entity.setUserRoleKey(vm);
				m = dao.save(entity);
			}else {
				throw new ResourceAccessException(" Please try with valid Role.");
			}
			

		}

		return m;
	}

	public UserRoleModel getById(Long roleId) {
		UserRoleModel cm = null;
		if (Optional.ofNullable(roleId).isPresent())
			cm = dao.findById(roleId).get();

		return cm;

	}

	public List<UserRoleModel> getAll() {

		return dao.findAll();
	}

	public UserRoleModel update(UserRoleModel entity) {
		UserRoleModel cm = null;
		if (Optional.ofNullable(entity.getRoleId()).isPresent()) {
			Optional<UserRoleModel> op = dao.findById(entity.getRoleId());
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
