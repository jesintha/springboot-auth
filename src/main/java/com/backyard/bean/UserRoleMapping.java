package com.backyard.bean;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "USER_ROLE_MAPPING")
public class UserRoleMapping {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userid", referencedColumnName = "id")
	private BackyardUser user;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "roleid", referencedColumnName = "id")
	private Roles roles;

}
