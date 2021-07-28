package com.example.jugalbeats.filter;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Order(0)
public class CORSFilter implements Filter {

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {

    }

	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain filterChain)
			throws IOException, ServletException {
		final HttpServletResponse res = (HttpServletResponse) response;
		final HttpServletRequest req = (HttpServletRequest) request;
		if (req.getRequestURI().equals("/")) {
			res.sendError(HttpStatus.METHOD_NOT_ALLOWED.value());
		}
		res.setHeader("Access-Control-Allow-Credentials", "true");
		//res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));
		res.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, PATCH, OPTIONS");
		res.setHeader("Access-Control-Allow-Headers",
				"User-Agent, Authorization, X-UserID, Content-Type, Accept, Origin, X-Requested-With, Access-Token, Refresh-Authorization, Access-Refresh-Token, User-Access-Token");
		res.setHeader("Access-Control-Expose-Headers",
				"Authorization, X-Custom-header, X-Secret, X-UserID, Access-Token, Refresh-Authorization, Access-Refresh-Token, User-Access-Token, matching-criteria");
		res.setHeader("X-FRAME-OPTIONS", "DENY");
		res.setHeader("X-XSS-Protection", "1");
		filterChain.doFilter(request, res);

	}

    @Override
    public void destroy() {

    }
}
