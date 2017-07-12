package com.appium.listener;

import static com.appium.baseapp.BasePage.idriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.ScreenshotException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.appium.baseapp.BaseTestSuite;

public class MyTestCaseStatusListener implements ITestListener {
	public static boolean status = true;

	// @Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

	}

	// @Override
	public void onTestSuccess(ITestResult result) {
		Reporter.setCurrentTestResult(result);
		Reporter.log(BaseTestSuite.testInfo);
		BaseTestSuite.testInfo = "";
		status = true;
	}

	// @Override
	public void onTestFailure(ITestResult result) {
		Reporter.setCurrentTestResult(result);
		Reporter.log(BaseTestSuite.testInfo);
		BaseTestSuite.testInfo = "";

		captureScreenshot(result);
	}

	// @Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	// @Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	// @Override
	public void onStart(ITestContext context) {
		// System.out.println("start" + context.getAllTestMethods().length);
		// System.out.println("start" + context.getName());
		// System.out.println("start" + context.getAllTestMethods());
		// TODO Auto-generated method stub

	}

	// @Override
	public void onFinish(ITestContext context) {
		// System.out.println("end" + context.getAllTestMethods().length);
		// System.out.println("end" + context.getName());
		// System.out.println("end" + context.getAllTestMethods());

	}

	public static void captureScreenshot(ITestResult result) {
		if (!result.isSuccess()) {
			try {
				SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
				if (idriver != null) {
					idriver.switchTo().defaultContent();
					File f = ((TakesScreenshot) idriver).getScreenshotAs(OutputType.FILE);
					try {
						String fileName = result.getName() + "_" + formater.format(Calendar.getInstance().getTime())
								+ ".jpg";
						FileUtils.copyFile(f, new File("./test-output/screenshot/" + fileName));
						Reporter.setCurrentTestResult(result);
						Reporter.log("<a href=\"../screenshot/" + fileName + "\" target=\"_blank\">Screenshot</a>");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} catch (ScreenshotException se) {
				se.printStackTrace();
			}
		}
	}

}
