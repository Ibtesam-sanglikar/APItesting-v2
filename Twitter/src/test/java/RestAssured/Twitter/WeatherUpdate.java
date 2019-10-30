package RestAssured.Twitter;
import static io.restassured.RestAssured.given;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class WeatherUpdate extends baseClass{
	Properties prop=new Properties();
	  @BeforeTest
	  public void init() throws IOException {
	  FileInputStream fis=new FileInputStream("C:\\AG\\Twitter\\src\\test\\java\\RestAssured\\Twitter\\Data.properties");
	  prop.load(fis);
	  log=Logger.getLogger(WeatherUpdate.class);
	  }
	
  @Test
  public void run() {
	  String consumer_key=prop.getProperty("consumer_key");
      String consumer_secret=prop.getProperty("consumer_secret");
      String token=prop.getProperty("token");
      String token_secret=prop.getProperty("token_secret");
	  RestAssured.baseURI=prop.getProperty("displayname_uri");
	  
	  log.info("fetching all banglore weather related tweets");
	  Response res=given().auth().oauth(consumer_key,consumer_secret,token,token_secret).queryParam("q", "#weather").queryParam("q", "#banglore").when()
			       .get("tweets.json").then().extract().response();
	  String response=res.asString();
	  System.out.println(response);
	  JsonPath js=new JsonPath(response);
	  List<Object> l=js.getList("statuses");
      int i=0;
      while(i<l.size())
      {
         System.out.println("---------------for tweet "+i+"---------------");
         String text=js.get("statuses["+i+"].text").toString();
         System.out.println("tweet text:"+text);
         System.out.println("\n");
         i++;
      }	    
  }
}