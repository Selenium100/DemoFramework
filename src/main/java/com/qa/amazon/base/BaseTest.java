package com.qa.amazon.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.qa.amazon.error.AppError;
import com.qa.amazon.util.FrameworkException;
import com.qa.amazon.util.TestUtil;
import com.qa.amazon.util.WebEventListener;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public static WebDriver driver;
	public static Properties pro;
	WebEventListener eventListener;
	EventFiringWebDriver e_driver;
	private static final Logger LOG = Logger.getLogger(BaseTest.class);

	public BaseTest() {
			pro = new Properties();
			String path = "\\src\\main\\java\\com\\qa\\amazon\\config\\";
			
			FileInputStream fis;
			try {
				fis = new FileInputStream(System.getProperty("user.dir")+path+"QAconfig.properties");
				try {
					pro.load(fis);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("Mistakly done");
				

	}

	public static void initialization() {
		String browser = pro.getProperty("browser");
		LOG.info("browser used is " + browser);
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("ff")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}else {
			throw new FrameworkException(AppError.NO_BROWSER_FOUND);
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);

		driver.get(pro.getProperty("url"));

	}

}
