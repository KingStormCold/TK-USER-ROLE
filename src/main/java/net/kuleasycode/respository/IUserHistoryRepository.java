package net.kuleasycode.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.kuleasycode.entity.UserHistoryEntity;

public interface IUserHistoryRepository extends JpaRepository<UserHistoryEntity, String>{

}
