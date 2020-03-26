
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC03_GET_GoogleMapAPI {
	
	@Test
	public void googleMapTest()
	{
		//specify base uri
		RestAssured.baseURI = "https://maps.googleapis.com";
		
		//Request Object
		RequestSpecification httpRequest = RestAssured.given();
		
		//Response Object
		Response response= httpRequest.request(Method.GET,"/maps/api/place/nearbysearch/xml?location=-33.8670522,151.1957362&radius=1500&type=supermarket&key=AIzaSyBjGCE3VpLU4lgTqSTDmHmJ2HoELb4Jy1s");
		
		
		//pritnig the response
		String responseBody = response.getBody().asString();
		System.out.println("Response from API---->"+responseBody);
		
		
		//Capture details of headers
		String contetType = response.header("Content-Type");
		System.out.println("Contetn type from response-->"+contetType);
		Assert.assertEquals(contetType, "application/xml; charset=UTF-8");
		
		String contentEncoding =  response.header("Content-Encoding");
		System.out.println("Contetn encoding from response-->"+contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip");
		
		
		
		
		
		
	}

}
