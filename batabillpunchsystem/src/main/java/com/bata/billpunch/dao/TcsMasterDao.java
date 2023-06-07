package com.bata.billpunch.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bata.billpunch.model.TCSmasterModel;

@Repository
public interface TcsMasterDao extends JpaRepository<TCSmasterModel, Long> {
}
