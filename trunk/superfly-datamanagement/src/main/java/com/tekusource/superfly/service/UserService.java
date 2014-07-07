package com.tekusource.superfly.service;

import java.util.List;
import java.util.Map;

import com.tekusource.superfly.model.UserDetail;

public interface UserService {
	
	public static final String LAST_NAME					= "lastname";
	public static final String FIRST_NAME					= "firstname";
	public static final String EMAIL						= "email";
	public static final String USER_NAME					= "username";
	public static final String PASS_WORD					= "password";
	
	public void save(UserDetail user);
	
	public void update(UserDetail user);
	
	public void remove(Long id);
	
	public UserDetail getUserBy(Long id);
	
	public UserDetail getUserByUsername(String value);
	
	public UserDetail getUserBy(Map<String, Object> values);
	
	public List<UserDetail> getAllUser();
	
	public boolean isUserExist(String username, String password);
}
