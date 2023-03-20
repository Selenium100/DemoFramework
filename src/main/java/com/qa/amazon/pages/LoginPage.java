package com.qa.amazon.pages;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.qa.amazon.base.BaseTest;

public class LoginPage extends BaseTest {
	
	private static final Logger LOG = Logger.getLogger(LoginPage.class);
	
	@FindBy(xpath="//span[text()='Hello, sign in']")
	WebElement signIn;
	
	@FindBy(id="ap_email")
	WebElement emailField;
	
	@FindBy(id="continue")
	WebElement continueBtn;
	
	@FindBy(id="ap_password")
	WebElement passwordField;
	
	@FindBy(id="signInSubmit")
	WebElement singInBtn;
	
	@FindBy(xpath="//div[@class='a-box-inner a-alert-container']/h4")
	WebElement incorrectPasswordAlert;
	
	
	
	
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void clickSignin() {
		LOG.info("Click SignIn");
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(signIn)).click();
	}
	
	public void enterEmail(String email) {
		LOG.info("Enter Email");
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(emailField)).sendKeys(email);
	}
	
	public void clickContinue() {
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(continueBtn)).click();
	}
	
	public void enterPassword(String password) {
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(password);
	}
	
	public void clickSignInafterGivingPassword() {
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(singInBtn)).click();
	}
	
	public void incorrectPasswordVerification() {
	Boolean flag =	new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(incorrectPasswordAlert)).isDisplayed();
	Assert.assertTrue(flag);
	System.out.println("Password is incorrect please enter correct one..");
		
	}
	

}
