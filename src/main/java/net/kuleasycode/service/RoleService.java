package net.kuleasycode.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;
import net.kuleasycode.converter.RoleConverter;
import net.kuleasycode.dto.RoleDto;
import net.kuleasycode.dto.UserRoleDto;
import net.kuleasycode.entity.RoleEntity;
import net.kuleasycode.enumclass.HttpsStatusEnum;
import net.kuleasycode.enumclass.SuccessEnum;
import net.kuleasycode.request.role.RoleRequest;
import net.kuleasycode.response.ResultResponse;
import net.kuleasycode.response.role.AllRoleResponse;
import net.kuleasycode.response.role.RoleDetailResponse;
import net.kuleasycode.respository.IRoleRepository;
import net.kuleasycode.respository.IUserRepository;

@Service
@Transactional
@Slf4j
public class RoleService {

	@Autowired
	private IRoleRepository roleRepository;
	
	@Autowired
	private RoleConverter roleConverter;
	
	@Autowired
	private IUserRepository userRepository;
	
	public RoleDto getRole(final String roleId) {
		Optional<RoleEntity> entity = roleRepository.findById(roleId);
		if (!entity.isPresent()) {
			return null;
		}
		return roleConverter.convertToDto(entity.get());
	}
	
	public ResultResponse<AllRoleResponse> findAllRole() {
		try {
			List<RoleDto> listRoles = roleRepository.findAll().stream().map(role -> roleConverter.convertToDto(role))
					.collect(Collectors.toList());
			List<RoleDetailResponse> result = listRoles.stream().map(roleDto -> RoleDetailResponse.of(roleDto))
					.collect(Collectors.toList());
			
			return new ResultResponse<>(HttpsStatusEnum._200.getKey(), HttpsStatusEnum._200.getValue(), new AllRoleResponse(result));
		} catch (Exception e) {
			log.info("find all role exception..." + e.toString(), e);
			return new ResultResponse<>(HttpsStatusEnum._500.getKey(), HttpsStatusEnum._500.getValue());
		}
	}
	
	public ResultResponse<String> insertUpdateRole(final RoleRequest request) {
		try {
			RoleDto roleDto = getRole(request.getRoleId());
			String message;
			if (StringUtils.isEmpty(roleDto)) {
				roleDto = new RoleDto();
				message = SuccessEnum.ADD_ROLE.getValue();
			} else {
				message = SuccessEnum.UPDATE_ROLE.getValue();
			}
			roleDto.setRoleId(request.getRoleId());
			roleDto.setDesciption(request.getDescription());
			roleRepository.save(roleConverter.convertToEntity(roleDto));
			return new ResultResponse<>(HttpsStatusEnum._200.getKey(), message);
			
		} catch (Exception e) {
			log.info("insert-update role exception..." + e.toString(), e);
			return new ResultResponse<>(HttpsStatusEnum._500.getKey(), HttpsStatusEnum._500.getValue());
		}
	}
	
	public ResultResponse<String> deleteRole(final String roleId) {
		try {
			List<Object> userRoles = userRepository.findByUserNameOrRoleIdInUserRole("", roleId);
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
			log.info("[START] Delete role ....");
			roleRepository.deleteById(roleId);
			log.info("[END] Delete role....");
			return new ResultResponse<>(HttpsStatusEnum._200.getKey(), HttpsStatusEnum._200.getValue());
		} catch (Exception e) {
			log.info("Delete role exception..." , e.toString(), e);
			return new ResultResponse<>(HttpsStatusEnum._500.getKey(), HttpsStatusEnum._500.getValue());
		}
	}
}