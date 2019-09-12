package com.org.project.twm.service;

import static com.org.project.twm.exception.EntityType.PROVIDER;
import static com.org.project.twm.exception.ExceptionType.ENTITY_EXCEPTION;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.project.twm.exception.EntityType;
import com.org.project.twm.exception.ExceptionType;
import com.org.project.twm.exception.TWMException;
import com.org.project.twm.service.weather.WeatherServiceImpl;
import com.org.project.twm.util.CacheManager;

/**
 * AverageProviderServiceImpl is an implementation of interface
 * {@link AverageProviderService}. This service will be used to implement fetch
 * the average of all the providers.
 * 
 * @author abhishek.sisodiya
 */

@Service("AverageProviderService")
public class AverageProviderServiceImpl implements AverageProviderService {

	@Autowired
	private CacheManager cacheManager;
	String MAX_DESC = "";
	String MAX_ICON = "";
	WeatherServiceImpl weatherServiceImpl = new WeatherServiceImpl();
	HashMap<String, HashMap<String, String>> openWeatherMap = new HashMap<String, HashMap<String, String>>();
	HashMap<String, HashMap<String, String>> weatherUnlocked = new HashMap<String, HashMap<String, String>>();
	HashMap<String, HashMap<String, String>> darkSky = new HashMap<String, HashMap<String, String>>();
	HashMap<String, HashMap<String, String>> weatherBit = new HashMap<String, HashMap<String, String>>();
	HashMap<String, HashMap<String, String>> AvgWeather = new HashMap<String, HashMap<String, String>>();
	int countOWM = 0, countDS = 0, countWB = 0, countWU = 0;

	@Override
	public HashMap<String, HashMap<String, String>> averageProvider(String lat, String longi) {

		callOpenWeatherMap(lat, longi);
		callDarkSky(lat, longi);
		callWeatherBit(lat, longi);
		callWeatherUnlocked(lat, longi);

		for (int i = 0; i < 5; i++) {
			HashMap<String, String> finalMap = new HashMap<String, String>();
			final Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, i);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);

			int count = 0;
			double minTempWB = 0, maxTempWB = 0, pressureWB = 0, preciWB = 0, windWB = 0, minTempOWM = 0, minTempAW = 0,
					minTempWU = 0, minTempDS = 0, maxTempAW = 0, maxTempOWM = 0, maxTempWU = 0, maxTempDS = 0,
					windAW = 0, windWU = 0, windOWM = 0, windDS = 0, preciDS = 0, preciAW = 0, humidDS = 0, humidWU = 0,
					humidOWM = 0, pressureDS = 0, pressureOWM = 0;
			List elements = new ArrayList<String>();
			List descVote = new ArrayList<String>();
			List iconVote = new ArrayList<String>();

			if (weatherUnlocked.containsKey(cal.getTime().toString())) {
				minTempWU = (Double.parseDouble(weatherUnlocked.get(cal.getTime().toString()).get("minTemp")));
				maxTempWU = (Double.parseDouble(weatherUnlocked.get(cal.getTime().toString()).get("maxTemp")));
				windWU = Double.parseDouble(weatherUnlocked.get(cal.getTime().toString()).get("wind"));
				humidWU = Double.parseDouble(weatherUnlocked.get(cal.getTime().toString()).get("humid"));
				elements.add(weatherUnlocked.get(cal.getTime().toString()).get("description"));
			}
			if (darkSky.containsKey(cal.getTime().toString())) {
				minTempDS = ((Double.parseDouble(darkSky.get(cal.getTime().toString()).get("minTemp")) - 32) * 5) / 9;
				maxTempDS = ((Double.parseDouble(darkSky.get(cal.getTime().toString()).get("maxTemp")) - 32) * 5) / 9;
				windDS = Double.parseDouble(darkSky.get(cal.getTime().toString()).get("wind"));
				preciDS = Double.parseDouble(darkSky.get(cal.getTime().toString()).get("precipitation"));
				humidDS = Double.parseDouble(darkSky.get(cal.getTime().toString()).get("humid"));
				pressureDS = Double.parseDouble(darkSky.get(cal.getTime().toString()).get("pressure"));
				elements.add(darkSky.get(cal.getTime().toString()).get("description"));
			}
			if (weatherBit.containsKey(cal.getTime().toString())) {
				minTempWB = Double.parseDouble(weatherBit.get(cal.getTime().toString()).get("minTemp"));
				maxTempWB = Double.parseDouble(weatherBit.get(cal.getTime().toString()).get("maxTemp"));
				windWB = Double.parseDouble(weatherBit.get(cal.getTime().toString()).get("wind"));
				preciWB = Double.parseDouble(weatherBit.get(cal.getTime().toString()).get("precipitation"));
				pressureWB = Double.parseDouble(weatherBit.get(cal.getTime().toString()).get("pressure"));
				elements.add(weatherBit.get(cal.getTime().toString()).get("description"));
			}
			if (openWeatherMap.containsKey(cal.getTime().toString())) {
				minTempOWM = Double.parseDouble(openWeatherMap.get(cal.getTime().toString()).get("minTemp")) - 273.15;
				maxTempOWM = Double.parseDouble(openWeatherMap.get(cal.getTime().toString()).get("maxTemp")) - 273.15;
				windOWM = Double.parseDouble(openWeatherMap.get(cal.getTime().toString()).get("wind"));
				humidOWM = Double.parseDouble(openWeatherMap.get(cal.getTime().toString()).get("humid"));
				pressureOWM = Double.parseDouble(openWeatherMap.get(cal.getTime().toString()).get("pressure"));
				elements.add(openWeatherMap.get(cal.getTime().toString()).get("description"));
			}
			if (minTempWB != 0) {
				count++;
			}
			if (minTempWU != 0) {
				count++;
			}
			if (minTempDS != 0) {
				count++;
			}
			if (minTempOWM != 0) {
				count++;
			}

