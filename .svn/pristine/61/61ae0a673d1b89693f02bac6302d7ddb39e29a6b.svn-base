package kr.co.vuelog.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.extern.log4j.Log4j;

@Log4j
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler{@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		log.info("success handler --> " + request.getRequestURI());
		
		log.info(authentication);
		log.info(authentication.getCredentials());
		log.info(authentication.getPrincipal());
		authentication.getAuthorities().forEach(authority -> {
			log.info(authority);
		});
		
		log.info("아이디" + authentication.getName());
		
		response.sendRedirect("/security/index");
	}

}
