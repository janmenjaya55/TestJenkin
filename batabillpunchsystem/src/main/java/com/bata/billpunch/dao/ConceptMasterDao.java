package com.bata.billpunch.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bata.billpunch.model.ConceptMasterModel;

@Repository
public interface ConceptMasterDao extends JpaRepository<ConceptMasterModel, Long>{

	
	 public void save(List<ConceptMasterModel> mn);
		
		@Modifying
		@Query(nativeQuery = true ,value="TRUNCATE TABLE tm_concept_master_dtls")
		 void findWithDeleteAll();
}
