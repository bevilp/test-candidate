package com.test.candidate.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * candidate
 *
 * @author Ben
 * @since 27/09/2015
 */
@Service
public class IntakeGenerationNotificationServiceImpl implements IntakeGenerationNotificationService {

    private static final Logger LOG = LoggerFactory
            .getLogger(CandidateServiceImpl.class);

    // this should be moved to application.yml
    private static final String INTAKE_SERVICE_NOTIFICATION_URL = "http://CANDIDATE-INTAKE-GENERATION-SERVICE/intake-generation-service/notify";

    private final RestTemplate restTemplate;

    @Autowired
    public IntakeGenerationNotificationServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void notify(int id) {
        ResponseEntity<String> response = restTemplate.postForEntity(INTAKE_SERVICE_NOTIFICATION_URL, id, String.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            LOG.info("successfully notified intake generation service");
        } else {
            LOG.error("Could not notify intake generation service");
        }
    }
}
