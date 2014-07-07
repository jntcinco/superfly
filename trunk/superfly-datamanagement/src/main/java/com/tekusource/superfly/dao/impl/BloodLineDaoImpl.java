package com.tekusource.superfly.dao.impl;

import org.springframework.stereotype.Repository;

import com.tekusource.superfly.dao.BloodLineDao;
import com.tekusource.superfly.model.BloodLine;

@Repository("bloodLineDao")
public class BloodLineDaoImpl extends GenericDaoImpl<BloodLine, Long> implements BloodLineDao {

	public BloodLineDaoImpl() {
		super(BloodLine.class);
	}
}
