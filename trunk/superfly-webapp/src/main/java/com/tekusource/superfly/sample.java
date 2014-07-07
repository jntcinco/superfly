package com.tekusource.superfly;

import java.io.File;

public class sample {

	public static void main(String[] args) {
		File f = new File("C:/cincojt/java/workspace/superfly/superfly-webapp/src/main/webapp/gallery/image/");
		if(!f.exists()) {
			f.mkdirs();
		}
		System.out.println("success!");
	}
}
