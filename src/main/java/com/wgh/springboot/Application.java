package com.wgh.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
@MapperScan("com.wgh.springboot.mapper.*")
@SpringBootApplication//(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages={"com.wgh.springboot.*"})
public class Application /*extends SpringBootServletInitializer*/ {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

   /* @Override//为了打包springboot项目
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }*/
}
