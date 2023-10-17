package com.tw.spec;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.tw.model.Role;
import com.tw.model.User;

public class UserByroleSpec implements Specification<User> {


	private static final long serialVersionUID = 1L;

	private String name;
	private String username;
	private String role;

	
	public UserByroleSpec(String name, String username, String role) {
		super();
		this.name = name;
		this.username = username;
		this.role = role;
		
	}


	@Override
	public Predicate toPredicate(Root<User> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

		Predicate conjunction = cb.conjunction();
		
		if( StringUtils.isNotEmpty( this.name ) ) {
			conjunction.getExpressions().add( cb.like( root.get("name") , "%" + this.name +"%" ) );
		}
		
		if( StringUtils.isNotEmpty( this.username ) ) {
			conjunction.getExpressions().add( cb.like( root.get("username") , "%" + this.username +"%" ) );
		}
		
		if( StringUtils.isNotEmpty( this.role ) ) {
			Join<User, Role> r = root.join("roles", JoinType.LEFT);
			conjunction.getExpressions().add( cb.like( r.get("role") , "%" + this.role +"%" ) );
		}
		
		return conjunction;
	}

}
