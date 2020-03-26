import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC06_GET_VlaidateJSONAllValues {
	
	@Test
	public void validateJSONAllValues()
	{
		//specify base uri
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		
		//Request Object
		RequestSpecification httpRequest = RestAssured.given();
		
		//Response Object
		Response response= httpRequest.request(Method.GET,"/Delhi");
		
		/*//Printing the response
		String responseBody  = response.getBody().asString();
		System.out.println("Response from API---->"+responseBody);*/
		
		JsonPath jsonPath= response.jsonPath();
		System.out.println(jsonPath.get("City"));
		System.out.println(jsonPath.get("Temperature"));
		System.out.println(jsonPath.get("Humidity"));
		System.out.println(jsonPath.get("WeatherDescription"));
		System.out.println(jsonPath.get("WindSpeed"));
		System.out.println(jsonPath.get("WindDirectionDegree"));
		
		Assert.assertEquals(jsonPath.get("City"), "Delhi");
		Assert.assertEquals(jsonPath.get("Temperature"), "23 Degree celsius");
		
	}

}
