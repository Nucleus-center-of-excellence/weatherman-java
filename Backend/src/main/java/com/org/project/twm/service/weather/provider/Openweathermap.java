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
public class Openweathermap extends Weather {
	@Override
	public Map<String, Object> parseData(ResponseEntity<Object> response) {
		Map<String, Object> json = new HashMap<String, Object>();
		Map<String, Object> json1 = new HashMap<String, Object>();
		List<LinkedHashMap<String, Object>> getList = ((List<LinkedHashMap<String, Object>>) ((LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap>>) response
				.getBody()).get("list"));
		LinkedHashMap<String, Object> getList1 = getList.get(0);
		json1.put("0", getList.get(0));
		json1.put("1", getList.get(6));
		json1.put("2", getList.get(13));
		json1.put("3", getList.get(22));
		json1.put("4", getList.get(29));
		json.put("openweathermap", json1);
		return json;
	}
}
