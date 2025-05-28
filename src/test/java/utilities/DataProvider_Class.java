package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProvider_Class extends Excel_CommonUtility{

	@DataProvider(name = "testInputData")
	public String[][] testInputData() throws IOException {
		
		String filePath = System.getProperty("user.dir") + "\\testData\\LoginTestDataInput.xlsx";
		String sheetName = "loginTestInput";
		
		return super.getData(filePath, sheetName);
	}
}
