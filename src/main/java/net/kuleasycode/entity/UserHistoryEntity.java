package net.kuleasycode.entity;

import java.util.Date;

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
@Table(name = "user_history")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserHistoryEntity {

	@Id
	@Column(name = "history_id")
	private String historyId;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "full_name")
	private String fullName;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "updated_date")
	private Date updatedDate;
	
	@Column(name = "updated_by")
	private String updatedBy;
	
	@Column(name = "enabled", nullable = false, columnDefinition = "boolean default false")
	private boolean enabled;
	
	@Column(name = "user_name")
	private String userName;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_name", nullable = false, insertable = false, updatable = false)
	private UserEntity userEntity;
}
