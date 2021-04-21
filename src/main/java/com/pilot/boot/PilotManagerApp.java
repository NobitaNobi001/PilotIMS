package com.pilot.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * main
 * @author ezuy
 * @date 20/12/21 11:16
 */
@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class PilotManagerApp {

    public static void main(String[] args) {
        SpringApplication.run(PilotManagerApp.class, args);
    }
}
