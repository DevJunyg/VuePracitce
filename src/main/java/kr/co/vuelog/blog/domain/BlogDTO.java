package kr.co.vuelog.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BlogDTO {
	
	private int blogno;
	private String email;
	private String bloginfo;
	private String id;
	private String blogname;

}
