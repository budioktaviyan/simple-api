package com.airsystem.sample.api.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.airsystem.sample.api.domain.Users;

/**
 * @author Budi Oktaviyan Suryanto (budi.oktaviyan@icloud.com)
 */

@Repository
public interface IUsersRepository extends JpaRepository<Users, Long> {

	@Query("from Users where roles.name != :name")
	List<Users> findByNotName(@Param("name") String name);

	@Query("select users from Users users where roles.name != :name")
	Page<Users> findByNotNameAndPaging(@Param("name") String name, Pageable pageable);

	@Modifying
	@Query("update Users set password = :newpassword where username = :username and password = :oldpassword")
	Integer modifyPassword(@Param("username") String username,
						   @Param("oldpassword") String oldpassword,
						   @Param("newpassword") String newpassword);
}