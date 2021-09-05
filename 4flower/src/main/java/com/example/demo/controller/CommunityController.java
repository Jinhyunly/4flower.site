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

import com.example.demo.entity.community.Community;
import com.example.demo.security.UserInfo;
import com.example.demo.security.service.MyUserDetails;
import com.example.demo.service.community.CommunityService;

@Controller
public class CommunityController {
	@Autowired
  private CommunityService communityService;

	//@RequestMapping(value = "**/community", method = RequestMethod.GET)
	@RequestMapping(value = "/community")
	public ModelAndView community(@ModelAttribute("params") Community params, Principal principal) {

		ModelAndView modelAndView = new ModelAndView();

		try{
			Authentication authentication = (Authentication) principal;
			MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
			UserInfo userInfo = userDetails.getUserInfo();
			modelAndView.addObject("userName", userInfo.getUserName());
//			modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");

		}catch(Exception e) {
		}

		List<Community> communityList = communityService.selectAllCommunity(params);
		modelAndView.addObject("communityList", communityList);

		modelAndView.setViewName("community");


		System.out.println("community controller start");
		return modelAndView;
	}

	@GetMapping(value = "/community/insert")
	public ModelAndView communityInsertGet(@Valid Community community, BindingResult bindingResult, Principal principal) {

		ModelAndView modelAndView = new ModelAndView();

		try{
			Authentication authentication = (Authentication) principal;
			MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
			UserInfo userInfo = userDetails.getUserInfo();
			modelAndView.addObject("userName", userInfo.getUserName());
//			modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");

		}catch(Exception e) {
		}

//		List<Community> communityList = communityService.selectAllCommunity(params);
//		modelAndView.addObject("communityList", communityList);
//
//		modelAndView.setViewName("community");

//		modelAndView.addObject("community", new Community());

		modelAndView.setViewName("communityWrite");

		System.out.println("community controller start");
		return modelAndView;
	}

	@PostMapping(value = "/community/insert")
	public ModelAndView communityInsertPost(@ModelAttribute("params") @Valid Community params, BindingResult bindingResult, Principal principal) {

		ModelAndView modelAndView = new ModelAndView();

		try{
			Authentication authentication = (Authentication) principal;
			MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
			UserInfo userInfo = userDetails.getUserInfo();
			modelAndView.addObject("userName", userInfo.getUserName());
			params.setUser_id(userInfo.getId());

//			modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");

		}catch(Exception e) {
		}

		if(params.getId() == 0) {
			//insert
			communityService.insertCommunity(params);
		}else {
			//update
			communityService.updateCommunity(params);
		}

		return new ModelAndView("redirect:/community");
	}

	@GetMapping("/community/detail/{id}&{pageNum}")
	public ModelAndView communityDetailGet(@PathVariable("id") String id,@PathVariable("pageNum") String pageNum, HttpServletRequest request,Principal principal) throws Exception{

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

		Community community = communityService.selectCommunityById(Integer.parseInt(id));

		community.setCurrentPageNo(Integer.parseInt(pageNum));
		modelAndView.addObject("community", community);

		modelAndView.setViewName("communityDetail");

		return modelAndView;

	}

	@GetMapping("/community/update/{id}")
	public ModelAndView communityUpdateGet(@PathVariable("id") String id,@Valid Community community, HttpServletRequest request,Principal principal) throws Exception{

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

//		Community community2 = communityService.selectCommunityById(Integer.parseInt(id));

//		community.setCurrentPageNo(Integer.parseInt(pageNum));
		modelAndView.addObject("community", community);

		modelAndView.setViewName("communityWrite");

		return modelAndView;

	}



}
