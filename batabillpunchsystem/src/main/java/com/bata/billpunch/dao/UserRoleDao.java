package com.bata.billpunch.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bata.billpunch.model.UserRoleModel;

@Repository
public interface UserRoleDao extends JpaRepository<UserRoleModel, Long> {
public UserRoleModel  findByUserRole(String userRole);
}
