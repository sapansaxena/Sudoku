package com.sudoku.impl.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration 
@ComponentScan("com.sudoku.impl") 
@EnableWebMvc   
@EnableAspectJAutoProxy

public class RestWsConfig {
}
