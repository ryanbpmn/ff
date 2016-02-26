package com.finance.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class FinanceStockList {
	//
	String hua = "http://vip.stock.finance.sina.com.cn/quotes_service/api/json_v2.php/Market_Center.getHQNodeData?page=0&num=2800&sort=symbol&asc=1&node=sh_a&symbol=&_s_r_a=page";
	String sza = "";
	
	public static void main(String[] args){
		new FinanceStockList().getHuA();
	}
	
	public JSONArray getHuA(){
		String hu = getResponse(this.hua);
		JSONArray array = JSONArray.fromObject(hu);
//		JSONObject item ;
//		for(int i = 0;i<array.size();i++){
//			item = JSONObject.fromObject(array.get(i));
//			System.out.println(item.get("symbol"));
//		}
		return array;
	}
	
	public static String getResponse(String requestUrl){
		  StringBuffer sb = new StringBuffer();
	        InputStream ips = getInputStream(requestUrl);
	        InputStreamReader isreader = null;
	        try {
	            isreader = new InputStreamReader(ips, "utf-8");
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        }
	        BufferedReader bufferedReader = new BufferedReader(isreader);
	        String temp = null;
	        try {
	            while ((temp = bufferedReader.readLine()) != null) {
	                sb.append(temp);
	            }
	            bufferedReader.close();
	            isreader.close();
	            ips.close();
	            ips = null;
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return sb.toString();
	}
	private static InputStream getInputStream(String requestUrl) {
        URL url = null;
        HttpURLConnection conn = null;
        InputStream in = null;
        try {
            url = new URL(requestUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setRequestMethod("GET");
            conn.connect();
 
            in = conn.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return in;
    }
}
