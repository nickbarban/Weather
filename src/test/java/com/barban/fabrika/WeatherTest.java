package com.barban.fabrika;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.codehaus.jackson.JsonProcessingException;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

public class WeatherTest {

	@Test
	public void getWeatherOdessaTest() {
		double testValue = Double.MIN_VALUE;
		String uri = "http://api.openweathermap.org/data/2.5/weather?q=Odessa,UA&units=metric";
		try {
			testValue = new WeatherServiceImpl().getTemperature(uri);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertFalse(testValue == Double.MIN_VALUE);
	}

	@Test(expected = NullPointerException.class)
	public void getWeatherWrongCityTest() {
		double testValue = Double.MIN_VALUE;
		String uri = "http://api.openweathermap.org/data/2.5/weather?q=Nocity,UA&units=metric";
		try {
			testValue = new WeatherServiceImpl().getTemperature(uri);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test(expected = IOException.class)
	public void makeCitiesMapTest() throws IOException {
		CitiesReader.makeCitiesMap("");
	}

	
	/*
	 * @Test(expected = IOException.class) public void makeCitiesMapTest() {
	 * 
	 * }
	 */

	/*
	 * private static double getTodayOdessaTemp() { String todayTempOdessaJson =
	 * new RestTemplate().getForObject(
	 * "http://api.openweathermap.org/data/2.5/weather?q=Odessa,UA&units=metric",
	 * String.class); List<String> list =
	 * Arrays.asList(todayTempOdessaJson.split(",")); for (String string : list)
	 * { if (string.contains("temp")) { String tmp =
	 * string.substring(string.length()-5); //System.out.println(tmp); return
	 * Double.parseDouble(tmp); } } return Double.MIN_VALUE; }
	 */
}
