package com.lz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;


/**
 * @author lz
 */
@SpringBootApplication
@CrossOrigin
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        
    }
}