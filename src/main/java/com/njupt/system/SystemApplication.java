package com.njupt.system;

import com.njupt.system.exception.CustomError;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class SystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args);
    }

    @Bean
    public CommandLineRunner initServer(){
        return args ->
                CustomError.checkDuplicate();
    }
}
