package com.chart;

import com.finance.stock.model.fenjia;

public class ChartUtil {
	public static String json = "";
	public static String toJson(fenjia fj){
		 String type = fj.type.equals("бТел") ? "-" : "";
		 json=json+"{'data':'"+fj.jiaoyi_date.toLocaleString()+"','price':"+fj.price+",'vol':'"+type+""+fj.vol+"'},";
		return json;
	}
}
