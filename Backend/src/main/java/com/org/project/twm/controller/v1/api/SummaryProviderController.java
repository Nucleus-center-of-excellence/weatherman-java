package com.org.project.twm.controller.v1.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.org.project.twm.dto.response.Response;
import com.org.project.twm.service.SummaryProviderService;

import io.swagger.annotations.ApiOperation;

/**
 * This controller class handle request/response for summary provider.
 *
 * @author abhishek.sisodiya
 */

@RestController
public class SummaryProviderController {

	@Autowired
	private SummaryProviderService summaryProviderService;

	@ApiOperation(value = "Returns precise provider.")
	@RequestMapping(value = "/summaryprovider", method = RequestMethod.GET)
	public Response createAuthenticationToken() {
		return Response.ok().setPayload(summaryProviderService.summaryProvider());
	}

}
