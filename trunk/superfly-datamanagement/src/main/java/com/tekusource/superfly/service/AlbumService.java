package com.tekusource.superfly.service;

import java.util.List;
import java.util.Map;

import com.tekusource.superfly.model.Album;

public interface AlbumService {

	public static final String COLUMN_CATEGORY										= "category";
	public static final String COLUMN_NAME											= "name";
	
	public void saveAlbum(Album album);

	public void updateAlbum(Album album);

	public void removeAlbum(Long id);

	public Album getAlbumById(Long id);

	public Album getAlbumByCategory(String value);
	
	public Album getAlbumByName(String value);

	public Album getAlbumBy(Map<String, Object> values);

	public List<Album> getAllAlbums();
}
