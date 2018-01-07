package com.plamenti.selenium_framework.tests;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.Test;

import com.plamenti.selenium_framework.base.BaseTestAbstract;
import com.plamenti.selenium_framework.constants.LoginPageConstants;
import com.plamenti.selenium_framework.pages.HomePage;
import com.plamenti.selenium_framework.pages.LoginPage;
import com.plamenti.selenium_framework.pages.PageFactory;
import com.plamenti.selenium_framework.pages.fragments.MainNavigation;

//@Listeners(ScreenShotListener.class)
public class HomePageTestsIT extends BaseTestAbstract {

	@Test
	public void loginFormTestIT() {
		// Given
		homePage = PageFactory.getHomePage();//new HomePage();
		// When
		homePage.goTo();
		loginPage = PageFactory.getLoginPage(); //new LoginPage();
		// Then
		assertThat(loginPage.loginFormIsPresent()).as("Login form is present").isTrue();
		assertThat(loginPage.loginEmailFieldIsPresent()).as("Login email field is present").isTrue();
		assertThat(loginPage.loginPasswordFieldIsPresent()).as("Login password field is present").isTrue();
		assertThat(loginPage.loginButtonIsPresent()).as("Login button is present").isTrue();
	}
	
	@Test
	public void homePageLoginWithValidCredentialsTestIT() {
		// Given
		homePage =  PageFactory.getHomePage();// new HomePage();
		homePage.goTo();
		
		loginPage = PageFactory.getLoginPage(); //new LoginPage();
		// When
		loginPage.enterEmail(LoginPageConstants.VALID_EMAIL).enterPassword(LoginPageConstants.VALID_PASSWORD)
				.andLogin();
		// Then
		mainNavigation = PageFactory.getMainNavigation(); //new MainNavigation();
		assertThat(mainNavigation.mainNavigationIsPresent()).isTrue();
		assertThat(mainNavigation.homeButtonIsPresent()).isTrue();
		assertThat(mainNavigation.promotionsButtonIsPresent()).isTrue();
		assertThat(mainNavigation.logoIsPresent()).isTrue();
		assertThat(mainNavigation.myAccountButtonIsPresent()).isTrue();
		assertThat(mainNavigation.bagButtonIsPresent()).isTrue();
	}

//	@Test
//	public void homePageLoginWithInvalidEmailAndValidPasswordTestIT() {
//		// Given
//		homePage = PageFactory.getHomePage();// new HomePage();
//		homePage.goTo();
//
//		// When
//		loginPage = PageFactory.getLoginPage(); //new LoginPage();
//		loginPage.enterEmail(LoginPageConstants.INVALID_EMAIL).enterPassword(LoginPageConstants.VALID_PASSWORD)
//				.andLogin();
//		
//
//		// Then
//		assertThat(loginPage.emailOrPasswordErrorMessageIsPresent()).isTrue();
//		assertThat(loginPage.loginFormIsPresent()).isTrue();
//		assertThat(loginPage.forgotPasswordErrorLinkIsPresent()).isTrue();
//		assertThat(loginPage.forgotPasswordLinkIsPresent()).isTrue();
//	}
//
//	@Test
//	public void homePageLoginWithValidEmailAndInvalidPasswordTestIT() {
//		// Given
//		homePage = PageFactory.getHomePage();// new HomePage();
//		homePage.goTo();
//
//		// When
//		loginPage = PageFactory.getLoginPage(); //new LoginPage();
//		loginPage.enterEmail(LoginPageConstants.VALID_EMAIL).enterPassword(LoginPageConstants.INVALID_PASSWORD)
//				.andLogin();
//
//		// Then
//		assertThat(loginPage.emailOrPasswordErrorMessageIsPresent()).isTrue();
//		assertThat(loginPage.loginFormIsPresent()).isTrue();
//		assertThat(loginPage.forgotPasswordErrorLinkIsPresent()).isTrue();
//		assertThat(loginPage.forgotPasswordLinkIsPresent()).isTrue();
//	}
//
//	@Test
//	public void homePageLoginWithEmptyEmailTestIT() {
//		// Given
//		homePage = PageFactory.getHomePage();// new HomePage();
//		homePage.goTo();
//
//		// When
//		loginPage = PageFactory.getLoginPage(); //new LoginPage();
//		loginPage.enterEmail("").enterPassword(LoginPageConstants.VALID_PASSWORD).andLogin();
//
//		// Then
//		assertThat(loginPage.emailErrorMessageIsPresent()).isTrue();
//	}
//
//	@Test
//	public void homePageLoginWithEmptyPasswordTestIT() {
//		// Given
//		homePage = PageFactory.getHomePage();// new HomePage();
//		homePage.goTo();
//
//		// When
//		loginPage = PageFactory.getLoginPage(); //new LoginPage();
//		loginPage.enterEmail(LoginPageConstants.VALID_EMAIL).enterPassword("").andLogin();
//
//		// Then
//		assertThat(loginPage.passwordErrorMessageIsPresent()).isTrue();
//		assertThat(loginPage.forgotPasswordLinkIsPresent()).isTrue();
//	}
}