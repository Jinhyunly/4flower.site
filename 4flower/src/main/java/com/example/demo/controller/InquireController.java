package com.example.demo.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.email.Mail;
import com.example.demo.security.UserInfo;
import com.example.demo.security.service.MyUserDetails;
import com.example.demo.service.inquire.InquireService;
import com.example.demo.service.inquire.SendEmailService;

@Controller
public class InquireController {
		@Autowired
	  private InquireService inquireService;
		@Autowired
		private SendEmailService sendEmailService;


		@RequestMapping(value = "/inquire")
		public ModelAndView inquire(Principal principal) {
			System.out.println("inquire controller start");

			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("inquire");

			return modelAndView;
		}



		@PostMapping(value = "/inquire/sendmail")
		public ModelAndView sendMailPost(@Valid Mail mail, BindingResult bindingResult, Principal principal) {

			ModelAndView modelAndView = new ModelAndView();
			String message= "";
			try{
				Authentication authentication = (Authentication) principal;
				MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
				UserInfo userInfo = userDetails.getUserInfo();
				modelAndView.addObject("userName", userInfo.getUserName());


			}catch(Exception e) {
			}
			List<String> receivers = new ArrayList<String>();
			receivers.add("dbdbddipp@gmail.com");
			try {
				sendEmailService.send(mail.getMailSubject(), "From Name : " + mail.getFromName() + "<br>From MailAddress : " + mail.getMailFrom() + "<br>phone Number : " + mail.getPhoneNum()+"<br><br>" + mail.getMailContent().replaceAll("\r\n", "<br>"), receivers);
			}catch(Exception e) {
				message= e.getMessage();
			}


			if(message == "") {
				message= "Send Mail Success.";
			}
			modelAndView.addObject("message", message);
			modelAndView.setViewName("inquire");

			return modelAndView;
		}
}
