package com.springproject.library.web;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springproject.library.form.UserForm;
import com.springproject.library.model.Authority;
import com.springproject.library.model.User;
import com.springproject.library.service.AuthorityService;
import com.springproject.library.service.UserDetailsServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UserController {

	@Autowired
	private UserDetailsServiceImpl userService;
	@Autowired
	private AuthorityService authorityService;

	@GetMapping("/user/create")
	public ModelAndView createUser() {
		UserForm userForm = new UserForm();
		userForm.setAuthorities((List<Authority>) authorityService.getAuthorities());
		return new ModelAndView("register", "user", userForm);
	}

	@PostMapping("/user/create")
	public String postCreateUser(@ModelAttribute("user") UserForm user, Model model) {
		log.info("user is " + user.toString());
		if (userService.loadUserByUsername(user.getUsername()) != null) {
			model.addAttribute("user", user);
			return "register";
		}

		userService.createUser(user.toUser());
		return "redirect:/books";
	}

}
