package utility;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Report {
	public static ExtentReports report;
	public static ExtentTest test;
	static Common c;

	public void startReport()
	{
		report = new ExtentReports(System.getProperty("user.dir")+c.pathModifier("//ExtentReports//make_my_trip.html"),false);
		test=report.startTest("Make My trip");
	}

	public void logStatus(String status,String msg)
	{
		switch(status.toLowerCase().toLowerCase())
		{
		case "pass":
			test.log(LogStatus.PASS, msg);break;
		case "fail":
			test.log(LogStatus.FAIL, msg);break;
		case "error":
			test.log(LogStatus.ERROR, msg);break;
		case "skip":
			test.log(LogStatus.SKIP, msg);break;
		case "fatal":
			test.log(LogStatus.FATAL, msg);break;
		case "info":
			test.log(LogStatus.INFO, msg);break;
		default:
			test.log(LogStatus.FAIL, "Invalid status found!!");
			System.exit(0);
		}
	}
	
	
	public void closeReport()
	{
		report.endTest(test);
		report.flush();
	}
	
	



}
