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
	private static Map<String, Double> tempMap= new LinkedHashMap<String, Double>();
	private static JFrame frame = new JFrame("Cities' temperatures for today");
	private static TextArea textArea = new TextArea();
	
	
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        try {
			cities = CitiesReader.makeCitiesMap();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e1.getMessage(), "CitiesReader IOException", JOptionPane.ERROR_MESSAGE);
		}
        
        if (cities != null) {
        	for (String cityName : cities.keySet()) {
        		
                try {
                	tempMap.put(cityName, wsi.getWeather(cityName, cities.get(cityName)));
        		} catch (JsonProcessingException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		} catch (IOException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
			}
        }
        
        double averageTemp = count(tempMap);
        
        StringBuilder sb = new StringBuilder();
        if (tempMap.size() > 0) {
        	for (String cityName : tempMap.keySet()) {
        		sb.append((cityName.length() > 8 ? cityName + ":\t" : cityName + ":\t\t") + tempMap.get(cityName) + "\r\n");
			}
        }
        sb.append("------------------------------------------------------------\r\n");
        sb.append("Average: \t\t" + new BigDecimal(averageTemp).setScale(2, RoundingMode.HALF_UP) + "\r\n");
        
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
		return sum/map.size();
	}
}
