package com.plamenti.selenium_framework.listeners;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.Augmenter;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.plamenti.selenium_framework.base.BaseTestAbstract;

public class ScreenShotListener extends TestListenerAdapter {

	@Override
	public void onTestFailure(ITestResult failingTest) {
		try {
			WebDriver driver = BaseTestAbstract.getDriver();
			String screenshotDirectory = System.getProperty("screenshotDirectory");
			String screenshotAbsolutePath = screenshotDirectory + File.separator + System.currentTimeMillis() + "_"
					+ failingTest.getName() + ".png";
			File screenshot = new File(screenshotAbsolutePath);

			if (this.createFile(screenshot)) {
				try {
					writeScreenshotToFile(driver, screenshot);
				} catch (ClassCastException weNeedToAugmentOurDriverObject) {
					writeScreenshotToFile(new Augmenter().augment(driver), screenshot);
				}
				System.out.println("Written screenshot to " + screenshotAbsolutePath);
			} else {
				System.err.println("Unable to create " + screenshotAbsolutePath);
			}
		} catch (Exception e) {
			System.err.println("Unable to capture screenshot...");
			e.printStackTrace();
		}
	}

	private boolean createFile(File screenshot) throws IOException {
		boolean fileCreated = false;

		if (screenshot.exists()) {
			fileCreated = true;
		} else {
			File parrentDirectory = new File(screenshot.getParent());
			if (parrentDirectory.exists() || parrentDirectory.mkdirs()) {
				fileCreated = screenshot.createNewFile();
			}
		}

		return fileCreated;
	}

	private void writeScreenshotToFile(WebDriver driver, File screenshot) throws WebDriverException, IOException {
		FileOutputStream fileOutputStream = new FileOutputStream(screenshot);
		fileOutputStream.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
		fileOutputStream.close();
	}
}
