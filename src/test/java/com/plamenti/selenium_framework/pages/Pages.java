package com.plamenti.selenium_framework.pages;

import com.plamenti.selenium_framework.pages.fragments.MainNavigation;

public class Pages {
	public static HomePage getHomePage() {
		return new HomePage();
	}
	
	public static LoginPage getLoginPage() {
		return new LoginPage();
	}
	
	public static MainNavigation getMainNavigation() {
		return new MainNavigation();
	}
}
