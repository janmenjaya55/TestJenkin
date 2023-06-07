package com.bata.billpunch.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bata.billpunch.model.ShopMasterModel;
import com.bata.billpunch.model.dto.ReceivingLocDto;

@Repository
public interface ShopMasterDao extends JpaRepository<ShopMasterModel, Long>{

	
	 public void save(List<ShopMasterModel> mn);
		
		@Modifying
		@Query(nativeQuery = true ,value="TRUNCATE TABLE tm_shop_master_dtls")
		 void findWithDeleteAll();
		
		@Query(nativeQuery = true, value = "select a.* from  tm_shop_master_dtls a where TRIM(a.SHOP_NAME) like UPPER(?1) ")
		public List<ShopMasterModel> findWithShopDetails(String shopcode);
		
		@Query(nativeQuery = true, value = "select a.* from  tm_shop_master_dtls a where a.SHOP_NO like ?1 ")
		public ShopMasterModel findWithShopName(String shopcode);
		
		@Query(nativeQuery = true, value = "select a.* from  tm_shop_master_dtls a where a.SHOP_NO like ?1 ")
		public ShopMasterModel findWithShopNo(String shopno);
		
		
		@Query(nativeQuery = true, value = "select a.* from  tm_shop_master_dtls a where TRIM(a.SHOP_NAME) like UPPER(?1) ")
		public ShopMasterModel findWithShopNoFormanual(String shopno);
		
		
		
		@Query(nativeQuery = true, value = "SELECT distinct  a.SHOP_NO as receiveLocCode ,trim(a.SHOP_NAME) as receiveLocName FROM tm_shop_master_dtls a where a.SHOP_NO is not null and a.SHOP_NAME is not null")
		public List<ReceivingLocDto> findAllReceivingLocManual();
		
		
		}
