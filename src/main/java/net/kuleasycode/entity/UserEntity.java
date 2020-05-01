package net.kuleasycode.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_entity")
@Getter
@Setter
public class UserEntity {

	@Id
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "full_name")
	private String fullName;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "updated_date")
	private Date updatedDate;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "updated_by")
	private String updatedBy;
	
	@Column(name = "enabled", nullable = false)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean enabled = true;
	
	@ManyToMany(mappedBy = "userOauth", fetch = FetchType.LAZY)
	@Fetch(value=FetchMode.SELECT)
	private Set<RoleEntity> rolesOauth;
	
	@OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<UserHistoryEntity> listUserHistory; 
}
