package com.tw.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tw.model.UserLogin;
@Repository
public interface UserLoginRepository extends JpaRepository<UserLogin, Long> {
	
	Optional<UserLogin> findByUsername(String username);

	  Boolean existsByUsername(String username);

}
