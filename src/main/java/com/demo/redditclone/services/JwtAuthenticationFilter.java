package com.demo.redditclone.services;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.demo.redditclone.dto.ExceptionResponse;
import com.demo.redditclone.exceptions.JWTTokenException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JWTProviderService jwtProviderService;

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String jwt = getJwtFromRequest(request);
			boolean inBlackList = jwtProviderService.inBlackList(jwt);
			if (StringUtils.hasText(jwt) && jwtProviderService.validateToken(jwt) && !inBlackList) {
				String username = jwtProviderService.getUserNameFromJWT(jwt);
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken authenticate = new UsernamePasswordAuthenticationToken(userDetails,
						null, userDetails.getAuthorities());
				authenticate.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticate);
			}

			filterChain.doFilter(request, response);
		} catch (ExpiredJwtException e) {
			ExceptionResponse res = new ExceptionResponse();
			res.setExceptionMessage("Token Expired");
			res.setExceptionType("JWTTokenException");
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.getWriter().write(new ObjectMapper().writeValueAsString(res));
		}

	}

	private String getJwtFromRequest(HttpServletRequest req) {
		String bearerToken = req.getHeader("Authorization");
		if (bearerToken != null) {
			return bearerToken.substring(7);
		} else {
			return null;
		}

	}

}
