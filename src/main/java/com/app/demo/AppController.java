package com.app.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.app.memcached.MemcachedDemo;

@Controller
@RequestMapping("/")
@SessionAttributes({"demo"})
public class AppController {
	
	@Resource
	MemcachedDemo memcachedDemo;

	@RequestMapping(value = "app")
	public ModelAndView appIndex(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("/app/index");
		return mav;
	}

	@RequestMapping(value = "mem")
	public ModelAndView mem(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println(memcachedDemo.getString("test"));
		ModelAndView mav = new ModelAndView("/app/mem");
		mav.addObject("demo", memcachedDemo.getString("test"));
		return mav;
	}
}
