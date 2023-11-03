package com.tw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
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
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import jakarta.websocket.server.PathParam;

@ApiResponse
@RestController
@RequestMapping("/rest/api/user/")
@SecurityRequirements(value = { @SecurityRequirement(name = "bearerAuth") })
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

	// @Operation(summary = "My endpoint", security = @SecurityRequirement(name =
	// "bearerAuth"))
	@PostMapping(value = "list")
	@PostAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<?> listUser(@RequestBody UserSpecDto dto) {
		return userService.listUser(dto);

	}

	// @Operation(summary = "Get a protected resource", description = "Retrieve a
	// protected resource that requires Bearer Token (JWT) authentication.")
	// @SecurityRequirement(name = "Bearer Authentication")
	// @GetMapping(value = "findAll")
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
