package com.barban.fabrika;

import com.fasterxml.jackson.core.JsonProcessingException;

public class WeatherException extends Exception {
	
	String invokedClassName = "";

	public WeatherException(Exception e, String invokedClassName) {
		super(e);
		this.invokedClassName = invokedClassName;
	}
	
	
}
