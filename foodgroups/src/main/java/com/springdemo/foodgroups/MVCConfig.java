package com.springdemo.foodgroups;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MVCConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

	   protected Class<?>[] getRootConfigClasses() {
	        return new Class[]{AppConfig.class};
	    }

	    protected Class<?>[] getServletConfigClasses() {
	        return null;
	    }

	    protected String[] getServletMappings() {
	        return new String[]{"/"};
	    }
}
