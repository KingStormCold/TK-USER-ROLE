package net.kuleasycode.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;
import net.kuleasycode.converter.UserConverter;
import net.kuleasycode.dto.RoleDto;
import net.kuleasycode.dto.UserDto;
import net.kuleasycode.dto.UserRoleDto;
import net.kuleasycode.entity.UserEntity;
import net.kuleasycode.enumclass.FailEnum;
import net.kuleasycode.enumclass.HttpsStatusEnum;
import net.kuleasycode.enumclass.SuccessEnum;
import net.kuleasycode.request.user.InsertUpdateUserRequest;
import net.kuleasycode.response.ResultResponse;
import net.kuleasycode.response.user.AllUserResponse;
import net.kuleasycode.response.user.UserDetailResponse;
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
	
	@Autowired
	private UserHistoryService userHistoryService;
 
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
	
	public ResultResponse<String> insertUpdateUser(final InsertUpdateUserRequest request, final String userRequest) {
		try {
			UserDto userDto = findById(request.getUserName());
			String message;
			if (StringUtils.isEmpty(userDto)) {
				userDto = UserDto.insert(request, userRequest);
				message = SuccessEnum.ADD_USER.getValue();
			} else {
				userDto = UserDto.update(userDto, request, userRequest);
				message = SuccessEnum.UPDATE_USER.getValue();
			}
			userRepository.save(userConverter.convertToEntity(userDto));
			for (String roleId : request.getRoles()) {
				if (countUserRole(roleId, userDto.getUserName()) != 0) {
					continue;
				}
				insertUserRole(roleId, userDto.getUserName());
			}
			userHistoryService.addRecordUser(userDto);
			return new ResultResponse<>(HttpsStatusEnum._200.getKey(), message);
		} catch (Exception e) {
			log.info("Insert-Update exception...", e.toString(), e);
			return new ResultResponse<>(HttpsStatusEnum._500.getKey(), HttpsStatusEnum._500.getValue());
		}
	}
	
	public ResultResponse<UserDetailResponse> getUser(final String userName) {
		try {
			UserDto userDto = findById(userName);
			if (StringUtils.isEmpty(userDto)) {
				return new ResultResponse<>(HttpsStatusEnum._200.getKey(), FailEnum.NOT_FOUND_USER.getValue());
			}
			Set<RoleDto> roles = new HashSet<>();
			for (RoleDto roleDto : userDto.getRolesOauth()) {
				RoleDto item = new RoleDto(roleDto.getRoleId(), roleDto.getDesciption(), null);
				roles.add(item);
			}
			UserDetailResponse response = UserDetailResponse.of(userDto);
			response.setRolesOauth(roles);
			return new ResultResponse<>(HttpsStatusEnum._200.getKey(), HttpsStatusEnum._200.getValue(), response);
		} catch (Exception e) {
			log.info("Insert-Update exception...", e.toString(), e);
			return new ResultResponse<>(HttpsStatusEnum._500.getKey(), HttpsStatusEnum._500.getValue());
		}
	}
	
	public ResultResponse<String> deleteUser(final String userName) {
		try {
			UserDto userDto = findById(userName);
			if (StringUtils.isEmpty(userDto)) {
				return new ResultResponse<>(HttpsStatusEnum._404.getKey(), FailEnum.NOT_FOUND_USER.getValue());
			}
			List<Object> userRoles = userRepository.findByUserNameOrRoleIdInUserRole(userName, "");
			List<UserRoleDto> listQueryDB = new ArrayList<>();
			log.info("[START] parse Object....");
			for (Object userRole : userRoles) {
				Object[] value = (Object[]) userRole;
				UserRoleDto userRoleDto = new UserRoleDto((String)value[0], (String)value[1]);
				listQueryDB.add(userRoleDto);
			}
			log.info("[END] parse Object....");
			
			log.info("[START] Delete user role....");
			for (UserRoleDto itemDelete: listQueryDB) {
				userRepository.deleteUserRole(itemDelete.getRoleId(), itemDelete.getUserName());
			}
			log.info("[END] Delete user role....");
			
			log.info("[START] Delete user....");
			userRepository.deleteById(userName);
			log.info("[END] Delete user....");
			return new ResultResponse<>(HttpsStatusEnum._200.getKey(), HttpsStatusEnum._200.getValue());
		} catch (Exception e) {
			log.info("Insert-Update exception...", e.toString(), e);
			return new ResultResponse<>(HttpsStatusEnum._500.getKey(), HttpsStatusEnum._500.getValue());
		}
	}
	
	public UserDto findById(final String userName) {
		Optional<UserEntity> entity = userRepository.findById(userName);
		if (!entity.isPresent()) {
			return null;
		}
		return userConverter.convertToDto(entity.get());
	}
	
	public void insertUserRole(final String roleId, final String userName) {
		userRepository.insertUserRole(roleId, userName);
	}
	
	public int countUserRole(final String roleId, final String userName) {
		return userRepository.countUserRole(roleId, userName);
	}
}
