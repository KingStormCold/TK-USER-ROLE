package net.kuleasycode.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.kuleasycode.converter.RoleConverter;
import net.kuleasycode.dto.RoleDto;
import net.kuleasycode.entity.RoleEntity;
import net.kuleasycode.respository.IRoleRepository;

@Service
@Transactional
public class RoleService {

	@Autowired
	private IRoleRepository roleRepository;
	
	@Autowired
	private RoleConverter roleConverter;
	
	public RoleDto getRole(final String roleId) {
		Optional<RoleEntity> entity = roleRepository.findById(roleId);
		if (!entity.isPresent()) {
			return null;
		}
		return roleConverter.convertToDto(entity.get());
	}
}
