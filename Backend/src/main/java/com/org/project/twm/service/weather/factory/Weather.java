package com.org.project.twm.service.weather.factory;

import java.util.Map;

import org.springframework.http.ResponseEntity;

/**
 * abstract class Weather is the parent of all classes.
 * use abstract method parseData to parse response.
 * 
 * @author abhishek.sisodiya
 */
public abstract class Weather {
	public abstract Map<String, Object> parseData(ResponseEntity<Object> response);
}
