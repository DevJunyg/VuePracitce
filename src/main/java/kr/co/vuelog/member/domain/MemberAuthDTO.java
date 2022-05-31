package kr.co.vuelog.member.domain;

import lombok.Data;

@Data
public class MemberAuthDTO {
	
	private int authno;
	private String auth;
	private String email;

}
