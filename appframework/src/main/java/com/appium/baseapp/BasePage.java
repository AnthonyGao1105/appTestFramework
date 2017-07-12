package com.appium.baseapp;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

import org.apache.log4j.LogManager;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.appium.baselibs.MyWebElement;

/**
 * @author 高江
 */
public class BasePage {
  public static AppiumDriver<WebElement> idriver;

  /**
   * Following functions are used to hide driver get MyWebElement
   */
  public static MyWebElement myWebelement(WebElement webElement) {
    return new MyWebElement(webElement);
  }

  public static MyWebElement myWebelement(String locator) {
    return new MyWebElement(locator);
  }

  public static MyWebElement myWebelement(String locator, String name, String... subLocator) {
    return new MyWebElement(locator, name, subLocator);
  }

  public static MyWebElement myWebelement(String locator, String name, int index) {
    return new MyWebElement(locator, name, index);
  }

  public static MyWebElement myWebelement(String locator, String name, int index,
      String... subLocator) {
    return new MyWebElement(locator, name, index, subLocator);
  }

  public static MyWebElement myWebelement(String locator, String name, int index,
      String[] subLocator, int[] sub_index) {
    return new MyWebElement(locator, name, index, subLocator, sub_index);
  }

  /**
   * 开启app
   * 
   * @param browser
   * @throws MalformedURLException
   */
  public static void openAPP(String os, String deviceName, String osVersion)
      throws MalformedURLException {
    if (os.equalsIgnoreCase("Android")) {
      DesiredCapabilities capabilities = new DesiredCapabilities();
      capabilities.setCapability("deviceName", deviceName);
      capabilities.setCapability("platformVersion", osVersion);
      capabilities.setCapability("platformName", os);
      capabilities.setCapability("appPackage", "com.jq.dccj.android");
      capabilities.setCapability("appActivity", "com.jq.dccj.android.activity.SplashActivity_");
      capabilities.setCapability("unicodeKeyboard", "True");
      capabilities.setCapability("resetKeyboard", "True");
      idriver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    } else if (os.equalsIgnoreCase("ios")) {
      // TODO
    }
  }
  
  /**
	 * 切换app窗口和h5窗口
	 * @param window window name
	 * @author anthony
	 */
	public static void switchtowindow(String window) {	
		Set<String> contextNames = idriver.getContextHandles();
		for (String contextName : contextNames) {
			if (contextName.contains(window)) {
				idriver.context(contextName);
				break;
			}
		}
	}

  /**
   * reset app
   */
  public static void resetApp() {
    idriver.resetApp();
  }

  /**
   * install app
   */
  public static void installApp(String appPath) {
    idriver.installApp(appPath);
  }

  /**
   * remove App
   */
  public static void removeApp(String bundleId) {
    idriver.removeApp(bundleId);
  }

  /**
   * 退出app
   */
  public static void exitApp() {
    //idriver.closeApp();
    idriver.quit();
  }

  /**
   * 启动app
   */
  public static void launchApp() {
    idriver.launchApp();
  }

  /**
   * Switch between windows
   */
  public static void switchToWindow() {

    // Switch to new window opened
    for (String winHandle : idriver.getWindowHandles()) {
      idriver.switchTo().window(winHandle);
    }
  }

  /**
   * 获取sessionID
   */
  public static String getSessionId() {
    Set<Cookie> cookies = idriver.manage().getCookies();
    String sessionId = "";
    for (Cookie cookie : cookies) {
      if (cookie.getName().equals("sid")) {
        sessionId = cookie.getValue();
        return sessionId;
      }
    }
    return sessionId;
  }

}
