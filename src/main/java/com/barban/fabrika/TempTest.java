package com.barban.fabrika;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;



public class TempTest {

	public static void main(String[] args) throws IOException, WeatherException {
		Set<City> cities = null;
		try {
			cities = JacksonReader.readCSVFile(City.class, "cities.csv");
		} catch (WeatherException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (City city : cities) {
			System.out.println(city);
		}
		
		for (City city : cities) {
			URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + city.getName()+ "," + 
							city.getCountry() + "&units=metric");
			System.out.println(JacksonReader.readTemperatureJson(url));
		}
		
	}

}
