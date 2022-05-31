package kr.co.vuelog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.vuelog.member.domain.MemberDTO;
import kr.co.vuelog.member.service.IMemberCheckService;
import lombok.extern.log4j.Log4j;

@RestController
@Log4j
@RequestMapping("/api")
public class CheckController {
   @Autowired
   private IMemberCheckService service;
   
   @PostMapping("/email_chk")
   public ResponseEntity<String>  emailchk(@RequestBody MemberDTO dto) {
      String email = dto.getEmail();
      
      log.info("emailchk controller.... ");
      log.info(email);
      
      String result = "";
      
      if(service.email(email) == null) {
         log.info("존재하지 않는 이메일입니다.");
         result = "0";
      }else if(service.email(email) != null) {
         log.info("존재하는 이메일");
         result="1";
      }
      
      return new ResponseEntity<>(result , HttpStatus.OK);
   }
   
   @PostMapping("/id_chk")
   @ResponseBody
   public ResponseEntity<String> idchk(String id) {
      log.info("idchk controller.... ");
      
      String result = "";
      
      if(service.id(id) == null) {
         log.info("존재하지 않는 아이디입니다.");
         result = "0";
      }else if(service.id(id) != null) {
         log.info(id);
         result="1";
      }   
      return new ResponseEntity<>(result , HttpStatus.OK);
   }
   
   @PostMapping("/nickname_chk")
   @ResponseBody
   public ResponseEntity<String> nicknamechk(@RequestBody MemberDTO dto) {
      String nickname = dto.getNickname();
      
      log.info("nickname check controller.... ");
      
      String result = "";
      
      if(service.nickname(nickname) == null) {
         log.info("존재하지 않는 닉네임입니다.");
         result = "0";
      }else if(service.nickname(nickname) != null) {
         log.info(nickname);
         result="1";
      }   
      return new ResponseEntity<>(result , HttpStatus.OK);
   }
}