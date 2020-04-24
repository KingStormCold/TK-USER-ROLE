package net.kuleasycode.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "role_entity")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleEntity {

	@Id
	@Column(name = "role_id")
	private String roleId;
	
	@Column(name = "desciption")
	private String desciption;
	
	@Column(name = "user_name")
	private String userName;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_name", insertable = false, nullable = false, updatable = false)
	private UserEntity userOauth;
}
