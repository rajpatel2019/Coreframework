package com.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import com.mysql.jdbc.PreparedStatement;
import com.utility.Test_Constants;
import com.utility.Test_util;

public class Test_Executor  {

	
	protected String TTESTSUITENAME;
	protected String ID;
	protected String TESTNAME;
	protected String RUNMODE;
	protected String TESTCLASS;
    protected static String TESTRESULT;
	protected String TESTSUITE;
	protected String FAILEDTESTNAME;
	protected String FAILEDTESTCLASS;
	protected static String NAME;
	protected static String TESTCASENAME;
	protected static String TESTCLASSNAME;
	protected static String RUN;
	protected static Connection conn = null;
	protected static String EXECUTIONTIME=Test_util.getSystemTime();
	protected static int TESTITERATION;

	public void runTestNGTest() {
		
		

		try {
			Class.forName(Test_Constants.DB_CLASS);

			conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3380/MyAutomation", "root", "");
			System.out.println("Database is connected !");

			java.sql.Statement stmt = conn.createStatement();

			java.sql.ResultSet RUN = stmt.executeQuery(Test_Constants.QUERY_GETRUNTESTSUITE_RS);

			List<String> Suitelist = new ArrayList<String>();
			while (RUN.next()) {
				TTESTSUITENAME = RUN.getString("Testsuite");
				System.out.println(TTESTSUITENAME);
				Suitelist.add(TTESTSUITENAME);
			}

			RUN = stmt.executeQuery(Test_Constants.QUERY_GETRUNTESTCASE_RS);

			while (RUN.next()) {

				ID = RUN.getString("TestcaseID");
				TESTNAME = RUN.getString("Testcasename");
				RUNMODE = RUN.getString("Runmode");
				TESTCLASS = RUN.getString("Testclassname");

				// Create an instance on TestNG
				TestNG myTestNG = new TestNG();

				// Create an instance of XML Suite and assign a name for it.
				XmlSuite mySuite = new XmlSuite();
				mySuite.setName("Suite");

				// Create an instance of XmlTest and assign a name for it.
				XmlTest myTest = new XmlTest(mySuite);
				myTest.setName("Test");

				// Add any parameters that you want to set to the Test.
				// myTest.setParameters(testngParams);

				// Create a list which can contain the classes that you want to
				// run.
				List<XmlClass> myClasses = new ArrayList<XmlClass>();

				if (Suitelist.contains(TESTNAME) && RUNMODE.equalsIgnoreCase("Y")) {
					myClasses.add(new XmlClass(TESTCLASS));
					System.out.println(myClasses);
				}

				// Assign that to the XmlTest Object created earlier.
				myTest.setXmlClasses(myClasses);

				// Create a list of XmlTests and add the Xmltest you created
				// earlier to
				// it.
				List<XmlTest> myTests = new ArrayList<XmlTest>();
				myTests.add(myTest);

				// add the list of tests to your Suite.
				mySuite.setTests(myTests);

				// Add the suite to the list of suites.
				List<XmlSuite> mySuites = new ArrayList<XmlSuite>();
				mySuites.add(mySuite);

				// Set the list of Suites to the testNG object you created
				// earlier.
				myTestNG.setXmlSuites(mySuites);

				// TestListenerAdapter tla = new TestListenerAdapter();
				// myTestNG.addListener(tla);

				// invoke run() - this will run your class.
				myTestNG.run();

			}

		} catch (Exception e) {
			System.out.println("Really poor exception handling " + e.toString());
		}
	}

