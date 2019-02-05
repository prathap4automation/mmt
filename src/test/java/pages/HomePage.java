package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.Common;

public class HomePage {
	WebDriver driver;
	static Common c;
	
	public void initDriverObj()
	{
		c=new Common();
		Object d=c.driverObj();
		if(d==null)
		{
			System.out.println("Driver not initiated!!");
			System.exit(0);
		}
		else {
			this.driver=(WebDriver) d;
		}
	}
	
	//page elements
	@FindBy(xpath="(//a[contains(text(),'flights')])[1]")
	public WebElement flightsLink;
	
	//site logo
	@FindBy(xpath="//img[@class='mmt_header_logo' ][contains(@src,'mmtlogo')]")
	public WebElement siteLogo;
	
	@FindAll(@FindBy(tagName="a"))
	public List<WebElement> links;
	
	//more link
	@FindBy(xpath="//a[contains(text(),'more +')]")
	public WebElement moreLink;
	
	@FindBy(xpath="//a[contains(text(),'more +')]/following::div[2]/ul/li[7]/a")
	public WebElement referAndEarnLink;
	
	@FindBy(xpath="//section[@id='js-hp-widgetSection']/div[2]/div/input")
	public WebElement flightFrom;
	
	@FindAll(@FindBy(xpath="//ul[contains(@class,'hp-widget__sfrom')]/li[*]/div/p/span"))
	public List<WebElement> fromDrpList;
	
	//constructor
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//operational methods
	public void clickFlightsLink()
	{	flightsLink.click(); 		}
	
	public void clickByLinkText(String link)
	{	driver.findElement(By.linkText(link)).click(); 	}
	
	public void clickReferAndEarn()
	{	this.referAndEarnLink.click(); 	}
	
	public void clickFlightFrom()
	{	flightFrom.click(); 				}
	
	
	
}
