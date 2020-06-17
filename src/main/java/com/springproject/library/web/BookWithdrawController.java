package com.springproject.library.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springproject.library.service.BookWithdrawService;

@Controller
public class BookWithdrawController {
	@Autowired
	BookWithdrawService bookWithdrawService;

	@GetMapping("/borrowed")
	public ModelAndView get() {
		
		return new ModelAndView("borrowed").addObject("bookWithdraws", bookWithdrawService.getAll());

	}
	
}
