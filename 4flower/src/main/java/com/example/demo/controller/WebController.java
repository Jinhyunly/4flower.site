package com.example.demo.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.security.UserInfo;
import com.example.demo.security.service.MyUserDetails;

@Controller
public class WebController {
	//@RequestMapping(value = "/", method = RequestMethod.GET)

	@RequestMapping("/")
	public ModelAndView home(HttpServletRequest request, HttpSession session, Principal principal) {
		ModelAndView modelAndView = new ModelAndView();

		String logoutFlg = request.getParameter("logout");
    String logoutMessage = "";

    if (logoutFlg != null && !logoutFlg.isEmpty()) {
    	logoutMessage = "logout success";
      modelAndView.addObject("logoutMessage", logoutMessage);
      session.setAttribute("userPrincipal", null);
    }

		try{

			Authentication authentication = (Authentication) principal;
			MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
			UserInfo userInfo = userDetails.getUserInfo();
			modelAndView.addObject("userName", userInfo.getUserName());

			modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");

		}catch(Exception e) {

		}

    modelAndView.setViewName("index");

		System.out.println("home controller start");
		//return "index";
		return modelAndView;
	}

}