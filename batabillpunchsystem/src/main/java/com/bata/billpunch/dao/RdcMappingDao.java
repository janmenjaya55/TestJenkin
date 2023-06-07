package com.bata.billpunch.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bata.billpunch.model.RdcMappingModel;

@Repository
public interface RdcMappingDao extends JpaRepository<RdcMappingModel, Long> {
	
	public RdcMappingModel findByRdcno(String rdcno);
	
	public List<RdcMappingModel> findByMergerdccode(String rdcno);

}
