package com.plamenti.selenium_framework.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.plamenti.selenium_framework.listeners.ScreenShotListener;
import com.plamenti.selenium_framework.pages.HomePage;
import com.plamenti.selenium_framework.pages.LoginPage;
import com.plamenti.selenium_framework.pages.fragments.MainNavigation;

@Listeners(ScreenShotListener.class)
public class BaseTestAbstract {
	protected LoginPage loginPage;
	protected HomePage homePage;
	protected MainNavigation mainNavigation;

	@BeforeSuite
	public static void instantiateDriver() {
		WebDriverThreadFactory.instantiateDriverObject();
	}

	public static WebDriver getDriver() {
		return WebDriverThreadFactory.getDriver();
	}

	@AfterMethod
	public static void quitDriver() {
		WebDriverThreadFactory.quitDriver();
	}

//	 @AfterMethod
//	 public static void clearCookies() throws Exception {
//	 WebDriverThreadFactory.clearCookies();
//	 }

	@AfterSuite
	public static void closeDriveObjects() {
		WebDriverThreadFactory.closeDriveObjects();
	}
}
