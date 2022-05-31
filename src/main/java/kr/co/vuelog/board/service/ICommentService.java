package kr.co.vuelog.board.service;

import java.util.List;

import kr.co.vuelog.board.domain.CommentDTO;
import kr.co.vuelog.board.domain.Criteria;
import kr.co.vuelog.board.domain.ReplyPageDTO;

public interface ICommentService {
	
	// 작성한 댓글 목록
	public List<CommentDTO> myComment(String email);
	public int register(CommentDTO cDto);
	public CommentDTO read(int cno);
	public int modify(CommentDTO cDto);
	public int remove(int cno);
	public ReplyPageDTO getList(Criteria cri, Integer pno);
	//public ReplyPageDTO getListPage(Criteria cri, Integer pno);
	

}
