package come.ferns.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	public String path;
	public FileInputStream fis = null;
	public XSSFWorkbook workbook = null;
	public XSSFSheet sheet = null;
	public XSSFRow row = null;
	public XSSFCell cell = null;
	
	public ExcelReader(String path) {
		this.path = path;
		
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			fis.close();		
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void setSheet(String sheetName) {
		sheet = workbook.getSheet(sheetName);
	}
	
	public int getRowsCount() {
		return sheet.getPhysicalNumberOfRows();
	}
	
	public int getColumnsCount () {
		return sheet.getRow(0).getPhysicalNumberOfCells();
	}

	public String getCellData(int rownum, int cellnum ) {
		DataFormatter formatter = new DataFormatter();				
		return formatter.formatCellValue(sheet.getRow(rownum).getCell(cellnum));
	}
	
	
}
