package net.kuleasycode.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import net.kuleasycode.dto.UserHistoryDto;
import net.kuleasycode.entity.UserHistoryEntity;

@Component
public class UserHistoryConverter {

	@Autowired
	private ModelMapper modelMapper;
	
	public UserHistoryDto convertToDto(UserHistoryEntity entity) {
		if (StringUtils.isEmpty(entity)) {
			return null;
		}
		UserHistoryDto result = modelMapper.map(entity, UserHistoryDto.class);
		return result;
	}
	
	public UserHistoryEntity convertToEntity(UserHistoryDto dto) {
		UserHistoryEntity result = modelMapper.map(dto, UserHistoryEntity.class);
		return result;
	}
}
