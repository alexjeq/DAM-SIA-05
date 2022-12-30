package org.scrum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.util.logging.Logger;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class CarRentalSpringApplication extends SpringBootServletInitializer {

    private static Logger logger = Logger.getLogger(CarRentalSpringApplication.class.getName());

    public static void main(String[] args) {
        logger.info("Loading ... CarRentalSpringApplication Default Settings ... ");
        SpringApplication.run(CarRentalSpringApplication.class, args);
    }

}
