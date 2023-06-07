package com.bata.billpunch.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bata.billpunch.model.UserDeptModel;

@Repository
public interface UserDeptDao extends JpaRepository<UserDeptModel, Long> {
	public UserDeptModel  findByUserDept(String userDept);
}
