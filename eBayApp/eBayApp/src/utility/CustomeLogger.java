package utility;

import java.util.logging.Level;

import org.apache.log4j.Logger;
import org.testng.Reporter;

public class CustomeLogger 
{
	public static  Logger logger = Logger.getLogger("devpinoyLogger");
	
	
	
	
	public CustomeLogger()
	{
		 logger.debug("[Msg-Init] : Initializing the custom logger..");
	}

	//To print message on console 
	public  void log (String message)
	{		
		logger.debug(message);
		Reporter.log(message);
	}
	




	public void error(Level warning, String message, Exception e)
	{
		logger.error(message, e);
		Reporter.log(message);
		
	}
	}
