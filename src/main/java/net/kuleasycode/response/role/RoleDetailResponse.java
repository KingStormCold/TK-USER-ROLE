package net.kuleasycode.response.role;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.kuleasycode.dto.RoleDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDetailResponse {

	@JsonProperty("role_id")
	private String roleId;
	
	@JsonProperty("description")
	private String description;
	
	public static RoleDetailResponse of(RoleDto roleDto) {
		RoleDetailResponse result = new RoleDetailResponse(roleDto.getRoleId(), roleDto.getDesciption());
		return result;
	}
}
