package com.utility;

import com.utility.Test_Executor;

public class Test_Runner {

	public static void main(String[] args) {
		int minretryCount = 0;
		int maxretryCount = 2;

		Test_Executor generatexml = new Test_Executor();
		Test_Executor.TESTITERATION = 1;
		generatexml.deleteTestCaseResult();
		generatexml.runTestNGTest();

		if (Test_Executor.getTestCaseResult()) {
			while (minretryCount <= maxretryCount) {

				generatexml.rerunfailedtestcase();
				Test_Executor.TESTITERATION++;

				minretryCount++;

			}
		}
	}
}
