package com.tekusource.superfly.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tekusource.superfly.dao.AlbumDao;
import com.tekusource.superfly.model.Album;
import com.tekusource.superfly.service.AlbumService;

@Service("albumService")
@Transactional
public class AlbumServiceImpl implements AlbumService {

	@Autowired
	private AlbumDao albumDao;
	
	public void saveAlbum(Album album) {
		if(album != null) {
			albumDao.create(album);
		}
	}

	public void updateAlbum(Album album) {
		if(album != null) {
			albumDao.update(album);
		}
	}

	public void removeAlbum(Long id) {
		if(id != null) {
			albumDao.remove(id);
		}
	}

	public Album getAlbumById(Long id) {
		return (Album) albumDao.get(id);
	}

	public Album getAlbumByCategory(String value) {
		return (Album) albumDao.getBy(COLUMN_CATEGORY, value);
	}

	public Album getAlbumByName(String value) {
		return (Album) albumDao.getBy(COLUMN_NAME, value);
	}

	public Album getAlbumBy(Map<String, Object> values) {
		Map<String, Boolean> orders = new HashMap<String, Boolean>();
		orders.put("", true);
		return (Album) albumDao.getBy(values, orders);
	}

	public List<Album> getAllAlbums() {
		return albumDao.getAll();
	}
}
