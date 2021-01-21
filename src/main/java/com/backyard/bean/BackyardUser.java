package com.backyard.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "USERS")
public class BackyardUser {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	private String username;
	
	private String password;
	
	@OneToMany(mappedBy="user", fetch = FetchType.EAGER,  cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRoleMapping> userRoleMapping;


	
}
