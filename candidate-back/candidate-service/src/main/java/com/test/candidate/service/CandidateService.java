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
     * @return
     */
    List<CandidateDto> getAllCandidates();

    /**
     * Update a candidate
     *
     * @param id
     * @param candidateForm
     * @return
     */
    CandidateDto updateCandidate(int id, CandidateForm candidateForm);

    /**
     * Create a new candidate
     *
     * @param candidateForm
     * @return
     */
    CandidateDto createCandidate(CandidateForm candidateForm);
}
