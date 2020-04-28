package net.kuleasycode.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import net.kuleasycode.dto.RoleDto;
import net.kuleasycode.entity.RoleEntity;

@Component
public class RoleConverter {

	@Autowired
	private ModelMapper modelMapper;
	
	public RoleDto convertToDto(RoleEntity entity) {
		if (StringUtils.isEmpty(entity)) {
			return null;
		}
		RoleDto result = modelMapper.map(entity, RoleDto.class);
		return result;
	}
	
	public RoleEntity convertToEntity(RoleDto dto) {
		RoleEntity result = modelMapper.map(dto, RoleEntity.class);
		return result;
	}
}
