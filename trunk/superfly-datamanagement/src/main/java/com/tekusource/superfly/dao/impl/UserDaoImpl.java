package com.tekusource.superfly.dao.impl;

import org.springframework.stereotype.Repository;

import com.tekusource.superfly.dao.UserDao;
import com.tekusource.superfly.model.UserDetail;

@Repository("userDao")
public class UserDaoImpl extends GenericDaoImpl<UserDetail, Long> implements UserDao {

	public UserDaoImpl() {
		super(UserDetail.class);
	}
}
