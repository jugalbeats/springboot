package com.example.jugalbeats.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Component
public class SecurityInterceptor implements WebMvcConfigurer{
	
	@Autowired
	private AuthorizeFilter authFilter;
	
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authFilter);
	}

}
