package TestNG;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileInputStream;
//import java.io.IOException;

//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.XSSFCell;
//import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel_Read {
	static XSSFWorkbook workbook ;
	XSSFSheet sheet;
	public Excel_Read(String Path){
		try{
			File file=new File(Path);
			FileInputStream fs = new FileInputStream(file);
			workbook=new XSSFWorkbook(fs);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			page_Object_signUp.noEceptionOccured=false;
		}
	}
	
	public  int getColNum(int sheet_Num){
		int Col_Num=0;
		try{
		 Col_Num= 12;
		}catch(Exception e){
			page_Object_signUp.noEceptionOccured=false;
		}
		return Col_Num;
	}
	public  int getRowNum(int sheet_Num){
		int row_Num=0;
		try{
		 row_Num= workbook.getSheetAt(sheet_Num).getLastRowNum()+1;
		}catch(Exception e){
			page_Object_signUp.noEceptionOccured=false;
		}
		return row_Num;
	}
	public String fetchData(int sheet_Num,int row,int column){
sheet =workbook.getSheetAt(sheet_Num);
String data=null;
try{
		 data=sheet.getRow(row).getCell(column).getStringCellValue();
		} catch(Exception e){
		//	System.out.println(e.getMessage());
		}
data = (data==null) ? "" : data;
		return data;
	}
	

}






























