package com.jt.easymall.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(value={"classpath:spring_mvc.xml","classpath:spring-quartz.xml"})
public class ConfigurationEM {

}
