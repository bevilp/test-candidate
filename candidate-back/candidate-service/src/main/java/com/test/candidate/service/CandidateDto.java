package com.test.candidate.service;

/**
 * CandidateDto
 *
 * @author Ben
 * @since 26/09/2015
 */
public class CandidateDto {

    private final int id;

    private final String name;

    private final boolean enabled;

    public CandidateDto(int id, String name, boolean enabled) {
        this.id = id;
        this.name = name;
        this.enabled = enabled;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
