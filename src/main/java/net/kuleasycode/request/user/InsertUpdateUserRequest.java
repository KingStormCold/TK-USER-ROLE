package net.kuleasycode.request.user;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsertUpdateUserRequest {

	private String userName;
	
	private String password;
	
	private String fullName;
	
	private String email;
	
	private String phone;
	
	private List<String> roles;
	
}