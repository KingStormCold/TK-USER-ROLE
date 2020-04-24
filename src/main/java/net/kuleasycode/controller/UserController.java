package net.kuleasycode.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import net.kuleasycode.request.user.InsertUpdateUserRequest;
import net.kuleasycode.response.ResultResponse;
import net.kuleasycode.response.user.AllUserResponse;
import net.kuleasycode.service.UserService;
import net.kuleasycode.utils.AuthenticationRequestInfo;

@RestController
@Slf4j
public class UserController {
	
	@Autowired
	private UserService userService;

	@PreAuthorize("hasAuthority('NONO')")
	@GetMapping(value = "/users/find-all")
	public ResultResponse<AllUserResponse> findAll() {
		log.info("find all user");
		String userName = AuthenticationRequestInfo.getNewInstance().getUserName();
		log.info("aaa" + userName);
		return userService.findAllUser();
	}
	
	@PreAuthorize("hasAuthority('NONO')")
	@GetMapping(value = "/users/insert-update")
	public ResultResponse<AllUserResponse> insertUpdate(@Valid @RequestBody InsertUpdateUserRequest request) {
		log.info("insert");
		return userService.findAllUser();
	}
}
