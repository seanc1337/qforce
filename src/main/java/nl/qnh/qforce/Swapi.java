package nl.qnh.qforce;

import nl.qnh.qforce.resources.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(Configuration.class)
public class Swapi {

    public static void main(String[] args) {
        SpringApplication.run(Swapi.class, args);
    }
}
