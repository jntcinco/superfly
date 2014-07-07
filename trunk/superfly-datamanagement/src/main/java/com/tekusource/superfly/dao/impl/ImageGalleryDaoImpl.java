package com.tekusource.superfly.dao.impl;

import org.springframework.stereotype.Repository;

import com.tekusource.superfly.dao.ImageGalleryDao;
import com.tekusource.superfly.model.Image;

@Repository("imageGalleryDao")
public class ImageGalleryDaoImpl extends GenericDaoImpl<Image, Long> implements ImageGalleryDao {

	public ImageGalleryDaoImpl() {
		super(Image.class);
	}
}
