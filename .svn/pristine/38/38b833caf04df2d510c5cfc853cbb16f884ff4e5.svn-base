 package kr.co.vuelog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import kr.co.vuelog.member.domain.CustomUser;
import kr.co.vuelog.member.domain.MemberDTO;
import kr.co.vuelog.member.mapper.MemberMapper;
import lombok.extern.log4j.Log4j;

@Log4j
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		log.info("detail handler... ");
		log.info("Load User By --> " + username);
		
		MemberDTO dto = memberMapper.read(username);
		
		log.warn("queried by member mapper : " + dto);
		if(dto == null) {
			log.info("sssssss");
			if(dto == null) throw new UsernameNotFoundException("dddddddddddddd"); 
		}
		
		return dto == null ? null : new CustomUser(dto);
	}

}
