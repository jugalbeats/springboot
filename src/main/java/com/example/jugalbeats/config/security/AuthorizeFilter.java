package com.example.jugalbeats.config.security;

import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.jugalbeats.exception.UnauthorizedException;
import com.example.jugalbeats.services.RegistrationAndLoginServices;
import com.example.jugalbeats.utils.Constants;
import com.example.jugalbeats.utils.JwtTokenUtil;

import io.jsonwebtoken.ExpiredJwtException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AuthorizeFilter implements HandlerInterceptor
{
	@Autowired
	private RegistrationAndLoginServices jwtUserDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response , Object handler) throws Exception
	{
		
		if(handler instanceof HandlerMethod) {
			HandlerMethod method=(HandlerMethod)handler;
			if(method.getMethod().isAnnotationPresent(Authorize.class)) {
				final String token= request.getHeader("Authorization");
				if(null==token) {
					throw new UnauthorizedException(Constants.AUTH_TOKEN_REQUIRED);
				}
				String username = null;
				String jwtToken = null;
				// JWT Token is in the form "Bearer token". Remove Bearer word and get
				// only the Token
				if (token != null && token.startsWith("Bearer ")) {
					jwtToken = token.substring(7);
					try {
						username = jwtTokenUtil.getUsernameFromToken(jwtToken);
					} catch (IllegalArgumentException e) {
						throw new UnauthorizedException(Constants.AUTH_TOKEN_REQUIRED);
					} catch (ExpiredJwtException e) {
						throw new UnauthorizedException(Constants.AUTH_TOKEN_EXPIRED);
					}
				} else {
					throw new UnauthorizedException(Constants.AUTH_TOKEN_BEARER);
				}
				InputStream stream=request.getInputStream();
				// Once we get the token validate it.
				if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

					UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);

					// if token is valid configure Spring Security to manually set
					// authentication
					if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {

						UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
								userDetails, null, userDetails.getAuthorities());
						usernamePasswordAuthenticationToken
								.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
						// After setting the Authentication in the context, we specify
						// that the current user is authenticated. So it passes the
						// Spring Security Configurations successfully.
						SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
					}
				}
			}
			
		}
		
		return true;
	}

}
