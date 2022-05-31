package kr.co.vuelog;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.co.vuelog.board.domain.CommentDTO;
import kr.co.vuelog.board.domain.Criteria;
import kr.co.vuelog.board.domain.ReplyPageDTO;
import kr.co.vuelog.board.service.ICommentService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/comments")
@AllArgsConstructor
public class CommentController {

	private ICommentService service;
	
	
	private static final Logger log =
			LoggerFactory.getLogger(CommentController.class);
	
	//댓글 작성
	@PostMapping(value = "/new",
				 consumes = "application/json",
				 produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> creat(@RequestBody CommentDTO commentDto) {
		log.info("CommentDTO : " + commentDto);
		
		int insertCount = service.register(commentDto);
		
		log.info("Comment INSERT COUNT : " + insertCount);
		
		return insertCount == 1 
							  ? new ResponseEntity<>("success", HttpStatus.OK)
							  : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//리스트
//		@GetMapping(value = "/pages/{pno}/{page}",
//				produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
//		public ResponseEntity<ReplyPageDTO> getList(@PathVariable("page") int page,
//													  @PathVariable("pno") Integer pno) {
//			Criteria cri = new Criteria(page, 10);
//			
//			log.info("get Comment List pno : " + pno);
//			
//			log.info("cri : " + cri);
//			
//			return new ResponseEntity<>(service.getListPage(cri, pno), HttpStatus.OK);
//		}
		
		// 리스트
		@GetMapping(value = "/pages/{pno}/{page}",
			   	produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
		public ResponseEntity<ReplyPageDTO> getList(@PathVariable("page") int page,
				                               @PathVariable("pno") Integer pno	) {
			Criteria cri = new Criteria(page, 5);
			
			log.info("cri : " + cri);
			log.info("댓글 리스트");
			log.info("" + service.getList(cri, pno));
			
			return new ResponseEntity<>(service.getList(cri, pno), HttpStatus.OK);
		}
			
	
	
	
	// 댓글 불러오기
		@GetMapping(value = "/{cno}",
					produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}) 
		public ResponseEntity<CommentDTO> read(@PathVariable("cno") int cno) {
			log.info("get : " + cno);
				
			return new ResponseEntity<>(service.read(cno), HttpStatus.OK);
		}
	
	// 댓글 삭제
	@DeleteMapping(value = "/{cno}",
				   produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<String> remove(@PathVariable("cno") int cno) {
		log.info("get : " + cno);
		
		return service.remove(cno) == 1 
				  ? new ResponseEntity<>("success", HttpStatus.OK)
				  : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 댓글 수정
	@RequestMapping(value = "/{cno}",
					method = {RequestMethod.PUT, RequestMethod.PATCH},
					consumes = "application/json",
					produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> modify(@PathVariable("cno") int cno,
										 @RequestBody CommentDTO cDto) {
		cDto.setCno(cno);
		
		log.info("cno : " + cno);
		log.info("modify : " + cDto);
		
		return service.modify(cDto) == 1
					? new ResponseEntity<>("success", HttpStatus.OK)
					: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

























