package com.nnk.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String root() {
		return "redirect:/home";
	}

	@RequestMapping("/home")
	public String homeRedirect(org.springframework.security.core.Authentication authentication) {
		if (authentication != null) {
			boolean isAdmin = authentication.getAuthorities().stream()
					.anyMatch(auth -> auth.getAuthority().equals("ADMIN"));
			boolean isUser = authentication.getAuthorities().stream()
					.anyMatch(auth -> auth.getAuthority().equals("USER"));

			if (isAdmin) {
				return "redirect:/user/list";
			} else if (isUser) {
				return "redirect:/bidList/list";
			}
		}
		return "home";
	}
}
