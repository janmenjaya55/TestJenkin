package com.bata.billpunch.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bata.billpunch.model.BillPunchDetailsModel;

@Repository
public interface BillPunchDetailsDemoDao extends JpaRepository<BillPunchDetailsModel, Long> {

	public List<BillPunchDetailsModel> save(List<BillPunchDetailsModel> mn);

}
