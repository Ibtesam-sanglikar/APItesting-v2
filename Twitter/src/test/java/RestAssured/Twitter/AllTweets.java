package RestAssured.Twitter;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class AllTweets extends baseClass{
	Properties prop=new Properties();
	  @BeforeTest
	  public void init() throws IOException {
	  FileInputStream fis=new FileInputStream("C:\\AG\\Twitter\\src\\test\\java\\RestAssured\\Twitter\\Data.properties");
	  prop.load(fis);
	  log=Logger.getLogger(AllTweets.class);
	  }
		
	  @Test
	  public void run() {	
		    String consumer_key=prop.getProperty("consumer_key");
		    String consumer_secret=prop.getProperty("consumer_secret");
		    String token=prop.getProperty("token");
		   
		    String token_secret=prop.getProperty("token_secret");
	        RestAssured.baseURI=prop.getProperty("tweet_uri"); 
	        Response res=given().auth().oauth(consumer_key,consumer_secret,token,token_secret).
	               queryParam("screen_name","ibtesamsanglik1").
		           when().get("user_timeline.json").then().extract().response();
		       
	         String s=res.asString();        
	         System.out.println(s);
	         JsonPath js=new JsonPath(s);
	         log.info("fetching all tweets of user : "+js.get("user[0].name").toString());
	         List<Object> l=js.getList("text");
             int i=0;
             System.out.println("all tweets of tweeted by  "+js.get("user[0].name").toString());
             while(i<l.size())
             {
            	System.out.println(l.get(i));
                System.out.println("\n");
                i++;
             }
	 }
	}