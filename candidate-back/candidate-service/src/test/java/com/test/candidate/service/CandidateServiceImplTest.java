package com.test.candidate.service;

import com.test.candidate.persistence.entity.Candidate;
import com.test.candidate.persistence.repository.CandidateRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

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

    private final List<Candidate> candidates = new ArrayList<>();
    {
        candidates.add(new Candidate("john", true));
        candidates.add(new Candidate("alex", true));
        candidates.add(new Candidate("joel", true));
    }

    @Test
    public void findAllCandidatesTest() {
        // we expect the repository to be called and to return the candidates list
        Mockito.when(candidateRepository.findAll()).thenReturn(candidates);

        List<Candidate> candidates = candidateService.getAllCandidates();

        Assert.assertNotNull(candidates);
        Assert.assertEquals(3, candidates.size());
    }

}
