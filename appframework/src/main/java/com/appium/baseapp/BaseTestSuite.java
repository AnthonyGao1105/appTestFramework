package com.appium.baseapp;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

/**
 * @author Anthony
 *
 */
public class BaseTestSuite {

	/*
	 * This value is used in print test info
	 */
	public static String testInfo = "";
	/*
	 * protected boolean isRunAll = true; This property will control which row
	 * in a data file to run It will work together with
	 * utilityLib.Tools.getDataByTableName executeRow = 0 will execute all test
	 * cases in the datatable
	 */

	protected static int executeRow = 0;

	@Parameters({ "executeRow" })
	@BeforeClass(alwaysRun = true)
	protected void setUpBeforeClass(int executeRow) {
		this.executeRow = executeRow;
	}

}
