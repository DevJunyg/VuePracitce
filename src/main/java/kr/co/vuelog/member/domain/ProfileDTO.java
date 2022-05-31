package kr.co.vuelog.member.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ProfileDTO {
	
	private String uuid;
	private String email;
	private Timestamp regdate;
	private String fileName;
	private char filetype;
	private String uploadPath;

}
