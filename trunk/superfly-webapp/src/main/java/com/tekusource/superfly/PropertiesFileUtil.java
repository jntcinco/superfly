package com.tekusource.superfly;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFileUtil {

	private InputStream in = null;
	private Properties properties;
	private static PropertiesFileUtil INSTANCE;
	
	private PropertiesFileUtil() {
		properties = new Properties();
	}
	
	public static PropertiesFileUtil getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new PropertiesFileUtil();
		}
		return INSTANCE;
	}
	
	public Properties loadPropertiesFile() {
		try {
			in = (InputStream) this.getClass().getResourceAsStream("/superfly.properties");
			properties.load(in);
		} catch(IOException io) {
			
		} 
		return properties;
	}
	
	public String getProperty(String name) {
		return properties.getProperty(name);
	}
	
	public void close() {
		if(in != null) {
			try {
				in.close();
				in = null;
			} catch(IOException i) {
				
			}
		}
	}
}
