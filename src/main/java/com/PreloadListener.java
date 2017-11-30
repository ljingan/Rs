package com;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class PreloadListener implements ServletContextListener {
    public void contextDestroyed(ServletContextEvent e) {

    }

    public void contextInitialized(ServletContextEvent e) {
        System.out.print("ttttttttttttttt");
    }
}
