package com.mygym.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//@ControllerAdvice
@Controller
public class ErrorController {

	@RequestMapping("/access-denied")
	public String error() {
		return "error/access-denied";
	}
}
