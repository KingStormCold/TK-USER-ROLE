package net.kuleasycode.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.kuleasycode.entity.UserEntity;

public interface IUserRepository extends JpaRepository<UserEntity, String> {

	@Modifying(clearAutomatically = true)
	@Query(value = "INSERT INTO user_role VALUES (:roleId, :userName)", nativeQuery = true)
	public void insertUserRole(@Param("roleId")String roleId, @Param("userName")String userName);
	
	@Query(value = "SELECT COUNT(role_id) FROM user_role WHERE role_id = :roleId AND user_name = :userName", nativeQuery = true)
	public int countUserRole(@Param("roleId")String roleId, @Param("userName")String userName);
	
	@Query(value = "SELECT user_role.role_id, user_role.user_name FROM user_role WHERE user_name = :userName OR role_id = :roleId", nativeQuery = true)
	public List<Object> findByUserNameOrRoleIdInUserRole(@Param("userName")String userName, @Param("roleId")String roleId);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "DELETE FROM user_role WHERE role_id = :roleId AND user_name = :userName", nativeQuery = true)
	public void deleteUserRole(@Param("roleId")String roleId, @Param("userName")String userName);
	
}
