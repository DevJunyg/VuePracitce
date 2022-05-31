package kr.co.vuelog.member.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class LoginLogDTO {
	
	private int logno;
	private char status;
	private Timestamp regdate;
	private String email;

}
