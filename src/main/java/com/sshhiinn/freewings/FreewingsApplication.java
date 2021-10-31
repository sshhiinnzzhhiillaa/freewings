package com.sshhiinn.freewings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FreewingsApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(FreewingsApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
