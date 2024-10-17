package com.practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {
	@RequestMapping("/about")
    public String aboutpage() {
        return "about";  // 
    }
	
	@RequestMapping("/player")
    public String playerpage() {
        return "player";  // 
    }
	
	@RequestMapping("/join")
    public String joinpage() {
        return "joinr";  //
    }
	
}
