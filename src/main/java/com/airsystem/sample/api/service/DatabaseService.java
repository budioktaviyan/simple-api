package com.airsystem.sample.api.service;

import org.springframework.stereotype.Component;

/**
 * @author Budi Oktaviyan Suryanto (budi.oktaviyan@icloud.com)
 */

@Component
public abstract class DatabaseService<T extends Object> implements IDatabaseService<T> {
}