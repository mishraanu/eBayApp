package utility;


import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;



public class TestDataReader 
{
	
	String dataFilePath;
	HashMap<String, String> hmap;
	Properties p;
	Enumeration<?> e;
	String tDataKey;
	String tDataValue;

	public TestDataReader(String dataFilePath) 
	{
		

		try {

			/*System.out.println("Test data file name: " + this.dataFilePath);*/

			hmap = new HashMap<>();
			p = new Properties();
			p.load(new FileInputStream("resources/Locators.properties"));

		} catch (Exception Ex) 
		{
			Ex.printStackTrace();
		}

		e = p.propertyNames();
	}

	public HashMap<String, String> getDataMap() {
		while (e.hasMoreElements()) {
			tDataKey = (String) e.nextElement();
			tDataValue = p.getProperty(tDataKey);
			hmap.put(tDataKey, tDataValue);

		}

		return hmap;
	}

}
