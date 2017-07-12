# appTestFramework
mobile app automation testing framework base on appium

example：
if u want to use this framework, u can folllow following steps:
1. download appTestFramework project
2. unzip to access the project
3. user "mvn clean install" to genernate the appframework.jar
4. create new test pom project and configure the pom file like that:

  <dependencies>
	<dependency>
    	<groupId>io.appium</groupId>
    	<artifactId>java-client</artifactId>
    	<version>3.4.1</version>
	</dependency>
	
	<dependency>
	<groupId>com.anthony.test</groupId>
	<artifactId>appframework</artifactId>
	<version>1.0.0-SNAPSHOT</version>
	</dependency>
	
	<dependency>
	<groupId>org.testng</groupId>
	<artifactId>testng</artifactId>
	<version>6.9.10</version>
	</dependency>
	
	<dependency>
	<groupId>net.sourceforge.jexcelapi</groupId>
	<artifactId>jxl</artifactId>
	<version>2.6.10</version>
	</dependency>
	
	<dependency>
	<groupId>org.eclipse.jetty</groupId>
	<artifactId>jetty-util-ajax</artifactId>
	<version>9.3.7.v20160115</version>
	</dependency>
	
	<dependency>
	<groupId>net.sourceforge.jexcelapi</groupId>
	<artifactId>jxl</artifactId>
	<version>2.6.10</version>
	</dependency>
	
  </dependencies>


test case example:

1. create page object to do the element management:

package com.anthony.app.page;

import com.appium.baseapp.BasePage;
import com.appium.baselibs.MyWebElement;

/**
 * @author anthony
 * @date Jul 12, 2017
 * @updateTime 10:20:57 AM
 */
public class TestBorrowPage extends BasePage{
	
	public static MyWebElement pro1500 = myWebelement("xpath://android.widget.TextView[contains(@text,'1500')]");
	
	public static MyWebElement borrowNow = myWebelement("com.xd.test:id/apply_confirm_btn_confirm");
	
	public static MyWebElement totalAmount = myWebelement("com.xd.test:id/apply_confirm_repayment");

	public static MyWebElement agreeCheckBox = myWebelement("com.xd.test:id/apply_confirm_auto_repay_cb");
	
	public static MyWebElement borrow = myWebelement("com.xd.test:id/apply_confirm_btn");
	
}

2. create test cases:

package com.anthony.app.appTest;


import static com.appium.baseapp.BasePage.exitApp;
import static com.appium.baseapp.BasePage.openAndroidApp;

import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.anthony.app.page.TestHomePage;
import com.anthony.app.page.TestBorrowPage;
/**
 * 
 * @author anthony
 * @date Jul 12, 2017
 * @updateTime 9:40:39 AM
 */
public class AppTest {
	@BeforeMethod
	
    public static void setPrecondition() throws MalformedURLException{
		
		openAndroidApp("GiONEE GN5001S", "5.1","com.xd.test","com.xd.test.ui.MainActivity");
		
    }
	
	/**
	 * 用户借款
	 * @param phone
	 * @param pw
	 * @param inviteCode
	 * @throws InterruptedException
	 */
	@Test(priority=0)
	
	public void apply() throws InterruptedException{
		TestHomePage.borrowLink.click();
		TestBorrowPage.pro1500.click();
		TestBorrowPage.borrowNow.click();
		TestBorrowPage.agreeCheckBox.click();
		TestBorrowPage.borrow.click();
		TestBorrowPage.confirmBorrow.click();
		Assert.assertEquals(DXDBorrowPage.borrowSuccessInfo.getAppText(), "提现申请中，请注意账户余额变化！");
	}
	
	@AfterMethod
    public void exit(){
		exitApp();
        }	
}


