package kr.co.vuelog.member.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Random;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import kr.co.vuelog.member.domain.MemberDTO;
import kr.co.vuelog.member.mapper.MemberMapper;
import kr.co.vuelog.member.service.IMailSenderService;
import kr.co.vuelog.member.service.util.MailUtils;

@Service("mss")
public class MailSenderServiceImpl implements IMailSenderService {
	
	private int size;
	
	@Autowired
	private JavaMailSenderImpl mailSender;
	
	@Autowired
	private MemberMapper memberMapper;
	
	// 인증키 생성
	private String getKey(int size) {
		this.size = size;
		return getAuthCode();
	}
	
	 //인증코드 난수 발생
    private String getAuthCode() {
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        int num = 0;

        while(buffer.length() < size) {
            num = random.nextInt(10);
            buffer.append(num);
        }

        return buffer.toString();
    }
	
    // 인증 메일 보내기
	@Override
	public int sendAuthMail(MemberDTO mDto) {
		
		int result = 0;
		
		//6자리 난수 인증번호 생성
        String authkey = getKey(6);
        
        String email = mDto.getEmail();
        
        mDto.setAuthkey(authkey);
        
        //인증메일 보내기
        try {
            MailUtils sendMail = new MailUtils(mailSender);
            sendMail.setSubject("회원가입 이메일 인증");
            sendMail.setText(new StringBuffer().append("<h1>[이메일 인증]</h1>")
            .append("<p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.</p>")
            .append("<a href='http://localhost:9988/member/authMail/")
            .append(authkey)
            .append("' target='_blenk'>이메일 인증 확인</a>")
            .toString());
            sendMail.setFrom("메일주소", "보내는 이");
            sendMail.setTo(email);
            sendMail.send();
            
            result = memberMapper.setAuthKey(mDto);
            
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        
          return result;
	}

}
