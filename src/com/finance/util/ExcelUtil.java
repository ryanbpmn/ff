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
		 
		 POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("F:\\baidu\\finance_project\\src\\sh600399_�ɽ���ϸ_2015-08-05.xls"));  
	        //2.�õ�Excel����������  
	        HSSFWorkbook wb = new HSSFWorkbook(fs);  
	        //3.�õ�Excel���������  
	        HSSFSheet sheet = wb.getSheetAt(0);  
	        //������  
	        int trLength = sheet.getLastRowNum();  
	        Double d[] = new Double[trLength];
	        //4.�õ�Excel���������  
	        HSSFRow row = sheet.getRow(0);  
	        //������  
	        int tdLength = row.getLastCellNum();  
	        //5.�õ�Excel������ָ���еĵ�Ԫ��  
	        HSSFCell cell = row.getCell((short)1);  
	        //6.�õ���Ԫ����ʽ  
	        CellStyle cellStyle = cell.getCellStyle(); 
	        int index = 0;

	        System.out.println(trLength);
	        for(int i=rows;i<=trLength;i++){  
	        	fenjia fj = new fenjia();
	            //�õ�Excel���������  
	            HSSFRow row1 = sheet.getRow(i);  
	            //�õ�Excel������ָ���еĵ�Ԫ��  
	            HSSFCell cell0 = row1.getCell(0); 
	            HSSFCell cell1 = row1.getCell(1);  
	            HSSFCell cell2 = row1.getCell(2);  
	            HSSFCell cell3 = row1.getCell(3);  
	            HSSFCell cell4 = row1.getCell(4);  
	            HSSFCell cell5 = row1.getCell(5);  
	            /** 
	             * Ϊ�˴���Excel�쳣Cannot get a text value from a numeric cell 
	             * ���������е����ݶ����ó�String���͸�ʽ 
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
		//1.�õ�Excel���ö���  
//      POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("d:/FTP/test.xls"));  
        POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("F:\\baidu\\finance_project\\src\\sh600399_�ɽ���ϸ_2015-08-05.xls"));  
        //2.�õ�Excel����������  
        HSSFWorkbook wb = new HSSFWorkbook(fs);  
        //3.�õ�Excel���������  
        HSSFSheet sheet = wb.getSheetAt(0);  
        //������  
        int trLength = sheet.getLastRowNum();  
        //4.�õ�Excel���������  
        HSSFRow row = sheet.getRow(0);  
        //������  
        int tdLength = row.getLastCellNum();  
        //5.�õ�Excel������ָ���еĵ�Ԫ��  
        HSSFCell cell = row.getCell((short)1);  
        //6.�õ���Ԫ����ʽ  
        CellStyle cellStyle = cell.getCellStyle(); 
        //System.out.println(trLength);
        for(int i=rows;i<=trLength;i++){  
        	fenjia fj = new fenjia();
            //�õ�Excel���������  
            HSSFRow row1 = sheet.getRow(i);  
            //�õ�Excel������ָ���еĵ�Ԫ��  
            HSSFCell cell0 = row1.getCell(0); 
            HSSFCell cell1 = row1.getCell(1);  
            HSSFCell cell2 = row1.getCell(2);  
            HSSFCell cell3 = row1.getCell(3);  
            HSSFCell cell4 = row1.getCell(4);  
            HSSFCell cell5 = row1.getCell(5);  
            /** 
             * Ϊ�˴���Excel�쳣Cannot get a text value from a numeric cell 
             * ���������е����ݶ����ó�String���͸�ʽ 
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
            //���ÿһ���е�ֵ  
            //System.out.print(cell1.getStringCellValue()+"\t\t\t");  
            list.add(fj);
        }  
		
		return list;
		
	}
	
	public static LinkedList<fenjia> getColData(int rows,String path) throws FileNotFoundException, IOException, ParseException{
		LinkedList list = new LinkedList<BigDecimal>();
		//1.�õ�Excel���ö���  
//      POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("d:/FTP/test.xls"));  
        POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(new File(path)));  
        //2.�õ�Excel����������  
        HSSFWorkbook wb = new HSSFWorkbook(fs);  
        //3.�õ�Excel���������  
        HSSFSheet sheet = wb.getSheetAt(0);  
        //������  
        int trLength = sheet.getLastRowNum();  
        //4.�õ�Excel���������  
        HSSFRow row = sheet.getRow(0);  
        //������  
        int tdLength = row.getLastCellNum();  
        //5.�õ�Excel������ָ���еĵ�Ԫ��  
        HSSFCell cell = row.getCell((short)1);  
        //6.�õ���Ԫ����ʽ  
        CellStyle cellStyle = cell.getCellStyle(); 
        //System.out.println(trLength);
        for(int i=rows;i<=trLength;i++){  
        	fenjia fj = new fenjia();
            //�õ�Excel���������  
            HSSFRow row1 = sheet.getRow(i);  
            //�õ�Excel������ָ���еĵ�Ԫ��  
            HSSFCell cell0 = row1.getCell(0); 
            HSSFCell cell1 = row1.getCell(1);  
            HSSFCell cell2 = row1.getCell(2);  
            HSSFCell cell3 = row1.getCell(3);  
            HSSFCell cell4 = row1.getCell(4);  
            HSSFCell cell5 = row1.getCell(5);  
            /** 
             * Ϊ�˴���Excel�쳣Cannot get a text value from a numeric cell 
             * ���������е����ݶ����ó�String���͸�ʽ 
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
            //���ÿһ���е�ֵ  
            //System.out.print(cell1.getStringCellValue()+"\t\t\t");  
            list.add(fj);
        }  
		
		return list;
		
	}
	
	public static void writeExcel(String path,String str,int num,boolean end ) throws FileNotFoundException, IOException{
		// ��һ����������ļ���������д��ʽ
		RandomAccessFile randomFile = new RandomAccessFile(path, "rw");
		// �ļ����ȣ��ֽ���
		long fileLength = randomFile.length();
		// ��д�ļ�ָ���Ƶ��ļ�β��
		randomFile.seek(fileLength);
		randomFile.writeBytes(str+"\r\n");
		randomFile.close();
	}
	public static void genExcel(Map[] data,String path) {
		try {	
		// ����Excel�Ĺ������ Workbook,��Ӧ��һ��excel�ĵ�  
	    HSSFWorkbook wb = new HSSFWorkbook();  
	  
	    // ����Excel�Ĺ���sheet,��Ӧ��һ��excel�ĵ���tab  
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
			// ����Excel��sheet��һ��  
	    	int lastRow = sheet.getLastRowNum();
	    	System.out.println("lastRow"+"    "+lastRow);
			while(iter.hasNext()) {
				Object key = iter.next();
				String value = day.get(key).toString();
				//System.out.println(""+key+"	"+value.split("#")[1]+"	"+value.split("#")[0]+"	");
				
					HSSFRow row = sheet.getRow(dayRowIndex);
				    // ����һ��Excel�ĵ�Ԫ��  
				    HSSFCell cell = row.createCell(cellindex);  
				    HSSFCell cell1 = row.createCell(cellindex+1); 
				    HSSFCell cell2 = row.createCell(cellindex+2); 
				   // System.out.println(dayRowIndex+"��"+cellindex+"��"+(cellindex+1)+"��"+(cellindex+2)+"��");
				    
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
			//System.out.println("ƽ����:"+ArthUtil.meanList(list));
			//.out.println("�м���:"+ArthUtil.middleList(list));
			writeExcel("E:\\Cloud\\finance\\a.txt","asdf",0,true);

//			Double ds[] = getColDoubleDate(1,1);
//
//			//ArrayUtil.printArray(ds);
//			ArrayUtil.selectSort(ds);
			
			//ArrayUtil.printArray(ds);
//			System.out.println("a");
//			System.out.println("�м���:"+ds[ds.length%2==0 ? ds.length/2 : (ds.length+1)/2]);
//			System.out.println("�����:"+ds[ds.length-1]);
//			System.out.println("��С��:"+ds[0]);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	  
}
