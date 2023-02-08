package login;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DataDriven_Poi {
	
	static List<String> userNames = new ArrayList<String>();
	static List<String> passWords = new ArrayList<String>();

	public void redExcel() throws IOException {
		FileInputStream fis = new FileInputStream("F:\\framework\\DataDrivenfram\\excel\\loginCredentialss.xlsx");
		
		Workbook workBook = new XSSFWorkbook(fis);
		Sheet sheet = workBook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {
			Row rowValue = (Row) rowIterator.next();
			
			Iterator<Cell> columnIterator = rowValue.iterator();
			int i = 2;
			while (columnIterator.hasNext()) {
				if (i%2==0) {
					userNames.add(columnIterator.next().getStringCellValue());
					//Cell cellValue = (Cell) columnIterator.next();
				}
				else {
					passWords.add(columnIterator.next().getStringCellValue());
					//Cell cellValue = (Cell) columnIterator.next();
				}
				i++;
				
			}
		}

	}
	
	public void loginValidationExcel(String uname, String pswd) {

		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://adactinhotelapp.com/HotelAppBuild2/");
		driver.manage().window().maximize();

		driver.findElement(By.id("username")).sendKeys(uname);
		driver.findElement(By.id("password")).sendKeys(pswd);
		WebElement submit = driver.findElement(By.id("login"));
		submit.click();
		
		

		
	}
	
	public void executeTest() {
		for (int i = 0; i <userNames.size() ; i++) {
			loginValidationExcel(userNames.get(i),passWords.get(i));
		}

	}

	
	public static void main(String[] args) throws IOException {
		DataDriven_Poi dataDrivenPoi = new DataDriven_Poi();
		dataDrivenPoi.redExcel();
		System.out.println(userNames);
		System.out.println(passWords);
		dataDrivenPoi.executeTest();
	}
	
}
