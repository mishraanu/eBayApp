package utility;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;


public class XMLDataReader 
{
	
	public HashMap<String, String> getScenarioData(String scenarioName) throws ParserConfigurationException, SAXException, IOException
	{
		HashMap<String, String> dataMap;
		 String xmlFile = "Resources/ScenarioData.xml";
		
		XMLDataHandler handler = new XMLDataHandler(scenarioName);
		SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(false);
        SAXParser parser = factory.newSAXParser();        
     	
        parser.parse(new File(xmlFile), handler);
        dataMap = handler.getScenarioData();
		
		return dataMap;
	}

}
