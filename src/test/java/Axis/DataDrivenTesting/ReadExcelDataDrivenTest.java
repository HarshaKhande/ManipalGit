package Axis.DataDrivenTesting;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ReadExcelDataDrivenTest {

	
	WebDriver driver;
    XSSFWorkbook workbook;
    XSSFSheet sheet;
    XSSFCell cell;
	 
  
    
	@Test
	public void fbLoginLogout() throws IOException{
		

	    // To set the path of the Chrome driver.
    	System.setProperty("webdriver.chrome.driver", 
    			"C:\\Users\\Harsha Patil\\Documents\\Manipal\\chromedriver-win64 (2)\\chromedriver-win64\\chromedriver.exe");
    			
    			WebDriver driver = new ChromeDriver();
    			
	     
	    // To launch facebook
	    driver.get("http://www.facebook.com/");
	    // To maximize the browser
	    driver.manage().window().maximize();
	    // implicit wait
	    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		// Import excel sheet.
		File src=new File("C:\\Users\\Harsha Patil\\eclipse-workspace-manipal\\DataDrivenTesting\\TestData.xlsx");		  
		// Load the file.
		FileInputStream fis = new FileInputStream(src);
		// Load he workbook.
		workbook = new XSSFWorkbook(fis);
		// Load the sheet in which data is stored.
		sheet= workbook.getSheetAt(0);
			for(int i=1; i<=sheet.getLastRowNum(); i++){
				/*I have added test data in the cell A2 as "testemailone@test.com" and B2 as "password"
				Cell A2 = row 1 and column 0. It reads first row as 0, second row as 1 and so on 
				and first column (A) as 0 and second column (B) as 1 and so on*/ 
				 
				// Import data for Email.
				cell = sheet.getRow(i).getCell(0);
				//cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//input[@name='email']")).clear();
				driver.findElement(By.xpath("//input[@name='email']")).sendKeys(cell.getStringCellValue());
				 
				// Import data for password.
				cell = sheet.getRow(i).getCell(1);
				//cell.setCellType(Cell.CELL_TYPE_STRING);
				driver.findElement(By.xpath("//input[@type='password'][@name='pass']")).clear();	         
				driver.findElement(By.xpath("//input[@type='password'][@name='pass']")).sendKeys(cell.getStringCellValue());
				driver.close();
			}
	}
	 
}

