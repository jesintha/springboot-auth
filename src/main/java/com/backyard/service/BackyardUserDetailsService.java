package com.backyard.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.backyard.bean.BackyardUser;
import com.backyard.bean.UserRoleMapping;
import com.backyard.helper.BackyardUserHelper;
import com.backyard.repository.UserRespository;

@Service
public class BackyardUserDetailsService implements UserDetailsService {

	@Autowired
	public UserRespository userRespository;

	private Collection<? extends GrantedAuthority> getAuthorities(List<UserRoleMapping> userRoleMappings) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (UserRoleMapping userRoleMapping : userRoleMappings) {
			authorities.add(new SimpleGrantedAuthority(userRoleMapping.getRoles().getRolename()));
			userRoleMappings.stream().map(p -> new SimpleGrantedAuthority(p.getRoles().getRolename()))
					.forEach(authorities::add);
		}

		return authorities;
	}

	@Override
	public BackyardUserHelper loadUserByUsername(String username) throws UsernameNotFoundException {
		List<BackyardUser> backyardUsers = userRespository.findByUsername(username);
		BackyardUser backyardUser = backyardUsers.get(0);
		return new BackyardUserHelper(backyardUsers.get(0), getAuthorities(backyardUser.getUserRoleMapping()));
	}

}
