package com.ecom.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtils {
	public static FileInputStream fis;
	public static FileOutputStream fos;
	public static XSSFWorkbook workbook;
	public static XSSFSheet worksheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	
	public static int getRowCount(String xlfile, String sheet) throws IOException {
		fis = new FileInputStream(xlfile);
		workbook = new XSSFWorkbook(fis);
		worksheet  = workbook.getSheet(sheet);
		int rownum =worksheet.getLastRowNum();
		workbook.close();
		fis.close();
		return rownum;
	}
	
	public static int getCellCount(String xlfile, String sheet, int rownum) throws IOException {
		fis = new FileInputStream(xlfile);
		workbook = new XSSFWorkbook(fis);
		worksheet  = workbook.getSheet(sheet);
		row = worksheet.getRow(rownum);
		int cellCount = row.getLastCellNum();
		workbook.close();
		fis.close();
		return cellCount;
	}
	
	public static String getCellData(String xlfile, String sheet, int rownum, int cellnum) throws IOException {
		fis = new FileInputStream(xlfile);
		workbook = new XSSFWorkbook(fis);
		worksheet  = workbook.getSheet(sheet);
		row = worksheet.getRow(rownum);
		cell = row.getCell(cellnum);
		String data="";
		
		try {
			DataFormatter formatter = new DataFormatter();
			String cellData = formatter.formatCellValue(cell);
			return cellData;
			
		}catch(Exception e) {}
		return data;
	}
	
	public static void setCellData(String xlfile, String sheet, int rownum, int cellnum, String data) throws IOException {
		fis = new FileInputStream(xlfile);
		workbook = new XSSFWorkbook(fis);
		worksheet = workbook.getSheet(sheet);
		row = worksheet.getRow(rownum);
		cell = row.createCell(cellnum);
		cell.setCellValue(data);
		
		fos = new FileOutputStream(xlfile);
		workbook.write(fos);
		fos.flush();
		fos.close();
		workbook.close();
		fis.close();
	}
}
