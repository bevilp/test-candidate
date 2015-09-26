package com.test.candidate.service.controller;

import com.test.candidate.service.CandidateDto;
import com.test.candidate.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Candidate controller
 * <p>
 * Created by oleg on 12/08/15.
 */
@RestController
@RequestMapping("/candidate")
public class CandidateController {

    private final CandidateService candidateService;

    @Autowired
    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    /**
     * Returns a list of all candidates
     *
     * @return list of all candidates
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<CandidateDto> findAll() {
        return candidateService.getAllCandidates();
    }
}
