package kr.co.vuelog.board.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.vuelog.board.domain.CommentDTO;
import kr.co.vuelog.board.domain.Criteria;
import kr.co.vuelog.board.domain.PostPageDTO;
import kr.co.vuelog.board.domain.ReplyPageDTO;
import kr.co.vuelog.board.mappers.CommentMapper;
import kr.co.vuelog.board.mappers.PostMapper;
import kr.co.vuelog.board.service.ICommentService;

@Service
public class CommentServiceImpl implements ICommentService {
	
	@Autowired
	private CommentMapper mapper;
	
	
	private static final Logger log = 
			LoggerFactory.getLogger(CommentServiceImpl.class);

	
	@Override
	public List<CommentDTO> myComment(String email) {
		return mapper.myComment(email);
	}
	

	@Override
	public CommentDTO read(int cno) {
		log.info("read.............." + cno);
		
		return mapper.read(cno);
	}

	@Override
	public int modify(CommentDTO replyDto) {
		log.info("modify................" + replyDto);
		return mapper.update(replyDto);
	}


	@Override
	public ReplyPageDTO getList(Criteria cri, Integer pno) {
		log.info("getList ............." + pno);
		return new ReplyPageDTO(mapper.getTotalCommentCnt(pno), mapper.getListWithPaging(cri, pno), cri);
	}

	@Override
	public int register(CommentDTO cDto) {
		int result = 0;
		result = mapper.insert(cDto);
		mapper.updatePostCommCnt(cDto.getPno());
		return result;
	}

	@Override
	public int remove(int cno) {
		log.info("remove.............."+cno);
		mapper.updatePostCommCnt(mapper.getpno(cno));
		return mapper.delete(cno);
	}

	
//	@Override
//	public ReplyPageDTO getListPage(Criteria cri, Integer pno) {
//		log.info("read..............."+pno);
//		return new ReplyPageDTO(mapper.getCountByPno(pno),
//				mapper.getListWithPaging(cri, pno));
//	}





	

}
