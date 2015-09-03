package com.barban.fabrika;

import java.io.IOException;
import java.io.StringReader;

import javax.swing.JOptionPane;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class WeatherServiceImpl implements WeatherService {

	public double getWeather(String city, String countryCode) throws JsonProcessingException, IOException{
		final String uri = "http://api.openweathermap.org/data/2.5/weather?q="
				+ city+ "," + countryCode + "&units=metric"; 
		System.out.println(uri);
		RestTemplate restTemplate = new RestTemplate();
		String json = null;
		JsonNode rootNode = null;
		JsonNode resultNode = null;
		try {
			json = restTemplate.getForObject(uri, String.class);
			rootNode = new ObjectMapper().readTree(new StringReader(json));
			try {
				resultNode = rootNode.get("main").get("temp");
			} catch (NullPointerException e) {
				JOptionPane.showMessageDialog(null, "City with name: " + city + "," + countryCode + " not found", 
						"WeatherServiceImpl Eror", JOptionPane.ERROR_MESSAGE);
			}			
			
		} catch (RestClientException e) {
			JOptionPane.showMessageDialog(null, "Internet connection problem", "WeatherServiceImpl Eror", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}		
		if (resultNode != null) {
			return Double.parseDouble(resultNode.asText());
		} else {
			JOptionPane.showMessageDialog(null, "Temperature for " + city + "," + countryCode + " not found", 
											"WeatherServiceImpl Eror", JOptionPane.ERROR_MESSAGE);
			return Double.MIN_VALUE; 
		}
		
	}

}
