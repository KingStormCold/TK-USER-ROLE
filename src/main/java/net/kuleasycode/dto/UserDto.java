package net.kuleasycode.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.kuleasycode.request.user.InsertUpdateUserRequest;
import net.kuleasycode.utils.PasswordUtil;

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
	
	public static UserDto insert(final InsertUpdateUserRequest request, final String userRequest) {
		UserDto userDto = new UserDto();
		userDto.setUserName(request.getUserName());
		userDto.setFullName(request.getFullName());
		userDto.setPassword(PasswordUtil.encryptPassword(request.getPassword()));
		userDto.setEmail(request.getEmail());
		userDto.setPhone(request.getPhone());
		userDto.setEnabled(true);
		userDto.setCreatedDate(new Date(System.currentTimeMillis()));
		userDto.setUpdatedDate(new Date(System.currentTimeMillis()));
		userDto.setCreatedBy(userRequest);
		userDto.setUpdatedBy(userRequest);
		return userDto;
	}
	
	public static UserDto update(final UserDto userDto, final InsertUpdateUserRequest request, final String userRequest) {
		userDto.setFullName(request.getFullName());
		userDto.setEmail(request.getEmail());
		userDto.setPhone(request.getPhone());
		userDto.setEnabled(true);
		userDto.setUpdatedDate(new Date(System.currentTimeMillis()));
		userDto.setUpdatedBy(userRequest);
		return userDto;
	}
}
