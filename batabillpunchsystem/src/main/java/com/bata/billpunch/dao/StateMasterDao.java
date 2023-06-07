package com.bata.billpunch.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bata.billpunch.model.StateMasterModel;

@Repository
public interface StateMasterDao extends JpaRepository<StateMasterModel, Long>{
	
	   public void save(List<StateMasterModel> mn);
		
		@Modifying
		@Query(nativeQuery = true ,value="TRUNCATE TABLE tm_state_master_dtls")
		 void findWithDeleteAll();
		
		@Query(nativeQuery = true, value = "select a.* from  tm_state_master_dtls a where TRIM(a.STATENAME) like UPPER(?1)")
		public List<StateMasterModel> findWithStateDetails(String statename);
		
		@Query(nativeQuery = true, value = "select a.* from  tm_state_master_dtls a where a.STATECODE like ?1 ")
		public StateMasterModel findWithStateName(String statecode);
}
