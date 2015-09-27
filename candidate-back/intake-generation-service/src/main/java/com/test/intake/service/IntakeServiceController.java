package com.test.intake.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * candidate
 *
 * @author Ben
 * @since 27/09/2015
 */
@RestController
public class IntakeServiceController {

    private static final Logger LOG = LoggerFactory
            .getLogger(IntakeServiceController.class);

    @RequestMapping(value = "/notify", method = RequestMethod.POST)
    public void candidateCreated(@RequestBody CandidateCreatedNotification candidateCreatedNotification) {
        LOG.info("received candidate created notification for candidate id[{}]", candidateCreatedNotification.getId());
    }
}
