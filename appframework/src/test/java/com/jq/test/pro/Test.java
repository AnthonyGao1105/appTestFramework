/**
 * 
 */
package com.jq.test.pro;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * @author anthony
 *
 */
public class Test {
	
	 
	
	 

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "/Users/anthony/Downloads/chromedriver");
		WebDriver driver= new ChromeDriver();
		driver.get("www.baidu.com");
		System.out.println("this is my first mac project");

	}

}
