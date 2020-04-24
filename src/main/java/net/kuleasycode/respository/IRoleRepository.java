package net.kuleasycode.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.kuleasycode.entity.RoleEntity;

public interface IRoleRepository extends JpaRepository<RoleEntity, String> {

}
