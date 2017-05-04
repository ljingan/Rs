package com.hl.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Haha {
	
	@Autowired
	private Chinese chinese;
	
	private static Haha haha = null;
	
	static{
//		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
//		haha = (Haha) ctx.getBean("Haha");
		System.out.println("hi,haha...");
	}
	
	public Haha getInstance(){
		return this.haha;
	}
	
	public void xiix(){
		this.chinese.eat();
		this.chinese.walk();
	}
	
}
