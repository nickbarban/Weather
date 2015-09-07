package com.barban.fabrika;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

//@JsonIgnoreProperties(ignoreUnknown = true)
public final class Temperature {
	
	
	String temperature = null;
	
	String city = null;
	
	
	public Temperature(@JsonProperty("temp")String temperature, @JsonProperty("name")String city) {
		this.temperature = temperature;
		this.city = city;
	}

	/*public BigDecimal getTemperature() {
		return temperature;
	}

	public void setTemperature(BigDecimal temperature) {
		this.temperature = temperature;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}*/

	@Override
	public String toString() {
		return city + ": " + temperature;
	}	

}
