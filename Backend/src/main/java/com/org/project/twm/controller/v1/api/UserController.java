package com.org.project.twm.controller.v1.api;

import static com.org.project.twm.exception.EntityType.*;
import static com.org.project.twm.exception.ExceptionType.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.org.project.twm.controller.v1.request.UserRequest;
import com.org.project.twm.dto.model.UserDTO;
import com.org.project.twm.dto.response.Response;
import com.org.project.twm.exception.TWMException;
import com.org.project.twm.exception.EntityType;
import com.org.project.twm.exception.ExceptionType;
import com.org.project.twm.model.User;
import com.org.project.twm.service.UserService;
import com.org.project.twm.util.AppConstants;
import com.org.project.twm.util.CacheManager;
import com.org.project.twm.util.Encryptor;
import com.org.project.twm.util.SendMail;

import io.swagger.annotations.ApiOperation;

/**
 * This controller class handle request/response for register, reset password,
 * forget password.
 *
 * @author abhishek.sisodiya
 */
@RestController
public class UserController {
	@Autowired
	private CacheManager cacheManager;

	@Autowired
	private UserService userDetailsService;

	@Autowired
	private SendMail sendMail;

	@ApiOperation(value = "Register User")
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public Response saveUser(@RequestBody UserRequest user) throws Exception {
		User user1 = userDetailsService.findByUsername(user.getUsername());
		if (user1 != null) {
			return Response.duplicateEntity().setErrors("Username already exist");
		}
		return Response.ok().setPayload(userDetailsService.save(user));
	}

	@ApiOperation(value = "Change password of user")
	@RequestMapping(value = "/reset-password", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response resetPassword(@RequestBody UserRequest user, @RequestParam("userName") String userName) {
		// userService.setPassword(authDetails, false);
		UserDTO updateUser = new UserDTO();
		String userNameUpdate = Encryptor.decrypt(AppConstants.ENCRYPTION_KEY, AppConstants.ENCRYPTION_INIT_VECTOR,
				userName);
		updateUser.setUsername(userNameUpdate);
		updateUser.setPassword(user.getPassword());

		userDetailsService.updatePassword(updateUser);
		return Response.ok().setPayload("Password reset successfully");
	}

	@ApiOperation(value = "Check Link Expiration")
	@RequestMapping(value = "/check-link-expiration", method = RequestMethod.GET)
	public Response isLinkExpire(@RequestParam("userName") String userName) {
		User user = (User) cacheManager.get(userName);
		if (user == null) {
			throw exception(USER, ENTITY_EXCEPTION, "Your Link is expired.");
		}
		return Response.ok();
	}

	@ApiOperation(value = "Forgot Password")
	@RequestMapping(value = "/forgotpassword", method = RequestMethod.GET)
	public Response forgotPassword(@RequestParam("username") String username,
			@RequestHeader("Origin") String clientHostName) throws Exception {
		User user = userDetailsService.findByUsername(username);
		if (user == null) {
			throw exception(USER, ENTITY_NOT_FOUND, username + " doesn't exists");
		}

		String userName = Encryptor.encrypt(AppConstants.ENCRYPTION_KEY, AppConstants.ENCRYPTION_INIT_VECTOR,
				user.getUsername());
		User userdto = new User();
		userdto.setUsername(user.getUsername());
		cacheManager.setlink(userName, userdto);
		try {
			userName = URLEncoder.encode(userName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw exception(USER, ENTITY_EXCEPTION, e.getMessage());
		}
		if (clientHostName == null) {
			sendMail.sendMail(user.getUsername(), "/#/verifyLink/?userName=" + userName);

		} else {
			sendMail.sendMail(user.getUsername(), clientHostName + "/#/verifyLink/?userName=" + userName);

		}
		return Response.ok().setPayload("Activation link has been sent to registered email address.");
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
