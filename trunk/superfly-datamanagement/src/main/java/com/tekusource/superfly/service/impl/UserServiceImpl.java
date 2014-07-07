package com.tekusource.superfly.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tekusource.superfly.dao.UserDao;
import com.tekusource.superfly.model.UserDetail;
import com.tekusource.superfly.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	public void save(UserDetail user) {
		if(user != null) {
			userDao.create(user);
		}
	}
	
	public void update(UserDetail user) {
		if(user != null) {
			userDao.update(user);
		}
	}
	
	public void remove(Long id) {
		if(id != null) {
			userDao.remove(id);
		}
	}
	
	public UserDetail getUserBy(Long id) {
		return userDao.get(id);
	}
	
	public UserDetail getUserByUsername(String value) {
		return (UserDetail) userDao.getBy(USER_NAME, value);
	}
	
	public UserDetail getUserBy( Map<String, Object> values) {
		Map<String, Boolean> orders = new HashMap<String, Boolean>();
		orders.put(LAST_NAME, true);
		return (UserDetail) userDao.getBy(values, orders);
	}
	
	public List<UserDetail> getAllUser() {
		return userDao.getAll();
	}
	
	public boolean isUserExist(String username, String password) {
		Map<String, Object> values = new HashMap<String, Object>();
		values.put( "username", username );
		values.put( "password", password );

        Map<String, Boolean> orders = new HashMap<String, Boolean>();
        orders.put( "username", true );
		UserDetail user = (UserDetail) userDao.getBy(values, orders);
		
		if(user != null) {
			return true;
		} else {
			return false;
		}
	}
}
