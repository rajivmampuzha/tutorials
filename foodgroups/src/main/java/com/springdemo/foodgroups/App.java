package com.springdemo.foodgroups;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.springdemo.foodgroups.dao.FoodGroupDao;
import com.springdemo.foodgroups.model.FoodGroup;

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
    	
    	System.out.println(dao.findFoodGroupById(1));
    	System.out.println(dao.findFoodGroupById(2));

    	System.out.println(dao.findFoodGroupById(3));

    	
    	System.out.println(dao.addFoodGroup("Test", "Not a real food group"));
    	
    	System.out.println(dao.create(new FoodGroup("nametest","descriptionTest")));

    	
    	System.out.println(dao.update(new FoodGroup(7, "nametest-updated","descriptionTest-update" + Math.random())));

    	System.out.println(dao.delete((6)));

    	
    	for (String string : context.getBeanDefinitionNames()) {
    		System.out.println(string);
		}
    	context.close();
    }
}
