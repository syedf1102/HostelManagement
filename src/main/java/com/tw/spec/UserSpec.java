package com.tw.spec;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.tw.model.User;

public class UserSpec implements Specification<User> {


	private static final long serialVersionUID = 1L;

	private String name;
	private String username;
	private String mobile;
	private String email;
	
	public UserSpec(String name, String username, String mobile, String email) {
		super();
		this.name = name;
		this.username = username;
		this.mobile = mobile;
		this.email = email;
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
		
		if( StringUtils.isNotEmpty( this.mobile ) ) {
			conjunction.getExpressions().add( cb.like( root.get("mobile") , "%" + this.mobile +"%" ) );
		}
		
		if( StringUtils.isNotEmpty( this.email ) ) {
			conjunction.getExpressions().add( cb.like( root.get("email") , "%" + this.email +"%" ) );
		}
		
		return conjunction;
	}

}
