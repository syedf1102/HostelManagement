package com.tw.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tw.enums.ERole;
import com.tw.model.RoleLogin;

@Repository
public interface RoleLoginRepository extends JpaRepository<RoleLogin, Long> {

	//public List<RolesLogin> findByRoleIn(Collection<String> roles);
	Optional<RoleLogin> findByName(ERole name);
}
