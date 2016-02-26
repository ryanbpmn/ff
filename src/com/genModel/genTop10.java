package com.genModel;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class genTop10 {
	public static String baseUrl = "http://stock.quote.stockstar.com/share/holdertop10_$$.shtml";
	
	public void gen(){
		try {
			URL url = new URL(baseUrl);
			URLConnection cnn = url.openConnection();
			cnn.connect();
			InputStream is = cnn.getInputStream();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
