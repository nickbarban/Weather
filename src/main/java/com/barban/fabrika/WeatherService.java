package com.barban.fabrika;

import java.io.IOException;

import org.codehaus.jackson.JsonProcessingException;

public interface WeatherService {
	
	public double getTemperature (String uri) throws JsonProcessingException, IOException;

}
