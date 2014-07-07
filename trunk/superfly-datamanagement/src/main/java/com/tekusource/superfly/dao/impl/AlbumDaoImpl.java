package com.tekusource.superfly.dao.impl;

import org.springframework.stereotype.Repository;

import com.tekusource.superfly.dao.AlbumDao;
import com.tekusource.superfly.model.Album;

@Repository("albumDao")
public class AlbumDaoImpl extends GenericDaoImpl<Album, Long> implements AlbumDao {

	public AlbumDaoImpl() {
		super(Album.class);
	}
}
