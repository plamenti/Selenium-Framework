package com.plamenti.selenium_framework.pages.fragments;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.plamenti.selenium_framework.pages.BasePage;

public class MainNavigation extends BasePage {

	@FindBy(how = How.ID, using = "mainNavWrapper")
	private WebElement mainNavigationWrapper;

	@FindBy(how = How.XPATH, using = "//*[contains(@class,'logo___icon')]")
	private WebElement logo;

	@FindBy(how = How.XPATH, using = "//div[contains(@class, 'myAccountNav')]")
	private WebElement myAccountButton;

	@FindBy(how = How.XPATH, using = "//div[contains(@class, 'cartNav') and contains(@class,'wrapper')]")
	private WebElement bagButton;

	@FindBy(how = How.XPATH, using = "//div[contains(@class, 'campaignsButton')]")
	private WebElement promotionsButton;

	@FindBy(how = How.XPATH, using = "//div[contains(@class, 'homeNav')]")
	private WebElement homeButton;

	public boolean mainNavigationIsPresent() {
		return this.mainNavigationWrapper != null && this.mainNavigationWrapper.isDisplayed();
	}
	
	public boolean logoIsPresent() {
		return this.logo != null && this.logo.isDisplayed();
	}
	
	public boolean myAccountButtonIsPresent() {
		return this.myAccountButton != null && this.myAccountButton.isDisplayed();
	}
	
	public boolean bagButtonIsPresent() {
		return this.bagButton != null && this.bagButton.isDisplayed();
	}
	
	public boolean promotionsButtonIsPresent() {
		return this.promotionsButton != null && this.promotionsButton.isDisplayed();
	}
	
	public boolean homeButtonIsPresent() {
		return this.homeButton != null && this.homeButton.isDisplayed();
	}
}
