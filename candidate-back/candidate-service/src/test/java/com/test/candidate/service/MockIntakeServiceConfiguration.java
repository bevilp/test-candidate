package com.test.candidate.service;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MockIntakeServiceConfiguration
 *
 * @author Ben
 * @since 27/09/2015
 */
@Configuration
public class MockIntakeServiceConfiguration {

    @Bean
    IntakeGenerationNotificationService intakeGenerationNotificationService() {
        return Mockito.mock(IntakeGenerationNotificationService.class);
    }
}
