package com.backyard;

import java.security.Principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
@EnableAuthorizationServer
@EnableResourceServer
@EnableGlobalMethodSecurity( 
		  securedEnabled = true)
public class BackyardAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackyardAuthApplication.class, args);
	}
	@GetMapping("/home")
    public String home() {
        return "Hello!";
    }
   @GetMapping("/user/me")
   @Secured("ROLE_ADMIN")
    public Principal user(Principal principal) {
        return principal;
    }

}
