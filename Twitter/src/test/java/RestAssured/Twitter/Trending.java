package RestAssured.Twitter;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Trending extends baseClass{
	Properties prop=new Properties();
	  @BeforeTest
	  public void init() throws IOException {
		  FileInputStream fis=new FileInputStream("C:\\AG\\Twitter\\src\\test\\java\\RestAssured\\Twitter\\Data.properties");
	      prop.load(fis);
	      log=Logger.getLogger(Trending.class);
	}
	   
	
    
      @Test
	  public void run() {
    	  String consumer_key=prop.getProperty("consumer_key");
          String consumer_secret=prop.getProperty("consumer_secret");
          String token=prop.getProperty("token");
          String token_secret=prop.getProperty("token_secret");
          String woeid[]= {"2295383","28218","23424977","23424852"};
    	  
		//to extract top 50 tweets of each country
		for(int i=0;i<4;i++)
		  {
			 RestAssured.baseURI=prop.getProperty("trends_uri");  
	         
			 Response res=given().auth().oauth(consumer_key,consumer_secret,token,token_secret).queryParam("id", woeid[i]).when().
	         get("place.json").then().assertThat().statusCode(200).extract().response();
	         String result=res.asString();
	         System.out.println(result);
	         JsonPath js=new JsonPath(result);
	         //to print the country name before tweets
	         if(i==0)
	         {
	        	 log.info("fetching trending hashtags of india----------"); 
	        	System.out.println("------INDIA-------");
	         }
	         else if(i==1)
	         {
	        	 log.info("fetching trending hashtags of UK----------");  
	        	 System.out.println("------UK-----");
	         }else if(i==2)
	         {
	        	log.info("fetching trending hashtags of US----------");  
	        	System.out.println("------US-----");
	         }
	         else if(i==3)
	         {
	        	 log.info("fetching trending hashtags of ISRAEL----------");  	
	        	 System.out.println("------ISRAEL-----");
	         }
	        
	         //extracting top 5 trending tweets
	         String hashtag=js.getString("trends.name");
	         for(int j=0;j<5;j++)
	          {
	            if(hashtag.startsWith("[[")||hashtag.startsWith("#"))
                {
	              String[] splitted=hashtag.split(",");
	              System.out.println(splitted[j]);
                }
              }
	          System.out.println("----------------------------------------");
	        }
      }
}