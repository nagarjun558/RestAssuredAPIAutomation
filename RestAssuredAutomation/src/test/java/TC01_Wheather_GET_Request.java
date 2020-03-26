import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC01_Wheather_GET_Request {
	
	@Test
	public void getWeatherDetails()
	{
		//specify base uri
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		
		//Request Object
		RequestSpecification httpRequest = RestAssured.given();
		
		//Response Object
		Response response= httpRequest.request(Method.GET,"/Hyderabad");
		
		//printing the response
		String responseBody = response.getBody().asString();
		System.out.println("Response from API -->"+responseBody);
		
		//get the status code
		int statusCode = response.statusCode();
		System.out.println("Status code--->"+statusCode);
		Assert.assertEquals(statusCode, 200);
		
		//getStatusLine
		String statusLine = response.getStatusLine();
		System.out.println("Status Line--->"+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
		
	}

}
