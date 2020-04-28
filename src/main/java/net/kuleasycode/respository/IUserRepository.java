package net.kuleasycode.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.kuleasycode.entity.UserEntity;

public interface IUserRepository extends JpaRepository<UserEntity, String> {

	@Modifying(clearAutomatically = true)
	@Query(value = "INSERT INTO user_role VALUES (:roleId, :userName)", nativeQuery = true)
	void insertUserRole(@Param("roleId")String roleId, @Param("userName")String userName);
	
	@Query(value = "SELECT COUNT(role_id) FROM user_role WHERE role_id = :roleId AND user_name = :userName", nativeQuery = true)
	int countUserRole(@Param("roleId")String roleId, @Param("userName")String userName);
}
