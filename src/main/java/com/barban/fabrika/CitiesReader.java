package com.barban.fabrika;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public final class CitiesReader {
	
	private static BufferedReader reader = null;
	
	private CitiesReader() {
		
	}
	
	public static Map<String, String> makeCitiesMap() throws IOException {
		Map<String, String> result = new LinkedHashMap<String, String>();
		reader = new BufferedReader(new FileReader("cities.csv"));
		String tmpStr = reader.readLine();
		while (tmpStr != null) {
			System.out.println("CitiesReader: tmpStr: " + tmpStr);
			String[] tmpStrArr = tmpStr.split(";");
			if (tmpStrArr.length > 1) {
				result.put(tmpStrArr[0], tmpStrArr[1]);
			}
			tmpStr = reader.readLine();
		}
		return result;
	}

}
