package com.barban.fabrika;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashSet;
import java.util.Set;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

/*
 * This class can read json or csv-file and return POJO
 */

public final class JacksonReader {
	
	public static <T> Set<T> readCSVFile(Class<T> type, String path) throws WeatherException {
		Set<T> cities = new LinkedHashSet();
		
		CsvMapper mapper = new CsvMapper();
		CsvSchema schema = CsvSchema.emptySchema().withHeader();//mapper.schemaFor(type);
		MappingIterator<T> iterator;
		try {
			iterator = mapper.reader(type).with(schema).readValues(new File(path));
		} catch (JsonProcessingException e) {
			throw new WeatherException (e, JacksonReader.class.getName());
		} catch (IOException e) {
			throw new WeatherException (e, JacksonReader.class.getName());
		}
		
		while (iterator.hasNext()) {
			cities.add(iterator.next());
		}
		
		return cities;		
	}
	
	public static Temperature readTemperatureJson (URL url) throws WeatherException {		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			mapper.generateJsonSchema(Temperature.class);
			mapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return mapper.readValue(url, Temperature.class);
		} catch (JsonParseException e) {
			throw new WeatherException (e, JacksonReader.class.getName());
		} catch (JsonMappingException e) {
			throw new WeatherException (e, JacksonReader.class.getName());
		} catch (IOException e) {
			throw new WeatherException (e, JacksonReader.class.getName());
		}
	}

}
