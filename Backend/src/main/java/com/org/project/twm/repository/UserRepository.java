package com.org.project.twm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.org.project.twm.model.User;

/**
 * User interface.
 *
 * @author abhishek.sisodiya
 */

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	User findByUsername(String username);	
}