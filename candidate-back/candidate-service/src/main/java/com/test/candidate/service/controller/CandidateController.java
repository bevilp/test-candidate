package com.test.candidate.service.controller;

import com.test.candidate.service.CandidateDto;
import com.test.candidate.service.CandidateService;
import com.test.candidate.service.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    /**
     * Update the candidate with specified id
     *
     * @param id            of the candidate to updateCandidate
     * @param candidateForm candidate details
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public CandidateDto updateCandidate(@PathVariable("id") int id, @RequestBody @Valid CandidateForm candidateForm) throws EntityNotFoundException {
        return candidateService.updateCandidate(id, candidateForm);
    }

    /**
     * Create a new candidate
     *
     * @param candidateForm candidate details
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public CandidateDto createCandidate(@RequestBody @Valid CandidateForm candidateForm) {
        return candidateService.createCandidate(candidateForm);
    }
}
