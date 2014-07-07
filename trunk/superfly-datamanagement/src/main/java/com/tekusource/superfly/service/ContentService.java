package com.tekusource.superfly.service;

import java.util.List;
import java.util.Map;

import com.tekusource.superfly.model.Content;

public interface ContentService {

	public void saveContent(Content content);
	
	public void updateContent(Content user);
	
	public void removeContent(Long id);
	
	public Content getContentBy(Long id);
	
	public Content getContentByPage(String value);
	
	public Content getContentBy(Map<String, Object> values);
	
	public List<Content> getAllContents();
}
