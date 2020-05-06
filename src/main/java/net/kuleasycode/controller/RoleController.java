package net.kuleasycode.controller;

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
import net.kuleasycode.request.role.RoleRequest;
import net.kuleasycode.response.ResultResponse;
import net.kuleasycode.response.role.AllRoleResponse;
import net.kuleasycode.service.RoleService;
import net.kuleasycode.utils.JsonUtil;

@RestController
@Slf4j
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@PreAuthorize("hasAuthority('TK-INSERT-UPDATE-USER')")
	@GetMapping(value = "/role/find-all")
	public ResultResponse<AllRoleResponse> findAllRole() {
		log.info("find all role... ");
		return roleService.findAllRole();
	}

	@PreAuthorize("hasAuthority('TK-INSERT-UPDATE-USER')")
	@PostMapping(value = "/role/inser-update")
	public ResultResponse<String> insertUpdateRole(@RequestBody final RoleRequest request) {
		String json = JsonUtil.convertObjectToJson(request);
		log.info("insert update role... " + json);
		if (StringUtils.isEmpty(request.validation())) {
			return roleService.insertUpdateRole(request);
		}
		return request.validation();
	}
	
	@PreAuthorize("hasAuthority('TK-INSERT-UPDATE-USER')")
	@GetMapping(value = "/role/delete-role")
	public ResultResponse<String> deleteRole(@RequestParam("role_id") String roleId) {
		log.info("delete role... " + roleId);
		if (!StringUtils.isEmpty(roleId)) {
			return roleService.deleteRole(roleId);
		}
		return new ResultResponse<>(HttpsStatusEnum._400.getKey(), FailEnum.NOT_EMPTY.getValue()); 
	}
}
