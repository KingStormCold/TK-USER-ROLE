package net.kuleasycode.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {

	public static String encryptPassword(final String password) {
		BCryptPasswordEncoder enPasswordEncoder = new BCryptPasswordEncoder();
		return enPasswordEncoder.encode(password);
	}
	
	public static boolean checkPassword(final String password, final String encoder) {
		BCryptPasswordEncoder enPasswordEncoder = new BCryptPasswordEncoder();
		return enPasswordEncoder.matches(password, encoder);
	}
}
