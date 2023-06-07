package com.bata.billpunch.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bata.billpunch.model.AdonisWeekMasterModel;

@Repository
public interface AdonisWeekMasterDao extends JpaRepository<AdonisWeekMasterModel, Long> {

}
