package com.tekusource.superfly.dao.impl;

import org.springframework.stereotype.Repository;

import com.tekusource.superfly.dao.VideoGalleryDao;
import com.tekusource.superfly.model.Video;

@Repository("videoGalleryDao")
public class VideoGalleryDaoImpl extends GenericDaoImpl<Video, Long> implements VideoGalleryDao {

	public VideoGalleryDaoImpl() {
		super(Video.class);
	}
}
