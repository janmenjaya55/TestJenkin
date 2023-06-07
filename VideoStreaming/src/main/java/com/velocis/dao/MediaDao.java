package com.velocis.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.velocis.model.MediaMaster;



public interface MediaDao extends JpaRepository<MediaMaster, Long>{
	@Query(nativeQuery = true,value = "SELECT distinct SUBSTRING_INDEX(file_name, \".\", 1)  FROM angular_login.tm_media_master ")
	public List<String> getFileNames();
	
	@Query(nativeQuery = true,value = "SELECT distinct file_name a FROM angular_login.tm_media_master where file_name like ?1")
	public String getFileNamesbyfilter(String filename);

}
