package TestNG;

import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.BasicConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestNGClass {
	ExtentReports extent;
	ExtentTest logger;
	// //////////PreDefined Variables\\\\\\\\\\\\\\\\\\\\
	public static int test_Cases_Start_Row = 1;
	public static int test_Cases_Start_Column = 2;
	public static int test_Cases_End_Column = 8;
	public static int firstNameCell = 2;
	public static int lastNameCell = 3;
	public static int mobileNumCell = 4;
	public static int emailCell = 5;
	public static int passwordCell = 6;
	public static int confirmPasswordCell = 7;
	public static int expectedUrlCell = 9;
	public static int actualUrlCell = 10;
	public static int statusCell = 11;
	public static int logInStatusCell = 12;
	public static int sheetNum = 0;
	public static int delay = 7;
	public static int numOfVariables = 6;
	public static int descriptionCell = 1;
	public static int testIDCell = 0;

	// /////////// VARIABLES TO BE USED IN CODE
	public int globalCounter = 0;
	public String exeptionMessage = "";
	public String url;
	public String ExeptionOccured = "";
	public static String successfulLogInUrl = "https://www.phptravels.net/account/";

	public static String ExcelPath = System.getProperty("user.dir")
			+ "\\Worksheet.xlsx";
	public static String WebDriverPath = System.getProperty("user.dir")
			+ "\\chromedriver.exe";

	public static String registePagePath = "https://www.phptravels.net/register";

	public static String stepsMade = "";
	Excel_Read Excel_Test_File = new Excel_Read(ExcelPath);
	Excel_Write Excel_Test_File_Write = new Excel_Write(ExcelPath);

	@BeforeTest
	public void startTest() {
		extent = new ExtentReports(System.getProperty("user.dir")
				+ "/test-output/STMExtentReport.html");
		extent.addSystemInfo("Host name", "Technical Assessment")
				.addSystemInfo("User Name", "Moustafa Aly");
		BasicConfigurator.configure();
	}

	// ///////// DATA FROM EXCEL/////////////////////
	@DataProvider(name = "DataFromExcel")
	public Object[][] getExcel() throws IOException {
		int num_of_rows = Excel_Test_File.getRowNum(sheetNum);
		// int num_of_col=Excel_Test_File.getColNum(sheetNum);
		String excel_File_Data[][] = new String[num_of_rows
				- test_Cases_Start_Row][numOfVariables];
		for (int i = test_Cases_Start_Row; i < (num_of_rows-test_Cases_Start_Row); i++) {
			for (int j = test_Cases_Start_Column; j < 8; j++) {
				excel_File_Data[i - test_Cases_Start_Row][j
						- test_Cases_Start_Column] = Excel_Test_File.fetchData(
						sheetNum, i, j);
			}
		}

		return excel_File_Data;
	}

	// //////// TEST USING DATA DRIVEN FROM EXCEL///////////////////////////

	@Test(dataProvider = "DataFromExcel")
	public void signUpRegister(String firstName, String lastName,
			String mobileNum, String email, String password,
			String confirmPassword) throws IOException {

		globalCounter++;

		stepsMade = stepsMade + "Data LOADED FROM EXCEL";

		try {
			System.setProperty("webdriver.chrome.driver", WebDriverPath);
		} catch (Exception a) {
			page_Object_signUp.noEceptionOccured = false;
			System.out.println("Driver Not found");
			exeptionMessage = a.getMessage();
			stepsMade = stepsMade + "-DRIVER Not INTIATED";
			exeptionMessage = a.getMessage();
			Assert.assertEquals("Quit", "Test Case");

		}
		stepsMade = stepsMade + "-DRIVER INTIATED";

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to(registePagePath);

		page_Object_signUp signUpPage = new page_Object_signUp(driver);
		try {
			signUpPage.enterFirstName(firstName);
			signUpPage.enterLastName(lastName);
			signUpPage.enterMobileNum(mobileNum);
			signUpPage.enterEmailAddress(email);
			signUpPage.enterPassword(password);
			signUpPage.enterConfirmPassword(confirmPassword);
			signUpPage.clickSignUpButton();
		} catch (Exception b) {
			driver.quit();
			signUpPage = new page_Object_signUp(driver);
			exeptionMessage = b.getMessage();
			Assert.assertEquals("Quit", "Test Case");

		}

		try {
			WebDriverWait wait = new WebDriverWait(driver, delay);

			wait.until(ExpectedConditions.visibilityOfElementLocated(By
					.xpath(" /html/body/div[2]/div[1]/div[1]/div/div/div[1]/div/div[1]/img")));
		} catch (Exception c) {
			System.out.println(c.getMessage());
		}

		System.out.println(Excel_Test_File.fetchData(sheetNum, globalCounter,
				expectedUrlCell));// ////
		System.out.println(driver.getCurrentUrl());// ///
		Excel_Test_File_Write.writeData(sheetNum, globalCounter, actualUrlCell,
				driver.getCurrentUrl());
		url = driver.getCurrentUrl();
		logger = extent.startTest("Test case ID: "
				+ Excel_Test_File
						.fetchData(sheetNum, globalCounter, testIDCell));
		if (url.equals(Excel_Test_File.fetchData(sheetNum, globalCounter,
				expectedUrlCell))) {
			driver.quit();

			Assert.assertTrue(true);
			Excel_Test_File_Write.writeData(sheetNum, globalCounter,
					statusCell, "Test Case passed");
			if (url.equals(successfulLogInUrl)) {
				if (page_Object_LogIn.logInVerifiy(email, password, url)) {
					Excel_Test_File_Write.writeData(sheetNum, globalCounter,
							logInStatusCell, "Successful LogIN");
					logger.log(LogStatus.PASS, "Successful LogIN");

				} else {
					Excel_Test_File_Write.writeData(sheetNum, globalCounter,
							logInStatusCell, "Not Successful LogIn");
					logger.log(LogStatus.PASS, "Not Successful LogIN");

				}

			} else {
				Excel_Test_File_Write.writeData(sheetNum, globalCounter,
						logInStatusCell, "N/A");

			}

		} else {
			logger.log(
					LogStatus.FAIL,
					logger.addScreenCapture(takeScreenShot(driver,
							globalCounter)) + "ScreenShot");

			driver.quit();
			Excel_Test_File_Write.writeData(sheetNum, globalCounter,
					statusCell, "Test Case failed");
			Excel_Test_File_Write.writeData(sheetNum, globalCounter,
					logInStatusCell, "N/A");

			Assert.assertTrue(false);

		}
		signUpPage = new page_Object_signUp(driver);

	}

	@AfterMethod
	public void getReport(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			logger.log(LogStatus.FAIL, "Test Failed");
			logger.log(
					LogStatus.INFO,
					"Test Description:   "
							+ Excel_Test_File.fetchData(sheetNum,
									globalCounter, descriptionCell));
			logger.log(LogStatus.INFO, stepsMade);
			logger.log(LogStatus.INFO, "Exeption Message:  " + exeptionMessage);

		} else {
			logger.log(LogStatus.PASS, "Test passed");
			logger.log(
					LogStatus.INFO,
					"Test Description:   "
							+ Excel_Test_File.fetchData(sheetNum,
									globalCounter, descriptionCell));
			logger.log(LogStatus.INFO, stepsMade);

		}
		stepsMade = "";
		exeptionMessage = "";
		extent.endTest(logger);
	}

	@AfterTest
	public void endReport() {
		extent.flush();
		extent.close();
	}

	public static String takeScreenShot(WebDriver driver, int testCaseCounter)
			throws IOException {
		File sourceFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		File Dest = new File("TestCase" + (testCaseCounter) + ".png");
		String errflpath = Dest.getAbsolutePath();
		FileUtils.copyFile(sourceFile, Dest);
		testCaseCounter++;
		return errflpath;
	}

}
