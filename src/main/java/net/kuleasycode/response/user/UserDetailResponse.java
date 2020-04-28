package net.kuleasycode.response.user;

import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.kuleasycode.dto.RoleDto;
import net.kuleasycode.dto.UserDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailResponse {

	@JsonProperty("user_name")
	private String userName;
	
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
	private Set<RoleDto> rolesOauth;
	
	public static UserDetailResponse of (UserDto dto) {
		UserDetailResponse result = new UserDetailResponse();
		result.setUserName(dto.getUserName());
		result.setFullName(dto.getFullName());
		result.setEmail(dto.getEmail());
		result.setPhone(dto.getPhone());
		result.setCreatedDate(dto.getCreatedDate());
		result.setUpdatedDate(dto.getUpdatedDate());
		result.setCreatedBy(dto.getCreatedBy());
		result.setUpdatedBy(dto.getUpdatedBy());
		result.setEnabled(dto.isEnabled());
		return result;
	}
}
