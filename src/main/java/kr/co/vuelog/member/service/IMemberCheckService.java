package kr.co.vuelog.member.service;

import java.util.List;

import kr.co.vuelog.blog.domain.BlogDTO;
import kr.co.vuelog.member.domain.MemberAuthDTO;
import kr.co.vuelog.member.domain.MemberDTO;


public interface IMemberCheckService {

	public MemberDTO read(String email);
	public List<MemberDTO> all();
	public int register(MemberDTO dto);
	public int bregister(BlogDTO dto);
	public String email(String email);
	public String id(String id);
	public String nickname(String nickname);
	public String password(MemberDTO dto);
	public int authregister(MemberAuthDTO adto);
	public void registerblog(BlogDTO bdto);

}
