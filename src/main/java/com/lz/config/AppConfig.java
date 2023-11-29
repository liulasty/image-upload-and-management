package com.lz.config;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: lz
 * @Date: 2023/11/08/10:43
 * @Description:
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lz
 */
@Configuration
public class AppConfig {
    @Value("${jwt.KEY}")
    private String jwtKey;
    
    

    @Bean
    public String getJwtKey() {
        System.out.println("this.KEY:" + this.jwtKey);
        return jwtKey;
    }
}