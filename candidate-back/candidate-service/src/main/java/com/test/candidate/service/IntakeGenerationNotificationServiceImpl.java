package com.test.candidate.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * candidate
 *
 * @author Ben
 * @since 27/09/2015
 */
public class IntakeGenerationNotificationServiceImpl implements IntakeGenerationNotificationService {

    private static final Logger LOG = LoggerFactory
            .getLogger(CandidateServiceImpl.class);

    private final String intakeServiceNotificationUrl;

    private final RestTemplate restTemplate;

    @Autowired
    public IntakeGenerationNotificationServiceImpl(String intakeServiceNotificationUrl, RestTemplate restTemplate) {
        this.intakeServiceNotificationUrl = intakeServiceNotificationUrl;
        this.restTemplate = restTemplate;
    }

    @Override
    public void notify(int id) {
        ResponseEntity<String> response = restTemplate.postForEntity(intakeServiceNotificationUrl, id, String.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            LOG.info("successfully notified intake generation service");
        } else {
            LOG.error("Could not notify intake generation service");
        }
    }
}
