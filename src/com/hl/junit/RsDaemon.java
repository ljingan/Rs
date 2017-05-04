package com.hl.junit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hl.demo.Chinese;

public class RsDaemon {

	@Autowired
	private Chinese chinese;

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"spring-config.xml");
		RsDaemon a = (RsDaemon) ctx.getBean("rsDaemon");
		a.say();
	}

	public void say() {
		System.out.println("hello world");
		chinese.eat();
		chinese.walk();
	}
}
