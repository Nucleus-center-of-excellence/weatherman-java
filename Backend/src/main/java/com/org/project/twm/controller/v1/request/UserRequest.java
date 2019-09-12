package com.org.project.twm.controller.v1.request;

import lombok.Getter;
import lombok.Setter;

/**
 * User request model.
 *
 * @author abhishek.sisodiya
 */

@Getter
@Setter
public class UserRequest {
	
	private String username;
	private String password;

}