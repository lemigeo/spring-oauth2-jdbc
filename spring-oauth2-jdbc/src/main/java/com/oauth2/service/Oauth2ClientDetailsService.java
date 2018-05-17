package com.oauth2.service;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.stereotype.Service;

@Service
public class Oauth2ClientDetailsService extends JdbcClientDetailsService {
	
	public Oauth2ClientDetailsService(@Qualifier("dataSource") DataSource dataSource) {
		super(dataSource);
	}
	
}
