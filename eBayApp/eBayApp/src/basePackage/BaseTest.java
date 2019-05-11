package basePackage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.xml.sax.SAXException;

import io.appium.java_client.android.AndroidDriver;


import utility.XMLDataReader;
import utility.CustomeLogger;
import utility.TestDataReader;

public class BaseTest extends RemoteWebDriver 
{
	protected TestDataReader td;
	protected static HashMap<String, String> locatorMap;	
	@SuppressWarnings("rawtypes")
	protected static AndroidDriver driver;
	//public static final Logger logger = Logger.getGlobal();
	private static Map<ITestResult, List<Throwable>> verificationFailuresMap = new HashMap<ITestResult, List<Throwable>>();
	protected static CustomeLogger logger;
	
	 //Default constructor
		protected BaseTest()
		{
			super();
		}

		
		public static void setStaticValue (CustomeLogger logger)
		{
			
			BaseTest.logger = logger;
			logger.log("[Msg-Init] : Initializing Base test...");	
		}

		
		@SuppressWarnings("rawtypes")
		@BeforeTest
		public void launchEbayApp () throws Exception
		{
			try 
			{
			System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.Jdk14Logger");
		    logger = new CustomeLogger();
			logger.log("Launching eBay app on emulator");
			System.out.println("Launching eBay app on emulator");
			
	        DesiredCapabilities Capabilites = new DesiredCapabilities();
			
			Capabilites.setCapability("platformName", "android");
			Capabilites.setCapability("deviceName", "emulator-5554");
			Capabilites.setCapability("automationName", "UiAutomator2");
			Capabilites.setCapability("appPackage", "com.ebay.mobile");
			Capabilites.setCapability("appActivity", "com.ebay.mobile.activities.MainActivity");
			Capabilites.setCapability("app", "/Users/anmishra/Android/eBay.apk");
			
		
			driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), Capabilites);
			
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			//td = new TestDataReader("Res/Locators.properties");
			//locatorMap = td.getDataMap();
			
			Thread.sleep(1000);
			Thread.sleep(1000);
			Thread.sleep(1000);
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		
		
		@DataProvider(name="globaldataprovider")  // method to read data from scenario data file to hashmap
		protected Object[][] getTestData(ITestContext context) throws ParserConfigurationException, SAXException, IOException 
		{
		   String scenarioName = context.getCurrentXmlTest().getName();
		   XMLDataReader dataReader = new XMLDataReader();
			HashMap<String, String> dataMap = dataReader.getScenarioData(scenarioName);
			//System.out.println("Size of the data map: "+dataMap.size());		  
			return new Object[][] { { dataMap } };
		}

		public static List<Throwable> getVerificationFailures() 

		{
			//logger.log("Status for current test: "+ Reporter.getCurrentTestResult().getName() + " is: "+ Reporter.getCurrentTestResult().getStatus());
			List<Throwable> verificationFailures = verificationFailuresMap.get(Reporter.getCurrentTestResult());
			return verificationFailures == null ? new ArrayList<Throwable>() : verificationFailures;
		}

	
		private static void addVerificationFailure(Throwable e)
		{
			List<Throwable> verificationFailures = getVerificationFailures();
			//logger.log("Size of verificationFailures: "+ verificationFailures.size());
			verificationFailuresMap.put(Reporter.getCurrentTestResult(), verificationFailures);
			verificationFailures.add(e);
		}

		
	    public static void verifyTrue(boolean condition, String message) 
	    {
	    	try {
	    		
	    		Assert.assertTrue(condition, message);
	    	} catch(Throwable e) {
	    		System.out.println(message);
	    		addVerificationFailure(e);
	    	}
	    }
	    
	    public static void verifyFalse(boolean condition, String message) 
	    {
	    	try {
	    		
	    		Assert.assertFalse(condition, message);
	    	} catch(Throwable e) 
	    	{
	    		System.out.println(message);
	    		addVerificationFailure(e);
	    	}
	    }
	    

		
		
}
