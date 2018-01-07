package com.plamenti.selenium_framework.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;

public class CsvDataProvider {

	public static Object[][] readData(String pathToDataFile) {
		List<String[]> myEntries = null;

		try (CSVReader reader = new CSVReader(new FileReader(pathToDataFile))) {

			myEntries = reader.readAll();

		} catch (FileNotFoundException e) {
			System.err.println(e.getStackTrace());
		} catch (IOException e) {
			System.err.println(e.getStackTrace());
		}

		return convertData(myEntries);
	}

	private static Object[][] convertData(List<String[]> data) {

		Object[][] result = new Object[data.size()][data.get(0).length];
		for (int i = 0; i < data.size(); i++) {
			for (int j = 0; j < data.get(i).length; j++) {
				result[i][j] = data.get(i)[j];
			}
		}

		return result;
	}
}
