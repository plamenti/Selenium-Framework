package com.plamenti.selenium_framework.config;

import java.util.Arrays;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.EdgeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import io.github.bonigarcia.wdm.OperaDriverManager;

public enum DriverType implements  DriverSetup{
	CHROME {
		public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
			ChromeDriverManager.getInstance().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			options.merge(capabilities);
			options.setHeadless(Boolean.getBoolean("headless"));
			
			return new ChromeDriver(options);
		}

		public DesiredCapabilities getDesiredCapabilities() {
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability("chrome.switches", Arrays.asList("--no-default-browser-check"));
			HashMap<String, String> chromePreferences = new HashMap<String, String>();
			chromePreferences.put("credentials_enable_service", "false");
			chromePreferences.put("profile.password_manager_enabled", "false");
			capabilities.setCapability("chrome.prefs", chromePreferences);
			
			return capabilities;
		}
	},
	EDGE {
		public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
			EdgeDriverManager.getInstance().setup();
			EdgeOptions options = new EdgeOptions();
			options.merge(capabilities);

			return new EdgeDriver();
		}

		public DesiredCapabilities getDesiredCapabilities() {
			DesiredCapabilities capabilities = DesiredCapabilities.edge();
			
			return capabilities;
		}
	},
	FIREFOX {
		public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
			FirefoxDriverManager.getInstance().setup();
			FirefoxOptions options = new FirefoxOptions();
			options.setHeadless(Boolean.getBoolean("headless"));
			options.merge(capabilities);

			return new FirefoxDriver(options);
		}

		public DesiredCapabilities getDesiredCapabilities() {
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			
			return capabilities;
		}
	},
	IE {

		public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
			InternetExplorerDriverManager.getInstance().setup();
			InternetExplorerOptions options = new InternetExplorerOptions();
			options.merge(capabilities);

			return new InternetExplorerDriver(options);
		}

		public DesiredCapabilities getDesiredCapabilities() {

			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
			capabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
			capabilities.setCapability("requireWindowFocus", true);
			
			return capabilities;
		}
	},
	OPERA{

		public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
			OperaDriverManager.getInstance().setup();
			OperaOptions options = new OperaOptions();
			options.merge(capabilities);
			
			return new OperaDriver(options);
		}

		public DesiredCapabilities getDesiredCapabilities() {
			DesiredCapabilities capabilities = DesiredCapabilities.operaBlink();
			
            return capabilities;
		};
	
	}

}
