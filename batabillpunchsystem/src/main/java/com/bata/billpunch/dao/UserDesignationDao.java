package com.bata.billpunch.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bata.billpunch.model.UserDesignationModel;

@Repository
public interface UserDesignationDao extends JpaRepository<UserDesignationModel, Long> {
	public UserDesignationModel  findByUserDegn(String userDegn);
}
