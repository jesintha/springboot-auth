package com.backyard;

import java.security.KeyPair;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@Configuration 
public class AuthServerConfig  extends AuthorizationServerConfigurerAdapter{
	
	   @Value("${spring.security.oauth2.client.clientid}")
	   private String clientId;
	   
	   @Value("${spring.security.oauth2.client.clientsecret}")
	   private String clientSecret;	
		
	    @Value("${spring.security.oauth2.resourceserver.jwt.keystore.file}")
	    private String jwtKeyStore;

	    @Value("${spring.security.oauth2.resourceserver.jwt.keystore.password}")
	    private String jwtKeyStorePassword;

	    @Value("${spring.security.oauth2.resourceserver.jwt.keystore.pairName}")
	    private String jwtKeyStorePairName;
	    
	   @Autowired
	   @Qualifier("authenticationManagerBean")
	   private AuthenticationManager authenticationManager;
	   
	   @Autowired
	   PasswordEncoder passwordEncoder;
	   
	   @Bean
       public JwtAccessTokenConverter jwtAccessTokenConverter() {
           final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
           final KeyPair keyPair = new KeyStoreKeyFactory(new ClassPathResource(jwtKeyStore), jwtKeyStorePassword.toCharArray())
                   .getKeyPair(jwtKeyStorePairName);
           converter.setKeyPair(keyPair);
           return converter;
       }
	   @Bean
	   public JwtTokenStore tokenStore() {
	      return new JwtTokenStore(jwtAccessTokenConverter());
	   }
	   
	   @Override
	   public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
	      endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
	      .accessTokenConverter(jwtAccessTokenConverter());
	   }
	   @Override
	   public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
	      security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	   }
	   @Override
	   public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
	      clients.inMemory().withClient(clientId).secret(passwordEncoder.encode(clientSecret)).scopes("read", "write")
	         .authorizedGrantTypes("password", "refresh_token").accessTokenValiditySeconds(20000)
	         .refreshTokenValiditySeconds(20000);

	   }
}
