package com.tw.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tw.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

	public List<Role> findByRoleIn(Collection<String> roles);
	
	Optional<Role> findByRole(String role);
}
