package kr.co.vuelog.member.mapper;

import kr.co.vuelog.blog.domain.BlogDTO;
import kr.co.vuelog.member.domain.MemberAuthDTO;
import kr.co.vuelog.member.domain.MemberDTO;

public interface MemberMapper {
	
	// 회원 정보 확인
	public MemberDTO read(String email);
	// 회원 정보(닉네임, id) 업데이트
	public int update(MemberDTO mDto);
	// 회원 패스워드 업데이트
	public int updatePw(MemberDTO mDto);
	// 회원 탈퇴
	public int quit(String email);
	// 회원 알람 설정
	public int alarm(MemberDTO mDto);
	// 구독자 수 업데이트
	public int updateSubCnt(String email);
	// 이메일 인증키 업데이트
	public int setAuthKey(MemberDTO mDto);
	// 이메일 인증 확인
	public int memberAuth(MemberDTO mDto);
	
	public int insert(MemberDTO dto);
	public int binsert(BlogDTO dto);
	public String email(String email);
	public String id(String id);
	public String nickname(String nickname);
	public int authregister(MemberAuthDTO adto);
	public int insertblog(BlogDTO bdto);
	public String chkpassword(MemberDTO dto);

}
