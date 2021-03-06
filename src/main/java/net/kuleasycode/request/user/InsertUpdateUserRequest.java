package net.kuleasycode.request.user;

import java.util.List;

import org.springframework.util.StringUtils;
import org.thymeleaf.util.ArrayUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.kuleasycode.enumclass.FailEnum;
import net.kuleasycode.enumclass.HttpsStatusEnum;
import net.kuleasycode.response.ResultResponse;
import net.kuleasycode.utils.ValidationRegex;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsertUpdateUserRequest {

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
	
	@JsonProperty("enabled")
	private String enabled;
	
	@JsonProperty("roles")
	private List<String> roles;
	
	@JsonProperty("req")
	private String req;
	
	public ResultResponse<String> validate() {
		String arrEnable[] = {"true", "false"};
		if (StringUtils.isEmpty(this.userName) || StringUtils.isEmpty(this.fullName) || StringUtils.isEmpty(this.email)
				|| StringUtils.isEmpty(this.phone) || StringUtils.isEmpty(this.enabled) || StringUtils.isEmpty(this.req)) {
			return new ResultResponse<>(HttpsStatusEnum._400.getKey(), FailEnum.NOT_EMPTY.getValue());
		} else if (!ValidationRegex.checkPhoneNumber(this.phone)) {
			return new ResultResponse<>(HttpsStatusEnum._400.getKey(), FailEnum.BAD_REQUEST_PHONE.getValue());
		} else if (!ValidationRegex.checkEmail(this.email)) {
			return new ResultResponse<>(HttpsStatusEnum._400.getKey(), FailEnum.BAD_REQUEST_EMAIL.getValue());
		} else if (ArrayUtils.contains(arrEnable, this.enabled)) {
			return new ResultResponse<>(HttpsStatusEnum._400.getKey(), FailEnum.BAD_REQUEST_ENABLED.getValue());
		}
		return null;
	}
}