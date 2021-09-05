package com.laoxin.code.generator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@PropertySource(value = "classpath:/dao-helper.properties")
@ComponentScan(basePackages = "com.laoxin.code.generator")
@Configuration
public class DaoEnvConfig {

    @Bean
    public PropertySourcesPlaceholderConfigurer propertyConfigurer() {
        //可以对PropertySource配置的环境变量进行解析
        //如果是PropertyPlaceholderConfigurer，实例化的时候需要指定特定的配置文件
        return new PropertySourcesPlaceholderConfigurer();
    }
}
