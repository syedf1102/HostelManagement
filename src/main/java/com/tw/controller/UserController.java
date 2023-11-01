package com.tw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tw.dto.UserAddDto;
import com.tw.dto.UserEditDto;
import com.tw.dto.UserSpecDto;
import com.tw.exception.UserAlreadyExistsException;
import com.tw.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/rest/api/user/")
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "add")
	public ResponseEntity<?> addUser(@RequestBody UserAddDto dto) throws UserAlreadyExistsException {
		return userService.addUser(dto);
	}

	@PostMapping(value = "edit")
	public ResponseEntity<?> editUser(@RequestBody UserEditDto dto) throws UserAlreadyExistsException {
		return userService.editUser(dto);
	}

	@PostMapping(value = "set")
	public ResponseEntity<?> resetPassword(@RequestBody UserEditDto dto) {
		return userService.resetPassword(dto);
	}

	@GetMapping(value = "deactive/{id}")
	public ResponseEntity<?> deactivateUser(@PathVariable Long id) {
		return userService.deactivateUser(id);
	}

	@PostMapping(value = "list")
	@Operation(summary = "My endpoint", security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<?> listUser(@RequestBody UserSpecDto dto) {
		return userService.listUser(dto);

	}

	@GetMapping(value = "findAll")
	public ResponseEntity<?> findAll() {
		return userService.findAll();

	}

	@GetMapping(value = "finallteacher")
	public ResponseEntity<?> findAllTeacher() {
		return userService.findAllTeacher();

	}

	@GetMapping(value = "delete")
	public ResponseEntity<?> delete(@PathParam("id") Long id) {
		return userService.delete(id);

	}

	@GetMapping(value = "getallrole")
	public ResponseEntity<?> getAllRole() {
		return userService.getAllRole();

	}

	@GetMapping(value = "getbyid")
	public ResponseEntity<?> getDataById(@PathParam("id") Long id) {
		return userService.getDataById(id);

	}

}
