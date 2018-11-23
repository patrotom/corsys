package cz.cvut.fit.corsys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CorsysApplication {

    private static final Logger LOG = LoggerFactory.getLogger(CorsysApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CorsysApplication.class, args);
        LOG.info("INFooo");
    }
}
