package com.appium.baseapp;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.appium.baselibs.MyWebElement;

/**
 * @author 高江
 *
 */
public class BasePage {
	public static AppiumDriver<WebElement> idriver;
	public static File app;

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

	public static MyWebElement myWebelement(String locator, String name, int index, String... subLocator) {
		return new MyWebElement(locator, name, index, subLocator);
	}

	public static MyWebElement myWebelement(String locator, String name, int index, String[] subLocator,
			int[] sub_index) {
		return new MyWebElement(locator, name, index, subLocator, sub_index);
	}

	/**
	 * 开启app
	 * 
	 * @param browser
	 * @throws MalformedURLException
	 */
	public static void openAndroidApp(String deviceName, String osVersion, String appPackage, String appActivity)
			throws MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		// File classpathRoot = new File(System.getProperty("user.dir"));
		// File appDir = new File(classpathRoot, "apps");
		// app = new File(appDir, "xks.apk");
		// capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("deviceName", deviceName);
		capabilities.setCapability("platformVersion", osVersion);
		capabilities.setCapability("appPackage", appPackage);
		capabilities.setCapability("appActivity", appActivity);
		capabilities.setCapability("unicodeKeyboard", "True");
		capabilities.setCapability("resetKeyboard", "True");
		capabilities.setCapability("noReset", true);
		idriver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 开启app
	 * 
	 * @param browser
	 * @throws MalformedURLException
	 */
	public static void openIosApp(String deviceName, String udid, String bundleId) throws MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", deviceName);
		capabilities.setCapability("platformName", "ios");
		capabilities.setCapability("udid", udid);
		capabilities.setCapability("bundleId", bundleId);
		idriver = new IOSDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
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
	public static void installAndroidApp(String appPath) {
		idriver.installApp(appPath);

	}

	/**
	 * remove app
	 */
	public static void removeApp(String bundleId) {
		idriver.removeApp(bundleId);
	}

	/**
	 * 退出app
	 */
	public static void exitApp() {
		idriver.quit();
	}

	/**
	 * 启动app
	 */
	public static void launchApp() {
		idriver.launchApp();
	}

}
