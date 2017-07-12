package com.jq.test.pro;

import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * App 自动化测试demo
 */
public class AppTest
	{
	
	AppiumDriver driver;
    /**
     * 设置前置条件
     * @throws MalformedURLException 
     * @throws InterruptedException 
     */
	
	@BeforeMethod
	
    public void setPrecondition() throws MalformedURLException, InterruptedException{
		DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName","HM NOTE 1S CU");
        capabilities.setCapability("platformVersion", "4.4.4");
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("appPackage", "com.jq.dccj.android");
        capabilities.setCapability("appActivity", "com.jq.dccj.android.activity.SplashActivity_");
        capabilities.setCapability("unicodeKeyboard", "True");
        capabilities.setCapability("resetKeyboard", "True");
		driver= new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		Thread.sleep(5000);
    }


    /**
     * 测试用例
     * @throws InterruptedException 
     */
    
    @Test
    
    public void testApp() throws InterruptedException{
    	
//    		driver.findElementByAndroidUIAutomator(null);
			
			//WebElement unlock= driver.findElement(By.id("com.jq.dccj.android:id/gesturepwd_unlock_lockview"));
			new TouchAction(driver).press(200,420).moveTo(150, 0).moveTo(0, 380).moveTo(150, 0).release().perform();
			Thread.sleep(5000);
			WebElement dccjapp = driver.findElement(By.id("com.jq.dccj.android:id/btn_consumer"));
			dccjapp.click();
			Thread.sleep(3000);
			WebElement joinNowButton = driver.findElement(By.id("com.jq.dccj.android:id/join_btn"));
			joinNowButton.click();
			Thread.sleep(3000);
			WebElement buyButton = driver.findElement(By.id("com.jq.dccj.android:id/btn_product_info_buy"));
			buyButton.click();
			Thread.sleep(3000);
			WebElement inputMoney = driver.findElement(By.id("com.jq.dccj.android:id/edt_input_money"));
			inputMoney.sendKeys("1000");
			Thread.sleep(3000);
			WebElement serveManager = driver.findElement(By.id("com.jq.dccj.android:id/edt_recommended"));
			serveManager.sendKeys("18616838733");
			Thread.sleep(3000);
			WebElement nextButton = driver.findElement(By.id("com.jq.dccj.android:id/btn_next_step"));
			nextButton.click();
			Thread.sleep(8000);
			WebElement verifyMoney = driver.findElement(By.id("com.jq.dccj.android:id/tv_amount"));
			WebElement verifyManager = driver.findElement(By.id("com.jq.dccj.android:id/tv_recommended_name"));
			System.out.println("本次购买金额："+verifyMoney.getText()+"\n"+"本次服务经理："+verifyManager.getText());
			Assert.assertEquals(verifyMoney.getText(), "1,000元");
			Assert.assertEquals(verifyManager.getText(), "服务经理:高江(18616838733)");
			}
    
    
    /**
     * 测试结束
     */
    @AfterMethod
    public void exit(){
        driver.quit();
        }
	
	}



