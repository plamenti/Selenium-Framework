package com.plamenti.selenium_framework.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;

public class WebDriverThreadFactory {
	private static ThreadLocal<WebDriverThread> driverThread;
	private static List<WebDriverThread> webDriverThreadPool = Collections.synchronizedList(new ArrayList<WebDriverThread>());
	
	public static void instantiateDriverObject() {
		driverThread = new ThreadLocal<WebDriverThread>() {
			@Override
			protected WebDriverThread initialValue() {
				WebDriverThread webDriverThread = new WebDriverThread();
				webDriverThreadPool.add(webDriverThread);
				return webDriverThread;
			}
		};
	}
	
	public static WebDriver getDriver(){
		return driverThread.get().getDriver();
	}
	
	public static void quitDriver(){
		driverThread.get().quitDriver();
	}
	
	 public static void clearCookies(){
	 getDriver().manage().deleteAllCookies();
	 }
	
	public static void closeDriveObjects() {
		for (WebDriverThread webDriverThread : webDriverThreadPool) {
			webDriverThread.quitDriver();
		}
	}
}
