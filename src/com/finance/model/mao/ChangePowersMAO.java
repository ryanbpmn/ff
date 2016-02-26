package com.finance.model.mao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.chart.ChartUtil;
import com.finance.model.ChangePowersModel;
import com.finance.model.ChangeVolMoneyModel;
import com.finance.stock.model.fenjia;
import com.finance.util.ExcelUtil;
import com.finance.util.FileList;
import com.finance.util.MapUtil;
import com.finance.util.TxtUtil;

public class ChangePowersMAO {
	
	public static void main(String[] args) {
		
		String[] files = FileList.fileList("F:\\baidu\\finance_project\\model\\cjmx\\601328\\");
		Map[] all = new Map[files.length];
		for(int i = 0;i<files.length;i++) {
			//System.out.println(files[i]);
			ChangePowersMAO mao = new ChangePowersMAO();
			ChangePowersModel model = mao.toModel(files[i],"2015-08-07");
			MapUtil.printMap(model.change_powers);  //console打印模型
			all[i] = model.change_powers;
		}
		//ExcelUtil.genExcel(all, "F:\\baidu\\finance_project\\model\\bb.xls");
	}
	
	public String genKey2(Double change) {
		if (change == 0.01) {
			return "0.01";
		}else if (change == 0.02){
			return "0.02";
		}else if  (change >=0.03 && change <4){
			return "0.03";
		}else if  (change ==-0.01){
			return "-0.01";
		}else if  (change == -0.02){
			return "-0.02";
		}else if  (change <=-0.03 ){
			return "-0.03";
		}else if  (change == 10000){
			return "0";
		}else {
			return change+"";
		}
	}
	//根据change生成Key.
	public String genKey(double change) {
		if (change == 0.01) 
			return "0.01";
		if (change > 0.01 && change <= 0.05)
			return "0.05";
		if (change > 0.05 && change <= 0.1)
			return "0.10";
		if (change > 0.1 && change <=0.5)
			return "0.50";
		if (change > 0.5 && change <= 1) 
			return "1.00";
		if (change > 1 && change <= 2) {
			return "2.00";
		}
		if (change > 2 && change < 10)
			return change+"";
		if (change == -0.01)
			return "-0.01";
		if (change >=-0.05 && change < -0.01)
			return "-0.05";
		if (change >=-0.10 && change < -0.05)
			return "-0.10";
		if (change >=-0.50 && change < -0.10)
			return "-0.50";
		if (change >=-1 && change < -0.50)
			return "-1.00";
		if (change >=-2 && change < -1)
			return "-2.00";
		if (change < -2 && change > -4)
			return "-4";
		if (change <-4 && change > -8)
			return "-8";
		if (change < -8) 
			return "-"+change;
		return change+"";
		
	}
	public  List toCVMModel(String excelPath,String date) {
	       //System.out.println(excelPath);
	       List lists = new LinkedList();
			ChangePowersModel model = new ChangePowersModel();
			try {
				List<fenjia> list = TxtUtil.getColData(1, excelPath,date);
				System.out.println(list.size());
				//计算出power, 根据change价格-power,生成model.
				int mindex = 0;
				Map temp = new HashMap();
				Map result = new HashMap();
				double prePrice = 0;
				String preType = "";
				fenjia prefj = null;
				int alll = 0;
				for(int i = 0;i<list.size();i++) {
					//ChartUtil.toJson(list.get(i));
//					System.out.println(list.get(i).change);
					double change = Double.parseDouble(list.get(i).change.trim());
					double money = list.get(i) == null ? 0 : list.get(i).money;
					double vol = list.get(i) == null ? 0 : list.get(i).vol;
					String key =genKey2(change);
//					System.out.println(key);
						String value[] = (result.get(key) == null) ? new String[]{"0","0"} : result.get(key).toString().split("#");
						double vols = Double.parseDouble(value[0]);
						double moneys = Double.parseDouble(value[1]);
						result.put(key, (vols+vol)+"#"+(moneys+money));
					
					
				} 
				Iterator iter = result.keySet().iterator();
				while(iter.hasNext()){
					ChangeVolMoneyModel cvm = new ChangeVolMoneyModel();
					String key = iter.next().toString();
					String value[] = (result.get(key) == null) ? new String[]{"0","0"} : result.get(key).toString().split("#");
					double vols = Double.parseDouble(value[0]);
					double moneys =  Double.parseDouble(value[1]);
					cvm.setChange(key);
					cvm.setMoney(moneys);
					cvm.setVol(vols);
//					System.out.println(cvm);
					lists.add(cvm);
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return lists;
		}
	public  ChangePowersModel toModel(String excelPath,String date) {
       System.out.println(excelPath);
		ChangePowersModel model = new ChangePowersModel();
		
		try {
			List<fenjia> list = TxtUtil.getColData(1, excelPath,date);
			//System.out.println(list.size());
			//计算出power, 根据change价格-power,生成model.
			model.setSize(list.size());
			int mindex = 0;
			Map temp = new HashMap();
			Map result = new HashMap();
			double prePrice = 0;
			String preType = "";
			fenjia prefj = null;
			int alll = 0;
			for(int i = 0;i<list.size();i++) {

				ChartUtil.toJson(list.get(i));
				if (!list.get(i).change.equals("10000")){
					
					double change = Double.parseDouble(list.get(i).change.trim());
					String key =genKey(change);
					
					//System.out.println(temp.get(change));
					String value[] = (result.get(key) == null) ? new String[]{"0","0"} : result.get(key).toString().split("#");
					double powers = Double.parseDouble(value[0]);
					int vols = Integer.parseInt(value[1]);
					int vol = list.get(i).vol;
					double this_powers = change * vol;
					result.put(key, (powers+this_powers) + "#"+(vols+vol));
					
				}else {
					double change = Double.parseDouble("0");
					
					int vol = list.get(i).vol;
					double this_powers = change * vol;
					String value[] = (result.get(change) == null) ? new String[]{"0","0"} : result.get(change).toString().split("#");
					double powers = Double.parseDouble(value[0]);
					int vols = Integer.parseInt(value[1]);
					result.put(change, (powers+this_powers) + "#"+(vols+vol));
					
				}
				

//				//
//				double curPrice = list.get(i).price;
//				compareException(prePrice,preType,curPrice,prefj);
//				prePrice = curPrice;
//				preType = list.get(i).type;
//				prefj = list.get(i);
//				alll += compareExceptionDouble(prePrice,preType,curPrice,prefj);
			} 
			//System.out.println("all"+alll);
			model.change_powers = result;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return model;
	}
	
	public static void compareException(double preMoney,String preType,double curMoney,fenjia fj){
		if(preMoney > curMoney  && preMoney > 0){
			if(preType.equals("卖盘") ||preType.equals("中性盘")) {
				//System.out.println(fj);
			}
		}
	}
	public static int compareExceptionDouble(double preMoney,String preType,double curMoney,fenjia fj){
		if(preMoney > curMoney  && preMoney > 0){
			if(preType.equals("卖盘") ||preType.equals("中性盘")) {
				//System.out.println(fj);
				return fj.vol+0;
			}else {
				return 0;
			}
		}else {
			return 0;
		}
	}
}
