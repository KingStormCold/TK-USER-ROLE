package net.kuleasycode.enumclass;

import lombok.Getter;

@Getter
public enum FailEnum {
	ERROR_USER_NOT_FOUND("F01", "Not found user."),
	NOT_EMPTY("F02", "Input is not empty."),
	BAD_REQUEST_PHONE("F03", "Max length of phone is 10 or 11 and only number."),
	BAD_REQUEST_EMAIL("F04", "Email is invalid."),
	BAD_REQUEST_ENABLED("F05", "Enabled invalid."),
	BAD_REQUEST_PASSWORD("F06", "Password is not empty."),
	ERROR_PASSWORD("F07", "You can not update password for this user."),
	ERROR_ROLE_EXIST("F08", "This role is exist."),
	ERROR_ROLE_NOT_FOUND("F09", "Not found role."),
	ERROR_USER_EXIST("F09", "This user is exist."),
	
	;

	private String key;
	
	private String value;

	private FailEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}
}