	public void rerunfailedtestcase() {
		
		

		try {
			Class.forName(Test_Constants.DB_CLASS);

			conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3380/MyAutomation", "root", "");

			java.sql.Statement stmt = conn.createStatement();

			java.sql.ResultSet RUN = stmt.executeQuery(Test_Constants.QUERY_GETFAILEDESTCASE_RS);
			System.out.println(RUN);

			while (RUN.next()) {

				TESTSUITE = RUN.getString("Testsuitename");
				FAILEDTESTNAME = RUN.getString("Testcasename");
				TESTRESULT = RUN.getString("Testcaseresult");
				FAILEDTESTCLASS = RUN.getString("Testclassname");

				if (!TESTRESULT.equalsIgnoreCase("FAIL")) {
					conn.close();
				}

				else {

					// Create an instance on TestNG
					TestNG myTestNG = new TestNG();

					// Create an instance of XML Suite and assign a name for it.
					XmlSuite mySuite = new XmlSuite();
					mySuite.setName("FaieldTestSuite");

					// Create an instance of XmlTest and assign a name for it.
					XmlTest myTest = new XmlTest(mySuite);
					myTest.setName("FailedTestCases");

					// Add any parameters that you want to set to the Test.
					// myTest.setParameters(testngParams);

					// Create a list which can contain the classes that you want
					// to
					// run.
					List<XmlClass> myClasses = new ArrayList<XmlClass>();

					myClasses.add(new XmlClass(FAILEDTESTCLASS));
					System.out.println(myClasses);

					// Assign that to the XmlTest Object created earlier.
					myTest.setXmlClasses(myClasses);

					// Create a list of XmlTests and add the Xmltest you created
					// earlier to
					// it.
					List<XmlTest> myTests = new ArrayList<XmlTest>();
					myTests.add(myTest);

					// add the list of tests to your Suite.
					mySuite.setTests(myTests);

					// Add the suite to the list of suites.
					List<XmlSuite> mySuites = new ArrayList<XmlSuite>();
					mySuites.add(mySuite);

					// Set the list of Suites to the testNG object you created
					// earlier.
					myTestNG.setXmlSuites(mySuites);

					// TestListenerAdapter tla = new TestListenerAdapter();
					// myTestNG.addListener(tla);

					// invoke run() - this will run your class.
					
					myTestNG.run();
					

				}
			}

		} catch (Exception e) {
			System.out.println("Really poor exception handling " + e.toString());
		}
	}

	public static boolean getTestCaseResult() {

		try {
			Class.forName(Test_Constants.DB_CLASS);

			conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3380/MyAutomation", "root", "");
			System.out.println("Database is connected !");

			java.sql.Statement stmt = conn.createStatement();

			java.sql.ResultSet RUN = stmt.executeQuery(Test_Constants.QUERY_GETFAILEDESTCASE_RS);
			System.out.println(RUN);

			while (RUN.next()) {

				TESTRESULT = RUN.getString("Testcaseresult");
				if (TESTRESULT.equalsIgnoreCase("FAIL")) {
					return true;

				}
				conn.close();

			}
		} catch (Exception e) {
			System.out.println("Really poor exception handling " + e.toString());
		}
		return false;

	}

	public void deleteTestCaseResult() {

		try {
			Class.forName(Test_Constants.DB_CLASS);

			conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3380/MyAutomation", "root", "");
			System.out.println("Database is connected !");

			java.sql.Statement stmt = conn.createStatement();

			int RUN = stmt.executeUpdate(Test_Constants.QUERY_DELETEFROMDB_RS);
			System.out.println(RUN);
			conn.close();
		} catch (Exception e) {
			System.out.println("Really poor exception handling " + e.toString());
		}

	}

	public static void inserttestresultintoDB(String NAME, String TESTCASENAME, String TESTRESULT,String TESTCLASSNAME) {
		
		    
		    
		
		try {
			Class.forName(Test_Constants.DB_CLASS);

			conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3380/MyAutomation", "root", "");
			System.out.println("Database is connected !");

			String query = "insert into Testresult values('" + NAME + "', '" + TESTCASENAME + "', '" + TESTRESULT
					+ "' ,'" + TESTCLASSNAME + "','" + EXECUTIONTIME + "','" + TESTITERATION + "')";
			PreparedStatement preparedStmt = (PreparedStatement) conn.prepareStatement(query);

			preparedStmt.executeUpdate();
			

			conn.close();

		} catch (Exception e) {
			System.out.println("Really poor exception handling " + e.toString());
		}

	}

}
