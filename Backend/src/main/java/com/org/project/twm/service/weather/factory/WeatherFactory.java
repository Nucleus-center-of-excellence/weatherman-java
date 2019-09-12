package com.org.project.twm.service.weather.factory;

import com.org.project.twm.service.weather.provider.Darksky;
import com.org.project.twm.service.weather.provider.Openweathermap;
import com.org.project.twm.service.weather.provider.Weatherbit;
import com.org.project.twm.service.weather.provider.Weatherunlocked;

/**
 * Use getWeatherDataParser method to get object of type Weather 
 * 
 * @author abhishek.sisodiya
 */
public class WeatherFactory {
	public static Weather getWeatherDataParser(WeatherApiTypes model) {
		Weather weather = null;
		switch (model) {
		case OPENWEATHERMAP:
			weather = new Openweathermap();
			break;

		case DARKSKY:
			weather = new Darksky();
			break;

		case WEATHERBIT:
			weather = new Weatherbit();
			break;

		case WEATHERUNLOCKED:
			weather = new Weatherunlocked();
			break;

		default:
			break;
		}
		return weather;
	}
}
