package com.plamenti.selenium_framework.listeners;

import static org.monte.media.FormatKeys.EncodingKey;
import static org.monte.media.FormatKeys.FrameRateKey;
import static org.monte.media.FormatKeys.KeyFrameIntervalKey;
import static org.monte.media.FormatKeys.MIME_AVI;
import static org.monte.media.FormatKeys.MediaTypeKey;
import static org.monte.media.FormatKeys.MimeTypeKey;
import static org.monte.media.VideoFormatKeys.CompressorNameKey;
import static org.monte.media.VideoFormatKeys.DepthKey;
import static org.monte.media.VideoFormatKeys.ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE;
import static org.monte.media.VideoFormatKeys.QualityKey;

import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.monte.media.Format;
import org.monte.media.FormatKeys.MediaType;
import org.monte.media.math.Rational;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class VideoRecordListener extends TestListenerAdapter {

	private ExtendedScreenRecorder screenRecorder;
	public final static String ENCODING_BLACK_CURSOR = "black";

	@Override
	public void onTestStart(ITestResult startedTest) {
		try {
			this.startVideoRecording(startedTest.getName());

		} catch (Exception e) {
			System.err.println("Unable to start recording a video onTestStart...");
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSuccess(ITestResult successTest) {
		try {
			List<File> recordedVideos = this.stopVideoRecording();
			if (recordedVideos != null && recordedVideos.size() > 0) {
				for (File file : recordedVideos) {
					file.delete();
				}
			}
		} catch (IOException e) {
			System.err.println("Unable to record video onTestSuccess...");
			e.printStackTrace();
		}
	}

	@Override
	public void onTestFailure(ITestResult failingTest) {
		try {
			this.stopVideoRecording();
		} catch (IOException e) {
			System.err.println("Unable to record video onTestFailure...");
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.err.println("Unable to stop video onTestFailure...");
			e.printStackTrace();
		}
	}

	private void startVideoRecording(String fileName) throws Exception {
		String videoDirectory = System.getProperty("videoDirectory");
		File videoDirectoryAsFile = new File(videoDirectory);

		GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
				.getDefaultConfiguration();

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = screenSize.width;
		int height = screenSize.height;

		Rectangle captureSize = new Rectangle(0, 0, width, height);

		this.screenRecorder = new ExtendedScreenRecorder(gc, captureSize,
				new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
				new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
						CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24, FrameRateKey,
						Rational.valueOf(15), QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60),
				new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_BLACK_CURSOR, FrameRateKey, Rational.valueOf(30)),
				null, videoDirectoryAsFile, fileName);

		this.screenRecorder.start();
	}

	private List<File> stopVideoRecording() throws IOException, NullPointerException {

		if (this.screenRecorder == null) {
			throw new NullPointerException("ScreenRecorder cannot be null");
		}
		
		this.screenRecorder.stop();

		return this.screenRecorder.getCreatedMovieFiles();
	}
}
