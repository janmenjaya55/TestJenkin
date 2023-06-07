package com.bata.billpunch.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bata.billpunch.model.RdcMasterModel;

@Repository
public interface RdcMasterDao extends JpaRepository<RdcMasterModel, Long>{

	
	 public void save(List<RdcMasterModel> mn);
		
		@Modifying
		@Query(nativeQuery = true ,value="TRUNCATE TABLE tm_rdc_master_dtls")
		 void findWithDeleteAll();
}
