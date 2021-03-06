package kr.co.vuelog.blog.mapper;

import java.util.List;

import kr.co.vuelog.blog.domain.BlockDTO;
import kr.co.vuelog.blog.domain.SubscribeDTO;
import kr.co.vuelog.member.domain.MemberDTO;

public interface BlockMapper {
	
	// 자신이 차단하는 회원 목록
	public List<MemberDTO> blockToList(String email);
	// 자신을 차단하는 회원 목록
	public List<MemberDTO> blockByList(String email);
	// 차단 정보 찾기
	public BlockDTO searchBlock(BlockDTO blockDto);
	// 차단 정보 등록
	public int blockInsert(BlockDTO blockDto);
	// 차단 정보 삭제
	public int blockDelete(BlockDTO blockDto);

}
