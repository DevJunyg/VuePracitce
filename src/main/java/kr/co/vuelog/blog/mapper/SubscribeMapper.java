package kr.co.vuelog.blog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.co.vuelog.blog.domain.SubscribeDTO;
import kr.co.vuelog.board.domain.Criteria;
import kr.co.vuelog.member.domain.MemberDTO;

public interface SubscribeMapper {
	
	// 자신이 구독하는 회원 목록
	public List<MemberDTO> subToList(@Param("cri") Criteria cri);
	// 자신을 구독하는 회원 목록
	public List<MemberDTO> subByList(@Param("cri") Criteria cri);
	// 구독 정보 찾기
	public SubscribeDTO searchSub(SubscribeDTO sDto);
	// 구독 정보 등록
	public int subInsert(SubscribeDTO sDto);
	// 구독 정보 삭제
	public int subDelete(SubscribeDTO sDto);

}
