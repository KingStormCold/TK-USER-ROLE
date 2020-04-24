package net.kuleasycode.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.kuleasycode.entity.UserEntity;

public interface IUserRepository extends JpaRepository<UserEntity, String> {

}
