package utility;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReporterNG implements IReporter 
{
private ExtentReports extent;
ExtentTest test;

public void generateReport (List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) 
{
	extent = new ExtentReports(outputDirectory + File.separator
			+ "ebayReport.html", true);

	for (ISuite suite : suites) {
		Map<String, ISuiteResult> result = suite.getResults();

		for (ISuiteResult r : result.values()) {
			ITestContext context = r.getTestContext();
			test = extent.startTest(context.getName());
			
			buildTestNodes(context.getPassedTests(), LogStatus.PASS);
			buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
			buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
		}
	}

	extent.flush();
	extent.close();
}



private void buildTestNodes(IResultMap tests, LogStatus status) {
	

	if (tests.size() > 0) {
		for (ITestResult result : tests.getAllResults()) {
		//	test = extent.startTest(result.getName());
			extent
			.addSystemInfo("Environment", "Android Slack Automation")
			.addSystemInfo("User Name", System.getProperty("user.name"));

			
			test.setStartedTime(getTime(result.getStartMillis()));
			test.setEndedTime(getTime(result.getEndMillis()));
			

			for (String group : result.getMethod().getGroups())
				test.assignCategory(group);
			
			String method_name = result.getMethod().getMethodName();
					
			if (result.getThrowable() != null) 
			{
				test.log(status, result.getMethod().getDescription());
				test.log(status, result.getThrowable());
				
			} else 
			{
				if (!method_name.contains("initObject"))
				{
				test.log(status, result.getMethod().getDescription());
				}
			}
			
			extent.endTest(test);
		}
	}
}

private Date getTime(long millis) {
	Calendar calendar = Calendar.getInstance();
	calendar.setTimeInMillis(millis);
	return calendar.getTime();
}
}
