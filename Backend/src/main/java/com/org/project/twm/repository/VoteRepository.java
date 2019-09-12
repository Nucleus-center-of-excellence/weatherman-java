package com.org.project.twm.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.org.project.twm.model.Vote;

/**
 * Voting interface.
 *
 * @author abhishek.sisodiya
 */

@Repository
public interface VoteRepository extends CrudRepository<Vote, Integer> {
	List<Vote> findByDate(String date);
}