package TestNG;
import org.junit.Assert;
import org.openqa.selenium.*;

public class page_Object_signUp {
	 WebElement firstName = null;
	 WebElement lastName = null;
	 WebElement mobileNum = null;
	 WebElement emailAddress = null;
	 WebElement password = null;
	 WebElement confirmPassword = null;
	 WebElement signUpButton = null;
	 public static boolean noEceptionOccured=true;
	page_Object_signUp(WebDriver driver){
		try{
		 firstName = driver.findElement(By.xpath("//*[@id='headersignupform']/div[3]/div[1]/div/label/input"));
		  lastName = driver.findElement(By.xpath("//*[@id='headersignupform']/div[3]/div[2]/div/label/input"));

		mobileNum = driver.findElement(By.xpath("//*[@id='headersignupform']/div[4]/label/input"));

		emailAddress = driver.findElement(By.xpath("//*[@id='headersignupform']/div[5]/label/input"));

		 password = driver.findElement(By.xpath("//*[@id='headersignupform']/div[6]/label/input"));

		confirmPassword =driver.findElement(By.xpath("//*[@id='headersignupform']/div[7]/label/input"));
		signUpButton=driver.findElement(By.xpath("//*[@id='headersignupform']/div[8]/button"));
		}catch(Exception e){
			noEceptionOccured=false;
		}
	}
	public void enterFirstName(String Name){
		try{
		firstName.sendKeys(Name);
		}catch(Exception e){
			noEceptionOccured=false;
			
		}
		TestNGClass.stepsMade=TestNGClass.stepsMade+"-First Name Entered";

	}
	public void enterLastName(String Name){
		try{
		lastName.sendKeys(Name);
		}catch(Exception e){
			noEceptionOccured=false;
		}
		TestNGClass.stepsMade=TestNGClass.stepsMade+"-Last Name Entered";

	}
	public void enterMobileNum(String Num){
		try{
		mobileNum.sendKeys(Num);
		}catch(Exception e){
			noEceptionOccured=false;
		}
		TestNGClass.stepsMade=TestNGClass.stepsMade+"- Mobile  Entered";

	}
	public void enterEmailAddress(String Email){
		try{
		emailAddress.sendKeys(Email);
		}catch(Exception e){
			noEceptionOccured=false;
		}
		TestNGClass.stepsMade=TestNGClass.stepsMade+"-Email Entered";

	}
	public void enterPassword(String Pass){
		try{
		password.sendKeys(Pass);
	}catch(Exception e){
		noEceptionOccured=false;
		}
		TestNGClass.stepsMade=TestNGClass.stepsMade+"-Password Entered";

	}
	public void enterConfirmPassword(String confPass){
		try{
		confirmPassword.sendKeys(confPass);	
		}catch(Exception e){
			noEceptionOccured=false;
		}
		TestNGClass.stepsMade=TestNGClass.stepsMade+"-Confirm password Entered";

	}
	public void clickSignUpButton(){
		try{
        signUpButton.click();
        }catch(Exception e){
        	noEceptionOccured=false;
		}
		TestNGClass.stepsMade=TestNGClass.stepsMade+"-SignUp Button Pressed";

	}
	
}






























