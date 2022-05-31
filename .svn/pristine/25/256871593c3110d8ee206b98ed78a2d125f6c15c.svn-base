package kr.co.vuelog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.vuelog.member.domain.MemberDTO;
import kr.co.vuelog.member.service.IMemberCheckService;
import kr.co.vuelog.member.service.IMemberService;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/api/login")
@Log4j
public class LoginController {

   @Autowired
   private IMemberCheckService chkservice;
   
   @Autowired
   private IMemberService service;
   
   @Autowired
   private BCryptPasswordEncoder pwencode;
   
   @PostMapping(value = "/", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
   @ResponseBody
   public ResponseEntity<String> login(@RequestBody MemberDTO dto) {
      log.info("check");
      log.info(dto.getEmail());
      
      if(chkservice.email(dto.getEmail()) != null) {
    	 String bpw = chkservice.password(dto);
    	 log.info(bpw);

         if(pwencode.matches(dto.getPassword(), bpw)) {
            return new ResponseEntity<String>("success", HttpStatus.OK);
         }else {
            return new ResponseEntity<String>("fail", HttpStatus.OK);
         }
      }else {
         return new ResponseEntity<String>("fail", HttpStatus.OK);
      }
   }
   
   @PostMapping(value = "/nickname", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
   @ResponseBody
   public ResponseEntity<String> nickname(@RequestBody MemberDTO dto) {
      
      if(service.read(dto.getEmail()) != null) {
         dto.setNickname(service.read(dto.getEmail()).getNickname());
         String nickname = dto.getNickname();
         
         return new ResponseEntity<String>(nickname, HttpStatus.OK);
      }else {
         return new ResponseEntity<String>("fail", HttpStatus.OK);
      }
   }

}