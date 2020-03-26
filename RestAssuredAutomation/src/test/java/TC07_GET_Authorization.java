
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC07_GET_Authorization {
	
	@Test
	public void AuthorizationTest()
	{
		//specify base uri
		RestAssured.baseURI = "http://restapi.demoqa.com/authentication/CheckForAuthentication";
		
		PreemptiveBasicAuthScheme authScheme =  new PreemptiveBasicAuthScheme();
		authScheme.setUserName("ToolsQA");
		authScheme.setPassword("TestPassword");
		
		RestAssured.authentication = authScheme;
		
		
		//Request Object
		RequestSpecification httpRequest = RestAssured.given();
		
		//Response Object
		Response response = httpRequest.request(Method.GET,"/");
		
		//printing response
		String responseBody =response.getBody().asString();
		System.out.println("Response from API--->"+responseBody);
		
		//status code
		int statusCode  = response.statusCode();
		Assert.assertEquals(statusCode, 200);
	}

}
