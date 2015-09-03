package com.barban.fabrika;

import java.io.IOException;

import org.codehaus.jackson.JsonProcessingException;

public interface WeatherService {
	
	public double getWeather (String city, String countryCode) throws JsonProcessingException, IOException;

}
