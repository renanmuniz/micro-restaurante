package com.microcreditcard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.UUID;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableSwagger2
public class MicroCreditcardApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroCreditcardApplication.class, args);
    }

}
