package kr.co.vuelog.member.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.vuelog.blog.domain.BlogDTO;
import kr.co.vuelog.blog.mapper.BlogMapper;
import kr.co.vuelog.member.domain.MemberDTO;
import kr.co.vuelog.member.domain.ProfileDTO;
import kr.co.vuelog.member.mapper.MemberMapper;
import kr.co.vuelog.member.mapper.ProfileMapper;
import kr.co.vuelog.member.service.IMemberService;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class MemberServiceImpl implements IMemberService {
	
	@Autowired
	private MemberMapper mapper;
	
	@Autowired
	private ProfileMapper proMapper;
	
	@Autowired
	private BlogMapper blogMapper;
	
	@Autowired
	   private BCryptPasswordEncoder pwencode;
	
	@Override
	public MemberDTO read(String email) {
		
		log.info(mapper.updateSubCnt(email));
		
		MemberDTO readMember = mapper.read(email);
		
		readMember.setMyBlog(blogMapper.searchEmail(email));
		
		ProfileDTO profileDTO = proMapper.read(email);
		
		if (profileDTO != null) {
			readMember.setProfileDTO(profileDTO);
		}
		
		log.info(readMember);
		
		return readMember;
	}

	@Transactional
	@Override
	public int update(MemberDTO readMember, BlogDTO bDto) {
		
		proMapper.delete(readMember.getEmail());
		
		int result = mapper.update(readMember);
		
		if (result > 0) {
			result = 0;
			result = blogMapper.updateId(bDto);
		}
		
		ProfileDTO profileDTO = readMember.getProfileDTO();
		
		if (result > 0 && profileDTO != null) {
			profileDTO.setEmail(readMember.getEmail());
			proMapper.insert(profileDTO);
		}
		
		return result;
	}

	@Override
	public int updatePw(MemberDTO mDto) {
		String bpw = pwencode.encode(mDto.getPassword());
		mDto.setPassword(bpw);
		return mapper.updatePw(mDto);
	}
	
	@Transactional
	@Override
	public int quit(String email) {
		
		int result = 0;
		
		ProfileDTO pDto = proMapper.read(email);
		
		log.info("ProfileDTO : " + pDto);
		
		if (pDto != null) {
			proMapper.delete(email);
		}
		
		result = blogMapper.delete(email);
		log.info("blogMapper.delete : " + result);
		
		if (result > 0) {
			result = 0;
		}
		
		result = mapper.quit(email);
		log.info("mapper.quit : " + result);
		
		return result;
	}

	@Override
	public int alarm(MemberDTO mDto) {
		return mapper.alarm(mDto);
	}

	@Override
	public int updateSubCnt(String email) {
		return mapper.updateSubCnt(email);
	}

	@Override
	public int memberAuth(MemberDTO mDto) {
		
		log.info("인증 회원 정보 : " + mDto);
		
		int result = mapper.memberAuth(mDto);
		
		return result;
	}

}
