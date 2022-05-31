package kr.co.vuelog.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class CommonsController {

	@GetMapping("/access/accessError")
	public void accessDenied() {
		log.info("access denied controller...");
		
	}
}
