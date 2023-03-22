package com.qa.amazon.testcases;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.amazon.base.BaseTest;
import com.qa.amazon.pages.LoginPage;
import com.qa.amazon.util.TestUtil;

public class LoginTest extends BaseTest {
	
	
	LoginPage loginPage;
	
	
	public LoginTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() {
		initialization();
		loginPage=new LoginPage();
	}
	
	@Test
	public void amazonLoginTest() {
		loginPage.clickSignin();
		loginPage.enterEmail(pro.getProperty("username"));
		loginPage.clickContinue();
		loginPage.enterPassword(pro.getProperty("password"));
		loginPage.clickSignInafterGivingPassword();
		loginPage.incorrectPasswordVerification();
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		if(ITestResult.FAILURE == result.getStatus()) {
			TestUtil.takeScreenshotAtEndOfTest();
		}
		driver.quit();
	}

}
