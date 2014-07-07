package com.tekusource.superfly.dao.impl;

import org.springframework.stereotype.Repository;

import com.tekusource.superfly.dao.SalePackageDao;
import com.tekusource.superfly.model.SalePackage;

@Repository("salePackageDao")
public class SalePackageDaoImpl extends GenericDaoImpl<SalePackage, Long> implements SalePackageDao {

	public SalePackageDaoImpl() {
		super(SalePackage.class);
	}
}
