package net.kuleasycode.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
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
	
	@JsonInclude(Include.NON_NULL)
	private Set<UserDto> userOauth;
}
