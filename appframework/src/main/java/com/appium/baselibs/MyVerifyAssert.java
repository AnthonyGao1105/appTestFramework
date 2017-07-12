package com.appium.baselibs;

import static com.appium.baselibs.PrintTestCases.print;
import io.appium.java_client.AppiumDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.appium.baseapp.BaseTestSuite;

/**
 * @author Anthony This is the class define verify/assert method
 *
 */
public class MyVerifyAssert {

  private static Map<ITestResult, List<Throwable>> verificationFailuresMap =
      new HashMap<ITestResult, List<Throwable>>();

  public static void assertTrue(String message, boolean condition) {
    print(message);
    Assert.assertTrue(condition, message);
  }

  public static void assertFalse(String message, boolean condition) {
    print(message);
    Assert.assertFalse(condition, message);
  }

  public static void assertEquals(String message, Object actual, Object expected) {
    print(message);
    Assert.assertEquals(actual, expected, message);
  }

  public static void verifyTrue(String message, boolean condition) {
    try {
      assertTrue(message, condition);
    } catch (Throwable e) {
      addVerificationFailure(e);
    }
  }

  public static void verifyTrue(AppiumDriver driver, String message, boolean condition) {
    try {
      assertTrue(message, condition);
    } catch (Throwable e) {
      addVerificationFailure(e, driver);
    }
  }

  public static void verifyMatches(String message, String actual, String expected) {
    try {
      assertTrue(message, actual.matches(expected));
    } catch (Throwable e) {
      addVerificationFailure(e);
    }
  }

  public static void verifyMatches(AppiumDriver driver, String message, String actual, String expected) {
    try {
      assertTrue(message, actual.matches(expected));
    } catch (Throwable e) {
      addVerificationFailure(e, driver);
    }
  }

  public static void verifyContains(String message, String actual, String expected) {
    try {
      assertTrue(message, actual.contains(expected));
    } catch (Throwable e) {
      addVerificationFailure(e);
    }
  }

  public static void verifyContains(AppiumDriver driver, String message, String actual, String expected) {
    try {
      assertTrue(message, actual.contains(expected));
    } catch (Throwable e) {
      addVerificationFailure(e, driver);
    }
  }

  public static void verifyNotContains(String message, String actual, String expected) {
    try {
      assertFalse(message, actual.contains(expected));
    } catch (Throwable e) {
      addVerificationFailure(e);
    }
  }

  public static void verifyNotContains(AppiumDriver driver, String message, String actual,
      String expected) {
    try {
      assertFalse(message, actual.contains(expected));
    } catch (Throwable e) {
      addVerificationFailure(e, driver);
    }
  }

  public static void verifyEquals(AppiumDriver driver, String message, Object actual, Object expected) {
    try {
      assertEquals(message, actual, expected);
    } catch (Throwable e) {
      addVerificationFailure(e, driver);
    }
  }

  public static void verifyEquals(String message, Object actual, Object expected) {
    try {
      assertEquals(message, actual, expected);
    } catch (Throwable e) {
      addVerificationFailure(e);
    }
  }

  public static void verifyArrayEquals(String message, Object actual, Object expected) {
    try {
      assertEquals(message, actual, expected);
    } catch (Throwable e) {
      addVerificationFailure(e);
    }
  }

  public static void verifyFalse(String message, boolean condition) {
    try {
      assertFalse(message, condition);
    } catch (Throwable e) {
      addVerificationFailure(e);
    }
  }

  public static void verifyFalse(AppiumDriver driver, String message, boolean condition) {
    try {
      assertFalse(message, condition);
    } catch (Throwable e) {
      addVerificationFailure(e, driver);
    }
  }

  public static void fail(String message) {
    Assert.fail(message);
  }

  public static List<Throwable> getVerificationFailures() {
    List<Throwable> verificationFailures =
        verificationFailuresMap.get(Reporter.getCurrentTestResult());
    return verificationFailures == null ? new ArrayList<Throwable>() : verificationFailures;
  }

  private static void doScreenShoot(AppiumDriver driver) {
    SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
    if (driver != null) {
      driver.switchTo().defaultContent();
      File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
      try {
        String fileName = formater.format(Calendar.getInstance().getTime()) + ".jpg";
        FileUtils.copyFile(f, new File("./test-output/screenshot/" + fileName));
        BaseTestSuite.testInfo =
            BaseTestSuite.testInfo + "<a href=\"" + "screenshot/" + fileName
                + "\" target=\"_blank\">Screenshot</a>";
      } catch (IOException e1) {
        e1.printStackTrace();
      }
    }
  }

  private static void addVerificationFailure(Throwable e, AppiumDriver driver) {
    doScreenShoot(driver);
    List<Throwable> verificationFailures = getVerificationFailures();
    verificationFailuresMap.put(Reporter.getCurrentTestResult(), verificationFailures);
    verificationFailures.add(e);
  }

  private static void addVerificationFailure(Throwable e) {
    List<Throwable> verificationFailures = getVerificationFailures();
    verificationFailuresMap.put(Reporter.getCurrentTestResult(), verificationFailures);
    verificationFailures.add(e);
  }

}
