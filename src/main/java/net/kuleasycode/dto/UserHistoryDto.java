package net.kuleasycode.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserHistoryDto {

	private String historyId;
	
	private String userName;
	
	private String password;
	
	private String fullName;
	
	private String email;
	
	private String phone;
	
	private Date updatedDate;
	
	private String updatedBy;
	
	private boolean enabled;
	
	private UserDto userDto;
}
