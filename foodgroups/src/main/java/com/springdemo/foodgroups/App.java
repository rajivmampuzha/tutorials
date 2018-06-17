package com.springdemo.foodgroups;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.springdemo.foodgroups.dao.FoodGroupDao;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    	FoodGroupDao dao = context.getBean("foodGroupDao",FoodGroupDao.class);
    	System.out.println(dao.listFoodGroups());
    	
    	for (String string : context.getBeanDefinitionNames()) {
    		System.out.println(string);
		}
    	context.close();
    }
}
