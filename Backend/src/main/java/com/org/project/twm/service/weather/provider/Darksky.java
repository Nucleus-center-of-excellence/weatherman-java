package com.org.project.twm.service.weather.provider;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.org.project.twm.service.weather.factory.Weather;

/**
 *  Concrete class Darksky that extends Weather abstract class have parseData 
 *  function which parse our response and returns it.
 * 
 * @author abhishek.sisodiya
 */
public class Darksky extends Weather {
	@Override
	public Map<String, Object> parseData(ResponseEntity<Object> response) {
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("darksky", response.getBody());
		return json;
	}
}
