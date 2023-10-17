package com.tw.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.tw.dto.UserAddDto;
import com.tw.dto.UserEditDto;
import com.tw.dto.UserSpecDto;
import com.tw.exception.UserAlreadyExistsException;
import com.tw.model.User;

public interface UserService {
	
	//ResponseEntity<?> authenticationUser(AuthenticationRequest authenticationRequest);

	Page<User> userList(Pageable pageable);
	
    public  ResponseEntity<?> addUser(UserAddDto dto) throws UserAlreadyExistsException;
	
	
	public ResponseEntity<?> editUser(UserEditDto dto) throws UserAlreadyExistsException;
	
	public ResponseEntity<?> resetPassword(UserEditDto dto);
	
	public ResponseEntity<?> deactivateUser(Long id);
	
	public ResponseEntity<?> listUser(UserSpecDto dto);

	ResponseEntity<?> findAll();
	
	public ResponseEntity<?> findAllTeacher();

	public ResponseEntity<?> delete(Long id);

	public ResponseEntity<?> getAllRole();

	public ResponseEntity<?> getDataById(Long id);
}
