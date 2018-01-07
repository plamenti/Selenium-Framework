package com.plamenti.selenium_framework.base;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.plamenti.selenium_framework.config.DriverType;

public class WebDriverThread {
	private WebDriver webdriver;
	private DriverType selectedDriverType;
	
	private final DriverType defaultDriverType = DriverType.CHROME;

	private final String browser = System.getProperty("browser").toUpperCase();
	private final String operatingSystem = System.getProperty("os.name".toUpperCase());
	private final String systemArchitecture = System.getProperty("os.arch");
	private final Boolean useRemoteDriver = Boolean.getBoolean("remoteDriver");
	
	public WebDriver getDriver(){
		if(this.webdriver == null) {
			this.selectedDriverType = this.determineEffectiveDriverType();
			DesiredCapabilities desiredCapabilities = this.selectedDriverType.getDesiredCapabilities();
			this.instantiateWebDriver(desiredCapabilities);
		}
		
		return this.webdriver;
	}

	public void quitDriver() {
		if(this.webdriver != null) {
			this.webdriver.quit();
			this.webdriver = null;
		}
	}

	private DriverType determineEffectiveDriverType() {
		DriverType driverType = defaultDriverType;
		
		try {
		driverType = DriverType.valueOf(browser);
		} catch (IllegalArgumentException ignored) {
            System.err.println("Unknown driver specified, defaulting to '" + driverType + "'...");
        } catch (NullPointerException ignored) {
            System.err.println("No driver specified, defaulting to '" + driverType + "'...");
        }
		
		return driverType;
	}
	
	private void instantiateWebDriver(DesiredCapabilities desiredCapabilities){
		System.out.println(" ");
		System.out.println("Current operating system " + this.operatingSystem);
		System.out.println("Current architecture: " + this.systemArchitecture);
		System.out.println("Current browser selection: " + this.selectedDriverType);
		System.out.println(" ");
		
		if(this.useRemoteDriver) {
			URL seleniumGridUrl = null;
			
			try {
				seleniumGridUrl = new URL(System.getProperty("gridUrl"));
			} catch(MalformedURLException me) {
				System.out.println("Malformed URL");
				me.printStackTrace();
			}
			
			String desiredBrowserVersion = System.getProperty("desiredBrowserVersion");
			String desiredPlatform = System.getProperty("desiredPlatform");
			
			if(desiredPlatform !=null && !desiredPlatform.isEmpty()) {
				desiredCapabilities.setPlatform(Platform.valueOf(desiredPlatform.toUpperCase()));
			}
			
			if(desiredBrowserVersion !=null && !desiredBrowserVersion.isEmpty()) {
				desiredCapabilities.setVersion(desiredBrowserVersion);
			}
			
			this.webdriver = new RemoteWebDriver(seleniumGridUrl, desiredCapabilities);
		}
		else {
			this.webdriver = this.selectedDriverType.getWebDriverObject(desiredCapabilities);
		}
	}
}
