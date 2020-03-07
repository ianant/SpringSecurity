package com.anant.mvc.spring.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String welcomePage() {
		
		return "home";
	}
	
	
	//add mapping for managers
	@GetMapping("/leaders")
	public String welcomeManagers() {
		return "managers";
	}
	
	//add mapping for system admins
		@GetMapping("/systems")
		public String welcomeAdmins() {
			return "admins";
		}
	
}
