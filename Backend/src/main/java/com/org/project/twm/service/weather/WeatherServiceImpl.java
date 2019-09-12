package com.org.project.twm.service.weather;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.org.project.twm.service.weather.factory.Weather;
import com.org.project.twm.service.weather.factory.WeatherApiTypes;
import com.org.project.twm.service.weather.factory.WeatherFactory;
import com.org.project.twm.util.CacheManager;

/**
 * WeatherServiceImpl is an implementation of interface {@link WeatherService}.
 * This service will be used to implement fetch weather.
 * 
 * @author abhishek.sisodiya
 */

@Service("WeatherService")
public class WeatherServiceImpl implements WeatherService {

	@Autowired
	public CacheManager cacheManager;

	private String openweathermapKey = System.getenv("OPENWEATHERMAP_API_KEY_1");
	private String darkskyKey = System.getenv("DARKSKY_API_KEY_1");
	private String weatherunlockedKey = System.getenv("WEATHERUNLOCKED_API_KEY_1");
	private String weatherunlockedappid = System.getenv("WEATHERUNLOCKED_APPID_1");
	private String weatherbitKey = System.getenv("WEATHERBIT_API_KEY_1");
	int owmKeyCount = 1, dsKeyCount = 1, wuKeyCount = 1, wbKeyCount = 1;

	@Override
	public Map<String, Object> openweathermap(String lat, String longi) {
		Map<String, Object> cachedData = cacheManager.get(lat + longi, "openweathermap", Map.class);
		if (cachedData != null) {
			return cachedData;
		} else {
			return callOpenWeatherMap(lat, longi, cacheManager);
		}
	}

	/**
	 * This method is used to fetch data from api.openweathermap.org
	 */
	public Map<String, Object> callOpenWeatherMap(String lat, String longi, CacheManager cacheManager) {
		owmKeyCount++;
		Map<String, Object> json = new HashMap<String, Object>();
		String url = String.format("http://api.openweathermap.org/data/2.5/forecast?id=524901&APPID=%s&lat=%s&lon=%s",
				openweathermapKey, lat, longi);
		RestTemplate restTemplate = new RestTemplate();
		try {
			ResponseEntity<Object> response = restTemplate.getForEntity(url, Object.class);
			Weather weather = WeatherFactory.getWeatherDataParser(WeatherApiTypes.OPENWEATHERMAP);
			json = weather.parseData(response);
			cacheManager.put(lat + longi, "openweathermap", json);
			cacheManager.expire(lat + longi);
		} catch (Exception e) {
			if (owmKeyCount == 2) {
				openweathermapKey = System.getenv("OPENWEATHERMAP_API_KEY_2");
				callOpenWeatherMap(lat, longi, cacheManager);
			} else if (owmKeyCount == 3) {
				openweathermapKey = System.getenv("OPENWEATHERMAP_API_KEY_3");
				callOpenWeatherMap(lat, longi, cacheManager);
			} else if (owmKeyCount == 4) {
				openweathermapKey = System.getenv("OPENWEATHERMAP_API_KEY_4");
				callOpenWeatherMap(lat, longi, cacheManager);
			} else {
				owmKeyCount = 0;
				json.put("openweathermap", e.toString());
			}
		}
		return json;
	}

	@Override
	public Map<String, Object> darksky(String lat, String longi) {

		Map<String, Object> cachedData = cacheManager.get(lat + longi, "darksky", Map.class);
		if (cachedData != null) {
			return cachedData;
		} else {
			return callDarkSky(lat, longi, cacheManager);
		}
	}

	/**
	 * This method is used to fetch data from api.darksky.net
	 */
	public Map<String, Object> callDarkSky(String lat, String longi, CacheManager cacheManager) {
		dsKeyCount++;
		Map<String, Object> json = new HashMap<String, Object>();
		RestTemplate restTemplate = new RestTemplate();
		String url = String.format("https://api.darksky.net/forecast/%s/%s,%s", darkskyKey, lat, longi);
		try {
			ResponseEntity<Object> response = restTemplate.getForEntity(url, Object.class);
			Weather weather = WeatherFactory.getWeatherDataParser(WeatherApiTypes.DARKSKY);
			json = weather.parseData(response);
			cacheManager.put(lat + longi, "darksky", json);
			cacheManager.expire(lat + longi);
		} catch (Exception e) {
			if (dsKeyCount == 2) {
				darkskyKey = System.getenv("DARKSKY_API_KEY_2");
				callDarkSky(lat, longi, cacheManager);
			} else if (dsKeyCount == 3) {
				darkskyKey = System.getenv("DARKSKY_API_KEY_3");
				callDarkSky(lat, longi, cacheManager);
			} else if (dsKeyCount == 4) {
				darkskyKey = System.getenv("DARKSKY_API_KEY_4");
				callDarkSky(lat, longi, cacheManager);
			} else {
				dsKeyCount = 0;
				json.put("darksky", e.toString());
			}
		}
		return json;
	}

