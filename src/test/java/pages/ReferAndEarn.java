package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ReferAndEarn {
	WebDriver driver;
	
	//locators
	@FindBy(xpath="//p[contains(text(),'Invite a friend to download the app')] ")
	public WebElement inviteFriend;
	
	//constructor
	public ReferAndEarn(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//operational methods 

}
