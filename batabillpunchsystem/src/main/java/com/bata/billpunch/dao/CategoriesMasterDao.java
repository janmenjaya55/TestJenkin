package com.bata.billpunch.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bata.billpunch.model.CategoriesMasterModel;

@Repository
public interface CategoriesMasterDao extends JpaRepository<CategoriesMasterModel, Long>{

	
	 public void save(List<CategoriesMasterModel> mn);
		
		@Modifying
		@Query(nativeQuery = true ,value="TRUNCATE TABLE tm_category_master_dtls")
		 void findWithDeleteAll();
		
		@Query(nativeQuery = true, value = "select a.* from  TM_CATEGORY_MASTER_DTLS a where TRIM(a.CATNAME) like UPPER(?1) ")
		public List<CategoriesMasterModel> findWithCatDetails(String catname);

}
