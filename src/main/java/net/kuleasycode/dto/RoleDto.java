package net.kuleasycode.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
	
	@JsonProperty("role_id")
	private String roleId;
	
	@JsonProperty("desciption")
	private String desciption;
	
	@JsonProperty("user_name")
	private String userName;
}
