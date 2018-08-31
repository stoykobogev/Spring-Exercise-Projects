package org.softuni.eventuresjms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class EventuresJmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventuresJmsApplication.class, args);
    }
}
