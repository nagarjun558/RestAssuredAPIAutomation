package DDTesting;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.utils.XLUtility;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DDtesting_POST_Excel {
	@Test(dataProvider="empdataprovider")
	public void postNewEmployeeCreation(String ename,String esal, String eage)
	{
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		
		RequestSpecification httpRequest = RestAssured.given();
		
		//Here we created to send along with POST data //JSON data
		JSONObject requestParams = new JSONObject();
		
		requestParams.put("name", ename);
		requestParams.put("salary", esal);
		requestParams.put("age",eage);
		
		//create headers to send along with POST request
		httpRequest.header("Content-Type","application/json");
		
		//Add json to the request body
		//we need to send the above data in JSOn format below line code will convert above into json format
		httpRequest.body(requestParams.toJSONString());
		
		//we need to send actual data now
		Response response= httpRequest.request(Method.POST,"/create");
		
		//printing the response
		String responseBody = response.getBody().asString();
		System.out.println("Response from API---->"+responseBody);
		
		Assert.assertEquals(responseBody.contains(ename),true);
		Assert.assertEquals(responseBody.contains(esal),true);
		Assert.assertEquals(responseBody.contains(eage),true);
		
		int statusCode = response.statusCode();
		Assert.assertEquals(statusCode, 200);
		
	}
	
	@DataProvider(name="empdataprovider")
	String [][] getEmpData() throws IOException
	{
		String path=System.getProperty("user.dir")+"\\src\\main\\java\\com\\qa\\data\\emptestdata.xlsx";
		
		int rownum = XLUtility.getRowCount(path, "RegTestData");
		int colCount = XLUtility.getCellCount(path, "RegTestData",1);
		
		System.out.println("Row Count--->"+rownum);
		System.out.println("Column Count--->"+colCount);
		
		String empdata[][] = new String[rownum][colCount];	
		
		for(int i=1; i<= rownum;i++)
		{
			for(int j=0; j<colCount; j++)
			{
				empdata[i-1][j] = XLUtility.getCellData(path, "RegTestData", i, j);
			}
		}
		
		return (empdata);
		
	}
		
	
	

}
