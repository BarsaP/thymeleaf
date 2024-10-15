package com.practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {
	@RequestMapping("/about")
    public String aboutpage() {
        return "about";  // Returns the view (home.html or home.jsp depending on your setup)
    }
	
	@RequestMapping("/player")
    public String playerpage() {
        return "player";  // Returns the view (home.html or home.jsp depending on your setup)
    }
	
	@RequestMapping("/join")
    public String joinpage() {
        return "joinr";  // Returns the view (home.html or home.jsp depending on your setup)
    }
	
}
