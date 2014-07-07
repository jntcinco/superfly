package com.tekusource.superfly;

public interface SuperflyMessages {

	// Image upload messages
	public static final String UPLOAD_SUCCESS										= "File upload successfully.";
	public static final String UPLOAD_FAILED										= "File upload failed due to: ";
	public static final String FILE_NOT_SUPPORTED									= "The file is not supported.";
	public static final String FILE_EXIST											= "File already exist.";
	
	// Admin page messages
	public static final String CONTENT_UPDATE										= "Content successfully updated.";
	public static final String CONTENT_SAVE											= "Content successfully save.";
	
	// User page messages
	public static final String INVALID_CREDENTIALS									= "Username or Password is invalid.";
	public static final String USER_EXIST											= "User already exist.";
}
