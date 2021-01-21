package com.pilot.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

/**
 * main
 * @author ezuy
 * @date 20/12/21 11:16
 */
@SpringBootApplication
public class PilotManagerApp {

    public static void main(String[] args) {
        SpringApplication.run(PilotManagerApp.class, args);
    }
}