			double avgMinTemp = (minTempWB + minTempWU + minTempDS + minTempOWM) / count;
			finalMap.put("minTemp", Double.toString(avgMinTemp));
			count = 0;

			if (maxTempWB != 0) {
				count++;
			}
			if (maxTempWU != 0) {
				count++;
			}
			if (maxTempDS != 0) {
				count++;
			}
			if (maxTempOWM != 0) {
				count++;
			}

			double avgMaxTemp = (maxTempWB + maxTempWU + maxTempDS + maxTempOWM) / count;
			finalMap.put("maxTemp", Double.toString(avgMaxTemp));
			count = 0;

			if (windWB != 0) {
				count++;
			}
			if (windWU != 0) {
				count++;
			}
			if (windDS != 0) {
				count++;
			}
			if (windOWM != 0) {
				count++;
			}

			double avgWind = (windWB + windWU + windDS + windOWM) / count;
			finalMap.put("wind", Double.toString(avgWind));
			count = 0;

			if (preciWB != 0) {
				count++;
			}
			if (preciDS != 0) {
				count++;
			}

			double avgPreci = (windAW + (preciDS * 100) + preciWB) / count;
			finalMap.put("precipitation", Double.toString(avgPreci));
			count = 0;

			if (humidWU != 0) {
				count++;
			}
			if (humidDS != 0) {
				count++;
			}
			if (humidOWM != 0) {
				count++;
			}

			double avgHumid = (humidWU + (humidDS * 100) + humidOWM) / count;
			finalMap.put("humid", Double.toString(avgHumid));
			count = 0;

			if (pressureDS != 0) {
				count++;
			}
			if (pressureOWM != 0) {
				count++;
			}
			if (pressureWB != 0) {
				count++;
			}

			double avgPressure = (pressureDS + pressureOWM + pressureWB) / count;
			finalMap.put("pressure", Double.toString(avgPressure));
			count = 0;

			for (Object word : elements) {
				if (Pattern.compile(Pattern.quote("rain"), Pattern.CASE_INSENSITIVE).matcher(word.toString()).find()) {
					descVote.add("Rainy");
					iconVote.add("HeavyRain.png");
				}
				if (Pattern.compile(Pattern.quote("cloud"), Pattern.CASE_INSENSITIVE).matcher(word.toString()).find()) {
					descVote.add("Cloudy");
					iconVote.add("Cloudy.png");
				}
				if (Pattern.compile(Pattern.quote("overcast"), Pattern.CASE_INSENSITIVE).matcher(word.toString())
						.find()) {
					descVote.add("Overcast");
					iconVote.add("Overcast.png");
				}
				if (Pattern.compile(Pattern.quote("storm"), Pattern.CASE_INSENSITIVE).matcher(word.toString()).find()) {
					descVote.add("Rain & Thunder");
					iconVote.add("CloudRainThunder.png");
				}
				if (Pattern.compile(Pattern.quote("snow"), Pattern.CASE_INSENSITIVE).matcher(word.toString()).find()) {
					descVote.add("Snowy");
					iconVote.add("HeavySnow.png");
				}
				if (Pattern.compile(Pattern.quote("sun"), Pattern.CASE_INSENSITIVE).matcher(word.toString()).find()) {
					descVote.add("Sunny");
					iconVote.add("Sunny.png");
				}
				if (Pattern.compile(Pattern.quote("clear"), Pattern.CASE_INSENSITIVE).matcher(word.toString()).find()) {
					descVote.add("Sunny");
					iconVote.add("Sunny.png");
				}
			}

