package com.utility;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.utility.ListenerTest.class)

public class Testcase8 extends Test_Runner{
	
	static String name ="Link";
    static String Testname ="Link";
    static String Testclass ="com.utility.Testcase8";
    static String Testresult;

	

 @Test

	public void printTestCaseName8() {

		System.out.println("First test case");
	}
 
 @AfterMethod
 public void afterMethod(ITestResult result)
 {
		try
		  {
		     if(result.getStatus() == ITestResult.SUCCESS)
		     {

		         
		         Testresult="PASS";
		       
		         
		     }

		     else if(result.getStatus() == ITestResult.FAILURE)
		     {
		         
		         Testresult="FAIL";
		       

		     }

		      else if(result.getStatus() == ITestResult.SKIP ){

		         
		         Testresult="SKIP";
		       

		     }
		     
		     Test_Executor.inserttestresultintoDB(name, Testname, Testresult, Testclass);
 }
    catch(Exception e)
    {
      e.printStackTrace();
    }

 }

}
