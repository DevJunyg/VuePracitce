package kr.co.vuelog.member.domain;

import java.sql.Timestamp;
import java.util.List;

import kr.co.vuelog.blog.domain.BlogDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDTO{
	
	private String email;
	private String password;
	private String nickname;
	private Timestamp regdate;
	private String authkey;
	private int subscribe;
	private char social;
	private Timestamp update_date;
	private char del_status;
	private Timestamp del_date;
	private char alarm;
	
	private ProfileDTO profileDTO;
	private BlogDTO myBlog;
	
	private List<MemberAuthDTO> authList;
}
