package com.tw.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tw.model.UserLogin;
import com.tw.repository.UserLoginRepository;

import jakarta.transaction.Transactional;

@Service
public class UserLoginDetailsImpl implements UserDetailsService{
	
	@Autowired
	 UserLoginRepository userLoginRepo;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 UserLogin user = userLoginRepo.findByUsername(username)
			        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

			    return UserDetailsImpl.build(user);
	}

}
