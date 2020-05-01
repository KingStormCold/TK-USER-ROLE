package net.kuleasycode.service;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.kuleasycode.converter.UserHistoryConverter;
import net.kuleasycode.dto.UserDto;
import net.kuleasycode.dto.UserHistoryDto;
import net.kuleasycode.respository.IUserHistoryRepository;

@Service
@Transactional
public class UserHistoryService {
	
	@Autowired
	private UserHistoryConverter userHistoryConverter;
	
	@Autowired
	private IUserHistoryRepository userHistoryRepository;
	
	public void addRecordUser(final UserDto userDto) {
		UserHistoryDto result = new UserHistoryDto();
		result.setHistoryId(UUID.randomUUID().toString());
		result.setUserName(userDto.getUserName());
		result.setPassword(userDto.getPassword());
		result.setFullName(userDto.getFullName());
		result.setEmail(userDto.getEmail());
		result.setPhone(userDto.getPhone());
		result.setUpdatedBy(userDto.getUpdatedBy());
		result.setUpdatedDate(userDto.getUpdatedDate());
		result.setEnabled(userDto.isEnabled());
		userHistoryRepository.save(userHistoryConverter.convertToEntity(result));
	}

}
