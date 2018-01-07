package com.plamenti.selenium_framework.config.managers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class PropertiesManager {
	public static String getPageProperty(String propertyName) {
		Properties config = loadProperties(System.getProperty("pagesProperties"));
		String property = config.getProperty(propertyName);

		return property.toString();
	}

	private static Properties loadProperties(String url) {
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(url));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
}
