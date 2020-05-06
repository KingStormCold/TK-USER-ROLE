package net.kuleasycode.response.role;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllRoleResponse {

	@JsonProperty("roles")
	private List<RoleDetailResponse> roles;
}
