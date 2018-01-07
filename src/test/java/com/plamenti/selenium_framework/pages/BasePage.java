package com.plamenti.selenium_framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.plamenti.selenium_framework.base.BaseTestAbstract;

public abstract class BasePage {
	private WebDriver driver;

	public BasePage() {
		this.driver = BaseTestAbstract.getDriver();
		AjaxElementLocatorFactory factory= new AjaxElementLocatorFactory(this.driver, 10);
		PageFactory.initElements(factory, this);
	}

	public WebDriver getDriver() {
		return driver;
	}
	
	public static ExpectedCondition<Boolean> jQueryAJAXCallsHaveCompleted() {
        return new ExpectedCondition<Boolean>() {

            public Boolean apply(WebDriver driver) {
                return (Boolean) ((JavascriptExecutor) driver).executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
            }
        };
	}
	
	protected WebElement findElementWitExplicithWait(By by) {
		WebDriverWait wait = new WebDriverWait(getDriver(), 10, 100);
		wait.until(jQueryAJAXCallsHaveCompleted());
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
		
		return element; 
	}
	
	protected WebElement waitForElement(WebElement element) {
		WebDriverWait wait = new WebDriverWait(getDriver(), 10, 100);
		wait.until(jQueryAJAXCallsHaveCompleted());
		return wait.until(ExpectedConditions.visibilityOf(element));
	}
}
