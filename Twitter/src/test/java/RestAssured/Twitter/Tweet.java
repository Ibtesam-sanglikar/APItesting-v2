package RestAssured.Twitter;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Tweet extends baseClass{	 
	  Properties prop=new Properties();
	  @BeforeTest
	  public void init() throws IOException {
	  FileInputStream fis=new FileInputStream("C:\\AG\\Twitter\\src\\test\\java\\RestAssured\\Twitter\\Data.properties");
	  prop.load(fis);
	  log=Logger.getLogger(Tweet.class);
	  }
	  
	  
  @Test
  public void run(){
    String consumer_key=prop.getProperty("consumer_key");
    String consumer_secret=prop.getProperty("consumer_secret");
    String token=prop.getProperty("token");
    String token_secret=prop.getProperty("token_secret");
	
    log.info("posting a tweet ----------");
    RestAssured.baseURI=prop.getProperty("tweet_uri");  
    Response res=given().auth().oauth(consumer_key,consumer_secret,token,token_secret).queryParam("status", "i am excited to learn trending technologies #qualitest").when().
    post("/update.json").then().assertThat().statusCode(200).extract().response();
    String response=res.asString();
    JsonPath js=new JsonPath(response);
    System.out.println("response is:");
    System.out.println(response);
    String user_id=js.get("id").toString();
    String message=js.get("text").toString();
    System.out.println("tweet text ="+message+"\t"+"\n tweeted with user_id:"+user_id);
    
  }
}
