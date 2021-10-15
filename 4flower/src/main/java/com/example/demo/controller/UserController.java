package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.user.User;
import com.example.demo.service.user.UserService;
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public ModelAndView getLoginPage(HttpServletRequest request) {

    	ModelAndView modelAndView = new ModelAndView();
  		String sessionFlg = request.getParameter("sessionOut");
  		String sessionMessage = "";

  		if (sessionFlg != null && !sessionFlg.isEmpty()) {
  			sessionMessage = "The session has ended, so please log in again.";
        modelAndView.addObject("sessiontMessage", sessionMessage);
      }

  		String errorFlg = request.getParameter("error");
  		String loginErrMessage = "";

  		if (errorFlg != null && !errorFlg.isEmpty()) {
  			loginErrMessage = "ID password is invalid or disabled.";
        modelAndView.addObject("loginErrMessage", loginErrMessage);
      }

      modelAndView.setViewName("login");
      return modelAndView;
    }

    @RequestMapping(value = "logout")
    public ModelAndView getLogoutPage() {


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/");
        return modelAndView;
    }

    @GetMapping("registration")
    public ModelAndView getRegistrationPage() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");

        return modelAndView;
    }

    @PostMapping("registration")
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        User userExists = userService.findUserByLoginId(user.getLoginId());
        if (userExists != null) {
            bindingResult
                .rejectValue("loginId", "error.loginId",
                    "There is already a user registered with the loginId provided.");
        }

        if(!user.getPassword().equals(user.getPasswordConfirm())) {
        	bindingResult
          .rejectValue("loginId", "error.loginId",
              "Please reconfirm your password.");
        }

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registration");
        }

        return modelAndView;
    }

}