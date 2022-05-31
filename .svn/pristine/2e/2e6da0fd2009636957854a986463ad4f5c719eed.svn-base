package kr.co.vuelog.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.vuelog.blog.domain.BlogDTO;
import kr.co.vuelog.member.domain.MemberAuthDTO;
import kr.co.vuelog.member.domain.MemberDTO;
import kr.co.vuelog.member.mapper.MemberMapper;
import kr.co.vuelog.member.service.IMemberCheckService;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class MemberCheckImpl implements IMemberCheckService{

	@Autowired
	private MemberMapper mapper;
	
	//회원 등록
	@Override
	public int register(MemberDTO dto) {
		return mapper.insert(dto);
	}
	
	//이메일 존재여
	@Override
	public String email(String email) {
		log.info("service email... ");
		return mapper.email(email);
	}

	@Override
	public String id(String id) {
		log.info("service id....");
		return mapper.id(id);
	}

	@Override
	public String nickname(String nickname) {
		log.info("service nickname....");
		return mapper.nickname(nickname);
	}

	@Override
	public int authregister(MemberAuthDTO adto) {
		log.info("service auth");
		return mapper.authregister(adto);
	}

	@Override
	public MemberDTO read(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MemberDTO> all() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int bregister(BlogDTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void registerblog(BlogDTO bdto) {
		log.info("register blog");
		mapper.insertblog(bdto);
		
	}

	@Override
	public String password(MemberDTO dto) {
		
		return mapper.chkpassword(dto);
	}

}
