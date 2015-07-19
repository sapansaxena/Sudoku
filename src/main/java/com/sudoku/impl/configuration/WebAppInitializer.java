package com.sudoku.impl.configuration;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;


	  
	public class WebAppInitializer  extends WebMvcConfigurationSupport implements WebApplicationInitializer      {
		public void onStartup(ServletContext servletContext) throws ServletException {  
	        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();  
	        ctx.register(RestWsConfig.class);  
	        ctx.setServletContext(servletContext);    
	        Dynamic dynamic = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));  
	        dynamic.addMapping("/");  
	        dynamic.setLoadOnStartup(1);  
	   }  
		 @Override
		    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		        final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		        final ObjectMapper objectMapper = new ObjectMapper();
		        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		        converter.setObjectMapper(objectMapper);
		        converters.add(converter);
		        super.configureMessageConverters(converters);
		    }
		 @Override
		 public Validator getValidator() {
			 return validator();
		 }
		  @Autowired
		    private MessageSource messageSource;
		 
		    @Bean
		    public LocalValidatorFactoryBean validator() {
		        LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
		        validatorFactoryBean.setValidationMessageSource(messageSource);
		        return validatorFactoryBean;
		    }
	}

