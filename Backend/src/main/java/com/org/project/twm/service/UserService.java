package com.org.project.twm.service;

import static com.org.project.twm.exception.EntityType.USER;
import static com.org.project.twm.exception.ExceptionType.ENTITY_NOT_FOUND;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.org.project.twm.controller.v1.request.UserRequest;
import com.org.project.twm.dto.model.UserDTO;
import com.org.project.twm.exception.EntityType;
import com.org.project.twm.exception.ExceptionType;
import com.org.project.twm.exception.TWMException;
import com.org.project.twm.model.User;
import com.org.project.twm.repository.UserRepository;


/**
 * Repository for User Table.
 *
 * @author abhishek.sisodiya
 */

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = findByUsername(username);
		if (user == null) {
			throw exception(USER, ENTITY_NOT_FOUND, username + " doesn't exists");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}

	public User findByUsername(String username) {
		User user = userRepo.findByUsername(username);
		return user;
	}

	public User save(UserRequest user) {
		User newUser = new User();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		return userRepo.save(newUser);
	}
	
	public void updatePassword(UserDTO user) {
		User daoUser = userRepo.findByUsername(user.getUsername());
		daoUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		daoUser.setUsername(user.getUsername());
		userRepo.save(daoUser);
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