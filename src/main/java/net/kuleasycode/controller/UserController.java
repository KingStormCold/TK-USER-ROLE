package net.kuleasycode.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import net.kuleasycode.enumclass.FailEnum;
import net.kuleasycode.enumclass.HttpsStatusEnum;
import net.kuleasycode.request.user.InsertUpdateUserRequest;
import net.kuleasycode.response.ResultResponse;
import net.kuleasycode.response.user.AllUserResponse;
import net.kuleasycode.response.user.UserDetailResponse;
import net.kuleasycode.service.UserService;
import net.kuleasycode.utils.AuthenticationRequestInfo;
import net.kuleasycode.utils.JsonUtil;

@RestController
@Slf4j
public class UserController {
	
	@Autowired
	private UserService userService;

	@PreAuthorize("hasAuthority('TK-FIND-ALL-USER')")
	@GetMapping(value = "/users/find-all")
	public ResultResponse<AllUserResponse> findAll() {
		String userRequest = AuthenticationRequestInfo.getNewInstance().getUserName();
		log.info("find all user with " + userRequest);
		return userService.findAllUser();
	}
	
	@PreAuthorize("hasAuthority('TK-INSERT-UPDATE-USER')")
	@PostMapping(value = "/users/insert-update")
	public ResultResponse<String> insertUpdate(@Valid @RequestBody InsertUpdateUserRequest request) {
		String userRequest = AuthenticationRequestInfo.getNewInstance().getUserName();
		String json = JsonUtil.convertObjectToJson(request);
		log.info(userRequest + " ...insert-update... " + json);
		return userService.insertUpdateUser(request, userRequest);
	}
	
	@PreAuthorize("hasAuthority('TK-GET-USER')")
	@GetMapping(value = "/users/find-by-user-name")
	public ResultResponse<UserDetailResponse> getUser(@RequestParam("user_name") String userName) {
		String userRequest = AuthenticationRequestInfo.getNewInstance().getUserName();
		log.info("get user info " + userName + " by " + userRequest);
		if (!StringUtils.isEmpty(userName)) {
			return userService.getUser(userName);
		}
		return new ResultResponse<>(HttpsStatusEnum._400.getKey(), FailEnum.NOT_EMPTY.getValue()); 
	}
	
	@PreAuthorize("hasAuthority('TK-DELETE-USER')")
	@GetMapping(value = "/users/delete")
	public ResultResponse<String> deleteUser(@RequestParam("user_name") String userName) {
		String userRequest = AuthenticationRequestInfo.getNewInstance().getUserName();
		log.info("delete " + userName + " by " + userRequest);
		if (!StringUtils.isEmpty(userName)) {
			return userService.deleteUser(userName);
		}
		return new ResultResponse<>(HttpsStatusEnum._400.getKey(), FailEnum.NOT_EMPTY.getValue()); 
	}
}
