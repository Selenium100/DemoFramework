package com.qa.amazon.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.qa.amazon.base.BaseTest;

public class TestUtil extends BaseTest {
	
	public static long PAGE_LOAD_TIMEOUT = 30;
	public static long IMPLICIT_WAIT = 30;
	public static String TESTDATA_SHEET_PATH = System.getProperty("user.dir")+"/src/main/java/com/qa/amazon/testdata";
	
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	String[][] getData;
	
	public String getCellData(String sheetName, int rownum,int colnum) {
		try {
			workbook = new XSSFWorkbook(new FileInputStream(TESTDATA_SHEET_PATH));
			sheet = workbook.getSheet(sheetName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sheet.getRow(rownum).getCell(colnum).getStringCellValue();
		
		
	}
	
	public Object[][] getArrayTable(String sheetName){
		try {
			workbook = new XSSFWorkbook(new FileInputStream(TESTDATA_SHEET_PATH));
			sheet = workbook.getSheet(sheetName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int rowCount = sheet.getLastRowNum();
		int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
		
		getData = new String[rowCount][colCount];
		
		int ci=0;
		for(int i=0;i<=rowCount;i++,ci++) {
			int cj=0;
			for(int j=0;j<colCount;j++,cj++) {
				
				getData[ci][cj] = getCellData(sheetName,i,j);
			}
		}
		return getData;
		
	}
	
	public void takeScreenshotAtEndOfTest() {
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		try {
			FileUtils.copyFile(src, new File(currentDir+"/screenshots"+System.currentTimeMillis()+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
