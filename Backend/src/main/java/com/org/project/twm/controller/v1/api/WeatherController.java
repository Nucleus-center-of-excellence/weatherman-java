package com.org.project.twm.controller.v1.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.org.project.twm.dto.response.Response;
import com.org.project.twm.service.weather.WeatherService;

import io.swagger.annotations.ApiOperation;

/**
 * This controller class handle request and response for Weather.
 *
 * @author abhishek.sisodiya
 */

@RestController()
@RequestMapping("/getweather")
public class WeatherController {

	@Autowired
	private WeatherService weatherService;

	@ApiOperation(value = "Get Weather Detail By www.openweathermap.org")
	@RequestMapping(value = "/openweathermap", method = RequestMethod.GET)
	public Response openweathermap(@RequestParam String lat, @RequestParam String longi, HttpServletRequest request,
			HttpServletResponse response) {
		return Response.ok().setPayload(weatherService.openweathermap(lat, longi));
	}

	@ApiOperation(value = "Get Weather Detail By www.darksky.net")
	@RequestMapping(value = "/darksky", method = RequestMethod.GET)
	public Response darksky(@RequestParam String lat, @RequestParam String longi, HttpServletRequest request,
			HttpServletResponse response) {
		return Response.ok().setPayload(weatherService.darksky(lat, longi));
	}

	@ApiOperation(value = "Get Weather Detail By www.weatherbit.io")
	@RequestMapping(value = "/weatherbit", method = RequestMethod.GET)
	public Response weatherbit(@RequestParam String lat, @RequestParam String longi, HttpServletRequest request,
			HttpServletResponse response) {
		return Response.ok().setPayload(weatherService.weatherbit(lat, longi));
	}

	@ApiOperation(value = "Get Weather Detail By www.weatherunlocked.io")
	@RequestMapping(value = "/weatherunlocked", method = RequestMethod.GET)
	public Response weatherunlocked(@RequestParam String lat, @RequestParam String longi, HttpServletRequest request,
			HttpServletResponse response) {
		return Response.ok().setPayload(weatherService.weatherunlocked(lat, longi));
	}
}