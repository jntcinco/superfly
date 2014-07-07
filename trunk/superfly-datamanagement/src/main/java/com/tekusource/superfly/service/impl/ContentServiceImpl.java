package com.tekusource.superfly.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tekusource.superfly.dao.ContentDao;
import com.tekusource.superfly.model.Content;
import com.tekusource.superfly.service.ContentService;

@Service("contentService")
@Transactional
public class ContentServiceImpl implements ContentService {

	@Autowired
	private ContentDao contentDao;
	
	public void saveContent(Content content) {
		if(content != null) {
			contentDao.create(content);
		}
	}
	
	public void updateContent(Content content) {
		if(content != null) {
			contentDao.update(content);
		}
	}
	
	public void removeContent(Long id) {
		if(id != null) {
			contentDao.remove(id);
		}
	}
	
	public Content getContentBy(Long id) {
		return (Content) contentDao.get(id);
	}
	
	public Content getContentByPage(String value) {
		return (Content) contentDao.getBy("page", value);
	}
	
	public Content getContentBy(Map<String, Object> values) {
		Map<String, Boolean> orders = new HashMap<String, Boolean>();
		orders.put("", true);
		return (Content) contentDao.getBy(values, orders);
	}
	
	public List<Content> getAllContents() {
		return contentDao.getAll();
	}
}