			MAX_DESC = maxVote(descVote);
			MAX_ICON = maxVote(iconVote);
			finalMap.put("description", MAX_DESC);
			finalMap.put("icon", MAX_ICON);

			if (finalMap.get("minTemp").equals("NaN") && finalMap.get("maxTemp").equals("NaN")
					&& finalMap.get("humid").equals("NaN") && finalMap.get("wind").equals("NaN")
					&& finalMap.get("pressure").equals("NaN") && finalMap.get("precipitation").equals("NaN")) {
				throw exception(PROVIDER, ENTITY_EXCEPTION, "No data available");
			}

			AvgWeather.put(Integer.toString(i), finalMap);
		}
		return AvgWeather;
	}

	private void callWeatherUnlocked(String lat, String longi) {
		try {
			countWU++;
			Map<String, Object> json5 = (Map<String, Object>) cacheManager
					.get(lat + longi, "weatherunlocked", Map.class).get("weatherunlocked");
			List<LinkedHashMap<String, Object>> days = (List<LinkedHashMap<String, Object>>) json5.get("Days");
			for (int i = 0; i < 5; i++) {
				HashMap<String, String> wuMap = new HashMap<String, String>();
				LinkedHashMap<String, Object> getList1 = days.get(i);
				List<LinkedHashMap<String, Object>> timeframes = (List<LinkedHashMap<String, Object>>) getList1
						.get("Timeframes");
				LinkedHashMap<String, Object> getList2 = timeframes.get(0);
				wuMap.put("description", getList2.get("wx_desc").toString());
				wuMap.put("wind", getList1.get("windspd_max_mph").toString());
				wuMap.put("humid", getList1.get("humid_max_pct").toString());
				wuMap.put("maxTemp", getList1.get("temp_max_c").toString());
				wuMap.put("minTemp", getList1.get("temp_min_c").toString());
				final Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, i);
				cal.set(Calendar.HOUR_OF_DAY, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
				cal.set(Calendar.MILLISECOND, 0);
				weatherUnlocked.put(cal.getTime().toString(), wuMap);
			}

		} catch (Exception e) {
			if (countWU == 1) {
				weatherServiceImpl.callWeatherUnlocked(lat, longi, cacheManager);
				callWeatherUnlocked(lat, longi);
			} else {
				countWU = 0;
				System.out.println("weatherunlocked " + e);
			}
		}

	}

	private void callWeatherBit(String lat, String longi) {
		try {
			countWB++;
			Map<String, Object> json3 = (Map<String, Object>) cacheManager.get(lat + longi, "weatherbit", Map.class);
			for (int i = 0; i < 5; i++) {
				HashMap<String, String> wbMap = new HashMap<String, String>();
				ArrayList getList1 = (ArrayList) json3.get("weatherbit");
				Map<String, Object> getList2 = (Map<String, Object>) getList1.get(i);
				Map<String, Object> getList3 = (Map<String, Object>) getList2.get("weather");
				wbMap.put("description", getList3.get("description").toString());
				wbMap.put("wind", getList2.get("wind_spd").toString());
				wbMap.put("maxTemp", getList2.get("max_temp").toString());
				wbMap.put("minTemp", getList2.get("min_temp").toString());
				wbMap.put("pressure", getList2.get("pres").toString());
				wbMap.put("precipitation", getList2.get("precip").toString());
				final Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, i);
				cal.set(Calendar.HOUR_OF_DAY, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
				cal.set(Calendar.MILLISECOND, 0);
				weatherBit.put(cal.getTime().toString(), wbMap);
			}
		} catch (Exception e) {
			if (countWB == 1) {
				weatherServiceImpl.callWeatherBit(lat, longi, cacheManager);
				callWeatherBit(lat, longi);
			} else {
				countWB = 0;
				System.out.println("weatherbit " + e);
			}
		}

	}

	private void callDarkSky(String lat, String longi) {
		try {
			countDS++;
			Map<String, Object> json2 = (Map<String, Object>) cacheManager.get(lat + longi, "darksky", Map.class)
					.get("darksky");
			LinkedHashMap<String, Object> daily = (LinkedHashMap<String, Object>) json2.get("daily");
			List<LinkedHashMap<String, Object>> data = (List<LinkedHashMap<String, Object>>) daily.get("data");
			for (int i = 0; i < 5; i++) {
				HashMap<String, String> dsMap = new HashMap<String, String>();
				LinkedHashMap<String, Object> getList1 = data.get(i);
				dsMap.put("description", getList1.get("summary").toString());
				dsMap.put("wind", getList1.get("windSpeed").toString());
				dsMap.put("humid", getList1.get("humidity").toString());
				dsMap.put("maxTemp", getList1.get("temperatureHigh").toString());
				dsMap.put("minTemp", getList1.get("temperatureLow").toString());
				dsMap.put("pressure", getList1.get("pressure").toString());
				dsMap.put("precipitation", getList1.get("precipProbability").toString());
				final Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, i);
				cal.set(Calendar.HOUR_OF_DAY, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
				cal.set(Calendar.MILLISECOND, 0);
				darkSky.put(cal.getTime().toString(), dsMap);
			}
		} catch (Exception e) {
			if (countDS == 1) {
				weatherServiceImpl.callDarkSky(lat, longi, cacheManager);
				callDarkSky(lat, longi);
			} else {
				countDS = 0;
				System.out.println("darksky " + e);
			}
		}

	}

	private void callOpenWeatherMap(String lat, String longi) {
		try {
			countOWM++;
			Map<String, Object> json1 = (Map<String, Object>) cacheManager.get(lat + longi, "openweathermap", Map.class)
					.get("openweathermap");
			for (int i = 0; i < 5; i++) {
				HashMap<String, String> owmMap = new HashMap<String, String>();
				LinkedHashMap<String, Object> data = (LinkedHashMap<String, Object>) json1.get(Integer.toString(i));
				LinkedHashMap<String, Object> main = (LinkedHashMap<String, Object>) data.get("main");
				List<LinkedHashMap<String, Object>> weather = (List<LinkedHashMap<String, Object>>) data.get("weather");
				LinkedHashMap<String, Object> weather1 = (LinkedHashMap<String, Object>) weather.get(0);
				owmMap.put("description", weather1.get("description").toString());
				LinkedHashMap<String, Object> wind = (LinkedHashMap<String, Object>) data.get("wind");
				owmMap.put("wind", wind.get("speed").toString());
				owmMap.put("humid", main.get("humidity").toString());
				owmMap.put("maxTemp", main.get("temp_max").toString());
				owmMap.put("minTemp", main.get("temp_min").toString());
				owmMap.put("pressure", main.get("pressure").toString());
				final Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, i);
				cal.set(Calendar.HOUR_OF_DAY, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
				cal.set(Calendar.MILLISECOND, 0);
				openWeatherMap.put(cal.getTime().toString(), owmMap);
			}
		} catch (Exception e) {
			if (countOWM == 1) {
				weatherServiceImpl.callOpenWeatherMap(lat, longi, cacheManager);
				callOpenWeatherMap(lat, longi);
			} else {
				countOWM = 0;
				System.out.println("openweathermap " + e);
			}
		}

	}

	private String maxVote(List list) {
		int maxCounter = 0;
		String ret = "";
		for (int index = 0; index < list.size(); index++) {
			int counter = 1;
			for (int innerIndex = index + 1; innerIndex < list.size(); innerIndex++) {
				if (list.get(index) == list.get(innerIndex)) {
					counter++;
				}
			}
			if (maxCounter < counter) {
				maxCounter = counter;
				ret = list.get(index).toString();
			}
		}
		return ret;
	}

	/**
	 * Returns a new RuntimeException
	 *
	 * @param entityType
	 * @param exceptionType
	 * @param args
	 * @return
	 */
	private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
		return TWMException.throwException(entityType, exceptionType, args);
	}
}
