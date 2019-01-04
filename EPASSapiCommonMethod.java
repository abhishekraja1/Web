package com.org.learningMaven;

import java.io.FileOutputStream;
import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class EPASSapiCommonMethod extends APICommonMethods
{

	String filePath = "/home/terralogic/xlsxFile.xlsx";
	String apiName; 
	int sheetno = 0;
	int rowno = 0;
	FileOutputStream fos = null;
	String sessionId;
	String TestResult;
	@Test(priority=1)
	public void epassTestScript() throws IOException
	{ 
		 apiName = "Login_API";
		  
		 
		String[] requestBody = readXlsx(filePath,apiName,sheetno,rowno);
	 
			
		if(requestBody[0].equals(apiName))
		{
			RestAssured.baseURI =requestBody[2];
			Response res=RestAssured.given().
					header("Content-Type","application/json").
						body(requestBody[3]).
						when().
						post("/login_verification.php").
						then().
						assertThat().statusCode(200).
					extract().response();
			String resString = res.asString();
			System.out.println(resString);
			sessionId = res.jsonPath().getString("sessionid");
			System.out.println(sessionId);	
			System.out.println(resString);
		}
	}
	
	@Test(priority=2)
	public void authentication() throws IOException
	{
		String apiName = "Login_Authentication_API";

		String[] RequestBody = readXlsx(filePath,  apiName, sheetno,  rowno);
		RequestBody[3] = "{\n" + "\"sessionid\":" +"\""+sessionId+"\""+"\n"+RequestBody[3];
		System.out.println(RequestBody[3]);
		String[] response = apiCall1(RequestBody[1],RequestBody[2],RequestBody[3]);
		TestResult =  CompareStatusCode(response[1]);
		//System.out.println(response[0]);
		//System.out.println("Test Case Result : "+TestResult);
		//writeUsingBufferedWriter(response[0]);
		
	}
	
	@Test(priority=3)
	public void AdminDetailsADDAPI() throws IOException
	{
		System.out.println("******");
		String apiName = "Admin_Details_ADD_API";
		String[] RequestBody = readXlsx(filePath,  apiName, sheetno,  rowno);
		
		RequestBody[3] = "{\n" + "\"sessionid\":" +"\""+sessionId+"\","+"\n"+RequestBody[3];
		System.out.println("******");
		System.out.println(RequestBody[3]);
		String[] response = apiCall1(RequestBody[1],RequestBody[2],RequestBody[3]);
		//System.out.println(response[0]);
		TestResult =  CompareStatusCode(response[1]);
		//writeUsingBufferedWriter(response[0]);
		
	}
	
	@Test(priority=4)
	public void  AdminDetailsEditAPI() throws IOException
	{
		String apiName = " Admin_Details_Edit_API";
		String[] RequestBody = readXlsx(filePath,  apiName, sheetno,  rowno);
		RequestBody[3] = "{\n" + "\"sessionid\":" +"\""+sessionId+"\","+"\n"+RequestBody[3];
		System.out.println(RequestBody[3]);
		String[] response = apiCall1(RequestBody[1],RequestBody[2],RequestBody[3]);
		TestResult =  CompareStatusCode(response[1]);
		System.out.println(response[0]);
		System.out.println("Test Case Result : "+TestResult);
		//writeUsingBufferedWriter(response[0]);		
	}
	
	@Test(priority=5)
	public void AdminDetailsGetList() throws IOException
	{
		String apiName = "Admin_Details_Get_List"; 

		String[] RequestBody = readXlsx(filePath,  apiName, sheetno,  rowno);
		RequestBody[3] = "{\n" + "\"sessionid\":" +"\""+sessionId+"\","+"\n"+RequestBody[3];
		String[] response = apiCall1(RequestBody[1],RequestBody[2],RequestBody[3]);
		TestResult =  CompareStatusCode(response[1]);
		System.out.println(response[0]);
		System.out.println("Test Case Result : "+TestResult);
		//writeUsingBufferedWriter(response[0]);
		
	}
	
	@Test(priority=6)
	public void RemoveAdminAPI() throws IOException
	{
		String apiName = "Remove_Admin_API";

		String[] RequestBody = readXlsx(filePath,  apiName, sheetno,  rowno);
		RequestBody[3] = "{\n" + "\"sessionid\":" +"\""+sessionId+"\","+"\n"+RequestBody[3];
		String[] response = apiCall1(RequestBody[1],RequestBody[2],RequestBody[3]);
		TestResult =  CompareStatusCode(response[1]);
		System.out.println(response[0]);
		System.out.println("Test Case Result : "+TestResult);
		//writeUsingBufferedWriter(response[0]);
		
	}
	
	@Test(priority=7)
	public void LogoutAPI() throws IOException
	{
		String apiName = "Logout_API";

		String[] RequestBody = readXlsx(filePath,  apiName, sheetno,  rowno);
		RequestBody[3] = "{\n" + "\"sessionid\":" +"\""+sessionId+"\""+"\n"+RequestBody[3];
		String[] response = apiCall1(RequestBody[1],RequestBody[2],RequestBody[3]);
		TestResult =  CompareStatusCode(response[1]);
		System.out.println(response[0]);                                                      
		System.out.println("Test Case Result : "+TestResult);
		//writeUsingBufferedWriter(response[0]);
		
	}
}
