package kr.co.vuelog;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.co.vuelog.blog.domain.BlogDTO;
import kr.co.vuelog.blog.service.ISubscribeService;
import kr.co.vuelog.board.domain.CommentDTO;
import kr.co.vuelog.board.domain.Criteria;
import kr.co.vuelog.board.domain.PostDTO;
import kr.co.vuelog.board.service.ICommentService;
import kr.co.vuelog.board.service.IPostService;
import kr.co.vuelog.member.domain.MemberDTO;
import kr.co.vuelog.member.service.IMemberService;
import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/api/member")
@Log4j
public class MemberController {
   
   @Autowired
   private IMemberService memberSvc;
   
   @Autowired
   private IPostService postSvc;
   
   @Autowired
   private ICommentService comSvc;
   
   @Autowired
   private ISubscribeService subSvc;
   
   // 회원 정보 확인
   @GetMapping(value = "/{email:.+}/",
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
   public ResponseEntity<MemberDTO> read(@PathVariable("email") String email) {
      
      log.info("read : " + email);
      
      return new ResponseEntity<>(memberSvc.read(email), HttpStatus.OK);
   }
   
   // 작성한 게시글 목록
   @GetMapping(value = "/{email:.+}/myposts",
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
   public ResponseEntity<List<PostDTO>> getMyPosts(@PathVariable("email") String email) {
      
      Criteria cri = new Criteria();
      
      cri.setAmount(10);
      cri.setStorage('n');
      cri.setEmail(email);
      
      return new ResponseEntity<>(postSvc.getListWithPaging(cri), HttpStatus.OK);
   }
   
   // 임시저장된 게시글 목록
   @GetMapping(value = "/{email:.+}/mystorage",
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
   public ResponseEntity<List<PostDTO>> getMyStorage(@PathVariable("email") String email) {
      
      Criteria cri = new Criteria();
      
      cri.setAmount(10);
      cri.setStorage('y');
      cri.setEmail(email);
      
      return new ResponseEntity<>(postSvc.getListWithPaging(cri), HttpStatus.OK);
   }
   
   // 작성한 댓글 목록
   @GetMapping(value = "/{email:.+}/mycomments",
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
   public ResponseEntity<List<CommentDTO>> getMyComments(@PathVariable("email") String email) {
      
      return new ResponseEntity<>(comSvc.myComment(email), HttpStatus.OK);
   }
   
   // 회원 정보 수정
   @RequestMapping(value = "/{email:.+}/", 
         method = {RequestMethod.PUT, RequestMethod.PATCH},
         consumes = "application/json",
         produces = {MediaType.TEXT_PLAIN_VALUE})
   public ResponseEntity<String> update(@PathVariable("email") String email,
                                @RequestBody Map<String, String> params) {
   
   log.info(params);
      
   MemberDTO readMember = memberSvc.read(email);
   
   BlogDTO bDto = new BlogDTO();
   
   readMember.setNickname(params.get("nickname"));
   
   bDto.setEmail(email);
   bDto.setId(params.get("id"));
   
   log.info("email : " + email);
   log.info("update nickname : " + readMember);
   log.info("update blog address : " + bDto);
   
   return memberSvc.update(readMember, bDto) == 1
         ? new ResponseEntity<>("success", HttpStatus.OK)
         : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
   }   
   
//   // 회원 비밀번호 확인
//   @GetMapping(value = "/password/{email:.+}/",
//            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
//   public ResponseEntity<MemberDTO> readPw(@PathVariable("email") String email,@RequestBody MemberDTO mDto) {
//      
//      log.info("read : " + mDto);
//      
//      return new ResponseEntity<>(memberSvc.readPw(mDto), HttpStatus.OK);
//   }
   
   // 회원 비밀번호 수정
   @RequestMapping(value = "/password", 
               method = {RequestMethod.PUT, RequestMethod.PATCH},
               consumes = "application/json",
               produces = {MediaType.TEXT_PLAIN_VALUE})
   public ResponseEntity<String> updatePw(@RequestBody MemberDTO mDto) {
      
      log.info("update : " + mDto);
      
      return memberSvc.updatePw(mDto) == 1
            ? new ResponseEntity<>("success", HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
   }
   
   // 회원 알람 설정
   @RequestMapping(value = "/{email:.+}/{alarm}", 
               method = {RequestMethod.PUT, RequestMethod.PATCH},
               consumes = "application/json",
               produces = {MediaType.TEXT_PLAIN_VALUE})
   public ResponseEntity<String> alarm(@PathVariable("email") String email,
                              @PathVariable("alarm") char alarm) {
      
      MemberDTO readMember = memberSvc.read(email);
      
      readMember.setAlarm(alarm);
      
      log.info("MemberDTO : " + readMember);
      
      return memberSvc.alarm(readMember) == 1
            ? new ResponseEntity<>("success", HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
   }
   
   // 회원 탈퇴
   @DeleteMapping(value = "/{email:.+}/",
               produces = {MediaType.TEXT_PLAIN_VALUE})
   public ResponseEntity<String> quit(@PathVariable("email") String email) {
      
      log.info("quit : " + email);
      
      return memberSvc.quit(email) == 1
            ? new ResponseEntity<String>("success", HttpStatus.OK) 
            : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
   }
   
   // 이메일 인증
   @RequestMapping(value = "/auth/{email:.+}/{authkey}", 
               method = {RequestMethod.PUT, RequestMethod.PATCH},
               consumes = "application/json",
               produces = {MediaType.TEXT_PLAIN_VALUE})
   public ResponseEntity<String> authConfirm(@PathVariable("email") String email,
         @PathVariable("authkey") String authkey) {
      
      log.info("email : " + email);
      log.info("authkey : " + authkey);
      
      MemberDTO mDto = new MemberDTO();
      
      mDto.setEmail(email);
      mDto.setAuthkey(authkey);
      
      int result = memberSvc.memberAuth(mDto);
      
      return result == 1
            ? new ResponseEntity<>("success", HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
   }
   
   // 자신이 구독 중인 회원 목록
   @GetMapping(value = "/subscribes/{email:.+}/{page}",
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
   public ResponseEntity<List<MemberDTO>> getSubList(@PathVariable("page") int page,
                                         @PathVariable("email") String email) {
      
      Criteria cri = new Criteria(page, 9);
      
      cri.setEmail(email);
      
      log.info("cri : " + cri);
      
      return new ResponseEntity<>(subSvc.subToList(cri), HttpStatus.OK);
   }
   
}