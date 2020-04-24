package net.kuleasycode.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {

	private String roleId;
	
	private String desciption;
	
	private String userName;
	
	private UserDto userOauth;
}
