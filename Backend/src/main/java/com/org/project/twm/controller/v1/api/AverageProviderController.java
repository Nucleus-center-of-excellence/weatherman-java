package com.org.project.twm.controller.v1.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.org.project.twm.dto.response.Response;
import com.org.project.twm.service.AverageProviderService;

import io.swagger.annotations.ApiOperation;

/**
 * This controller class handle request/response for average of all providers.
 *
 * @author abhishek.sisodiya
 */

@RestController
public class AverageProviderController {

	@Autowired
	private AverageProviderService averageProviderService;

	@ApiOperation(value = "Average of all the providers.")
	@RequestMapping(value = "/averageprovider", method = RequestMethod.GET)
	public Response getAverageOfProviders(@RequestParam String lat, @RequestParam String longi) {
		return Response.ok().setPayload(averageProviderService.averageProvider(lat, longi));
	}
}
