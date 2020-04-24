package net.kuleasycode.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component("authenticationRequestInfo")
public class AuthenticationRequestInfo {

	private String userName;
	
	public AuthenticationRequestInfo(String userName) {
		this.userName = userName;
	}

	public AuthenticationRequestInfo() {
		super();
	}
	
	public static AuthenticationRequestInfo getNewInstance() {
		return new AuthenticationRequestInfo();
	}

	public String getUserName() {
		if(StringUtils.isEmpty(this.userName)){
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			this.userName = auth.getName();
		}
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
