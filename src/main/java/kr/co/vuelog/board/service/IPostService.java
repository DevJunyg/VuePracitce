package kr.co.vuelog.board.service;

import java.util.List;


import kr.co.vuelog.board.domain.Criteria;
import kr.co.vuelog.board.domain.PostAttachDTO;
import kr.co.vuelog.board.domain.PostDTO;
import kr.co.vuelog.board.domain.PostPageDTO;
import kr.co.vuelog.board.domain.TagDTO;

public interface IPostService {
	public List<PostDTO> getListWithPaging(Criteria cri);
	
	public int getTotalCnt(Criteria cri);
	
	public List<PostAttachDTO> getImgList(Integer pno);
	
	public List<TagDTO> getTagList(Integer pno);
	
	public List<PostDTO> getTagWithPaging(Criteria cri);
	
	
	public PostPageDTO getListPage(Criteria cri);
	public PostDTO read(Integer pno);
	public int register(PostDTO pDto);
	public boolean modify(PostDTO pDto);
	public boolean remove(Integer pno);
	
}
