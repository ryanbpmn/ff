package com.finance.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DayFileUtil {
	public static void main(String[] args) {
		String file = "F:\\baidu\\finance_project\\model\\600399\\sh600399.day"
				+ "";
		try {
			FileInputStream fis = new FileInputStream(new File(file));
			byte[] bs = new byte[1024];
			BufferedReader br = new BufferedReader(new FileReader(file));
			String str = "";
			while((str=br.readLine()) != null) {
				System.out.println(str);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
