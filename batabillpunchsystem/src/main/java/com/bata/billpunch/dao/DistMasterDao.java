package com.bata.billpunch.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bata.billpunch.model.DistMasterModel;

@Repository
public interface DistMasterDao extends JpaRepository<DistMasterModel, Long>{

	
	 public void save(List<DistMasterModel> mn);
		
		@Modifying
		@Query(nativeQuery = true ,value="TRUNCATE TABLE tm_dist_master_dtls")
		 void findWithDeleteAll();
		
		 
		 @Query(nativeQuery = true, value = "SELECT a.* FROM tm_dist_master_dtls a where a.DISTRICT_CODE like ?1")
		 public DistMasterModel findwithDistCode(String distcode);
}
