package utility;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader{
	public static Properties pro;

	
	
	public PropertyReader() throws IOException{
	
	File src=new File("E:\\Automation\\WebDriver_Exercises\\LocaleBetaLogin\\src\\properties\\lang.properties");
	
	FileInputStream fis = new FileInputStream(src);
	pro=new Properties();
	pro.load(fis);
	
	
	}
	
	public String getLanguage(){
		return pro.getProperty("language");
	}
	
	public String getCountry(){
		return pro.getProperty("country");
	}
	
}
