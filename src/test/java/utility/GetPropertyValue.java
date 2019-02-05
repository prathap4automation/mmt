package utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class GetPropertyValue {
	String result = "";
	FileInputStream inputStream;
	
	public String getPropValue(String name) throws IOException
	{
		try {
			Properties prop = new Properties();
			String propFileName = System.getProperty("user.dir")+"/src/test/resources/config.properties";
 
			inputStream = new FileInputStream(propFileName); 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
			result = prop.getProperty(name);
//			System.out.println(result);
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		return result;
	}
}
