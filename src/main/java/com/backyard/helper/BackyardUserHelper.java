package com.backyard.helper;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.backyard.bean.BackyardUser;

public class BackyardUserHelper extends User {

	private static final long serialVersionUID = 1L;
	
	public BackyardUserHelper(BackyardUser user, Collection<? extends GrantedAuthority> listOfgrantedAuthorities) {
		      super(
		    		  user.getUsername(),
		    		  user.getPassword(),listOfgrantedAuthorities 
		    		);
		   }


}
