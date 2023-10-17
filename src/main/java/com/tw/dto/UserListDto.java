package com.tw.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserListDto {
	private Long id;
	private String username;
	private String name;
	private String mobile;
	private String email;
	private RoleDto role;

}
