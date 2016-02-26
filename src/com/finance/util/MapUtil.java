package com.finance.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.finance.model.ChangeVolMoneyModel;

public class MapUtil {
	public static void printMap(Map map) {
		int size = map.size();
		Iterator iter = map.keySet().iterator();
		double tempChange = 0;
		double tempChangePower = 0;
		double cha = 0;
		while(iter.hasNext()) {
			Object key = iter.next();
			double change = Double.parseDouble(key.toString());
			
			String value = map.get(key).toString();
			String vol  = value.split("#")[1];
			String power = value.split("#")[0];
			
			if (change > tempChange){
				tempChange = change;
				tempChangePower = Double.parseDouble(power);
			}
			cha += Double.parseDouble(power);
			//System.out.println(""+key+"	"+value.split("#")[1]+"	"+value.split("#")[0]+"	");
		}
		System.out.println(""+ (cha-tempChangePower)+"    ");
	}
	public static double toValue(Map map) {
		int size = map.size();
		Iterator iter = map.keySet().iterator();
		double tempChange = 0;
		double tempChangePower = 0;
		double cha = 0;
		while(iter.hasNext()) {
			Object key = iter.next();
			double change = Double.parseDouble(key.toString());
			
			String value = map.get(key).toString();
			String vol  = value.split("#")[1];
			String power = value.split("#")[0];
			
			if (change > tempChange){
				tempChange = change;
				tempChangePower = Double.parseDouble(power);
			}
			cha += Double.parseDouble(power);
			//System.out.println(""+key+"	"+value.split("#")[1]+"	"+value.split("#")[0]+"	");
		}
		return cha-tempChangePower;
	}
	// +3 ºÍ-3.
	public static String list3(List list) {
		int size = list.size();
		String str = "";
		String str3 = "";
		String str03 = "";
		for(int i = 0;i<size;i++){
			ChangeVolMoneyModel cvm = (ChangeVolMoneyModel)list.get(i);
			if (cvm.change.equals("0.03") ) {
				str3 = ""+cvm.vol+"\t";
			}
			if (cvm.change.equals("-0.03")) {
				str03 += cvm.vol + "\t"; 
			}
			if (cvm.change.equals("0")) {
				str += cvm.vol + "\t"; 
			}
		}
		return str3+str03+str;
	}
	public static void printList(List list){
		int size = list.size();
		for(int i = 0;i<size;i++){
			ChangeVolMoneyModel cvm = (ChangeVolMoneyModel)list.get(i);
			System.out.println(cvm.change+"\t"+cvm.vol+"\t");
		}
	}
}
