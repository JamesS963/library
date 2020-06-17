package com.springproject.library.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.springproject.library.model.User;
import com.springproject.library.service.BookWithdrawService;

@Controller
public class BookWithdrawController {
	@Autowired
	BookWithdrawService bookWithdrawService;

	@GetMapping("/borrowed")
	public ModelAndView get() {

		return new ModelAndView("borrowed").addObject("bookWithdraws", bookWithdrawService.getAll());

	}

	@GetMapping("/myRentedBooks")
	public ModelAndView myRentedBooks(@AuthenticationPrincipal User user) {
		
		return new ModelAndView("myBorrowed").addObject("bookWithdraws", bookWithdrawService.getByUser(user));
		
		}
	
	@GetMapping("/bookWithdraw/return/{id}")
	public ModelAndView get(@PathVariable Long id) {
		bookWithdrawService.delete(id);
		return new ModelAndView("borrowed");
	}

}
