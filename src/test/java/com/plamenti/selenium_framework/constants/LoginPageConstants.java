package com.plamenti.selenium_framework.constants;

import com.plamenti.selenium_framework.config.managers.PropertiesManager;

public class LoginPageConstants {
	public static final String EMPTY_LOGIN_FIELDS_ERROR_MESSAGE = "This is a required field.";
	public static final String VALID_EMAIL = PropertiesManager.getPageProperty("valid.email");
	public static final String VALID_PASSWORD = PropertiesManager.getPageProperty("valid.password");
	public static final String INVALID_EMAIL = PropertiesManager.getPageProperty("invalid.email");
	public static final String INVALID_PASSWORD = PropertiesManager.getPageProperty("invalid.password");
}
