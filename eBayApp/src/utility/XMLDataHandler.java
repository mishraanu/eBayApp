/**
 * 
 */
package utility;

import java.util.HashMap;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;






public class XMLDataHandler extends DefaultHandler {

	 
	private String scenarioName;
	private boolean insideRequiredScenario;
	private HashMap<String,String> dataMap;
	
	
	
	XMLDataHandler(String scenarioName)
	{
		this.scenarioName = scenarioName;
		dataMap = new HashMap<>();
	}
	
	
	
	
	
	
	/**
	   <br>uri - The Namespace URI, or the empty string if the element has no Namespace URI or if Namespace processing is not being performed.<br>
       localName - The local name (without prefix), or the empty string if Namespace processing is not being performed.<br>
       qName - The qualified name (with prefix), or the empty string if qualified names are not available.<br>
       attributes - The attributes attached to the element. If there are no attributes, it shall be an empty Attributes object.<br>
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
	{
		
		if(qName.equalsIgnoreCase("scenario"))
		{
			if(attributes.getValue("name").equalsIgnoreCase(scenarioName))
				insideRequiredScenario = true;
			else if(attributes.getValue("name").equalsIgnoreCase("default"))
				insideRequiredScenario = true;
			else
				insideRequiredScenario = false;
		}
		
		if(qName.equalsIgnoreCase("testdata"))
		{
			if(insideRequiredScenario)
				dataMap.put(attributes.getValue("name"),attributes.getValue("value"));
				
		}
		
		
	}
	
	public HashMap<String, String> getScenarioData() {
	  
	   
	   
	   
		return dataMap;
	}

}
