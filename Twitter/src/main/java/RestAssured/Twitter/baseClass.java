package RestAssured.Twitter;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class baseClass {
	
	public static Logger log=null; 
	public baseClass() {
	PropertyConfigurator.configure("C:\\AG\\Twitter\\resource\\log4j.properties");
	}

}
