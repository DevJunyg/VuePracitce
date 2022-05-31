package kr.co.vuelog.board.domain;

import java.sql.Timestamp;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
	private Integer pno;
	private String title;
	private String content;
	private String email;
	private String boardtype;
	private char storage;
	private Timestamp storedate;
	private int viewcnt;
	private int comcnt;
	private int tagcnt;
	private int recomcnt;
	private Timestamp regdate;
	private Timestamp update_date;
	private char del_status;
	private Timestamp del_date;
	private String imgsrc;
	
	private List<TagDTO> tagList;
	private List<PostAttachDTO> attachList;
}
