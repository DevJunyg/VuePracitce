package kr.co.vuelog.member.service;

import kr.co.vuelog.blog.domain.BlogDTO;
import kr.co.vuelog.member.domain.MemberDTO;

public interface IMemberService {
	
	// 회원 정보 확인
	public MemberDTO read(String email);
	// 회원 정보 업데이트
	public int update(MemberDTO mDto, BlogDTO bDto);
	// 회원 패스워드 업데이트
	public int updatePw(MemberDTO mDto);
	// 회원 탈퇴
	public int quit(String email);
	// 회원 알람 설정
	public int alarm(MemberDTO mDto);
	// 구독자 수 업데이트
	public int updateSubCnt(String email);
	// 이메일 인증 처리
	public int memberAuth(MemberDTO mDto);
	
}
