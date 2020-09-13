package TestNG;



import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class page_Object_LogIn {
	 WebElement EmailAddress = null;
	 WebElement Password = null;
	 WebElement signInButton = null;
	 public static int delay=7;
	 page_Object_LogIn(WebDriver driver){
		try{

			EmailAddress = driver.findElement(By.xpath("//*[@id='loginfrm']/div[3]/div[1]/label/input"));

			Password = driver.findElement(By.xpath("//*[@id='loginfrm']/div[3]/div[2]/label/input"));
			signInButton=driver.findElement(By.xpath("//*[@id='loginfrm']/button"));


		}catch(Exception e){
		
		}
	}


	public void EnterEmailAddress(String Email){
		try{
		EmailAddress.sendKeys(Email);
		}catch(Exception e){
			
		}
		TestNGClass.stepsMade=TestNGClass.stepsMade+"-LOGIN EMAIL ENTERED";

	}
	public void EnterPassword(String Pass){
		try{
		Password.sendKeys(Pass);
	}catch(Exception e){
		
		}
		TestNGClass.stepsMade=TestNGClass.stepsMade+"-LOGIN PASSWORD ENTERED";

	}

	public void clickSignInButton(){
		try{
        signInButton.click();
        }catch(Exception e){
		}
		TestNGClass.stepsMade=TestNGClass.stepsMade+"-LOGIN SIGNIN BUTTON CLICKED";

	}
	public  static boolean logInVerifiy(String email,String password,String url){
		WebDriver   driver=new ChromeDriver(); 
		    driver.manage().window().maximize();
		    driver.navigate().to("https://www.phptravels.net/login");
		    page_Object_LogIn signInPage=new page_Object_LogIn(driver);
		    signInPage.EnterEmailAddress(email);
		    signInPage.EnterPassword(password);
		    signInPage.clickSignInButton();
		    try{
				  WebDriverWait wait = new WebDriverWait(driver, delay);

				  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(" /html/body/div[2]/div[1]/div[1]/div/div/div[1]/div/div[1]/img")));
				  }catch(Exception e){
					  System.out.println(e.getMessage());
				  }
		    if(driver.getCurrentUrl().equals(url)){
		    	driver.close();
				TestNGClass.stepsMade=TestNGClass.stepsMade+"-LOGIN VERIFIED";

		    	return true;

		    }else{
		    	driver.close();
				TestNGClass.stepsMade=TestNGClass.stepsMade+"-LOGIN NOT VERIFIED";

		    	return false;
		    }

	}
}



























