package login;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Login_Test {

	String[][] data = { { "elonmusk14", "62G9IH" }, { "elonmusk14", "62G9IHl" }, { "62G9IH", "62G9IH" },
			{ "gagsg", "jhagdjad" }, };

	@DataProvider(name = "loginCredentials")
	public String[][] loginDataProvide() {

		return data;

	}

	@Test(dataProvider = "loginCredentials")
	public void loginValidation(String uname, String pswd) {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://adactinhotelapp.com/HotelAppBuild2/");
		driver.manage().window().maximize();

		driver.findElement(By.id("username")).sendKeys(uname);
		driver.findElement(By.id("password")).sendKeys(pswd);
		driver.findElement(By.id("login")).click();

	}

}
