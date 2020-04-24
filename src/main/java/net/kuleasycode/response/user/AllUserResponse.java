package net.kuleasycode.response.user;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllUserResponse {
	
	@JsonProperty("is_empty")
	private boolean isEmpty;
	
	@JsonProperty("list_user_info")
	List<UserInfoResponse> listUserInfo;
}
