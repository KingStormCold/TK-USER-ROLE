package net.kuleasycode.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

	private String userName;
	
	private String password;
	
	private String fullName;
	
	private String email;
	
	private String phone;

	private Date createdDate;

	private Date updatedDate;
	
	private String createdBy;
	
	private String updatedBy;
	
	private boolean enabled;
	
	private List<RoleDto> roleEntities;
}
