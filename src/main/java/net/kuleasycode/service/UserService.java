package net.kuleasycode.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.kuleasycode.converter.UserConverter;
import net.kuleasycode.dto.UserDto;
import net.kuleasycode.enumclass.HttpsStatusEnum;
import net.kuleasycode.response.ResultResponse;
import net.kuleasycode.response.user.AllUserResponse;
import net.kuleasycode.response.user.UserInfoResponse;
import net.kuleasycode.respository.IUserRepository;

@Service
@Slf4j
@Transactional
public class UserService {

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private UserConverter userConverter;
 
	public ResultResponse<AllUserResponse> findAllUser() {
		//method reference java8
//		List<UserDto> result = userRepository.findAll().stream()
//				.map(userConverter::convertToDto).collect(Collectors.toList());
		try {
			log.info("[START] stream map....");
			List<UserDto> listUserDto = userRepository.findAll().stream()
					.map(userEntity -> userConverter.convertToDto(userEntity)).collect(Collectors.toList());
			List<UserInfoResponse> result = listUserDto.stream().map(userDto -> UserInfoResponse.of(userDto))
					.collect(Collectors.toList());
			AllUserResponse allUserResponse = new AllUserResponse(result.isEmpty(), result);
			log.info("[DONE] stream map....");
			return new ResultResponse<>(HttpsStatusEnum._200.getKey(), HttpsStatusEnum._200.getValue(), allUserResponse);
		} catch (Exception e) {
			log.error("Exception find all user", e.toString(), e);
			return new ResultResponse<>(HttpsStatusEnum._500.getKey(), HttpsStatusEnum._500.getValue());
		}
	}
}
