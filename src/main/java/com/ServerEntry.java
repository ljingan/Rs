package com;

import com.common.net.ServerBootStrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ServerEntry {

    private static final Logger logger = LoggerFactory.getLogger(ServerBootStrap.class);

    public static void main(String[] args) {
//        log.error("start server.....");
        Context context = new Context();
        context.initialize();
        logger.error("start server socket...");
//        init();
    }

    @PostConstruct
    private void initialsize() {
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(3000);
                    System.out.println("start start start start");
                    init();
                } catch (InterruptedException e) {
                }
            }
        }.start();
    }

//    @PostConstruct
    public void init() {
        logger.error("start server.....");
        try {
            final ServerBootStrap bootstrap = new ServerBootStrap();
            bootstrap.start(8099);
            logger.error("finish start server socket...");
            logger.error("finish start server.....");

            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                public void run() {
                    bootstrap.stop();
                }
            }));
        } catch (Exception e) {
            logger.error("server error", e);
        }
    }

}
