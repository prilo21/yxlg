package com.yxlg.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DirectorController {

	@RequestMapping("/r/{path1}")
	public String redirct(@PathVariable String path1) {
		return path1;
	}

	@RequestMapping("/r/{path1}/{path2}")
	public String redirct(@PathVariable String path1, @PathVariable String path2) {
		return path1 + "/" + path2;
	}

	@RequestMapping("/r/{path1}/{path2}/{path3}")
	public String redirct(@PathVariable String path1, @PathVariable String path2, @PathVariable String path3) {
		return path1 + "/" + path2 + "/" + path3;
	}
	
}
