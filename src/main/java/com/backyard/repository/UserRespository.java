package com.backyard.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.backyard.bean.BackyardUser;

public interface UserRespository extends JpaRepository<BackyardUser, Long> {
	
	 public List<BackyardUser> findByUsername(String username);

}
