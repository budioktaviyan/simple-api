package com.airsystem.sample.api.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.airsystem.sample.api.domain.Users;

/**
 * @author Budi Oktaviyan Suryanto (budi.oktaviyan@icloud.com)
 */

@Repository
public interface IUsersRepository extends JpaRepository<Users, Long> {
	@Modifying
	@Query("update Users set password=?3 where username=?1 and password=?2")
	Integer setApplicationPassword(String username, String oldpassword, String newpassword);

	List<Users> findByUsername(String username);

	List<Users> findByRolesName(String rolesname, Sort sort);

	Page<Users> findByRolesName(String rolesname, Pageable pageable);
}