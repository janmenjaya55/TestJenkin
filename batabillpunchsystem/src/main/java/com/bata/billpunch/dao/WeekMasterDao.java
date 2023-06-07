package com.bata.billpunch.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bata.billpunch.model.WeekMasterModel;

@Repository
public interface WeekMasterDao extends JpaRepository<WeekMasterModel, Long> {

	
	 public void save(List<WeekMasterModel> mn);
		
		@Modifying
		@Query(nativeQuery = true ,value="TRUNCATE TABLE tm_week_master_dtls")
		 void findWithDeleteAll();
		
		@Query(nativeQuery = true, value = "select a.* from  TM_WEEK_MASTER_DTLS a where a.BATA_WEEK like ?1 ")
		public List<WeekMasterModel> findWithWeekDetails(String weekcode);
}
