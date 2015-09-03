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

	public double getTemperature(String uri) throws JsonProcessingException, IOException, NumberFormatException, NullPointerException{
		double result = Double.MIN_VALUE;
		RestTemplate restTemplate = new RestTemplate();
		String json = restTemplate.getForObject(uri, String.class);
		JsonNode rootNode = new ObjectMapper().readTree(new StringReader(json));
		JsonNode resultNode = rootNode.get("main").get("temp");
		
		if (resultNode != null) {
			result = Double.parseDouble(resultNode.asText());
		} else {
			throw new NullPointerException();
		}
		return result;
	}

}
