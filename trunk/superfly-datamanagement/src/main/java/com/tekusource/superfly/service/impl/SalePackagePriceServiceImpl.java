package com.tekusource.superfly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tekusource.superfly.dao.BloodLineDao;
import com.tekusource.superfly.dao.SalePackageDao;
import com.tekusource.superfly.model.BloodLine;
import com.tekusource.superfly.model.SalePackage;
import com.tekusource.superfly.service.SalePackagePriceService;

@Service("salePackagePriceService")
@Transactional
public class SalePackagePriceServiceImpl implements SalePackagePriceService {

	@Autowired
	private BloodLineDao bloodLineDao;
	
	@Autowired
	private SalePackageDao salePackageDao;
	
	public void saveBloodLine(BloodLine bloodLine) {
		bloodLineDao.create(bloodLine);
	}
	
	public void saveSalePackage(SalePackage salePackage) {
		salePackageDao.create(salePackage);
	}

	public void updateSalePackage(SalePackage salePackage) {
		salePackageDao.update(salePackage);
	}
	
	public void updateBloodLine(BloodLine bloodLine) {
		if(bloodLine != null) {
			bloodLineDao.update(bloodLine);
		}
	}
	
	public List<BloodLine> getBloodLines() {
		return (List<BloodLine>) bloodLineDao.getAll();
	}
	
	public List<SalePackage> getSalePackages() {
		return (List<SalePackage>) salePackageDao.getAll();
	}
	
	public boolean isBloodlineSave(BloodLine bloodLine) {
		if(bloodLine != null) {
			saveBloodLine(bloodLine);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isSalePackageSave(SalePackage salePackage) {
		if(salePackage != null) {
			saveSalePackage(salePackage);
			return true;
		} else {
			return false;
		}
	}
}
