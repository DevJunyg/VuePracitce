package kr.co.vuelog.blog.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class BlockDTO {
	
	private int bno;
	private String email;
	private String blockemail;
	private Timestamp regdate;

}
