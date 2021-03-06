package kr.co.vuelog.board.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.co.vuelog.board.domain.Criteria;
import kr.co.vuelog.board.domain.PostDTO;

public interface PostMapper {
	public List<PostDTO> getList(@Param("cri") Criteria cri);
	
	public void updateViewCnt(Integer pno);
	
	public int getTotalCnt(@Param("cri") Criteria cri);
	
	public void updateReplyCnt(Integer pno);
	
	public int create(PostDTO pDto);
	public PostDTO read(Integer pno);
	public int update(PostDTO pDto);
	public int delete(Integer pno);
	
}
