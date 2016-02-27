package com.genModel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.finance.model.ChangePowersModel;
import com.finance.model.mao.ChangePowersMAO;
import com.finance.stock.model.fenjia;
import com.finance.util.DownloadUtil;
import com.finance.util.ExcelUtil;
import com.finance.util.FileList;
import com.finance.util.FinanceStockList;
import com.finance.util.MapUtil;
import com.finance.util.TxtUtil;
import com.pool.P6_10;

public class genPowerModel {
	public static String baseUrl = "http://market.finance.sina.com.cn/downxls.php?date=[#]&symbol=$$";
	
	public static String baseFilePath = "E:\\baidu\\finance_project\\model\\";
	
	/**
	 *  1.下载pool里的股票池.
	 *  2.结项下载后的文件
	 *  3.生成数据.
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date now = new Date();
			try {
//				Date from = df.parse(1900+now.getYear()+"-"+(now.getMonth()+1)+"-"+(now.getDate()-1));//"2015-10-12");
//				Date to =  df.parse(1900+now.getYear()+"-"+(now.getMonth()+1)+"-"+now.getDate());
				Date from = df.parse("2016-2-25");
				Date to = df.parse("2016-2-26");
				
//				new genPowerModel().genModelPowerExcel(baseUrl,from,to,filePath,2);
//				String[] pool = Agu.pool; {}
//				String[] pool = {"600332","600085","600000","600109","601377"};
//				String[] pool = {"600399"};	
				String[] pool = P6_10.strs;
				String filePath = "E:\\baidu\\finance_project\\model\\";
				// first download ....
				DownloadUtil downloader = new DownloadUtil();
//				for(int i = 0;i<pool.length;i++) {
//					downloader.downloadCJMX(baseUrl,pool[i],from,to,filePath);
//				}
				new genPowerModel().genModelOne(baseUrl,from,to,pool);
				String[] temp = new String[]{""};
//				for(int i = 0;i<pool.length;i++) {
//					temp[0] = pool[i];
//					Object[] obj = new genPowerModel().genModel2(baseUrl, from, to,temp);
//					
//						double before =MapUtil.toValue((Map)(((Object[])obj[0])[0]));
//						double after = MapUtil.toValue((Map)(((Object[])obj[1])[0]));;
//						for(int j = 0;j<obj.length;j++) {
//							Object[] ones = (Object[])obj[j];
//							//MapUtil.printMap((Map)ones[0]);
//						}
//						if (after > before && after > 0) {
//							System.out.println(pool[i]);
//							System.out.println(before);
//							System.out.println(after);
//						}
//				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public String genSingleBi(String file){
		List<fenjia> list;
		int big = 0;int bbig = 0;
		int small = 0;
		int bbbig_sell = 0;
		int bbbig_buy = 0;
		int s_sell = 0;
		int s_buy = 0;
		int b_sell = 0;
		int b_buy = 0;
		try {
			list = TxtUtil.getColData(1, file,"");
			for(int i = 0;i<list.size();i++){
				fenjia f = list.get(i);
				if (f.money <= 40000) {
					if (f.type.equals("买盘")){
						s_buy += f.vol;
					} else {
						s_sell +=f.vol;
					}
					small++;
				}
				if (f.money >= 400000) {
					if (f.type.equals("买盘")){
						bbbig_buy += f.vol;
					} else {
						bbbig_sell +=f.vol;
					}
					bbig++;
				}
				if (f.money > 40000 && f.money < 400000) {
					if (f.type.equals("买盘")){
						b_buy += f.vol;
					} else {
						b_sell +=f.vol;
					}
					big++;
				}
			}
//			System.out.println("特大单笔数:"+bbig);
//			System.out.println("大单笔数:"+big);
//			System.out.println("小单笔数:"+small);
//			System.out.println("特大单买入量:"+bbbig_buy);
//			System.out.println("特大单卖出量:"+bbbig_sell);
//			System.out.println("大单买入量:"+b_buy);
//			System.out.println("大单卖出量:"+b_sell);
//			System.out.println("小单买入量:"+s_buy);
//			System.out.println("小单卖出量:"+s_sell);
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bbig + "\t"+big+"\t"+small+"\t" + bbbig_buy+"\t"+bbbig_sell+"\t"+(bbbig_buy-bbbig_sell)
					+"\t"+b_buy+"\t"+b_sell+"\t"+(b_buy-b_sell)+"\t"+s_buy+"\t"+s_sell+"\t"+(s_buy-s_sell);
	}
	/**
	 *  单数的函数.
	 * @param basurl
	 * @param from
	 * @param to
	 * @param pool
	 * @return
	 */
	public void genBi(String filepath) {
		try {

			String[] files = FileList.fileList(filepath);
			for(int j = 0; j< files.length;j++){
				System.out.println(files[j]);
			List<fenjia> list = TxtUtil.getColData(1, files[j],"");
			int bbig = 0;
			int big = 0;
			int small = 0;
			int bbbig_sell = 0;
			int bbbig_buy = 0;
			int s_sell = 0;
			int s_buy = 0;
			int b_sell = 0;
			int b_buy = 0;
			for(int i = 0;i<list.size();i++){
				fenjia f = list.get(i);
				if (f.money <= 40000) {
					if (f.type.equals("买盘")){
						s_buy += f.vol;
					} else {
						s_sell +=f.vol;
					}
					small++;
				}
				if (f.money >= 400000) {
					if (f.type.equals("买盘")){
						bbbig_buy += f.vol;
					} else {
						bbbig_sell +=f.vol;
					}
					bbig++;
				}
				if (f.money > 40000 && f.money < 400000) {
					if (f.type.equals("买盘")){
						b_buy += f.vol;
					} else {
						b_sell +=f.vol;
					}
					big++;
				}
			}
//			System.out.println("特大单笔数:"+bbig);
//			System.out.println("大单笔数:"+big);
//			System.out.println("小单笔数:"+small);
//			System.out.println("特大单买入量:"+bbbig_buy);
//			System.out.println("特大单卖出量:"+bbbig_sell);
//			System.out.println("大单买入量:"+b_buy);
//			System.out.println("大单卖出量:"+b_sell);
//			System.out.println("小单买入量:"+s_buy);
//			System.out.println("小单卖出量:"+s_sell);
		}
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void bbigBi(List list){
		
	}
	public void bigBi(List list){
		
	}
	public void smallBi(List list) {
		
	}
	public Object[] genModel2(String basurl,Date from,Date to,String[] pool){
		String code = "";
		String filePath = "E:\\baidu\\finance_project\\model\\";
		DownloadUtil downloader = new DownloadUtil();
		Object[] obj =  new Object[2];
		for(int m = 0;m<pool.length;m++) {
			//code = JSONObject.fromObject(array.get(m)).get("symbol").toString();
			code = pool[m];
			downloader.downloadCJMX(basurl,code,from,to,filePath);
			String[] files = FileList.fileList(filePath);
			Map[] all = new Map[files.length];
			
			
			int oneIndex  = 0;
			for(int i = 0;i<files.length;i++) {
				Object[] ones = new Object[2];
				if (files[i].indexOf(code) >=0) {
					ChangePowersMAO mao = new ChangePowersMAO();
					ChangePowersModel model = mao.toModel(files[i],"2015-08-07");
					List list =mao.toCVMModel(files[i],"2015-08-07");
					//MapUtil.printMap(model.change_powers);  //console打印模型
					ones[0] = model.change_powers;
					//MapUtil.printList(list);
					ones[1] = list;
					obj[oneIndex++] = ones;
				}
			}
		}
//		System.out.println(ChartUtil.json);
		return obj;
	}
	

	public void genModelOne(String basurl,Date from,Date to,String[] pool) throws FileNotFoundException, IOException{
		String code = "";
//		String pool[] = My.pool;
//		String pool[] = new String[]{"002497"};
//		String pool[] = BankFinancePool.pool;
		String filePath = "E:\\baidu\\finance_project\\model\\";
		DownloadUtil downloader = new DownloadUtil();
		for(int m = 0;m<pool.length;m++) {
			//code = JSONObject.fromObject(array.get(m)).get("symbol").toString();
			code = pool[m];
//			downloader.downloadCJMX(basurl,code,from,to,filePath);
			String[] files = FileList.fileList(filePath);
			Map[] all = new Map[files.length];
			String preOutput = "";
			for(int i = 0;i<files.length;i++) {
				if (files[i].indexOf(code) >=0) {
					String bi = genSingleBi(files[i]);
					ChangePowersMAO mao = new ChangePowersMAO();
					ChangePowersModel model = mao.toModel(files[i],"2015-08-07");
					List list =mao.toCVMModel(files[i],"2015-08-07");
					//MapUtil.printMap(model.change_powers);  //console打印模型
					double power = MapUtil.toValue(model.change_powers);
					
					String list3str = MapUtil.list3(list);
					String output = power+"\t"+model.size+"\t"+bi+"\t"+list3str;
					preOutput = output;
//					System.out.println(output);
					if (power > 0) {
						ExcelUtil.writeExcel("E:\\Cloud\\finance\\output.txt", files[i]	, i, (i==files.length-1) ? true : false);
						
						ExcelUtil.writeExcel("E:\\Cloud\\finance\\output.txt", output, i, (i==files.length-1) ? true : false);
					}
				}
			}
		}
//		System.out.println(ChartUtil.json);
	}
	public void genModel(String basurl,Date from,Date to,String[] pool){
		String code = "";
//		String pool[] = My.pool;
//		String pool[] = new String[]{"002497"};
//		String pool[] = BankFinancePool.pool;
		String filePath = "E:\\baidu\\finance_project\\model\\";
		DownloadUtil downloader = new DownloadUtil();
		for(int m = 0;m<pool.length;m++) {
			//code = JSONObject.fromObject(array.get(m)).get("symbol").toString();
			code = pool[m];
//			downloader.downloadCJMX(basurl,code,from,to,filePath);
			String[] files = FileList.fileList(filePath);
			Map[] all = new Map[files.length];
			for(int i = 0;i<files.length;i++) {
				if (files[i].indexOf(code) >=0) {
					ChangePowersMAO mao = new ChangePowersMAO();
					ChangePowersModel model = mao.toModel(files[i],"2015-08-07");
					List list =mao.toCVMModel(files[i],"2015-08-07");
					MapUtil.printMap(model.change_powers);  //console打印模型
					MapUtil.printList(list);
				}
			}
		}
//		System.out.println(ChartUtil.json);
	}
	
	public void genModelLoop(String baseurl,Date from,Date to,int len ) {
		String code = "";
		JSONArray array  = new FinanceStockList().getHuA();
		DownloadUtil downloader = new DownloadUtil();
		String filepath = "";
		for(int m = 0;m<array.size();m++) {
			code = JSONObject.fromObject(array.get(m)).get("symbol").toString();
			code = code.replace("sh", "");
			downloader.downloadCJMX(baseurl,code,from,to,filepath);
			String[] files = FileList.fileList("F:\\baidu\\finance_project\\model\\cjmx$$\\");
			Map[] all = new Map[files.length];
			
			for(int i = 0;i<files.length;i++) {
				
				if (files[i].indexOf(code) >=0) {
					ChangePowersMAO mao = new ChangePowersMAO();
					ChangePowersModel model = mao.toModel(files[i],"2015-08-07");
					List list =mao.toCVMModel(files[i],"2015-08-07");
					MapUtil.printMap(model.change_powers);  //console打印模型
					MapUtil.printList(list);
				}
			}

			//生成excel,m列,一行一列.
			
		}
	}

	public void genModelPowerExcel(String baseurl,Date from,Date to,String excel,int rowsize){
//		try {
//			//生成excel,m列,一行一列. ,一列是code,m; 一行是change_powers的一条数据.
//			  POIFSFileSystem fs;
//			
//				fs = new POIFSFileSystem(new FileInputStream(new File(excel)));
//				//2.得到Excel工作簿对象  
//		        HSSFWorkbook wb = new HSSFWorkbook(fs);  
//		        //3.得到Excel工作表对象  
//		        HSSFSheet sheet = wb.getSheetAt(0);  
//		        //4.得到Excel工作表的行  、
//		        int trLength = sheet.getLastRowNum();  //0
//		        //先创建本次的行数.
//		        for(int i = 1;i<=rowsize;i++){
//		        	sheet.createRow(trLength+i);
//		        }
		        
		String code = "";
		JSONArray array  = new FinanceStockList().getHuA();
		DownloadUtil downloader = new DownloadUtil();
		String filepath = "";
		for(int m = 0;m<array.size();m++) {
			code = JSONObject.fromObject(array.get(m)).get("symbol").toString();
			code = code.replace("sh", "");
			downloader.downloadCJMX(baseurl,code,from,to,filepath);
			String[] files = FileList.fileList("F:\\baidu\\finance_project\\model\\cjmx$$\\");
			Map[] all = new Map[files.length];
			ChangePowersModel model = new ChangePowersModel();
			for(int i = 0;i<files.length;i++) {
				
				if (files[i].indexOf(code) >=0) {
					ChangePowersMAO mao = new ChangePowersMAO();
					model = mao.toModel(files[i],"2015-08-07");
					MapUtil.printMap(model.change_powers);  //console打印模型
				}
			}

			
//		        int modelSize = model.change_powers.size();
//		        Iterator iter = model.change_powers.keySet().iterator();
//		        int begins = trLength+1;
//		        while(iter.hasNext()){
//			        	//本次开始的行数.
//			        	 HSSFRow rows = sheet.getRow(begins); 
//			        	 //根据code得到列.
//			        	 int cellIndexs = Integer.parseInt(code.substring(1,code.length()));
//			        	 System.out.println("cellindex"+cellIndexs+"-"+rows);
//			        	 if (rows != null) {
//				        	 HSSFCell cells = rows.createCell(cellIndexs);  
//				        	 cells.setCellValue(model.change_powers.get(iter.next().toString())+"");
//				        	 begins = begins +1;
//			        	 }
//		        }
		}    
			
		        
		}
	}

