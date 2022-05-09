package com.expenser.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InitController {

	@GetMapping("")
	public ModelAndView getHome() {
		return new ModelAndView("index");
	}
}
