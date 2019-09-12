package com.org.project.twm.dto.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Jwt request model.
 *
 * @author abhishek.sisodiya
 */

@Getter
@Setter
public class UserDTO {
	
	private String username;
	private String password;

}