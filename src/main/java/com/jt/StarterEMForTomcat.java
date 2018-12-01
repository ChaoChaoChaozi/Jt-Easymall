package com.jt;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

public class StarterEMForTomcat extends SpringBootServletInitializer{
	
	//覆盖父类的一个方法
		@Override
		protected SpringApplicationBuilder configure
		(SpringApplicationBuilder builder) {
			//调用builder构造对象,完成当前的方法指向main方法的过程
			return builder.sources(StarterEM.class);
		}
		
}
