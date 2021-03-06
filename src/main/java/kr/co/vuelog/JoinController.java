package kr.co.vuelog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.vuelog.blog.domain.BlogDTO;
import kr.co.vuelog.member.domain.MemberDTO;
import kr.co.vuelog.member.domain.MemberVO;
import kr.co.vuelog.member.service.IMemberCheckService;
import lombok.extern.log4j.Log4j;

@RestController
@Log4j
@RequestMapping("/api/join")
public class JoinController {

   @Autowired
   private IMemberCheckService service;
   
   @Autowired
      private BCryptPasswordEncoder pwencode; 
   
   @GetMapping("/joinForm")
   public void joinForm() {
      log.info("get joinForm.... ");
   }

   
   @PostMapping(value = "/joinok",
            consumes = "application/json",
            produces = {MediaType.TEXT_PLAIN_VALUE})
      public ResponseEntity<String> join(@RequestBody MemberVO vo) {
         log.info("join ok... ");
         String result="";
         
         MemberDTO dto = new MemberDTO();
         dto.setEmail(vo.getEmail());
         dto.setNickname(vo.getNickname());
         
         String bpw = pwencode.encode(vo.getPassword());
         dto.setPassword(bpw);

         if(service.register(dto)==1) {
            log.info("회원 등록 성공");
            result = "1";
         
           BlogDTO bdto = new BlogDTO();
           bdto.setBlogno(4);
           bdto.setBlogname(dto.getNickname());
           bdto.setEmail(dto.getEmail());
           bdto.setId(vo.getId());
           service.registerblog(bdto);
           
         }else if(service.register(dto)==0) {
            result = "0";
         }
         
//         AuthDTO adto = new AuthDTO();
//         adto.setAuth("ROLE_MEMBER");
//         adto.setEmail(email);
//         
//         service.authregister(adto);
         
         
         return result == "1" ? new ResponseEntity<>("success",HttpStatus.OK) 
                         : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
}