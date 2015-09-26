package com.test.candidate.persistence.repository;

import com.test.candidate.persistence.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Candidate repository
 * <p>
 * Created by oleg on 09/08/15.
 */
@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Integer> {

}
