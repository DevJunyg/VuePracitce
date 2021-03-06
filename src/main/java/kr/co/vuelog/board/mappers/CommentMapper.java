package kr.co.vuelog.board.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.co.vuelog.board.domain.CommentDTO;
import kr.co.vuelog.board.domain.Criteria;

public interface CommentMapper {
	
	// 작성한 댓글 목록 최근 5건
	public List<CommentDTO> myComment(String email);
	public int insert(CommentDTO cDto);
	public CommentDTO read(int cno);
	
	public int getpno(int cno);
	
	public int update(CommentDTO cDto);
	public int delete(int cno);
  //public List<CommentDTO> getListWithPaging(Criteria cri, Integer pno);
	public List<CommentDTO> getListWithPaging(@Param("cri") Criteria cri,
			@Param("pno") Integer pno);
	public int getTotalCommentCnt(@Param("pno") Integer pno);
	public int getCountByPno(Integer pno);
	
	public void updatePostCommCnt(@Param("pno") Integer pno);
	
}
