package com.org.project.twm.controller.v1.request;

import lombok.Getter;
import lombok.Setter;

/**
 * User Vote request model.
 *
 * @author abhishek.sisodiya
 */

@Getter
@Setter
public class UserVoteRequest {
	
	private String providerId;
	private String userId;
	
}
