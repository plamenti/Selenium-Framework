package com.plamenti.selenium_framework.tests;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.plamenti.selenium_framework.base.BaseTestAbstract;
import com.plamenti.selenium_framework.constants.LoginPageConstants;
import com.plamenti.selenium_framework.pages.Pages;
import com.plamenti.selenium_framework.utils.CsvDataProvider;


//@Listeners(ScreenShotListener.class)
public class HomePageTestsIT extends BaseTestAbstract {

	@Test
	public void loginFormTestIT() {
		// Given
		homePage = Pages.getHomePage();
		// When
		homePage.goTo();
		loginPage = Pages.getLoginPage();
		// Then
		assertThat(loginPage.loginFormIsPresent()).as("Login form is present").isTrue();
		assertThat(loginPage.loginEmailFieldIsPresent()).as("Login email field is present").isTrue();
		assertThat(loginPage.loginPasswordFieldIsPresent()).as("Login password field is present").isTrue();
		assertThat(loginPage.loginButtonIsPresent()).as("Login button is present").isTrue();
	}

	@Test
	public void homePageLoginWithValidCredentialsTestIT() {
		// Given
		homePage = Pages.getHomePage();
		homePage.goTo();

		loginPage = Pages.getLoginPage();
		// When
		loginPage.enterEmail(LoginPageConstants.VALID_EMAIL).enterPassword(LoginPageConstants.VALID_PASSWORD)
				.andLogin();
		// Then
		mainNavigation = Pages.getMainNavigation();
		assertThat(mainNavigation.mainNavigationIsPresent()).isTrue();
		assertThat(mainNavigation.homeButtonIsPresent()).isTrue();
		assertThat(mainNavigation.promotionsButtonIsPresent()).isTrue();
		assertThat(mainNavigation.logoIsPresent()).isTrue();
		assertThat(mainNavigation.myAccountButtonIsPresent()).isTrue();
		assertThat(mainNavigation.bagButtonIsPresent()).isTrue();
	}

	@Test
	public void homePageLoginWithInvalidEmailAndValidPasswordTestIT() {
		// Given
		homePage = Pages.getHomePage();
		homePage.goTo();

		// When
		loginPage = Pages.getLoginPage();
		loginPage.enterEmail(LoginPageConstants.INVALID_EMAIL).enterPassword(LoginPageConstants.VALID_PASSWORD)
				.andLogin();

		// Then
		assertThat(loginPage.emailOrPasswordErrorMessageIsPresent()).isTrue();
		assertThat(loginPage.loginFormIsPresent()).isTrue();
		assertThat(loginPage.forgotPasswordErrorLinkIsPresent()).isTrue();
		assertThat(loginPage.forgotPasswordLinkIsPresent()).isTrue();
	}

	@Test
	public void homePageLoginWithValidEmailAndInvalidPasswordTestIT() {
		// Given
		homePage = Pages.getHomePage();
		homePage.goTo();

		// When
		loginPage = Pages.getLoginPage();
		loginPage.enterEmail(LoginPageConstants.VALID_EMAIL).enterPassword(LoginPageConstants.INVALID_PASSWORD)
				.andLogin();

		// Then
		assertThat(loginPage.emailOrPasswordErrorMessageIsPresent()).isTrue();
		assertThat(loginPage.loginFormIsPresent()).isTrue();
		assertThat(loginPage.forgotPasswordErrorLinkIsPresent()).isTrue();
		assertThat(loginPage.forgotPasswordLinkIsPresent()).isTrue();
	}

	@Test
	public void homePageLoginWithEmptyEmailTestIT() {
		// Given
		homePage = Pages.getHomePage();
		homePage.goTo();

		// When
		loginPage = Pages.getLoginPage();
		loginPage.enterEmail("").enterPassword(LoginPageConstants.VALID_PASSWORD).andLogin();

		// Then
		assertThat(loginPage.emailErrorMessageIsPresent()).isTrue();
	}

	@Test
	public void homePageLoginWithEmptyPasswordTestIT() {
		// Given
		homePage = Pages.getHomePage();
		homePage.goTo();

		// When
		loginPage = Pages.getLoginPage();
		loginPage.enterEmail(LoginPageConstants.VALID_EMAIL).enterPassword("").andLogin();

		// Then
		assertThat(loginPage.passwordErrorMessageIsPresent()).isTrue();
		assertThat(loginPage.forgotPasswordLinkIsPresent()).isTrue();
	}

	@Test(dataProvider = "provideData")
	public void homePageLoginWithInvalidEmailAndInvalidPasswordTestIT(String email, String password) {
		// Given
		homePage = Pages.getHomePage();
		homePage.goTo();

		// When
		loginPage = Pages.getLoginPage();
		loginPage.enterEmail(email).enterPassword(password).andLogin();
		
		// Then
		assertThat(loginPage.emailOrPasswordErrorMessageIsPresent()).isTrue();
		assertThat(loginPage.loginFormIsPresent()).isTrue();
		assertThat(loginPage.forgotPasswordErrorLinkIsPresent()).isTrue();
		assertThat(loginPage.forgotPasswordLinkIsPresent()).isTrue();
	}

	@DataProvider
	public Object[][] provideData() {
		
		return CsvDataProvider.readData(LoginPageConstants.PATH_TO_CSV_FILE);
	}
}
