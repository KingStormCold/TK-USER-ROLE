package net.kuleasycode.request.role;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.kuleasycode.enumclass.FailEnum;
import net.kuleasycode.enumclass.HttpsStatusEnum;
import net.kuleasycode.response.ResultResponse;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleRequest {

	@JsonProperty("role_id")
	private String roleId;
	
	@JsonProperty("description")
	private String description;
	
	public ResultResponse<String> validation() {
		if (StringUtils.isEmpty(this.roleId) || StringUtils.isEmpty(this.description)) {
			return new ResultResponse<>(HttpsStatusEnum._400.getKey(), FailEnum.NOT_EMPTY.getValue());
		}
		return null;
	}
}
