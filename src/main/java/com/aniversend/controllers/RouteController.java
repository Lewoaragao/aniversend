package com.aniversend.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RouteController {
		
		@GetMapping("/")
		public String index(HttpSession session) {
			session.setAttribute("nameApp", "AniverSend");
			return "index";
		}
		
	}
