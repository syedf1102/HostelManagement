package com.tw.serviceImpl;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tw.convertor.UserAddCovertor;
import com.tw.convertor.UserDtoAutoConvertor;
import com.tw.convertor.UserDtoConvertor;
import com.tw.dto.PageDto;
import com.tw.dto.UserAddDto;
import com.tw.dto.UserDto;
import com.tw.dto.UserEditDto;
import com.tw.dto.UserListDto;
import com.tw.dto.UserSpecDto;
import com.tw.enums.StatusType;
import com.tw.exception.UserAlreadyExistsException;
import com.tw.generics.AppConstants;
import com.tw.generics.Code;
import com.tw.generics.Messages;
import com.tw.generics.Response;
import com.tw.model.Role;
import com.tw.model.User;
import com.tw.repository.RoleRepository;
import com.tw.repository.UserRepository;
import com.tw.service.UserService;
import com.tw.spec.UserByroleSpec;
import com.tw.spec.UserSpec;
import com.tw.utility.Constants;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Page<User> userList(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	@Override
	public ResponseEntity<?> addUser(UserAddDto dto) throws UserAlreadyExistsException {

		logger.info("Creating user: " + dto);

		Optional<User> userOpt = userRepository.findOneByUsername(dto.getUsername());
		if (userOpt.isPresent()) {
			logger.info("UserName already exists.");
			throw new UserAlreadyExistsException();
		}

		User user = new UserAddCovertor().apply(dto);
		List<Role> roles = roleRepository.findByRoleIn(Arrays.asList(dto.getRoles()));
		user.setRoles(roles);

		user.setUsername(dto.getUsername());
		user.setName(dto.getName());
		user.setMobile(dto.getMobile());
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());
		user.setCreated(Calendar.getInstance());
		user.setModified(Calendar.getInstance());

		userRepository.save(user);

		logger.info(String.format("User %s has been created successfully", user.getUsername()));

		return Response.build(Code.OK, Messages.USER_CREATED_MSG);
	}

	@Override
	public ResponseEntity<?> editUser(UserEditDto dto) throws UserAlreadyExistsException {
		logger.info("editing user " + dto);
		User user = userRepository.findOneById(dto.getId());
		if (dto.getUsername().equals(user.getUsername())) {

		} else {
			Optional<User> userOpt = userRepository.findOneByUsername(dto.getUsername());
			if (userOpt.isPresent()) {
				logger.info("UserName already exists.");
				throw new UserAlreadyExistsException();
			}
		}
		user.setUsername(dto.getUsername());
		user.setName(dto.getName());
		user.setMobile(dto.getMobile());
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());
		List<Role> roles = roleRepository.findByRoleIn(Arrays.asList(dto.getRoles()));
		user.setRoles(roles);

		userRepository.save(user);
		return Response.build(Code.OK, Messages.EDIT_USER, user);
	}

	@Override
	public ResponseEntity<?> resetPassword(UserEditDto dto) {
		logger.info("reseting password " + dto);
		logger.info("password :", dto.getPassword());
		User user = userRepository.findOneById(dto.getId());

		user.setPassword(dto.getPassword());
		userRepository.save(user);
		return Response.build(Code.ACCEPTED, Messages.RESET_PASSWORD, user);
	}

	@Override
	public ResponseEntity<?> deactivateUser(Long id) {
		logger.info("deactivating user for id:-" + id);
		User user = userRepository.findOneById(id);
		if (user.getStatus().equals(StatusType.Active.toString())) {
			user.setStatus(StatusType.Deactive.toString());
			return Response.build(Code.ACCEPTED, Messages.USER_STATUS_DEACTIVE, user.getStatus());
		} else {
			user.setStatus(StatusType.Active.toString());
			return Response.build(Code.ACCEPTED, Messages.USER_STATUS_ACTIVE, user.getStatus());
		}

	}

	@Override
	public ResponseEntity<?> listUser(UserSpecDto dto) {
		logger.info("showing list of users", dto);

		PageRequest pg = PageRequest.of(dto.getPage() - 1, dto.getSize(), Direction.ASC, AppConstants.MODIFIED);

		Page<User> users = userRepository
				.findAll(new UserSpec(dto.getName(), dto.getUsername(), dto.getEmail(), dto.getMobile()), pg);

		List<UserDto> list = users.stream().map(new UserDtoConvertor()).collect(Collectors.toList());
		PageDto pageDto = new PageDto(list, users.getTotalElements());
		return Response.build(Code.OK, pageDto);
	}

	@Override
	public ResponseEntity<?> findAll() {
		logger.info("showing list of users");
		List<User> users = userRepository.findAll(new UserByroleSpec("", "", Constants.ROLE_SALE));
		List<UserListDto> list = users.stream().map(new UserDtoAutoConvertor()).collect(Collectors.toList());
		return Response.build(Code.OK, list);
	}

	@Override
	public ResponseEntity<?> findAllTeacher() {

		List<User> userList = userRepository.findAll();
//		List<UserAddDto> dto = userList.stream().map(new UserAddCovertor()).collect(Collectors.toList());
		return Response.build(Code.OK, userList);
	}

	@Override
	public ResponseEntity<?> delete(Long id) {
		User user = new User();
		if (id != null) {
			user = userRepository.getOne(id);
			user.setDeleted(true);
			userRepository.save(user);
		}
		return Response.build(Code.OK, Messages.DELETED);
	}

	@Override
	public ResponseEntity<?> getAllRole() {
		List<Role> list = roleRepository.findAll();
		return Response.build(Code.OK, list);
	}

	@Override
	public ResponseEntity<?> getDataById(Long id) {
		User user = userRepository.getOne(id);
		UserAddDto dto = new UserAddDto();
		BeanUtils.copyProperties(user, dto);
		return Response.build(Code.OK, dto);
	}

}