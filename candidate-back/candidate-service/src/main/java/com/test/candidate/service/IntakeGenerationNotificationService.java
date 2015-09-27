package com.test.candidate.service;

/**
 * candidate
 *
 * @author Ben
 * @since 27/09/2015
 */
public interface IntakeGenerationNotificationService {

    /**
     * Notify the intake generation service that a new candidate has been created.
     *
     * @param id of the newly created candidate
     */
    void notify(int id);
}
