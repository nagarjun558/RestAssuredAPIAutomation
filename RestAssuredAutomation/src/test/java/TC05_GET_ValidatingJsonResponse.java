import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC05_GET_ValidatingJsonResponse {
	@Test
	public void validatingJsonResponseWeatherAPI()
	{
		//specify base uri
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		
		//Request Object
		RequestSpecification httpRequest = RestAssured.given();
		
		//Response Object
		Response response= httpRequest.request(Method.GET,"/Delhi");
		
		//Pritnig the response
		String responseBody = response.getBody().asString();
		System.out.println("Response from API---->"+responseBody);
		
		Assert.assertEquals(responseBody.contains("Delhi"), true);
	}
	
}
