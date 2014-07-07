package com.tekusource.superfly.service;

import java.util.List;

import com.tekusource.superfly.model.BloodLine;
import com.tekusource.superfly.model.SalePackage;

public interface SalePackagePriceService {

	public void saveBloodLine(BloodLine bloodLine);
	public void saveSalePackage(SalePackage salePackage);
	public void updateBloodLine(BloodLine bloodLine);
	public void updateSalePackage(SalePackage salePackage);
	public List<BloodLine> getBloodLines();
	public List<SalePackage> getSalePackages();
	public boolean isBloodlineSave(BloodLine bloodLine);
	public boolean isSalePackageSave(SalePackage salePackage);
}
