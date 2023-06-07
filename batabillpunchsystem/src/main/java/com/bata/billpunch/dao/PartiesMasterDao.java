package com.bata.billpunch.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bata.billpunch.model.PartiesMasterModel;
import com.bata.billpunch.model.dto.PartyResponseDto;

@Repository
public interface PartiesMasterDao extends JpaRepository<PartiesMasterModel, Long>{

	 public void save(List<PartiesMasterModel> mn);
		
		@Modifying
		@Query(nativeQuery = true ,value="TRUNCATE TABLE tm_parties_master_dtls")
		 void findWithDeleteAll();
		
		@Query(nativeQuery = true,value = "SELECT distinct  max(a.PARTY_CODE) as partycode,max(a.PARTY_NAME) as partyname FROM TT_BILL_PUNCH_DTLS_ONE a where a.PARTY_CODE is not null and a.PARTY_NAME is not null group by a.PARTY_CODE")
		public List<PartyResponseDto> findWithAllPartycodeAndPartyName();
		
		@Query(nativeQuery = true,value = "SELECT   a.* FROM TM_PARTIES_MASTER_DTLS a  where a.PARTY_NO like ?1")
		public PartiesMasterModel findWithPartyName(String code);
}
