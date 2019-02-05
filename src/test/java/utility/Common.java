package utility;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Common {
	static WebDriver driver;
	static GetPropertyValue prop;

	public void init()
	{	prop=new GetPropertyValue(); 			}

	public String getCurrentDir()
	{	return System.getProperty("user.dir"); 	}
	
	public String getOsBit()
	{	return System.getProperty("os.arch"); 	}

	public String getCurrentOS()
	{	
		String os_name=System.getProperty("os.name");
		if(os_name.trim().toLowerCase().contains("mac"))
			return "mac";
		else if(os_name.trim().toLowerCase().contains("windows"))
			return "windows";
		else if(os_name.trim().toLowerCase().contains("linux"))
			return "linux";
		else {
			System.out.println("Invalid os");System.exit(0);
			return null;
		}
	}
	
	

	public String getImagesDir() throws IOException
	{	return System.getProperty("user.dir")+prop.getPropValue("imagesPath"); 	}

	public Object driverObj()
	{	return driver; 							}

	public Object initBrowser(String browser) throws IOException
	{
		this.init();
		if(this.getCurrentOS().equals("windows"))
		{
			if(this.getOsBit().contains("64"))
			{
				switch(browser.trim().toLowerCase())
				{
				case "chrome":
					if(prop.getPropValue("windowsChrome64Driver").equals("null")) {
						System.out.println(browser+" driver not built!!");
						System.exit(0);
					}
					else {
						System.setProperty("webdriver.chrome.driver", this.getCurrentDir()+prop.getPropValue("windowsChrome64Driver"));
						driver=new ChromeDriver();
					}
					break;
				case "firefox":
					if(prop.getPropValue("windowsFirefox64Driver").equals("null")) {
						System.out.println(browser+" driver not built!!");
						System.exit(0);
					}
					else {
						System.setProperty("webdriver.gecko.driver", this.getCurrentDir()+prop.getPropValue("windowsFirefox64Driver"));
						driver=new FirefoxDriver();
					}
					break;
				case "opera":
					if(prop.getPropValue("windowsOpera64Driver").equals("null")) {
						System.out.println(browser+" driver not built!!");
						System.exit(0);
					}
					else {
						System.setProperty("webdriver.opera.driver", this.getCurrentDir()+prop.getPropValue("windowsOpera64Driver"));
						driver=new OperaDriver();
					}
					break;
				case "ie":
					if(prop.getPropValue("windowsIe64Driver").equals("null")) {
						System.out.println(browser+" driver not built!!");
						System.exit(0);
					}
					else {
						System.setProperty("webdriver.ie.driver", this.getCurrentDir()+prop.getPropValue("windowsIe64Driver"));
						driver=new InternetExplorerDriver();
					}
					break;
				case "safari":
					System.out.println("Safari is not built for windows!! try with other browser");
					System.exit(0);
					break;
				default:
					System.out.println("Invalid Browser Name!!");
					System.exit(0);
					break;
				}
			}
			else if(this.getOsBit().contains("32")||this.getOsBit().contains("86"))
			{
				switch(browser.trim().toLowerCase())
				{
				case "chrome":
					if(prop.getPropValue("windowsChrome32Driver").equals("null")) {
						System.out.println(browser+" driver not built!!");
						System.exit(0);
					}
					else {
						System.setProperty("webdriver.chrome.driver", this.getCurrentDir()+prop.getPropValue("windowsChrome32Driver"));
						driver=new ChromeDriver();
					}
					break;
				case "firefox":
					if(prop.getPropValue("windowsFirefox32Driver").equals("null")) {
						System.out.println(browser+" driver not built!!");
						System.exit(0);
					}
					else {
						System.setProperty("webdriver.gecko.driver", this.getCurrentDir()+prop.getPropValue("windowsFirefox32Driver"));
						driver=new FirefoxDriver();
					}
					break;
				case "opera":
					if(prop.getPropValue("windowsOpera32Driver").equals("null")) {
						System.out.println(browser+" driver not built!!");
						System.exit(0);
					}
					else {
						System.setProperty("webdriver.opera.driver", this.getCurrentDir()+prop.getPropValue("windowsOpera32Driver"));
						driver=new OperaDriver();
					}
					break;
				case "ie":
					if(prop.getPropValue("windowsIe32Driver").equals("null")) {
						System.out.println(browser+" driver not built!!");
						System.exit(0);
					}
					else {
						System.setProperty("webdriver.ie.driver", this.getCurrentDir()+prop.getPropValue("windowsIe32Driver"));
						driver=new InternetExplorerDriver();
					}
					break;
				case "safari":
					System.out.println("Safari is not built for windows!! try with other browser");
					System.exit(0);
					break;
				default:
					System.out.println("Invalid Browser Name!!");
					System.exit(0);
					break;
				}
			}
			else {
				System.out.println("Invalid "+this.getCurrentOS()+" Os bit!!");
			}
		}
		else if(this.getCurrentOS().equals("linux"))
		{
			if(this.getOsBit().contains("64"))
			{
				switch(browser.trim().toLowerCase())
				{
				case "chrome":
					if(prop.getPropValue("linuxChrome64Driver").equals("null")) {
						System.out.println(browser+" driver not built!!");
						System.exit(0);
					}
					else {
						System.setProperty("webdriver.chrome.driver", this.getCurrentDir()+prop.getPropValue("linuxChrome64Driver"));
						driver=new ChromeDriver();
					}
					break;
				case "firefox":
					if(prop.getPropValue("linuxFirefox64Driver").equals("null")) {
						System.out.println(browser+" driver not built!!");
						System.exit(0);
					}
					else {
						System.setProperty("webdriver.gecko.driver", this.getCurrentDir()+prop.getPropValue("linuxFirefox64Driver"));
						driver=new FirefoxDriver();
					}
					break;
				case "opera":
					if(prop.getPropValue("linuxOpera64Driver").equals("null")) {
						System.out.println(browser+" driver not built!!");
						System.exit(0);
					}
					else {
						System.setProperty("webdriver.opera.driver", this.getCurrentDir()+prop.getPropValue("linuxOpera64Driver"));
						driver=new OperaDriver();
					}
					break;
				case "ie":
					System.out.println("Internet explorer is not built for linux!! try with other browser");
					System.exit(0);
					break;
				case "safari":
					System.out.println("Safari is not built for linux!! try with other browser");
					System.exit(0);
					break;
				default:
					System.out.println("Invalid Browser Name!!");
					System.exit(0);
					break;
				}
			}
			else if(this.getOsBit().contains("32")||this.getOsBit().contains("86"))
			{
				switch(browser.trim().toLowerCase())
				{
				case "chrome":
					if(prop.getPropValue("linuxChrome32Driver").equals("null")) {
						System.out.println(browser+" driver not built!!");
						System.exit(0);
					}
					else {
						System.setProperty("webdriver.chrome.driver", this.getCurrentDir()+prop.getPropValue("linuxChrome32Driver"));
						driver=new ChromeDriver();
					}
					break;
				case "firefox":
					if(prop.getPropValue("linuxFirefox32Driver").equals("null")) {
						System.out.println(browser+" driver not built!!");
						System.exit(0);
					}
					else {
						System.setProperty("webdriver.gecko.driver", this.getCurrentDir()+prop.getPropValue("linuxFirefox32Driver"));
						driver=new FirefoxDriver();
					}
					break;
				case "opera":
					if(prop.getPropValue("linuxOpera32Driver").equals("null")) {
						System.out.println(browser+" driver not built!!");
						System.exit(0);
					}
					else {
						System.setProperty("webdriver.opera.driver", this.getCurrentDir()+prop.getPropValue("linuxOpera32Driver"));
						driver=new OperaDriver();
					}
					break;
				case "ie":
					System.out.println("Internet explorer is not built for linux!! try with other browser");
					System.exit(0);
					break;
				case "safari":
					System.out.println("Safari is not built for linux!! try with other browser");
					System.exit(0);
					break;
				default:
					System.out.println("Invalid Browser Name!!");
					System.exit(0);
					break;
				}
			}
			else {
				System.out.println("Invalid "+this.getCurrentOS()+" Os bit!!");
			}
		}
		else if(this.getCurrentOS().equals("mac"))
		{
			if(this.getOsBit().contains("64"))
			{
				switch(browser.trim().toLowerCase())
				{
				case "chrome":
					System.setProperty("webdriver.chrome.driver", this.getCurrentDir()+prop.getPropValue("macChrome64Driver"));
					driver=new ChromeDriver();
					break;
				case "firefox":
					System.setProperty("webdriver.gecko.driver", this.getCurrentDir()+prop.getPropValue("macFirefox64Driver"));
					driver=new FirefoxDriver();
					break;
				case "opera":
					System.setProperty("webdriver.opera.driver", this.getCurrentDir()+prop.getPropValue("macOpera64Driver"));
					driver=new OperaDriver();
					break;
				case "ie":
					System.out.println("Internet explorer is not built for mac!! try with other browser");
					System.exit(0);
					break;
				case "safari":
					driver = new SafariDriver();
					break;
				default:
					System.out.println("Invalid Browser Name!!");
					System.exit(0);
					break;
				}
			}
			else if(this.getOsBit().contains("32")||this.getOsBit().contains("86"))
			{
				switch(browser.trim().toLowerCase())
				{
				case "chrome":
					if(prop.getPropValue("macChrome32Driver").equals("null")) {
						System.out.println(browser+" driver not built!!");
						System.exit(0);
					}
					else {
						System.setProperty("webdriver.chrome.driver", this.getCurrentDir()+prop.getPropValue("macChrome32Driver"));
						driver=new OperaDriver();
					}
					break;
				case "firefox":
					if(prop.getPropValue("macFirefox32Driver").equals("null")) {
						System.out.println(browser+" driver not built!!");
						System.exit(0);
					}
					else {
						System.setProperty("webdriver.gecko.driver", this.getCurrentDir()+prop.getPropValue("macFirefox32Driver"));
						driver=new FirefoxDriver();
					}
					break;
				case "opera":
					if(prop.getPropValue("macOpera32Driver").equals("null")) {
						System.out.println(browser+" driver not built!!");
						System.exit(0);
					}
					else {
						System.setProperty("webdriver.opera.driver", this.getCurrentDir()+prop.getPropValue("macOpera32Driver"));
						driver=new OperaDriver();
					}
					break;
				case "ie":
					System.out.println("Internet explorer is not built for mac!! try with other browser");
					System.exit(0);
					break;
				case "safari":
					driver = new SafariDriver();
					break;
				default:
					System.out.println("Invalid Browser Name!!");
					System.exit(0);
					break;
				}
			}
			else {
				System.out.println("Invalid "+this.getCurrentOS()+" Os bit!!");
			}
		}
		return driver;
	}

	public void close(WebDriver d)
	{
		d.close();
		driver=null;
	}

	public void quit(WebDriver d)
	{
		d.quit();
		driver=null;
	}
	
	public String pathModifier(String s)
	{
		if(this.getCurrentOS().equals("mac")) {
			String url=s.replace('\\', '/');
			return url;
		}
		else {
			return s;
		}
	}

	public String screenshot() throws WebDriverException, IOException
	{
		Date d=new Date();
		SimpleDateFormat df=new SimpleDateFormat("dd-MM-yy-H-mm-ss");
		String ssname=df.format(d)+".png";
		System.out.println(this.getCurrentOS());
		FileUtils.copyFile(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE), new File(this.pathModifier("screenshots//")+ssname));
		return System.getProperty("user.dir")+this.pathModifier("//screenshots//")+ssname;
		
	}

	public void minimizeBrowser() throws IOException
	{
		if(!prop.getPropValue("browserName").trim().toLowerCase().equals("safari"))
			driver.manage().window().setPosition(new Point(-3000,0));
	}

	public String isLinkBroken(URL url) throws Exception
	{
		//url = new URL("https://yahoo.com");
		String response = "";
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		try
		{
			connection.connect();
			response = connection.getResponseMessage();         
			connection.disconnect();
			return response;
		}
		catch(Exception exp)
		{
			return exp.getMessage();
		}   
	}

}
