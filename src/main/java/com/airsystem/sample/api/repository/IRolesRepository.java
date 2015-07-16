package com.airsystem.sample.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.airsystem.sample.api.domain.Roles;

/**
 * @author Budi Oktaviyan Suryanto (budi.oktaviyan@icloud.com)
 */
public interface IRolesRepository extends JpaRepository<Roles, Long> {
}