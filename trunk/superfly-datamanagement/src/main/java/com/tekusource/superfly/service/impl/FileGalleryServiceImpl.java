package com.tekusource.superfly.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tekusource.superfly.dao.ImageGalleryDao;
import com.tekusource.superfly.dao.VideoGalleryDao;
import com.tekusource.superfly.model.Image;
import com.tekusource.superfly.model.UserDetail;
import com.tekusource.superfly.model.UserSession;
import com.tekusource.superfly.model.Video;
import com.tekusource.superfly.service.FileGalleryService;
import com.tekusource.superfly.service.UserService;

@Service("fileGalleryService")
@Transactional
public class FileGalleryServiceImpl implements FileGalleryService {

	@Autowired
	private ImageGalleryDao imageGalleryDao;
	
	@Autowired
	private VideoGalleryDao videoGalleryDao;
	
	@Autowired
	private UserService userService;
	
	// Image methods
	public void saveImage(Image image) {
		if(image != null) {
			imageGalleryDao.create(image);
		}
	}

	public void updateImage(Image image) {
		if(image != null) {
			imageGalleryDao.update(image);
		}
	}

	public void removeImage(Long id) {
		if(id != null) {
			imageGalleryDao.remove(id);
		}
	}

	public Image getImageBy(Long id) {
		return (Image) imageGalleryDao.get(id);
	}

	public Image getImageByFileName(String value) {
		return (Image) imageGalleryDao.getBy(COLUMN_FILENAME, value);
	}

	public Image getImageBy(Map<String, Object> values) {
		Map<String, Boolean> orders = new HashMap<String, Boolean>();
		orders.put("", true);
		return (Image) imageGalleryDao.getBy(values, orders);
	}

	public List<Image> getAllImages() {
		return (List<Image>) imageGalleryDao.getAll();
	}
	
	// Video methods
	public void saveVideo(Video video) {
		if(video != null)
			videoGalleryDao.create(video);
	}
	
	public List<Video> getAllVideos() {
		return (List<Video>) videoGalleryDao.getAll();
	}
	
	public Video getVideoByFileName(String value) {
		return (Video) videoGalleryDao.getBy(COLUMN_FILENAME, value);
	}
	
	public boolean isFileUploaded(UserSession userSession, List<FileItem> fileItems, Image image, Video video, String remoteFilePath) {
		String fileName = null, fileDescription = null;
		boolean isSuccess = true;
		for(FileItem fileItem : fileItems) {
			if(!fileItem.isFormField()) {
				fileName = fileItem.getName();
				File storageFolder = new File(remoteFilePath);
				if(!storageFolder.exists()) {
					storageFolder.mkdirs();
				}
		        File fNew= new File(remoteFilePath, fileName);
		        if(!fNew.exists()) {
		        	try {
			        	fileItem.write(fNew);
		        	} catch(Exception e) {
		        		isSuccess = false;
		        	}
		        } else {
		        	isSuccess = false;
		        }
			} else {
				fileDescription = fileItem.getString();
			}
			if(fileName != null && fileDescription != null) {
				UserDetail userDetail = null;
				if(userSession != null) {
					userDetail = (UserDetail) userService.getUserByUsername(userSession.getUsername());
		        }
				if(image != null) {
			        image.setFileDescription(fileDescription);
					image.setFileName(fileName);
		        	if(userDetail != null) 
		        		image.setUserDetail(userDetail);
					if(getImageByFileName(image.getFileName()) == null) 
						if(isSuccess)
							saveImage(image);
				} else {
					video.setFileDescription(fileDescription);
					video.setFileName(fileName);
		        	if(userDetail != null) 
		        		video.setUserDetail(userDetail);
					if(getVideoByFileName(video.getFileName()) == null)
						if(isSuccess)
							saveVideo(video);
				}
			}
		}
		fileItems.clear();
		fileItems = null;
		return isSuccess;
	}
}
