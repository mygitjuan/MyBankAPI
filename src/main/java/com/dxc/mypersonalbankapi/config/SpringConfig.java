package com.dxc.mypersonalbankapi.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
//@Import({ReposConfig.class, ControllerConfig.class})
//@PropertySource("classpath:config.properties")
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = {"com.dxc.mypersonalbankapi.persistencia"})
@EntityScan("com.dxc.mypersonalbankapi.modelos")
/*
@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = {"com.banana.persistence", "com.banana.services"})
@EntityScan("com.banana.models")
 */

public class SpringConfig {
    /*@Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }*/
}
