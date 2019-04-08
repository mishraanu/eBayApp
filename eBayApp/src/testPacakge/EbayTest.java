package testPacakge;

import static org.testng.Assert.assertTrue;

import java.util.HashMap;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import basePackage.BaseTest;
import basePackage.BasicUserActions;
import basePackage.BasicUserActions.elementOfEbay;
import basePackage.BasicUserActions.findType;
import basePackage.BasicUserActions.loadingType;
import basePackage.BasicUserActions.swipeType;

public class EbayTest extends BaseTest 
{
	BasicUserActions basicUserActions;
	
	@Test(description = "To create object.")
	public void initObject () throws Exception
	{
		basicUserActions = new BasicUserActions();
		logger.info("object basicUserActions");
	}

	
	
	@Test(description = "verify Launch screen one of eBay Android app and navigate to Login screen.",dataProvider = "globaldataprovider")
	public void verifyEbayAppLaunchScreen (HashMap<String, String> dataMap) throws Exception
	{
		basicUserActions.waitForCheckLoadingGone(loadingType.SECTIONLOADING);
		System.out.println("Verifying Home screen");
		
		verifyTrue(BasicUserActions.isElementVisible(findType.ID,"com.ebay.mobile:id/logo"),"[Msg info : Home screen is not displaying");	
	}
	
	@Test(description = "verify login functionality for eBya App.",dataProvider = "globaldataprovider")
	public void verifyLoginFunctionalityForeBayApp (HashMap<String, String> dataMap) throws Exception
	{
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
	
	
	@Test(description = "verify search functionality for eBya App.",dataProvider = "globaldataprovider")
	public void verifySearchFunctionalityForeBayApp (HashMap<String, String> dataMap) throws Exception
	{
	
		verifyTrue(basicUserActions.enterTextResultAndSelect(dataMap.get("itemSearchText"),dataMap.get("itemSearchOption")),"[Msg-Failure] : List view data was incorrect");	
	}
	

	@SuppressWarnings("static-access")
	@Test(description = "verify select item functionality for cart for eBya App.",dataProvider = "globaldataprovider")
	public void verifySelectItemFunctionalityForeBayApp (HashMap<String, String> dataMap) throws Exception
	{
		BasicUserActions.clickOnElement(findType.ID,"com.ebay.mobile:id/button_sort");	
		verifyTrue(basicUserActions.verifySortOptionAndSelect(),"[Msg-Failure] : List view data was incorrect");
		BasicUserActions.tapOnElement(elementOfEbay.SORTOPTION1,null);
		basicUserActions.waitForCheckLoadingGone(loadingType.SECTIONLOADING);
		
		//selecting LG prouduct 
		BasicUserActions.clickOnElement(findType.XPATH,"//android.support.v7.widget.RecyclerView/android.widget.FrameLayout[2]");
		verifyTrue(basicUserActions.verifyAndSelectItemFromEbayList(dataMap.get("productTitle"),dataMap.get("productPrise")),"[Msg-Failure] :Procut data was incorrect");
		Thread.sleep(2000);
		basicUserActions.swipeOnScreen(42,874,swipeType.BTT,200);
		basicUserActions.swipeOnScreen(42,874,swipeType.BTT,200);
	
		
		//Adding item to cart
		BasicUserActions.clickOnElement(findType.ID,"com.ebay.mobile:id/button_add_to_cart");	

	}

	
	@Test(description = "verify checkout item functionality for cart for eBya App.",dataProvider = "globaldataprovider")
	public void verifyCheckoutFunctionalityForeBayApp (HashMap<String, String> dataMap) throws Exception
	{
		BasicUserActions.enterTextInElement(findType.ID,"com.ebay.mobile:id/edit_text_username", dataMap.get("loginName"));
		BasicUserActions.enterTextInElement(findType.ID,"com.ebay.mobile:id/edit_text_password", dataMap.get("passsword"));
		
		BasicUserActions.clickOnElement(findType.ID,"com.ebay.mobile:id/button_sign_in");
		basicUserActions.waitForCheckLoadingGone(loadingType.SECTIONLOADING);
		BasicUserActions.clickOnElement(findType.ID,"com.ebay.mobile:id/button_google_deny");
		basicUserActions.waitForCheckLoadingGone(loadingType.SECTIONLOADING);
		verifyTrue(BasicUserActions.isElementVisible(findType.ID,"com.ebay.mobile:id/logo"),"[Msg info : Home screen is not displaying");
		
		//Verifying options 
		verifyTrue(BasicUserActions.isElementVisible(findType.ACCID,"Buy It Now"),"[Msg info : Buy it now option is not displaying]");
		verifyTrue(BasicUserActions.isElementVisible(findType.ID,"com.ebay.mobile:id/button_view_in_cart"),"[Msg info : View in cart option is not displaying");
		verifyTrue(BasicUserActions.isElementVisible(findType.ID,"com.ebay.mobile:id/button_watch"),"[Msg info : watch option is not displaying]");

		//Clicking on view in cart option
		BasicUserActions.clickOnElement(findType.ID,"com.ebay.mobile:id/button_view_in_cart");
		
		verifyTrue(BasicUserActions.isElementVisibleByName(elementOfEbay.ITEMINCART,dataMap.get("productTitle")),"[Msg info : Item name is not displaying in checkout screen");
		verifyTrue(BasicUserActions.isElementVisibleByName(elementOfEbay.ITEMPRISE,dataMap.get("productPrise")),"[Msg info : Item Price is not displaying in checkout screen");
		
		//Tapping on Checkout option
		BasicUserActions.clickOnElement(findType.ID,"com.ebay.mobile:id/shopping_cart_button_layout");
	

	}
	

	
	
}
