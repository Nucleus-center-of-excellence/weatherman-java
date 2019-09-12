package com.org.project.twm.controller.v1.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.org.project.twm.dto.response.Response;

import io.swagger.annotations.ApiOperation;

/**
 * This controller class handle request/response to send GoogleMapKey.
 *
 * @author abhishek.sisodiya
 */
@RestController
public class GoogleMapKeyController {
	@ApiOperation(value = "Get Google Map Key.")
	@RequestMapping(value = "/gmapkey", method = RequestMethod.GET)
	public Response googleMapKey() {
		return Response.ok().setPayload(System.getenv("GOOGLE_MAP_KEY"));
	}
}
