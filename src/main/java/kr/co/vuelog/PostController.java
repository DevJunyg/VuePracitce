package kr.co.vuelog;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.vuelog.board.domain.Criteria;
import kr.co.vuelog.board.domain.PostAttachDTO;
import kr.co.vuelog.board.domain.PostDTO;
import kr.co.vuelog.board.domain.PostPageDTO;
import kr.co.vuelog.board.domain.TagDTO;
import kr.co.vuelog.board.service.IPostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	private static final Logger logger = LoggerFactory.getLogger(PostController.class);
	
	@Autowired
	private IPostService service;

	private String uploadPath = "C:\\workspace\\sts_4.8.1RELEASE\\vuelog\\src\\main\\webapp\\resources\\fileUpload";
	
	// 게시물 생성
	@PostMapping(value = "/new",
				consumes = "application/json",
				produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> create(@RequestBody PostDTO postDto){
		logger.info("PostDTO : " + postDto);
		
		int getPno = service.register(postDto);
		
		logger.info("생성된 pno : " + getPno);
		
		if (postDto.getTagList() != null) {
			postDto.getTagList().forEach(tag -> logger.info("" + tag));
		}
		
		if (postDto.getAttachList() != null) {
			postDto.getAttachList().forEach(attach -> logger.info("" + attach));
		} 
		
		return getPno > 0 ? new ResponseEntity<>("" + getPno, HttpStatus.OK)
						  : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 리스트
	@GetMapping(value = "/pages/{page}",
		   	produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<PostPageDTO> getList(@PathVariable("page") int page, @RequestParam String keyword) {
		Criteria cri = new Criteria(page, 10);
		
		logger.info("search : " + keyword);
		if (keyword != null) {
			cri.setKeyword(keyword);
		}
		
		logger.info("cri : " + cri);
		return new ResponseEntity<>(service.getListPage(cri), HttpStatus.OK);
	}
	
	// 게시글 읽기
	@GetMapping(value = "/{pno}",
			produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}) 
	public ResponseEntity<PostDTO> read(@PathVariable("pno") int pno){
		logger.info("get : " + pno);
		logger.info("get post : " + service.read(pno));
		return new ResponseEntity<>(service.read(pno), HttpStatus.OK);
	}
	
	// 게시글 삭제
	@DeleteMapping(value = "/{pno}",
			produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> remove(@PathVariable("pno") int pno){
		logger.info("delete : " + pno);
		
		List<PostAttachDTO> attachList = service.getImgList(pno);
		
		if (service.remove(pno)) {
			deleteFiles(attachList);
		}
		
		return service.remove(pno) 
				? new ResponseEntity<String>("success", HttpStatus.OK) 
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 게시물 수정
	@RequestMapping(value = "/{pno}",
					method = {RequestMethod.PUT, RequestMethod.PATCH},
					consumes = "application/json",
					produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> modify(@PathVariable("pno") int pno, 
									 	 @RequestBody PostDTO postDto){
		postDto.setPno(pno);
		logger.info("pno : " + pno);
		logger.info("post : " + postDto);
		
		return service.modify(postDto)
								? new ResponseEntity<>("success", HttpStatus.OK)
								: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}		
	
	// 태그 가져오기
	@GetMapping(value = "/gettaglist/{pno}", 
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<TagDTO>> getTagList(@PathVariable("pno") Integer pno) {
		logger.info("getTagList " + pno);

		return new ResponseEntity<List<TagDTO>>(service.getTagList(pno), HttpStatus.OK);
	}
	
	
	
	@GetMapping(value = "/getAttachList/{pno}", 
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<PostAttachDTO>> getAttachList(@PathVariable("pno") Integer pno){
		logger.info("getAttachList " + pno);
		
		return new ResponseEntity<List<PostAttachDTO>>(service.getImgList(pno), HttpStatus.OK);
	}
	
	private void deleteFiles(List<PostAttachDTO> attachList) {
		if(attachList == null || attachList.size() <= 0) {
			return;
		}
		
		
		logger.info("delete attach files............");
		logger.info("" + attachList);
		
		attachList.forEach(attach -> {
			try {
				Path file = Paths.get(uploadPath + "\\" + attach.getFileName());
				
				// 파일 존재시 삭제 메소드
				Files.deleteIfExists(file);
				
				
			} catch (Exception e) {
				logger.error("delete file error : " + e.getMessage());
			}
		});
	}
	
}
