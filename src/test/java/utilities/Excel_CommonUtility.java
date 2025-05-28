package utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel_CommonUtility {
	
	public FileInputStream fi;
	public XSSFWorkbook wb;
	public XSSFSheet ws;
	public XSSFRow wr;
	public XSSFCell wc;

	public String[][] getData(String filePath, String sheetName) throws IOException {
		
		fi = new FileInputStream(filePath);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheetName);
		int lastRow = ws.getLastRowNum();
		int lastCell = ws.getRow(lastRow).getLastCellNum();
		
		String[][] data = new String[lastRow][lastCell];
		
		for(int i = 1; i <= lastRow; i++) {
			wr = ws.getRow(i);
			for(int j = 0; j < lastCell; j++) {
				wc = (wr != null) ? wr.getCell(j):null;
				data[i-1][j] = (wc != null)? wc.toString(): "";
			}
		}
		return data;
	}
}
