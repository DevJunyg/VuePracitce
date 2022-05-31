package kr.co.vuelog.blog.mapper;

import kr.co.vuelog.blog.domain.BlogDTO;

public interface BlogMapper {
	
	// 블로그 정보 확인
	public BlogDTO read(String id);
	// 이메일로 찾기
	public BlogDTO searchEmail(String email);
	// 블로그 정보(이름, 소개) 업데이트
	public int updateInfo(BlogDTO bDto);
	// 블로그 주소(ID) 업데이트
	public int updateId(BlogDTO bDto);
	// 블로그 삭제(회원 탈퇴 시)
	public int delete(String email);

}
