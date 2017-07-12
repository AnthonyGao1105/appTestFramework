package com.appium.baselibs;

import static com.appium.baselibs.PrintTestCases.print;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

/**
 * @author 高江
 *
 */
public class MyBy extends By {
	@Override
	public List<WebElement> findElements(SearchContext context) {
		// TODO Auto-generated method stub
		return null;
	}

	public static By locator(String locator) {
		if (!locator.contains("xpath") && !locator.contains("class")) {
			return By.id(locator);
		} else if (locator.contains("xpath")) {
			String[] lArr = locator.split(":");
			String by = lArr[0];
			String using = locator.substring(by.length() + 1);
			return By.xpath(using);
		} else if (locator.contains("class")) {
			String[] lArr = locator.split(":");
			String by = lArr[0];
			String using = locator.substring(by.length() + 1);
			return By.className(using);
		} else {
			print(" Element " + locator + "cannot be found.. ");
			throw new IllegalArgumentException("Cannot find elements when name text is null.");
		}
	}
}
