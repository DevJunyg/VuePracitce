package kr.co.vuelog.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.vuelog.blog.domain.SubscribeDTO;
import kr.co.vuelog.blog.mapper.BlogMapper;
import kr.co.vuelog.blog.mapper.SubscribeMapper;
import kr.co.vuelog.blog.service.ISubscribeService;
import kr.co.vuelog.board.domain.Criteria;
import kr.co.vuelog.member.domain.MemberDTO;
import kr.co.vuelog.member.mapper.ProfileMapper;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class SubscribeServiceImpl implements ISubscribeService {
	
	@Autowired
	private SubscribeMapper mapper;
	
	@Autowired
	private ProfileMapper proMapper;
	
	@Autowired
	private BlogMapper blogMapper;
	
	@Override
	public List<MemberDTO> subToList(Criteria cri) {
		
		List<MemberDTO> subToList = mapper.subToList(cri);
		
		MemberDTO subMember = new MemberDTO();
		
		if (subToList.size() > 0) {
			for (int i = 0; i < subToList.size(); i++) {
				subMember = subToList.get(i);
				subMember.setProfileDTO(proMapper.read(subMember.getEmail()));
				subMember.setMyBlog(blogMapper.searchEmail(subMember.getEmail()));
				subToList.set(i, subMember);
			}
		}
		
		log.info(subToList);
		
		return subToList;
//		return mapper.subToList(cri);
	}

	@Override
	public List<MemberDTO> subByList(Criteria cri) {
		
		List<MemberDTO> subByList = mapper.subByList(cri);
		
		MemberDTO subMember = new MemberDTO();
		
		if (subByList.size() > 0) {
			for (int i = 0; i < subByList.size(); i++) {
				subMember = subByList.get(i);
				subMember.setProfileDTO(proMapper.read(subMember.getEmail()));
				subMember.setMyBlog(blogMapper.searchEmail(subMember.getEmail()));
				subByList.set(i, subMember);
			}
		}
		
		log.info(subByList);
		
		return subByList;
//		return mapper.subByList(cri);
	}
	
	@Override
	public int subInsert(SubscribeDTO sDto) {
		return mapper.subInsert(sDto);
	}

	@Override
	public int subDelete(SubscribeDTO sDto) {
		return mapper.subDelete(sDto);
	}

	@Override
	public SubscribeDTO searchSub(SubscribeDTO sDto) {
		return mapper.searchSub(sDto);
	}

}
