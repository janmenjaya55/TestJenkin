package com.bata.billpunch.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.bata.billpunch.dao.TcsMasterDao;
import com.bata.billpunch.model.TCSmasterModel;

@Service
@Transactional
public class TcsMasterServiceimpl {
	@Autowired
	private TcsMasterDao dao;

	public TCSmasterModel getById(Long tcsId) {

		TCSmasterModel cm = null;
		if (Optional.ofNullable(tcsId).isPresent())
			cm = dao.findById(tcsId).get();

		return cm;

	}

	public List<TCSmasterModel> getAll() {

		return dao.findAll();
	}

	public TCSmasterModel update(TCSmasterModel entity) {
		TCSmasterModel cm = null;
		if (Optional.ofNullable(entity.getTcsId()).isPresent()) {
			Optional<TCSmasterModel> op = dao.findById(entity.getTcsId());
			if (op.isPresent()) {
				cm = dao.save(entity);
			} else {
				throw new ResourceAccessException("Data not found for the given Id.");
			}

		} else {
			throw new ResourceAccessException("Given id is not present.");
		}

		return cm;
	}

}
