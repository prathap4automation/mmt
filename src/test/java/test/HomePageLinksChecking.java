package test;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import pages.HomePage;
import pages.ReferAndEarn;
import utility.Common;
import utility.GetPropertyValue;
import utility.HtmlManager;
//import utility.Report;

public class HomePageLinksChecking {
	static WebDriver driver;
	static WebDriverWait wait;
	static Common c;
	static GetPropertyValue prop;
	static HomePage hp;
	static ReferAndEarn re;
	static ExtentReports report;
	static ExtentTest test;
	static HtmlManager html;

	@BeforeTest
	public void init() throws IOException
	{
		prop = new GetPropertyValue();
		c =new Common();
		html =new HtmlManager();
		driver = (WebDriver) c.initBrowser(prop.getPropValue("browserName"));
		re=new ReferAndEarn(driver);
		report=new ExtentReports(c.pathModifier("ExtentReports//make_my_trip.html"),false);
		test=report.startTest("Make My trip");
	}

	@Test(priority=1)
	//	expectedExceptions=NoSuchElementException.classƒƒ
	public void openSite() throws IOException, InterruptedException
	{
		driver.manage().deleteAllCookies();
		driver.get(prop.getPropValue("baseUrl"));
		if(!driver.getTitle().contains(prop.getPropValue("siteTitle"))) {
//			super.logStatus("fail", "Title not matched!! check "+test.addScreenCapture(c.screenshot()));
			test.log(LogStatus.FAIL, html.fail("Title not matched!! check ")+test.addScreenCapture(c.screenshot()));
			System.exit(0);
		}
		wait = new WebDriverWait(driver,20);
		hp = new HomePage(driver);
		driver.manage().window().maximize();
		try {
			wait.until(ExpectedConditions.visibilityOf(hp.flightsLink));
		}
		catch(Exception ex) {	
			test.log(LogStatus.FAIL, html.fail("Home page not opened!! check ")+test.addScreenCapture(c.screenshot()));
//			super.logStatus("fail", "Home page not opened!! check "+test.addScreenCapture(c.screenshot()));
		}
//		super.logStatus("pass", "Site opened, check "+test.addScreenCapture(c.screenshot()));
		test.log(LogStatus.PASS, html.pass("Site opened using browser \""+prop.getPropValue("browserName")+"\""));
		//		c.minimizeBrowser();
	}

	//checking logo present or not
	@Test(priority=2,dependsOnMethods="openSite")
	public void logoCheck() throws WebDriverException, IOException, FindFailed
	{
		//checking with image name
		Boolean logoPresent = hp.siteLogo.isDisplayed();
		if(!logoPresent){
			test.log(LogStatus.FAIL, html.fail("Logo not found!! check ")+test.addScreenCapture(c.screenshot()));
//			super.logStatus("fail", "Logo not found!! check "+test.addScreenCapture(c.screenshot()));
			Assert.fail("Logo not found!!");
		}
		test.log(LogStatus.PASS, html.pass("Logo found."));
		//Image matching
		Screen s= new Screen();
		try{
			s.find(c.getImagesDir()+prop.getPropValue("siteLogo"));
			test.log(LogStatus.PASS, html.pass("Logo image found."));
//			super.logStatus("pass", "Logo image found!!");
		}
		catch(Exception e1){
			test.log(LogStatus.FAIL, html.fail("Logo image not found!! check ")+test.addScreenCapture(c.screenshot()));
//			super.logStatus("fail", "Logo image not found!! check "+test.addScreenCapture(c.screenshot()));
			Assert.fail("Logo image not found!!");
		}
		
	}

	//Checking broken links
	/*
	 	Let’s see some of the HTTP status codes.
		200 – Valid Link
		404 – Link not found
		400 – Bad request
		401 – Unauthorized
		500 – Internal Error
	 */
	@Test(priority=3,enabled=false)
	public void testBrokenLinks() throws Exception
	{
		String url;
		System.out.println("Total links are :"+hp.links.size());
		//collecting all links
		List<WebElement> elementList = hp.links;
		List<WebElement> firstSortedList = new ArrayList<WebElement>(); 
		int i=0;
		for (WebElement element : elementList) {
			url=element.getAttribute("href");
			if(url!= null)
			{
				firstSortedList.add(i, element);
				i++;
			}   
		} 
		System.out.println("After first sorting :"+firstSortedList.size());
		for(int j=0;j<firstSortedList.size();j++) {
			WebElement e=firstSortedList.get(j);
			url=null;
			url=firstSortedList.get(j).getAttribute("href");
			try
			{		 
				System.out.println("URL: " + url+ " returned " + c.isLinkBroken(new URL(url)));
			}
			catch(Exception exp)
			{
				System.out.println("At " +  e.getAttribute("innerHTML")+ " Exception occured -&gt; " + exp.getMessage()); 
			}
		}
	}
	
	//hovering and click on child element
	@Test(priority=4)
	public void testHover() throws WebDriverException, IOException
	{
		Actions a=new Actions(driver);
		wait.until(ExpectedConditions.visibilityOf(hp.moreLink));
		a.clickAndHold(hp.moreLink).build().perform();
		wait.until(ExpectedConditions.visibilityOf(hp.referAndEarnLink));
		hp.clickReferAndEarn();
		wait.until(ExpectedConditions.visibilityOf(re.inviteFriend));
		try {
			driver.navigate().to(prop.getPropValue("baseUrl"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			wait.until(ExpectedConditions.visibilityOf(hp.flightsLink));
//			super.logStatus("pass", "Home page opened successfully.");
		}
		catch(Exception ex) {
//			super.logStatus("fail", ex.getMessage()+"\n"+test.addScreenCapture(c.screenshot()));
			test.log(LogStatus.FAIL, html.fail(ex.getMessage())+"\n"+test.addScreenCapture(c.screenshot()));
		}
	}
	
	//checking drop down is in sorted order or not
	@Test(priority=5)
	public void testDorpdown() throws WebDriverException, IOException
	{
		hp.clickFlightFrom();
		wait.until(ExpectedConditions.visibilityOfAllElements(hp.fromDrpList));
		
		List<WebElement> list=hp.fromDrpList;
		List<String> actual =new ArrayList<String>();
		List<String> temp =new ArrayList<String>();
		for(WebElement ele: list)
		{
			actual.add(ele.getText());
		}
		temp.addAll(actual);
		Collections.sort(temp);
		if(temp.equals(actual)) {
			test.log(LogStatus.PASS, html.pass("Dropdown of from flght is in sorted order."));
//			super.logStatus("pass", "Dropdown of from flght is in sorted order.");
		}
		else {
			test.log(LogStatus.FAIL, html.fail("Dropdown of from flght is not in sorted order!!")+test.addScreenCapture(c.screenshot()));
			Assert.fail();
//			super.logStatus("fail", "Dropdown of from flght is not in sorted order!! check "+test.addScreenCapture(c.screenshot()));
		}
	}
	

	@AfterTest
	public void teardown()
	{
//		super.closeReport();
		report.endTest(test);
		report.flush();
		c.quit(driver);
	}
}
