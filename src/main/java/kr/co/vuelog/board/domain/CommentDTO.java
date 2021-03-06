package kr.co.vuelog.board.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentDTO {
	private int cno;
	private String email;
	private String content;
	private Integer pno;
	private String tagemail;
	private Date regdate;
	private Date update_date;
	private char del_status;
	private Date del_date;
	
}
