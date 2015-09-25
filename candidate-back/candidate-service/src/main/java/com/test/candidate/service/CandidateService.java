package com.test.candidate.service;

import com.test.candidate.persistence.entity.Candidate;

import java.util.List;

/**
 * Created by oleg on 12/08/15.
 */
public interface CandidateService {

    List<Candidate> getAllCandidates();
}
