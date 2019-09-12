package com.org.project.twm.controller.v1.api;

import static com.org.project.twm.exception.EntityType.USER;
import static com.org.project.twm.exception.ExceptionType.ENTITY_NOT_FOUND;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.org.project.twm.config.JwtTokenUtil;
import com.org.project.twm.controller.v1.request.UserRequest;
import com.org.project.twm.dto.response.Response;
import com.org.project.twm.exception.TWMException;
import com.org.project.twm.exception.EntityType;
import com.org.project.twm.exception.ExceptionType;
import com.org.project.twm.repository.UserRepository;
import com.org.project.twm.service.UserService;

import io.swagger.annotations.ApiOperation;

/**
 * This controller class handle request/response for login and register.
 *
 * @author abhishek.sisodiya
 */

@RestController
public class JwtAuthenticationController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserService userDetailsService;

	@Autowired
	private UserRepository userDao;

	@ApiOperation(value = "Authenticate User")
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public Response createAuthenticationToken(@RequestBody UserRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		Map<String, Object> json = new HashMap<String, Object>();
		json.put("token", token);
		json.put("userId", userDao.findByUsername(authenticationRequest.getUsername()).getId());

		return Response.ok().setPayload(json);
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw exception(USER, ENTITY_NOT_FOUND, username);
		}
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