package kr.co.vuelog.board.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class TblCommentDTO {
	
	private int cno;
	private String email;
	private String content;
	private int pno;
	private Timestamp regdate;
	private Timestamp update_date;
	private char del_status;
	private Timestamp del_date;
	private String tagemail;

}
