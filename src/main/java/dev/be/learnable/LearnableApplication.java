package dev.be.learnable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class LearnableApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnableApplication.class, args);
    }

}
