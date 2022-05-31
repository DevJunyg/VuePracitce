package kr.co.vuelog;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Handles requests for the application home page.
 */
@Controller
public class DefaultController {

    @GetMapping(value = {""}, produces = {"text/html"})
    public String index(HttpServletRequest req) {
        return "index";
    }
}

