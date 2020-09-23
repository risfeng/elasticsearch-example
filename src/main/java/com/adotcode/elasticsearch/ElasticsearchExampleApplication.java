package com.adotcode.elasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;

/**
 * ES示例项目
 *
 * @author risfeng
 * @date 2020/9/18
 */
@SpringBootApplication(exclude = {JacksonAutoConfiguration.class})
public class ElasticsearchExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElasticsearchExampleApplication.class, args);
    }

}
