package com.org.project.twm.service.weather;

import java.util.Map;

/**
 * WeatherService implements an application to define method which have business
 * logic and communicate with controller and weather data.
 * 
 * @author abhishek.sisodiya
 */
public interface WeatherService {

	Map<String, Object> openweathermap(String lat, String longi);

	Map<String, Object> darksky(String lat, String longi);

	Map<String, Object> weatherbit(String lat, String longi);

	Map<String, Object> weatherunlocked(String lat, String longi);

}