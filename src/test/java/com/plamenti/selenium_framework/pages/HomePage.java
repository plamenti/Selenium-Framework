package com.plamenti.selenium_framework.pages;

public class HomePage extends BasePage{

	private String url = System.getProperty("homePageUrl");
	
	public HomePage goTo() {
		this.getDriver().get(url);
		
		return this;
	}

}
