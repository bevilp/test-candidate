package com.test.candidate.service;

import com.google.common.collect.ImmutableList;
import com.test.candidate.persistence.entity.Candidate;
import com.test.candidate.persistence.repository.CandidateRepository;
import com.test.candidate.service.controller.CandidateForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

    private static final Logger LOG = LoggerFactory
            .getLogger(CandidateServiceImpl.class);

    private static final Function<Candidate, CandidateDto> CANDIDATE_TO_CANDIDATE_VO_MAPPER =
            candidate -> new CandidateDto(candidate.getId(), candidate.getName(), candidate.isEnabled());

    private final CandidateRepository candidateRepository;

    private final IntakeGenerationNotificationService intakeGenerationNotificationService;

    @Autowired
    public CandidateServiceImpl(CandidateRepository candidateRepository, IntakeGenerationNotificationService intakeGenerationNotificationService) {
        this.candidateRepository = candidateRepository;
        this.intakeGenerationNotificationService = intakeGenerationNotificationService;
    }

    @Override
    public List<CandidateDto> getAllCandidates() {
        LOG.info("retrieving all candidates");
        return ImmutableList.copyOf(candidateRepository.findAll()
                .stream()
                .map(CANDIDATE_TO_CANDIDATE_VO_MAPPER)
                .collect(Collectors.toList()));
    }

    @Override
    public CandidateDto updateCandidate(int id, @NotNull CandidateForm candidateForm) throws EntityNotFoundException {
        LOG.info("updating candidate with id[{}] with {}", id, candidateForm);
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
        LOG.info("creating candidate with {}", candidateForm);
        Assert.notNull(candidateForm);
        Candidate candidate = new Candidate(candidateForm.getName(), candidateForm.isEnabled());
        candidate = candidateRepository.save(candidate);

        // notify the intake generation service that a candidate has been created
        intakeGenerationNotificationService.notify(candidate.getId());

        return CANDIDATE_TO_CANDIDATE_VO_MAPPER.apply(candidate);
    }

    @Override
    public void deleteCandidates(List<Integer> candidateIds) {
        LOG.info("deleting candidates with {}", candidateIds);
        List<Candidate> candidatesToDelete = candidateRepository.findAll(candidateIds);
        candidateRepository.delete(candidatesToDelete);
    }

    @Override
    public void deleteCandidate(int id) throws EntityNotFoundException {
        LOG.info("deleting candidate with id[{}]", id);
        try {
            candidateRepository.delete(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Candidate with id[" + id + "] not found. Could not delete.");
        }
    }
}