	@Override
	public Map<String, Object> weatherbit(String lat, String longi) {
		Map<String, Object> cachedData = (Map<String, Object>) cacheManager.get(lat + longi, "weatherbit", Map.class);
		if (cachedData != null) {
			return cachedData;
		} else {
			return callWeatherBit(lat, longi, cacheManager);
		}
	}

	/**
	 * This method is used to fetch data from api.weatherbit.io
	 */
	public Map<String, Object> callWeatherBit(String lat, String longi, CacheManager cacheManager) {
		wbKeyCount++;
		Map<String, Object> json = new HashMap<String, Object>();
		String url = String.format("https://api.weatherbit.io/v2.0/forecast/daily?lat=%s&lon=%s&key=%s", lat, longi,
				weatherbitKey);
		RestTemplate restTemplate = new RestTemplate();
		try {
			ResponseEntity<Object> response = restTemplate.getForEntity(url, Object.class);
			Weather weather = WeatherFactory.getWeatherDataParser(WeatherApiTypes.WEATHERBIT);
			json = weather.parseData(response);
			cacheManager.put(lat + longi, "weatherbit", json);
			cacheManager.expire(lat + longi);
		} catch (Exception e) {
			if (wbKeyCount == 2) {
				weatherbitKey = System.getenv("WEATHERBIT_API_KEY_2");
				callWeatherBit(lat, longi, cacheManager);
			} else if (wbKeyCount == 3) {
				weatherbitKey = System.getenv("WEATHERBIT_API_KEY_3");
				callWeatherBit(lat, longi, cacheManager);
			} else if (wbKeyCount == 4) {
				weatherbitKey = System.getenv("WEATHERBIT_API_KEY_4");
				callWeatherBit(lat, longi, cacheManager);
			} else {
				wbKeyCount = 0;
				json.put("accuweather", e.toString());
			}
		}
		return json;

	}

	@Override
	public Map<String, Object> weatherunlocked(String lat, String longi) {

		Map<String, Object> cachedData = cacheManager.get(lat + longi, "weatherunlocked", Map.class);
		if (cachedData != null) {
			return cachedData;
		} else {
			return callWeatherUnlocked(lat, longi, cacheManager);
		}
	}

	/**
	 * This method is used to fetch data from api.weatherunlocked.com
	 */
	public Map<String, Object> callWeatherUnlocked(String lat, String longi, CacheManager cacheManager) {
		wuKeyCount++;
		String url = String.format(
				"http://api.weatherunlocked.com/api/forecast/" + lat + "," + longi + "?app_id=%s&app_key=%s",
				weatherunlockedappid, weatherunlockedKey);
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			ResponseEntity<Object> response = restTemplate.getForEntity(url, Object.class);
			Weather weather = WeatherFactory.getWeatherDataParser(WeatherApiTypes.WEATHERUNLOCKED);
			json = weather.parseData(response);
			cacheManager.put(lat + longi, "weatherunlocked", json);
			cacheManager.expire(lat + longi);
		} catch (Exception e) {
			if (wuKeyCount == 2) {
				weatherunlockedKey = System.getenv("WEATHERUNLOCKED_API_KEY_2");
				weatherunlockedappid = System.getenv("WEATHERUNLOCKED_APPID_2");
				callWeatherUnlocked(lat, longi, cacheManager);
			} else if (wuKeyCount == 3) {
				weatherunlockedKey = System.getenv("WEATHERUNLOCKED_API_KEY_3");
				weatherunlockedappid = System.getenv("WEATHERUNLOCKED_APPID_3");
				callWeatherUnlocked(lat, longi, cacheManager);
			} else if (wuKeyCount == 4) {
				weatherunlockedKey = System.getenv("WEATHERUNLOCKED_API_KEY_4");
				weatherunlockedappid = System.getenv("WEATHERUNLOCKED_APPID_4");
				callWeatherUnlocked(lat, longi, cacheManager);
			} else {
				wuKeyCount = 0;
				json.put("weatherunlocked", e.toString());
			}
		}
		return json;
	}

}
