package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
	@Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
			//registry.addResourceHandler("/static/**").addResourceLocations("/static/");
//      registry.addResourceHandler("/css/**").addResourceLocations("/css/");
//      registry.addResourceHandler("/js/**").addResourceLocations("/js/");
//      registry.addResourceHandler("/img/**").addResourceLocations("/img/");
//      registry.addResourceHandler("/resources/**").addResourceLocations("/resources/static/css").setCachePeriod(31556926);
//      registry.addResourceHandler("/resources/**").addResourceLocations("/resources/static/images").setCachePeriod(31556926);
			registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
      //super.addResourceHandlers(registry);
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
      BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
      return bCryptPasswordEncoder;
  }
}
