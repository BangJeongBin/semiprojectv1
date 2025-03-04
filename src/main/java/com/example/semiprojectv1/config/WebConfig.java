package com.example.semiprojectv1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

/* spring boot 실행 시 먼저 실행 */
@Configuration
public class WebConfig {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        // UTF-8 인코딩 설정
        restTemplate.getMessageConverters().add(
                0, new StringHttpMessageConverter(StandardCharsets.UTF_8)
        );

        return restTemplate;
    }
}
