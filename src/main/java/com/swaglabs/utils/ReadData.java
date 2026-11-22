package com.swaglabs.utils;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadData {
	XSSFSheet sheet;
	public ReadData(String filename)
	{
		String filepath="src//test//resources//testdata//"+filename+".xlsx";
		try {
			FileInputStream instream = new FileInputStream(filepath);
			
			XSSFWorkbook workbook = new XSSFWorkbook(instream);
			sheet=workbook.getSheet("Sheet1");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	
	
	public String getData(int row,int col)
	{
		return sheet.getRow(row).getCell(col).getStringCellValue();
	}
	
	public List<String> getData(int row)
	{
		List<String> rowdata=new ArrayList<String>();
		int cellscount= sheet.getRow(row).getLastCellNum(); //returns to count i.e 2
		
		for(int i=0;i<cellscount;i++)
		{
		 String data=sheet.getRow(row).getCell(i).getStringCellValue();
		 rowdata.add(data);
		}
		
		return rowdata;
	}
	
	public String [][] getData()
	{
		int lastrowNo=sheet.getLastRowNum();
		int colsCount=sheet.getRow(0).getLastCellNum();
		
		String tabledata[][]=new String [lastrowNo][colsCount];
		int k=0,l;
		for(int i=1;i<=lastrowNo;i++)
		{
			l=0;
			for(int j=0;j<colsCount;j++)
			{
				 String data=sheet.getRow(i).getCell(j).getStringCellValue();
				 tabledata[k][l]=data;
				 l++;
			}
			k++;
			
		}
		return tabledata;
		
	}
	
	
	public static void main(String[] args) {
		ReadData read= new ReadData("loginpage");
		String data=read.getData(10, 0);
		System.out.println("Cell data: "+ data);
		
		List<String> rowdata=read.getData(1);
		System.out.println("Row Data :\n "+rowdata);
		
		
		System.out.println("Table Data:");
		
		String tabledata[][]=read.getData();
		
		for (int i = 0; i < tabledata.length; i++) {
			for (int j = 0; j < tabledata[i].length; j++) {
				System.out.print(tabledata[i][j]+" ");
			}
			System.out.println();
		}
	}

}
