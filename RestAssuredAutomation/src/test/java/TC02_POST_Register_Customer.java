
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC02_POST_Register_Customer {
	
	@Test
	public void RegisterCustomer()
	{
		//specify base uri
		RestAssured.baseURI= "http://restapi.demoqa.com/customer";
		
		//Request Object
		RequestSpecification httpRequest = RestAssured.given();
		
		//RequestPayload along with POST Request
		JSONObject requestParams= new JSONObject();
		requestParams.put("FirstName", "aj1");
		requestParams.put("LastName", "aj2");
		requestParams.put("UserName", "arjun55890");
		requestParams.put("Password", "arjun55890");
		requestParams.put("Email", "arjun55890@gmail.com");
		
		//Headers in POST Request
		httpRequest.header("Content-Type","application/json");
		
		//Whatever values specified in the above request params we need to convert to JSON format
		httpRequest.body(requestParams.toJSONString());
		
		//we have to send actual request now
		Response response= httpRequest.request(Method.POST,"/register");
		
		
		//Pritnig the reposne
		String responseBody = response.getBody().asString();
		System.out.println("Response from API--->"+responseBody);
		
		//status code validation
		int statusCode = response.statusCode();
		System.out.println("Status code--->"+statusCode);
		Assert.assertEquals(statusCode, 201);
		
		
		//Success code validation
		String successCode = response.jsonPath().get("SuccessCode");
		System.out.println("SuccessCOde--->"+successCode);
		Assert.assertEquals(successCode, "OPERATION_SUCCESS");
		
		
		
		
		
		
		
	}

}
