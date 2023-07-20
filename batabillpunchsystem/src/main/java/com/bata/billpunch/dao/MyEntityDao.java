package com.bata.billpunch.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bata.billpunch.model.AdonisMasterModel;
import com.bata.billpunch.model.MyEntityXml;

@Repository
public interface MyEntityDao extends JpaRepository<MyEntityXml, Long> {

}
