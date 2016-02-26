package com.finance.util;

import java.math.BigDecimal;
import java.util.List;

import com.finance.stock.model.fenjia;

public class ArthUtil {
	/**
	 * 
	 * ���ݳɽ���ϸ��excel����,
	 * @param d
	 * @param vol
	 * @param change
	 * @return
	 */
	
	
	//�ɽ������� d[],�ܳɽ��� vol,�ɽ��۸�䶯 
	public static double[] tuidong_power(Double d[],int vol,double change[]){
		double result[] = new double[d.length];
		Double rate[] = new Double[d.length];
		for(int i = 0;i<d.length;i++) {
			rate[i] = new BigDecimal(d[i]).divide(new BigDecimal(vol),2,BigDecimal.ROUND_DOWN).doubleValue();
		}
		return result;
	}
	//��Ȩƽ��ֵ.
	
	//ƽ��ֵ.
	public static BigDecimal meanList(List<fenjia> list) {
		BigDecimal bd = new BigDecimal(0);
		BigDecimal sums  = new BigDecimal(0);
		for(int i = 0;i<list.size();i++) {
			sums = sums.add(new BigDecimal(list.get(i).getPrice()));
		}
		bd = sums.divide(new BigDecimal(list.size()),2,BigDecimal.ROUND_DOWN);
		return bd;
	}
	
	//�м���
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
