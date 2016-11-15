package com.bit2016.mysite.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bit2016.mysite.service.UserService;
import com.bit2016.mysite.vo.Users;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(@ModelAttribute Users vo) {
		return "user/joinform";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute @Valid Users vo, BindingResult result) {
		if (result.hasErrors()) {
			return "user/joinform";
		}
		userService.join(vo);
		return "redirect:/user/joinsuccess";
	}

	@RequestMapping("/loginform")
	public String loginForm() {
		return "user/loginform";
	}

	@RequestMapping("/joinsuccess")
	public String joinSuccess() {
		return "user/joinsuccess";
	}

	@RequestMapping("/modifyform")
	public String modifyForm(HttpSession session, Model model) {
		Users authUser = (Users) session.getAttribute("authUser");
		// 접근제한
		if (authUser == null) {
			return "redirect:/user/loginform";
		}
		Users users = userService.getUser(authUser.getNo());
		model.addAttribute("users", users);
		return "user/modifyform";
	}

	@RequestMapping("/modify")
	public String modify(HttpSession session, @ModelAttribute Users vo) {
		Users authUser = (Users) session.getAttribute("authUser");
		// 접근제한
		if (authUser == null) {
			return "redirect:/user/loginform";
		}
		vo.setNo(authUser.getNo());
		authUser.setName(vo.getName());
		session.setAttribute("authUser", authUser);
		userService.modify(vo);
		return "redirect:/user/modifyform?result=success";
	}

}
