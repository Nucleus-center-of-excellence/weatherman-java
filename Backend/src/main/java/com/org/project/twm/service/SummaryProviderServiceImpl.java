package com.org.project.twm.service;

import static com.org.project.twm.exception.EntityType.*;
import static com.org.project.twm.exception.ExceptionType.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
//import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.project.twm.exception.EntityType;
import com.org.project.twm.exception.ExceptionType;
import com.org.project.twm.exception.TWMException;
import com.org.project.twm.model.Vote;
import com.org.project.twm.repository.VoteRepository;
import com.org.project.twm.util.CacheManager;

/**
 * SummaryProviderServiceImpl is an implementation of interface
 * {@link SummaryProviderService}. This service will be used to implement fetch
 * the best provider.
 * 
 * @author abhishek.sisodiya
 */

@Service("SummaryProviderService")
public class SummaryProviderServiceImpl implements SummaryProviderService {

	@Autowired
	private VoteRepository votingRepository;

	@Autowired
	private CacheManager cacheManager;

	private int MAX_PROVIDER_ID = 0;

	public Map<String, Object> summaryProvider() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String previousYear = dateFormat.format(getPreviousYear());
		String yesterday = dateFormat.format(yesterday());
		Map<String, Object> json = new HashMap<String, Object>();
		Map<String, Object> fromCache = cacheManager.get("summaryProvider", "FAV_PROVIDER_ID", Map.class);
		if (fromCache != null) {
			return fromCache;
		} else {
//			try {
				List<Vote> votes = votingRepository.findByDate(previousYear);
				if (votes.size() > 0) {
					favProvider(votes);
				} else {
					List<Vote> yesterdayVotes = votingRepository.findByDate(yesterday);
					if (yesterdayVotes.size() > 0) {
						favProvider(yesterdayVotes);
					} else {
						throw exception(VOTE, ENTITY_NOT_FOUND, yesterday + " & " + previousYear);
					}
				}
//			} catch (Exception e) {
//				System.out.println(e);
//			}
			json.put("FAV_PROVIDER_ID", MAX_PROVIDER_ID);
			cacheManager.put("summaryProvider", "FAV_PROVIDER_ID", json);
			cacheManager.expire("summaryProvider");
		}
		Map<String, Object> fromCacheAfterAdd = cacheManager.get("summaryProvider", "FAV_PROVIDER_ID", Map.class);
		return fromCacheAfterAdd;
	}

	private Date yesterday() {
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}

	private static Date getPreviousYear() {
		Calendar prevYear = Calendar.getInstance();
		prevYear.add(Calendar.YEAR, -1);
		return prevYear.getTime();
	}

	private int favProvider(List<Vote> votes) {
		List<Integer> providerIDS = new ArrayList<Integer>();
		for (Vote vote : votes) {
			providerIDS.add(vote.getProvider().getId());
		}
		int maxCounter = 0;
		for (int index = 0; index < providerIDS.size(); index++) {
			int counter = 1;
			for (int innerIndex = index + 1; innerIndex < providerIDS.size(); innerIndex++) {
				if (providerIDS.get(index) == providerIDS.get(innerIndex)) {
					counter++;
				}
			}
			if (maxCounter < counter) {
				maxCounter = counter;
				MAX_PROVIDER_ID = providerIDS.get(index);
			}
		}
		return MAX_PROVIDER_ID;
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
