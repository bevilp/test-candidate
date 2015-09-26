package com.test.candidate.service;

import com.google.common.collect.ImmutableList;
import com.test.candidate.persistence.entity.Candidate;
import com.test.candidate.persistence.repository.CandidateRepository;
import com.test.candidate.service.controller.CandidateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
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
        return ImmutableList.copyOf(candidateRepository.findAll()
                .stream()
                .map(CANDIDATE_TO_CANDIDATE_VO_MAPPER)
                .collect(Collectors.toList()));
    }

    @Override
    public CandidateDto updateCandidate(int id, @NotNull CandidateForm candidateForm) throws EntityNotFoundException {
        Assert.notNull(candidateForm);
        Candidate candidate = candidateRepository.findOne(id);
        if (candidate == null) {
            throw new EntityNotFoundException("Could not find candidate with id[" + id + "]");
        }

        candidate.setName(candidateForm.getName());
        candidate.setEnabled(candidateForm.isEnabled());
        candidate = candidateRepository.save(candidate);
        return CANDIDATE_TO_CANDIDATE_VO_MAPPER.apply(candidate);
    }

    @Override
    public CandidateDto createCandidate(@NotNull CandidateForm candidateForm) {
        Assert.notNull(candidateForm);
        Candidate candidate = new Candidate(candidateForm.getName(), candidateForm.isEnabled());
        candidate = candidateRepository.save(candidate);
        return CANDIDATE_TO_CANDIDATE_VO_MAPPER.apply(candidate);
    }
}
