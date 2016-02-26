package com.finance.util;

import java.math.BigDecimal;
import java.util.List;

import com.finance.stock.model.fenjia;

public class ArthUtil {
	/**
	 * 
	 * 根据成交明细的excel计算,
	 * @param d
	 * @param vol
	 * @param change
	 * @return
	 */
	
	
	//成交量数组 d[],总成交量 vol,成交价格变动 
	public static double[] tuidong_power(Double d[],int vol,double change[]){
		double result[] = new double[d.length];
		Double rate[] = new Double[d.length];
		for(int i = 0;i<d.length;i++) {
			rate[i] = new BigDecimal(d[i]).divide(new BigDecimal(vol),2,BigDecimal.ROUND_DOWN).doubleValue();
		}
		return result;
	}
	//加权平均值.
	
	//平均值.
	public static BigDecimal meanList(List<fenjia> list) {
		BigDecimal bd = new BigDecimal(0);
		BigDecimal sums  = new BigDecimal(0);
		for(int i = 0;i<list.size();i++) {
			sums = sums.add(new BigDecimal(list.get(i).getPrice()));
		}
		bd = sums.divide(new BigDecimal(list.size()),2,BigDecimal.ROUND_DOWN);
		return bd;
	}
	
	//中间数
	public static Double middleList(List<fenjia> list) {
		int size = list.size();
		System.out.println(size);
		int middle = size % 2;
		System.out.println(middle);
		double result = 0;
		if (middle == 0) {
			 result = (list.get(size/2).getPrice() + list.get(size/2 -1).getPrice()) /2;
		} else {
			result = (list.get((size+1)/2).getPrice());
		}
		return result ;
	}
	
}
