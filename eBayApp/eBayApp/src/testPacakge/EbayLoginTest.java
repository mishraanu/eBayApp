package testPacakge;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import basePackage.BaseTest;
import basePackage.BasicUserActions;
import basePackage.BasicUserActions.findType;
import basePackage.BasicUserActions.loadingType;

public class EbayLoginTest extends BaseTest
{
     BasicUserActions basicUserActions;
     Logger logger = Logger.getLogger("devpinoyLogger");
	
	@Test(description = "To create object.")
	public void initEbayLoginTest () throws Exception
	{
		basicUserActions = new BasicUserActions();
		logger.debug("object basicUserActions");
	}

	
	
	@Test(description = "verify Launch screen one of eBay Android app and navigate to Login screen.",dataProvider = "globaldataprovider")
	public void verifyEbayAppLaunchScreen (HashMap<String, String> dataMap) throws Exception
	{
		System.out.println("--------------------------------------------------------");
		basicUserActions.waitForCheckLoadingGone(loadingType.SECTIONLOADING);
		System.out.println("Verifying Home screen");
		
		verifyTrue(BasicUserActions.isElementVisible(findType.ID,"com.ebay.mobile:id/logo"),"[Msg info : Home screen is not displaying");	
	}

	@Test(description = "verify login functionality for eBya App.",dataProvider = "globaldataprovider")
	public void verifyLoginFunctionalityForeBayApp (HashMap<String, String> dataMap) throws Exception
	{
		System.out.println("--------------------------------------------------------");
		System.out.println("Verifying Login screen");
		BasicUserActions.clickOnElement(findType.ID,"com.ebay.mobile:id/home");
		verifyTrue(BasicUserActions.isElementVisible(findType.ID,"com.ebay.mobile:id/textview_sign_out_status"),"[Msg info :Sign in option is not displaying");	
		BasicUserActions.clickOnElement(findType.ID,"com.ebay.mobile:id/textview_sign_out_status");
		
		BasicUserActions.enterTextInElement(findType.ID,"com.ebay.mobile:id/edit_text_username", dataMap.get("loginName"));
		BasicUserActions.enterTextInElement(findType.ID,"com.ebay.mobile:id/edit_text_password", dataMap.get("passsword"));
		
		BasicUserActions.clickOnElement(findType.ID,"com.ebay.mobile:id/button_sign_in");
		basicUserActions.waitForCheckLoadingGone(loadingType.SECTIONLOADING);
		BasicUserActions.clickOnElement(findType.ID,"com.ebay.mobile:id/button_google_deny");
		basicUserActions.waitForCheckLoadingGone(loadingType.SECTIONLOADING);
		verifyTrue(BasicUserActions.isElementVisible(findType.ID,"com.ebay.mobile:id/logo"),"[Msg info : Home screen is not displaying]");	
	}

}
