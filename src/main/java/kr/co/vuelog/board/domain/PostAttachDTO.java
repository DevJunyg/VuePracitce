package kr.co.vuelog.board.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class PostAttachDTO {
	private String uuid;
	private String uploadPath;
	private String fileName;
	private boolean filetype;
	private Timestamp regdate;
	private Integer pno;
}
