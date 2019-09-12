package com.org.project.twm.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.project.twm.controller.v1.request.UserVoteRequest;
import com.org.project.twm.model.Provider;
import com.org.project.twm.model.User;
import com.org.project.twm.model.Vote;
import com.org.project.twm.repository.VoteRepository;

/**
 * This service will be used to implement user vote for provider.
 * 
 * @author abhishek.sisodiya
 */

@Service
public class VotingService {

	@Autowired
	private VoteRepository votingRepository;

	public String userVote(UserVoteRequest payload) {
		Vote voting = new Vote();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date = dateFormat.format(Calendar.getInstance().getTime());
		voting.setDate(date);
		Provider provider = new Provider();
		provider.setId(Integer.parseInt(payload.getProviderId().toString()));
		voting.setProvider(provider);
		User user = new User();
		user.setId(Integer.parseInt(payload.getUserId().toString()));
		voting.setUser(user);
		try {
			votingRepository.save(voting);
		} catch (Exception e) {
			return "Bad request.";
		}
		return "Thanks for your feedback.";
	}

}
