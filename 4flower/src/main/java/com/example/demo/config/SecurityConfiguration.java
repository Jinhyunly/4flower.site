package com.example.demo.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.csrf.MissingCsrfTokenException;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.security.SessionExpiredDetactingLoginUrlAuthenticatioinEntryPoint;
import com.example.demo.security.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

//  @Autowired
//  private UserService userService;

  @Bean
  public DaoAuthenticationProvider authenticationProvider(MyUserDetailsService userService) {//UserService userService
      DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
      authenticationProvider.setUserDetailsService(userService);
      authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
      return authenticationProvider;
  }
  @Override
  public void configure(WebSecurity web) {
//      web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
  	 web.ignoring().antMatchers("/error","/resources/**", "/static/**", "/css/**", "/fonts/**", "/js/**", "/images/**", "/icon/**", "/sass/**");
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      //auth.authenticationProvider(authenticationProvider(userService));
      auth.userDetailsService(myUserDetailsService()).passwordEncoder(bCryptPasswordEncoder);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
      http
          .authorizeRequests()
          		.antMatchers("/resources/**", "/static/**", "/css/**", "/fonts/**", "/js/**", "/images/**", "/icon/**", "/sass/**").permitAll()
              .antMatchers("/","/logout","/community/**","/notice/**","/login","/gallery/**","/registration").permitAll()
              .antMatchers("/gallery/fileinsert").authenticated() //.hasAuthority("ADMIN") // ADMIN 권한의 유저만 /home 에 접근가능
          .anyRequest()
              .authenticated()
              .and().csrf().disable()
          .formLogin()
              .loginPage("/login")
              .failureUrl("/login?error=true")
              //.defaultSuccessUrl("/home")
              .defaultSuccessUrl("/")
              .usernameParameter("loginId")
              .passwordParameter("password")
          .and()
              .logout()
              .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
              //
              .logoutSuccessUrl("/?logout=1")
              .deleteCookies("JSESSIONID","remember-me")	//cookie삭제
              .invalidateHttpSession(true) //session삭제
          .and()
              .exceptionHandling()
              .accessDeniedPage("/access-denied")
              .authenticationEntryPoint(authenticationEntryPoint())
              .accessDeniedHandler(accessDeniedHandler())
		      .and()
		      		.sessionManagement()
		      		.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)

		      		.maximumSessions(1)
		      		.maxSessionsPreventsLogin(false)
		      		.expiredUrl("/login?sessionOut=1")
      				//.sessionRegistry(sessionRegistry())
  				.and()
  						.invalidSessionUrl("/login");

  }

  @Bean
  public UserDetailsService myUserDetailsService() {
  	//기존의 UserService 필요하지않으면 삭제 UserService
  	return new MyUserDetailsService();
  }

  @Bean
  AuthenticationEntryPoint authenticationEntryPoint() {
  	return new SessionExpiredDetactingLoginUrlAuthenticatioinEntryPoint("/login");
  }
  @Bean
  AccessDeniedHandler accessDeniedHandler() {
  	return new AccessDeniedHandler() {
  		@Override
  		public void handle(HttpServletRequest request, HttpServletResponse response,
  				org.springframework.security.access.AccessDeniedException accessDeniedException) throws IOException, ServletException{
  			if(accessDeniedException instanceof MissingCsrfTokenException) {
  				authenticationEntryPoint().commence(request, response, null);
  			}else {
  				new AccessDeniedHandlerImpl().handle(request, response, accessDeniedException);
  			}
  		}
  	};
  }

//  @Bean
//  public SessionRegistry sessionRegistry() {
//      return new SessionRegistryImpl();
//  }
//
//  @Bean
//  public static ServletListenerRegistrationBean httpSessionEventPublisher() {
//      return new ServletListenerRegistrationBean(new HttpSessionEventPublisher());
//  }



}
