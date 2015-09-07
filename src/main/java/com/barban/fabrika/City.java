package com.barban.fabrika;

import com.google.common.collect.ComparisonChain;

public final class City implements Comparable<City>{
		
	private String name;
	private String country;
	
	public City() {
	}

	public City(String name, String country) {
		this.name = name;
		this.country= country;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountryCode(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return name + ", " + country;
	}

	
	public int compareTo(City o) {
		// TODO Auto-generated method stub
		return ComparisonChain.start().compare(this.country, o.country).compare(this.name, o.name).result();
	}
	
	

}
