package com.finance.util;

import java.io.File;

public class FileList {
	public static String[] fileList(String baseFile) {
		File f = new File(baseFile);
		File[] files = f.listFiles();
		String results[] = new String[files.length];
		for(int i = 0;i<files.length;i++) {
			results[i] = files[i].getPath();
		}
		return results;
	}
}
