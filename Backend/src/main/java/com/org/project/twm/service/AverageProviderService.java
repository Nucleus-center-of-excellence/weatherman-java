package com.org.project.twm.service;

import java.util.HashMap;

/**
 * AverageProviderService implements an application to define method which have
 * business logic and communicate with controller and summary data.
 * 
 * @author abhishek.sisodiya
 */
public interface AverageProviderService {
	HashMap<String, HashMap<String, String>> averageProvider(String lat,String longi);
}