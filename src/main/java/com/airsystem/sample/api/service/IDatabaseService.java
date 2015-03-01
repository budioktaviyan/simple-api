package com.airsystem.sample.api.service;

import java.util.List;

/**
 * @author Budi Oktaviyan Suryanto (budi.oktaviyan@icloud.com)
 */
public interface IDatabaseService<T> {

	List<T> findById(String id);
}