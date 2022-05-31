package kr.co.vuelog;

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

import kr.co.vuelog.blog.domain.BlockDTO;
import kr.co.vuelog.blog.domain.BlogDTO;
import kr.co.vuelog.blog.domain.SubscribeDTO;
import kr.co.vuelog.blog.service.IBlockService;
import kr.co.vuelog.blog.service.IBlogService;
import kr.co.vuelog.blog.service.ISubscribeService;
import kr.co.vuelog.board.domain.Criteria;
import kr.co.vuelog.board.domain.PostPageDTO;
import kr.co.vuelog.board.service.IPostService;
import kr.co.vuelog.member.domain.MemberDTO;
import kr.co.vuelog.member.service.IMemberService;

@RestController
@RequestMapping("/api/blog")
public class BlogController {
   
   @Autowired
   private IBlogService blogSvc;
   
   @Autowired
   private ISubscribeService subSvc;
   
   @Autowired
   private IBlockService blockSvc;
   
   @Autowired
   private IMemberService memberSvc;
   
   @Autowired
   private IPostService postSvc;
   
   private static final Logger log = LoggerFactory.getLogger(BlogController.class);
   
   // 블로그 정보 확인
   @GetMapping(value = "/{id}",
         produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
   public ResponseEntity<BlogDTO> read(@PathVariable("id") String id) {
      
      log.info("read blog : " + id);
      
      return new ResponseEntity<>(blogSvc.read(id), HttpStatus.OK);
   }
   
   // 블로그 게시글 리스트
   @GetMapping(value = "/{id}/pages/{page}",
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
   public ResponseEntity<PostPageDTO> getList(@PathVariable("page") int page, @PathVariable("id") String id, @RequestParam String keyword) {
      Criteria cri = new Criteria(page, 10);
      
      BlogDTO readBlog = blogSvc.read(id);
      
      cri.setEmail(readBlog.getEmail());
      
      log.info("search : " + keyword);
      if (keyword != null) {
         cri.setKeyword(keyword);
      }
      
      log.info("cri : " + cri);
      return new ResponseEntity<>(postSvc.getListPage(cri), HttpStatus.OK);
   }
   
   // 블로그 정보 수정
   @RequestMapping(value = "/{id}",
               method = {RequestMethod.PUT, RequestMethod.PATCH},
               consumes = "application/json",
               produces = {MediaType.TEXT_PLAIN_VALUE})
   public ResponseEntity<String> updateInfo(@PathVariable("id") String id, 
                                @RequestBody BlogDTO bDto){
      
      bDto.setId(id);
      log.info("id : " + id);
      log.info("Blog : " + bDto);
      
      return blogSvc.updateInfo(bDto) == 1
                        ? new ResponseEntity<>("success", HttpStatus.OK)
                        : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
   }   
   
   // 자신을 구독 중인 회원 목록
   @GetMapping(value = "/subscribes/{email:.+}/{page}",
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
   public ResponseEntity<List<MemberDTO>> getSubList(@PathVariable("email") String email,
                                         @PathVariable("page") int page) {
      
      Criteria cri = new Criteria(page, 9);
      cri.setEmail(email);
      
      log.info("cri : " + cri);
      log.info("email : " + email);
      
      return new ResponseEntity<>(subSvc.subByList(cri), HttpStatus.OK);
   }
   
   // 구독 정보 확인
   @GetMapping(value = "/subscribe/{id}/{email:.+}/",
         produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
   public ResponseEntity<Boolean> getSub(@PathVariable("id") String id,
                                  @PathVariable("email") String email) {
      
      BlogDTO readBlog = blogSvc.read(id);
      
      SubscribeDTO sDto = new SubscribeDTO();
      
      boolean result = false;
      
      sDto.setEmail(email);
      sDto.setSubemail(readBlog.getEmail());
      
      SubscribeDTO readDto = subSvc.searchSub(sDto);
      
      if (readDto != null) {
         result = true;
      }
      
      return new ResponseEntity<>(result, HttpStatus.OK);
   }
   
   // 구독
   @PostMapping(value = "/subscribe/{id}/{email:.+}/",
             consumes = "application/json",
             produces = {MediaType.TEXT_PLAIN_VALUE})
   public ResponseEntity<String> subscribe(@PathVariable("id") String id,
                                  @PathVariable("email") String email) {
      
      SubscribeDTO sDto = new SubscribeDTO();
      BlogDTO readBlog = blogSvc.read(id);
      
      sDto.setEmail(email);
      sDto.setSubemail(readBlog.getEmail());
      
      log.info("SubscribeDTO : " + sDto);
      
      int insertCount = subSvc.subInsert(sDto);
      
      return insertCount == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
   }
   
   // 구독 해지
   @DeleteMapping(value = "/subscribe/{id}/{email:.+}/",
               produces = {MediaType.TEXT_PLAIN_VALUE})
   public ResponseEntity<String> unsubscribe(@PathVariable("id") String id,
                                   @PathVariable("email") String email){
      
      BlogDTO readBlog = blogSvc.read(id);
      
      SubscribeDTO sDto = new SubscribeDTO();
      
      sDto.setEmail(email);
      sDto.setSubemail(readBlog.getEmail());

      return subSvc.subDelete(sDto) == 1
            ? new ResponseEntity<String>("success", HttpStatus.OK) 
            : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
   }
   
   // 차단 정보 확인
   @GetMapping(value = "/block/{id}/{email:.+}/",
         produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
   public ResponseEntity<Boolean> getBlock(@PathVariable("id") String id,
                                  @PathVariable("email") String email) {
      
      BlogDTO readBlog = blogSvc.read(id);
      
      BlockDTO blockDto = new BlockDTO();
      
      boolean result = false;
      
      blockDto.setEmail(email);
      blockDto.setBlockemail(readBlog.getEmail());
      
      BlockDTO readBlock = blockSvc.searchBlock(blockDto);
      
      log.info("blockDTO : " + readBlock);
      
      if (readBlock != null) {
         result = true;
      }
      
      return new ResponseEntity<>(result, HttpStatus.OK);
   }
   
   // 차단
   @PostMapping(value = "/block/{id}/{email:.+}/",
             consumes = "application/json",
             produces = {MediaType.TEXT_PLAIN_VALUE})
   public ResponseEntity<String> block(@PathVariable("id") String id,
                               @PathVariable("email") String email) {
      
      BlogDTO readBlog = blogSvc.read(id);
      
      BlockDTO blockDto = new BlockDTO();
      
      SubscribeDTO subDto = new SubscribeDTO();
      
      subDto.setEmail(email);
      subDto.setSubemail(readBlog.getEmail());
      
      SubscribeDTO readDto = subSvc.searchSub(subDto);
      
      if (readDto != null) {
         subSvc.subDelete(subDto);
      }
      
      blockDto.setEmail(email);
      blockDto.setBlockemail(readBlog.getEmail());
      
      log.info("BlockDTO : " + blockDto);
      
      int insertCount = blockSvc.blockInsert(blockDto);
      
      return insertCount == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
   }
   
   // 차단 해제
   @DeleteMapping(value = "/block/{id}/{email:.+}/",
               produces = {MediaType.TEXT_PLAIN_VALUE})
   public ResponseEntity<String> unblock(@PathVariable("id") String id,
                                @PathVariable("email") String email){
      
      BlogDTO readBlog = blogSvc.read(id);
      
      BlockDTO blockDto = new BlockDTO();
      
      blockDto.setEmail(email);
      blockDto.setBlockemail(readBlog.getEmail());
      
      log.info("BlockDTO : " + blockDto);

      return blockSvc.blockDelete(blockDto) == 1
            ? new ResponseEntity<String>("success", HttpStatus.OK) 
            : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
   }

}