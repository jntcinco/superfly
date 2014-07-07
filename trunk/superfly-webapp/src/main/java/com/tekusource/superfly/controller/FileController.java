package com.tekusource.superfly.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tekusource.superfly.PropertiesFileUtil;
import com.tekusource.superfly.SuperflyMessages;
import com.tekusource.superfly.model.Album;
import com.tekusource.superfly.model.Image;
import com.tekusource.superfly.model.UserSession;
import com.tekusource.superfly.model.Video;
import com.tekusource.superfly.service.AlbumService;
import com.tekusource.superfly.service.FileGalleryService;

@Controller
@RequestMapping(value = "/file")
public class FileController extends AbstractController {

	@Autowired
	private FileGalleryService fileGalleryService;
	
	@Autowired
	private AlbumService albumService;

	@RequestMapping(value="/image", method=RequestMethod.GET)
	public ModelAndView pageInitializer(HttpSession httpSession, ModelMap model) {
		Album album = (Album) albumService.getAlbumByCategory("image");
		if(album != null) {
			model.addAttribute("album", album);
		}
		return new ModelAndView("image_gallery", model);
	}
	
	@RequestMapping(value="/video", method=RequestMethod.GET)
	public ModelAndView video(ModelMap model) {
		Album album = (Album) albumService.getAlbumByCategory("video");
		if(album != null) {
			model.addAttribute("album", album);
		}
		return new ModelAndView("video_gallery", model);
	}
	
	@RequestMapping(value = "/upload", method=RequestMethod.POST)
	@ResponseBody
	@SuppressWarnings("unchecked")
	public Map<String, ? extends Object> upload(HttpServletRequest request) throws Exception {
		String message = "";
		if(ServletFileUpload.isMultipartContent(request)) {
			try {
	        	PropertiesFileUtil pfu = PropertiesFileUtil.getInstance();
	    		pfu.loadPropertiesFile();
				String remoteFilePath = null;
				List<FileItem> fileItems = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
		        UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");

	        	String fileName = fileItems.get(1).getName().toUpperCase();
				Album album = null;
				Image image = null;
				Video video = null;
	        	
	        	if(fileName.contains(".JPG") || fileName.contains(".JPEG") || fileName.contains(".GIF") || fileName.contains(".PNG")) {
	        		remoteFilePath = request.getSession().getServletContext().getRealPath(pfu.getProperty("superfly.remote.image.path"));
	        		album = (Album) albumService.getAlbumById(1l);
	        		image = new Image();
	        		image.setAlbum(album);
	        	} else if(fileName.contains(".MP4") || fileName.contains(".3GP") || fileName.contains(".FLV") || fileName.contains(".OGV") || fileName.contains(".WEBM")) {
	        		remoteFilePath = request.getSession().getServletContext().getRealPath(pfu.getProperty("superfly.remote.video.path"));
	        		album = (Album) albumService.getAlbumById(2l);
	        		video = new Video();
	        		video.setAlbum(album);
	        	} else {
	        		message = SuperflyMessages.FILE_NOT_SUPPORTED;
	        	}
	        	if(fileGalleryService.isFileUploaded(userSession, fileItems, image, video, remoteFilePath)) {
					message = SuperflyMessages.UPLOAD_SUCCESS;
	        	} else {
		        	message = SuperflyMessages.FILE_EXIST;
	        	}
			} 
			catch(Exception e) {
				message = SuperflyMessages.UPLOAD_FAILED + e;
			}
		}

		return Collections.singletonMap("message", message);
	}
}
