package kr.co.vuelog.board.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostPageDTO {
	private int postCnt;
	private List<PostDTO> list;
	
	private Criteria cri;
}
