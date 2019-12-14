package testcase;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import utility.LocaleMsgReader;

public class LoginCases {
	
	WebDriver driver;
	Actions act;

	@Parameters("Locale")
	@Test(priority=0)
	public void openingLoginPage(String localeSelection) throws Exception {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\sundar\\Downloads\\chromedriver\\chromedriver.exe");
		
	    driver = new ChromeDriver();
		
	    act=new Actions(driver);

		driver.get("enter url");

		WebElement one=driver.findElement(By.xpath("//a[@class='dropdown-toggle'][contains(text(),'English')]"));
		
		WebElement two=driver.findElement(By.xpath("//li[@class='dropdown hover-open']/ul/li[1]/a"));
		
		String locale=one.getText();
		
		System.out.println(locale);
		
		if(locale.contains(localeSelection)){
			
			System.out.println("Locale is english only");
			
		}else{
			
			act.moveToElement(one).click(two).build().perform();
			
		}

	}

	@Test(priority=1)
	public void verifyMandatoryValidaton() throws Exception {
       
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		SoftAssert soft=new SoftAssert();
		WebElement mail=driver.findElement(By.xpath("//input[@id='email']/following::div[@id='advice-required-entry-email']"));		
		System.out.println(mail.getText());
		soft.assertEquals(mail.getText(), LocaleMsgReader.getMessage("mandatoryValidation"));
		soft.assertEquals(driver.findElement(By.xpath("//input[@id='pass']/following::div[@id='advice-required-entry-pass']")).getText(), LocaleMsgReader.getMessage("mandatoryValidation"));
        soft.assertAll();
	}
	
	@Test(priority=2)
	public void verifyinvalidEMail() throws Exception {
       
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("Test");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		WebElement mail=driver.findElement(By.xpath("//input[@id='email']/following::div[@id='advice-validate-email-email']"));		
		System.out.println(mail.getText());
		Assert.assertEquals(mail.getText(), LocaleMsgReader.getMessage("invalidEmailFormat"));
		
	}

	@Test(priority=3)
	public void verifyPasswordMinLength() throws Exception {
       
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("Test");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		WebElement mail=driver.findElement(By.xpath("//input[@id='pass']/following::div[@id='advice-validate-custom-password-pass']"));		
		System.out.println(mail.getText());
		Assert.assertEquals(mail.getText(), LocaleMsgReader.getMessage("passwordMinLength"));
		
	}
}