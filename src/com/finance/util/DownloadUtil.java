package com.finance.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DownloadUtil {
	public static String baseUrl = "http://market.finance.sina.com.cn/downxls.php?date=[#]&symbol=$$";
	
	//2015-6-10.
	
	public  void downloadCJMX(String b,String code,Date from,Date to,String filePath){

		 Calendar   calendar   =   new   GregorianCalendar(); 
	     
		URL url;
		Date temp = from;
		try {
			while (temp.before(to) || temp.equals(to)) {
				String burl = b;
				String date = temp.getYear()+1900 + "-"+
							  (temp.getMonth()+1 >=10 ? temp.getMonth()+1 : "0"+(temp.getMonth()+1)) +
							  "-"+(temp.getDate() >=10 ? temp.getDate() : "0"+temp.getDate());
				//System.out.println(temp.getYear()+"-"+date);
				burl = burl.replace("[#]",date);
				if (code.startsWith("6"))
					burl = burl.replace("$$", "sh"+code);
				if (code.startsWith("0")  )
					burl = burl.replace("$$", "sz"+code);
				if (code.startsWith("3")  )
					burl = burl.replace("$$", "sz"+code);
				if (code.startsWith("2"))
					burl = burl.replace("$$", "sh"+code);
				//System.out.println("url£º"+burl);
				url = new URL(burl);
				System.out.println("url:"+url);
				URLConnection conn = url.openConnection();
				InputStream is = conn.getInputStream();
				FileOutputStream fs = new FileOutputStream(filePath+"\\"+code+"_"+date+".xls");
				byte[] buffer = new byte[1024];
				int length=0;
				 while ((length = is.read(buffer)) != -1) {
		                fs.write(buffer, 0, length);
		            }
				 calendar.setTime(temp); 
			     calendar.add(calendar.DATE,1);
			     temp = calendar.getTime();
			     //System.out.println(temp);
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			 calendar.setTime(temp); 
		     calendar.add(calendar.DATE,1);
		     temp = calendar.getTime();
		     downloadCJMX(b,"",temp, to,filePath);
		}
	
	}
	
	public static void main(String[] args) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date from = df.parse("2015-08-05");
			Date to =  df.parse("2015-08-07");
			String filePath = "";
			new DownloadUtil().downloadCJMX(baseUrl,"600399",from,to,filePath);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
