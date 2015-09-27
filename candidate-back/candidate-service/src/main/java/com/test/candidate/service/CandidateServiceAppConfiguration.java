package com.test.candidate.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * candidate
 *
 * @author Ben
 * @since 27/09/2015
 */
@Configuration
public class CandidateServiceAppConfiguration {

    @Value("${intakeServiceNotificationUrl}")
    private String intakeServiceNotificationUrl;

    @Bean
    public IntakeGenerationNotificationService intakeGenerationNotificationService() {
        RestTemplate restTemplate = new RestTemplate();
        return new IntakeGenerationNotificationServiceImpl(intakeServiceNotificationUrl, restTemplate);
    }
}
