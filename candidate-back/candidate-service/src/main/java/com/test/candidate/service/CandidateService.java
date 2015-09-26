package com.test.candidate.service;

import com.test.candidate.service.controller.CandidateForm;

import java.util.List;

/**
 * Created by oleg on 12/08/15.
 */
public interface CandidateService {

    /**
     * Get a list of all candidates
     *
     * @return immutable list of all candidates
     */
    List<CandidateDto> getAllCandidates();

    /**
     * Update a candidate
     *
     * @param id            candidate to update id
     * @param candidateForm new candidate details
     * @return updated candidate
     * @throws EntityNotFoundException when no candidate with specified id can be found
     */
    CandidateDto updateCandidate(int id, CandidateForm candidateForm) throws EntityNotFoundException;

    /**
     * Create a new candidate
     *
     * @param candidateForm new candidate details
     * @return created candidate
     */
    CandidateDto createCandidate(CandidateForm candidateForm);
}
