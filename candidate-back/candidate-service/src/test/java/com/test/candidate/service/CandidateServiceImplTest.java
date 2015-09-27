package com.test.candidate.service;

import com.test.candidate.persistence.entity.Candidate;
import com.test.candidate.persistence.repository.CandidateRepository;
import com.test.candidate.service.controller.CandidateForm;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * candidate
 *
 * @author Ben
 * @since 25/09/2015
 */
@RunWith(MockitoJUnitRunner.class)
public class CandidateServiceImplTest {

    @InjectMocks
    private CandidateServiceImpl candidateService;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private IntakeGenerationNotificationService intakeGenerationNotificationService;

    private final List<Candidate> candidates = new ArrayList<>();

    {
        candidates.add(new Candidate("john", true));
        candidates.add(new Candidate("alex", true));
        candidates.add(new Candidate("joel", true));
    }

    @Test
    public void findAllCandidatesTest() {
        // we expect the repository to be called and to return the candidates list
        when(candidateRepository.findAll()).thenReturn(candidates);

        List<CandidateDto> candidates = candidateService.getAllCandidates();

        assertNotNull(candidates);
        Assert.assertEquals(3, candidates.size());
    }

    @Test
    public void insertCandidateTest() {
        when(candidateRepository.save(any(Candidate.class))).thenReturn(new Candidate("Ben", false));
        verify(intakeGenerationNotificationService).notify(anyInt());

        CandidateDto candidateDto = candidateService.createCandidate(new CandidateForm("Ben", false));

        assertNotNull(candidateDto);
        assertEquals("Ben", candidateDto.getName());
        assertFalse(candidateDto.isEnabled());
    }

    @Test(expected = IllegalArgumentException.class)
    public void insertCandidateNullFormTest() {
        candidateService.createCandidate(null);
    }

    @Test
    public void updateCandidateTest() throws EntityNotFoundException {
        int candidateId = 1;
        Candidate candidate = new Candidate("Benoit", true);
        when(candidateRepository.findOne(candidateId)).thenReturn(candidate);

        Candidate updatedCandidate = new Candidate("Ben", false);
        when(candidateRepository.save(any(Candidate.class))).thenReturn(updatedCandidate);

        CandidateDto candidateDto = candidateService.updateCandidate(1, new CandidateForm("Ben", false));

        assertNotNull(candidateDto);
        assertEquals("Ben", candidateDto.getName());
        assertFalse(candidateDto.isEnabled());
    }

    @Test(expected = EntityNotFoundException.class)
    public void updateNonExistingCandidateTest() throws EntityNotFoundException {
        int candidateId = 10;
        when(candidateRepository.findOne(candidateId)).thenReturn(null);

        candidateService.updateCandidate(candidateId, new CandidateForm("test", false));
    }
}
