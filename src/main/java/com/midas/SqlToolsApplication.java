package com.midas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author midas
 */
@SpringBootApplication(scanBasePackages = "com.midas")
public class SqlToolsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SqlToolsApplication.class, args);
    }

}

