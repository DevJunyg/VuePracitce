package kr.co.vuelog.blog.service;

import java.util.List;

import kr.co.vuelog.blog.domain.SubscribeDTO;
import kr.co.vuelog.board.domain.Criteria;
import kr.co.vuelog.member.domain.MemberDTO;

public interface ISubscribeService {
	
	// 자신이 구독하는 회원 목록
	public List<MemberDTO> subToList(Criteria cri);
	// 자신을 구독하는 회원 목록
	public List<MemberDTO> subByList(Criteria cri);
	// 구독 정보 찾기
	public SubscribeDTO searchSub(SubscribeDTO sDto);
	// 구독 정보 등록
	public int subInsert(SubscribeDTO sDto);
	// 구독 정보 삭제
	public int subDelete(SubscribeDTO sDto);

}
