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
import java.util.Properties;



public class BlockUser extends baseClass{
	  Properties prop=new Properties();
	  @BeforeTest
	  public void init() throws IOException {
	  FileInputStream fis=new FileInputStream("C:\\AG\\Twitter\\src\\test\\java\\RestAssured\\Twitter\\Data.properties");
	  prop.load(fis);
	  log=Logger.getLogger(BlockUser.class);
	  }
		
	  @Test
	  public void run() {
		    String consumer_key=prop.getProperty("consumer_key");
	        String consumer_secret=prop.getProperty("consumer_secret");
	        String token=prop.getProperty("token");
	        String token_secret=prop.getProperty("token_secret");
	        RestAssured.baseURI=prop.getProperty("block_uri");  
	        log.info("blocking user from replying or retweeting from this account");
	        Response res=given().auth().oauth(consumer_key,consumer_secret,token,token_secret).queryParam("screen_name", "@AmreenDarga").when().
	        post("create.json").then().assertThat().statusCode(200).extract().response();
	        String result=res.asString();
	        System.out.println(result);
	        JsonPath js=new JsonPath(result);  
	        String name=js.get("name").toString();
	        System.out.println("user::"+name+" is blocked");
	    }
}

