package com.org.project.twm.service.weather.provider;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.org.project.twm.service.weather.factory.Weather;

/**
 *  Concrete class Openweathermap that extends Weather abstract class have parseData 
 *  function which parse our response and returns it.
 * 
 * @author abhishek.sisodiya
 */
public class Weatherbit extends Weather {
	@Override
	public Map<String, Object> parseData(ResponseEntity<Object> response) {
		Map<String, Object> json = new HashMap<String, Object>();
		List<LinkedHashMap<String, Object>> getList = ((List<LinkedHashMap<String, Object>>) ((LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap>>) response
				.getBody()).get("data"));
		json.put("weatherbit", getList);
		return json;
	}
}