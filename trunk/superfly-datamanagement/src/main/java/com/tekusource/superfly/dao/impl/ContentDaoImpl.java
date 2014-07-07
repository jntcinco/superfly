package com.tekusource.superfly.dao.impl;

import org.springframework.stereotype.Repository;

import com.tekusource.superfly.dao.ContentDao;
import com.tekusource.superfly.model.Content;

@Repository("contentDao")
public class ContentDaoImpl extends GenericDaoImpl<Content, Long> implements ContentDao {

	public ContentDaoImpl() {
		super(Content.class);
	}
}
