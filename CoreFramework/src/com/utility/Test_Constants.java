package com.utility;

public class Test_Constants {
	
	//DB SQL
	
		public static final String QUERY_GETRUNTESTSUITE_RS="SElECT Testsuite from Testsuiterunmode where Testsuiterunmode='Y'";
		public static final String QUERY_GETRUNTESTCASE_RS="SELECT TestcaseID,Testcasename,Testclassname ,Runmode from Testrunconfig where Runmode='Y'";
		public static final String QUERY_GETFAILEDESTCASE_RS="SELECT Testsuitename,Testcasename,Testclassname ,Testcaseresult from Testresult where Testcaseresult='FAIL'";
		public static final String QUERY_DELETEFROMDB_RS="DELETE FROM Testresult WHERE Testcaseresult in ('PASS','FAIL')";
		public static final String DB_CLASS="com.mysql.jdbc.Driver";
		

}
