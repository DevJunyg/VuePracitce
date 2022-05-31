package kr.co.vuelog.member.domain;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.extern.log4j.Log4j;

@Log4j
public class CustomUser extends User{
	
	@Autowired
	private MemberDTO member;

	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		
		log.info("1cus");
	}
	
	public CustomUser(MemberDTO dto) {
		super(dto.getEmail(), dto.getPassword(), 
				dto.getAuthList().stream().map(
						auth -> new SimpleGrantedAuthority(auth.getAuth())
						)
				.collect(Collectors.toList())
				);
		log.info("2cus");
		this.member = dto;
	}
	
}
