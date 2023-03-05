package com.bata.billpunch.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.bata.billpunch.login.model.UserModel;

@Component
@Repository
public interface UserRepository extends JpaRepository<UserModel,Long> {

    Boolean existsByUsername(String username);
    UserModel findByUsername(String username);
    UserModel findByEmailId(String email);


}