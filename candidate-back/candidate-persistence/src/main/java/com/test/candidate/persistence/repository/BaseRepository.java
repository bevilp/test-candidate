package com.test.candidate.persistence.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * BaseRepository
 *
 * Base repository interface
 *
 * @author Ben
 * @since 25/09/2015
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends Repository<T, ID> {

    /**
     * find all the entities
     *
     * @return list of entities
     */
    List<T> findAll();
}
