package com.org.project.twm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.org.project.twm.model.Provider;

/**
 * Provider interface.
 *
 * @author abhishek.sisodiya
 */

@Repository
public interface ProviderRepository  extends CrudRepository<Provider, Integer> {
	Provider findByProvidername(String name);
	}