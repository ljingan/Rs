package com;

import com.common.net.ServerBootStrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ServerEntry {

    private static final Logger logger = LoggerFactory.getLogger(ServerBootStrap.class);

    @PostConstruct
    public void init() {
        logger.error("start server.....");
        try {
            Thread.sleep(10000);
            final ServerBootStrap bootstrap = new ServerBootStrap();
            bootstrap.start(20006);
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
