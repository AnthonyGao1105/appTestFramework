package com.appium.listener;

import java.util.List;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.xml.XmlSuite;

public class MyReport implements IReporter {

	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {

		ISuiteResult results = suites.get(0).getResults().get("Default test");
		ITestContext context = results.getTestContext();

		IResultMap failedTests = context.getFailedTests();

		// Print all test exceptions...
		for (ITestResult r : failedTests.getAllResults()) {
			Reporter.log("----------------");
			System.out.println("-------best--------");
			Reporter.log(r.getThrowable().toString());
			// System.out.println( r.getThrowable());
		}

		suites.get(0).getResults().get("Default test").getTestContext().getFailedTests().getAllResults();

	}

}
