package com.example.demo.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.notice.Notice;
import com.example.demo.security.UserInfo;
import com.example.demo.security.service.MyUserDetails;
import com.example.demo.service.notice.NoticeService;

@Controller
public class NoticeController {
	@Autowired
  private NoticeService noticeService;

	//@RequestMapping(value = "**/notice", method = RequestMethod.GET)
	@RequestMapping(value = "/notice")
	public ModelAndView notice(@ModelAttribute("params") Notice params, Principal principal) {

		ModelAndView modelAndView = new ModelAndView();

		try{
			Authentication authentication = (Authentication) principal;
			MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
			UserInfo userInfo = userDetails.getUserInfo();
			modelAndView.addObject("userName", userInfo.getUserName());
//			modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");

		}catch(Exception e) {
		}

		List<Notice> noticeList = noticeService.selectAllNotice(params);
		modelAndView.addObject("noticeList", noticeList);

		modelAndView.setViewName("notice");


		System.out.println("notice controller start");
		return modelAndView;
	}

	@GetMapping(value = "/notice/insert")
	public ModelAndView noticeInsertGet(@Valid Notice notice, BindingResult bindingResult, Principal principal) {

		ModelAndView modelAndView = new ModelAndView();

		try{
			Authentication authentication = (Authentication) principal;
			MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
			UserInfo userInfo = userDetails.getUserInfo();
			modelAndView.addObject("userName", userInfo.getUserName());
//			modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");

		}catch(Exception e) {
		}

//		List<Notice> noticeList = noticeService.selectAllNotice(params);
//		modelAndView.addObject("noticeList", noticeList);
//
//		modelAndView.setViewName("notice");

//		modelAndView.addObject("notice", new Notice());

		modelAndView.setViewName("noticeWrite");

		System.out.println("notice controller start");
		return modelAndView;
	}

	@PostMapping(value = "/notice/insert")
	public ModelAndView noticeInsertPost(@ModelAttribute("params") @Valid Notice params, BindingResult bindingResult, Principal principal) {

		ModelAndView modelAndView = new ModelAndView();

		try{
			Authentication authentication = (Authentication) principal;
			MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
			UserInfo userInfo = userDetails.getUserInfo();
			modelAndView.addObject("userName", userInfo.getUserName());
//			modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");

		}catch(Exception e) {
		}

		if(params.getId() == 0) {
			//insert
			noticeService.insertNotice(params);
		}else {
			//update
			noticeService.updateNotice(params);
		}

		return new ModelAndView("redirect:/notice");
	}

	@GetMapping("/notice/detail/{id}&{pageNum}")
	public ModelAndView noticeDetailGet(@PathVariable("id") String id,@PathVariable("pageNum") String pageNum, HttpServletRequest request,Principal principal) throws Exception{

		ModelAndView modelAndView = new ModelAndView();

		try{
			Authentication authentication = (Authentication) principal;
			MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
			UserInfo userInfo = userDetails.getUserInfo();
			modelAndView.addObject("userName", userInfo.getUserName());

			if("momo".equals(userInfo.getLoginId())){
				modelAndView.addObject("userId", userInfo.getLoginId());
			}
		}catch(Exception e){

		}

		Notice notice = noticeService.selectNoticeById(Integer.parseInt(id));

		notice.setCurrentPageNo(Integer.parseInt(pageNum));
		modelAndView.addObject("notice", notice);

		modelAndView.setViewName("noticeDetail");

		return modelAndView;

	}

	@GetMapping("/notice/update/{id}")
	public ModelAndView noticeUpdateGet(@PathVariable("id") String id,@Valid Notice notice, HttpServletRequest request,Principal principal) throws Exception{

		ModelAndView modelAndView = new ModelAndView();

		try{
			Authentication authentication = (Authentication) principal;
			MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
			UserInfo userInfo = userDetails.getUserInfo();
			modelAndView.addObject("userName", userInfo.getUserName());

			if("momo".equals(userInfo.getLoginId())){
				modelAndView.addObject("userId", userInfo.getLoginId());
			}
		}catch(Exception e){

		}

//		Notice notice2 = noticeService.selectNoticeById(Integer.parseInt(id));

//		notice.setCurrentPageNo(Integer.parseInt(pageNum));
		modelAndView.addObject("notice", notice);

		modelAndView.setViewName("noticeWrite");

		return modelAndView;

	}
}
