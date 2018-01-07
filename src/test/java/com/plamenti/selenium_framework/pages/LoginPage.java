package com.plamenti.selenium_framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage extends BasePage {
	
	private String url = System.getProperty("homePageUrl");

	@FindBy(how = How.ID, using = "f2-ie-mail")
	private WebElement loginEmailField;

	@FindBy(how = How.ID, using = "f2-ip-password")
	private WebElement loginPasswordField;

	@FindBy(how = How.ID, using = "submit-login")
	private WebElement loginButton;

	@FindBy(how = How.ID, using = "f2-ie-mail-error")
	private WebElement emailErrorMessage;

	@FindBy(how = How.ID, using = "f2-ip-password-error")
	private WebElement passwordErrorMessage;

	@FindBy(how = How.ID, using = "background-image")
	private WebElement backgroundImage;

	@FindBy(how = How.XPATH, using = "//div[contains(@class, 'error-pane')]")
	private WebElement emailOrPasswordErrorMessage;

	@FindBy(how = How.XPATH, using = "//a[contains(@class, 'facebook')]")
	private WebElement facebookLoginButton;

	@FindBy(how = How.XPATH, using = "//div[contains(@class, 'googlelogin')]")
	private WebElement googleLoginButton;

	@FindBy(how = How.ID, using = "login")
	private WebElement loginForm;

	private WebElement forgotPasswordLink = waitForElement(
			this.loginForm.findElement(By.xpath("//a[@href='#password-remind']")));

	private WebElement forgotPasswordErrorLink = waitForElement(
			this.emailOrPasswordErrorMessage.findElement(By.xpath("//a[@href='#password-remind']")));

	@FindBy(how = How.ID, using = "f2-icb-remember")
	private WebElement keepMeLoginCheckbox;

	@FindBy(how = How.ID, using = "login-poptip")
	private WebElement loginPopTip;

	private WebElement registerNowLink = waitForElement(
			this.loginForm.findElement(By.xpath("//a[@href='#register']")));;

	public void goTo() {
		this.getDriver().manage().deleteAllCookies();
		this.getDriver().get(url);
	}

	public LoginPage enterEmail(String email) {

		this.loginEmailField.clear();
		this.loginEmailField.sendKeys(email);

		return this;
	}

	public LoginPage enterPassword(String email) {
		this.loginPasswordField.clear();
		this.loginPasswordField.sendKeys(email);

		return this;
	}

	public void andLogin() {
		this.loginButton.click();
	}

	public boolean emailOrPasswordErrorMessageIsPresent() {
		boolean isPresent = false;

		try {
			isPresent = this.emailOrPasswordErrorMessage != null && this.emailOrPasswordErrorMessage.isDisplayed();
		} catch (StaleElementReferenceException e) {
			this.emailOrPasswordErrorMessage = waitForElement(
					this.getDriver().findElement(By.xpath("//div[contains(@class, 'error-pane')]")));

			isPresent = this.emailOrPasswordErrorMessage != null && this.emailOrPasswordErrorMessage.isDisplayed();
		}

		return isPresent;
	}

	public boolean forgotPasswordErrorLinkIsPresent() {
		boolean isPresent = false;
		try {
			isPresent = (this.forgotPasswordErrorLink != null && this.forgotPasswordErrorLink.isDisplayed());
		} catch (StaleElementReferenceException e) {
			forgotPasswordErrorLink = this.emailOrPasswordErrorMessage
					.findElement(By.xpath("//a[@href='#password-remind']"));
			isPresent = (this.forgotPasswordErrorLink != null && this.forgotPasswordErrorLink.isDisplayed());
		}

		return isPresent;
	}

	public boolean loginFormIsPresent() {
		boolean isPresent = false;

		try {
			isPresent = this.loginForm != null && this.loginForm.isDisplayed();
		} catch (StaleElementReferenceException e) {
			this.loginForm = waitForElement(this.getDriver().findElement(By.id("login")));
			isPresent = this.loginForm != null && this.loginForm.isDisplayed();
		}

		return isPresent;
	}

	public boolean emailErrorMessageIsPresent() {
		return this.emailErrorMessage != null && this.emailErrorMessage.isDisplayed();
	}

	public boolean passwordErrorMessageIsPresent() {
		return this.passwordErrorMessage != null && this.passwordErrorMessage.isDisplayed();
	}

	public boolean forgotPasswordLinkIsPresent() {
		boolean isPresent = false;

		try {
			isPresent = this.forgotPasswordLink != null && this.forgotPasswordLink.isDisplayed();
		} catch (StaleElementReferenceException e) {
			this.forgotPasswordLink = waitForElement(
					this.loginForm.findElement(By.xpath("//a[@href='#password-remind']")));

			isPresent = this.forgotPasswordLink != null && this.forgotPasswordLink.isDisplayed();
		}

		return isPresent;
	}

	public boolean keepMeLoginCheckboxIsPresent() {
		return this.keepMeLoginCheckbox != null && this.keepMeLoginCheckbox.isDisplayed();
	}

	public boolean keepMeLoginCheckboxIsSelected() {
		return this.keepMeLoginCheckbox.isSelected();
	}

	public boolean loginEmailFieldIsPresent() {
		return this.loginEmailField != null && this.loginEmailField.isDisplayed();
	}

	public boolean loginPasswordFieldIsPresent() {
		return this.loginPasswordField != null && this.loginPasswordField.isDisplayed();
	}

	public boolean loginButtonIsPresent() {
		return this.loginButton != null && this.loginButton.isDisplayed();
	}

	public boolean loginPopTipIsPresent() {
		return this.loginPopTip != null && this.loginPopTip.isDisplayed();
	}

	public boolean registerNowLinkIsPresent() {
		return this.registerNowLink != null && this.registerNowLink.isDisplayed();
	}
}
