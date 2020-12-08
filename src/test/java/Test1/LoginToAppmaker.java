package Test1;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginToAppmaker {

	public String baseURL ="https://appmakercms.otenro.com/app/login";
	public String ResetPasswordURL = "https://appmaker.otenro.com/app/resetPassword/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjVkOTJmNDAwMTZlYmUyNmQ1YjhiOGVmZCIsImVtYWlsIjoic2FrdW50aGFsYW5mbUBnbWFpbC5jb20iLCJpYXQiOjE1NzAwOTIwMDB9.kr02qnxDVijmzwhnrb717czXZA7Hf99ikGgrcd3XQZg";
	//String driverPath = "C:\\Automation_with_Jenkins\\SetWithJenkins\\Astro_Run_With_Jenkins\\ChromeDr\\chromedriver.exe";
	//String driverPath ="/usr/bin/google-chrome";
	
	public WebDriver driver;
	public String Appname;
	JavascriptExecutor js;
	
	
	@BeforeTest
	public void VerifyFrames() throws Exception {
	

		System.out.println("Launching Chrome Browser ");
		System.setProperty("webdriver.chrome.driver","/usr/bin/chromedriver");
		//System.setProperty("webdriver.chrome.driver",driverPath);
		//System.setProperty("webdriver.chrome.logfile",driverPath);
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		options.addArguments("window-size=1400,1500");
		options.addArguments("--disable-gpu");
		options.addArguments("--no-sandbox");
		options.addArguments("start-maximized");
		options.addArguments("enable-automation");
		options.addArguments("--disable-infobars");
		options.addArguments("--disable-dev-shm-usage");
		
		driver = new ChromeDriver(options);
		System.out.println("Browser launched");
		//driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	// Test case TC01 = Log in to the system with correct credentials
	@Test(priority = 2)
	public void VerifyLoginPage() throws Exception {
	

		driver.get(baseURL);

		//driver.findElement(By.name("Login_btn")).click();
		//Thread.sleep(2000);
		//System.out.println("Login button clicked = Passed");

		driver.findElement(By.name("email")).sendKeys("sakunthalanfm@gmail.com");
		Thread.sleep(2000);
		System.out.println("Email entered = Passed");

		driver.findElement(By.name("password")).sendKeys("Saku@123");
		System.out.println("Password entered = Passed");
		Thread.sleep(2000);

		driver.findElement(By.name("submitbtn")).click();
		System.out.println("Login button clicked = Passed");
		Thread.sleep(2000);
		
		driver.findElement(By.cssSelector(".me-default-button:nth-child(2) > .ng-scope")).click();
		System.out.println("You are already logged into an active session. Proceeding with this new session will result in you being logged out of your active session and any unsaved progress being lost. Please confirm to proceed.- Yes clicked");
		Thread.sleep(2000);

		String actual_msg = driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div/div[2]"))
				.getAttribute("innerHTML");
		String expect = "Login Successful";

		if (actual_msg.contains(expect)) {
			System.out.println("Validation passed = " + actual_msg);
		} else {
			System.out.println("Test Case Failed = " + actual_msg);
		}

		Thread.sleep(7000);

	
	}

	// Create new App
	
	//-------------------------------------*********************************************************--------------------------------------------------

	@Test(enabled = false) // (priority=3)
	public void CreateNewApp() throws Exception {

	

		driver.findElement(By.name("Create_New_App")).click();
		System.out.println("Select a app button clicked");
		Thread.sleep(9000);
		
		driver.manage().deleteAllCookies();
		System.out.println("All cookies Deleted");
		Thread.sleep(5000);
		
		driver.navigate().refresh();
		driver.navigate().refresh();
		Thread.sleep(5000);

		/*JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement Element = driver.findElement(By.cssSelector("#tab-content-0 > div > div:nth-child(7) > div > div.col-md-5.text-left > div.padding-top.margin-top"));
		js.executeScript("arguments[0].scrollIntoView();", Element);
		Thread.sleep(4000);*/
/*
		List<WebElement> a = driver.findElements(By.name("New_Templates"));
		a.get(0).click();
		
		//System.out.println(driver.findElement(By.cssSelector(".auto-btn-select-Zodiac")));
		
		//driver.findElement(By.cssSelector(".auto-btn-select-Zodiac")).click();
		Thread.sleep(4000);
		
		//System.out.println(driver.findElement(By.cssSelector(".auto-btn-select-Zodiac")));
		
		
//Check app naming window validation -----------------------------------------------------------------------------------------------------------
		
		driver.findElement(By.name("appName")).sendKeys("Astro 123");
		System.out.println("App name entered");
		Thread.sleep(5000);
		
		driver.findElement(By.name("GetStarted_Btn")).click();
		System.out.println("Get Started button clicked");
		Thread.sleep(2000);
		
		String actual_msg21 = driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div/div[2]")).getAttribute("innerHTML");
		String expect21 = "App name already exists!";

		if (actual_msg21.contains(expect21)) {
			System.out.println("Validation passed = " + actual_msg21);
		} else {
			System.out.println("Test Case Failed = " + actual_msg21);
		}
		Thread.sleep(7000);
		
		driver.findElement(By.name("appName")).sendKeys("@#@%%@#%$_-/)?/");
		System.out.println("App name entered with special characters");
		Thread.sleep(5000);
		
		String actual_msg1 = driver.findElement(By.xpath("/html/body/div[1]/ui-view/form/div[2]/fieldset[3]/span")).getAttribute("innerHTML");
		String expect1 = "You can only add english characters and numbers";

		if (actual_msg1.contains(expect1)) {
			System.out.println("Validation passed = " + actual_msg1);
		} else {
			System.out.println("Test Case Failed = " + actual_msg1);
		}
		Thread.sleep(7000);
		
		driver.findElement(By.name("appName")).clear();
		System.out.println("App naming window cleared");
		Thread.sleep(5000);
		
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();
		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) {
			int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}
		String generatedString = buffer.toString();

		WebElement App = driver.findElement(By.name("appName"));
		App.sendKeys(generatedString +" Astro");
		Thread.sleep(2000);

		WebElement Appname = driver.findElement(By.name("appName"));
		Appname.sendKeys(Keys.CONTROL + "a");

		Thread.sleep(1000);
		System.out.println("App name = " + Appname.getAttribute("value"));
		System.out.println("App name entered");

		String copiedText = driver.findElement(By.name("appName")).getAttribute("value").toString();
		Thread.sleep(1000);

		File objFile = new File("C:\\Appmaker Automation\\Article smoke Testing Process\\ExcelWriteReadFile\\AppnameReadWrite.xlsx");
		FileInputStream objInstream = new FileInputStream(objFile);
		FileOutputStream fos = null;
		XSSFWorkbook objWorkbk1 = new XSSFWorkbook(objInstream);// Workbook create
		XSSFSheet sheet = objWorkbk1.getSheet("AppNames");
		XSSFRow row = null;
		XSSFCell cell = null;
		int colNum = -1; // work sheet create object and get specific sheet

		row = sheet.getRow(0);

		for (int i = 0; i < row.getLastCellNum(); i++) {
			if (row.getCell(i).getStringCellValue().trim().equals("App Name"))
				;
			{
				colNum = i;
			}
		}
		row = sheet.getRow(1);
		if (row == null)
			row = sheet.createRow(1);

		cell = row.getCell(colNum);
		if (cell == null)
			cell = row.createCell(colNum);

		cell.setCellValue(copiedText); // App name saved to excel sheet

		fos = new FileOutputStream("C:\\Appmaker Automation\\Article smoke Testing Process\\ExcelWriteReadFile\\AppnameReadWrite.xlsx");
		objWorkbk1.write(fos);

		fos.close();
		objWorkbk1.close();

		Thread.sleep(10000);

		driver.findElement(By.name("GetStarted_Btn")).click();
		System.out.println("Get Started button clicked");
		Thread.sleep(7000);

		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		//driver.findElement(By.name("yes_btn")).click();
		//Thread.sleep(2000);
		//driver.findElement(By.name("yes_btn")).click();
		
		WebElement element = driver.findElement(By.name("yes_btn"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element); 
		
		System.out.println("Deleting demo data yes button clicked");
		Thread.sleep(2000);

		String actual_msg2 = driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div/div[2]")).getAttribute("innerHTML");
		String expect2 = "Successfully Removed";

		if (actual_msg2.contains(expect2)) {
			System.out.println("Validation passed = " + actual_msg2);
		} else {
			System.out.println("Test Case Failed = " + actual_msg2);
		}
		Thread.sleep(7000);
		
	/*	driver.findElement(By.className("automation-auto-refresh")).click();
		System.out.println("Auto Refesh slider button clicked");
		Thread.sleep(4000);
		
		driver.findElement(By.name("refresh")).click();
		System.out.println("Refresh button clicked");
		Thread.sleep(4000);*/
		

		

	}
	
	

