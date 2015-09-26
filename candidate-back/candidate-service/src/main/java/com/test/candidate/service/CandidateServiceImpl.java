package com.test.candidate.service;

import com.test.candidate.persistence.entity.Candidate;
import com.test.candidate.persistence.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * CandidateServiceImpl
 *
 * @author Ben
 * @since 25/09/2015
 */
@Service
@Transactional
public class CandidateServiceImpl implements CandidateService {

    private static final Function<Candidate, CandidateDto> CANDIDATE_TO_CANDIDATE_VO_MAPPER =
            candidate -> new CandidateDto(candidate.getId(), candidate.getName(), candidate.isEnabled());

    private final CandidateRepository candidateRepository;

    @Autowired
    public CandidateServiceImpl(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @Override
    public List<CandidateDto> getAllCandidates() {
        return candidateRepository.findAll()
                .stream()
                .map(CANDIDATE_TO_CANDIDATE_VO_MAPPER)
                .collect(Collectors.toList());
    }
}
