package basePackage;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import utility.CustomeLogger;





public class BasicUserActions extends BaseTest
{
	

public  enum findType
{
	XPATH, NAME, ID, ACCID, CLASS
}

public  enum elementType
{
	CHECKBOX, RADIO, TOGGLE
}

public  enum compareType
{
	EQUALS, EQUALSIGNORECASE, CONTAINS
}


public  enum swipeType
{
	BTT, TTB, LTR, RTL
}



public  enum attributeType
{
	TEXT, VALUE
}



public  enum screenOrientationMode

{
     LANDSCAPEMODE, PORTRAITMODE
}


public  enum loadingType
{
	SECTIONLOADING
}


public  enum elementOfEbay
{
	SORTOPTION2, SORTOPTION1,SORTOPTION3,SORTOPTION4,SORTOPTION5
, ITEMINLIST, ITEMINCART, ITEMPRISE}

public static WebElement sortOption1 =null;
public static WebElement sortOption2 =null;
public static WebElement sortOption3 =null;
public static WebElement sortOption4 =null;
public static WebElement sortOption5 =null;



public static WebDriverWait wait=new WebDriverWait(driver, 10);

//Default constructor
public BasicUserActions() throws Exception
{
	super();
}



//Method to verify display of element
	public static boolean isElementVisible(findType type, String locator) throws Exception
	{
		
		boolean isVisible = false;

		try
		{
			WebElement genericElement = null;
		

			logger.log("[Msg-Info] : Checking if an element is visible");

			switch(type)
			{
				case XPATH:
					genericElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
					break;
				case NAME:
					genericElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.name(locator)));
					break;
				case ID:
					genericElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(locator)));
					break;
				case ACCID:
					genericElement = wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId(locator)));
					break;
					
				default:
					break;
			}



			logger.log("[Msg-Result] Is Displayed : "+genericElement.isDisplayed());
			logger.log("[Msg-Result] Is Enabled : "+genericElement.isEnabled());

		
			if(genericElement.isDisplayed() || genericElement.isEnabled())
			{
				logger.log("[Msg-Success] : Element is visible");
				isVisible = true;
			}
		}
		catch(NoSuchElementException e)
		{
			logger.log("[Msg-Info] : Element is not visible");
			
		}
		catch(Exception e)
		{
			logger.error(Level.WARNING, e.getMessage(),e);
		}
		return isVisible;
	}
	
	
	
	
	//Method to click on element
	public static void clickOnElement(findType type, String locator) throws Exception
	{
		try
		{

			WebElement genericElement = null;
			
			logger.log("[Msg-Info] : Tapping on element...");
			
			switch(type)
			{
				case XPATH:
					genericElement = driver.findElement(By.xpath(locator));
					break;
					
				case NAME:
					genericElement = driver.findElement(By.name(locator));
					break;
				case ID:
					genericElement = driver.findElement(By.id(locator));
					break;
				case ACCID:
					genericElement = driver.findElement(MobileBy.AccessibilityId(locator));
					break;
					
					
				default:
					break;
			}

			genericElement.click();

		}
		catch(Exception e)
		{
			logger.error(Level.WARNING, e.getMessage(), e);
		}
	}
	
	
	
	//Method to enter text in element
	public static void enterTextInElement(findType type, String locator, String textToEnter) throws Exception
	{
		
		try
		{
			WebElement genericElement = null;
			 
			
	
			logger.log("[Msg-Info] : Entering Text in a text field");
	
			switch(type)
			{
				case XPATH:
					genericElement = driver.findElement(By.xpath(locator));
					break;
				case NAME:
					genericElement = driver.findElement(By.name(locator));
					break;
				case ID:
					genericElement = driver.findElement(By.id(locator));
					break;
				default:
					break;
			}

			
			//Clearing text
			try
			{
				if(genericElement.getText().length()>0)
				{
					logger.log("[Msg-Info] : Looks like there is some text present already, clearing that text...");
					genericElement.clear();
				}
			}
			catch(NullPointerException e)
			{
				logger.log("[Msg-Info] : Text field is empty");
			}

			
			//entering text
			try
			{
				logger.log("Entering text in :"+textToEnter);
				genericElement.sendKeys(textToEnter);
			}
			catch(WebDriverException e)
			{
				logger.log("[Msg-Info] : In catch");
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				
				genericElement.clear();
				genericElement.sendKeys(textToEnter);
			}
							
			
		}
		catch(Exception e)
		{
			logger.error(Level.WARNING, e.getMessage(), e);
		}
	}
	
	
	//Method to swipe using hash map
	public static void swipeOnScreen(String direction) throws Exception
	{
		try
		{
			logger.log("[Msg-Info] : Swiping "+direction+" on the screen");
			
			
			
			MobileElement element = (MobileElement) driver.findElement(By.id("com.ebay.mobile:id/highlights_vertical_separator1")); 
			JavascriptExecutor js = (JavascriptExecutor)driver; 
			HashMap<String, String> scrollObject = new HashMap<String, String>(); 
			scrollObject.put("direction", "up"); 
			scrollObject.put("element", ((RemoteWebElement) element).getId());
			js.executeScript("mobile: scrollTo", scrollObject);
			
		}
		catch(Exception e)
		{
			logger.error(Level.WARNING, e.getMessage(), e);
		}
	}
	public static TouchAction action = new TouchAction(driver);
	
	//Method to swipe elements from left or right side of the screen on iPhone view.
	// x=73 y=113 offset=300
	public static void swipeOnScreen (int cordX, int cordY , swipeType stype, int offset) throws Exception
	{
		try
		{
			
			

			logger.log("[Msg-Info] : Swiping on the screen");	
			switch(stype)
			{
				case BTT:
					//driver.swipe(cordX, cordY, cordX, cordY-offset, 10);
					
				    action.press(cordX,cordY).waitAction(600).moveTo(cordX,cordY-offset).release().perform();
					break;
				case TTB:
					//driver.swipe(cordX, cordY, cordX, cordY+offset, 10);
					
					action.press(cordX,cordY).waitAction(600).moveTo(cordX,cordY+offset).release().perform();
					break;
				case LTR:
					//driver.swipe(cordX, cordY, cordX+offset, cordY, 10);
					
				    action.press(cordX,cordY).waitAction(600).moveTo(cordX+offset,cordY).release().perform();
					break;
				case RTL:
					//driver.swipe(cordX, cordY, cordX-offset, cordY, 10);
					
				    action.press(cordX,cordY).waitAction(600).moveTo(cordX-offset,cordY).release().perform();
					break;
					
				default:
					break;
			}
			
			
		}
		catch(Exception e)
		{
			
			logger.error(Level.WARNING, e.getMessage(), e);
		}
	}
	

		
	//Method to Swipe 
	// BTT - Bottom to Top
	// TTB - Top to Bottom
	// LTR - Left to Right
	// RTL - Right to Left
	public static void swipeOnScreen(findType ftype, String locator, swipeType stype, int offset) throws Exception
	{
		try
		{
			
			logger.log("[Msg-Info] : Swiping on the screen");
			
			WebElement genericElement = null;
			
			switch(ftype)
			{
				case XPATH:
					genericElement = driver.findElement(By.xpath(locator));
					break;
				case NAME:
					genericElement = driver.findElement(By.name(locator));
					break;
				case CLASS:
					genericElement = driver.findElement(By.className(locator));
					break;
				case ID:
					genericElement = driver.findElement(By.id(locator));
					break;
				case ACCID:
					genericElement = driver.findElement(MobileBy.AccessibilityId(locator));
					break;
				default:
					break;
			}

			logger.log("[Msg-Info] : Swiping ..."); 

			int x = genericElement.getLocation().getX();
			int y = genericElement.getLocation().getY();
			
			logger.log("x cord - "+x);
			logger.log("y cord - "+y);

			logger.log("[Msg-Info] : Position : "+x+" , "+y);
			
			
			switch(stype)
			{
				case BTT:
					driver.swipe(x, y, x, y-offset, 10);
					break;
				case TTB:
					driver.swipe(x, y, x, y+offset, 10);
					break;
				case LTR:
					driver.swipe(x, y, x+offset, y, 10);
					break;
				case RTL:
					driver.swipe(x, y, x-offset, y, 10);
					break;
				default:
					break;
			}
			
			
		}
		catch(Exception e)
		{
			logger.error(Level.WARNING, e.getMessage(), e);
		}
	}
	
	
	
	//Method to verify text on element
	public static boolean isTextOfElementCorrect(findType type, String locator, String textToVerify, compareType compType) throws Exception
	{
	
		boolean isVisible = false;

		try
		{

			WebElement genericElement = null;
	

			
			logger.log("[Msg-Info] : Checking if text on an element is correct");

			switch(type)
			{

				case XPATH:
					genericElement = driver.findElement(By.xpath(locator));
					break;
				case NAME:
					genericElement = driver.findElement(By.name(locator));
					break;
				case ID:
					genericElement = driver.findElement(By.id(locator));
					break;
				case ACCID:
					genericElement = driver.findElement(MobileBy.AccessibilityId(locator));
					break;
				default:
					break;
			}

			
			
			String displaydText;
			
			
			//Getting text of an element --> either by text or by value
			try
			{
				//logger.log("[Displayed] : "+genericElement.getText().trim());
				displaydText = genericElement.getText().trim();
				if(displaydText.equals(""))
				{
					displaydText = genericElement.getAttribute("value").trim();
				}
			}
			catch(NullPointerException e)
			{
				displaydText = genericElement.getAttribute("name").trim();
			}

			

			logger.log("[Displayed] : "+displaydText);
			logger.log("[Expected]  : "+textToVerify.trim());
			
			if(compType == compareType.EQUALS)
			{
				if(displaydText.equals(textToVerify.trim()))
				{
					logger.log("[Msg-Success] : Element is visible with expected Text");
					isVisible = true;
				}
			}
			
			if(compType == compareType.CONTAINS)
			{
				if(displaydText.contains(textToVerify))
				{
					logger.log("[Msg-Success] : Element is visible with expected Text");
					isVisible = true;
				}
			}

			if(compType == compareType.EQUALSIGNORECASE)
			{
				if(displaydText.equalsIgnoreCase(textToVerify))
				{
					logger.log("[Msg-Success] : Element is visible with expected Text");
					isVisible = true;
				}
			}
		}
		catch(NoSuchElementException e)
		{
			logger.log("[Msg-Info] : Element is not visible");
		}
		catch(Exception e)
		{
			logger.error(Level.WARNING, e.getMessage(), e);
		}
		
		return isVisible;
	}
	
	
	//Method to hide keyboard using keyboard down key
    public static void hideKeyboardByDownKey() throws Exception
    {
    		try 
    		{
    			logger.log("[Msg-Info] : Hiding keyboard....");
    			driver.hideKeyboard();
    		} 
    		catch (Exception e) 
    		{
    			logger.log( "[Msg-Info] : Not able to hide keyboard");
    			logger.error(Level.WARNING, e.getMessage(), e);
    		}
    }
    
	//Method to wait till loading icon displaying on page
	public void waitForCheckLoadingGone(loadingType type)  throws Exception
	{
		
			System.out.println("[Msg-Info] : Checking whether loading icon is visible.");
			WebElement loadingElement =null ;
			try
			{
			switch(type)
			  {	
				 case SECTIONLOADING:
					loadingElement = driver.findElementByClassName("android.widget.ProgressBar");
					break; //section loader

											

				 default:
					 break;
				}
			
			    while(loadingElement.isDisplayed() || loadingElement.isEnabled())
				{
			    	System.out.println("[Msg-Info] : Waiting for data to get load...");
					
				}
	       }
		
			catch (NoSuchElementException | StaleElementReferenceException nse) 
			{
				System.out.println("[Msg-Info] : Loading Icon is not visible. Moving ahead!!");
			}
			
		}
	

	//Method to verify search search options
	public boolean enterTextResultAndSelect(String expSearchText, String expText) throws Exception
	{
		
			boolean searchResultDisplayed = false;
			System.out.println("[Msg-Info] : Searching text from eBay search");
			
			try
				{
				    BasicUserActions.clickOnElement(findType.ID,"com.ebay.mobile:id/search_box");
				    
				    BasicUserActions.enterTextInElement(findType.ID,"com.ebay.mobile:id/search_src_text",expSearchText);
				    waitForCheckLoadingGone(loadingType.SECTIONLOADING);
				    
				    
				    WebElement displayedSearchResult = driver.findElement(By.xpath("//android.widget.ListView/android.widget.RelativeLayout[1]/android.widget.TextView"));
				    
					System.out.println("Displayed :"+displayedSearchResult.getText().trim());
 				    System.out.println("[Msg-Info] : Clicking on search option");
					displayedSearchResult.click();
					    	
					waitForCheckLoadingGone(loadingType.SECTIONLOADING);
				    WebElement displayedSearchResultOnHomeScreen = driver.findElement(By.xpath("//android.widget.TextView[contains(@content-desc,'Current search: ')]"));
						    
				    String displayedSearchResultText = displayedSearchResultOnHomeScreen.getText();
					if(displayedSearchResultText.equalsIgnoreCase(expSearchText))
					  {

						    	System.out.println("[Msg-Info] : Selected search category displaying on result screen");
						    	
						    	searchResultDisplayed = true;
						}

					  				}
				catch(NoSuchElementException e)
				{
					System.out.println("[Msg-Info] : No result found as expected");
				}
			return searchResultDisplayed;
	}

	
	
	//Method to verify sort options present in list and select expected one
	public boolean verifySortOptionAndSelect() throws Exception
	{
		
			boolean isSortOptionsPresent = false;
			System.out.println("[Msg-Info] : Verifying sort Options");	
			
			
		

				  sortOption1 = driver.findElement(By.xpath("//android.widget.ListView/android.widget.LinearLayout[1]/android.widget.CheckedTextView"));
				 
				  String option1 = sortOption1.getText().trim();
				  System.out.println("Displayed :"+option1);
				  
				  sortOption2 = driver.findElement(By.xpath("//android.widget.ListView/android.widget.LinearLayout[2]/android.widget.CheckedTextView"));
				  String option2 = sortOption2.getText().trim();
				  System.out.println("Displayed :"+option2);
					 
				  sortOption3 = driver.findElement(By.xpath("//android.widget.ListView/android.widget.LinearLayout[3]/android.widget.CheckedTextView"));
				  String option3 = sortOption3.getText().trim();
				  System.out.println("Displayed :"+option3);
					 
				  sortOption4 = driver.findElement(By.xpath("//android.widget.ListView/android.widget.LinearLayout[4]/android.widget.CheckedTextView"));
				  String option4 = sortOption4.getText().trim();
				  System.out.println("Displayed :"+option4);
					 
				  sortOption5 = driver.findElement(By.xpath("//android.widget.ListView/android.widget.LinearLayout[5]/android.widget.CheckedTextView"));
				  String option5 = sortOption5.getText().trim();
				  System.out.println("Displayed :"+option5);
					 
				  
				  if(option1.equals("Best Match") && option2.equals("Lowest Price + Shipping")
					 && option3.equals("Highest Price + Shipping") && option4.equals("Ending Soonest") && option5.equals("Newly Listed"))
				  {
					  System.out.println("[Msg-Info] : All sorting options displaying");

					  isSortOptionsPresent=true;
				  }
				
		return isSortOptionsPresent;
    }
	
	
	 public static void tapOnElement(elementOfEbay type, String expText) throws Exception
	 {
		 try
		 {
			
			 System.out.println("[Msg-Info] : Tapping on : "+type);
			 
			 switch(type)
			 {
			 	case SORTOPTION1:			 		
			 		sortOption1.click();
			 		break;
			 		
			 	case SORTOPTION2:
			 		sortOption2.click();
			 		break;
			 		
			 	case SORTOPTION3:
			 		sortOption3.click();
			 		break;
			 		
			 	case SORTOPTION4:
			 		sortOption4.click();
			 		break;
			 		
			 	case SORTOPTION5:
			 		sortOption5.click();
			 		break;
			 		
			 	case ITEMINLIST:
			 	productNameInList.click();

			 	default:
			 		break;
			 }
		 }
		 catch(Exception e)
		 {
				System.out.println(e);	
		 }
	 }


	public static WebElement productNameInList = null;
	
		@SuppressWarnings("unchecked")
		public boolean verifyAndSelectItemFromEbayList (String expPatName,String expRate) throws Exception
		{
			boolean isCorrect = false;
				
				
				System.out.println("[Msg-Info] : Verifying patient's data in patinets list.");
				
				try 
				{
					
					List<WebElement> shoppingListItem = driver.findElements(By.xpath("//android.support.v7.widget.RecyclerView/android.widget.RelativeLayout"));  
					System.out.println("[Msg-Info] : Number of records displayed in list : "+shoppingListItem.size());
					
					for(WebElement listItem : shoppingListItem)
					{
	
				    productNameInList = driver.findElement(By.xpath("//android.support.v7.widget.RecyclerView/android.widget.RelativeLayout[2]/android.widget.LinearLayout/android.widget.TextView[1]"));
				    System.out.println("[Displayed] : "+productNameInList.getText().trim());
				    System.out.println("[Expected]  : "+expPatName);				
					
				    WebElement productRateInList = driver.findElement(MobileBy.AccessibilityId(expRate));
				    System.out.println("[Displayed] : "+productRateInList.getText().trim());
				    System.out.println("[Expected]  : "+expRate);	

					if(productNameInList.getText().trim().contains(expPatName) || productRateInList.getText().trim().equals(expRate))
					{
					    	System.out.println("[Msg-Info] : Expected data displaying in list.");
					    	isCorrect = true;
					 }

					 
					
					if (isCorrect) 
					{
						System.out.println("[Msg-info] Selecting product "+expPatName);
						productRateInList.click();
						break;
					}
					
					}//end of for
				}
					catch (Exception e) 
				{
					System.out.println("[Got the error : "+e);
				}
				return isCorrect;
						
		}

		//Method to verify if element is visible on screen
		@SuppressWarnings("unchecked")
		public static boolean isElementVisibleByName(elementOfEbay type, String expText) throws Exception
		{
			boolean isDisplayed = false;
			try
			{
				
				logger.log("[Msg-Info] :  Verifying presence of : "+type);
				
				switch(type)
				{
					case ITEMINCART:
						
						WebElement itemInCartThumnail = driver.findElementById("com.ebay.mobile:id/item_thumbnail");
						WebElement itemInCartName = driver.findElementByXPath("//android.widget.ImageView/following::android.widget.TextView");
						
						String displayedItemInCartName = itemInCartName.getText().trim();
						System.out.println("Displayed : "+displayedItemInCartName);
						System.out.println("Expcected: "+expText);
						
						if((itemInCartThumnail.isDisplayed()) && (displayedItemInCartName.contains(expText)))
						{
							 isDisplayed = true;
						}
						break;
					case ITEMPRISE:
						WebElement itemPrise = driver.findElementById("com.ebay.mobile:id/item_price");
						String displayedItemInPrise = itemPrise.getText().trim();
						System.out.println("Displayed : "+displayedItemInPrise);
						System.out.println("Expcected: "+expText);
						
						if(displayedItemInPrise.contains(expText))
						{
							 isDisplayed = true;
						}
						break;
						
						
				
					   default:
						   break;
					}
				}
				catch (Exception e) 
				{
					logger.error(Level.WARNING, e.getMessage(), e);
					
				}
				
				if(isDisplayed)
					logger.log("[Msg-Info] :  Element "+type+" is visible on screen");

				return isDisplayed;
			}

}
