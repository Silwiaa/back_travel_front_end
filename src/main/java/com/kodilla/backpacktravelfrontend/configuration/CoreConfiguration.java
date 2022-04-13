package com.kodilla.backpacktravelfrontend.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CoreConfiguration {

    @Bean
    public static RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
