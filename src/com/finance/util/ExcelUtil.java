package com.finance.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;

import com.finance.stock.model.fenjia;

public class ExcelUtil {
	public static Double[] getColDoubleDate(int rows,int col) throws FileNotFoundException, IOException {
		 
		 POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("F:\\baidu\\finance_project\\src\\sh600399_成交明细_2015-08-05.xls"));  
	        //2.得到Excel工作簿对象  
	        HSSFWorkbook wb = new HSSFWorkbook(fs);  
	        //3.得到Excel工作表对象  
	        HSSFSheet sheet = wb.getSheetAt(0);  
	        //总行数  
	        int trLength = sheet.getLastRowNum();  
	        Double d[] = new Double[trLength];
	        //4.得到Excel工作表的行  
	        HSSFRow row = sheet.getRow(0);  
	        //总列数  
	        int tdLength = row.getLastCellNum();  
	        //5.得到Excel工作表指定行的单元格  
	        HSSFCell cell = row.getCell((short)1);  
	        //6.得到单元格样式  
	        CellStyle cellStyle = cell.getCellStyle(); 
	        int index = 0;

	        System.out.println(trLength);
	        for(int i=rows;i<=trLength;i++){  
	        	fenjia fj = new fenjia();
	            //得到Excel工作表的行  
	            HSSFRow row1 = sheet.getRow(i);  
	            //得到Excel工作表指定行的单元格  
	            HSSFCell cell0 = row1.getCell(0); 
	            HSSFCell cell1 = row1.getCell(1);  
	            HSSFCell cell2 = row1.getCell(2);  
	            HSSFCell cell3 = row1.getCell(3);  
	            HSSFCell cell4 = row1.getCell(4);  
	            HSSFCell cell5 = row1.getCell(5);  
	            /** 
	             * 为了处理：Excel异常Cannot get a text value from a numeric cell 
	             * 将所有列中的内容都设置成String类型格式 
	             */  
	            if(cell1!=null){  
	                  cell1.setCellType(Cell.CELL_TYPE_STRING);  
	             }  
	            Double dd = Double.parseDouble(cell1.getStringCellValue());

		           //System.out.print(dd+" ");
	          if (!dd.equals(""))
	        	  d[index++] = dd;
	        }  
		return d;
	}
	public static LinkedList<fenjia> getColData(int rows,int col) throws FileNotFoundException, IOException, ParseException{
		LinkedList list = new LinkedList<BigDecimal>();
		//1.得到Excel常用对象  
//      POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("d:/FTP/test.xls"));  
        POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("F:\\baidu\\finance_project\\src\\sh600399_成交明细_2015-08-05.xls"));  
        //2.得到Excel工作簿对象  
        HSSFWorkbook wb = new HSSFWorkbook(fs);  
        //3.得到Excel工作表对象  
        HSSFSheet sheet = wb.getSheetAt(0);  
        //总行数  
        int trLength = sheet.getLastRowNum();  
        //4.得到Excel工作表的行  
        HSSFRow row = sheet.getRow(0);  
        //总列数  
        int tdLength = row.getLastCellNum();  
        //5.得到Excel工作表指定行的单元格  
        HSSFCell cell = row.getCell((short)1);  
        //6.得到单元格样式  
        CellStyle cellStyle = cell.getCellStyle(); 
        //System.out.println(trLength);
        for(int i=rows;i<=trLength;i++){  
        	fenjia fj = new fenjia();
            //得到Excel工作表的行  
            HSSFRow row1 = sheet.getRow(i);  
            //得到Excel工作表指定行的单元格  
            HSSFCell cell0 = row1.getCell(0); 
            HSSFCell cell1 = row1.getCell(1);  
            HSSFCell cell2 = row1.getCell(2);  
            HSSFCell cell3 = row1.getCell(3);  
            HSSFCell cell4 = row1.getCell(4);  
            HSSFCell cell5 = row1.getCell(5);  
            /** 
             * 为了处理：Excel异常Cannot get a text value from a numeric cell 
             * 将所有列中的内容都设置成String类型格式 
             */  
            if(cell1!=null){  
                  cell1.setCellType(Cell.CELL_TYPE_STRING);  
             }  
            if(cell2!=null){  
        	   cell2.setCellType(Cell.CELL_TYPE_STRING);  
          }   if(cell3!=null){  
        	  cell3.setCellType(Cell.CELL_TYPE_STRING);  
         }   if(cell4!=null){  
        	 cell4.setCellType(Cell.CELL_TYPE_STRING);  
        }   if(cell5!=null){  
        	cell5.setCellType(Cell.CELL_TYPE_STRING);  
       }  
        SimpleDateFormat bartDateFormat = 
        		new SimpleDateFormat("HH24:mm:dd"); 
       // System.out.println(cell0.getDateCellValue());
        fj.setJiaoyi_date(cell0.getDateCellValue());
        fj.setPrice(Double.parseDouble(cell1.getStringCellValue()));
        fj.setChange(cell2.getStringCellValue());
        fj.setVol(Integer.parseInt(cell3.getStringCellValue()));
        fj.setMoney(Double.parseDouble(cell4.getStringCellValue()));
        fj.setType(cell5.getStringCellValue());
            //获得每一列中的值  
            //System.out.print(cell1.getStringCellValue()+"\t\t\t");  
            list.add(fj);
        }  
		
		return list;
		
	}
	
	public static LinkedList<fenjia> getColData(int rows,String path) throws FileNotFoundException, IOException, ParseException{
		LinkedList list = new LinkedList<BigDecimal>();
		//1.得到Excel常用对象  
//      POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("d:/FTP/test.xls"));  
        POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(new File(path)));  
        //2.得到Excel工作簿对象  
        HSSFWorkbook wb = new HSSFWorkbook(fs);  
        //3.得到Excel工作表对象  
        HSSFSheet sheet = wb.getSheetAt(0);  
        //总行数  
        int trLength = sheet.getLastRowNum();  
        //4.得到Excel工作表的行  
        HSSFRow row = sheet.getRow(0);  
        //总列数  
        int tdLength = row.getLastCellNum();  
        //5.得到Excel工作表指定行的单元格  
        HSSFCell cell = row.getCell((short)1);  
        //6.得到单元格样式  
        CellStyle cellStyle = cell.getCellStyle(); 
        //System.out.println(trLength);
        for(int i=rows;i<=trLength;i++){  
        	fenjia fj = new fenjia();
            //得到Excel工作表的行  
            HSSFRow row1 = sheet.getRow(i);  
            //得到Excel工作表指定行的单元格  
            HSSFCell cell0 = row1.getCell(0); 
            HSSFCell cell1 = row1.getCell(1);  
            HSSFCell cell2 = row1.getCell(2);  
            HSSFCell cell3 = row1.getCell(3);  
            HSSFCell cell4 = row1.getCell(4);  
            HSSFCell cell5 = row1.getCell(5);  
            /** 
             * 为了处理：Excel异常Cannot get a text value from a numeric cell 
             * 将所有列中的内容都设置成String类型格式 
             */  
            if(cell1!=null){  
                  cell1.setCellType(Cell.CELL_TYPE_STRING);  
             }  
            if(cell2!=null){  
        	   cell2.setCellType(Cell.CELL_TYPE_STRING);  
          }   if(cell3!=null){  
        	  cell3.setCellType(Cell.CELL_TYPE_STRING);  
         }   if(cell4!=null){  
        	 cell4.setCellType(Cell.CELL_TYPE_STRING);  
        }   if(cell5!=null){  
        	cell5.setCellType(Cell.CELL_TYPE_STRING);  
       }  
        SimpleDateFormat bartDateFormat = 
        		new SimpleDateFormat("HH24:mm:dd"); 
       // System.out.println(cell0.getDateCellValue());
        fj.setJiaoyi_date(cell0.getDateCellValue());
        fj.setPrice(Double.parseDouble(cell1.getStringCellValue()));
        fj.setChange(cell2.getStringCellValue());
        fj.setVol(Integer.parseInt(cell3.getStringCellValue()));
        fj.setMoney(Double.parseDouble(cell4.getStringCellValue()));
        fj.setType(cell5.getStringCellValue());
            //获得每一列中的值  
            //System.out.print(cell1.getStringCellValue()+"\t\t\t");  
            list.add(fj);
        }  
		
		return list;
		
	}
	
	public static void writeExcel(String path,String str,int num,boolean end ) throws FileNotFoundException, IOException{
		// 打开一个随机访问文件流，按读写方式
		RandomAccessFile randomFile = new RandomAccessFile(path, "rw");
		// 文件长度，字节数
		long fileLength = randomFile.length();
		// 将写文件指针移到文件尾。
		randomFile.seek(fileLength);
		randomFile.writeBytes(str+"\r\n");
		randomFile.close();
	}
	public static void genExcel(Map[] data,String path) {
		try {	
		// 创建Excel的工作书册 Workbook,对应到一个excel文档  
	    HSSFWorkbook wb = new HSSFWorkbook();  
	  
	    // 创建Excel的工作sheet,对应到一个excel文档的tab  
	    HSSFSheet sheet = wb.createSheet("sheet1");  
	  
	    
	    int length = data.length;
	    int cellindex = 0;
	    int cellyz = 0;
    	int dayRowIndex = 0;
        int tempRow = 0;
        
        /** **/
        for (int i = 0;i<=99;i++){
        	sheet.createRow(i);
        }
	    for(int i = 0;i<data.length;i++){
	    	Map day = data[i];
	    	Iterator iter = day.keySet().iterator();
			// 创建Excel的sheet的一行  
	    	int lastRow = sheet.getLastRowNum();
	    	System.out.println("lastRow"+"    "+lastRow);
			while(iter.hasNext()) {
				Object key = iter.next();
				String value = day.get(key).toString();
				//System.out.println(""+key+"	"+value.split("#")[1]+"	"+value.split("#")[0]+"	");
				
					HSSFRow row = sheet.getRow(dayRowIndex);
				    // 创建一个Excel的单元格  
				    HSSFCell cell = row.createCell(cellindex);  
				    HSSFCell cell1 = row.createCell(cellindex+1); 
				    HSSFCell cell2 = row.createCell(cellindex+2); 
				   // System.out.println(dayRowIndex+"行"+cellindex+"列"+(cellindex+1)+"列"+(cellindex+2)+"列");
				    
				    cell.setCellValue(key.toString());  
				    //System.out.println(value);
				    cell1.setCellValue(value.split("#")[0]);
				    cell2.setCellValue(value.split("#")[1]);
				    dayRowIndex++;
				
			}
			dayRowIndex = 0;
			cellindex = (cellyz+1) * 3;
			cellyz++;
	    }

	    FileOutputStream os;
		
			os = new FileOutputStream(path);

		    wb.write(os);  
		    os.close();  
		    
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	public static void main(String[] args) {
		try {
			//List list = getColData(1,1);
			//System.out.println("平均数:"+ArthUtil.meanList(list));
			//.out.println("中间数:"+ArthUtil.middleList(list));
			writeExcel("E:\\Cloud\\finance\\a.txt","asdf",0,true);

//			Double ds[] = getColDoubleDate(1,1);
//
//			//ArrayUtil.printArray(ds);
//			ArrayUtil.selectSort(ds);
			
			//ArrayUtil.printArray(ds);
//			System.out.println("a");
//			System.out.println("中间数:"+ds[ds.length%2==0 ? ds.length/2 : (ds.length+1)/2]);
//			System.out.println("最大数:"+ds[ds.length-1]);
//			System.out.println("最小数:"+ds[0]);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	  
}
