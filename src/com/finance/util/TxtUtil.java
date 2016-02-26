package com.finance.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

import com.finance.stock.model.fenjia;

public class TxtUtil {
	public static LinkedList<fenjia> getColData(int rows,String path,String date) throws FileNotFoundException, IOException, ParseException{
		
		//1.�õ�Excel���ö���  
//      POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("d:/FTP/test.xls"));  
		LinkedList list = new LinkedList();
		File file = new File(path);
        BufferedReader reader = null;
        try {
            //System.out.println("����Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�һ���У�");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // һ�ζ���һ�У�ֱ������nullΪ�ļ�����
            int rowindex = 0;
            SimpleDateFormat df =new SimpleDateFormat("HH:mm:ss");
            while ((tempString = reader.readLine()) != null) {
                if (tempString.contains("<script")) {
                	break;
                }
                if (rowindex > 0) {
                	// ��ʾ�к�
                    String[] row  = tempString.split("	");
                   // System.out.println("row"+tempString);
                    String time = row[0];
                    String price = row[1];
                    String  change = row[2];
                    //System.out.println(row[3] == null);
                    String vol = row.length==3 ? "0":row[3];
                    fenjia fj = new fenjia();
                    fj.change = row[2].equals("--") ? "10000":row[2];
                    fj.jiaoyi_date = df.parse(time);
                    price = price.equals("") ? "0" : price;
                    fj.price = Double.parseDouble(price);
                    fj.vol = Integer.parseInt(vol);
                    fj.type = row[5];
                    String money = row.length==4 ? "0": row[4];
                    fj.money = Double.parseDouble(money);
                    list.add(fj);
                    
                }
                rowindex++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
		return list;
		
	}
}
