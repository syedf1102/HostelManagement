package com.tw.convertor;

import java.util.function.Function;

import com.tw.dto.UserDto;
import com.tw.model.User;

public class UserDtoConvertor implements Function<User, UserDto> {

	@Override
	public UserDto apply(User t) {
		UserDto dto = new UserDto();
		dto.setUsername(t.getUsername());
		dto.setId( t.getId() );
		dto.setName( t.getName() );
		dto.setEmail( t.getEmail() );
		dto.setMobile( t.getMobile() );
		dto.setStatus( t.getStatus() );
		dto.setPassword(t.getPassword());
		dto.setRoles( t.getRoles().stream().map( r -> r.getRole() ).toArray( String[]::new ) );
		return dto;
	}

}
