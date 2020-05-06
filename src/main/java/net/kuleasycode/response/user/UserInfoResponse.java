package net.kuleasycode.response.user;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.kuleasycode.dto.UserDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponse {

	@JsonProperty("user_name")
	private String userName;
	
	@JsonProperty("full_name")
	private String fullName;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "Asia/Bangkok")
	@JsonProperty("created_date")
	private Date createdDate;
	
	@JsonProperty("created_by")
	private String craetedBy;
	
	@JsonProperty("enabled")
	private boolean enabled;
	
	public static UserInfoResponse of(UserDto dto) {
		UserInfoResponse result = new UserInfoResponse();
		result.setUserName(dto.getUserName());
		result.setFullName(dto.getFullName());
		result.setCreatedDate(dto.getCreatedDate());
		result.setCraetedBy(dto.getCreatedBy());
		result.setEnabled(dto.isEnabled());
		return result;
	}
}
