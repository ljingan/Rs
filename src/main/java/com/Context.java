package com;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Context {
    private static Logger log = LoggerFactory.getLogger(Context.class);

    private static ApplicationContext _instance;

    public static ApplicationContext getContext() {
        if (_instance != null) {
            return _instance;
        }
        throw new RuntimeException("please wait start");
    }

    /**
     * 启动Spring容器
     */
    public void initialize() {
        log.error("start Spring context....");
        _instance = new ClassPathXmlApplicationContext(
                new String[] { "SpringConfig.xml" });
//        LoginBean loginBean = getContext().getBean(LoginBean.class);
//        loginBean.login();
    }

    @SuppressWarnings("unchecked")
    public <T> T getBean(String name) {
        return (T) getContext().getBean(name);
    }

    public <T> T getBean(Class<T> beanClazz) {
        String[] names = getContext().getBeanNamesForType(beanClazz);
        if (names != null && names.length > 0) {
            if (names.length == 1) {
                T bean = getBean(names[0]);
                return bean;
            } else {
                log.error("接口 {} 不少于一个实现类", beanClazz);
            }
        } else {
            log.error("接口 {} 没有实现类", beanClazz);
        }
        return null;
    }
}