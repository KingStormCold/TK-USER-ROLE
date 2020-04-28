package net.kuleasycode.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import net.kuleasycode.entity.UserEntity;

@Getter
@Setter
public class RoleDto {
	
	@JsonProperty("role_id")
	private String roleId;
	
	@JsonProperty("desciption")
	private String desciption;
	
	@JsonProperty("users")
	private Set<UserEntity> userOauth;
	
	public RoleDto() {
		
	}
}
