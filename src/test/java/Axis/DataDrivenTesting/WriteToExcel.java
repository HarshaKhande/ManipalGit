package Axis.DataDrivenTesting;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class WriteToExcel {

	WebDriver driver;
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	XSSFCell cell;

	@SuppressWarnings("deprecation")
	@Test
	public void fblogin() throws IOException {

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Harsha Patil\\Documents\\Manipal\\chromedriver-win64 (2)\\chromedriver-win64\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		driver.get("http://www.facebook.com/");

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.MILLISECONDS);

		// Import excel sheet

		File src = new File("C:\\Users\\Harsha Patil\\eclipse-workspace-manipal\\DataDrivenTesting\\TestData.xlsx");

		// load the file

		FileInputStream fis = new FileInputStream(src);

		// load the work book

		workbook = new XSSFWorkbook(fis);

		// access the sheet from the work book

		sheet = workbook.getSheetAt(0);

		for (int i = 1; i <= sheet.getLastRowNum(); i++) {

			// import the data from email

			cell = sheet.getRow(i).getCell(0);

			driver.findElement(By.xpath("//input[@name = 'email']")).clear();

			driver.findElement(By.xpath("//input[@name = 'email']")).sendKeys(cell.getStringCellValue());

			// import the data for the password

			cell = sheet.getRow(i).getCell(1);

			driver.findElement(By.xpath("//input[@id = 'pass']")).clear();

			driver.findElement(By.xpath("//input[@id = 'pass']")).sendKeys(cell.getStringCellValue());

			String title = driver.getTitle();

			System.out.println(title);

			// to write the data to excel

			FileOutputStream fos = new FileOutputStream(src);

			// create the cell where the data needs to be written

			sheet.getRow(i).createCell(2).setCellValue(title);

			// perform the write operation

			workbook.write(fos);

			fos.close();

		}

	}

}
