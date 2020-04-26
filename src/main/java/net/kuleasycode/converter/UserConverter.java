package net.kuleasycode.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import net.kuleasycode.dto.UserDto;
import net.kuleasycode.entity.UserEntity;

@Component
public class UserConverter {

	@Autowired
	private ModelMapper modelMapper;
	
	public UserDto convertToDto(UserEntity entity) {
		if (StringUtils.isEmpty(entity)) {
			return null;
		}
		UserDto result = modelMapper.map(entity, UserDto.class);
		return result;
	}
	
	public UserEntity convertToEntity(UserDto dto) {
		UserEntity result = modelMapper.map(dto, UserEntity.class);
		return result;
	}
}
