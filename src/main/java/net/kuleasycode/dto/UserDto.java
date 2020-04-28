package net.kuleasycode.dto;

import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import net.kuleasycode.entity.RoleEntity;
import net.kuleasycode.request.user.InsertUpdateUserRequest;
import net.kuleasycode.utils.PasswordUtil;

@Getter
@Setter
public class UserDto {

	@JsonProperty("user_name")
	private String userName;
	
	@JsonProperty("password")
	private String password;
	
	@JsonProperty("full_name")
	private String fullName;
	
	@JsonProperty("email")
	private String email;
	
	@JsonProperty("phone")
	private String phone;

	@JsonProperty("created_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "Asia/Bangkok")
	private Date createdDate;

	@JsonProperty("updated_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "Asia/Bangkok")
	private Date updatedDate;
	
	@JsonProperty("created_by")
	private String createdBy;
	
	@JsonProperty("updated_by")
	private String updatedBy;
	
	@JsonProperty("enabled")
	private boolean enabled;
	
	@JsonProperty("roles")
	private Set<RoleEntity> rolesOauth;
	
	public UserDto() {
		
	}
	
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
