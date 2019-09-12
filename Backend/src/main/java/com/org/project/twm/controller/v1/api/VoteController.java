package com.org.project.twm.controller.v1.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.org.project.twm.controller.v1.request.UserVoteRequest;
import com.org.project.twm.dto.response.Response;
import com.org.project.twm.service.VotingService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

/**
 * This controller class handle request/response for login and register.
 *
 * @author abhishek.sisodiya
 */

@RestController
public class VoteController {

	@Autowired
	private VotingService votingService;

	@ApiOperation(value = "User Vote for particular provider.", authorizations = {
			@Authorization(value = "Authorization") })
	@RequestMapping(value = "/uservote", method = RequestMethod.POST)
	public Response createAuthenticationToken(@RequestBody UserVoteRequest payload) {
		return Response.ok().setPayload(votingService.userVote(payload));
	}

}
