package com.airsystem.sample.service;

import java.util.List;

/**
 * @author Budi Oktaviyan Suryanto (budi.oktaviyan@icloud.com)
 */
public interface IDatabaseService<T> {

	List<T> findById(String id);
}