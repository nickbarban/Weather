package com.barban.fabrika;

import java.awt.TextArea;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.codehaus.jackson.JsonProcessingException;

/**
 * Hello world!
 *
 */
public class WeatherMain {

	private static Map<String, String> cities = new LinkedHashMap<String, String>();
	private static WeatherServiceImpl wsi = new WeatherServiceImpl();
	private static Map<String, Double> tempMap = new LinkedHashMap<String, Double>();
	private static JFrame frame = new JFrame("Cities' temperatures for today");
	private static TextArea textArea = new TextArea();

	public static void main(String[] args) {
		// System.out.println( "Hello World!" );
		try {
			cities = CitiesReader.makeCitiesMap("cities.csv");
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "CitiesReader Exception", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}

		if (cities != null) {
			for (String cityName : cities.keySet()) {

				String uri = "http://api.openweathermap.org/data/2.5/weather?q=" + cityName + "," + cities.get(cityName)
						+ "&units=metric";
				try {
					tempMap.put(cityName, wsi.getTemperature(uri));
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, cityName + e.getMessage(), "NumberFormatException", JOptionPane.ERROR_MESSAGE);
					//e.printStackTrace();
				} catch (JsonProcessingException e) {
					JOptionPane.showMessageDialog(null, cityName + e.getMessage(), "JsonProcessingException", JOptionPane.ERROR_MESSAGE);
					//e.printStackTrace();
				} catch (NullPointerException e) {
					JOptionPane.showMessageDialog(null, "No such city: " + cityName, "NullPointerException", JOptionPane.ERROR_MESSAGE);
					
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, cityName + e.getMessage(), "IOException", JOptionPane.ERROR_MESSAGE);
					//e.printStackTrace();
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();

				
		if (tempMap.size() > 0) {
			for (String cityName : tempMap.keySet()) {
				sb.append((cityName.length() > 8 ? cityName + ":\t" : cityName + ":\t\t") + tempMap.get(cityName)
						+ "\r\n");
			}
		}
		sb.append("------------------------------------------------------------\r\n");
		
		if (tempMap.size() > 0) {
			double averageTemp = count(tempMap);
			sb.append("Average: \t\t" + new BigDecimal(averageTemp).setScale(2, RoundingMode.HALF_UP) + "\r\n");
		} else sb.append("No information about cities' temperatures");

		textArea.setText(sb.toString());
		frame.getContentPane().add(textArea);
		frame.setSize(400, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	private static double count(Map<String, Double> map) {
		double sum = 0;
		for (Double temp : map.values()) {
			sum += temp;
		}
		return sum / map.size();
	}
}
