package com.bit2016.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bit2016.mysite.service.GalleryService;
import com.bit2016.mysite.vo.Gallery;
import com.bit2016.mysite.vo.Users;
import com.bit2016.security.Auth;
import com.bit2016.security.AuthUser;

@Controller
@RequestMapping("/gallery")
public class GalleryController {

	@Autowired
	private GalleryService galleryService;

	@RequestMapping("")
	public String index(Model model) {
		List<Gallery> list = galleryService.selectAll();
		model.addAttribute("list", list);

		return "gallery/index";
	}

	@Auth
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(@RequestParam("file") MultipartFile file, @RequestParam("comments") String comments,
			Model model, @AuthUser Users authUser) {
		galleryService.restore(file, comments, authUser.getNo());
		return "redirect:/gallery";
	}

	@RequestMapping("/view")
	public String view(@RequestParam("no") Long no, Model model) {
		Gallery vo = galleryService.selectByNo(no);
		model.addAttribute("vo", vo);
		return "gallery/view";
	}

	@RequestMapping("/delete")
	public String delete(@ModelAttribute Gallery vo, Model model) {
		galleryService.delete(vo);
		return "redirect:/gallery";
	}
}
