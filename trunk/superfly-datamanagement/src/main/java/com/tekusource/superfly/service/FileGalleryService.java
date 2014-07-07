package com.tekusource.superfly.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;

import com.tekusource.superfly.model.Image;
import com.tekusource.superfly.model.UserSession;
import com.tekusource.superfly.model.Video;

public interface FileGalleryService {

	public final static String COLUMN_FILENAME										= "fileName";
	
	// image methods
	public void saveImage(Image image);

	public void updateImage(Image image);

	public void removeImage(Long id);

	public Image getImageBy(Long id);

	public Image getImageByFileName(String value);

	public Image getImageBy(Map<String, Object> values);

	public List<Image> getAllImages();
	
	public boolean isFileUploaded(UserSession userSession, List<FileItem> fileItems, Image image, Video video, String remoteFilePath);
	
	// video methods
	public void saveVideo(Video video);
	
	public List<Video> getAllVideos();
	
	public Video getVideoByFileName(String value);
}
