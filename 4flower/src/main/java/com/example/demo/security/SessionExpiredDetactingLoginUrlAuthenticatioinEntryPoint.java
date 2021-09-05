package com.example.demo.security;

import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

public class SessionExpiredDetactingLoginUrlAuthenticatioinEntryPoint extends LoginUrlAuthenticationEntryPoint{

	public SessionExpiredDetactingLoginUrlAuthenticatioinEntryPoint(String loginFormUrl) {
		super(loginFormUrl);
	}

}
