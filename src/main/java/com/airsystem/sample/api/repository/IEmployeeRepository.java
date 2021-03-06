package com.airsystem.sample.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.airsystem.sample.api.domain.Employee;

/**
 * @author Budi Oktaviyan Suryanto (budi.oktaviyan@icloud.com)
 */

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Long> {
}