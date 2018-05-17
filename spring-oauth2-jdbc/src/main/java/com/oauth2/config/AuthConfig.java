package com.oauth2.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import com.oauth2.service.Oauth2ClientDetailsService;
import com.oauth2.service.Oauth2UserDetailsService;

@Configuration
@EnableAuthorizationServer
public class AuthConfig extends AuthorizationServerConfigurerAdapter {
	
	@Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;
	
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	
	@Autowired
	@Qualifier("tokenStore")
	private TokenStore tokenStore;
	
    @Autowired
    private Oauth2ClientDetailsService clientDetailsService;
    
    @Autowired
    private Oauth2UserDetailsService userDetailsService;
    
	@Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.withClientDetails(clientDetailsService);
    }
	
	@Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
        		.tokenStore(tokenStore)
        		.tokenEnhancer(tokenEnhancer())
        		.tokenServices(tokenServices())
        		.userDetailsService(userDetailsService)
            .authenticationManager(authenticationManager);
    }
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    		security
    		.tokenKeyAccess("permitAll()")
    		.checkTokenAccess("isAuthenticated()")
    		.allowFormAuthenticationForClients();
    }
	
	@Bean(value="tokenStore")
    public JdbcTokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }
	
	@Bean
    public DefaultTokenServices tokenServices() {
       final DefaultTokenServices tokenServices = new DefaultTokenServices();
       tokenServices.setSupportRefreshToken(true);
       tokenServices.setTokenStore(tokenStore);
       tokenServices.setReuseRefreshToken(false);
       return tokenServices;
    }
	
    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new OAuth2TokenEnhanser();
    }
}
