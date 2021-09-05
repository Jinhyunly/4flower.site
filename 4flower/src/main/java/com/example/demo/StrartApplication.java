package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StrartApplication {

	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/");
		SpringApplication.run(StrartApplication.class, args);
	}

	//path
//	public static ServletContext getContext() {
//    return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest()
//                .getServletContext();
//	}

}
