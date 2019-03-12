package com.googleauth.selenium.automation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.Assert;

public class TestGoogleAuthentication {
	private WebDriver driver;
	private Properties prop = new Properties();

	public void loadPropertiesFile() {
		File file = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\application.properties");
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@BeforeClass
	public void beforeClass() {
		loadPropertiesFile();
		System.setProperty(prop.getProperty("driverName"),
				System.getProperty("user.dir") + "\\lib\\" + prop.getProperty("driverPath"));
		driver = new ChromeDriver();
	}

	@AfterClass
	public void afterClass() {
		if (driver == null) {
			return;
		}
		driver.quit();
		driver = null;
	}

	public void waitForPageLoad() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	public void doGoogleAuthentication() {

		// Check for Signin button
		WebElement signin_button = driver.findElement(By.xpath(prop.getProperty("sign_in")));

		// Click on Signin button
		signin_button.click();
		waitForPageLoad();

		// Check for User name input field
		WebElement identifierId_text = driver.findElement(By.id(prop.getProperty("username")));
		waitForPageLoad();
		identifierId_text.clear();

		// Enter google account user name
		identifierId_text.sendKeys(prop.getProperty("username_value"));
		identifierId_text.sendKeys(Keys.ENTER);
		waitForPageLoad();

		// Check for password field
		WebElement passwords_text = driver.findElement(By.xpath(prop.getProperty("password")));
		waitForPageLoad();

		// Searching for password input fied
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(passwords_text));

		// Enter password
		passwords_text.sendKeys(prop.getProperty("password_value"));
		passwords_text.sendKeys(Keys.ENTER);
	}

	@Test
	public void testMethod() {
		driver.get(prop.getProperty("url"));

		waitForPageLoad();

		doGoogleAuthentication();

		waitForPageLoad();

		WebElement verify_text = driver.findElement(By.xpath(prop.getProperty("verify_text")));
		boolean text = verify_text.isDisplayed();

		Assert.assertTrue(text, prop.getProperty("errorMessage"));
	}
}
