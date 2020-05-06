package com.cg;

import java.util.HashMap;

import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cg.flight.util.MailConstants;
import com.cg.flight.entity.User;
import com.cg.flight.web.AdminInterceptor;
import com.cg.flight.web.LoginInterceptor;

@SpringBootApplication
public class FlightManagementSystemApplication implements WebMvcConfigurer {

	static Logger logger = LoggerFactory.getLogger(FlightManagementSystemApplication.class);
	
	public static void main(String[] args) {
		logger.debug("Bootstraping the application");
		SpringApplication.run(FlightManagementSystemApplication.class, args);
	}

	@Autowired
	private LoginInterceptor loginInterceptor;
	
	@Autowired
	private AdminInterceptor adminInterceptor;
	
	@Bean(name="authenticatemap")
	public Map<String,User> getAuthenticateMap(){
		return new HashMap<>();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	
		registry.addInterceptor(loginInterceptor).addPathPatterns(new String[] {"/addbooking","/cancelbooking/*","/viewuser/*",
				"/viewpassenger/*","/viewbooking/*","/viewschedule/*/*/*"});
		registry.addInterceptor(adminInterceptor).addPathPatterns(new String[] {"/addschedule","/editschedule"
				});

	}
	
	@Autowired
	private Environment environment;
	
	@Bean(name="mailprops")
	public Properties getMailProperties() {
		
		Properties props = System.getProperties();
		props.setProperty(MailConstants.HOST,environment.getProperty(MailConstants.HOST));
		props.setProperty(MailConstants.PORT,environment.getProperty(MailConstants.PORT));
		props.setProperty(MailConstants.AUTH,environment.getProperty(MailConstants.AUTH));
		props.setProperty(MailConstants.STARTTLS,environment.getProperty(MailConstants.STARTTLS));
		
		return props;
		
	}
		
		
	}
		
		
	
