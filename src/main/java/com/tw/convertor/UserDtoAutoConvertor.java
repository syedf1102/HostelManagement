package com.tw.convertor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import com.tw.dto.RoleDto;
import com.tw.dto.UserListDto;
import com.tw.model.Role;
import com.tw.model.User;


public class UserDtoAutoConvertor implements Function<User, UserListDto> {

	@Override
	public UserListDto apply(User t) {
		UserListDto dto = new UserListDto();
		dto.setUsername(t.getUsername());
		dto.setId( t.getId() );
		dto.setName( t.getName() );
		dto.setMobile(t.getMobile());
		dto.setEmail(t.getEmail());
		
		List<RoleDto> rdtolist=new ArrayList<>();
		for (Role role : t.getRoles()) {
			RoleDto r=new RoleDto();
			r.setId(role.getId());
			r.setRole(role.getRole());
			rdtolist.add(r);
		}
		if(rdtolist!=null && rdtolist.size() >0 ) {
			dto.setRole(rdtolist.get(0));
		}
		return dto;
	}

}
