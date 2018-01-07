package com.plamenti.selenium_framework.listeners;

import java.awt.AWTException;
import java.awt.GraphicsConfiguration;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import org.monte.media.Format;
import org.monte.media.Registry;
import org.monte.screenrecorder.ScreenRecorder;

public class ExtendedScreenRecorder extends ScreenRecorder {
	private final String videoFileName;

	public ExtendedScreenRecorder(GraphicsConfiguration cfg, Rectangle captureArea, Format fileFormat,
			Format screenFormat, Format mouseFormat, Format audioFormat, File movieFolder, String videoFileName)
			throws IOException, AWTException {
		super(cfg, captureArea, fileFormat, screenFormat, mouseFormat, audioFormat, movieFolder);
		this.videoFileName = videoFileName;
	}

	@Override
	protected File createMovieFile(Format fileFormat) throws IOException {
		if (!movieFolder.exists()) {
			movieFolder.mkdirs();
		} else if (!movieFolder.isDirectory()) {
			throw new IOException("\"" + movieFolder + "\" is not a directory.");
		}
		
		return new File(movieFolder, this.videoFileName  + "_" + System.currentTimeMillis() + "."
                + Registry.getInstance().getExtension(fileFormat));
	}
}
